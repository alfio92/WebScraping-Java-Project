package controller;
import com.jaunt.Document;
import com.jaunt.Elements;
import com.jaunt.Element;
import com.jaunt.UserAgent;
import com.jaunt.UserAgentSettings;
import com.jaunt.util.MultiMap;
import com.jaunt.ResponseException;
import com.jaunt.NotFound;
import com.jaunt.JauntException;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.List;


public abstract class WebScraper implements WebConnector{
	
	private Document html;
	private UserAgent useragent;
	
	public void connect(String url){
		
		try{	
			useragent = new UserAgent();
			UserAgentSettings stg = useragent.settings;
			
			/*
	        imposto a falso autoredirect, poichè settato a "true" dà 
	        problemi di connessione ad alcuni siti
	       */
			
	        stg.autoRedirect=false; 
	        
	        this.html = useragent.visit(url);
	 		
		} 
			// eccezione, se la pagina restituisce un errore HTTP
			catch(ResponseException e){ 
											
										System.err.println(e.getMessage());
											
										}
	}
	
	//estraggo il contenturo dei tag specificati in input
	
	public Set<Map<String, String>> extractContentTag(String tag, String[] nestedTags, String[] attributes, String rootTag) throws NotFound{
		
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>>();
		
		Element root = html.findFirst(rootTag);
	
			Elements content = root.findEvery(tag); 
			
			for(Element current : content){
			
				if(nestedTags.length > 0 && attributes.length > 0){
					for(int i=0; i<nestedTags.length; i++){
						Elements nested = current.findEvery(nestedTags[i]);
						Map<String, String> tagData = new LinkedHashMap<String, String>();
						tagData.put("tag", tag);
						for(Element currentTag : nested){
							
							for(int j = 0; j<attributes.length; j++){
							
								tagData.put(attributes[j], currentTag.getAtString(attributes[j]));
								
							}	
							
							tagData.put("text", currentTag.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," "));
							data.add(tagData);
						}
				
					}
				}
				else if(nestedTags.length > 0){// vi sono tag innestati da rilevare, ma non attributi
					
						for(int i=0; i<nestedTags.length; i++){
							Elements nested = current.findEvery(nestedTags[i]);
							Map<String, String> tagData = new LinkedHashMap<String, String>();
							tagData.put("tag", tag);
							for(Element currentTag : nested){
								
								tagData.put("text", currentTag.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," "));
								data.add(tagData);
							}
						}
					
				}
				else if(attributes.length > 0){// vi sono attributi da rilevare, ma non tags innestati
					
						Map<String, String> tagData = new LinkedHashMap<String, String> ();
						tagData.put("tag", tag);
						for(int j = 0; j<attributes.length; j++){
								
							tagData.put(attributes[j], current.getAtString(attributes[j]));
									
						}			
						tagData.put("text", current.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," "));
						data.add(tagData);
					
				}
				else{// non vi sono ne attributi e ne tags innestati da rilevare
						Map<String, String> tagData = new LinkedHashMap<String, String> ();
						tagData.put("tag", tag);
						for(int j = 0; j<attributes.length; j++){
								
							tagData.put(attributes[j], current.getAtString(attributes[j]));
									
						}	
								
						tagData.put("text", current.innerText("<br>",null, null, false, false, true, true).replaceAll("<br>"," "));
						data.add(tagData);
				}
			}
			
		return data;
	}
	
	
	//accedo agli headers della pagina
	
	public Map<String, String> headRequest(String url, String[] headerAttributes){
		
		Map<String, String> headers = new HashMap<String, String>();
		
		//si solleva eccezione nel caso in cui la richiesta head non è andata a buon fine
		try{
			
			useragent.sendHEAD(url);
			MultiMap<String,String> header = useragent.response.getHeaders();		
			
			for(int j=0; j<headerAttributes.length; j++){
				
				headers.put(headerAttributes[j], header.getFirst(headerAttributes[j]));
				
			}	
				
		}catch(JauntException e){  System.err.println(e);  }
		
		return headers;
		
	}
	
	public String extractTextFromOneTag(String tag) throws NotFound{
		
		String data="";
		
		Element tagData = html.findFirst(tag);
		data = tagData.innerText();
		
		return data;
	}
	
}
