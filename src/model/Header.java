package model;

public class Header {
	
	private String contentType;
	private String contentEncoding;
	private String contentLanguage;
	private String cacheControl;
	private String connection;
	private String server;
	
	public Header(){}
	
	public Header(String contentType, String contentEncoding, String contentLanguage, String cacheControl,
			String connection, String server) {
		super();
		this.contentType = contentType;
		this.contentEncoding = contentEncoding;
		this.contentLanguage = contentLanguage;
		this.cacheControl = cacheControl;
		this.connection = connection;
		this.server = server;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}

	public String getContentLanguage() {
		return contentLanguage;
	}

	public void setContentLanguage(String contentLanguage) {
		this.contentLanguage = contentLanguage;
	}

	public String getCacheControl() {
		return cacheControl;
	}

	public void setCacheControl(String cacheControl) {
		this.cacheControl = cacheControl;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}
	
	

}
