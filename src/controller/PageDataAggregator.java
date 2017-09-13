package controller;

import java.util.Set;

import model.Header;
import model.Image;
import model.Link;
import model.Media;
import model.Page;
import model.Text;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.sql.SQLException;


public class PageDataAggregator implements DataAggregator{

	/**
	 * @author Alfio Incani
	 * Classe il cui scopo principale è quello di interagire con un repository per il mantenimento dei
	 * dati, relativi ad una pagina, in memoria di massa e per il retrieve degli stessi
	 * Formatta/Sformatta il model per visualizzare/salvare i dati dello stesso
	 * 
	*/
	

	//dipendente solo dal model e dallo schema di database adottato
	//se si adotta un altro model e un altro schema di DB si riestende l'interfaccia DataAggregator
	
	
	private Store Database;
	private boolean pageExistsOnDB;
	
	public PageDataAggregator(Store Database, Page page){
		
		this.Database = Database;
		this.pageExistsOnDB = false;
	}
	
	public PageDataAggregator(Store Database){
		
		this.Database = Database;
		
	}
	
	public void saveResource(Page page) throws SQLException{
		
		Map<String, String> data = new HashMap<String, String>();
		
		boolean resourceOnDB = Database.control("webresource", "hostname", page.getHostname()); // true, se l'hostname già presente nel DB
		System.out.println(resourceOnDB);
		data.put("hostname", page.getHostname());
		
		// se la risorsa web non esiste sul db fai insert
		if(resourceOnDB) Database.insert("webresource", data);
			else return;
			
	}
	
	public void savePage(Page page) throws SQLException{
		
		Map<String, String> data = new HashMap<String, String>();
		
		this.pageExistsOnDB = Database.control("page", "url", page.getUrlPage());
		
		Header headers = page.getHeaders();
		
		data.put("url", page.getUrlPage());
		data.put("webresource_hostname", page.getHostname());
		data.put("title", page.getTitle());	
		data.put("content_type", headers.getContentType());
		data.put("content_encoding", headers.getContentEncoding());
		data.put("content_language", headers.getContentLanguage());
		data.put("cache_control", headers.getCacheControl());
		data.put("connection", headers.getConnection());
		data.put("server", headers.getServer());
		
		//se la pagina non esiste sul DB fai insert
		if(this.pageExistsOnDB)
				Database.insert("page", data);
			else
				Database.update("page", data);
	}
	
	public void saveTexts(Page page) throws SQLException{
		
		Map<String, String> data = new HashMap<String, String>();
		
		Set<Text> textSet = new LinkedHashSet<Text>();
		textSet = page.getText();
		
		for( Text text : textSet){
			System.out.println(text);
			data.put("page_url", page.getUrlPage());
			data.put("type", text.getType());
			data.put("text", text.getContent().replaceAll("'", " "));
			
			if(this.pageExistsOnDB){
				Database.insert("text", data);
				System.out.println("DIO");
			}
			else
				Database.update("text", data);
			
		}
	}
	
	public void saveLinks(Page page) throws SQLException{
		
		Map<String, String> data = new HashMap<String, String>();
		
		Set<Link> linksSet = new LinkedHashSet<Link>();
		linksSet = page.getLinksContainer();
		
		for( Link link : linksSet){
			
			data.put("page_url", page.getUrlPage());
			data.put("text", link.getLinkText().replaceAll("'", " "));
			data.put("href", link.getHref());
			data.put("title", link.getLinkTitle().replaceAll("'", " "));
			
			if(this.pageExistsOnDB)
				Database.insert("link", data);
			else
				Database.update("link", data);
			
		}		
	}
	
	public void saveImages(Page page) throws SQLException{
		
		Map<String, String> data = new HashMap<String, String>();
		
		Set<Image> imageSet = new LinkedHashSet<Image>();
		imageSet = page.getImagesContainer();
		
		for( Image image : imageSet){
			
			data.put("page_url", page.getUrlPage());
			data.put("src", image.getSrc_img());
			data.put("alt", image.getaltText_img());
			data.put("width", image.getwidth_img());
			data.put("height", image.getheight_img());
			
			if(this.pageExistsOnDB)
				Database.insert("image", data);
			else
				Database.update("image", data);
			
			data.clear();
		}
	}
	
