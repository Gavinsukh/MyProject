package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import controller.EmployeeHandler;
import controller.ProductHandler;

public class ProductManage extends JFrame implements MenuListener{
	
	JMenuBar menuBar;
	JMenu manage, logOut, viewMenu;
	JMenuItem insert, delete, update; 
	ViewProduct vp = null;
	ManageProductForm mp = null;

	
	public ProductManage() {
		//initialize
		//menuBar
		menuBar = new JMenuBar();
		
		//menuBar fill
		manage = new JMenu("Manage Product"); //homeMenu
		viewMenu = new JMenu("View Product");
		logOut = new JMenu("Log Out");
		
		//menuListener
		logOut.addMenuListener(this);
		viewMenu.addMenuListener(this);
		manage.addMenuListener(this);
	
		//add menu to menuBar
		menuBar.add(manage);
		menuBar.add(viewMenu);
		menuBar.add(logOut);
		
		//setMenuBar
		this.setJMenuBar(menuBar);	
		
		//set JFrame
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1366,768));
		this.setLocationRelativeTo(null);
		this.setTitle("Product Management");
		this.setVisible(true);
	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == logOut) {
			//int flag = 
			int flag = JOptionPane.showConfirmDialog(null, "Are you sure want to log out?");
			if(flag == 0) {
				this.dispose();
				EmployeeHandler.viewLoginView();
			}
		}
		else if(e.getSource() == viewMenu) {
			if(mp != null) {
				mp.dispose();
			}
			else if(vp != null) {
				vp.dispose();
			}
			add(vp = ProductHandler.viewViewProduct());
		}
		else if(e.getSource() == manage) {
			if(vp != null) {
				vp.dispose();
			}
			else if(mp != null) {
				mp.dispose();
			}
			add(mp = ProductHandler.viewManageProductForm());
		}
	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

    


