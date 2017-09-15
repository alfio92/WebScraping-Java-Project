package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import exceptions.PageNotFoundException;
import exceptions.ResourceNotFoundException;
import model.Page;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;


public class UserControl {
   /**
	@author Alfio Incani
	classe che viene delegata dalla UI usata dall'utente e che passa il controllo 
	a tutto il resto del sistema che elabora l'output.
	**/
	
	private WebConnector scrapUrlPage;
	private PageManager manage;
    private DataAggregator aggregator;
	
	public UserControl(WebConnector webConn){
		
		this.scrapUrlPage = webConn;
	//	this.aggregator=aggreg;
	//	this.manage = new PageManager(Database);
	}
	
    public UserControl(WebConnector webinterf, DataAggregator aggregator){
		
    	this.aggregator = aggregator;
		this.scrapUrlPage = webinterf;
	//	this.manage = new PageManager(Database);
	}

    /*
     * scrap di una pagina web , dato il suo url
     */
	public void viewScrapPageData() throws ResponseException, NotFound, SQLException{
   	
		scrapUrlPage.scrap();
		//return scrapUrlPage.getModel();
	}
	
	/*
	 * mostro tutti i contenuti di una pagina avendo dato in input il suo url, interrogando il DB
	 */
	public Set<Map<String,String>> viewPage(String url) throws SQLException, PageNotFoundException{
		
		Set<Map<String, String>> dataToView = null;
		  Page page = this.aggregator.getData(url);
			if(dataToView == null){
				
				throw new PageNotFoundException();
				
			}
			else return dataToView;

	}

	/*
	 * mostro tutte le pagine salvate di un dato hostname
	 * ricava gli url delle pagine di un hostname
	 */
	public Set<Map<String,String>> viewPagesByHost(String hostname) throws SQLException,ResourceNotFoundException{
		
		Set<Map<String,String>> dataToView = null ;
		//   dataToView = this.manage.getPagesByResource(hostname);
		if( dataToView== null){
			
			throw new ResourceNotFoundException();
		}
		else return dataToView;
	}
	
	/*
	 * salvo la pagina su DB
	 */
	public void savePage(Page page)throws SQLException, IOException{
		
		//aggregator.aggregate(Page page)
		aggregator.saveData(page);
	}

}
