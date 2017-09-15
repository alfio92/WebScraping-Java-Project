package main;
import exceptions.PageNotFoundException;
import exceptions.ResourceNotFoundException;
import controller.Store;
import exceptions.InputNotValidException;
import persistence.MySQLDB;
import view.SwtGui;
import view.UI;;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws PageNotFoundException, ResourceNotFoundException, InputNotValidException
    {
    	
    		
        /*All'avvio dell'applicazione è necessario che questa, per prima cosa si colleghi al database,
    	quindi si crea l'istanza del db */
    	
    	Store Database= new MySQLDB("127.0.0.1", "webscrapingdatabase" ,3306, "root", "");
	
    	UI userInterface = new SwtGui(/*Database*/);
    	userInterface.runInterface(Database);
    }
}
