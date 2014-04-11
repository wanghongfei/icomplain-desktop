package cn.neo.icomplain;

import java.util.Date;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import cn.neo.icomplain.entity.ComplainItem;
import cn.neo.icomplain.util.DbManager;

/**
 * Application main class/main window.
 * @author whf
 *
 */
public class App extends Shell {
	private Text title;
	private Text content;
	
	private Label prompt;
	private Spinner alValue;
	
	private App instance;
	private Display display;
	
	private boolean isPersonSelected = true;
	private boolean isAffairSelected = false;
	
	private static Logger log = Logger.getLogger(App.class);;
	

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// show splash window
		LoadingWindow loadingWindow = new LoadingWindow(Display.getDefault());
		loadingWindow.centralize();
		loadingWindow.open();
		
		log.info("Intializing application ...");
		long startTime = System.currentTimeMillis();
		
		// initialize JAP
		initJPA();
		
		try {
			// show window
			Display display = Display.getDefault();
			App shell = new App(display);
			shell.centralize();
			
			// close splash window
			loadingWindow.close();
			
			// show main window
			shell.open();
			shell.layout();
			
			long endTime = System.currentTimeMillis();
			log.info("IComplain has started in " + (endTime - startTime) + " ms");
			
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Show window on screen center
	 */
	public void centralize() {
		Monitor monitor = display.getPrimaryMonitor();
		Rectangle bounds = monitor.getBounds();
		Rectangle rect = instance.getBounds();
		
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		instance.setLocation(x, y);
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public App(Display display) {
		super(display, SWT.SHELL_TRIM);
		instance = this;
		this.display = display;
		
		setText("IComplain");
		setSize(370, 508);
		setLayout(null);

		title = new Text(this, SWT.BORDER);
		title.setBounds(101, 28, 204, 31);

		Button btnPerson = new Button(this, SWT.RADIO);
		btnPerson.setSelection(true);
		btnPerson.setBounds(101, 81, 71, 25);
		btnPerson.setText("Person");
		// user clicks this button
		btnPerson.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isPersonSelected = true;
				isAffairSelected = false;
			}
		});

		Button btnAffair = new Button(this, SWT.RADIO);
		btnAffair.setBounds(197, 81, 108, 25);
		btnAffair.setText("Affair");
		// user clicks this button
		btnAffair.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isPersonSelected = false;
				isAffairSelected = true;
			}
		});

		content = new Text(this, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL
				| SWT.MULTI);
		content.setBounds(102, 126, 203, 153);

		Button btnSubmit = new Button(this, SWT.NONE);
		btnSubmit.setBounds(99, 380, 92, 33);
		btnSubmit.setText("I complain");
		// If user clicks this button,
		// persist complaint to database.
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				log.info("Prepare to persist data");
				
				// pop up a confirm dialog
				AlertWindow alert = new AlertWindow(instance, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
				
				// If user click "Give up",
				// cancel persist operation.
				if(!alert.open()) {
					log.info("User canceled persistence");
					return;
				}
				
				//System.out.println(alValue.getData());
					
				// create entity
				ComplainItem item = new ComplainItem();

				item.setTime(new Date());
				item.setTitle(title.getText());
				item.setContent(content.getText());
				item.setAngerLevel(alValue.getSelection());

				String type = isPersonSelected ? "PERSON" : "AFFAIR";
				item.setType(type);

				DbManager.persist(item);
				
				clearText();
				prompt.setText("Submit successfully");
				log.info("Persistence finished");
			}
		});

		Button btnGiveUp = new Button(this, SWT.NONE);
		btnGiveUp.setText("Give up");
		btnGiveUp.setBounds(213, 380, 92, 33);
		btnGiveUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clearText();
			}
		});

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(31, 28, 46, 33);
		lblNewLabel.setText("Title:");

		Label lblType = new Label(this, SWT.NONE);
		lblType.setText("Type:");
		lblType.setBounds(31, 81, 46, 21);

		Label lblContent = new Label(this, SWT.NONE);
		lblContent.setText("Content:");
		lblContent.setBounds(25, 173, 71, 21);

		Label lblAngerLevel = new Label(this, SWT.NONE);
		lblAngerLevel.setText("AL:");
		lblAngerLevel.setBounds(45, 311, 32, 21);

		alValue = new Spinner(this, SWT.BORDER);
		alValue.setMaximum(5);
		alValue.setMinimum(1);
		alValue.setSelection(1);
		alValue.setBounds(176, 312, 46, 31);
		
		prompt = new Label(this, SWT.NONE);
		prompt.setBounds(132, 430, 146, 21);
		createContents();
	}
	
	/**
	 * Clear the content of the Text control.
	 */
	public void clearText() {
		title.setText("");
		content.setText("");
		
		prompt.setText("Clean successfully");
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	private static void initJPA() {
		try {
			Class.forName("cn.neo.icomplain.util.DbManager");
		} catch (ClassNotFoundException ex) {
			// never happen!
			ex.printStackTrace();
		}
	}
}
