package controller;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedHashSet;

import com.jaunt.NotFound;

import utilities.ValidatorUrl;

/*
 * WebpageScraping
 * passaggio parametri a Scraping
 */

/*
 * aggiustare la classe in modo che restituisca tutti i dati e non utilizzi il model
 * il bind del model lo fa la classe che chiama questa
 * trovare metodo di jaunt che restituisce il nome del tag o dell'attributo del tag
 * cambiare il model relativo al text con attributi "tagtext, textvalue", cambiare quindi la tabella 
 * del DB relativa ai testi
 */


public class HtmlScraping extends WebScraper{

	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HtmlScraping(String url) {
		super();
		this.url=url;
		super.connect(url);
	} 
    
    public void connect() {
    	
    	//esegue la connessione alla risorsa specificata e restituisce tutto il codice html della pagina 
    
    	super.connect(this.url);
    	
    }

    //ottengo <p>,<h1>..<h6>
    public Set<Map<String, String>> getTexts() throws NotFound{
    	
    	Set<Map<String, String>> texts = new LinkedHashSet<Map<String, String>>();
    	
    	texts = extractContentTag("<p>", new String[0], new String[0], "<body>");
    	
    	//trovo tutti gli headers
    	int headerType = 1;
    	while(headerType < 7){
    		
    		texts.addAll(extractContentTag("<h" + headerType + ">", new String[0], new String[0], "<body>"));
    		headerType++;
    	}
    	
    	return texts;
    }
    
    public Set<Map<String, String>> getLists() throws NotFound{
    	
    	Set<Map<String, String>> lists = new LinkedHashSet<Map<String, String>>();
    	
    	String [] nested = new String[1];
    	nested[0] = "<li>";
    	
    	lists = extractContentTag("<ul>", nested, new String[0], "<body>");
    	
    	lists.addAll(extractContentTag("<ol>", nested, new String[0], "<body>"));
    	
    	return lists;
    }
    
    public Set<Map<String, String>> getTables() throws NotFound{
    	Set<Map<String, String>> tables = new LinkedHashSet<Map<String, String>>();
    	
    	String[] nested = new String[3];
    	
    	nested[0] = "<tr>";
    	nested[1] = "<th>";
    	nested[2] = "<td>";
    	tables = extractContentTag("<table>", nested, new String[0], "<body>");
    	
    	return tables;
    }
    
    public Set<Map<String, String>> getLinks() throws NotFound{
    	
    	Set<Map<String, String>> links = new LinkedHashSet<Map<String, String>>();
    	
    	String attrs[] = new String[2];
    	
    	attrs[0] = "href";
    	attrs[1] = "title";
    	links = extractContentTag("<a>", new String[0], attrs, "<body>");
    	
    	return links;
    }
    
    public Set<Map<String, String>> getImages() throws NotFound{
    	
    	Set<Map<String, String>> images = new LinkedHashSet<Map<String, String>>();
    	
    	String attrs[] = new String[4];
    	
    	attrs[0] = "src";
    	attrs[1] = "alt";
    	attrs[2] = "width";
    	attrs[3] = "height";
    	images = extractContentTag("<img>", new String[0], attrs, "<body>");
    	
    	return images;
    }
    
    public Set<Map<String, String>> getMedias() throws NotFound{
    	
    	Set<Map<String, String>> medias = new LinkedHashSet<Map<String, String>>();
    	
    	String attrs[] = new String[4];
    	String nested[] = new String[1];
    	String tags[] = new String[5];
    	
    	tags[0] = "<video>";
    	tags[1] = "<audio>";
    	tags[2] = "<embed>";
    	tags[3] = "<object>";
    	tags[4] = "<iframe>";
    	
    	attrs[0] = "src";
    	attrs[1] = "type";
    	attrs[2] = "width";
    	attrs[3] = "height";
    	
    	
    	for(int i=0; i<5; i++){
    		
    		if(tags[i] == "<video>" || tags[i] == "<audio>") nested[0] = "<source>";
    			
    		medias = extractContentTag(tags[i], nested, attrs, "<body>");
    		
    	}  	
    	return medias;
    }
    
    public Map<String, String> getHeaders(){
    	
    	Map<String, String> headers = new HashMap<String, String> ();
    	
    	String [] attrs = new String[6];
    	
    	attrs[0] = "connection";
    	attrs[1] = "cache-control";
    	attrs[2] = "server";
    	attrs[3] = "content-type";
    	attrs[4] = "content-encoding";
    	attrs[5] = "content-language";
    	
    	headers = headRequest(this.url, attrs);
    	return headers;
    	
    }
    
    public Map<String, String> getPageData() throws NotFound{
    	
    	Map<String, String> pagedata = new HashMap<String, String>();
    	
    	ValidatorUrl validator = new ValidatorUrl();
    	String hostname = validator.getHost(this.getUrl());
    
    	
    	pagedata.put("hostname", hostname);
    	pagedata.put("url", this.url);
    	pagedata.put("title", extractTextFromOneTag("<title>"));
    	
    	return pagedata;
    }
    
    public Set<Map<String, String>> scrap() throws NotFound{
    	
    	Set<Map<String, String>> page = new LinkedHashSet<Map<String, String>>();
    	
    	page.add(this.getPageData());
    	page.add(this.getHeaders());
    	page.addAll(this.getTexts());
    	page.addAll(this.getLists());
    	page.addAll(this.getTables());
    	page.addAll(this.getLinks());
    	page.addAll(this.getImages());
    	page.addAll(this.getMedias());
    	
    	return page;
    }
     
	}
 