package controller;

import model.Page;
import utilities.ValidatorUrl;

import java.util.Set;
import java.util.Map;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import com.jaunt.Document;
import com.jaunt.NotFound;

/**
	@author Alfio Incani
	classe che viene delegata dalla UI usata dall'utente e che passa il controllo 
	a tutto il resto del sistema che elabora l'output.
	**/

public class Controller {
	
	private WebConnector web;
	private DataAggregator aggregator;
	private Page page;
	
	public Controller(WebConnector connector, DataAggregator aggregator){
		//inizializzo tutti i moduli dell'applicativo
		
		this.web=connector;
		this.aggregator=aggregator;
		
	}
	
	public Controller(WebConnector connector){
		
		this.web = connector;
	}
	
	public Controller(DataAggregator aggregator){
		
		this.aggregator = aggregator;
		
	}

	// salva i dati
	public void saveData(Page page)throws SQLException,IOException{
		
	
		this.aggregator.save(page);
		
	}
	
	// recupera i dati dal DB e li mostra all'utente
	public Page showDataPageFromDB(String url)throws SQLException{ //Map<String, Object> lo restituisco
		
		return aggregator.get(url);
				
	}
	
	//data un hostname mostra gli urls dello stesso
	public Set<String> showUrlPagesByResourceNameFromDB(String resourceName)throws SQLException{
		//ritorna lista di pagine salvata dato un resource name
		
		Set<String> urls = new LinkedHashSet<String> ();
		urls.addAll(aggregator.getUrls(resourceName));
		
		return urls;
		
	}
	
	/*
	 * 	ritorna i dati di una pagina su cui si è effettuato lo scraping
	 *	richiamo la componente webconnector per prendermi tutti i dati della pagina specificata
	 *  dopodichè si esegue il bind del model
	 */
	
	public Page showDataFromScraping() throws NotFound{
		
		Set<Map<String, String>> pageData = new LinkedHashSet<Map<String, String>>();				
		pageData = this.web.scrap();
		
		this.bindModel(pageData);
		
		System.out.println(page);
		return page;
		
	}
	
	//dopo aver recuperato i dati tramite il processo di scraping, si esegue il bind con i dati dell'applicativo
	public void bindModel(Set<Map<String, String>> data){
		
		this.page = new Page();
		
		for( Map<String, String> row : data){
			
			if(row.containsKey("hostname")){
				page.setHostname(row.get("hostname"));	
				page.setUrlPage(row.get("url"));
				page.setTitle(row.get("title"));
			}
			if(row.containsKey("content-type")){
				page.setHeaders(row.get("content-type"), row.get("content-encoding"),row.get("content-language"), row.get("cache-control"), row.get("connection"), row.get("server"));
				
			}
		}
		
		for( Map<String, String> row : data){
			
			if(row.get("tag") == "<p>" || row.get("tag") == "<h1>" || row.get("tag") == "<h2>" || row.get("tag") == "<h3>"
				|| row.get("tag") == "<h4>" || row.get("tag") == "<h5>" || row.get("tag") == "<h6>" || row.get("tag") == "<ul>"
				|| row.get("tag") == "<ol>" || row.get("tag") == "<td>"){
				
				page.addText(row.get("tag"), row.get("text"));	
			
			}
		}
		
		for( Map<String, String> row : data){
			
			if(row.get("tag") == "<a>"){
				page.addLinkInstance(row.get("href"), row.get("text"), row.get("title"));
			
			}
		}
		
		for( Map<String, String> row : data){
			
			if(row.get("tag") == "<img>"){
				page.addImageInstance(row.get("src"), row.get("alt"), row.get("height"), row.get("width"));
				
			}
		}
		
		for( Map<String, String> row : data){
			
			if(row.get("tag") == "<video>" || row.get("tag") == "<audio>" || row.get("tag") == "<object>" || row.get("tag") == "<embed>" || row.get("tag") == "<iframe>"){
				page.addMediaInstance(row.get("tag"), row.get("src"), row.get("type"), row.get("height"), row.get("width"));
			
			}
		}
		System.out.println(page);
	}

}
