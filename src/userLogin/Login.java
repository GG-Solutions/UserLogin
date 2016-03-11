package userLogin;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Login {

	protected Shell shell;
	private Text txtUsername;
	private Text txtPassword;
	private Button btnNewUser;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		txtUsername = new Text(shell, SWT.BORDER);
		txtUsername.setText("UserName");
		txtUsername.setToolTipText("User Name");
		txtUsername.setBounds(102, 115, 97, 21);
		
		txtPassword = new Text(shell, SWT.BORDER);
		txtPassword.setText("Password");
		txtPassword.setBounds(205, 115, 97, 21);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
				try {
					LoginInfo.uLogin(txtUsername.getText(),txtPassword.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(112, 142, 75, 25);
		btnNewButton.setText("Login");
		
		btnNewUser = new Button(shell, SWT.NONE);
		btnNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		btnNewUser.setBounds(112, 181, 75, 25);
		btnNewUser.setText("New User");

	}
	
}
