import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.*;
import java.util.*;

class AdminFrame extends JFrame implements ActionListener{
	
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	
	Font f1=new Font ("Rockwell",Font.BOLD,20);
	Font f2=new Font ("Sylfaen",Font.BOLD,15);
	
	JLabel l1=new JLabel("Supershop Management System(S.M.S)");
	JLabel l2=new JLabel("Date : " +dateFormat.format(date));

	JButton btnAddProducts=new JButton("Add Products");
	JButton btnShowProducts=new JButton("Show All Products");
	JButton btnEditProduct=new JButton("Edit Product");
	JButton btnDltProduct=new JButton("Delete Product");
	JButton btnCreateAnUser=new JButton("Create An User");
	JButton btnEditUser=new JButton("Edit An User");
	JButton btnDeleteUser=new JButton("Delete An User");
	JButton btnLogout=new JButton("Logout");
	JButton btnViewRecords=new JButton("View Records");
	
	public AdminFrame(){
		setSize(800,600);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(l1);
		l1.setFont(f1);
		add(l2);
		l2.setFont(f2);

		add(btnAddProducts);
		btnAddProducts.setBounds(50,210,140,30);
		add(btnShowProducts);
		btnShowProducts.setBounds(50,260,140,30);
		add(btnEditProduct);
		btnEditProduct.setBounds(50,310,140,30);
		add(btnDltProduct);
		btnDltProduct.setBounds(50,360, 140, 30);
		add(btnCreateAnUser);
		btnCreateAnUser.setBounds(300,210,140,30);
		add(btnEditUser);
		btnEditUser.setBounds(300,260,140,30);
		add(btnDeleteUser);
		btnDeleteUser.setBounds(300, 310, 140, 30);
		add(btnViewRecords);
		btnViewRecords.setBounds(300, 360, 140, 30);
		add(btnLogout);
		btnLogout.setBounds(70,500,80,25);
		
	
		l1.setBounds(200,10,400,20);
		l2.setBounds(220,65,150,20);
	
		btnAddProducts.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
			dispose();
			
			AddProduct btnAddProducts=new AddProduct();
			btnAddProducts.setVisible(true);
        	}
		});
		
		btnShowProducts.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
				dispose();
				ShowAllProducts c=new ShowAllProducts();
				c.setVisible(true);
	        }
		});
		
		btnEditProduct.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();	
				EditProduct b5=new EditProduct();
				b5.setVisible(true);
	        }
		});
		
		btnDltProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				DeleteProduct btnDltProduct=new DeleteProduct();
				btnDltProduct.setVisible(true);	
	        }
		});
	
		btnCreateAnUser.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
				dispose();
				CreateUser btnCreateAnUser=new CreateUser();
				btnCreateAnUser.setVisible(true);
	        }
		
		});
		

		btnEditUser.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();	
				EditUser btnEditUser=new EditUser();
				btnEditUser.setVisible(true);
	        }
		});
		
		btnDeleteUser.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();	
				DeleteUser btnDeleteUser=new DeleteUser();
				btnDeleteUser.setVisible(true);
	        }
		});
		
		btnViewRecords.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
				dispose();
				ViewRecords btnViewRecords=new ViewRecords();
				btnViewRecords.setVisible(true);
	        }
		});
		
		btnLogout.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
				dispose();
				LoginFrame btnLogout=new LoginFrame();
				btnLogout.setVisible(true);
	        }
		});
		
	}
	
	public void actionPerformed(ActionEvent e){
		
	}	
}
