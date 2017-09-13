package controller;

import com.jaunt.NotFound;
import java.util.Set;
import java.net.URISyntaxException;
import java.util.Map;


public interface WebConnector {

	public void connect() throws NotFound;
	public Set<Map<String, String>> scrap() throws NotFound;
    
}
