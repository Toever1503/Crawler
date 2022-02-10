package MODEL;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import MODEL.FILE.FILESTORE;
import MODEL.FRAME.MAIN;
import MODEL.IMAGE.CHAPTER;
import MODEL.IMAGE.IMAGE;
import MODEL.IMAGE.MANGA;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ENDPOINT {
	public static String HOST = "www.google.com";
	public static String LOGIN = "";
	public static String CHAP = "";
	public static String IMG = "";
	public static String GETCHAP = "https://api.truyenmoinhat.xyz/getChap.php";
	public static String REFRESH = "https://api.truyenmoinhat.xyz/refresh.php";

	public static List<CHAPTER> getChapManga(RequestBody body, String link, Map<String, String> header) {
		String content = REQUEST.sendPost(body, link, header);
		
		System.out.println(content);
		
		if (content.isEmpty())
			return null;

		try {
			JSONObject jsonObject = new JSONObject(content.trim());
			if (jsonObject.get("result").equals("success")) {
				JSONObject manga = jsonObject.getJSONObject("data");

				List<CHAPTER> listChap = new ArrayList<CHAPTER>();
				if ((boolean) manga.get("hasChap")) {
					JSONArray array = manga.getJSONArray("listChap");

					JSONObject chap = null;
					for (int i = 0; i < array.length(); ++i) {
						chap = array.getJSONObject(i);
						listChap.add(new CHAPTER(chap.get("chap").toString(), chap.get("link").toString().trim(), null,
								i + 1));
					}
				}
				return listChap.isEmpty() ? null : listChap;
			} else {
				MAIN.getInstance().setErrorResponse2(jsonObject.get("message").toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MAIN.getInstance().setErrorResponse1("Error: cannot parse json when get manga chapter");
		}

		return null;
	}

	public static List<IMAGE> getImg(RequestBody body, int type) {
		String content = REQUEST.sendPost(body, ENDPOINT.IMG, null);
		System.out.println(content);
		
		if (content == null)
			return null;
		try {
			JSONObject jsonObject = new JSONObject(content);

			if (jsonObject.get("result").equals("success")) { // for success
				AUTH.getCreadential().setQuotas(jsonObject.getInt("quotas"));
				List<IMAGE> list = new ArrayList<IMAGE>();

				JSONObject data = jsonObject.getJSONObject("data");

				if ((boolean) data.get("hasImg")) {
					JSONArray a = new JSONArray(data.getJSONArray("listImg"));
					JSONObject imgO;

					for (int i = 0; i < a.length(); ++i) {
						imgO = a.getJSONObject(i);
						list.add(new IMAGE(imgO.get("fileName").toString(), imgO.get("link").toString()));
					}
				}
				return list.isEmpty() ? null : list;
			} else { // for error
				if (type == 1) {
					MAIN.getInstance().setErrorResponse1(jsonObject.get("message").toString());
				}
				MAIN.getInstance().setErrorResponse2(jsonObject.get("message").toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public static Map<String, Object> crawLN(RequestBody body) {
		String content = REQUEST.sendPost(body, ENDPOINT.IMG, null);
		
		System.out.println(content);
		
		if (content == null)
			return null;

		Map<String, Object> ln = new HashMap<String, Object>();
		try {
			JSONObject jsonObject = new JSONObject(content);

			if (jsonObject.get("result").equals("success")) {
				AUTH.getCreadential().setQuotas(jsonObject.getInt("quotas"));
				JSONObject data = jsonObject.getJSONObject("data");

				if ((boolean) data.get("hasText")) {
					ln.put("text", data.get("text").toString());

					if ((boolean) data.get("hasImg")) {
						JSONArray a = new JSONArray(data.getJSONArray("listImg"));
						JSONObject imgO;
						List<IMAGE> list = new ArrayList<IMAGE>();
						for (int i = 0; i < a.length(); ++i) {
							imgO = a.getJSONObject(i);
							list.add(new IMAGE(imgO.get("fileName").toString(), imgO.get("link").toString()));
						}
						ln.put("img", list.isEmpty() ? null : list);
					} else
						ln.put("img", null);
				}
			} else {
				MAIN.getInstance().setErrorResponse2(jsonObject.get("message").toString());
			}

		} catch (Exception e) {
			// TODO: handle exception
			MAIN.getInstance().setErrorResponse1("Error: cannot parse json when get novel");
		}
		return ln;
	}

	public static void main(String[] args) {

		Map<String, String> selector = new HashMap<>();
//		selector.put("vol", ".volume-list");
//		selector.put("chap", ".list-chapter li a");
		selector.put("img", ".reading-detail img");
//
		Map<String, Object> map = new HashMap<>();
		map.put("manga", "TENSEI SHITARA SLIME DATTA KEN");
		map.put("selector", selector);
		map.put("isVol", false);
		map.put("link", "http://www.nettruyenpro.com/truyen-tranh/majo-no-tabitabi/chap-14/776236");
		map.put("type", "manga");
		JSONObject json = new JSONObject(map);
		RequestBody body = new FormBody.Builder().addEncoded("DATA", json.toString()).build();

		System.out.println(ENDPOINT.getImg(body, 1));
		;

//		for (CHAPTER c : ENDPOINT.getChapManga(body, ENDPOINT.CHAP, null)) {
//			System.out.println(c.getName() + "->" + c.getLink());
//		}
//
//		for (VOL vol : ENDPOINT.getChapLN(body, "http://localhost:8090/php/truyenmoinhat/manga.php", null)) {
//			System.out.println(vol.getName());
//			for (CHAPTER c : vol.getChapter()) {
//				System.out.println(c.getName() + "->" + c.getLink());
//			}
//			System.out.println("======================");
//		}

	}
}
