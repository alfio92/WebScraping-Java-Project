package controller;

import com.jaunt.Document;
import com.jaunt.UserAgent;
import com.jaunt.UserAgentSettings;
import com.jaunt.ResponseException;

public abstract class ResourceConnect implements WebConnector {
	
	/*classe astratta che serve a non ripetere il codice ogni volta che si 
	  implementa l'interfaccia WebConnectorInterface   */

	public Document resourceConnect(String url){
		//metodo per la connessione all'url specificato che mi restituisce tutto il documento della pagina
		
		Document html;
		UserAgent useragent;
		
		try{	
		useragent = new UserAgent();
		UserAgentSettings stg =useragent.settings;
        stg.autoRedirect=false; /*imposto a falso autoredirect, poichè settato a "true" dà 
                                  problemi di connessione ad alcuni siti
		                         */
		html = useragent.visit(url);
			
		return html;
		
		} 
		// eccezione, se la pagina restituisce un errore HTTP
		catch(ResponseException e){ System.err.println(e.getMessage());}
		
	return null;		
	}
		
}
