package view;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import controller.CartItemHandler;
import controller.TransactionHandler;
import model.Employee;

public class CashierView extends JFrame implements MouseListener{

	private JMenuBar menubar;
	private JMenu transactionMenu, cartMenu, logoutMenu;
	private CashierTransactionInternalView transactionInternal = null;
	private CashierCartInternalView cartInternal = null;
	
	public CashierView() {
		JFrame frame = new JFrame("Cashier Menu");
		
		// Menubar
		menubar = new JMenuBar();
		transactionMenu = new JMenu("Transaction");
		cartMenu = new JMenu("Cart");
		logoutMenu = new JMenu("Logout");
		
		transactionMenu.addMouseListener(this);
		cartMenu.addMouseListener(this);
		logoutMenu.addMouseListener(this);
		
		menubar.add(transactionMenu);
		menubar.add(cartMenu);
		menubar.add(logoutMenu);
		
		setJMenuBar(menubar);
		
		showFrame();
	}
	
	public void showFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setSize(new Dimension(1366,768));
		setResizable(false);
		setTitle("Cashier Menu");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == logoutMenu) 
		{
			int result = JOptionPane.showConfirmDialog(null, "Do you want to logout?");

			if(result == 0) {
				this.dispose();
				LoginView lv = new LoginView();
			}
		}
		else if(e.getSource() == transactionMenu) 
		{
			if(transactionInternal != null) 
			{
				transactionInternal.dispose();
			}
			if(cartInternal != null) {
				cartInternal.dispose();
			}
			add(transactionInternal = TransactionHandler.viewTodayTransactionReport());
		}
		else if(e.getSource() == cartMenu)
		{
			if(cartInternal != null) {
				cartInternal.dispose();
			}
			if(transactionInternal != null) {
				transactionInternal.dispose();
			}
			add(cartInternal = CartItemHandler.viewManageCartForm());
			
//			CartItemHandler.viewAddToCartForm();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}
