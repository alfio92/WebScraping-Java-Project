package exceptions;

public class ResourceNotFoundException extends Exception {
//utente fornisce una URL di una risorsa non trovata sul DB
	
    private static final long serialVersionUID = -311087985291245627L; 
    
    public ResourceNotFoundException(){
 	   super();
    }
    
    public ResourceNotFoundException(String msg){
 	   super(msg);
    }
                   
    public ResourceNotFoundException(Throwable thr){
 	   super(thr);
    }
    
    public ResourceNotFoundException(String msg, Throwable thr){
 	   super(msg, thr);
    }
}
