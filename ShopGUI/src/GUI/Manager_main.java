package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Manager_main {
	static int row=0;

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
	public Manager_main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 30, 30, 0, 30};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblMyLittleShops = new JLabel("My Little Shop(s)");
		GridBagConstraints gbc_lblMyLittleShops = new GridBagConstraints();
		gbc_lblMyLittleShops.insets = new Insets(0, 0, 5, 5);
		gbc_lblMyLittleShops.gridx = 2;
		gbc_lblMyLittleShops.gridy = 0;
		frame.getContentPane().add(lblMyLittleShops, gbc_lblMyLittleShops);
		
		String[] combo = { "Shop1", "Shop2", "Shop3", "Shop 4", "Shop 5" };
		
		JComboBox comboBox = new JComboBox(combo);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		table = new JTable(new DefaultTableModel(new Object[] {"No.","Name","Barcode No.","Quantity"},0));
		final DefaultTableModel model =(DefaultTableModel)table.getModel();
		scrollPane_1.setViewportView(table);
		for(int i=0;i<10;i++) {
			model.addRow(new Object[] {0,0,0,0});
		}
		
		JButton btnNewButton = new JButton("See");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String in=comboBox.getSelectedItem().toString();
				int rowCount = model.getRowCount();
			    //Remove rows one by one from the end of the table
			    for (int i = rowCount - 1; i >= 0; i--) {
			        model.removeRow(i);
			    }
			    row=0;
				if(in.equals("Shop1")) {
				    for(int i=0;i<10;i++) {
						model.addRow(new Object[] {1,1,1,1});
					}
				}
				else if(in.equals("Shop2")) {
					for(int i=0;i<10;i++) {
						model.addRow(new Object[] {2,2,2,2});
					}
				}
				else {
					for(int i=0;i<10;i++) {
						model.addRow(new Object[] {3,3,3,3});
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
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int SelRow = table.getSelectedRow();
				model.removeRow(SelRow);
			}
		});
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemove.gridx = 3;
		gbc_btnRemove.gridy = 2;
		frame.getContentPane().add(btnRemove, gbc_btnRemove);
	}

}
