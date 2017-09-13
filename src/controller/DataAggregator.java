package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import model.Page;

public interface DataAggregator {
	
	public void save(Page page) throws SQLException, IOException;
	public Page get(String url) throws SQLException;
	public Set<String> getUrls(String resource)throws SQLException;
}
