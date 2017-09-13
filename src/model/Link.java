package model;

public class Link {   //<nav> <a></a>  </nav>

	private String href; //href, deve iniziare come un indirizzo "http://....."
	private String linkText;
	private String linkTitle;
	
	public Link(){}
	
	public Link(String href, String linkText, String linkTitle) {
		super();
		this.href = href;
		this.linkText = linkText;
		this.linkTitle = linkTitle;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getLinkText() {
		return linkText;
	}
	public void setLinkText(String linkText) {
		this.linkText = linkText;
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
		result = prime * result + ((href == null) ? 0 : href.hashCode());
		result = prime * result + ((linkText == null) ? 0 : linkText.hashCode());
		result = prime * result + ((linkTitle == null) ? 0 : linkTitle.hashCode());
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
		if (href == null) {
			if (other.href != null)
				return false;
		} else if (!href.equals(other.href))
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
		return true;
	}

	@Override
	public String toString() {
		return "Link [href=" + href + ", linkText=" + linkText + ", linkTitle="
				+ linkTitle + "]";
	}
	
}
