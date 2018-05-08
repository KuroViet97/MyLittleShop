package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.product.service.MylittleShopIOExceptionException;
import com.product.service.MylittleShopParserConfigurationExceptionException;
import com.product.service.MylittleShopSAXExceptionException;
import com.servlet.ClientGUI;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
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
		frame.getContentPane().setBackground(Color.PINK);
		
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
		
		String[] combo2= {"Shop 1","Shop 2","Shop 3"};
		
		final JComboBox comboBox_1 = new JComboBox(combo2);
		comboBox_1.setBounds(192, 20, 89, 26);
		frame.getContentPane().add(comboBox_1);
		comboBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //
                // Get the source of the component, which is our combo
                // box.
                //
                JComboBox comboBox = (JComboBox) event.getSource();

                Object selected = comboBox.getSelectedItem();
                if(selected.toString().equals("Shop 1")) {
                	Client_main.shop=1;
                }
                else if(selected.toString().equals("Shop 2")) {
                	Client_main.shop=2;
                }
                else {
                	Client_main.shop=3;
                }
            }
        });
		
		String[] combo= {"Client","Manager"};
		
		comboBox = new JComboBox(combo);
		comboBox.setBounds(41, 22, 89, 22);
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //
                // Get the source of the component, which is our combo
                // box.
                //
                JComboBox comboBox = (JComboBox) event.getSource();

                Object selected = comboBox.getSelectedItem();
                if(selected.toString().equals("Manager")) {
                	comboBox_1.setVisible(false);
                }
                else {
                	comboBox_1.setVisible(true);
                }
            }
        });
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usrn=textField.getText();
			    String pwd=passwordField.getText();
			    String choice=comboBox.getSelectedItem().toString();
			    if(choice.equals("Manager")) {
			    	try {
			    		if(usrn.equals("manager")) {
			    			if(ClientGUI.validInfo(usrn, pwd)) {
			    				frame.dispose();
			    				Manager_main.main(null);
			    			}
			    			else {
				    			JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.WARNING_MESSAGE);
			    			}
			    		}
			    		else {
			    			JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.WARNING_MESSAGE);
			    		}
					} catch (RemoteException | MylittleShopParserConfigurationExceptionException
							| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			    else if(choice.equals("Client")) {
			    	try {
			    		if(usrn.equals("employee")) {
			    			if(ClientGUI.validInfo(usrn, pwd)) {
			    				frame.dispose();
			    				Client_main.main(null);
			    			}
				    		else {
				    			JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.WARNING_MESSAGE);
				    		}
			    		}
			    		else {
			    			JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.WARNING_MESSAGE);
			    		}
					} catch (RemoteException | MylittleShopParserConfigurationExceptionException
							| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			}
		});
		btnLogin.setBounds(62, 190, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(279, 190, 89, 23);
		frame.getContentPane().add(btnCancel);
	}
}