	public void saveMedias(Page page) throws SQLException{
		
		Map<String, String> data = new HashMap<String, String>();
		
		Set<Media> mediaSet = new LinkedHashSet<Media>();
		mediaSet = page.getMediasContainer();
		
		for( Media media : mediaSet){
			
			data.put("page_url", page.getUrlPage());
			data.put("src", media.getsrc());
			data.put("type", media.gettype());
			data.put("tag", media.getTag());
			data.put("width", media.getwidth());
			data.put("height", media.getheight());
			
			if(this.pageExistsOnDB)
				Database.insert("media", data);
			else
				Database.update("media", data);
			
			data.clear();
		}
		
	}
	
	//Set<Map<String, String>>..
	public Set<Map<String, String>> getTexts(String key)throws SQLException{
		
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>>();
		
		String table="text";
		String[] attributes= new String[2];
		
		attributes[0] = "type";
		attributes[1] = "text";
		
		data = Database.read(attributes, table, " WHERE page_url="+"'"+key+"'"+";");
		return data;
	}
	
	public Set<Map<String, String>> getLinks(String key)throws SQLException{
		
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>>();
		
		String table="link";
		String[] attributes= new String[3];
		
		attributes[0] = "href";
		attributes[1] = "title";
		attributes[2] = "text";
		
		data = Database.read(attributes, table, " WHERE page_url="+"'"+key+"'"+";");
		return data;
	}
	
	public Set<Map<String, String>> getImages(String key)throws SQLException{
		
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>>();
		
		String table="image";
		String[] attributes= new String[4];
		
		attributes[0] = "src";
		attributes[1] = "alt";
		attributes[2] = "width";
		attributes[3] = "height";
		
		data = Database.read(attributes, table, " WHERE page_url="+"'"+key+"'"+";");
		return data;
	}
	
	public Set<Map<String, String>> getMedias(String key)throws SQLException{
		
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>>();
		
		String table="media";
		String[] attributes= new String[5];
		
		attributes[0] = "src";
		attributes[1] = "type";
		attributes[2] = "tag";
		attributes[3] = "width";
		attributes[4] = "height";
		
		data = Database.read(attributes, table, " WHERE page_url="+"'"+key+"'"+";");
		return data;
	}
	
	public Set<Map<String, String>> getPage(String key) throws SQLException{
		
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>>();
		
		String table="page";
		String[] attributes= new String[1];
		
		attributes[0] = "*";
		data = Database.read(attributes, table, " WHERE url="+"'"+key+"'"+";");
		
		return data;
	}
	
	public String getHostname(String key) throws SQLException{
		
		String hostname = "";
		
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>>();
		
		String attribute[] = new String[1];
		attribute[0] = "webresource_hostname";
		
		
		data = Database.read(attribute, "page", key);
		
		return hostname;
		
	}
	
	public Set<String> getUrls(String hostname) throws SQLException{
		
		Set<String> urls = new LinkedHashSet<String>();
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>>();
		String[] attribute = new String[1];
		
		attribute[0] = "url";
		data = Database.read(attribute, "page", "WHERE webresource_hostname="+"'"+hostname+"'"+";");
		
		for(Map<String, String> url : data){
			
			urls.add(url.get("url").toString());
		}
		
		return urls;
	}
	
	public void save(Page page) throws SQLException{
		
		this.saveResource(page);
		this.savePage(page);
		this.saveTexts(page);
		this.saveLinks(page);
		this.saveImages(page);
		this.saveMedias(page);
		
	}
	
	// associo tutti i dati ritornati dal database al model
	public Page get(String url) throws SQLException{
		
		Set<Map<String,String>> data = new LinkedHashSet<Map<String,String>>();
		
		data = this.getPage(url);
		
		Page page = new Page();
		
		for( Map<String, String> row : data){
			
			page.setHostname(row.get("hostname"));
			
			page.setHeaders(row.get("content_type"), row.get("content_encoding"),row.get("content_language"), row.get("cache_control"), row.get("connection"), row.get("server"));			
		}
		data.clear();
		
		data = this.getTexts(url);
		
		for( Map<String, String> row : data){
			
			page.addText(row.get("type"), row.get("text"));	
			
		}
		data.clear();
		
		data = this.getLinks(url);
		
		for( Map<String, String> row : data){
			
			page.addLinkInstance(row.get("href"), row.get("text"), row.get("title"));
			
		}
		data.clear();
		
		data = this.getImages(url);
		
		for( Map<String, String> row : data){
			
			page.addImageInstance(row.get("src"), row.get("alt"), row.get("height"), row.get("width"));
			
		}
		data.clear();
		
		data = this.getMedias(url);
		
		for( Map<String, String> row : data){
			
			page.addMediaInstance(row.get("tag"), row.get("src"), row.get("type"), row.get("height"), row.get("width"));
			
		}
		
		return page;
	}
	
	
}
