package exceptions;

public class PageNotFoundOnDatabaseException extends Exception{ 
	// utente fornisce un url non trovato sul DB
	
	private static final long serialVersionUID = -311087985291245628L; 
    
    public PageNotFoundOnDatabaseException(){
 	   super();
    }
    
    public PageNotFoundOnDatabaseException(String msg){
 	   super(msg);
    }
                   
    public PageNotFoundOnDatabaseException(Throwable thr){
 	   super(thr);
    }
    
    public PageNotFoundOnDatabaseException(String msg, Throwable thr){
 	   super(msg, thr);
    }
}
