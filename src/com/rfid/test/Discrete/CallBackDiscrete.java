package com.rfid.test.Discrete;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.rfid.callBack.CallBack.Discrete;

class CallBackDiscrete implements Discrete {

	private static final String API_BASE = "https://4eaff70e-88b7-482c-8205-34a365caa431.mock.pstmn.io";
	private static final String API_TAG_DATA = "/tag_data";
	private static final String TAG_DATA_URI = API_BASE + API_TAG_DATA;

	private final URL url;
	private final HttpURLConnection con;

	public CallBackDiscrete() throws Exception {
		try {
			url = new URL(TAG_DATA_URI);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("Keep-Alive", "timeout=5, max=100");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

	@Override
	public void result(String data, String antennaNo, String deviceNo,String communicationMode, String temperature) {
		try(OutputStream os = con.getOutputStream()) {
    		byte[] input = data.getBytes("utf-8");
    		os.write(input, 0, input.length);			
		} catch (Exception e) {
			System.out.println(e);
			return;
		}

		try(BufferedReader br = new BufferedReader(
 			new InputStreamReader(con.getInputStream(), "utf-8"))) {
    		StringBuilder response = new StringBuilder();
    		String responseLine = null;
    		while ((responseLine = br.readLine()) != null) {
        		response.append(responseLine.trim());
    		}
    		System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
	}

	@Override
	public void serialPortException(String name, boolean result) {
		System.out.println( " ���ںţ� " + name + " ���: " + result);
	}

	public void close() {
		con.disconnect();
	}
}