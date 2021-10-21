package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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

import connect.Connect;
import controller.ProductHandler;
import controller.TransactionHandler;
import model.Product;
import model.Transaction;
import model.TransactionItem;

public class CashierTransactionInternalView extends JInternalFrame implements ActionListener, MouseListener {

	JPanel mainPanel, northPanel, centerPanel, centerNorthPanel, centerCenterPanel, centerCenterNorthPanel,centerCenterCenterPanel;
	JLabel roleLabel, listLabel, dummyLabel;
	
	//Date
	JLabel dateLabel;
	JLabel dateText;
	JTextField yearField, monthField;
	
	//Table
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scrollpane;
	JLabel idLabel, purchaseDateLabel, employeeIdLabel, paymentLabel;
	Connect con;
	Vector<Object> rowData;
	
	//Table Transaction Item
	JPanel centerSouthPanel, southPanel;
	JLabel transactionItemTitleLabel, transactionItem;
	
	public CashierTransactionInternalView() {
		mainPanel = new JPanel(new BorderLayout());
		northPanel =  new JPanel(new GridLayout(1,2,0,0));
		centerPanel =  new JPanel(new BorderLayout());
		centerNorthPanel = new JPanel(new FlowLayout());
		centerCenterPanel = new JPanel();
		centerSouthPanel = new JPanel();
		southPanel = new JPanel(new FlowLayout());
		
		//northPanel
		northPanel.setBackground(new Color(59,66,83));
		dummyLabel = new JLabel("");
		roleLabel = new JLabel("Transaction List"); 
		roleLabel.setHorizontalAlignment(JLabel.LEFT);
		roleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		roleLabel.setForeground(new Color(229,233,240));
		
		northPanel.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));
		
		northPanel.add(roleLabel);
		northPanel.add(dummyLabel);
		
		//CenterCenter northPanel - Date
		centerNorthPanel.setBackground(new Color(230,230,230));
		
		dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		dateLabel.setForeground(new Color(30,30,30));
		centerNorthPanel.add(dateLabel);
		
		dateText = new JLabel(LocalDate.now().toString());
		dateText.setFont(new Font("Roboto", Font.PLAIN, 14));
		dateText.setForeground(new Color(30,30,30));
		centerNorthPanel.add(dateText);
		
		centerNorthPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));
		
		//CenterCenter centerPanel - Table
		centerCenterPanel.setBackground(new Color(230,230,230));
		
		idLabel = new JLabel("Transaction ID");
		purchaseDateLabel = new JLabel("Purchase Date");
		employeeIdLabel = new JLabel("Employee ID");
		paymentLabel = new JLabel("Payment Type");
		
		Object[] columns = {"ID","Purchase Date","Employee ID","Payment Type", "Timestamp"};
		dtm = new DefaultTableModel(columns, 0);
		
		table = new JTable(dtm);
		table.addMouseListener(this);
		scrollpane = new JScrollPane(table);
		centerCenterPanel.add(scrollpane);
		
		//Center southPanel
		centerSouthPanel.setBackground(new Color(230,230,230));
		
		transactionItemTitleLabel = new JLabel("Click one of the cells to view its transaction item");
		transactionItemTitleLabel.setFont(new Font("Roboto", Font.BOLD, 14));
		transactionItemTitleLabel.setForeground(new Color(30,30,30));
		centerSouthPanel.add(transactionItemTitleLabel);
		
		//Center Panel
		centerPanel.setBackground(new Color(230,230,230));
		centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
		centerPanel.add(centerSouthPanel,BorderLayout.SOUTH);
		
		//South Panel
		southPanel.setBackground(new Color(230,230,230));
		transactionItem = new JLabel("");
		transactionItemTitleLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		southPanel.setBorder(BorderFactory.createEmptyBorder(0,100,50,100));
		southPanel.add(transactionItem);
		
		//Main Panel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		add(mainPanel);
		
		showFrame();
		
		refreshTable();
	}

	public void showFrame() {	
		this.setClosable(true);
		this.setTitle("Transaction");
		this.setVisible(true);
	}
	
	private void refreshTable() {
		Object[] columns = {"ID","Purchase Date","Employee ID","Payment Type", "Timestamp"};
		
		dtm = new DefaultTableModel(columns,0);
		
		List<Transaction> allTransaction = TransactionHandler.getTodayTransaction();
		
		for (Transaction transaction : allTransaction) {
			rowData = new Vector<>();
			
			rowData.add(transaction.getID());
			rowData.add(transaction.getPurchaseDate());
			rowData.add(transaction.getEmployeeID());
			rowData.add(transaction.getPaymentType());
			rowData.add(transaction.getPaymentTimestamp());
			
			dtm.addRow(rowData);
		}
		table.setModel(dtm);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == table) {
			System.out.println("Table clicked");
			String searchedID = table.getValueAt((table.getSelectedRow()),0).toString();
			transactionItemTitleLabel.setText("Transaction "+searchedID+" item detail:");
			
			int parsedID = Integer.parseInt(searchedID);
			String allTransactionItemText="";
			
			List<TransactionItem> allTransactionItem = TransactionHandler.getAllTransactionItem(parsedID);
			
//			for(int i = 0; i <= allTransactionItem.size() - 1 ; i++) {
//				allTransactionItemText+= allTransactionItem.get(i).getProductID();
//				allTransactionItemText = allTransactionItemText + " (" +allTransactionItem.get(i).getQuantity()+")";
//				
//				if(i!= allTransactionItem.size() - 1) {
//					allTransactionItemText+= ", ";
//				}
//			}
			
			allTransactionItemText += "<html>";
			
			for (TransactionItem transactionItem : allTransactionItem) {
				Product currProduct = ProductHandler.getProduct(transactionItem.getProductID());
				
				allTransactionItemText += currProduct.getName() + " - " + transactionItem.getQuantity() + "<br/>";
			}
			
			allTransactionItemText += "</html>";
				
			transactionItem.setText(allTransactionItemText);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		if(e.getSource() == findTransaction) {
//			String year = yearField.getText();
//			String month = monthField.getText();
//			List<Transaction> allTransaction = TransactionHandler.getMonthlyTransaction(month,year);
//			
//			Object[] columns = {"ID","Purchase Date","Employee ID","Payment Type"};
//			dtm = new DefaultTableModel(columns,0);
//			
//			if(!(allTransaction == null)) {
//				for (Transaction transaction : allTransaction) {
//					rowData = new Vector<>();
//					
//					rowData.add(transaction.getID());
//					rowData.add(transaction.getPurchaseDate());
//					rowData.add(transaction.getEmployeeID());
//					rowData.add(transaction.getPaymentType());
//					
//					dtm.addRow(rowData);
//					
//				}
//				table.setModel(dtm);
//			}else {
//				String errorMessage = TransactionHandler.errorMessage;
//				JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
//			}
//			
//		}	
	}
}
