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
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
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

	// imp_uid로 아임포트 서버에서 결제 정보 조회
	@Override
	public PaymentInfo getPaymentInfo(String imp_uid) {
		String token = getAccessToken();
		String price = "10";
		HttpsURLConnection conn = null;
		try {
			URL url = new URL("https://api.iamport.kr/payments/" + imp_uid);

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

			price = jsonObj.getString("amount").split("\\.")[0];
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

		return new PaymentInfo(Integer.parseInt(price));
	}

	// 결제정보와 DB 정보와 일치여부 확인
	@Override
	public int verifyPayInfo(String imp_uid, long spaceId) {
		int flag = 0;
		PaymentInfo info = getPaymentInfo(imp_uid);
		SpaceInfoVO vo = spaceInfoService.selectOne(spaceId);
		if (info.getPrice() == vo.getPrice()) {
			flag = 1;
		}
		return flag;
	}

	// 예약 취소 시, 환불
	@Override
	public int refund(RefundVO vo) {
		int flag = 0;
		// 결제 취소 시 환불가능 금액 계산

		// 환불 요청
		String token = getAccessToken();
		String price = "10";
		HttpsURLConnection conn = null;
		try {
			URL url = new URL("https://api.iamport.kr/payments/cancel");

			conn = (HttpsURLConnection) url.openConnection();

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", token);
			conn.setDoOutput(true);
			JsonObject json = new JsonObject();

			json.addProperty("reason", vo.getReason()); // 가맹점 클라이언트로부터 받은 환불사유
			json.addProperty("imp_uid", vo.getImp_uid()); // imp_uid를 환불 `unique key`로 입력
			json.addProperty("amount", vo.getCancel_request_amount()); // 가맹점 클라이언트로부터 받은 환불금액
//			json.addProperty("checksum", vo.getCancel_request_amount()); // [권장] 환불 가능 금액 입력

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			bw.write(json.toString());
			bw.flush();
			bw.close();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			Gson gson = new Gson();

			@SuppressWarnings("unchecked")
			Map<String, String> result = gson.fromJson(br.readLine(), Map.class);
			logger.info("받아온 환불데이터: {}",result);
			logger.info("code: {}",result.get("code")); //code 가 0인 것만 아직 환불이 진행되지 않은 거래입니다.
			
			String response = String.valueOf(result.get("response"));
			logger.info("response: {}",response); 
			
			if(String.valueOf(result.get("code")).equals("0.0")) {
				JSONObject jsonObj = toJsonObj(response);
				System.out.println("환불요청 response tojson : " + jsonObj);
				
				price = jsonObj.getString("cancel_amount");
				logger.info("cancel_amount:{}", price);
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
			if(first_txt.split("=").length>1) {
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

}
