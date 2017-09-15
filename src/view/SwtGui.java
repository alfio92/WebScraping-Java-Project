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

import java.sql.SQLException;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaunt.NotFound;
import com.jaunt.ResponseException;

import controller.PageDataAggregator;
import controller.Store;
import controller.UserControl;
import controller.WebConnector;
import controller.WebScraping;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import utilities.ValidatorUrl;
import exceptions.InputNotValidException;
import exceptions.PageNotFoundException;
import model.Model;
import model.Page;

public class SwtGui implements UI{

	protected Shell shell;
	private Text text;
	private Table table;
	private ValidatorUrl validator;
	private Store Database;
    
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwtGui window = new SwtGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
		
		try {
			SwtGui window = new SwtGui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create contents of the window.
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
		fd_scrolledComposite.right = new FormAttachment(100, -10);
		fd_scrolledComposite.left = new FormAttachment(0, 81);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnTagname = new TableColumn(table, SWT.NONE);
		tblclmnTagname.setWidth(134);
		tblclmnTagname.setText("Type");
		
		TableColumn tblclmnTagname_1 = new TableColumn(table, SWT.NONE);
		tblclmnTagname_1.setWidth(100);
		tblclmnTagname_1.setText("TagName");
		
		TableColumn tblclmnTagsData = new TableColumn(table, SWT.NONE);
		tblclmnTagsData.setWidth(300);
		tblclmnTagsData.setText("Tag's Data");
		
		
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Button ConnectButton = new Button(shell, SWT.NONE);
		fd_scrolledComposite.top = new FormAttachment(ConnectButton, 20);
		
		/*
		 * aziona la logica di business relativa alla connessione di una risorsa ed allo scrap
		 * della pagina che l'utente fornisce in input
		 */ 
		
		ConnectButton.addSelectionListener(new SelectionAdapter() {
			
			    private UserControl user;
			    private Page model;
			@Override
			public void widgetSelected(SelectionEvent e) {
				
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

				String url = text.getText();   
				   if(!validator.validate(url)){
					    try {
							throw new InputNotValidException();
						} catch (InputNotValidException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				   }
				   this.model = new Page(url, validator.getHost(url));
		           this.user = new UserControl(new WebScraping(url, model), new PageDataAggregator(Database, model));
			       
					try {
						user.viewScrapPageData();
					} catch (NotFound | ResponseException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				System.out.println("Dio");
				   
			}
			
		});
		FormData fd_ConnectButton = new FormData();
		fd_ConnectButton.bottom = new FormAttachment(text, 75, SWT.BOTTOM);
		fd_ConnectButton.right = new FormAttachment(0, 195);
		fd_ConnectButton.top = new FormAttachment(text, 20);
		fd_ConnectButton.left = new FormAttachment(0, 58);
		ConnectButton.setLayoutData(fd_ConnectButton);
		ConnectButton.setText("Connect");
		
		Button SearchDataPageButton = new Button(shell, SWT.NONE);
		
		
		SearchDataPageButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e){
				//azionare la logica di business relativa alla ricerca dei contenuti di una pagina su DB
				/*
				 * this.user = new UserControl(new PageDataAggregator(Database,new Page()));  
			      printToTable(user.viewPage(urlToView));
				*/
				System.out.println("Cristo");
			}
		});
		FormData fd_SearchDataPageButton = new FormData();
		fd_SearchDataPageButton.left = new FormAttachment(ConnectButton, 49);
		fd_SearchDataPageButton.top = new FormAttachment(text, 20);
		fd_SearchDataPageButton.bottom = new FormAttachment(scrolledComposite, -20);
		SearchDataPageButton.setLayoutData(fd_SearchDataPageButton);
		SearchDataPageButton.setText("Search Data Page");
		
		Button saveDataButton = new Button(shell, SWT.NONE);
		fd_scrolledComposite.bottom = new FormAttachment(saveDataButton, -23);
		
		
		saveDataButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//delegare il database a salvare i dati
				System.out.println(text.getText());
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
			
			@Override
			public void widgetSelected(SelectionEvent e){
				
				//azionare la logica di business relativa alla ricerca su DB di pagine web per nome di una risorsa
				/*
				 * this.user = new UserControl(new PageDataAggregator(Database,new Page()));  
			      printToTable(user.viewPage(urlToView));
				*/
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
