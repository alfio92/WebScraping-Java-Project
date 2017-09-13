package exceptions;

public class InputNotValidException extends Exception{  
	//l'utente fornisce in input un url non valido
	
           private static final long serialVersionUID = -3110879852912449162L; 
           
           public InputNotValidException(){
        	   super();
           }
           
           public InputNotValidException(String msg){
        	   super(msg);
           }
                          
           public InputNotValidException(Throwable thr){
        	   super(thr);
           }
           
           public InputNotValidException(String msg, Throwable thr){
        	   super(msg, thr);
           }
                 
}
