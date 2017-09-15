package model;


public class Video { //tag video, object, embed
	/*nei tag video non è assolutamente necessario controllare 
	 * l'estensione del contenuto di norma non è presente nel tag video
           */
	private String src_vid; 
	private String videoTechnology;
	private String height_vid;
	private String width_vid;
	
	public Video(){}
	
	public Video(String src_vid, String videoTechnology, String height_vid, String width_vid) {
		super();
		this.src_vid = src_vid;
		this.videoTechnology = videoTechnology;
		this.height_vid = height_vid;
		this.width_vid = width_vid;
	}
	public String getsrc_vid() {
		return src_vid;
	}
	public void setsrc_vids(String src_vid) {
		this.src_vid = src_vid;
	}
	public String getVideoTechnology() {
		return videoTechnology;
	}
	public void setVideoTechnology(String videoTechnology) {
		this.videoTechnology = videoTechnology;
	}
	public String getheight_vid() {
		return height_vid;
	}
	public void setheight_vid(String height_vid) {
		this.height_vid = height_vid;
	}
	public String getwidth_vid() {
		return width_vid;
	}
	public void setwidth_vid(String width_vid) {
		this.width_vid = width_vid;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height_vid == null) ? 0 : height_vid.hashCode());
		result = prime * result + ((src_vid == null) ? 0 : src_vid.hashCode());
		result = prime * result + ((videoTechnology == null) ? 0 : videoTechnology.hashCode());
		result = prime * result + ((width_vid == null) ? 0 : width_vid.hashCode());
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
		Video other = (Video) obj;
		if (height_vid == null) {
			if (other.height_vid != null)
				return false;
		} else if (!height_vid.equals(other.height_vid))
			return false;
		if (src_vid == null) {
			if (other.src_vid != null)
				return false;
		} else if (!src_vid.equals(other.src_vid))
			return false;
		if (videoTechnology == null) {
			if (other.videoTechnology != null)
				return false;
		} else if (!videoTechnology.equals(other.videoTechnology))
			return false;
		if (width_vid == null) {
			if (other.width_vid != null)
				return false;
		} else if (!width_vid.equals(other.width_vid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Video [src_vid=" + src_vid + ", videoTechnology=" + videoTechnology + ", height_vid=" + height_vid + ", width_vid="
				+ width_vid + "]";
	}

	
}
