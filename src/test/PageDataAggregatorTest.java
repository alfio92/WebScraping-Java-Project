package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

import com.jaunt.NotFound;

import controller.Controller;
import controller.HtmlScraping;
import controller.PageDataAggregator;
import controller.Store;
import model.Page;
import repository.MySQLDB;

public class PageDataAggregatorTest{

	Store db = new MySQLDB("127.0.0.1", "webconn" ,3306, "root", "");
	
	PageDataAggregator aggregator = new PageDataAggregator(db);
	@Test
	public void testSave()  throws SQLException, IOException, NotFound, IllegalAccessException, ClassNotFoundException, InstantiationException{
		db.connect();
		Page page = new Page();
		
		Controller ctrl = new Controller(new HtmlScraping("http://www.youtube.com"), aggregator);
		
		page = ctrl.showDataFromScraping();
		
		System.out.println(page);
		System.out.println(page.getHeaders().getContentType());
		System.out.println(page.getImagesContainer());
		System.out.println(page.getLinksContainer());
		System.out.println(page.getText());
		System.out.println(page.getMediasContainer());
		
		ctrl.saveData(page);
		
	}
	
	@Test
	public void testGet()  throws SQLException, IOException, NotFound, IllegalAccessException, ClassNotFoundException, InstantiationException{
		
		db.connect();
		Page page = new Page();
		
		Controller ctrl = new Controller( aggregator);
		
		page = ctrl.showDataPageFromDB("http://www.youtube.com");
		System.out.println(page);
	}
	
	@Test
	public void testGetUrl() throws SQLException, IOException, NotFound, IllegalAccessException, ClassNotFoundException, InstantiationException{
		
		db.connect();
		Set<String> res = new LinkedHashSet<String> ();
		Controller ctrl = new Controller(aggregator);
		
		res = ctrl.showUrlPagesByResourceNameFromDB("www.ilpost.it");
		
		System.out.println(res);
	}

}
