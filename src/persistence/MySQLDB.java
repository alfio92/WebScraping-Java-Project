package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;

import controller.Store;

import java.util.LinkedHashSet;
/**
 * Si implementano i metodi MySQL per operare sul database
 * @author Alfio Incani
 *
 */
//
public class MySQLDB extends RelationalDB{
	
	private String host;
	private String DBName;
	private int port;
	private String UserId;
	private String pass;
	private Connection database;
	
	public MySQLDB(String host, String DBName, int port, String UserId, String pass ){
		
		//registro jdbc driver
		//formulo url del database
		this.host=host;
		this.DBName= DBName;
		this.port= port;
		this.UserId = UserId;
		this.pass = pass;
		
	}
	
	public void connect() throws ClassNotFoundException,InstantiationException, IllegalAccessException{
		
		//formulo url del database
		//collegamento al database
		
		this.database=null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.database = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + DBName, UserId, pass);
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }	
		
	}
	public void insert(String table, Map<String, Object> data) throws SQLException{
		//eseguo transazione al database per inserire una intera pagina parsata
		
		Statement statement = this.database.createStatement();
		String ins_query = "INSERT INTO "+table+" SET ";
		for (Map.Entry<String, Object> entry: data.entrySet()){
			
			ins_query = ins_query + entry.getKey().replaceAll("'", "''") + " = '" +entry.getValue().toString().replaceAll("'", "''")+ "', ";
		}
		ins_query = ins_query.substring(0, ins_query.length() - 2);       
        statement.executeUpdate(ins_query);
		
	}
	public void update(String table, Map<String, Object> data) throws SQLException{
		//aggiorno una pagina già presente nel database
		
		Statement statement = this.database.createStatement();
		String up_query = "UPDATE "+table+" SET ";
		for (Map.Entry<String, Object> entry: data.entrySet()){
			
			up_query = up_query + entry.getKey().replaceAll("'", "''") + " = '" +entry.getValue().toString().replaceAll("'", "''")+ "', ";
		}
	   up_query = up_query.substring(0, up_query.length() - 2);       
       statement.executeUpdate(up_query);
			
	}
	
	public boolean controlAlreadyPageExists(String attr_table, String cond) throws SQLException{
		//controllo se esiste già una pagina parsata da inserire sul database
		//se il risultato della query non restituisce risultati, il metodo restituisce false, true altrimenti
		boolean control = false;
		Statement statement = this.database.createStatement();
		String query = "SELECT " + attr_table+" WHERE "+cond+";";
		ResultSet rs = statement.executeQuery(query);
		if(rs == null) control = true;
		return control;
	}
	
	public Set<Map<String,Object>> read(String table,String cond)throws SQLException{
		
      ResultSet DBdata;
					
			Statement select = this.database.createStatement();
			DBdata =  select.executeQuery("SELECT * FROM "+table+" WHERE "+cond);
	        		
		return	super.processingResults(DBdata);
			}
	
	}
