package MODEL.IMAGE;

import java.io.File;
import java.util.List;
import java.util.Map;

import MODEL.TRUYENMOINHAT;
import MODEL.FILE.FILESTORE;

public class MANGA {
	private String manga;
	private Map<String, String> selector;
	private String host;
	private boolean type; // 0 manga, 1 light novel
	private String link;
	private List<CHAPTER> listChap;
	private volatile String status;
	private int index;
	private String root;
	private String enpoint;

	public MANGA() {
	}

	public MANGA(String manga, Map<String, String> selector, String host, boolean type, String link, String endpoint) {
		super();
		this.manga = manga;
		this.selector = selector;
		this.host = host;
		this.type = type;
		this.link = link;
		this.status = "NOTRUN";
		this.enpoint = endpoint;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CHAPTER> getListChap() {
		return listChap;
	}

	public void setListChap(List<CHAPTER> listChap) {
		this.listChap = listChap;
	}

	public String getManga() {
		return manga;
	}

	public void setManga(String manga) {
		this.manga = manga;
	}

	public Map<String, String> getSelector() {
		return selector;
	}

	public void setSelector(Map<String, String> selector) {
		this.selector = selector;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean getType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getEnpoint() {
		return enpoint;
	}

	public void setEnpoint(String enpoint) {
		this.enpoint = enpoint;
	}

	public static void main(String[] args) {
		new File("TRUYENMOINHAT\\manga\\NÓNG VÀ LẠNH\\Chapter 30 ~ +'=()!Mất kiên nhẫn").mkdirs();
	}

}
