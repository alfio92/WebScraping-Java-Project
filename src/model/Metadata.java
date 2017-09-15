package model;

public class Metadata {
	//dati contrassegnati dal tag <meta> in <head>
	
	private String author;
	private String description;
	private String charset;
	private String keywords;
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Metadata(){}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	@Override
	public String toString() {
		return "Metadata [author=" + author + ", description=" + description + ", charset=" + charset + ", keywords="
				+ keywords + "]";
	}

}
