package controller;
import java.io.IOException;
import java.lang.reflect.Field;
import com.google.common.reflect.*;
import model.Page;
import java.util.Map;
import java.util.Set;

import model.Model;

import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class ModelsDataName implements DataAggregator{
	
	/*
	 * Ottengo i nomi degli attributi del DB
	 */
	
	public Map<String, Set<String>> getClassName()throws IOException{
		
		//Map<TableName, Set<AttributeName>>
		
	final ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
	Map<String,Set<String>> dataType = new LinkedHashMap<String,Set<String>>();
	//	ArrayList<String> classes = new ArrayList<String>();
        
		for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
		  
		  if (info.getName().startsWith("model.")) {
		    final Class<?> clazz = info.load();
		//    classes.add(clazz.getSimpleName());
		    dataType.put(clazz.getSimpleName().toLowerCase(), new LinkedHashSet<String>(getAttributes(clazz)));
		    
		  }
		}
		return dataType;
	}
	
	public Set<String> getAttributes(Class<?> clazz){
		Set<String> attributes = new LinkedHashSet<String> ();
		
		Field[] attrbts = clazz.getDeclaredFields();
		 for(Field attr : attrbts){
		      attributes.add(attr.getName().toLowerCase());	 
			 
		 }
		
		return attributes;
	}
	
	/*
	public Set<Map<String, Object>> getAttributes(Page model) throws IOException{
		
	
 // get all model's classname
		Map<String,Map<String, Set<Object>>> datatype = new LinkedHashMap<String, Map<String, Set<Object>>>();
		
		Set<Map<String, Object>> rows = new LinkedHashSet<Map<String, Object>>();
		Class<?> modelContext= model.getClass();
		Field[] attributes = modelContext.getDeclaredFields();
		
		  for(Field attribute : attributes){
			 LinkedHashMap<String, Object> attr = new LinkedHashMap<String,Object>();
			  attr.put(attribute.getName(), null);
			  rows.add(new LinkedHashMap<String, Object>(attr));
		  }
		
		return rows;
	}
  */
}
