package model;
import java.util.Set;
import java.util.LinkedHashSet;


public class Page implements Model{
	
	private String hostname;
	private String urlPage;
	private String title;
	private String pageLanguage;
	private String last_modified_date;
	private Metadata metadata;
	private Text text;
	private Set<Image> imagesContainer;
	private Set<Link> linksContainer;
	private Set<Video> videosContainer;

	
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
	
	public String getLanguage() {
		return pageLanguage;
	}
	public void setLanguage(String pageLanguage) {
		this.pageLanguage = pageLanguage;
	}
	public String getLast_modified_date() {
		return last_modified_date;
	}
	public void setLast_modified_date(String last_modified_date) {
		this.last_modified_date = last_modified_date;
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
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public Set<Video> getVideosContainer() {
		return videosContainer;
	}
	public void setVideosContainer(Set<Video> videosContainer) {
		this.videosContainer = videosContainer;
	}
	
	//controllo Duplicati?
	public void addImageInstance(String src, String description, String altText, String height, String width){
		
		if(src == null) return;	
		
	  Image img =new Image(src, description, altText, height, width);
	  this.imagesContainer.add(img);
	}
    public void addLinkInstance(String href, String linkText, String linkTitle, String linkType){
		
    if(href == null) return;
    	
	  Link link = new Link(href, linkText, linkTitle, linkType);
	  
      this.linksContainer.add(link);
	}
    public void addVideoInstance(String src, String videoText, String heigth, String width){
	
    if(src == null) return;
    
	  Video video = new Video(src, videoText, heigth, width);
	  this.videosContainer.add(video);
    }
    public void addHeadingText(String tagHeader, String htext){
    	
    	if(htext == null)return;
    	
    	this.text.addHeading(tagHeader, htext);
    }
    public void addParagraphText(String par){
    	
    	if(par == null)return;
    	
    	this.text.addParagraph(par);
    }
    public void addTextList(String lt){
    	
    	if(lt == null)return;
    	
    	this.text.addTextList(lt);
    }
    	
    public void addMetadata(String author, String description, String charset, String keywords){
    	
      this.metadata.setAuthor(author);
      this.metadata.setDescription(description);
      this.metadata.setCharset(charset);
      this.metadata.setKeywords(keywords);
      
    }
   	
	public Page(String hostname, String urlPage, String title, String language, String last_modified_date) {
		super();
		this.hostname = hostname;
		this.urlPage = urlPage;
		this.title = title;
		this.pageLanguage = language;
		this.last_modified_date = last_modified_date;
		this.text = new Text();
		this.metadata = new Metadata();
		this.imagesContainer = new LinkedHashSet<Image>();
		this.linksContainer = new LinkedHashSet<Link>();
		this.videosContainer= new LinkedHashSet<Video>();  
	}
	
	public Page(String hostname, String urlPage){
		
		super();
		this.hostname = hostname;
		this.urlPage = urlPage;
		this.text = new Text();
		this.metadata = new Metadata();
		this.imagesContainer = new LinkedHashSet<Image>();
		this.linksContainer = new LinkedHashSet<Link>();
		this.videosContainer= new LinkedHashSet<Video>();  
	}
	
	public void print(){
		//Stampa dei dati riguardanti la pagina
    	System.out.println(this.toString());
    	System.out.println(this.metadata.toString());
    	System.out.println(this.text.toString());
		//iterazioni per la stampa dei vari dati strutturati presenti nella pagina
    	
		for(Image img : this.imagesContainer){
			System.out.println(img.toString());
		}
		for(Link link : this.linksContainer){
			System.out.println(link.toString());
		}
		
		for(Video vid: this.videosContainer){
			System.out.println(vid.toString());
		}
	
	}
	@Override
	public String toString() {
		return "Page [hostname=" + hostname + ", urlPage=" + urlPage + ", title=" + title + ", pageLanguage="
				+ pageLanguage + ", last_modified_date=" + last_modified_date + "]";
	}
}