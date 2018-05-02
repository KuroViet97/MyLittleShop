package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Login");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(154, 102, 232, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(154, 135, 232, 22);
		frame.getContentPane().add(passwordField);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(41, 106, 70, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(41, 139, 70, 14);
		frame.getContentPane().add(lblPassword);
		
		String[] combo= {"Client","Manager"};
		
		comboBox = new JComboBox(combo);
		comboBox.setBounds(41, 22, 89, 22);
		frame.getContentPane().add(comboBox);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usrn=textField.getText();
			    String pwd=passwordField.getText();
			    String choice=comboBox.getSelectedItem().toString();
			    if(usrn.equals("quan")&&pwd.equals("deptrai")&&choice.equals("Manager")) {
			     Manager_main.main(null);
			     frame.dispose();
			    }
			    else if(usrn.equals("quan")&&pwd.equals("deptrai")&&choice.equals("Client")) {
			     Client_main.main(null);
			     frame.dispose();
			    }
			    else {
			     JOptionPane.showMessageDialog(btnLogin, "Wrong username or password","Error", 0);
			     textField.setText(null);
			     passwordField.setText(null);
			    }
			}
		});
		btnLogin.setBounds(62, 190, 89, 23);
		frame.getContentPane().add(btnLogin);
	}
}
