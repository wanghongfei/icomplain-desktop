package cn.neo.icomplain;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class LoadingWindow extends Shell {
	private Display display;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LoadingWindow shell = new LoadingWindow(display);
			shell.open();
			shell.layout();
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
		Rectangle rect = getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		setLocation(x, y);
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public LoadingWindow(Display display) {
		super(display, SWT.BORDER | SWT.TITLE | SWT.PRIMARY_MODAL);
		this.display = display;

		Label lblLoading = new Label(this, SWT.NONE);
		lblLoading.setBounds(57, 10, 71, 21);
		lblLoading.setText("Loading ...");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Please wait");
		setSize(181, 82);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
