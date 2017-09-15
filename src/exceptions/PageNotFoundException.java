package exceptions;

public class PageNotFoundException extends Exception{ 
	// utente fornisce un url non trovato sul DB
	
	private static final long serialVersionUID = -311087985291245628L; 
    
    public PageNotFoundException(){
 	   super();
    }
    
    public PageNotFoundException(String msg){
 	   super(msg);
    }
                   
    public PageNotFoundException(Throwable thr){
 	   super(thr);
    }
    
    public PageNotFoundException(String msg, Throwable thr){
 	   super(msg, thr);
    }
}
