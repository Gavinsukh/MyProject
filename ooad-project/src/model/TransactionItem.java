package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;

public class TransactionItem {
	private int transactionID;
	private int productID;
	private int quantity;

	public TransactionItem() {
		// TODO Auto-generated constructor stub
	}

	public TransactionItem(int transactionID, int productID, int quantity) {
		this.transactionID = transactionID;
		this.productID = productID;
		this.quantity = quantity;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public TransactionItem addTransactionItem(int transactionID, int productID, int quantity)
	{
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO transactionitem (transactionID,productID,quantity) VALUES (?,?,?)");
			ps.setInt(1, transactionID);
			ps.setInt(2, productID);
			ps.setInt(3, quantity);
			
			if(ps.executeUpdate() == 1)
			{
				return new TransactionItem(transactionID, productID, quantity);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<TransactionItem> getAllTransactionItem(int transactionID){
		List<TransactionItem> allTransactionItem = new ArrayList<>();
		
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM transactionitem WHERE transactionID=?");
			ps.setInt(1,transactionID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int newTransactionID = rs.getInt("transactionID");
				int newProductID = rs.getInt("productID");
				int newQuantity = rs.getInt("quantity");
				
				TransactionItem newTransactionItem = new TransactionItem(newTransactionID, newProductID, newQuantity);
				allTransactionItem.add(newTransactionItem);
			}
			
			return allTransactionItem;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
