package GUI;
import Camera.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.product.service.MylittleShopIOExceptionException;
import com.product.service.MylittleShopParserConfigurationExceptionException;
import com.product.service.MylittleShopSAXExceptionException;
import com.servlet.ClientGUI;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Client_main {
	static int row=0;
	static String cc=null;

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	
	//set shop number
	public static int shop=1;

	/**
	 * Launch the application.
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client_main window = new Client_main();
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
	public Client_main() {
		initialize();
	}
	
	private int quantity() {
	      
	      JTextField aF=new JTextField(20);
	      
	      Object[] mes= {
	        "Input: ",aF,
	      };
	      int opt=JOptionPane.showConfirmDialog(null,mes,"Input item quantity",JOptionPane.OK_CANCEL_OPTION);
	      if(opt==JOptionPane.OK_OPTION) {
	    	  if(Integer.parseInt(aF.getText())<0) {
	    		  JOptionPane.showMessageDialog(null, "Quantity can't be smaller than 0", "Error", JOptionPane.ERROR_MESSAGE);
	    		  return 0;
	    	  }
	      }
	      if(opt==JOptionPane.CANCEL_OPTION) {
	    	  return 0;
	      }
	      else {
	    	  return Integer.parseInt(aF.getText());
	      }
	}
	
	private boolean checkname(String a,DefaultTableModel model,JTable table) {
		int rowCount=model.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			String az=(String)table.getValueAt(i, 1);
			if(a.equals(az)) {
				return false;
			}
		}
		return true;
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
		gridBagLayout.columnWidths = new int[]{30, 0, 250, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[] {31, 30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		frame.getContentPane().setLayout(gridBagLayout);
		frame.getContentPane().setBackground(Color.PINK);
		
		System.out.println(shop);
		
		lblNewLabel = new JLabel("My Little Shop");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Shop "+shop);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		table = new JTable(new DefaultTableModel(new Object[] {"No.","Name","Barcode No.","Quantity","Price"},0));
		final DefaultTableModel model =(DefaultTableModel)table.getModel();
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Scan");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
		        new Thread(() -> {
		        	BarcodeScanner gs = new BarcodeScanner();
		    		//GrabberShow gs = new GrabberShow();
		            ExecutorService executorService = Executors.newSingleThreadExecutor();
		            Future<String> future = executorService.submit(gs);
		            String cc;
					try {
						//Add data to table
						cc = future.get();
						try {
							String name=ClientGUI.getData(cc);
							String price=ClientGUI.getPrice(cc);
								int z=quantity();
								if(z!=0) {
						    	model.addRow(new Object[] {row,name,cc,z,price});
					            row=row+1;
								}
				            Thread.currentThread().stop();
						} catch (RemoteException | MylittleShopParserConfigurationExceptionException
								| MylittleShopIOExceptionException | MylittleShopSAXExceptionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (InterruptedException | ExecutionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}).start();
			    	  //essential
			    	  /*File file=new File("Barcode1.jpg");
			    	  int integer=Integer.parseInt(BarcodeReader.Scanner(file))-1;*/
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 1;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int rowCount = model.getRowCount();
			    //Remove rows one by one from the end of the table
			    for (int i = rowCount - 1; i >= 0; i--) {
			    	String valuebarcode=(String)table.getValueAt(i, 2);
			    	int valuequantity=(int)table.getValueAt(i, 3);
			    	try {
			    		if(ClientGUI.comparetransaction(shop, valuequantity, valuebarcode)) {
						ClientGUI.createnewtransaction(shop, valuebarcode, valuequantity);
				    	model.removeRow(i);
			    		}
			    		else {
			    			JOptionPane.showMessageDialog(null, "The output amount is larger than the quantity in the database!", "Error", JOptionPane.INFORMATION_MESSAGE);
			    			model.removeRow(i);
			    		}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			    row=0;
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 2;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.main(null);
				frame.dispose();
				row=0;
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 3;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
	}
}