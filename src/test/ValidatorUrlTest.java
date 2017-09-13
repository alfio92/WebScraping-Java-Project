package test;

import static org.junit.Assert.*;

import org.junit.Test;
import utilities.ValidatorUrl;

public class ValidatorUrlTest {

	@Test
	public void testFixUrl() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHost() {
	
		ValidatorUrl val = new ValidatorUrl();
		String url = "https://repubblica.edu.it/file.html/";
		
		int beginIndex = url.indexOf("/");
		int splitIndex = url.indexOf(".");
		
		String part1 = url.substring(beginIndex, splitIndex);	
		String part2 = url.substring(splitIndex);
		
		int slashIndex = part2.indexOf("/");
		part2 = part2.substring(0,slashIndex+1);
		
		String hostname = part1.concat(part2);	
		hostname = hostname.substring(2, hostname.length()-1);
		
		
		
		System.out.println(hostname);
		
		String data = val.getHost(url);
	
		
	}

}
