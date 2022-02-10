package MODEL.IMAGE;

import java.util.List;

public class CHAPTER {
	private String name;
	private String link;
	private List<IMAGE> img;
	private String content;
	private int index;
	private String status;

	public CHAPTER(String name, String link, List<IMAGE> img, int index) {
		super();
		this.name = name;
		this.link = link;
		this.img = img;
		this.index = index;
		this.status = "NOTRUN";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<IMAGE> getImg() {
		return img;
	}

	public void setImg(List<IMAGE> img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

}
