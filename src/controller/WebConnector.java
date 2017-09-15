package controller;
import java.sql.SQLException;
import com.jaunt.Document;
import com.jaunt.ResponseException;
import com.jaunt.NotFound;

public interface WebConnector {

	public Document resourceConnect(String url);
	public void scrap() throws ResponseException, SQLException, NotFound;
  /*
   * public Map<String, String> detectPage();
   * public Map<String, String> detectMetadata();
   * public Set<Map<String, String>> detectImage();
   * public Set<Map<String, String>> detectLink();
   * public Set<Map<String, String>> detectVideo(); 
   */
	
}
