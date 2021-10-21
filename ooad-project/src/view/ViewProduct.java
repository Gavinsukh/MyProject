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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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

public class ViewProduct extends JInternalFrame{
//	 implements ActionListener, MouseListener
	//table
	JTable tableProduct;
	DefaultTableModel dt;
	JScrollPane scroll;
		
	JPanel mainPanel,  northPanel, centerPanel, centerNorthPanel, centerCenterPanel, centerSouthPanel;
	JLabel roleLabel, listLabel, dummyLabel;
	JLabel nameLabel, descLabel, priceLabel, stockLabel, idLabel;
	
	
	Vector<Object> rowData;
	
	public ViewProduct() {
		mainPanel = new JPanel(new BorderLayout());
		northPanel = new JPanel(new GridLayout(1,2,0,0));
		centerPanel = new JPanel(new BorderLayout());
		centerCenterPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		centerNorthPanel = new JPanel();
		centerSouthPanel = new JPanel(new GridLayout(5,2));
		
		//north panel
		northPanel.setBackground(new Color(59,66,83));
		dummyLabel = new JLabel("");
		roleLabel = new JLabel("View Product");
		roleLabel.setHorizontalAlignment(JLabel.LEFT);
		roleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		roleLabel.setForeground(new Color(229,233,240));
		
		northPanel.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));
		northPanel.add(roleLabel);
		northPanel.add(dummyLabel);
		
		centerNorthPanel.setBackground(new Color(230,230,230));
		idLabel = new JLabel("ID");
		nameLabel = new JLabel("Name");
		descLabel = new JLabel("Description");
		priceLabel = new JLabel("Price");
		stockLabel = new JLabel("Stock");
		
		tableProduct = new JTable(dt);
		refreshTable();
		scroll = new JScrollPane(tableProduct);
		centerNorthPanel.add(scroll);
		centerNorthPanel.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
		
		centerPanel.setBackground(new Color(230,230,230));
		centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		add(mainPanel);
		
		setTitle("View Product");
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

}
