package controller;
import java.util.List;

import com.jaunt.NotFound;
import com.jaunt.*;
import com.jaunt.util.*;

import model.Page;
import utilities.ValidatorUrl;

/*
 * aggiustare la classe in modo che restituisca tutti i dati e non utilizzi il model
 * il bind del model lo fa la classe che chiama questa
 * trovare metodo di jaunt che restituisce il nome del tag o dell'attributo del tag
 * cambiare il model relativo al text con attributi "tagtext, textvalue", cambiare quindi la tabella 
 * del DB relativa ai testi
 */


public class WebScraping extends ResourceConnect{
	
	
	private UserAgent useragent;
	private Document html;
	private Element head;   //node
	private Element body;   //rootTag
	private Page model;
	private String url;
	
    public WebScraping(String url, Page page) {
		super();
		this.url=url;
		this.html = null;
		this.model = page;
	//	this.aggregator = new PageDataAggregator(Database);
	} 

	
	
	public String sendHeadRequest(String url){
		//mi serve per ottenere la data ultima modifica
		String last_modified_date = "";
		//si solleva eccezione nel caso in cui la richiesta head non è andata a buon fine
		try{	
		useragent.sendHEAD(url);
		MultiMap<String,String> header = useragent.response.getHeaders();
		if(header.containsKey("Last-Modified")){
			last_modified_date = header.getFirst("Last-Modified");  //data ultima modifica della pagina
		   }
		else last_modified_date = null;  
		     
		  }catch(JauntException e){  System.err.println(e);  }
			
		return last_modified_date;
	}
	
	public void detectMetadata() throws NotFound{
		
		//ottengo i metadati
		 String charset="", author="", description="", keywords="";
		  
		   Elements metadata = head.findEvery("<meta>");
		      for(Element metatag : metadata){
		    	   if(metatag.hasAttribute("charset"))charset = metatag.getAtString("charset");
		    	   if(metatag.hasKeyword("author")) author = metatag.getAtString("content");
		    	   if(metatag.hasKeyword("description")) description = metatag.getAtString("content");
		    	   if(metatag.hasKeyword("keywords")) keywords = metatag.getAtString("content");
		    	   
		 //se il charset è specificato in un attributo content, diversi siti lo inseriscono in "content='text/html; charset=....' 
		    	   if(charset == null) {
		    		   
		               List<String> s = metatag.findAttributeValues("<meta content='text/html; charset='");
		                charset = s.get(0).substring(s.get(0).indexOf("charset="), s.get(0).length()-1);
		                } 	  
		      }
		
		   this.model.addMetadata(author, description, charset, keywords);
	}
	
