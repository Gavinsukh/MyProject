package view;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connect.Connect;
import controller.EmployeeHandler;

public class LoginView extends JFrame implements ActionListener{
	//Darker Shade(46,52,65)
	//White (229,233,240)
	
	JPanel mainPanel, northPanel, centerPanel, southPanel ,centerSouthPanel, centerNorthPanel;
	JLabel companyLabel, titleLabel, imageLabel, usernameLabel, passwordLabel;
	ImageIcon userImage;
	JTextField userName;
	JPasswordField userPass;
	JButton loginButton;

	public LoginView() {
		mainPanel = new JPanel(new BorderLayout());
		northPanel =  new JPanel(new GridLayout(2, 1,0,0));
		centerPanel =  new JPanel(new GridLayout(2, 1,0,10));
		southPanel =  new JPanel();
		centerNorthPanel = new JPanel();
		centerSouthPanel = new JPanel(new GridLayout(5,1,0,10));
		
		//Main Panel
		
		//North Panel
		northPanel.setBackground(new Color(59,66,83)); 
		
		companyLabel = new JLabel("Just Du It");
		companyLabel.setHorizontalAlignment(JLabel.CENTER);
		companyLabel.setFont(new Font("Roboto", Font.BOLD, 24));
		companyLabel.setForeground(new Color(229,233,240));
		
		titleLabel = new JLabel("WELCOME");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Roboto",Font.PLAIN, 80));
		titleLabel.setForeground(new Color(229,233,240));
		
		northPanel.add(companyLabel);
		northPanel.add(titleLabel);
		
		//Center NorthPanel
		centerNorthPanel.setBackground(new Color(59,66,83)); 
		userImage = new ImageIcon(new ImageIcon("user.png").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
		imageLabel = new JLabel(userImage);
		centerNorthPanel.add(imageLabel);
		
		//Center South Panel
		centerSouthPanel.setBackground(new Color(59,66,83));


		usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Roboto",Font.BOLD, 14));
		usernameLabel.setForeground(new Color(229,233,240));
		userName = new JTextField();
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Roboto",Font.BOLD, 14));
		passwordLabel.setForeground(new Color(229,233,240));
		userPass = new JPasswordField();
		
		loginButton = new JButton("Login");
		loginButton.addActionListener(this);
		
		centerSouthPanel.setBorder(BorderFactory.createEmptyBorder(0,550,80,550));
		
		centerSouthPanel.add(usernameLabel);
		centerSouthPanel.add(userName);
		centerSouthPanel.add(passwordLabel);
		centerSouthPanel.add(userPass);
		centerSouthPanel.add(loginButton);
		
		//Center Panel
		centerPanel.setBackground(new Color(59,66,83)); 
		centerPanel.add(centerNorthPanel);
		centerPanel.add(centerSouthPanel);
		
		//South Panel
		southPanel.setBackground(new Color(59,66,83));
		
		
		//Putting Panel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		add(mainPanel);
		
		showFrame();
	}
	
	public void showFrame() {	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setSize(new Dimension(1366,768));
		setResizable(false);
		setTitle("Login");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton ) {
			String loginName = userName.getText();
			String loginPass = String.valueOf(userPass.getPassword());
			int role=0;
			
			role=EmployeeHandler.login(loginName,loginPass);
			
			if(role==0) {
				String errorMessage = EmployeeHandler.errorMessage;
				JOptionPane.showMessageDialog(null, errorMessage,"Error", JOptionPane.ERROR_MESSAGE);
			}else {
				EmployeeHandler.viewerChooser(role);
				this.dispose();
			}
		}
		
	}

}
