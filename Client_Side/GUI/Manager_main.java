package GUI;
import Barcode.*;

import com.servlet.*;

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
import javax.swing.text.DateFormatter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Manager_main {
	protected static final String JOptionPanel = null;
	static int row=0;
	int shop=1;

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void stuff() {
		//run the main frame on a new thread to be safe
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
	//Used for generating barcode
	private void numbermessage() {
	      
	      JTextField aF=new JTextField(20);
	      
	      Object[] mes= {
	        "Input: ",aF,
	      };
	      int opt=JOptionPane.showConfirmDialog(null,mes,"Input a String",JOptionPane.OK_CANCEL_OPTION);
	      if(opt==JOptionPane.OK_OPTION) {
	    	  if(aF.getText().length()>12) {
	    		  JOptionPane.showMessageDialog(aF, "Input must have less than 12 characters", "Error", opt);
	    		  numbermessage();
	    	  }
	    	  else {
	    		  String name="Barcode"+aF.getText()+".jpg";
	    		  BarcodeReader.Generator(aF.getText(), name);
	    	  }
	      }
		
	}
	//clear the current table
	private void cleartable(DefaultTableModel model) {
		int rowCount = model.getRowCount();
	    //Remove rows one by one from the end of the table
	    for (int i = rowCount - 1; i >= 0; i--) {
		        model.removeRow(i);
	    }
	    row=0;
	}
	
	//Set up the table
	private void setTable(DefaultTableModel model) {
		cleartable(model);
		try {
			String a=ClientGUI.getAllShopNames();
			String[] smola=a.split("---");
			String item=ClientGUI.getAllProductBarcodes();
			String[] smolitem=item.split("---");
			
			for(int i=0;i<smola.length;i++) {
				model.addRow(new Object[] {(i+1)+". "+smola[i]});
				for(int j=0;j<smolitem.length;j++) {
					model.addRow(new Object[] {ClientGUI.getShopItemQuantity(i+1,smolitem[j])});
				}
			}
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	//function to input dates
	private void inputDate(DefaultTableModel model) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//only accept right date format, otherwise return ""
		JFormattedTextField dateTextField = new JFormattedTextField(format);
		JFormattedTextField dateTextField2 = new JFormattedTextField(format);
		Object[] obiwan= {
				"Input by format: yyyy-MM-dd",
		        "Input minDate: ",dateTextField,
		        "Input maxDate: ",dateTextField2,
		};
		int opt3=JOptionPane.showConfirmDialog(null,obiwan,"Input the dates",JOptionPane.OK_CANCEL_OPTION);
		if(opt3==JOptionPane.OK_OPTION) {
			if(dateTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "One or both of the Dates you've entered is in incorrect format", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(dateTextField2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "One or both of the Dates you've entered is in incorrect format", "Error", JOptionPane.ERROR_MESSAGE);
				inputDate(model);
			}
			else {
				try {
					String[] a=ClientGUI.viewTransaction(dateTextField.getText(),dateTextField2.getText());
					if(a==null) {
						JOptionPane.showMessageDialog(null, "Wrong input", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						cleartable(model);
						for(int i=0;i<a.length;i++) {
							model.addRow(new Object[] {a[i]});
						}
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		}
	}
	
	
	
	public Manager_main() throws RemoteException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws RemoteException 
	 */
	private void initialize() throws RemoteException {
		frame = new JFrame();
		//set frame to fullscreen size
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		table = new JTable(new DefaultTableModel(new Object[] {"Overview"},0));
		final DefaultTableModel model =(DefaultTableModel)table.getModel();
		scrollPane_1.setViewportView(table);
		
		JLabel lblMyLittleShops = new JLabel("My Little Shop(s)");
		GridBagConstraints gbc_lblMyLittleShops = new GridBagConstraints();
		gbc_lblMyLittleShops.insets = new Insets(0, 0, 5, 5);
		gbc_lblMyLittleShops.gridx = 2;
		gbc_lblMyLittleShops.gridy = 0;
		frame.getContentPane().add(lblMyLittleShops, gbc_lblMyLittleShops);
		
		
		
		JButton btnNewButton_z = new JButton("Make a shop/product");
		btnNewButton_z.setPreferredSize(new Dimension(200,100));
		GridBagConstraints gbc_btnNewButton_z = new GridBagConstraints();
		gbc_btnNewButton_z.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_z.weightx=0;
		gbc_btnNewButton_z.weighty=0;
		gbc_btnNewButton_z.gridx = 0;
		gbc_btnNewButton_z.gridy = 1;
		frame.getContentPane().add(btnNewButton_z, gbc_btnNewButton_z);
		btnNewButton_z.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "deprecation" })
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("rawtypes")
				JComboBox combo=new JComboBox();
				combo.addItem("Shop");
				combo.addItem("Product");
				int option=JOptionPane.showConfirmDialog(null, combo, "Select", JOptionPane.OK_CANCEL_OPTION);
				if(option==JOptionPane.OK_OPTION) {
				if(combo.getSelectedItem().toString().equals("Shop")) {
					JTextField aF=new JTextField();
					JTextField bF=new JTextField();
					JPasswordField cF=new JPasswordField();
					JPasswordField dF=new JPasswordField();
					Object[] shopobij= {
					        "Input the shop's name: ",aF,
					        "Input name of the associated employee:",bF,
					        "Input password for the employee: ",cF,
					        "Confirm password: ",dF,
					};
					int choice=JOptionPane.showConfirmDialog(null, shopobij, "Input the shop's required information", JOptionPane.OK_CANCEL_OPTION);
					if(choice==JOptionPane.OK_OPTION) {
						if(aF.getText()!=null && bF.getText()!=null && cF.getText()!=null) {
							if(cF.getText().equals(dF.getText())) {
								try {
									ClientGUI.addShop(aF.getText(), bF.getText(), cF.getText());
									cleartable(model);
									setTable(model);
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "The two passwords are not the same", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"No field should be left empty","Error",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else if(combo.getSelectedItem().toString().equals("Product")) {
						JTextField aF=new JTextField();
						//Custom text field accept only number
						JTextField bF=new JTextField();
						JTextField cF=new JTextField();
						//JTextField bF=new JTextField();
						//Custom text field only accept number
						Object[] obij= {
						        "Input name: ",aF,
						        "Input Barcode: ",bF,
						        "Input price: ",cF,
						};
						JOptionPane.showConfirmDialog(null, obij, "Select", JOptionPane.OK_CANCEL_OPTION);
						if(aF.getText()==null||aF.getText().length()>15) {
							JOptionPane.showMessageDialog(null, "Invalid syntax(The input must range from 1-15 characters", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else {
							try {
								if(ClientGUI.checkProduct(bF.getText(), aF.getText())) {
									
									ClientGUI.AddProduct(bF.getText(), aF.getText(),Integer.parseInt(cF.getText()));
									cleartable(model);
									setTable(model);
								}
								else {
									JOptionPane.showMessageDialog(null, "The item you're trying to add already exist or is invalid", "Error", JOptionPane.ERROR_MESSAGE);
								}
							} catch (NumberFormatException | RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				}
			}
			}
		});
		
		
		
		//initialize table
		shop=Client_main.shop;
		System.out.println(shop);
		cleartable(model);
		setTable(model);
		
		JButton btnNewButton = new JButton("See Shops/Items/Transactions");
		btnNewButton.setPreferredSize(new Dimension(200,100));
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent arg0) {
				JComboBox combo=new JComboBox();
				combo.addItem("See filtered transactions");
				combo.addItem("See transaction between dates");
				combo.addItem("See shop/products");
				Object[] obij= {
				        "Action: ",combo,
				};
				int opt2=JOptionPane.showConfirmDialog(null,obij,"See transactions",JOptionPane.OK_CANCEL_OPTION);
				if(opt2==JOptionPane.OK_OPTION) {
					if(combo.getSelectedItem().toString().equals("See filtered transactions")) {
						JComboBox combo2=new JComboBox();
						combo2.addItem("See ALL transactions");
						combo2.addItem("See all OUT transactions");
						combo2.addItem("See all IN transactions");
						combo2.addItem("See transactions by product name");
						Object[] obij2= {
						        "Action: ",combo2,
						};
						int opt3=JOptionPane.showConfirmDialog(null,obij2,"See all transactions",JOptionPane.OK_CANCEL_OPTION);
						if(opt3==JOptionPane.OK_OPTION) {
							if(combo2.getSelectedItem().toString().equals("See ALL transactions")){
								cleartable(model);
								try {
									model.addRow(new Object[] {"<Order: ShopID---barcode---productname---price---date---status---quantity---shopID>"});
									String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
									String[] trans=ClientGUI.viewTransaction("2008-01-01", timeStamp);
									for(int i=0;i<trans.length;i++) {
										model.addRow(new Object[] {trans[i]});
									}
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else if(combo2.getSelectedItem().toString().equals("See all OUT transactions")) {
								cleartable(model);
								String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
								try {
									model.addRow(new Object[] {"<<Order: ShopID---barcode---productname---price---date---status---quantity---shopID>>"});
									String[] trans=ClientGUI.viewTransaction("2008-01-01", timeStamp);
									int sum=0;
									for(int i=0;i<trans.length;i++) {
										if(trans[i].contains("*OUT*")) {
											String[] smoltrans=trans[i].split("---");
											int price=Integer.parseInt(smoltrans[3]);
											sum=sum+price;
											model.addRow(new Object[] {trans[i]});
										}
									}
									model.addRow(new Object[] {"Total price: "+sum+"$"});
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else if(combo2.getSelectedItem().toString().equals("See all IN transactions")) {
								cleartable(model);
								String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
								try {
									model.addRow(new Object[] {"<<Order: ShopID---barcode---productname---price---date---status---quantity---shopID>>"});
									String[] trans=ClientGUI.viewTransaction("2008-01-01", timeStamp);
									int sum=0;
									for(int i=0;i<trans.length;i++) {
										if(trans[i].contains("*IN*")) {
											String[] smoltrans=trans[i].split("---");
											int price=Integer.parseInt(smoltrans[3]);
											sum=sum+price;
											model.addRow(new Object[] {trans[i]});
										}
									}
									model.addRow(new Object[] {"Total price: "+sum+"$"});
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else if(combo2.getSelectedItem().toString().equals("See transactions by product name")) {
								JComboBox comboz=new JComboBox();
								String a;
								try {
									a = ClientGUI.getAllProductNames();
								String[] smola=a.split("---");
								for(int i=0;i<smola.length;i++) {
									comboz.addItem(smola[i]);
								}
								Object[] obj3= {
								        "Action: ",comboz,
								};
								int opt4=JOptionPane.showConfirmDialog(null,obj3,"Select product name",JOptionPane.OK_CANCEL_OPTION);
								if(opt4==JOptionPane.OK_OPTION) {
									cleartable(model);
									String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
									try {
										model.addRow(new Object[] {"<<Order: ShopID---barcode---productname---price---date---status---quantity---shopID>>"});
										String[] trans=ClientGUI.viewTransaction("2008-01-01", timeStamp);
										int priceIN=0;
										for(int i=0;i<trans.length;i++) {
											String[] smoltrans=trans[i].split("---");
											if(smoltrans[2].equals(comboz.getSelectedItem().toString())) {
												if(smoltrans[5].equals("*OUT*")) {
													priceIN=Integer.parseInt(smoltrans[3])*Integer.parseInt(smoltrans[6])+priceIN;
												}
												model.addRow(new Object[] {trans[i]});
											}
										}
										model.addRow(new Object[] {"Revenue(based on OUT transactions): "+priceIN+"$"});
									} catch (RemoteException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}

								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					else if(combo.getSelectedItem().toString().equals("See transaction between dates")) {
						inputDate(model);
					}
					else if(combo.getSelectedItem().toString().equals("See shop/products")) {
						cleartable(model);
						setTable(model);
					}
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
		btnRemove.setPreferredSize(new Dimension(200,100));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Barcode Generator");
		btnNewButton_1.setPreferredSize(new Dimension(200,100));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numbermessage();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Restock");
		btnNewButton_2.setPreferredSize(new Dimension(200,100));
		btnNewButton_2.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
			public void actionPerformed(ActionEvent arg0) {
				//the javadoc new DefaultFormatterFactory(defaultFormat, displayFormat, editFormat)
				JComboBox combo = new JComboBox();
				String a;
				try {
					a = ClientGUI.getAllProductNames();
				String[] smola=a.split("---");
				for(int i=0;i<smola.length;i++) {
					combo.addItem(smola[i]);
				}
			      
			      Object[] mes= {
			        "Choose item to restock: ",combo,
			      };
				int opt=JOptionPane.showConfirmDialog(null,mes,"Input an item",JOptionPane.OK_CANCEL_OPTION);
				if(opt==JOptionPane.OK_OPTION) {
					JTextField aF=new JTextField();
					JComboBox bF=new JComboBox();
					try {
						String tempid=ClientGUI.getAllShopIDs();
						String[] smoltemp=tempid.split("---");
					for(int i=0;i<smoltemp.length;i++) {
						bF.addItem(smoltemp[i]);
					}
					Object[] obij= {
					        "Input quantity: ",aF,
					        "Input shop ID:",bF,
					};
					int opt2=JOptionPane.showConfirmDialog(null,obij,"Finalize",JOptionPane.OK_CANCEL_OPTION);
					if(opt2==JOptionPane.OK_OPTION) {
						try {
							ClientGUI.insertTransactionIn(Integer.parseInt(bF.getSelectedItem().toString()), Integer.parseInt(aF.getText()), (String)combo.getSelectedItem());
						} catch (NumberFormatException | RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cleartable(model);
						setTable(model);
					}

					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

				} catch (RemoteException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
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
