package model;

public class Image {
	
	private String src_img;
	private String description_img;
	private String altText_img;
	private String height_img;
	private String width_img;
	
	public Image(){}
	
	public Image(String src, String description, String altText_img, String height_img, String width_img) {
		super();
		this.src_img = src;
		this.description_img = description;
		this.altText_img = altText_img;
		this.height_img = height_img;
		this.width_img = width_img;
	}

	public String getSrc_img() {
		return src_img;
	}
	public void setSrc_img(String src) {
		this.src_img = src;
	}
	public String getDescription() {
		return description_img;
	}
	public void setDescription(String description) {
		this.description_img = description;
	}
	public String getaltText_img() {
		return altText_img;
	}
	
	public void setaltText_img(String altText_img) {
		this.altText_img = altText_img;
	}
	public String getheight_img() {
		return height_img;
	}
	public void setheight_img(String height_img) {
		this.height_img = height_img;
	}
	public String getwidth_img() {
		return width_img;
	}
	public void setwidth_img(String width_img) {
		this.width_img = width_img;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altText_img == null) ? 0 : altText_img.hashCode());
		result = prime * result + ((description_img == null) ? 0 : description_img.hashCode());
		result = prime * result + ((height_img == null) ? 0 : height_img.hashCode());
		result = prime * result + ((src_img == null) ? 0 : src_img.hashCode());
		result = prime * result + ((width_img == null) ? 0 : width_img.hashCode());
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
		Image other = (Image) obj;
		if (altText_img == null) {
			if (other.altText_img != null)
				return false;
		} else if (!altText_img.equals(other.altText_img))
			return false;
		if (description_img == null) {
			if (other.description_img != null)
				return false;
		} else if (!description_img.equals(other.description_img))
			return false;
		if (height_img == null) {
			if (other.height_img != null)
				return false;
		} else if (!height_img.equals(other.height_img))
			return false;
		if (src_img == null) {
			if (other.src_img != null)
				return false;
		} else if (!src_img.equals(other.src_img))
			return false;
		if (width_img == null) {
			if (other.width_img != null)
				return false;
		} else if (!width_img.equals(other.width_img))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Image [src=" + src_img + ", description=" + description_img + ", altText_img=" + altText_img + ", height_img=" + height_img
				+ ", width_img=" + width_img + "]";
	}
}