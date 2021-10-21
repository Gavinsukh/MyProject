package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CartItemHandler;
import controller.ProductHandler;
import controller.TransactionHandler;
import model.CartItem;
import model.Product;
import model.Transaction;

public class CashierCartInternalView extends JInternalFrame implements ActionListener {

	private JPanel mainPanel, northPanel, centerPanel, centerNorthPanel, centerCenterPanel, centerSouthPanel;
	private JLabel roleLabel, dummyLabel;
	
	//Button
	private JButton insertButton, updateButton, deleteButton, checkoutButton;
	
	//Table
	private static JTable table;
	private JScrollPane scrollpane;
	private JLabel totalPriceLabel;
	private static JLabel totalPriceValueLabel;
	
	private static Vector<Object> rowData;
	private static DefaultTableModel dtm;
	private static Object[] columns = {"ID", "Name", "Price", "Quantity", "Sub Total"};
	
	private CartItem currCartItem;
	
	public CashierCartInternalView() {
		mainPanel = new JPanel(new BorderLayout());
		northPanel =  new JPanel(new GridLayout(1,2,0,0));
		centerPanel =  new JPanel(new BorderLayout());
		centerNorthPanel = new JPanel(new FlowLayout());
		centerCenterPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		centerSouthPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
//		southPanel = new JPanel(new GridLayout(4,2));
		
		//northPanel
		northPanel.setBackground(new Color(59,66,83));
		dummyLabel = new JLabel("");
		roleLabel = new JLabel("Manage Cart Form"); 
		roleLabel.setHorizontalAlignment(JLabel.LEFT);
		roleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		roleLabel.setForeground(new Color(229,233,240));
		
		northPanel.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));

		northPanel.add(roleLabel);
		northPanel.add(dummyLabel);
		
		//Center centerPanel
		centerCenterPanel.setBackground(new Color(230,230,230));
		
		totalPriceLabel = new JLabel("Grand Total");
		centerCenterPanel.add(totalPriceLabel);
		
		totalPriceValueLabel = new JLabel();
		centerCenterPanel.add(totalPriceValueLabel);
		
		insertButton = new JButton("Insert");
		insertButton.setBackground(new Color(0, 204, 0));
		insertButton.setForeground(Color.WHITE);
		centerCenterPanel.add(insertButton);

		updateButton = new JButton("Update");
		updateButton.setBackground(new Color(239, 191, 0));
		updateButton.setForeground(Color.WHITE);
		centerCenterPanel.add(updateButton);

		deleteButton = new JButton("Delete");
		deleteButton.setBackground(new Color(204, 0, 0));
		deleteButton.setForeground(Color.WHITE);
		centerCenterPanel.add(deleteButton);
		
		checkoutButton = new JButton("Checkout");
		checkoutButton.setBackground(new Color(135, 206, 250));
		checkoutButton.setForeground(Color.WHITE);
		centerCenterPanel.add(checkoutButton);

		centerCenterPanel.setBorder(BorderFactory.createEmptyBorder(0,120,30,440));
		
		//Center northPanel
		centerNorthPanel.setBackground(new Color(230,230,230));
		
//		Object[] columns = {"ID", "Name", "Price", "Quantity"};
		dtm = new DefaultTableModel(columns, 0);
		
		table = new JTable(dtm);
//				refreshTable();
		scrollpane = new JScrollPane(table);
		centerNorthPanel.add(scrollpane);
		centerNorthPanel.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
		
		//Center Panel
		centerPanel.setBackground(new Color(230,230,230));
		centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
		centerPanel.add(centerSouthPanel, BorderLayout.SOUTH);

		//Main Panel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		addListener();
		add(mainPanel);
		
		showFrame();
		refreshTable();
	}
	
	public static void refreshTable() {
		
		dtm = new DefaultTableModel(columns,0);
		
		List<CartItem> allCarts = CartItemHandler.getListCartItem();
		
		
		
		for (CartItem cart : allCarts) {
			rowData = new Vector<>();
			
			int productPrice = cart.getProductPrice();
			int cartQuantity = cart.getQuantity();
			int subTotal = cart.getSubTotal();
			
			rowData.add(cart.getProductID());
			rowData.add(cart.getProductName());
			rowData.add(productPrice);
			rowData.add(cartQuantity);
			rowData.add(subTotal);
			
			dtm.addRow(rowData);
		}
		
		Integer grandTotal = CartItemHandler.getGrandTotal();
		
		table.setModel(dtm);
		totalPriceValueLabel.setText(grandTotal.toString());
	}

	public void showFrame() {	
		this.setClosable(true);
		this.setTitle("Cart");
		this.setVisible(true);
	}
	
	public void addListener() {
		insertButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		checkoutButton.addActionListener(this);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				Integer productID = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				Integer quantity = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
				
				currCartItem = new CartItem(productID, quantity);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertButton)
		{
			CartItemHandler.viewAddToCartForm();
		}
		else if(e.getSource() == updateButton)
		{
			if(currCartItem == null)
			{
				JOptionPane.showMessageDialog(this, "Please choose a cart first!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			CartItemHandler.viewUpdateCartForm(currCartItem);
			CartItemHandler.refreshManageCartTable();
			currCartItem = null;
		}
		else if(e.getSource() == deleteButton)
		{
			if(currCartItem == null)
			{
				JOptionPane.showMessageDialog(this, "Please choose a cart first!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int deleteConfirmation = JOptionPane.showConfirmDialog(this, "Are you sure to delete the cart?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(deleteConfirmation == JOptionPane.YES_OPTION) 
			{
				Integer productID = currCartItem.getProductID();
				
				boolean isDeleted = CartItemHandler.deleteItem(productID.toString());
				
				if(!isDeleted)
				{
					JOptionPane.showMessageDialog(this, CartItemHandler.message, "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(this, CartItemHandler.message, "Success", JOptionPane.INFORMATION_MESSAGE);
					CartItemHandler.refreshManageCartTable();
					currCartItem = null;
				}				
			}
		}
		else if(e.getSource() == checkoutButton)
		{
			int checkoutConfirmation = JOptionPane.showConfirmDialog(this, "Are you sure to checkout the cart?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if(checkoutConfirmation == JOptionPane.YES_OPTION)
			{
				CartItemHandler.viewPaymentForm();
			}
		}
	}

}
