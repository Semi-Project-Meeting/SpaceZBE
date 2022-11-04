package com.spaceZ.payment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.spaceZ.reservation.ReservationVO;
import com.spaceZ.serviceInfo.SpaceInfoService;
import com.spaceZ.serviceInfo.SpaceInfoVO;

@Repository
public class PaymentDAOimpl implements PaymentDAO {

	private static final Logger logger = LoggerFactory.getLogger(PaymentDAOimpl.class);

	@Value("${import.imp_key}")
	private String imp_key;

	@Value("${import.imp_secret}")
	private String imp_secret;

	@Autowired
	SpaceInfoService spaceInfoService;

	@Autowired
	HttpSession session;

	// import에서 accesstoken 생성하여 받아오기.
	@Override
	public String getAccessToken() {
		logger.info("imp_key: {}", imp_key);
		logger.info("imp_secret: {}", imp_secret);

		HttpsURLConnection conn = null;
		String token = null;
		try {
			URL url = new URL("https://api.iamport.kr/users/getToken");

			conn = (HttpsURLConnection) url.openConnection();

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			JsonObject json = new JsonObject();

			json.addProperty("imp_key", imp_key);
			json.addProperty("imp_secret", imp_secret);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			bw.write(json.toString());
			bw.flush();
			bw.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			Gson gson = new Gson();
			String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
			logger.info("getAccessToken response : {}", response);

			token = gson.fromJson(response, Map.class).get("access_token").toString();
			logger.info("token: {}", token);
			br.close();

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		conn.disconnect();

		return token;
	}

	// imp_uid로 아임포트 서버에서 결제 및 결제 정보 조회
	@Override
	public int getPaymentInfo(ReservationVO vo) {
		String token = getAccessToken();
		int price = vo.getPrice();
		HttpsURLConnection conn = null;
		try {
			URL url = new URL("https://api.iamport.kr/payments/" + vo.getImp_uid());

			conn = (HttpsURLConnection) url.openConnection();

			conn.setRequestMethod("GET");

			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", token);
			conn.setDoOutput(true);
			JsonObject json = new JsonObject();

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			bw.write(json.toString());
			bw.flush();
			bw.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			Gson gson = new Gson();
			String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();
			logger.info("getPaymentInfo response : {}", response);
			JSONObject jsonObj = toJsonObj(response);
			logger.info("getPaymentInfo response toJsonObj  : {}", response);

			price = Integer.parseInt(jsonObj.getString("amount").split("\\.")[0]);
			logger.info("price:{}", price);
			br.close();

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		conn.disconnect();

		return price;
	}

	// 보증금 결제하기
	@Override
	public int depositOK(ReservationVO vo) {
		int flag = 0;
		
		// 계산되어야 할 값과 실제 계산된 값이 맞는지 확인.
		int price = getPaymentInfo(vo);
		logger.info("실제 계산된 돈: {}", price);
		SpaceInfoVO vo2 = spaceInfoService.selectOne(vo.getSpaceId());
		int price2 = (int) (vo2.getPrice() * getReserveTime(vo.getStartDate(), vo.getEndDate()));
		int depositPrice = (int) (price2 * 0.2);
		logger.info("계산되어야할 돈: {}", depositPrice);
		if (price == depositPrice) {
			vo.setPrice(price2 - depositPrice - vo.getMileage());
			reserve(vo);
			flag = 1;
		}
		;
		return flag;
	}

	// 결제정보와 DB 정보와 일치여부 확인
	@Override
	public int verifyPayInfo(ReservationVO vo) {
		int flag = 1;

		// 실제 결제된 가격 확인(인증토큰 발급)
		int price = getPaymentInfo(vo);
		logger.info("실제 계산된 돈: {}", price);
		// 계산되어야할 가격 확인
		SpaceInfoVO vo2 = spaceInfoService.selectOne(vo.getSpaceId());
		int price2 = vo2.getPrice() * getReserveTime(vo.getStartDate(), vo.getEndDate()) - vo.getMileage();
		logger.info("계산되어야할 돈: {}", price2);
		// 가격 비교
		if (price != price2) {
			// 일치하지 않을 시, 오류로 계산된 금액 환불처리
			refund(new RefundVO(vo.getPrepay_uid(), "계산된 금액과 일치하지 않습니다.", price, vo.getMemberId()));
			flag = 0;
		}
		return flag;
	}

	// 후불결제 예약
	@Override
	public int reserve(ReservationVO vo) { // ReservationVO 로 반환해야함.
		int flag = 0;
		String token = getAccessToken();
		String merchant_uid = getRanStr();
		vo.setPostpay_uid(merchant_uid);
		HttpsURLConnection conn = null;

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", vo.getPrice()-vo.getMileage());
		jsonObject.put("schedule_at", getUnixTime(vo.getEndDate()));
		jsonObject.put("merchant_uid", merchant_uid);

		logger.info("jsonObject:{}", jsonObject);

		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse("[" + jsonObject.toString() + "]");
		logger.info("jsonElement:{}", jsonElement);

		try {
			URL url = new URL("https://api.iamport.kr/subscribe/payments/schedule");

			conn = (HttpsURLConnection) url.openConnection();

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", token);
			conn.setDoOutput(true);
			JsonObject json = new JsonObject();

			json.addProperty("customer_uid", vo.getMemberId()); // 가맹점 클라이언트로부터 받은 환불사유
			json.add("schedules", jsonElement); // imp_uid를 환불 `unique key`로 입력
			logger.info("json:{}", json);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			bw.write(json.toString());
			bw.flush();
			bw.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			Gson gson = new Gson();

			@SuppressWarnings("unchecked")
			Map<String, String> result = gson.fromJson(br.readLine(), Map.class);
			logger.info("받아온 데이터: {}", result);
			logger.info("code: {}", result.get("code")); // code 가 0인 것만 아직 환불이 진행되지 않은 거래입니다.

			String response = String.valueOf(result.get("response"));
			logger.info("response: {}", response);

			if (String.valueOf(result.get("code")).equals("0.0")) {
				JSONObject jsonObj = toJsonObj(response);
				System.out.println("response tojson : " + jsonObj);

				String price = jsonObj.getString("amount");
				logger.info("amount:{}", price);
				flag = 1;
			}

			br.close();

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		conn.disconnect();

		return flag;
	}

	// 예약 취소 시, 환불
	@Override
	public int refund(RefundVO vo) {
		int flag = 0;
		logger.info("vo:{}", vo);
		// 결제 취소 시 환불가능 금액 계산

		// 환불 요청
		String token = getAccessToken();
		String price = "";
		HttpsURLConnection conn = null;
		try {
			URL url = new URL("https://api.iamport.kr/payments/cancel");
			JsonObject json = new JsonObject();

			if (vo.getCancel_request_amount() == 0) {
				json.addProperty("customer_uid", vo.getMemberId()); // 가맹점 클라이언트로부터 받은 환불사유
				json.addProperty("merchant_uid", vo.getMerchant_uid()); // imp_uid를 환불 `unique key`로 입력
				url = new URL("https://api.iamport.kr/subscribe/payments/unschedule");
			} else {
				json.addProperty("reason", vo.getReason()); // 가맹점 클라이언트로부터 받은 환불사유
				json.addProperty("merchant_uid", vo.getMerchant_uid()); // imp_uid를 환불 `unique key`로 입력
				json.addProperty("amount", vo.getCancel_request_amount()); // 가맹점 클라이언트로부터 받은 환불금액
//			json.addProperty("checksum", vo.getCancel_request_amount()); // [권장] 환불 가능 금액 입력
			}

			conn = (HttpsURLConnection) url.openConnection();

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", token);
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			bw.write(json.toString());
			bw.flush();
			bw.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			Gson gson = new Gson();

			@SuppressWarnings("unchecked")
			Map<String, String> result = gson.fromJson(br.readLine(), Map.class);
			logger.info("받아온 환불데이터: {}", result);
			logger.info("code: {}", result.get("code")); // code 가 0인 것만 아직 환불이 진행되지 않은 거래입니다.

			String response = String.valueOf(result.get("response"));
			logger.info("response: {}", response);

			if (String.valueOf(result.get("code")).equals("0.0")) {
				JSONObject jsonObj = toJsonObj(response);
				System.out.println("환불요청 response tojson : " + jsonObj);

//				price = jsonObj.getString("cancel_amount");
//				logger.info("cancel_amount:{}", price);
				flag = 1;
			}

			br.close();

		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		conn.disconnect();

		return flag;
	}

	// response를 jsonobject 객체로 변환
	public JSONObject toJsonObj(String response) {
		String str_obj = response;
		logger.info("str_obj : {}", str_obj);

		JSONObject jsonObject = new JSONObject();
		str_obj = str_obj.substring(1, str_obj.length() - 1);
		logger.info(str_obj);
		for (String first_txt : str_obj.split(", ")) {
//			logger.info("first_txt:{}",first_txt);
//			logger.info("first_txt.split(\"=\")[0]:{}",first_txt.split("=")[0]);
//			logger.info("first_txt.split(\"=\")[1]:{}",first_txt.split("=")[1]);
			if (first_txt.split("=").length > 1) {
				try {
					jsonObject.put(first_txt.split("=")[0], first_txt.split("=")[1]);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

		logger.info("jsonObject : {}", jsonObject);
		return jsonObject;
	}

	// 결제 시도 시각 in Unix Time Stamp.
	public long getUnixTime(String endDate) {
		long unixTime = 0;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date = formatter.parse(endDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			unixTime = c.getTimeInMillis() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		logger.info("unixTime:{}", unixTime);

		return unixTime;
	}

	// 예약된 총 시간 계산
	public int getReserveTime(String startDate, String endDate) {
		int time = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date1 = formatter.parse(startDate);
			Date date2 = formatter.parse(endDate);
			time = (int) ((date2.getTime() - date1.getTime()) / 3600000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	// random 주문번호 생성
	public String getRanStr() {
		// 주문번호 고유값 설정 위해, 난수생성 -> 이것은 공간 등록 시 생성되어 추가되어야 한다.
		char random_alphabet = (char) ((Math.random() * 26) + 97);
		// merchant_uid : 고유값 -> 프론트에서 받아서 결제완료 버튼 클릭 시, VO Data로 다시 넘어와야 함.
		String merchant_uid = String.valueOf(random_alphabet) + System.currentTimeMillis();
		logger.info("merchantid:{}", merchant_uid);
		return merchant_uid;
	}

	@Override
	public String getMercant_uid() {
		// 주문번호 고유값 설정 위해, 난수생성 -> 이것은 공간 등록 시 생성되어 추가되어야 한다.
		char random_alphabet = (char) ((Math.random() * 26) + 97);
		// merchant_uid : 고유값 -> 프론트에서 받아서 결제완료 버튼 클릭 시, VO Data로 다시 넘어와야 함.
		String merchant_uid = String.valueOf(random_alphabet) + System.currentTimeMillis();
		return merchant_uid;
	}

}
