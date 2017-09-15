package controller;
import model.Image;
import model.Link;
import model.Page;
import model.Video;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class RetrieveStoreData {
	
	private Page page;
	private DataAggregator aggregator;
	private WebConnector webc;
	
	public RetrieveStoreData(Page page, DataAggregator aggregator, WebConnector webc){
		this.page=page;
		this.aggregator=aggregator;
		this.webc= webc;
		
	}
	
	public void Store()throws SQLException, IOException{
		
	  this.saveWebresourceData();
	  this.savePageData();
	  this.saveMetadataData();
	  this.saveTextData();
	  this.saveImageData();
	  this.saveLinkData();
	  this.saveVideoData();
		
	}
	
	public void connectToWebConnector(){
		
		/*
		 * this.webc.scrap();
		 * Map<String, String> pageData= this.webc.detectPage();
		 *  this.page.setTitle(pageData.get("title"));
		 * Map<String, String> metadata= this.webc.detectMetadata();
		 * this.webc.detectText();
		 * Set<Map<String, String>> imagedata= this.webc.detectImage();
		 * Set<Map<String, String>> linkdata=this.webc.detectLink();
		 * Set<Map<String, String>> videodata=this.webc.detectVideo();
		 * 
		 * 
		 */
	}
	
	public void saveWebresourceData(){
		
		String host = page.getHostname();
		//aggregator.save(table, key, rows)

	}
	
	public void savePageData(){
		
		Map<String, Object> rows = new LinkedHashMap<String, Object>();
		rows.put("url", page.getUrlPage());
		rows.put("last_modified", page.getLast_modified_date());
		rows.put("title_page", page.getTitle());
		rows.put("lang_page", page.getLanguage());
		rows.put("fk_webresource", page.getHostname());
		//delego dataAggregator a salvare i dati relativi alla pagina 
		//aggregator.saveData(table, key, rows);
		
	}
	
	public void saveMetadataData(){
		
     Map<String, Object> rows = new LinkedHashMap<String, Object>();
        rows.put("author", page.getMetadata().getAuthor());
        rows.put("description", page.getMetadata().getDescription());
        rows.put("charset", page.getMetadata().getCharset());
        rows.put("keywords", page.getMetadata().getKeywords());
        rows.put("fk_webresource", page.getHostname());
      //delego dataAggregator a salvare i metadati della pagina 
        //aggregator.saveData(table, key, rows)
        
	}
	
	public void saveTextData(){
		
		  Map<String, Object> rows = new LinkedHashMap<String, Object>();
		
		//aggiungo tutti i testi <h1>....<h6> al db
		 Map<String, Set<String>> headers = page.getText().getHeadings();
		 Set<String> paragraphs = page.getText().getParagraph();
		 Set<String> lists = page.getText().getLists();
		 Set<String> headerText = new LinkedHashSet<String>();
		 
		 for(Map.Entry<String, Set<String>> map : headers.entrySet()){
			 for(String s : map.getValue()){
			  headerText.add(map.getKey()+": "+s);
			 }
		 }
		 
		 Iterator itp = paragraphs.iterator();
		 Iterator itli = lists.iterator();
		 Iterator itheads = headerText.iterator();
		 
		 String par="",listtext = "", header="";
		 
		  while(itp.hasNext() || itli.hasNext()|| itheads.hasNext()){
			  
		  	 if(!itp.hasNext())   par = "";
		  	 if(!itli.hasNext())  listtext = "";	
			 par = (String)itp.next();
			 listtext =(String) itli.next();
			 header=(String) itheads.next();
			 rows.put("header_text", header);
			 rows.put("paragraph", par);
			 rows.put("list_text", listtext);
			 rows.put("fk_page", page.getUrlPage());
			 //aggregator.saveData(table, rows)
			  //
		  }
		
	}
	
	public void saveImageData(){
		
		 Map<String, Object> rows = new LinkedHashMap<String, Object>();
		
		 Set<Image> images = page.getImagesContainer();
		 for(Image img: images){
			 rows.put("src_img", img.getSrc_img());
			 rows.put("description_img", img.getDescription());
			 rows.put("alt_text_img", img.getaltText_img());
			 rows.put("width_img", img.getwidth_img());
			 rows.put("height_img", img.getwidth_img());
			 rows.put("fk_page", page.getUrlPage());
		     //delego dataAggregator a salvare le immagini della pagina
			 //aggregator.saveData(table, key, rows)
			 
			 /*
			 if(pageNotExists)managePage.storeData("image", data);
			 else managePage.updateData("image", data);
		     */
		 }
		
	}
	
	public void saveLinkData(){
		
		 Map<String, Object> rows = new LinkedHashMap<String, Object>();
		
		Set<Link> links = page.getLinksContainer();
		 for(Link link : links){
			 rows.put("href", link.getAnchor());
			 rows.put("title_link", link.getLinkText());
			 rows.put("text_link", link.getLinkTitle());
			 rows.put("type_link", link.getLinkType());
			 //delego dataAggregator a salvare i link della pagina
			 //aggregator.saveData(table, rows.get("fk_page"), rows);
			 
			/* 
			 if(pageNotExists)managePage.storeData("image", data);
			 else managePage.updateData("image", data);
			 data.clear();
			 */
		 } 
	
	}
	
	public void saveVideoData(){
		
		 Map<String, Object> rows = new LinkedHashMap<String, Object>();
		
		Set<Video> videos = page.getVideosContainer();
        for(Video vid : videos){
       	     rows.put("src_vid", vid.getsrc_vid());
			 rows.put("tech_vid", vid.getVideoTechnology());
			 rows.put("width_vid", vid.getwidth_vid());
			 rows.put("height_vid", vid.getheight_vid());
			 //delego dataAggregator a salvare i video della pagina
			 //aggregator.saveData(table, key, rows);
			 
			 /*
			 if(pageNotExists)managePage.storeData("image", data);
			 else managePage.updateData("image", data);
			 data.clear();
		     */
        }
	}

	public void getUrlsByHostname(String host){}
	public void getPageDataByUrl(String url){}
	public Page getModel(){
		return this.page;
	}
	
}
