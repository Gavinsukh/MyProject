package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;

public class CartItem {
	
	private int productID;
	private int quantity;

	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public CartItem(int productID, int quantity) {
		this.productID = productID;
		this.quantity = quantity;
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
	
	public String getProductName()
	{
		Product currProduct = new Product();
		currProduct = currProduct.getProduct(productID);
		
		if(currProduct != null)
		{
			return currProduct.getName();			
		}
		
		return null;
	}
	
	public int getProductPrice()
	{
		Product currProduct = new Product();
		currProduct = currProduct.getProduct(productID);
		
		if(currProduct != null)
		{
			return currProduct.getPrice();			
		}
		
		return -1;
	}
	
	public int getSubTotal()
	{
		Product currProduct = new Product();
		currProduct = currProduct.getProduct(productID);
		
		return quantity * currProduct.getPrice();
	}
	
	public int getGrandTotal()
	{
		int grandTotal = 0;
		List<CartItem> allCartItem = getListCartItem();
		
		for (CartItem cartItem : allCartItem) {
			grandTotal += cartItem.getSubTotal();
		}
		
		return grandTotal;
	}
	
	public CartItem getCartItem(int productID)
	{
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM cartitem WHERE productID = ?");
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				int quantity = rs.getInt("quantity");
				
				CartItem currentCartItem = new CartItem(productID, quantity);
				return currentCartItem;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public List<CartItem> getListCartItem()
	{
		List<CartItem> allCartItem = new ArrayList<CartItem>();
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM cartitem");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int productID = rs.getInt("productID");
				int quantity = rs.getInt("quantity");
				
				CartItem currentCartItem = new CartItem(productID, quantity);
				allCartItem.add(currentCartItem);
			}
			
			return allCartItem;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public CartItem insertCartItem()
	{
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO cartitem VALUES (?,?)");
			ps.setInt(1, productID);
			ps.setInt(2, quantity);
			
			if(ps.executeUpdate() == 1)
			{
				return new CartItem(productID, quantity);
			}
			
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public CartItem updateStock()
	{
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("UPDATE cartitem SET quantity=? WHERE productID=?");
			ps.setInt(1, quantity);
			ps.setInt(2, productID);
			
			if(ps.executeUpdate() == 1)
			{	
				return new CartItem(productID, quantity);
			}
			
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Boolean deleteItem()
	{
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("DELETE FROM cartitem WHERE productID=?");
			ps.setInt(1, productID);
			
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Boolean deleteAllCartItem()
	{
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("DELETE FROM cartitem");
			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
