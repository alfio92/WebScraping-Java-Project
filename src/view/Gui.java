package view;

public abstract class Gui implements UI {

	public void run(){
		
		try {
			SwtGui window = new SwtGui();;
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}
