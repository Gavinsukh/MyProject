package controller;


import model.Product;
import view.ManageProductForm;
import view.ViewProduct;

import java.util.Vector;

import model.Product;

public class ProductHandler {
	public static String errorMsg;
	
	public ProductHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean checkNumber(String i) {
		try {
			Double.parseDouble(i);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static Vector<Product> getAllProduct(){
		Product product = new Product();
		return product.getAllProduct();
	}
	
	public static Product addProduct(String name, String description, String price, String stock) {
		int parsedPrice;
		int parsedStock;
		
		//validation belom yg if exist
		if(name.trim().isEmpty()) {
			errorMsg = "Name must be filled";
			return null;
		}
		else if(description.trim().isEmpty()) {
			errorMsg = "Description must be filled";
			return null;
		}
		else if(price.trim().isEmpty()) {
			errorMsg = "Price must be filled";
			return null;
		}
		else if(!checkNumber(price)) {
			errorMsg = "Price must be numeric (integer)";
			return null;
		}
		else if(stock.trim().isEmpty()) {
			errorMsg = "Stock must be filled";
			return null;
		}
		else if(!checkNumber(stock)) {
			errorMsg = "Stock must be numeric (integer)";
			return null;
		}
		else if(Integer.valueOf(price)<=0) {
			errorMsg = "Price must be more than 0";
			return null;
		}
		else if(Integer.valueOf(stock)<=0) {
			errorMsg = "Stock must be more than 0";
			return null;
		}
		else {
			parsedPrice = Integer.parseInt(price);
			parsedStock = Integer.parseInt(stock);
			
//			Product productSearch = new Product();
//			Product product = new Product();
//			product = product.getProduct(name);
			
//			if(name.equals(product.getName())) {
//				errorMsg = "Name is same";
//				return null;
//			}
//			else {
				Product product = new Product();
				
				product.setName(name);
				product.setDescription(description);
				product.setPrice(parsedPrice);
				product.setStock(parsedStock);
				
				return product.insertProduct();
//			}
			
		}
	}
	
	public static Product updateProduct(int ID, String name, String description, String price, String stock) {
		int parsedPrice;
		int parsedStock;
		parsedPrice = Integer.parseInt(price);
		parsedStock = Integer.parseInt(stock);
		
		Product product = new Product();
		
//		Product productSearch = new Product();
//		productSearch = productSearch.getProduct(name);
		
		//validation belom yg if exist
		if(name.trim().isEmpty()) {
			errorMsg = "Name must be filled";
			return null;
		}
		else if(description.trim().isEmpty()) {
			errorMsg = "Description must be filled";
			return null;
		}
		else if(price.trim().isEmpty()) {
			errorMsg = "Price must be filled";
			return null;
		}
		else if(!checkNumber(price)) {
			errorMsg = "Price must be numeric (integer)";
			return null;
		}
		else if(stock.trim().isEmpty()) {
			errorMsg = "Stock must be filled";
			return null;
		}
		else if(!checkNumber(stock)) {
			errorMsg = "Stock must be numeric (integer)";
			return null;
		}
//		else if(productSearch == null) {
//			errorMsg = "Name is same";
//			return null;
//		}
		else if(Integer.valueOf(price)<=0) {
			errorMsg = "Price must be more than 0";
			return null;
		}
		else if(Integer.valueOf(stock)<=0) {
			errorMsg = "Stock must be more than 0";
			return null;
		}
		else {
			product.setID(ID);
			product.setName(name);
			product.setDescription(description);
			product.setPrice(parsedPrice);
			product.setStock(parsedStock);
			
			return product.updateProduct();
		}
		
	}
	
	public static Product reduceProductStock(int ID, int stock) {
		Product product = getProduct(ID);
//		product.setID(ID);
//		product.setStock(stock);
		
		
		if(product != null)
		{
			product.setStock(stock);
			return product.updateProduct();			
		}
		
		return null;
	}
	
	public static boolean deleteProduct(int ID) {
		Product product = new Product();
		product.setID(ID);
		
		if(!product.deleteProduct()) {
			return false;
		}
		else return true;
	}

	
	public static Product getProduct(int productID)
	{
		Product product = new Product();
		return product.getProduct(productID); 
	}
	
	public static ViewProduct viewViewProduct() {
		return new ViewProduct();
	}
	
	public static ManageProductForm viewManageProductForm() {
		return new ManageProductForm();
	}

}
