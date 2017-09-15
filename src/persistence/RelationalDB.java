package persistence;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import controller.Store;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public abstract class RelationalDB implements Store{
	
	//processare dati ritornati dal DB ("ResultSet")

	public Set<Map<String,Object>> processingResults(ResultSet DBdata) throws SQLException{
		
		//lettura di tutti i dati ricavati dalla query
	    //per stampare piu pagine List<Map<String,Set<String>>>
	
		
			Set<Map<String, Object>> rowsData = new LinkedHashSet<Map<String, Object>>();
		
		// Recupero nomi colonne (in array)
		ResultSetMetaData metadata = (ResultSetMetaData)DBdata.getMetaData();   // prendo metadati del db
		int numberColumn= metadata.getColumnCount();   //numero colonne presenti
        String columnNames[] = new String[numberColumn]; //array in cui vi sono tutti i nomi degli attributi
        
		for(int i = 1; i<=numberColumn; i++){
			//ottengo i nomi degli attributi
			   columnNames[i-1] = metadata.getColumnName(i); 
		            		        
	     }
			
			for(int i=1; i<=numberColumn; i++){
				columnNames[i-1] = metadata.getColumnLabel(i);
			}
			//aggiungo il risultato della query ad una map una riga alla volta
		       while (DBdata.next()){
				Map<String, Object> row = new LinkedHashMap<String, Object>();
				
				for (int i=1; i<=numberColumn; i++){
					if(DBdata.getString(i) != null)   //se il campo preso è null non stamparlo
					   row.put(columnNames[i-1], DBdata.getString(i));
				}
				
				rowsData.add(row);
			}
		      
		//	return data;
		return rowsData; // 1 sola pagina da DB, per ottenere più pagine da qui, forse si deve cambiare "cond" in "PageManager", aggiungendo "GROUP BY" 
		
	}

}
