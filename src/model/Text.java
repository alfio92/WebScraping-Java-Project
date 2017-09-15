package model;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;


public class Text {
    /*tale classe la usiamo come classe container in quanto consideriamo ogni testo 
     * non collegato a nessun altro elemento della pagina(ovviamente testi non racchiusi 
     * da nessun altro tag tranne che <h1>..<h6>, <p>, <li>, escludendo testi usati per la descrizione 
     * di ogni altro tag html)
	*/
	
	private Map<String, Set<String>> headings;  //<h1>-<h6>
	private Set<String> paragraphs; //<p>  
	private Set<String> textLists; //   <ul><li>.....<ol><li>
    
	public Text() {
		super();
		this.headings =  new LinkedHashMap<String, Set<String>>();
		this.paragraphs = new LinkedHashSet<String>();
		this.textLists = new LinkedHashSet<String>();
	}
	
	public Map<String, Set<String>> getHeadings() {
		return headings;
	}
	public void setHeadings(Map<String, Set<String>> headings) {
		this.headings = headings;
	}
	public Set<String> getParagraph() {
		return paragraphs;
	}
	public void setParagraph(Set<String> paragraphs) {
		this.paragraphs = paragraphs;
	}
	public Set<String> getLists() {
		return textLists;
	}
	public void setTextLists(Set<String> textLists) {
		this.textLists = textLists;
	}
	
	public void addParagraph(String par){
	     
		this.deleteBlankSpaces(par);
	    this.paragraphs.add(par);
	}
	
	public void addHeading(String headingTag, String heading){
		
		this.deleteBlankSpaces(heading);
	      if(this.headings.containsKey(headingTag)){
	          //se uno dei tag <h1>...<h6> contenuti in headingTag è già stato inserito
	    	  this.headings.get(headingTag).add(heading);
	      }
	      else{
	    	  this.headings.put(headingTag, new LinkedHashSet<String>());
	    	  this.headings.get(headingTag).add(heading);
	      }
	      
	}
	public void addTextList(String l){
		
		this.deleteBlankSpaces(l);
		this.textLists.add(l);
	}
	
	public String deleteBlankSpaces(String text){
		//metodo che mi serve a eliminare gli spazi bianchi a inizio stringa
		
		String unescapedString = null;
		//effettuare encoding del testo preso dalla pagina (h1, h2,....,p,...)
		
	//	unescapedString = StringEscapeUtils.unescapeHtml4(text);
		if (unescapedString.startsWith(" ")){
		//elimino gli spazi bianchi che stanno all'inizio della stringa, se presenti
			for(int i =0; i<unescapedString.length();i++){
			 
				if(!(unescapedString.substring(i).endsWith(" "))){
			        
					unescapedString = unescapedString.substring(i, unescapedString.length()-1);
				}
			}
		}
		return unescapedString;
	}
	
	@Override
	public String toString() {
		return "Text [headings=" + headings + ", paragraphs=" + paragraphs + ", lists=" + textLists + "]";
	}
}
