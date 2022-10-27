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
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

@Repository
public class PaymentDAOimpl implements PaymentDAO {

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Value("${import.imp_key}")
	private String imp_key;

	@Value("${import.imp_secret}")
	private String imp_secret;

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

			System.out.println("response : " + response);

			token = gson.fromJson(response, Map.class).get("access_token").toString();
			System.out.println("token:"+token);
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
	public PaymentInfo getPaymentInfo(String accessToke) {
		String token = getAccessToken();
		
		return null;
	}
	
	
	@Override
	public int verifyPayInfo() {
		
		return 0;
	}

}
