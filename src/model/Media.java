package model;


public class Media { //tag Media, object, embed
	/*nei tag Media non è assolutamente necessario controllare 
	 * l'estensione del contenuto di norma non è presente nel tag Media
           */
	private String tag;
	private String src; 
	private String type;
	private String height;
	private String width;
	
	public Media(){}
	
	public Media(String tag, String src, String type, String height, String width) {
		super();
		this.tag = tag;
		this.src = src;
		this.type = type;
		this.height = height;
		this.width = width;
	}
	
	
	public String getTag(){
		return tag;
	}
	public void setTag(String tag){
		this.tag = tag;
	}
	public String getsrc() {
		return src;
	}
	public void setsrcs(String src) {
		this.src = src;
	}
	public String gettype() {
		return type;
	}
	public void settype(String type) {
		this.type = type;
	}
	public String getheight() {
		return height;
	}
	public void setheight(String height) {
		this.height = height;
	}
	public String getwidth() {
		return width;
	}
	public void setwidth(String width) {
		this.width = width;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((src == null) ? 0 : src.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Media other = (Media) obj;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (src == null) {
			if (other.src != null)
				return false;
		} else if (!src.equals(other.src))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Media [src=" + src + ", type=" + type + ", height=" + height + ", width="
				+ width + "]";
	}

	
}
