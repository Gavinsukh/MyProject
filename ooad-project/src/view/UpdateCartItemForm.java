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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CartItemHandler;
import model.CartItem;

public class UpdateCartItemForm extends JFrame implements ActionListener{

	private JPanel mainPanel, northPanel, centerPanel;
	private JLabel roleLabel, dummyLabel, idLabel, quantityLabel;
	
	private JPanel idFieldPanel, quantityFieldPanel, idLabelPanel, quantityLabelPanel, updateButtonPanel;
	
	private JButton updateButton;
	private JTextField idField, quantityField;
	
	private CartItem currCartItem;
	private GridBagConstraints gbc;
	
	private Integer productID, quantity;
	
	public UpdateCartItemForm(CartItem currCartItem) {
		
		this.currCartItem = currCartItem;
		
		mainPanel = new JPanel(new BorderLayout());
		northPanel =  new JPanel(new GridLayout(1,2,0,0));
		centerPanel =  new JPanel(new GridBagLayout());
//		centerCenterPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
//		centerNorthPanel = new JPanel();
//		centerSouthPanel = new JPanel(new GridLayout(3,2));
		idFieldPanel = new JPanel();
		quantityFieldPanel = new JPanel();
		idLabelPanel = new JPanel();
		quantityLabelPanel = new JPanel();
		updateButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		//northPanel
		northPanel.setBackground(new Color(59,66,83));
		dummyLabel = new JLabel("");
		roleLabel = new JLabel("Update Cart Item Form");
		roleLabel.setHorizontalAlignment(JLabel.LEFT);
		roleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		roleLabel.setForeground(new Color(229,233,240));
		
		northPanel.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));
		northPanel.add(roleLabel);
		northPanel.add(dummyLabel);
		
		//Center center panel
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
		
		updateButton = new JButton("Update");
		updateButton.setBackground(new Color(0, 204, 0));
		updateButton.setForeground(Color.WHITE);
		updateButton.setPreferredSize(new Dimension(150, 30));
		updateButtonPanel.add(updateButton);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(idLabelPanel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		centerPanel.add(idFieldPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		centerPanel.add(quantityLabelPanel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		centerPanel.add(quantityFieldPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		centerPanel.add(updateButtonPanel, gbc);
		
		productID = currCartItem.getProductID();
		quantity = currCartItem.getQuantity();
		
		idField.setText(productID.toString());
		quantityField.setText(quantity.toString());
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		add(mainPanel);
		
		addListener();
		showFrame();
	}
	
	private void addListener()
	{
		updateButton.addActionListener(this);
	}
	
	private void showFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setSize(new Dimension(1366,768));
		setResizable(false);
		setTitle("Update Cart");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == updateButton)
		{
			String productIDString = idField.getText();
			String quantityString = quantityField.getText();
			
			CartItem cartItem = CartItemHandler.updateStock(productIDString, quantityString);
			
			if(cartItem == null)
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