	public void detectText() throws NotFound{
		//parsare anche i testi dei tag <td>, tables <tables> <tr>"nome-dati"</tr><td>...</td>
		
		//parso l'html della pagina
		   //ottengo i testi della pagina
		   //ottengo gli headers della pagina
		   
		   int i = 1;    //identifica tipo di tag header
		   while(i < 7){
			   String tagHeader = "<h" + i + ">";
			   Elements headers = body.findEvery(tagHeader);
			 for(Element elem : headers){  //all'interno di tags che denotano del testo vi possono essere altri tags
				//itero sugli header tag <h1>....<h6>
				 String headerText = elem.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," ");
				 this.model.addHeadingText(tagHeader, headerText);
			 }  
			   i++;
		   }
		   //ottengo i paragraphs della pagina
		   Elements paragraphs = body.findEvery("<p>");
		   for(Element elem : paragraphs){
			   //itero su ogni tag <p> trovato
			   String paragraph = elem.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," ");
			   this.model.addParagraphText(paragraph);
		   }
		   
		   //ottenimento testi sotto forma di liste tag <ul> , tag <ol>
		   Elements unorderedLists = body.findEvery("<ul>");
		   Elements orderedLists = body.findEvery("<ol>");
		   
		   for(Element elem : unorderedLists){
			   //itero su ogni tag <li> trovato nella pagina
			   Elements lists = elem.findEvery("<li>");
			         for(Element list : lists){
			        
			                  String listText = list.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," ");
			                  this.model.addTextList(listText);
			         }
		   } 
		   for(Element elem : orderedLists){
			   //itero su ogni tag <li> trovato nella pagina
			   Elements lists = elem.findEvery("<li>");
			         for(Element list : lists){
			        	
			                  String listText = list.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," ");
			                  this.model.addTextList(listText);
			        	 
			         }
		   } 
		   //fine ottenimento testi
		   
	}
	public void detectImage(){
		
		 //ottengo immagini
		Elements images = body.findEvery("<img>");
	     for(Element image : images){
	    	//itero su ogni tag img della pagina trovato
	    	  String src = image.getAtString("src"); 
	    	  String imgDescription = image.getAtString("description"),
	    			 altText = image.getAtString("alt"),
	                 width = image.getAtString("width"),
	    		     height = image.getAtString("heigth");
	    	  //aggiungo ogni nuova immagine trovata nella pagina
	    	 this.model.addImageInstance(src, imgDescription, altText, width, height);
	    	  
	     }
	     //alcune immagini possono essere embeddate in un div
	    Elements divImg = body.findEvery("<div style='background-image:'>"); 
	     for(Element img : divImg){
	    	 String src = img.getAtString("background-image").substring(4); 
	    	 String imgDescription = img.getAtString("description");
	    	   if (imgDescription==null){
	    		    img.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," ");
	    		   
	    	   }
	    	 
	    	 String	altText = img.getAtString("alt"),
	                 width = img.getAtString("width"),
	    		     height = img.getAtString("heigth");
	     this.model.addImageInstance(src, imgDescription, altText, width, height);
	     }
	     
	     //fine ottenimento immagini
	}
	public void detectLinks(){
				 
	     //ottengo Links
		Elements links = body.findEvery("<a>");
	         for (Element link : links){
	        	   //itero  su ogni tag link trovato
	        	   String anchor = link.getAtString("href"),
	        			   linkText = link.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," "),
	        			   linkType = link.getAtString("type"),
	        			   linkTitle = link.getAtString("title");
	          //aggiungo ogni nuovo link trovato nella pagina   
	            	this.model.addLinkInstance(anchor, linkText, linkType, linkTitle);
	         }
	         //fine ottenimento links
	}
	
	public void detectVideos() throws NotFound{
		
		  //ottengo video  <video>, <embed>, <object>
	      //utilizzo di html5
		
		ValidatorUrl validate = new ValidatorUrl();
		
		 boolean tagvideo = false;
	     Elements videos = body.findEvery("<video>");
	     String src = "",width="",height="",playerVideo="";
	     if(videos != null){
	    	 tagvideo=true;
	         for (Element video : videos ){
	                //itero su ogni tag <video> trovato
	                src = video.getAtString("src");
	                if (src == null){
	                	
	                Element source = video.findFirst("<source>"); //getAtString("src");
	                src = source.getAtString("src");
	                }
	                       width = video.getAtString("width");
	                       height = video.getAtString("height");
	                       playerVideo = "html5";
	                
	                this.model.addVideoInstance(src, width, height, playerVideo);
	         }
	         return;
	     }
	     
	     if(!tagvideo){
	      videos = body.findEvery("<embed>");
	          for(Element embedVideo : videos){
	        	  if(embedVideo.hasAttribute("allowFullScreen") && embedVideo.hasAttribute("src")){
	        		  src = embedVideo.getAtString("src");
	        		  height = embedVideo.getAtString("heigth");
	        		  width = embedVideo.getAtString("width");
	        		  playerVideo = embedVideo.getAtString("type");
	        		  this.model.addVideoInstance(src, width, height, playerVideo);
	        	  
	            }
	          }
	          return;
	     } 
	     

	     
	     else{   
	      videos = body.findEvery("<object>");
	      Elements parameters = videos.findEvery("<param>");
	      for(Element par : parameters){
              if(par.hasAttribute("allowFullScreen")){
	                for(Element objectVideo : videos){
	                	src = objectVideo.getAtString("data");
	                	if(src==null){
	                		objectVideo.getAtString("src");
	                	}
	                	
	                	width = objectVideo.getAtString("width");
	                    height = objectVideo.getAtString("height");
	                    playerVideo = objectVideo.getAtString("type");
	                 }
	                this.model.addVideoInstance(src, width, height, playerVideo);
	        	  }
           }
	     }
	
	     //iframe video youtube api
	     videos= body.findEvery("<iframe>");
	     for(Element iframe : videos){
	    	 src = iframe.getAtString("src");
	    	   if(!validate.getHost(src).contains("youtube")){
	    		       src=null;
	    	   }
	    	 width = iframe.getAtString("width");
             height = iframe.getAtString("height");  
             playerVideo = "YouTube iframe API";
             
             this.model.addVideoInstance(src, width, height, playerVideo);
	     }
	     
	}

 	public void detectPage(String url)throws NotFound{
 		  String last_modified_date;
 		  //eseguo una richiesta HEAD per ottenere la data corrente del server cui mi collego
 		   last_modified_date = sendHeadRequest(url);	
 		//   String resourceName = url.substring(url.indexOf("http://"), url.indexOf("/"));		        
 		   String title = head.findFirst("<title>").innerText();
 		   String lang = head.getAtString("lang");
 		   if(lang==null) lang = head.getAtString("xml:lang");
 
 		   this.model.setTitle(title);
 		   this.model.setLanguage(lang);
 		   this.model.setLast_modified_date(last_modified_date);
 	}
	public void scrap() throws ResponseException, NotFound{
		//ottengo i dati relativi alla pagina web
	  try{
		
	   this.html = super.resourceConnect(url);
	   this.head = html.findFirst("<head>");
	   this.body = html.findFirst("<body>");
	  } 
	  catch(NotFound e){ e.printStackTrace();}
	   detectPage(this.url);
	   detectMetadata();
	   detectText();
	   detectImage();
	   detectLinks();
	   detectVideos();
	  
	         }
	
	public Page getModel(){
		return this.model;
	}
}
 