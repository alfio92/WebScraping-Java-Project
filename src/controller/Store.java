package controller;
import java.util.Set;
import java.sql.SQLException;
import java.util.Map;
import java.sql.ResultSet;

public interface Store {
	
	public void insert(String table, Map<String, Object> data) throws SQLException;
	public void update(String table, Map<String, Object> data) throws SQLException;
	public Set<Map<String, Object>> read(String table, String cond) throws SQLException;
	public void connect() throws ClassNotFoundException,InstantiationException, IllegalAccessException;
	public boolean controlAlreadyPageExists(String attr_table, String cond) throws SQLException;
	

}
