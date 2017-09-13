package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jaunt.NotFound;

import controller.HtmlScraping;

public class HtmlScrapingTest {

	@Test
	public void testConnect() throws NotFound{
		HtmlScraping html = new HtmlScraping("http://www.repubblica.it");
		
		System.out.println();
	}

	@Test
	public void testGetText() throws NotFound{
		HtmlScraping html = new HtmlScraping("http://www.repubblica.it");
		System.out.println(html.getTexts());
		System.out.println(html.getLists());
		System.out.println(html.getMedias());
	}

	@Test
	public void testGetLists() throws NotFound {
	
		HtmlScraping html = new HtmlScraping("http://www.repubblica.it");
		System.out.println(html.getLists());
	}
	
	@Test
	public void testGetMedias() throws NotFound{
		HtmlScraping html = new HtmlScraping("http://www.repubblica.it");
		System.out.println(html.getMedias());
	}
	
	@Test
	public void testGetImages() throws NotFound{
		HtmlScraping html = new HtmlScraping("http://www.repubblica.it");
		System.out.println(html.getImages());
	}
	
	@Test
	public void testGetLinks() throws NotFound{
		HtmlScraping html = new HtmlScraping("http://www.repubblica.it");
		System.out.println(html.getLinks());
	}
	
	@Test
	public void testGetTables() throws NotFound{
		HtmlScraping html = new HtmlScraping("http://www.repubblica.it");
		System.out.println(html.getTables());
	}
	
	@Test
	public void testScrapPage() throws NotFound{
		HtmlScraping html = new HtmlScraping("http://www.repubblica.it");
	//	System.out.println(html.scrap());
	}
	
	@Test
	public void testGetHeaders() throws NotFound{
		HtmlScraping html = new HtmlScraping("http://www.repubblica.it");
		System.out.println(html.getHeaders());
	}

}
