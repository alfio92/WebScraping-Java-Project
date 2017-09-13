package model;


public class Text {

	private String type;
	private String content;
	
	
	public Text(){
		
	}
	
	public Text(String type, String content) {
		super();
		this.type = type;
		this.content = content;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Text [type=" + type + ", content=" + content + "]";
	}
	
	
}