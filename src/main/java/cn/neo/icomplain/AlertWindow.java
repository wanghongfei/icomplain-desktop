package cn.neo.icomplain;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class AlertWindow extends Dialog {

	protected boolean result = false;
	protected Shell alertWindow;
	protected App parentWindow;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public AlertWindow(Shell parent, int style) {
		super(parent, style);

		parentWindow = (App)parent;
		
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public boolean open() {
		createContents();
		alertWindow.open();
		alertWindow.layout();
		Display display = getParent().getDisplay();
		while (!alertWindow.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		alertWindow = new Shell(getParent(), getStyle());
		alertWindow.setSize(319, 113);
		alertWindow.setText("Warning");
		
		Button btnConfirm = new Button(alertWindow, SWT.NONE);
		btnConfirm.setBounds(56, 37, 92, 33);
		btnConfirm.setText("Confirm");
		btnConfirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = true;
				
				// close dialog
				alertWindow.close();
				alertWindow.dispose();
				
				//parentWindow.clearText();
			}
		});
		
		Label lblAreYouSure = new Label(alertWindow, SWT.NONE);
		lblAreYouSure.setBounds(46, 10, 228, 21);
		lblAreYouSure.setText("Are you sure to submit this post?");
		
		Button btnGiveUp = new Button(alertWindow, SWT.NONE);
		btnGiveUp.setText("Give up");
		btnGiveUp.setBounds(171, 37, 92, 33);
		btnGiveUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = false;

				// close dialog
				alertWindow.close();
				alertWindow.dispose();

				//parentWindow.clearText();
			}
		});

	}
}
