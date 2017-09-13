package test;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.jaunt.Document;
import com.jaunt.NotFound;

import controller.HtmlScraping;
import controller.WebScraper;

public class WebScraperTest {

	private HtmlScraping web = new HtmlScraping("http://www.repubblica.it/");
	
	@Test
	public void testTestConnect() {
		fail("Not yet implemented");
	}

	@Test
	public void testTestExtractContentTag() throws NotFound{
	
		
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>> ();	
		
		
		String[] nested = new String[3];
    	
    	nested[0] = "<tr>";
    	nested[1] = "<th>";
    	nested[2] = "<td>";
    	data = web.extractContentTag("<table>", nested, new String[0], "<body>");
    	
		
		System.out.println(data);
		
		
	}

	@Test
	public void testGetMedata() {
		fail("Not yet implemented");
	}

	@Test
	public void testConnect() throws NotFound {
		
		web.connect(web.getUrl());
		 
        
		//System.out.println(title);
	}

	@Test
	public void testGetPageData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMetadata() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetImages() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLinks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMedias() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTexts() {
		fail("Not yet implemented");
	}

}
