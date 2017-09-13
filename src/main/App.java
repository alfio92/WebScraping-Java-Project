package main;
import exceptions.PageNotFoundOnDatabaseException;
import exceptions.ResourceNotFoundOnDatabaseException;
import repository.MySQLDB;
import controller.Store;
import exceptions.InputNotValidException;
import view.SwtGui;
import view.UI;;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws PageNotFoundOnDatabaseException, ResourceNotFoundOnDatabaseException, InputNotValidException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
    	
    		
        /*All'avvio dell'applicazione è necessario che questa, per prima cosa si colleghi al database,
    	quindi si crea l'istanza del db */
    	
    	Store Database = new MySQLDB("127.0.0.1", "webconn" ,3306, "root", "");
      	Database.connect();
    	
    	UI userInterface = new SwtGui();
    	userInterface.runInterface(Database);
    	
    }
}
