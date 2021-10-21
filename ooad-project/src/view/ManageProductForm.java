package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.ProductHandler;
import model.Product;

public class ManageProductForm extends JInternalFrame implements ActionListener, MouseListener{
	
	JPanel mainPanel,  northPanel, centerPanel, centerNorthPanel, centerCenterPanel, centerSouthPanel;
	JLabel roleLabel, listLabel, dummyLabel;
	
	//button
	JButton insertButton, updateButton, deleteButton;
	JLabel spacingLabel1, spacingLabel2;
	
	//table
	JTable tableProduct;
	DefaultTableModel dt;
	JScrollPane scroll;
	
	//text
	JTextField nameField, descField, priceField, stockField, idField;
	
	//label
	JLabel nameLabel, descLabel, priceLabel, stockLabel, idLabel;
	
	Vector<Object> rowData;
	
	public ManageProductForm() {
		mainPanel = new JPanel(new BorderLayout());
		northPanel = new JPanel(new GridLayout(1,2,0,0));
		centerPanel = new JPanel(new BorderLayout());
		centerCenterPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		centerNorthPanel = new JPanel();
		centerSouthPanel = new JPanel(new GridLayout(5,2));
		
		//north panel
		northPanel.setBackground(new Color(59,66,83));
		dummyLabel = new JLabel("");
		roleLabel = new JLabel("Manage Product Form");
		roleLabel.setHorizontalAlignment(JLabel.LEFT);
		roleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		roleLabel.setForeground(new Color(229,233,240));
		
		northPanel.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));
		northPanel.add(roleLabel);
		northPanel.add(dummyLabel);
		
		//center centerpanel
		centerCenterPanel.setBackground(new Color(230,230,230));
		
		//insert button
		insertButton = new JButton("Insert");
		insertButton.setBackground(new Color(0,204,0));
		insertButton.setForeground(Color.WHITE);
		insertButton.addActionListener(this);
		centerCenterPanel.add(insertButton);
		
		//update button
		updateButton = new JButton("Update");
		updateButton.setBackground(new Color(239,191,0));
		updateButton.setForeground(Color.WHITE);
		updateButton.addActionListener(this);
		centerCenterPanel.add(updateButton);
		
		//delete button
		deleteButton = new JButton("Delete");
		deleteButton.setBackground(new Color(204,0,0));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.addActionListener(this);
		centerCenterPanel.add(deleteButton);
		
		centerCenterPanel.setBorder(BorderFactory.createEmptyBorder(0,440,30,440));
		
		//center northpanel
		centerNorthPanel.setBackground(new Color(230,230,230));
		idLabel = new JLabel("ID");
		nameLabel = new JLabel("Name");
		descLabel = new JLabel("Description");
		priceLabel = new JLabel("Price");
		stockLabel = new JLabel("Stock");
		
		tableProduct = new JTable(dt);
		tableProduct.addMouseListener(this);
		refreshTable();
		scroll = new JScrollPane(tableProduct);
		centerNorthPanel.add(scroll);
		centerNorthPanel.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
		
		//center south panel
		centerSouthPanel.setBackground(new Color(230,230,230));
		idField = new JTextField();
		nameField = new JTextField();
		descField = new JTextField();
		priceField = new JTextField();
		stockField = new JTextField();
		
		idField.setEditable(false);
		
		centerSouthPanel.add(idLabel);
		centerSouthPanel.add(idField);
	
		centerSouthPanel.add(nameLabel);
		centerSouthPanel.add(nameField);
		
		centerSouthPanel.add(descLabel);
		centerSouthPanel.add(descField);
		
		centerSouthPanel.add(priceLabel);
		centerSouthPanel.add(priceField);
		
		centerSouthPanel.add(stockLabel);
		centerSouthPanel.add(stockField);
		centerSouthPanel.setBorder(BorderFactory.createEmptyBorder(0,440,30,440));
		
		//center panel
		centerPanel.setBackground(new Color(230,230,230));
		centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
		centerPanel.add(centerSouthPanel, BorderLayout.SOUTH);
		
		//main panel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		add(mainPanel);
		setTitle("Manage Product");
		setClosable(false);
		setVisible(true);
	}
	
	private void refreshTable() {
		Object[] columns = {"ID", "name", "description", "price", "stock"};
		dt = new DefaultTableModel(columns, 0);
		
		Vector<Product> products = ProductHandler.getAllProduct();
		for (Product product : products) {
			rowData = new Vector<>();
			rowData.add(product.getID());
			rowData.add(product.getName());
			rowData.add(product.getDescription());
			rowData.add(product.getPrice());
			rowData.add(product.getStock());
			
			dt.addRow(rowData);
		}
		tableProduct.setModel(dt);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		idField.setText(tableProduct.getValueAt(tableProduct.getSelectedRow(), 0).toString());
		nameField.setText(tableProduct.getValueAt(tableProduct.getSelectedRow(), 1).toString());
		descField.setText(tableProduct.getValueAt(tableProduct.getSelectedRow(), 2).toString());
		priceField.setText(tableProduct.getValueAt(tableProduct.getSelectedRow(), 3).toString());
		stockField.setText(tableProduct.getValueAt(tableProduct.getSelectedRow(), 4).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//insert
		if(e.getSource() == insertButton) {
			String name = nameField.getText();
			String description = descField.getText();
//			int price = Integer.parseInt(priceField.getText());
			String price = priceField.getText();
//			int stock = Integer.parseInt(stockField.getText());
			String stock = stockField.getText();
			
			if(ProductHandler.addProduct(name, description, price, stock) == null) {
				String errorMsg = ProductHandler.errorMsg;
				JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
//				ProductHandler.addProduct(name, description, price, stock);
				JOptionPane.showMessageDialog(null, "Product Successfully Inserted");
				refreshTable();
					
				idField.setText("");
				nameField.setText("");
				descField.setText("");
				priceField.setText("");
				stockField.setText("");
			}
		}
		
		//update
		else if(e.getSource() == updateButton) {
			int ID = Integer.parseInt(idField.getText());
			String name = nameField.getText();
			String description = descField.getText();
//			int price = Integer.parseInt(priceField.getText());
//			int stock = Integer.parseInt(stockField.getText());
			
			String price = priceField.getText();
			String stock = stockField.getText();
			
			if(ProductHandler.updateProduct(ID, name, description, price, stock)==null) {
				String errorMsg = ProductHandler.errorMsg;
				JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
//				ProductHandler.updateProduct(ID, name, description, price, stock);
				JOptionPane.showMessageDialog(null, "Product Succesfully Updated");
				refreshTable();
					
				idField.setText("");
				nameField.setText("");
				descField.setText("");
				priceField.setText("");
				stockField.setText("");
			}
		}
		
		//delete
		else if(e.getSource() == deleteButton) {
			int ID = Integer.parseInt(idField.getText());
			
			if(ProductHandler.deleteProduct(ID)) {
				refreshTable();
				
				idField.setText("");
				nameField.setText("");
				descField.setText("");
				priceField.setText("");
				stockField.setText("");
			}
		}
	}
}
