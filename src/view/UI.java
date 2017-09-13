package view;
import controller.Store;
import exceptions.InputNotValidException;
import exceptions.PageNotFoundOnDatabaseException;

public interface UI {

	public void runInterface(Store Database) throws InputNotValidException, PageNotFoundOnDatabaseException;
	
}
