package model;

public class Link {   //<nav> <a></a>  </nav>

	private String anchor; //href, deve iniziare come un indirizzo "http://....."
	private String linkText;
	private String linkType;
	private String linkTitle;
	
	public Link(){}
	
	public Link(String anchor, String linkText, String linkTitle, String linkType) {
		super();
		this.anchor = anchor;
		this.linkText = linkText;
		this.linkType = linkType;
		this.linkTitle = linkTitle;
	}
	public String getAnchor() {
		return anchor;
	}
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}
	public String getLinkText() {
		return linkText;
	}
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anchor == null) ? 0 : anchor.hashCode());
		result = prime * result + ((linkText == null) ? 0 : linkText.hashCode());
		result = prime * result + ((linkTitle == null) ? 0 : linkTitle.hashCode());
		result = prime * result + ((linkType == null) ? 0 : linkType.hashCode());
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
		Link other = (Link) obj;
		if (anchor == null) {
			if (other.anchor != null)
				return false;
		} else if (!anchor.equals(other.anchor))
			return false;
		if (linkText == null) {
			if (other.linkText != null)
				return false;
		} else if (!linkText.equals(other.linkText))
			return false;
		if (linkTitle == null) {
			if (other.linkTitle != null)
				return false;
		} else if (!linkTitle.equals(other.linkTitle))
			return false;
		if (linkType == null) {
			if (other.linkType != null)
				return false;
		} else if (!linkType.equals(other.linkType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Link [anchor=" + anchor + ", linkText=" + linkText + ", linkType=" + linkType + ", linkTitle="
				+ linkTitle + "]";
	}
	
}
