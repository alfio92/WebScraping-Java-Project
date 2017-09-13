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
		
		UrlValidator validator = new UrlValidator();
		
		// this.fixUrl(url);
		
		return validator.isValid(url);
	}
	
	public String fixUrl(String url){
		
       if(!(url.startsWith("http://") || url.startsWith("https://"))){
			
			url= "http://".concat(url);
		}
		
		if(url.contains("#"))  url = url.substring(0, url.indexOf("#")); 
		
		return url;
	}
	
	public String getHost(String url){
		
		String hostname="";
		
		if(!url.endsWith("/")) url = url + "/";
		
		int beginIndex = url.indexOf("/");
		int splitIndex = url.indexOf(".");
		
		String part1 = url.substring(beginIndex, splitIndex);	
		String part2 = url.substring(splitIndex);
		
		int slashIndex = part2.indexOf("/");
		part2 = part2.substring(0,slashIndex+1);
		
		hostname = part1.concat(part2);	
		hostname = hostname.substring(2, hostname.length()-1);
		
		return hostname;
	}
}
