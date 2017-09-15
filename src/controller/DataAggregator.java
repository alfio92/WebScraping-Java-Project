package controller;

import java.io.IOException;
import java.sql.SQLException;

import model.Page;

public interface DataAggregator {
    
	//solo metodo aggregate() che poi chiamerà saveData()
	
	public void saveData(Page page) throws SQLException, IOException;
	public Page getData(String url);
}
