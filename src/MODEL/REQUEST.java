package MODEL;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.SysexMessage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

import MODEL.FILE.FILESTORE;
import MODEL.FRAME.MAIN;
import MODEL.IMAGE.IMAGE;
import MODEL.IMAGE.USER;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http2.Header;

public class REQUEST {
	private static OkHttpClient httpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
			.writeTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).callTimeout(60, TimeUnit.SECONDS)
			.build();

	public static String sendPost(RequestBody body, String url, Map<String, String> header) {
		String result = null;
		Request request = null;

		Builder requestBuilder = new Request.Builder().url(url).addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
				.addHeader("Content-Type", "application/json; charset=UTF-8").addHeader("Referred", ENDPOINT.HOST)
				.addHeader("Connection", "Keep-Alive")
				.addHeader("auth", AUTH.getCreadential() == null ? "1" : AUTH.getCreadential().getUsername());

		if (!(header == null))
			for (String key : header.keySet())
				requestBuilder.addHeader(key, header.get(key));
		if (body != null)
			requestBuilder.post(body);

		request = requestBuilder.build();

		try (Response response = httpClient.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				if (response.code() == 404) {
					MAIN.getInstance().setDialog(response.body().string());
				}
				String s = response.toString();
				System.out.println(response.body().string());
				throw new IOException("Unexpected code " + s);
			}

			// Get response body
			result = response.code() == 200 ? response.body().string() : null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return result;
	}

	public static byte[] getImg(String link, String host) {
		Builder requestBuilder = new Request.Builder().url(link).addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
				.addHeader("Connection", "Keep-Alive").addHeader("Referred", host);

		byte[] result = null;
		Request request = requestBuilder.build();
		try (Response response = httpClient.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				String s = response.toString();
				throw new IOException("Unexpected code " + s);
			}

			// Get response body
			result = response.code() == 200 ? response.body().bytes() : null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep((int) (Math.random() * (300 - 100 + 1)) + 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(REQUEST.getImg("http://anhnhanh.org/data/images/23468/771738/022.jpg?data=net",
				"http://www.nettruyenpro.com/"));
//		long s = System.currentTimeMillis();
//
//		Map<String, Object> data1 = new HashMap<>();
//		data1.put("host", "www.nettruyenpro.com");
//		data1.put("type", "light novel");
////		data1.put("selector", ".reading-detail img");
//		Map<String, String> selector = new HashMap<>();
//		selector.put("img", "#chapter-content img");
//		selector.put("text", "#chapter-content p");
//		AUTH.setAuth(new USER("abc1", ""));
//
//		data1.put("selector", selector);
//		data1.put("link",
//				"https://docln.net/truyen/7921-boushoku-no-berserk-ore-dake-level-to-iu-gainen-wo-toppa-suru/c71668-illustration");
//
//		JSONObject jsonObject1 = new JSONObject(data1);
//
//		FormBody body = new FormBody.Builder().addEncoded("DATA", jsonObject1.toString()).build();
//		System.out.println(REQUEST.sendPost(body, "http://localhost:8090/php/truyenmoinhat/tchap.php", null));
//
//		System.out.println("Time exec: " + (System.currentTimeMillis() - s));

	}

}
