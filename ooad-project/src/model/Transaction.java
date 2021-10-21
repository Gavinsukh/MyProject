package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connect.Connect;

public class Transaction {
	private int ID;
	private Date purchaseDate;
	private int employeeID;
	private String paymentType;
	private Timestamp paymentTimestamp;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(int ID, Date purchaseDate, int employeeID, String paymentType, Timestamp paymentTimestamp) {
		this.ID = ID;
		this.purchaseDate = purchaseDate;
		this.employeeID = employeeID;
		this.paymentType = paymentType;
		this.paymentTimestamp = paymentTimestamp;
	}
	
	public Transaction(Date purchaseDate, int employeeID, String paymentType, Timestamp paymentTimestamp) {
		super();
		this.purchaseDate = purchaseDate;
		this.employeeID = employeeID;
		this.paymentType = paymentType;
		this.paymentTimestamp = paymentTimestamp;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Timestamp getPaymentTimestamp() {
		return paymentTimestamp;
	}

	public void setPaymentTimestamp(Timestamp paymentTimestamp) {
		this.paymentTimestamp = paymentTimestamp;
	}

	public List<Transaction> getAllTransaction(){
		List<Transaction> allTransactions = new ArrayList<Transaction>();
		
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM transaction");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int newId = rs.getInt("ID");
				Date newPurchaseDate = rs.getDate("purchaseDate");
				String newPaymentType = rs.getString("paymentType");
				int newEmployeeID = rs.getInt("employeeID");
				Timestamp newTimestamp = rs.getTimestamp("paymentTimestamp");
				
				Transaction newTransaction = new Transaction(newId ,newPurchaseDate ,newEmployeeID, newPaymentType, newTimestamp);
				allTransactions.add(newTransaction);
			}
			
			return allTransactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Transaction> getMonthlyTransaction(){
		List<Transaction> allTransactions = new ArrayList<Transaction>();
		
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM transaction WHERE YEAR(purchaseDate)=? AND MONTH(purchaseDate)=?");
			ps.setInt(1,this.purchaseDate.getYear());
			ps.setInt(2,this.purchaseDate.getMonth());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int newId = rs.getInt("ID");
				Date newPurchaseDate = rs.getDate("purchaseDate");
				String newPaymentType = rs.getString("paymentType");
				int newEmployeeID = rs.getInt("employeeID");
				Timestamp newTimestamp = rs.getTimestamp("paymentTimestamp");
				
				Transaction newTransaction = new Transaction(newId ,newPurchaseDate ,newEmployeeID, newPaymentType, newTimestamp);
				allTransactions.add(newTransaction);
			}
			
			return allTransactions;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Transaction> getTodayTransactions()
	{
		List<Transaction> todayTransactions = new ArrayList<Transaction>();
		
		PreparedStatement ps;
		try {
			ps = Connect.getInstance().prepareStatement("SELECT * FROM `transaction` WHERE purchaseDate = CURDATE() ORDER BY paymentTimestamp ASC");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				int newId = rs.getInt("ID");
				Date newPurchaseDate = rs.getDate("purchaseDate");
				String newPaymentType = rs.getString("paymentType");
				int newEmployeeID = rs.getInt("employeeID");
				Timestamp newTimestamp = rs.getTimestamp("paymentTimestamp");
				
				Transaction newTransaction = new Transaction(newId ,newPurchaseDate ,newEmployeeID, newPaymentType, newTimestamp);
				todayTransactions.add(newTransaction);
			}
			
			return todayTransactions;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Transaction addTransaction()
	{
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO transaction (purchaseDate,employeeID,paymentType,paymentTimestamp) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1,new java.sql.Date(purchaseDate.getTime()));
			ps.setInt(2, employeeID);
			ps.setString(3, paymentType);
			ps.setTimestamp(4, paymentTimestamp);
			
			if(ps.executeUpdate() == 1)
			{
				ResultSet rs = ps.getGeneratedKeys();
				int lastInsertedID = -1;
				
				if(rs.next())
				{
					lastInsertedID = rs.getInt(1);
				}
				
//				System.out.println(lastInsertedID);
				
				return new Transaction(lastInsertedID, purchaseDate, employeeID, paymentType, paymentTimestamp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
