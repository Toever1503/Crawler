package MODEL.FILE;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import MODEL.CUrl.CookieIO;
import MODEL.CUrl.Resolver;

public class FILEGET {

	public static Document getHTML(String link, String host) {
		CUrl curl = new CUrl(link);
		Map<String, String> header = new HashMap<>();
		header.put("Referer", host);
		curl.headers(header);
		curl.opt("-A",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.361");
		curl.opt("-L");

		// cookie for truyenqqtop.com
//		curl.cookie("VinaHost-Shield=1ce0bde47ddd2b89cd28b35d17b16d7a");

		CUrl.Resolver<Document> htmlResolver = new CUrl.Resolver<Document>() {
			@SuppressWarnings("unchecked")
			@Override
			public Document resolve(int httpCode, byte[] responseBody) throws Throwable {
				String html = new String(responseBody, "UTF-8");
				return Jsoup.parse(html);
			}
		};


		Document doc = curl.exec(htmlResolver, null);
		System.out.println(curl.getHttpCode());

		return curl.getHttpCode() == 200 ? doc : null;
	}

	
	public static byte[] getImage(String link, String host) {
		CUrl curl = new CUrl(link);
		Map<String, String> header = new HashMap<>();
		header.put("Referer", host);
		curl.headers(header);
		curl.opt("-A",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.361");
		curl.opt("-L");
//		String raw = curl.exec("UTF-8");
		byte[] raw = curl.exec();
		return curl.getHttpCode() == 200 ? raw : null;
	}
	
	public static void main(String[] args) {
		try {
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(FILESTORE.$_r+"/1.jpg"));
			outputStream.write(FILEGET.getImage("https://28505.mitemin.net/userpageimage/viewimagebig/icode/i554793/", "docln.net"));
			
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
