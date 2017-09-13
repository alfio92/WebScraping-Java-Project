package controller;
import java.util.Set;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface Store {
	
	public void insert(String table, Map<String, String> data) throws SQLException;
	public void update(String table, Map<String, String> data) throws SQLException;
	public Set<Map<String, String>> read(String attributes[], String table, String key) throws SQLException;
	public void connect() throws ClassNotFoundException,InstantiationException, IllegalAccessException;
	public boolean control(String table, String attribute, String data) throws SQLException;
	

}
