package controller;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

/**
 * @author Alfio Incani
 * Serve all' ottenimento dei dati di tutte le varie pagine salvate, 
 * tale classe ha lo scopo di definire le condizioni SQL che servono all'ottenimento dei
 * relativi dati richiesti, che poi inoltrera al modulo relativo alla persistenza
 * esse si ottengono chiamando il modulo relativo alla persistenza dei dati
 * 
 *
 */

//nascondere i nomi degli attributi e delle tabelle del DB, lasciare solo istr. SQL 
public class PageManager {
	
	Store Database;
	
	public PageManager(Store storeable){
		this.Database = storeable;
	}
	
	
	public Map<String, Map<String, Set<Object>>> getPageByUrl(String url) throws SQLException{
		
		   //passo il nome dell'url
		
		String table = "page, metadata, text, image, link, video "; //da inserire tutte le tabelle???   "*"
		String cond = "url="+url;
		return this.Database.read(table,cond);
				
	}
	
	public Map<String, Map<String, Set<Object>>> getData(String resource)throws SQLException{ //getPagesByResource
		//passo l'hostname della risorsa
		
		String table = "webresource, page, metadata, text, image, link, video "; //da inserire tutte le tabelle?? "*"
		String cond = "hostname="+resource+" GROUP BY url";
		return this.Database.read(table,cond);
		
	}
	public void storeData(String table, Map<String, Object> data) throws SQLException{
		
		this.Database.insert(table, data);
		
	}
	public void updateData(String table, Map<String, Object>data)throws SQLException{
		
		
		this.Database.update(table, data);
	}

	
	public boolean controlOnDB(String url, String date)throws SQLException{
		//viene chiamato da "DataAggregator"
		String attr_table = "url, last_modified FROM page";
		String cond;
		   if(date==null) cond="url="+url;
		   else
		       cond = "url="+url+" AND last_modified="+date;
		return this.Database.controlAlreadyPageExists(attr_table, cond);
		
	}
	
}
