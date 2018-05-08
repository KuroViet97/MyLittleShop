package GUI;
import Barcode.*;

import com.product.service.MylittleShopIOExceptionException;
import com.product.service.MylittleShopParserConfigurationExceptionException;
import com.product.service.MylittleShopSAXExceptionException;
import com.servlet.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.axis2.AxisFault;

import Camera.Barcode;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class Manager_main {
	static int row=0;
	int shop=1;

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_main window = new Manager_main();
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
	private void numbermessage() {
		String a="";
	      
	      JTextField aF=new JTextField(20);
	      
	      Object[] mes= {
	        "Input: ",aF,
	      };
	      int opt=JOptionPane.showConfirmDialog(null,mes,"Input a number",JOptionPane.OK_CANCEL_OPTION);
	      if(opt==JOptionPane.OK_OPTION) {
	    	  if(aF.getText().length()>12) {
	    		  JOptionPane.showMessageDialog(aF, "Input must have less than 12 numbers", "Error", opt);
	    		  numbermessage();
	    	  }
	    	  else {
	    		  String name="Barcode"+aF.getText()+".jpg";
	    		  BarcodeReader.Generator(aF.getText(), name);
	    	  }
	      }
		
	}
	private void cleartable(DefaultTableModel model) {
		int rowCount = model.getRowCount();
	    //Remove rows one by one from the end of the table
	    for (int i = rowCount - 1; i >= 0; i--) {
		        model.removeRow(i);
	    }
	    row=0;
	}
	
	public Manager_main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setSize(screenSize.width, screenSize.height);
	    frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		//frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 30, 30, 0, 30};
		gridBagLayout.rowHeights = new int[] {30, 30, 50, 30, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.getContentPane().setBackground(Color.PINK);
		
		JLabel lblMyLittleShops = new JLabel("My Little Shop(s)");
		GridBagConstraints gbc_lblMyLittleShops = new GridBagConstraints();
		gbc_lblMyLittleShops.insets = new Insets(0, 0, 5, 5);
		gbc_lblMyLittleShops.gridx = 2;
		gbc_lblMyLittleShops.gridy = 0;
		frame.getContentPane().add(lblMyLittleShops, gbc_lblMyLittleShops);
		
		String[] combo = { "Shop1", "Shop2", "Shop3"};
		
		final JComboBox comboBox = new JComboBox(combo);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		table = new JTable(new DefaultTableModel(new Object[] {"Data"},0));
		final DefaultTableModel model =(DefaultTableModel)table.getModel();
		scrollPane_1.setViewportView(table);
		
		//initialize table
		shop=Client_main.shop;
		System.out.println(shop);
		if(shop==1) {
		    try {
				String db=ClientGUI.viewDatabase1();
				String[] dbsplit=db.split("\n");
				model.addRow(new Object[] {dbsplit[0]});
				model.addRow(new Object[] {dbsplit[1]});
				model.addRow(new Object[] {dbsplit[2]});
			} catch (RemoteException | MylittleShopParserConfigurationExceptionException
					| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(shop==2) {
		    try {
				String db=ClientGUI.viewDatabase2();
				String[] dbsplit=db.split("\n");
				model.addRow(new Object[] {dbsplit[0]});
				model.addRow(new Object[] {dbsplit[1]});
				model.addRow(new Object[] {dbsplit[2]});
			} catch (RemoteException | MylittleShopParserConfigurationExceptionException
					| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(shop==3) {
		    try {
				String db=ClientGUI.viewDatabase3();
				String[] dbsplit=db.split("\n");
				model.addRow(new Object[] {dbsplit[0]});
				model.addRow(new Object[] {dbsplit[1]});
				model.addRow(new Object[] {dbsplit[2]});
			} catch (RemoteException | MylittleShopParserConfigurationExceptionException
					| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//cccccccccccombo action
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //
                // Get the source of the component, which is our combo
                // box.
                //
                JComboBox comboBox = (JComboBox) event.getSource();

                Object selected = comboBox.getSelectedItem();
                if(selected.toString().equals("Shop1")) {
            		cleartable(model);
        		    try {
        				String db=ClientGUI.viewDatabase1();
        				String[] dbsplit=db.split("\n");
        				model.addRow(new Object[] {dbsplit[0]});
        				model.addRow(new Object[] {dbsplit[1]});
        				model.addRow(new Object[] {dbsplit[2]});
        			} catch (RemoteException | MylittleShopParserConfigurationExceptionException
        					| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
                }
                else if(selected.toString().equals("Shop2")) {
                	cleartable(model);
        		    try {
        				String db=ClientGUI.viewDatabase2();
        				String[] dbsplit=db.split("\n");
        				model.addRow(new Object[] {dbsplit[0]});
        				model.addRow(new Object[] {dbsplit[1]});
        				model.addRow(new Object[] {dbsplit[2]});
        			} catch (RemoteException | MylittleShopParserConfigurationExceptionException
        					| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
                }
                else if(selected.toString().equals("Shop3")) {
                	cleartable(model);
        		    try {
        				String db=ClientGUI.viewDatabase3();
        				String[] dbsplit=db.split("\n");
        				model.addRow(new Object[] {dbsplit[0]});
        				model.addRow(new Object[] {dbsplit[1]});
        				model.addRow(new Object[] {dbsplit[2]});
        			} catch (RemoteException | MylittleShopParserConfigurationExceptionException
        					| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
                }
            }
        });
		
		JButton btnNewButton = new JButton("See All");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String in=comboBox.getSelectedItem().toString();
				int rowCount = model.getRowCount();
			    //Remove rows one by one from the end of the table
			    for (int i = rowCount - 1; i >= 0; i--) {
			        model.removeRow(i);
			    }
			    row=0;
			    model.addRow(new Object[] {"SHOP 1"});
			    try {
					String db=ClientGUI.viewDatabase1();
					String[] dbsplit=db.split("\n");
					model.addRow(new Object[] {dbsplit[0]});
					model.addRow(new Object[] {dbsplit[1]});
					model.addRow(new Object[] {dbsplit[2]});
				} catch (RemoteException | MylittleShopParserConfigurationExceptionException
						| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    model.addRow(new Object[] {"SHOP 2"});
			    try {
					String db=ClientGUI.viewDatabase2();
					String[] dbsplit=db.split("\n");
					model.addRow(new Object[] {dbsplit[0]});
					model.addRow(new Object[] {dbsplit[1]});
					model.addRow(new Object[] {dbsplit[2]});
				} catch (RemoteException | MylittleShopParserConfigurationExceptionException
						| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    model.addRow(new Object[] {"SHOP 3"});
			    try {
					String db=ClientGUI.viewDatabase3();
					String[] dbsplit=db.split("\n");
					model.addRow(new Object[] {dbsplit[0]});
					model.addRow(new Object[] {dbsplit[1]});
					model.addRow(new Object[] {dbsplit[2]});
				} catch (RemoteException | MylittleShopParserConfigurationExceptionException
						| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JButton btnRemove = new JButton("Logout");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Barcode Generator");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numbermessage();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Restock");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int SelRow = table.getSelectedRow();
				model.removeRow(SelRow);
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 2;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 3;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemove.gridx = 3;
		gbc_btnRemove.gridy = 3;
		frame.getContentPane().add(btnRemove, gbc_btnRemove);
	}
}
