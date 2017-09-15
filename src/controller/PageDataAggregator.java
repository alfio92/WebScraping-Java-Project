package controller;
import java.util.LinkedList;
import java.util.Set;

import model.Image;
import model.Link;
import model.Model;
import model.Page;
import model.Video;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PageDataAggregator implements DataAggregator{

	/**
	 * @author Alfio Incani
	 * Classe il cui scopo principale è quello di interagire con un repository per il mantenimento dei
	 * dati, relativi ad una pagina, in memoria di massa e per il retrieve degli stessi
	 * 
	*/
	
	//vi sono i vincoli posti dal DB per la lunghezza dei dati

	private Store Database;
	private Page page;
	
	
	public PageDataAggregator(Store Database, Page page){
		
		this.Database = Database;
		this.page = page;
	}
	
	/*
	public void createPage(String resourceName, String url,String title,String lang,String last_modified_date){
		
	//	if(url.length()>= 256||title.length()>=128) {}
		if(resourceName==null || title==null) return; 
		this.page = new Page(resourceName, url, title, lang, last_modified_date);
	}
	
	public void createMetadata(String author,String description,String charset,String keywords){
		
		if(author == null && description==null && charset==null && keywords==null) return;
		page.addMetadata(author, description, charset, keywords);
		
	}
	public void addText(String tagHeader,String headerText){
		
		if(tagHeader == null || headerText == null ||(headerText.length() >= 256)) return;
		this.deleteBlankSpaces(headerText);
		page.addHeadingText(tagHeader, headerText);
	}
	public void addParagraph(String paragraph){
		
		if(paragraph == null || paragraph.length() > 1024) return;
		this.deleteBlankSpaces(paragraph);
		page.addParagraphText(paragraph);
	}
	public void addTextList(String listText){
		
		if(listText== null || listText.length() > 256)return;
		this.deleteBlankSpaces(listText);
		page.addTextList(listText);
	}
	public void createImage(String src,String imgDescription,String altText,String width,String height){
		
		if(src == null || src.length() >1024 || altText.length() > 256)return;
		if(width.length() > 4) width=null;
		if(height.length() > 4) height=null;
		page.addImageInstance(src, imgDescription, altText, height, width);
	}
	public void createLink(String anchor,String linkText,String linkTitle,String linkType){
		
		if(anchor==null || anchor.length()>1024 || linkText.length()>256)return;
		page.addLinkInstance(anchor, linkText, linkTitle, linkType);
	}
	public void createVideo(String src,String videoTechnology,String width,String height){
		
		if(src==null || src.length() >1024) return;
		if(width.length() > 4) width=null;
		if(height.length() > 4) height=null;
		page.addVideoInstance(src, videoTechnology, width, height);
	}
	
	*/
	
	public void saveData() throws SQLException{
		
		//pattern per il save su DB: -passare la map con i dati alla classe astratta "Database" e che richiama tale metodo che esegue la transazione su DB
		//mantenere i nomi degli attributi del DB all'interno della classe "Database" per un maggior riuso software
		
	/*	Map<String,Map<String,Set<Object>>> data = new LinkedHashMap<String,Map<String,Set<Object>>> ();
	 *   String[] Tables;
		for(){
		data.put(this.page.getClass().getSimpleName(), new LinkedHashMap<String,Set<String>>()) ;
		}  */
		
		/*
		List<String> Tables = new LinkedList<String>();
	
		Field[] attributes = page.getClass().getFields();
		Class<?>[] modelClasses = page.getClass().getClasses();
		for(int i= 0; i<modelClasses.length;i++){
		 if(Tables.get(i)==null){
			 
			Tables.add(modelClasses[i].getClass().getSimpleName());
		 }
					
		}
		
		
		List<Object> tables = null;
		Object o = null;
		for(int i=0; i<tables.size();i++){
			if(tables.get(i)==null){
		    o = page.getClass().getClasses();
			}
		}
		
		Map<String,Object> data= new LinkedHashMap<String, Object>();
		//controllo se la pagina è già presente nel database
		boolean pageNotExists = managePage.controlOnDB(page.getUrlPage(), page.getLast_modified_date());
		//stampo dati di tutta la pagina appena parsata
		
		page.print();
		
		//aggiungo dati pagina
		//aggiungo hostname al db
		data.put("hostname", page.getHostname());
		 if(pageNotExists) managePage.storeData("webresource", data); //se la pagina non esiste nel db fai insert, update altrimenti
		    else managePage.updateData("webresource", data);
		 data.clear();
		 
		   //aggiungo dati pagina al db
		 
		data.put("url", page.getUrlPage());
		data.put("last_modified", page.getLast_modified_date());
		data.put("title", page.getTitle());
		data.put("lang_page", page.getLanguage());
		 if(pageNotExists) managePage.storeData("page", data);	
		    else managePage.updateData("page", data);
		 data.clear();
		 
		//aggiungo metadata al db
		 Metadata meta = page.getMetadata();
		 
		data.put("author", meta.getAuthor());
		data.put("description", meta.getDescription());
		data.put("charset", meta.getCharset());
		data.put("keywords", meta.getKeywords());
		 if(pageNotExists) managePage.storeData("page", data);	
		    else managePage.updateData("page", data);
        data.clear();
		 
		 //aggiungo tutti i testi <h1>....<h6> al db
		 Map<String, Set<String>> headers = page.getText().getHeadings();
		  
			   
			   for(Map.Entry<String, Set<String>> entry : headers.entrySet()){
				    Set<String> headerTexts = entry.getValue();
				        for(String header : headerTexts ){
				          data.put("header_text", entry.getKey()+"_"+header);
				          if(pageNotExists) managePage.storeData("text", data);
				          else managePage.updateData("text", data);
				          data.clear();
				        }
			   }   
			   
	    //aggiungo tutti i <p>
			   Set<String> paragraphs = page.getText().getParagraph();
			     for(String par : paragraphs){
			    	 data.put("paragraph", par);
			    	 if(pageNotExists) managePage.storeData("text", data);
			          else managePage.updateData("text", data);
			          data.clear();
			     }
		//aggiungo tutti i tag <li>
			   
			   Set<String> lists = page.getText().getLists();
				 for(String text : lists){
				     data.put("text_list", text);
				     if(pageNotExists) managePage.storeData("text", data);
				      else managePage.updateData("text", data);
				      data.clear();
				     } 
		//aggiungo img
			   Set<Image> images = page.getImagesContainer();
				 for(Image img: images){
					 data.put("src_img", img.getSrc());
					 data.put("alt_text_img", img.getAltText());
					 data.put("width_img", img.getWidth());
					 data.put("height_img", img.getHeight());
					 if(pageNotExists)managePage.storeData("image", data);
					 else managePage.updateData("image", data);
					 data.clear();
				 }
		//aggiungo 	links	
			   Set<Link> links = page.getLinksContainer();
				 for(Link link : links){
					 data.put("href", link.getAnchor());
					 data.put("link_text", link.getLinkText());
					 data.put("link_title", link.getLinkTitle());
					 data.put("link_type", link.getLinkType());
					 if(pageNotExists)managePage.storeData("image", data);
					 else managePage.updateData("image", data);
					 data.clear();
				 } 
		//aggiungo video		 
		       Set<Video> videos = page.getVideosContainer();
		         for(Video vid : videos){
		        	 data.put("src_vid", vid.getSource());
					 data.put("video_tech", vid.getVideoTechnology());
					 data.put("width_vid", vid.getWidth());
					 data.put("height", vid.getHeight());
					 if(pageNotExists)managePage.storeData("image", data);
					 else managePage.updateData("image", data);
					 data.clear();
		         } */
	}
	
	public void getTablesAtrribute(){}
	public Map<String,Map<String,Set<Object>>> aggregate(){
		//aggrego il model in una struttura dati per il save su DB
		//tables: webresource, page, metadata, text, image, link, video
		String[] tables = new String[7];
		tables[0]="webresource";
		tables[1]="page";
		tables[2]="metadata";
		tables[3]="text";
		tables[4]="image";
		tables[5]="link";
		tables[6]="video";
				
		Map<String,Map<String,Set<Object>>> data = new LinkedHashMap<String, Map<String,Set<Object>>>();
		data.put("webresource", new LinkedHashMap<String, Set<Object>>());
		data.put("page", new LinkedHashMap<String, Set<Object>>());
		data.put("metadata", new LinkedHashMap<String, Set<Object>>());
		data.put("text", new LinkedHashMap<String, Set<Object>>());
		data.put("image", new LinkedHashMap<String, Set<Object>>());
		data.put("link", new LinkedHashMap<String, Set<Object>>());
		data.put("video", new LinkedHashMap<String, Set<Object>>());
		
		
		return data;
	}
	
	public Page getData(String input){
		Page page=null;
		
	/*
	 * if (input is url){ manager.getDataPageByUrl(input)}
	 * if (url is resourceName){ manager.getPagesByResource(input)}
	 */
		
		return page;
	}
	
	/*le routine qui presenti è una migliore pratica eseguirle in un'altra classe 
	 * che interagisce direttamente con i dati del model che richiama tale classe 
	 * eseguendo "getData" e "saveData"??
	 * 
	 * RetrieveandStoreModel
	
	*/
	//public boolean controlKey(String host){}
	
	                                       //rows
	public void saveData(String table, String key){}
			
	                                //rows
	public void saveData(String table){}
			
	public void saveData(Page page)throws IOException, SQLException{
		//      saveData(table,rows,key)
		
		
		//get classes name(tabelle) getAttributes getTypingAttributes(attributi)	
		//this.page.aggregate();
		//se in una pagina già esistente(si fa UPDATE) bisogna inserire nuovi dati che non erano presenti prima, possofare "UPDATE"??
		//ControlOnDB() controllo se esiste già l'hostname di una pagina nel database
		//if(controlOnDB){ insertData()} else { updateData()}
		//get tables (get all classes of model package), use Guava for retrieve className
		//get data (get all attributes name)
		//store data where url="url"
		
		//Map<String, Map<String, Set<Object>>> data = getClassName();
		   
		//tableName   attrName
		Map<String,Set<String>> dataType = getClassName();
		
		for(Map.Entry<String, Set<String>> map : dataType.entrySet()){
			   Set<String> attributesName = map.getValue(); 
		//	   Set<Map<String, Object>> rows = new LinkedHashSet<>
			   Map<String,Object> rows = new LinkedHashMap<String,Object>();
			   for(String attr : attributesName){
				   
				   rows.put(attr,null);
			   }
			                     //table         
			 managePage.storeData(map.getKey(), attributesName, retrieveModelData(attributesName));
		}
	}

	public Set<Map<String, Object>> retrieveModelData(Set<String> attributes){
		
		
		Map<String, Object> rows = new LinkedHashMap<String,Object>();
		
		for(Map.Entry<String, Object> row : rows.entrySet()){
			
			for(String attr : attributes){
		     rows.put(attr, page.getUrlPage());
		     rows.put(attr, page.getTitle());
			}
		//	rowthis.page.getHostname();
			
		}	
		return rows;
		
	}
	
	public boolean control(String key){
		boolean control=true;
		
		return control;
	}
	
	public void storeData(String key, String entity, Map<String, Object> rowsToStore)throws SQLException{
		
		String condition="";
		if(this.control(key)){
			Database.update(entity, rowsToStore);
		}
		
	}
	
	public Map<String, Object> getData(){
		
		String condition="";
		Map<String, Object> rows = new LinkedHashMap<String, Object>();
		
		return rows;
		
	}
	
}



