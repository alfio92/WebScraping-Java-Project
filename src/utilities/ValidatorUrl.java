package utilities;
import org.apache.commons.validator.routines.UrlValidator;

public class ValidatorUrl {

	/* Classe che controlla se l'url dato in input è corretto
	 * e se possibile corregge eventuali errori presenti nella stringa dell'url
	 *    
	 */
	
	/**
	 * @author Alfio Incani
	 * @param URL scritto che l'utente ha fornito in input
	 * @return ritorna un boolean che ci dice se l'url è validato oppure no
	 */
	
	public boolean validate(String url){
		
		if(!(url.startsWith("http://") || url.startsWith("https://"))){
			
			url= "http://".concat(url);
		}
		
		if(url.contains("#")){  url =url.split("#")[0]; }
		UrlValidator validate = new UrlValidator();
		return validate.isValid(url);
	}
	
	public String getHost(String url){
		
		String host="";
		if(url.endsWith("/")){
		   host=url.substring(url.indexOf("."), url.indexOf("/"));
		}
		else{
			url=url+"/";
			host=url.substring(url.indexOf("."), url.indexOf("/"));
		}
		return host;
	}
}
