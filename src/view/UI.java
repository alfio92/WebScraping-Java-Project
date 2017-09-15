package view;
import controller.Store;
import exceptions.InputNotValidException;
import exceptions.PageNotFoundException;

public interface UI {

	public void runInterface(Store Database) throws InputNotValidException, PageNotFoundException;
	
}
