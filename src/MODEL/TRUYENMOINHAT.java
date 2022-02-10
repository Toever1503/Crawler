package MODEL;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import MODEL.FILE.FILESTORE;
import MODEL.FRAME.MAIN;
import MODEL.IMAGE.CHAPTER;
import MODEL.IMAGE.IMAGE;
import MODEL.IMAGE.MANGA;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class TRUYENMOINHAT {
	private volatile int i = 1;
	private volatile int totalImg = 0;

	public void beginCraw(MANGA manga, ExecutorService t) {
		DecimalFormat f = new DecimalFormat("##.##");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("manga", manga.getManga());
		data.put("selector", manga.getSelector());
		data.put("host", manga.getHost());
		data.put("type", manga.getType() ? "novel" : "manga");
		data.put("quotas", AUTH.getCreadential().getQuotas());

		float listChap = (float) manga.getListChap().size();
		if (!manga.getType()) { // for manga
			String root = manga.getRoot() + "/" + manga.getManga();
			try {
				data.put("endpoint", null);
				for (CHAPTER chap : manga.getListChap()) {
					chap.setStatus("running");
					t.execute(new Thread(chap.getName() + chap.getIndex()) {
						public void run() {
							String path = root + "/"
									+ chap.getName().replaceAll("[\\[\\];?$,\\/\\\\#^&*:\\\"@]", "").trim();
							new File(path).mkdirs();

							data.put("link", chap.getLink());
							JSONObject jsonData = new JSONObject(data);

							RequestBody body = new FormBody.Builder().addEncoded("DATA", jsonData.toString()).build();
							List<IMAGE> listImg = ENDPOINT.getImg(body, 1);

							if (listImg == null) {
								chap.setStatus("failed");
								MAIN.getInstance().setErrorResponse1(
										manga.getManga() + " - " + chap.getName() + "->Error: cannot get images");
							} else {
								try {
									new File(path).mkdirs();
									int countImg = 0;
									if (!(listImg == null)) {
										for (IMAGE img : listImg) {
											if (!manga.getStatus().equalsIgnoreCase("RUNNING"))
												return;
											if (FILESTORE.saveImg(img, path, manga.getHost()) == 1) {
												totalImg++;
												countImg++;
											}
										}
									}
									if (countImg == listImg.size())
										chap.setStatus("SUCCESS");

									MAIN.getInstance().updateStatus(manga.getIndex(),
											f.format(i / listChap * 100) + "%", totalImg);
									if (i == (int) listChap) {
										manga.setStatus("COMPLETE");
										MAIN.currentThread--;
									}
									i++;
								} catch (Exception e) {
									MAIN.getInstance().setErrorResponse1(
											manga.getManga() + " - " + chap.getName() + "->Error connect!");
								}
							}
							try {
								Thread.sleep(((int) Math.random() * 200 - 100 + 1) + 100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						};
					});
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				MAIN.getInstance().setErrorResponse1("Error: cannot download manga");
			}
		} else { // for novel
			try {
				String root = manga.getRoot() + "/" + manga.getManga();
				data.put("endpoint", manga.getEnpoint() + "/" + manga.getManga());
				String imgFolder = root + "/" + manga.getManga();
				for (CHAPTER chap : manga.getListChap()) {
					chap.setStatus("running");

					t.execute(new Thread() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							String path = root + "/"
									+ chap.getName().replaceAll("[\\[\\];?$,\\/\\\\#^&*:\\\"@]", "").trim();

							data.put("link", chap.getLink());
							JSONObject jsonData = new JSONObject(data);
							RequestBody body = new FormBody.Builder().addEncoded("DATA", jsonData.toString()).build();

							Map<String, Object> ln = ENDPOINT.crawLN(body);
							if (ln == null) {
								chap.setStatus("failed");
								MAIN.getInstance().setErrorResponse1(
										manga.getManga() + " - " + chap.getName() + "->Error: cannot novel chap");
							} else {
								new File(path).mkdirs();
								String content = ln.get("text").toString();
								List<IMAGE> listImg = (List<IMAGE>) ln.get("img");

								FILESTORE._$1_w_3l(content, path);

								int countImg = 0;
								if (!(listImg == null)) {
									new File(imgFolder).mkdirs();
									for (IMAGE img : listImg) {
										if (!manga.getStatus().equalsIgnoreCase("RUNNING"))
											return;
										if (FILESTORE.saveImg(img, imgFolder, manga.getHost()) == 1) {
											totalImg++;
											countImg++;
										}
									}
								}
								chap.setStatus("SUCCESS");
								MAIN.getInstance().updateStatus(manga.getIndex(), f.format(i / listChap * 100) + "%",
										totalImg);

								if (i == (int) listChap) {
									manga.setStatus("COMPLETE");
									MAIN.currentThread--;
								}
								i++;
							}
							try {
								Thread.sleep(((int) Math.random() * 200 - 100 + 1) + 100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				MAIN.getInstance().setErrorResponse1("Error: cannot get novels");
			}
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Map<String, String> selector = new HashMap<>();
		selector.put("vol", "");
		selector.put("chap", ".list-chapter li a");
		selector.put("img", ".reading-detail img");

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("manga", "123");
		data.put("link", "http://www.nettruyenpro.com/truyen-tranh/do-thi-ta-vuong/chap-233/757760");
		data.put("selector", selector);
		data.put("host", "www.nettruyenpro.com");
		data.put("type", "manga");

		JSONObject jsonData = new JSONObject(data);
//		System.out.println(jsonData);

		RequestBody body = new FormBody.Builder().addEncoded("DATA", jsonData.toString()).build();

		List<IMAGE> ls = ENDPOINT.getImg(body, 1);
//		if (ls != null) {
//			for (IMAGE img : ls) {
//				System.out.println(img.getName() + "->" + img.getLink());
//			}
//		}

		System.out.println(System.currentTimeMillis() - start);

	}
}
