package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.CartItem;
import model.Product;
import view.AddToCartForm;
import view.CashierCartInternalView;
import view.PaymentForm;
import view.UpdateCartItemForm;

public class CartItemHandler {

	public static String message;
	
	public CartItemHandler() {
		
	}
	
	public static CartItem addToCart(String productID, String quantity)
	{
		if(productID.trim().isEmpty())
		{
			message = "Product ID must be filled";
			return null;
		}
		
		if(quantity.trim().isEmpty())
		{
			message = "Quantity must be filled";
			return null;
		}
		
		Integer productIDNum = isNumeric(productID);
		Integer quantityNum = isNumeric(quantity);
		
		if(productIDNum == null || quantityNum == null)
		{
			message = "Input must be numeric";
			return null;
		}
		
		Product currProduct = ProductHandler.getProduct(productIDNum);
		
		if(currProduct == null)
		{
			message = "Product is not found";
			return null;
		}
		
		if(quantityNum < 0)
		{
			message = "Quantity cannot be less than 0";
			return null;
		}
		
		if(quantityNum > currProduct.getStock())
		{
			message = "Product stock is not sufficient";
			return null;
		}
		
		CartItem currCartItem = getCartItemByProductID(productIDNum);
		
		CartItem updatedCartItem;
		
		if(currCartItem == null)
		{
			currCartItem = new CartItem(productIDNum, quantityNum);
			
			updatedCartItem = currCartItem.insertCartItem();
		}
		else
		{
			quantityNum += currCartItem.getQuantity();
			return updateStock(productID, String.valueOf(quantityNum));
		}
		
		if(updatedCartItem == null)
		{
			message = "Failed to insert the cart";
		}
		else
		{
			message = "Successfully inserted the cart";
		}
		
		return updatedCartItem;
	}
	
	public static CartItem updateStock(String productID, String stock)
	{
		Integer productIDNum = isNumeric(productID);
		Integer stockNum = isNumeric(stock);
		
		if(productIDNum == null || stockNum == null)
		{
			message = "Input must be numeric";
			return null;
		}
		
		Product currProduct = ProductHandler.getProduct(productIDNum);
		
		if(currProduct == null)
		{
			message = "Product is not found";
			return null;
		}
		
		if(stockNum < 0)
		{
			message = "Quantity cannot be less than 0";
			return null;
		}
		
		if(stockNum > currProduct.getStock())
		{
			System.out.println(currProduct.getStock());
			System.out.println(stockNum);
			message = "Product stock is not sufficient";
			return null;
		}
		
		CartItem currCartItem = new CartItem(productIDNum, stockNum);
		CartItem updatedCartItem;
		
		updatedCartItem = currCartItem.updateStock();
		
		if(updatedCartItem == null)
		{
			message = "Failed to update the cart";
		}
		else
		{
			message = "Successfully updated the cart";
		}
		
		return updatedCartItem;
	}
	
	public static Boolean deleteItem(String productID)
	{
		Integer productIDNum = isNumeric(productID);
		
		if(productIDNum == null)
		{
			message = "Input must be numeric";
			return null;
		}
		
		CartItem currCartItem = getCartItemByProductID(productIDNum);
		
		Boolean isDeleted = false;
		
		if(currCartItem == null)
		{
			message = "Cart Item Not Found";
			return false;
		}
		
		isDeleted = currCartItem.deleteItem();
		
		if(!isDeleted)
		{
			message = "Failed to delete the product";
		}
		else
		{
			message = "Success to delete the product";
		}
		
		return isDeleted;
	}
	
	public static boolean clearCartItemList()
	{
		CartItem cartItem = new CartItem();
		boolean isDeleted = cartItem.deleteAllCartItem();
		
		if(!isDeleted)
		{
			message = "Failed to insert the transaction";
		}
		else
		{
			message = "Successfully inserted the transaction";
		}
		
		return isDeleted;
	}
	
	
	public static int getGrandTotal()
	{
		CartItem cartItem = new CartItem();
		return cartItem.getGrandTotal();
	}
	
	public static CartItem getCartItemByProductID(int productID) {
		CartItem cartItem = new CartItem();
		return cartItem.getCartItem(productID);
	}
	
	public static List<CartItem> getListCartItem(){
		CartItem cartItem = new CartItem();
		return cartItem.getListCartItem();
	}
	
	public static CashierCartInternalView viewManageCartForm() {
		return new CashierCartInternalView();
	}

	public static AddToCartForm viewAddToCartForm() {
		return new AddToCartForm();
	}
	
	public static UpdateCartItemForm viewUpdateCartForm(CartItem currCartItem) {
		return new UpdateCartItemForm(currCartItem);
	}
	
	public static PaymentForm viewPaymentForm()
	{
		return new PaymentForm();
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
	
	public static void refreshManageCartTable()
	{
		CashierCartInternalView.refreshTable();
	}
}
