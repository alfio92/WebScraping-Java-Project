package exceptions;

public class ResourceNotFoundOnDatabaseException extends Exception {
//utente fornisce una URL di una risorsa non trovata sul DB
	
    private static final long serialVersionUID = -311087985291245627L; 
    
    public ResourceNotFoundOnDatabaseException(){
 	   super();
    }
    
    public ResourceNotFoundOnDatabaseException(String msg){
 	   super(msg);
    }
                   
    public ResourceNotFoundOnDatabaseException(Throwable thr){
 	   super(thr);
    }
    
    public ResourceNotFoundOnDatabaseException(String msg, Throwable thr){
 	   super(msg, thr);
    }
}
