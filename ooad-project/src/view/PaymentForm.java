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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CartItemHandler;
import controller.TransactionHandler;
import model.Transaction;

public class PaymentForm extends JFrame implements ActionListener{

	private JPanel mainPanel, northPanel, centerPanel;
	private JLabel roleLabel, dummyLabel, grandTotalLabel, grandTotalValueLabel, paymentMethodLabel, totalPaymentLabel;
	private JComboBox<String> paymentMethodCb;
	
	private JPanel grandTotalLabelPanel, grandTotalValueLabelPanel, paymentMethodLabelPanel, paymentMethodCbPanel, totalPaymentLabelPanel, totalPaymentFieldPanel, checkoutButtonPanel;
	
	private JButton checkoutButton;
	private JTextField totalPaymentField;
	
	private GridBagConstraints gbc;
	
	private String[] paymentMethodList = {"Choose a payment type", "Cash", "Credit"};
	
	public PaymentForm() {
		mainPanel = new JPanel(new BorderLayout());
		northPanel =  new JPanel(new GridLayout(1,2,0,0));
		centerPanel =  new JPanel(new GridBagLayout());
		
		grandTotalLabelPanel = new JPanel();
		grandTotalValueLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		paymentMethodLabelPanel = new JPanel();
		paymentMethodCbPanel = new JPanel();
		totalPaymentLabelPanel = new JPanel();
		totalPaymentFieldPanel = new JPanel();
		checkoutButtonPanel = new JPanel();
		
		//northPanel
		northPanel.setBackground(new Color(59,66,83));
		dummyLabel = new JLabel("");
		roleLabel = new JLabel("Payment Form");
		roleLabel.setHorizontalAlignment(JLabel.LEFT);
		roleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		roleLabel.setForeground(new Color(229,233,240));
		
		northPanel.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));
		northPanel.add(roleLabel);
		northPanel.add(dummyLabel);
		
		//centerPanel
		grandTotalLabel = new JLabel("Grand Total");
		grandTotalLabel.setPreferredSize(new Dimension(150, 30));
		grandTotalLabelPanel.add(grandTotalLabel);
		
		grandTotalValueLabel = new JLabel();
		grandTotalValueLabel.setPreferredSize(new Dimension(200, 30));
		grandTotalValueLabelPanel.add(grandTotalValueLabel);
		
		paymentMethodLabel = new JLabel("Payment Method");
		paymentMethodLabel.setPreferredSize(new Dimension(150, 30));
		paymentMethodLabelPanel.add(paymentMethodLabel);
		
		paymentMethodCb = new JComboBox<String>(paymentMethodList);
		paymentMethodCb.setPreferredSize(new Dimension(200, 30));
		paymentMethodCbPanel.add(paymentMethodCb);
		
		totalPaymentLabel = new JLabel("Total Payment");
		totalPaymentLabel.setPreferredSize(new Dimension(150, 30));
		totalPaymentLabelPanel.add(totalPaymentLabel);
		
		totalPaymentField = new JTextField();
		totalPaymentField.setPreferredSize(new Dimension(200, 30));
		totalPaymentFieldPanel.add(totalPaymentField);
		
		checkoutButton = new JButton("Checkout");
		checkoutButton.setBackground(new Color(0, 204, 0));
		checkoutButton.setForeground(Color.WHITE);
		checkoutButton.setPreferredSize(new Dimension(150, 30));
		checkoutButtonPanel.add(checkoutButton);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(grandTotalLabelPanel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		centerPanel.add(grandTotalValueLabelPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		centerPanel.add(paymentMethodLabelPanel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		centerPanel.add(paymentMethodCbPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		centerPanel.add(totalPaymentLabelPanel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		centerPanel.add(totalPaymentFieldPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		centerPanel.add(checkoutButtonPanel, gbc);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		getCartData();
		
		addListener();
		add(mainPanel);
		showFrame();
	}
	
	private void getCartData()
	{
		Integer grandTotal = CartItemHandler.getGrandTotal();
		
		grandTotalValueLabel.setText(String.valueOf(grandTotal));
	}
	
	private void addListener()
	{
		checkoutButton.addActionListener(this);
	}
	
	private void showFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setSize(new Dimension(1366,768));
		setResizable(false);
		setTitle("Payment Form");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == checkoutButton)
		{
			String paymentMethodString = String.valueOf(paymentMethodCb.getSelectedItem());
			String totalPaymentString = totalPaymentField.getText();
			
			Transaction newTransaction = TransactionHandler.insertTransaction(paymentMethodString, totalPaymentString);
			
			if(newTransaction == null)
			{
				JOptionPane.showMessageDialog(this, TransactionHandler.message, "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, TransactionHandler.message, "Success", JOptionPane.INFORMATION_MESSAGE);
				CartItemHandler.refreshManageCartTable();
				dispose();
			}
		}
	}

}
