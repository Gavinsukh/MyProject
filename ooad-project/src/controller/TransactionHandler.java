package controller;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import connect.Connect;
import model.TransactionItem;
import model.CartItem;
import model.Employee;
import model.Product;
import model.Transaction;
import view.CashierCartInternalView;
import view.CashierTransactionInternalView;
import view.TransactionReport;

public class TransactionHandler {
	public static String message;
	
	public TransactionHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<Transaction> getAllTransaction(){
		Transaction transaction = new Transaction();
		List<Transaction> allTransaction = transaction.getAllTransaction();
		
		if(allTransaction.isEmpty()) {
			message = "Data is not found!";
			return null;
		}
		
		return allTransaction;
	}
	
	public static List<Transaction> getMonthlyTransaction(String reqMonth, String reqYear){
		int month;
		int year;
		
		try {
			month = Integer.parseInt(reqMonth);
			year = Integer.parseInt(reqYear);
		} catch (NumberFormatException e) {
			message="Month and year must be filled with numerical!";
			e.printStackTrace();
			return null;
		}
		
		if(!(year>=1900 && year<=2021)) {
			message = "Please input the year between 1900 and 2021";
			return null;
		}else if(!(month>=1 && month <= 12)){
			message = "Please input the month between 1 and 12";
			return null;
		}
		
		String dateFormat = (year+1900) +"-"+ (month+1);
		Date searchDate = null;
		
		try {
			searchDate = new SimpleDateFormat("yyyy-MM").parse(dateFormat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		Transaction newTransaction = new Transaction();
		newTransaction.setPurchaseDate(searchDate);
		
		List<Transaction> allTransaction = newTransaction.getMonthlyTransaction();
		
		if(allTransaction.isEmpty()) {
			message = "Data is not found!";
			return null;
		}
		
		return allTransaction;
	}
	
	public static Transaction insertTransaction(String paymentType, String totalMoney)
	{
		if(paymentType.trim().isEmpty() || paymentType.equals("Choose a payment type"))
		{
			message = "Payment Method must be chosen";
			return null;
		}
		
		if(totalMoney.trim().isEmpty())
		{
			message = "Total Payment must be filled";
			return null;
		}
		
		if(isNumeric(totalMoney) == null)
		{
			message = "Total Payment must be numeric";
			return null;
		}
		
		int grandTotalNum = CartItemHandler.getGrandTotal();
		int totalMoneyNum = Integer.parseInt(totalMoney);
		
		if(totalMoneyNum < grandTotalNum)
		{
			message = "Payment amount is insufficient";
			return null;
		}
		
		Employee currEmployee = EmployeeHandler.getLoginEmployee();
		
		if(currEmployee == null)
		{
			message = "Please login first!";
			return null;
		}
				
		Date currDate = new Date();
		
		Transaction newTransaction = new Transaction(currDate, currEmployee.getID(), paymentType, new Timestamp(currDate.getTime()));
		Transaction resultTransaction = newTransaction.addTransaction();
		
		if(resultTransaction == null)
		{
			message = "Failed to add the transaction!";
			return null;
		}
		
		List<CartItem> cartItemList = CartItemHandler.getListCartItem();
		
		if(cartItemList.isEmpty())
		{
			message = "Cart is still empty";
			return null;
		}
		
		for(CartItem cartItem : cartItemList)
		{
			Product currProduct = ProductHandler.getProduct(cartItem.getProductID());
			
			if(currProduct == null)
			{
				message = "All products must exist!";
				return null;
			}
			
			if(cartItem.getQuantity() < 0)
			{
				message = "All quantities must be more than 0";
				return null;
			}
			
			if(cartItem.getQuantity() > currProduct.getStock())
			{
				message = "All quantities must be less than the product's stock";
				return null;
			}
		}
		
		for (CartItem cartItem : cartItemList) {
			Product currProduct = ProductHandler.getProduct(cartItem.getProductID());
			
			ProductHandler.reduceProductStock(cartItem.getProductID(), currProduct.getStock() - cartItem.getQuantity());
			
			TransactionItem newTransactionItem = new TransactionItem();
			
			TransactionItem currTransactionItem = newTransactionItem.addTransactionItem(resultTransaction.getID(), currProduct.getID(), cartItem.getQuantity());
		}
		
		boolean isDeleted = CartItemHandler.clearCartItemList();
		
		if(!isDeleted)
		{
			message = "Failed to process the cart";
			return null;
		}
		else
		{
			int changeNum = calculateChange(totalMoneyNum, grandTotalNum);
			message = "Successfully processed the transaction. Here is your change: " + changeNum;
			
			return newTransaction;
		}
	}
	
	private static int calculateChange(int totalMoney, int grandTotal)
	{
		return totalMoney - grandTotal;
	}
	
	public static List<Transaction> getTodayTransaction()
	{
		List<Transaction> todayTransactions = Transaction.getTodayTransactions();
		
		return todayTransactions;
	}
	
	public static List<TransactionItem> getAllTransactionItem(int transactionID){
		TransactionItem transactionItem = new TransactionItem();
		
		return transactionItem.getAllTransactionItem(transactionID);
	}

	public static TransactionReport viewTransactionReport(){
		return new TransactionReport();
	}
	
	public static CashierTransactionInternalView viewTodayTransactionReport() {
		return new CashierTransactionInternalView();
	}

	public static Integer isNumeric(String str)
	{
		Integer number;
		try {
			number = Integer.parseInt(str);
			return number;
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
}
