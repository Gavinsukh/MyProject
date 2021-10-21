package view;

import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import controller.EmployeeHandler;
import controller.TransactionHandler;

public class ManagerView extends JFrame implements MouseListener{
	
	JMenuBar menubar;
	JMenu transactionMenu, employeeMenu, logoutMenu;
	TransactionReport transactionInternal = null;
	ManageEmployeeForm employeeInternal = null;

	public ManagerView() {
		 JFrame frame = new JFrame("Manager Menu");
		 
		 //Menubar
		 menubar = new JMenuBar();
		 transactionMenu = new JMenu("Transaction");
		 employeeMenu = new JMenu("Employee");
		 logoutMenu = new JMenu("Logout");
		 
		 transactionMenu.addMouseListener(this);
		 employeeMenu.addMouseListener(this);
		 logoutMenu.addMouseListener(this);
		 
		 menubar.add(transactionMenu);
		 menubar.add(employeeMenu);
		 menubar.add(logoutMenu);
		 
		 setJMenuBar(menubar);
		 
		 showFrame();
	}
	
	public void showFrame() {	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setSize(new Dimension(1366,768));
		setResizable(false);
		setTitle("Manager Menu");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == logoutMenu) {
			System.out.println("Logout menu is selected");

			int result = JOptionPane.showConfirmDialog(null, "Do you want to logout?");
			System.out.println(result);

			if(result == 0) {
				this.dispose();
				LoginView lv = EmployeeHandler.viewLoginView();
			}
		}else if(e.getSource() == transactionMenu) {
			System.out.println("Transaction View");
			if(transactionInternal != null) {
				transactionInternal.dispose();
			}
			if(employeeInternal != null) {
				employeeInternal.dispose();
			}
			add(transactionInternal = TransactionHandler.viewTransactionReport());
		}else if(e.getSource() == employeeMenu) {
			System.out.println("Employee View");
			if(transactionInternal != null) {
				transactionInternal.dispose();
			}
			if(employeeInternal != null) {
				employeeInternal.dispose();
			}
			add(employeeInternal = EmployeeHandler.viewManageEmployeeForm());

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
