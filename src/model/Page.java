package model;
import java.util.Set;
import java.util.LinkedHashSet;


public class Page {
	
	
	private String hostname;
	private String urlPage;
	private String title;
	private Header headers;
	private Set<Text> texts;
	private Set<Image> imagesContainer;
	private Set<Link> linksContainer;
	private Set<Media> MediasContainer;

	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getUrlPage() {
		return urlPage;
	}
	public void setUrlPage(String urlPage) {
		this.urlPage = urlPage;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Image> getImagesContainer() {
		return imagesContainer;
	}
	public void setImagesContainer(Set<Image> imagesContainer) {
		this.imagesContainer = imagesContainer;
	}
	public Set<Link> getLinksContainer() {
		return linksContainer;
	}
	public void setLinksContainer(Set<Link> linksContainer) {
		this.linksContainer = linksContainer;
	}
	
	public Header getHeaders() {
		return headers;
	}
	public void setHeaders(Header headers) {
		this.headers = headers;
	}
	public Set<Text> getText() {
		return texts;
	}
	public void setText(Set<Text> texts) {
		this.texts = texts;
	}
	public Set<Media> getMediasContainer() {
		return MediasContainer;
	}
	public void setMediasContainer(Set<Media> MediasContainer) {
		this.MediasContainer = MediasContainer;
	}
	
	public void setHeaders(String contentType, String contentEncoding, String contentLanguage, String cacheControl, String connection, String server){
		
		this.headers.setContentType(contentType);
		this.headers.setContentEncoding(contentEncoding);
		this.headers.setContentLanguage(contentLanguage);
		this.headers.setCacheControl(cacheControl);
		this.headers.setConnection(connection);
		this.headers.setServer(server);
		
	}

	public void addImageInstance(String src, String altText, String height, String width){
		
		if(src == null) return;	
		
	  Image img =new Image(src, altText, height, width);
	  this.imagesContainer.add(img);
	}
    public void addLinkInstance(String href, String linkText, String linkTitle){
		
    if(href == null) return;
    	
	  Link link = new Link(href, linkText, linkTitle);
	  
      this.linksContainer.add(link);
	}
    public void addMediaInstance(String tagname, String src, String type, String height, String width){
	
    if(src == null) return;
    
	  Media media = new Media(tagname, src, type, height, width);
	  this.MediasContainer.add(media);
    }
    
    public void addText(String type, String content){
    	
    	Text text = new Text(type, content);
    	
    	this.texts.add(text);
    	
    }
    
    public Page(){
    	
    	this.headers = new Header();
		this.texts = new LinkedHashSet<Text>();
		this.imagesContainer = new LinkedHashSet<Image>();
		this.linksContainer = new LinkedHashSet<Link>();
		this.MediasContainer= new LinkedHashSet<Media>();
    }
   	
	public Page(String hostname, String urlPage, String title) {
		super();
		this.hostname = hostname;
		this.urlPage = urlPage;
		this.title = title;
		this.headers = new Header();
		this.texts = new LinkedHashSet<Text>();
		this.imagesContainer = new LinkedHashSet<Image>();
		this.linksContainer = new LinkedHashSet<Link>();
		this.MediasContainer= new LinkedHashSet<Media>();  
	}
	
	public Page(String hostname, String urlPage){
		
		super();
		this.hostname = hostname;
		this.urlPage = urlPage;
		this.texts = new LinkedHashSet<Text>();
		this.headers = new Header();
		this.imagesContainer = new LinkedHashSet<Image>();
		this.linksContainer = new LinkedHashSet<Link>();
		this.MediasContainer= new LinkedHashSet<Media>();  
	}
	
	public void print(){
		//Stampa dei dati riguardanti la pagina
    	System.out.println(this.toString());
    	System.out.println(this.headers.toString());
    	System.out.println(this.texts.toString());
		//iterazioni per la stampa dei vari dati strutturati presenti nella pagina
    	
		for(Image img : this.imagesContainer){
			System.out.println(img.toString());
		}
		for(Link link : this.linksContainer){
			System.out.println(link.toString());
		}
		
		for(Media vid: this.MediasContainer){
			System.out.println(vid.toString());
		}
	
	}
	@Override
	public String toString() {
		return "Page [hostname=" + hostname + ", urlPage=" + urlPage + ", title=" + title + "]";
	}

	
}