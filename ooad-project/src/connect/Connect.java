package connect;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;

public class Connect {
	
	private static Connection con;
	private static ResultSet rs;
	private static Statement stat;
	
	//Singleton
	public static Connection getInstance() {
		if(con==null) {
			new Connect();
		}
		
		return con;
	}
	
	//Connection
	public Connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/just_du_it", "root", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
