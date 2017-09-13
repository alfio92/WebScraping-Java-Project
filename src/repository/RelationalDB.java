package repository;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.Store;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public abstract class RelationalDB implements Store{
	
	//processare dati ritornati dal DB ("ResultSet")

	public Set<Map<String, String>> processingResults(ResultSet DBdata) throws SQLException{
		
		//lettura di tutti i dati ricavati dalla query
		
		Set<Map<String, String>> data = new LinkedHashSet<Map<String, String>>();
		
		// Recupero nomi colonne (in array)
		ResultSetMetaData metadata = (ResultSetMetaData)DBdata.getMetaData();   // prendo metadati del db
		int numberColumn= metadata.getColumnCount();   //numero colonne presenti
        String columnNames[] = new String[numberColumn]; //array in cui vi sono tutti i nomi degli attributi
        
		for(int i = 1; i<=numberColumn; i++){
			//ottengo i nomi degli attributi
			   columnNames[i-1] = metadata.getColumnName(i); 
		            		        
	     }			
			//aggiungo il risultato della query ad una map una riga alla volta
		       while (DBdata.next()){
			       Map<String, String> row = new LinkedHashMap<String, String>();
				
			       for(int i=1; i<=numberColumn; i++){
			    	     	    	
			    	    	 row.put(columnNames[i-1], DBdata.getString(i));
				    	    	 
			       }
			    data.add(row);	  
			}
		      
			return data;	
	}
}
