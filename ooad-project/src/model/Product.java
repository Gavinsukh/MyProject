package model;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connect.Connect;

public class Product {
	private int ID;
	private String name;
	private String description;
	private int price;
	private int stock;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int ID, String name, String description, int price, int stock) {
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Product getProduct(int productID)
	{
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM `product` WHERE ID = ?");
			ps.setInt(1, productID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.first())
			{
				int id = rs.getInt("ID");
				String name = rs.getString("name");
				String description = rs.getString("description");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				
				return new Product(id, name, description, price, stock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public Vector<Product> getAllProduct(){
		Vector<Product> products = new Vector<>();
		try {
			PreparedStatement p = Connect.getInstance().prepareStatement("SELECT * FROM product");
			ResultSet r = p.executeQuery();
			
			while(r.next()){
				int ID = r.getInt("ID");
				String name = r.getString("name");
				String description = r.getString("description");
				int price = r.getInt("price");
				int stock = r.getInt("stock");
				
				Product product = new Product(ID, name, description, price, stock);
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Product insertProduct() {
		Product product = new Product();
		
		try {
			PreparedStatement p = Connect.getInstance().prepareStatement("INSERT INTO product" + "(name, description, price, stock) VALUES (?, ?, ?, ?)");
			p.setString(1, name);
			p.setString(2, description);
			p.setInt(3, price);
			p.setInt(4, stock);
			
			p.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Product Inserted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
		
	}
	
	public Product updateProduct() {
		Product product = new Product();
		
		try {
			PreparedStatement p = Connect.getInstance().prepareStatement("UPDATE product SET name=?, description=?, price=?, stock=? WHERE ID=?");
			p.setString(1, name);
			p.setString(2, description);
			p.setInt(3, price);
			p.setInt(4, stock);
			p.setInt(5, ID);
			
			if(p.executeUpdate() == 1)
			{
				return new Product(ID, name, description, price, stock);
			}
			//JOptionPane.showMessageDialog(null, "Product Updated");
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
//	public Product reduceProductStock() {
//		Product product = new Product();
//		
//		try {
//			PreparedStatement p = Connect.getInstance().prepareStatement("UPDATE product SET stock=? WHERE ID=?");
//			p.setInt(1, stock);
//			p.setInt(2, ID);
//			
//			if(p.executeUpdate() == 1)
//			{
//				return new Product(ID, name, description, price, stock);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return product;
//	}
	
	public boolean deleteProduct() {
		int a = JOptionPane.showConfirmDialog(null, "Are you sure want to delete ID?" + ID);
//		System.out.println(a);
		try {
			if(a != 1 && a != 2) {
				PreparedStatement p = Connect.getInstance().prepareStatement("DELETE FROM product WHERE ID=?");
				p.setInt(1, ID);
				p.executeUpdate();
				JOptionPane.showMessageDialog(null, "Product Succesfully Deleted");
			}
			return true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
}
