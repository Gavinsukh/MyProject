package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.table.DefaultTableModel;

import controller.CartItemHandler;
import controller.ProductHandler;
import model.CartItem;
import model.Product;

public class AddToCartForm extends JFrame implements ActionListener {

	private JPanel mainPanel, northPanel, centerPanel, centerNorthPanel, centerCenterPanel, centerSouthPanel;
	private JLabel roleLabel, dummyLabel, idLabel, quantityLabel;
	
	private JPanel idFieldPanel, quantityFieldPanel, idLabelPanel, quantityLabelPanel, insertButtonPanel;
	
	private JButton insertButton;
	private DefaultTableModel dtm;
	private JTable productTable;
	private JScrollPane scrollpane;
	private JTextField idField, quantityField;

	private Vector<Object> rowData;
	
	public AddToCartForm() {
		
		mainPanel = new JPanel(new BorderLayout());
		northPanel =  new JPanel(new GridLayout(1,2,0,0));
		centerPanel =  new JPanel(new BorderLayout());
		centerCenterPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		centerNorthPanel = new JPanel();
		centerSouthPanel = new JPanel(new GridLayout(3,2));
		idFieldPanel = new JPanel();
		quantityFieldPanel = new JPanel();
		idLabelPanel = new JPanel();
		quantityLabelPanel = new JPanel();
		insertButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		idFieldPanel.setBackground(new Color(230,230,230));
		quantityFieldPanel.setBackground(new Color(230,230,230));
		idLabelPanel.setBackground(new Color(230,230,230));
		quantityLabelPanel.setBackground(new Color(230,230,230));
		insertButtonPanel.setBackground(new Color(230,230,230));
		
		//northPanel
		northPanel.setBackground(new Color(59,66,83));
		dummyLabel = new JLabel("");
		roleLabel = new JLabel("Add To Cart Form"); 
		roleLabel.setHorizontalAlignment(JLabel.LEFT);
		roleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		roleLabel.setForeground(new Color(229,233,240));
		
		northPanel.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));
		northPanel.add(roleLabel);
		northPanel.add(dummyLabel);
		
		
		//Center northPanel
		centerNorthPanel.setBackground(new Color(230,230,230));
		idLabel = new JLabel("ID");
		quantityLabel = new JLabel("Quantity");
		
		Object[] columns = {"ID", "Name", "Description", "Price", "Stock"};
		dtm = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		productTable = new JTable(dtm);
//						refreshTable();
		scrollpane = new JScrollPane(productTable);
		centerNorthPanel.add(scrollpane);
		centerNorthPanel.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));

		//Center centerPanel
//		centerCenterPanel.setBackground(new Color(230,230,230));
//
//		centerCenterPanel.setBorder(BorderFactory.createEmptyBorder(0,440,30,440));

		//Center southPanel
		centerSouthPanel.setBackground(new Color(230,230,230));
		
		idLabel =  new JLabel("ID");
		idLabel.setPreferredSize(new Dimension(150, 30));
		idLabelPanel.add(idLabel);
		
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(200, 30));
		idFieldPanel.add(idField);
		idField.setEditable(false);
		
		quantityLabel =  new JLabel("Quantity");
		quantityLabel.setPreferredSize(new Dimension(150, 30));
		quantityLabelPanel.add(quantityLabel);
		
		quantityField = new JTextField();
		quantityField.setPreferredSize(new Dimension(200, 30));
		quantityFieldPanel.add(quantityField);
		
		insertButton = new JButton("Insert");
		insertButton.setBackground(new Color(0, 204, 0));
		insertButton.setForeground(Color.WHITE);
		insertButton.setPreferredSize(new Dimension(150, 30));
		insertButtonPanel.add(insertButton);
		
		centerSouthPanel.add(idLabelPanel);
		centerSouthPanel.add(idFieldPanel);
		centerSouthPanel.add(quantityLabelPanel);
		centerSouthPanel.add(quantityFieldPanel);
		centerSouthPanel.add(insertButtonPanel);
		
		centerSouthPanel.setBorder(BorderFactory.createEmptyBorder(0,440,30,440));
		
		centerPanel.setBackground(new Color(230,230,230));
		centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
		centerPanel.add(centerSouthPanel, BorderLayout.CENTER);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		addListener();
		add(mainPanel);
		showFrame();
		
		refreshTable();
	}
	
	private void addListener()
	{
		productTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				String id = productTable.getValueAt(productTable.getSelectedRow(), 0).toString();
				idField.setText(id);
				
				System.out.println(id);
			}
		});
		
		insertButton.addActionListener(this);
	}

	private void refreshTable()
	{
		Object[] columns = {"ID", "Name", "Description", "Price", "Stock"};
		dtm = new DefaultTableModel(columns, 0);
		
		List<Product> allProducts = ProductHandler.getAllProduct();
		
		
		for (Product product : allProducts) {
			rowData = new Vector<>();
			
			rowData.add(product.getID());
			rowData.add(product.getName());
			rowData.add(product.getDescription());
			rowData.add(product.getPrice());
			rowData.add(product.getStock());
			dtm.addRow(rowData);
		}
		
		productTable.setModel(dtm);
	}
	
	private void showFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setSize(new Dimension(1366,768));
		setResizable(false);
		setTitle("Add To Cart");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertButton)
		{	
			String productIDString = idField.getText();
			String quantityString = quantityField.getText();
			
			CartItem currCartItem = CartItemHandler.addToCart(productIDString, quantityString);
			
			if(currCartItem == null)
			{
				JOptionPane.showMessageDialog(this, CartItemHandler.message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, CartItemHandler.message, "Success", JOptionPane.INFORMATION_MESSAGE);
				CartItemHandler.refreshManageCartTable();
				dispose();
			}
		}	
	}
}
