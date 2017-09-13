package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import java.awt.Dialog;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.TableCursor;
import view.eclipse.wb.swt.SWTResourceManager;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import controller.Controller;
import controller.PageDataAggregator;
import controller.Store;
import controller.WebConnector;
import controller.HtmlScraping;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import utilities.ValidatorUrl;
import exceptions.InputNotValidException;
import exceptions.PageNotFoundOnDatabaseException;

import model.Page;


import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.browser.Browser;

public class SwtGui extends Gui{
	
	/*
	 * Limito l'utilizzo della view esclusivamente alla visualizzazione della finestra di utilizzo
	 * da parte dell'utente e alla visualizzazione dei dati associati all'input dell'utente
	 */
	

	protected Shell shell;
	private Text text;
	private Store Database;
	private Page model;  //il model è statico in quanto bisogna mantenere in memoria i dati
    private Controller controller;
	
	/**
	 * Launch the application.
	 * @param args
	 */

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public void runInterface(Store database){
		this.Database=database;
		super.run();
	/*	
		try {
			SwtGui window = super.run();;
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	
	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(716, 745);
		shell.setText("WebScraping App");
		shell.setLayout(new FormLayout());
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(0, 40);
		fd_lblNewLabel.right = new FormAttachment(0, 365);
		fd_lblNewLabel.left = new FormAttachment(0, 58);
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Insert Resource or Url Page");
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		FormData fd_text = new FormData();
		fd_text.bottom = new FormAttachment(lblNewLabel, 54, SWT.BOTTOM);
		fd_text.top = new FormAttachment(lblNewLabel, 15);
		fd_text.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_text.right = new FormAttachment(100, -143);
		text.setLayoutData(fd_text);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_scrolledComposite.right = new FormAttachment(100, -33);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Button ConnectButton = new Button(shell, SWT.NONE);
		fd_scrolledComposite.top = new FormAttachment(ConnectButton, 21);
		
		/*
		 * aziona la logica di business relativa alla connessione di una risorsa ed allo scrap
		 * della pagina che l'utente fornisce in input
		 */ 
		
		ConnectButton.addSelectionListener(new SelectionAdapter() {
			
			  //  private Controller controller;
			    
			    private ValidatorUrl validator; 
			    
			@Override
			public void widgetSelected(SelectionEvent e){
				
				/*
				 * azionare la logica di business relativa alla connessione di una risorsa ed allo scrap
				 * della pagina
				 * 
				 * ValidatorUrl urlVal;
				 * 
				 * if(!(urlVal.validate(text.getText())))
				 *                throw  new InputNotValidException()
				 *    else{
				 *         WebConnectorInterface intf = ;
		                    this.user = new UserControl(new WebScraping(new PageDataAggregator(Database, new Page())));
			                user.scrapPage("url");
			                }
				 */
				
                validator = new ValidatorUrl();
				String url = validator.fixUrl(text.getText());  
				
				//OK
				
				   if(!validator.validate(url)){
					    try {
							throw new InputNotValidException();
						} catch (InputNotValidException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				   }
				   				   
				   controller = new Controller(new HtmlScraping(url));
				   
				   try{
				   model = controller.showDataFromScraping();  //ritorno i dati da mostrare all'utente
				   
				   }catch(NotFound e1){
					   e1.printStackTrace();
					   
				   }
				   //print(this.model);
		
		       //    this.user = new UserControl(new WebScraping(url, model), new PageDataAggregator(Database, model));
			       
			/*		try {
						controller.showDataFromScraping(url);
					} catch (NotFound | ResponseException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  */
				
				System.out.println("ciao");
				   
			}
			
		});
		
		FormData fd_ConnectButton = new FormData();
		fd_ConnectButton.bottom = new FormAttachment(text, 75, SWT.BOTTOM);
		fd_ConnectButton.right = new FormAttachment(0, 195);
		fd_ConnectButton.top = new FormAttachment(text, 20);
		fd_ConnectButton.left = new FormAttachment(0, 58);
		ConnectButton.setLayoutData(fd_ConnectButton);
		ConnectButton.setText("Scrap Page");
		
		Button SearchDataPageButton = new Button(shell, SWT.NONE);
		
		
		SearchDataPageButton.addSelectionListener(new SelectionAdapter() {
			
		//	private Controller controller;
			private ValidatorUrl urlVal;
			
			@Override
			public void widgetSelected(SelectionEvent e){
				
				//azionare la logica di business relativa alla ricerca dei contenuti di una pagina su DB
				
				urlVal = new ValidatorUrl();
				
				String url=text.getText();
				String res="";
				
				
					try {
						if(!(urlVal.validate(text.getText())))
							
						throw  new InputNotValidException();
						
					} catch (InputNotValidException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					res = urlVal.getHost(url);
				                
				
				controller=new Controller(new PageDataAggregator(Database));
				try{
				controller.showDataPageFromDB(url);
				}catch(SQLException e2) { e2.printStackTrace();}
		
			}
		});
		FormData fd_SearchDataPageButton = new FormData();
		fd_SearchDataPageButton.top = new FormAttachment(text, 20);
		fd_SearchDataPageButton.bottom = new FormAttachment(scrolledComposite, -21);
		fd_SearchDataPageButton.left = new FormAttachment(ConnectButton, 49);
		SearchDataPageButton.setLayoutData(fd_SearchDataPageButton);
		SearchDataPageButton.setText("Search Data Page");
		
		Button saveDataButton = new Button(shell, SWT.NONE);
		fd_scrolledComposite.bottom = new FormAttachment(saveDataButton, -22);
		
		List list = new List(scrolledComposite, SWT.BORDER);
		list.setItems(new String[] {"model"});
		scrolledComposite.setContent(list);
		scrolledComposite.setMinSize(list.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		
		saveDataButton.addSelectionListener(new SelectionAdapter() {
			
		//	private Controller controller;
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//delegare il database a salvare i dati
				// Controller controller = new Controller(new PageDataAggregator(this.Database));
				//controller.saveData();
				//dialog.open("Data are saved");
				
				controller = new Controller(new PageDataAggregator(Database));
				try{
					
					// vado a salvare i dati precedentemente parsati e mantenuti in memoria
				controller.saveData(model); 
								
				}
				catch(SQLException | IOException e3){ e3.printStackTrace();}
				
				System.out.println("Data has been saved");
			}
		});
		FormData fd_saveDataButton = new FormData();
		fd_saveDataButton.right = new FormAttachment(100, -503);
		fd_saveDataButton.left = new FormAttachment(0, 58);
		fd_saveDataButton.top = new FormAttachment(100, -58);
		fd_saveDataButton.bottom = new FormAttachment(100, -10);
		saveDataButton.setLayoutData(fd_saveDataButton);
		saveDataButton.setText("Save Data");
		
		Button SearchPagesButton = new Button(shell, SWT.NONE);
		fd_SearchDataPageButton.right = new FormAttachment(SearchPagesButton, -48);
		
		
        SearchPagesButton.addSelectionListener(new SelectionAdapter() {
			
        //	private Controller controller;
        	private ValidatorUrl val;
			@Override
			public void widgetSelected(SelectionEvent e){
				
				//azionare la logica di business relativa alla ricerca su DB di pagine web per nome di una risorsa
				/*
				 * this.user = new UserControl(new PageDataAggregator(Database,new Page()));  
			      printToTable(user.viewPage(urlToView));
				*/
				String res = text.getText();
				res = val.getHost(res);
				
				controller = new Controller(new PageDataAggregator(Database));
				try{
				controller.showUrlPagesByResourceNameFromDB(res);
				}
				catch(SQLException e2){
					e2.printStackTrace();
				}
			}
		});
		FormData fd_SearchPagesButton = new FormData();
		fd_SearchPagesButton.bottom = new FormAttachment(text, 75, SWT.BOTTOM);
		fd_SearchPagesButton.right = new FormAttachment(0, 612);
		fd_SearchPagesButton.top = new FormAttachment(text, 20);
		fd_SearchPagesButton.left = new FormAttachment(0, 452);
		SearchPagesButton.setLayoutData(fd_SearchPagesButton);
		SearchPagesButton.setText("Search Pages");

	}
	
  public void printToTable(/* Model data, Table table*/){
		
		/*
		 * //ciclo su ogni dato preso dal database per stamparlo [attributo] : [valore]
		 * TableItem tableItem = new TableItem(table, SWT.NONE);
		   tableItem.setText("New TableItem");
		for (Map<String, String> dataToPrint : data){
			for(Map.Entry<String, String> entry : dataToPrint.entrySet()){
               
				System.out.println(entry.getKey()+":"+ entry.getValue());
			}
		 * 
		*/
  }
}
