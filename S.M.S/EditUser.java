import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.*;
import javax.swing.*;

class EditUser extends JFrame implements ActionListener{
	
	public static Connection ConnectDB(){
        try{
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sms","root","");
            return conn;
           }
		catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        	 return null;
           }
    }
	Connection conn=null;
	Admin admin;
	Employee employee;
	Font f1=new Font ("Rockwell",Font.BOLD,20);
	Font f2=new Font ("Sylfaen",Font.BOLD,15);
	Font f3=new Font ("Sylfaen",Font.BOLD,15);
	Font f4=new Font ("Sylfaen",Font.BOLD,15);
	Font f5=new Font ("Sylfaen",Font.BOLD,15);
	Font f6=new Font ("Sylfaen",Font.BOLD,15);
	Font f7=new Font ("Sylfaen",Font.BOLD,15);
	Font f8=new Font ("Sylfaen",Font.BOLD,15);
	Font f9=new Font ("Sylfaen",Font.BOLD,15);
	
	JLabel l1=new JLabel("Supershop Management System(S.M.S)");
	JLabel l2=new JLabel("Type Of User:");
	JLabel l3=new JLabel("Enter User ID:");
	JLabel l4=new JLabel("User Password:");
	JLabel l5=new JLabel("User Name:");
	JLabel l6=new JLabel("Contact No:");
	JLabel l7=new JLabel("Email:");
	JLabel l8=new JLabel("Address:");
	JLabel l9=new JLabel("Date Of Joining:");
	
	JTextField tfUserID=new JTextField();
	JTextField tfUserPassword=new JTextField();
	JTextField tfUserName=new JTextField();
	JTextField tfContactNo=new JTextField();
	JTextField tfEmail=new JTextField();
	JTextField tfAddress=new JTextField();
	JTextField tfDateOfJoining=new JTextField();
	
	
	String[] combobox={"Admin","Employee"};
	JComboBox cbTypeOfUser=new JComboBox(combobox);
	JButton bEditPassword=new JButton("Edit");
	JButton bEditName=new JButton("Edit");
	JButton bEditContactNo=new JButton("Edit");
	JButton bEditEmail=new JButton("Edit");
	JButton bEditAddress=new JButton("Edit");
	JButton bEditDateOfJoining=new JButton("Edit");
	JButton bBack=new JButton("Back");
	JButton bLogout=new JButton("Logout");
	
	public EditUser(){
		conn=ConnectDB();
		
		setSize(800,600);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(l1);
		l1.setFont(f1);
		l1.setBounds(200,10,400,20);
		add(l2);
		l2.setFont(f2);
		l2.setBounds(200,100,200,20);
		add(l3);
		l3.setFont(f3);
		l3.setBounds(200,160,200,20);
		add(l4);
		l4.setFont(f4);
		l4.setBounds(200,210,200,20);
		add(l5);
		l5.setFont(f5);
		l5.setBounds(200,260,200,20);
		add(l6);
		l6.setFont(f6);
		l6.setBounds(200,310,200,20);
		add(l7);
		l7.setFont(f7);
		l7.setBounds(200,360,200,20);
		add(l8);
		l8.setFont(f8);
		l8.setBounds(200,410,200,20);
		add(l9);
		l9.setFont(f9);
		l9.setBounds(200,460,200,20);
		
		add(tfUserID);
		add(tfUserPassword);
		add(tfUserName);
		add(tfContactNo);
		add(tfEmail);
		add(tfAddress);
	    add(tfDateOfJoining);
	   
		add(cbTypeOfUser);
		cbTypeOfUser.setBounds(400,100,100,20);
		add(bEditPassword);
		bEditPassword.setBounds(570, 205, 100, 30);
		add(bEditName);
		bEditName.setBounds(570,255, 100, 30);
		add(bEditContactNo);
		bEditContactNo.setBounds(570, 305, 100, 30);
		add(bEditEmail);
		bEditEmail.setBounds(570,355, 100, 30);
		add(bEditAddress);
		bEditAddress.setBounds(570, 405, 100, 30);
		add(bEditDateOfJoining);
		bEditDateOfJoining.setBounds(570, 455, 100, 30);
		add(bBack);
		bBack.setBounds(620,500,90,25);
		add(bLogout);
		bLogout.setBounds(75,500,100,23);
		
		tfUserID.setBounds(350,155,200,30);
		tfUserPassword.setBounds(350,205,200,30);
		tfUserName.setBounds(350,255,200,30);
		tfContactNo.setBounds(350,305,200,30);		
		tfEmail.setBounds(350,355,200,30);		
		tfAddress.setBounds(350,405,200,30);		
		tfDateOfJoining.setBounds(350,455,200,30);	
		
		cbTypeOfUser.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {	
				try{
				}
				catch(Exception ae){
					ae.printStackTrace();
				}
	        }
		});
		
		bEditPassword.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();
				String a;
				a=(String)cbTypeOfUser.getSelectedItem();
				admin=new Admin();
				employee=new Employee();
				try{
					String sql="";
					
					if(a.equals("Admin")){
						 sql="update adminlogin set password=? where id=?";
						 PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
						 admin.setid(tfUserID.getText());
					     admin.setpassword(tfUserPassword.getText());
					    
						 pst.setString(1,admin.getpassword());
					     pst.setString(2,admin.getid());
		
					     pst.executeUpdate();
					}
					
				    else if(a.equals("Employee")){
					     sql="update userlogin set  password=?  where id=?";
					     PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					    
					     employee.setid(tfUserID.getText());
					     employee.setpassword(tfUserPassword.getText());
					     
					     pst.setString(1,employee.getpassword());
					     pst.setString(2,employee.getid());
					  
					     
					 
					     pst.executeUpdate();
				    }
				     tfUserID.setText("");
				     tfUserPassword.setText("");
					 JOptionPane.showMessageDialog(null,"User Password Edited Successfully");
					 EditUser bEditPassword=new EditUser();
					 bEditPassword.setVisible(true);
				}
				catch(Exception ae){
					ae.printStackTrace();
				}
	        }
		});
		
		bEditName.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();
				String a;
				a=(String)cbTypeOfUser.getSelectedItem();
				admin=new Admin();
				employee=new Employee();
				try{
					String sql="";
					
					if(a.equals("Admin")){
						sql="update adminlogin set name=? where id=?";
						 PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
						
						 admin.setid(tfUserID.getText());    
					     admin.setname(tfUserName.getText());
					     
					     pst.setString(1,admin.getname());
					     pst.setString(2,admin.getid());
		
					     pst.executeUpdate();
					}
					
				    else if(a.equals("Employee")){
					     sql="update userlogin set  name=? where id=?";
					     PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					    	  
					     employee.setid(tfUserID.getText());
					     employee.setname(tfUserName.getText());
					     
					     pst.setString(1,employee.getname());
					     pst.setString(2,employee.getid());
					     pst.executeUpdate();
				    }
					 
				     tfUserID.setText("");
				     tfUserName.setText("");
					 JOptionPane.showMessageDialog(null,"User Name Edited Successfully");
					 EditUser bEditName=new EditUser();
					 bEditName.setVisible(true);
				}
				catch(Exception ae){
					ae.printStackTrace();
				}
	        }
		});
		
		bEditContactNo.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();
				String a;
				a=(String)cbTypeOfUser.getSelectedItem();
				admin=new Admin();
				employee=new Employee();
				try{
					String sql="";
					
					if(a.equals("Admin")){
						sql="update adminlogin set contact_no=? where id=?";
						 PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
						 admin.setid(tfUserID.getText());
					     admin.setcontact_no(tfContactNo.getText());
					     					
					     pst.setString(1,admin.getcontact_no());					    
					     pst.setString(2,admin.getid());
		
					     pst.executeUpdate();
					}
					
				    else if(a.equals("Employee")){
					    sql="update userlogin set contact_no=? where id=?";
					    PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					    					  
					     employee.setid(tfUserID.getText());
					     employee.setcontact_no(tfContactNo.getText());
					     
					     pst.setString(1,employee.getcontact_no());
					     pst.setString(2,employee.getid());

					     pst.executeUpdate();
				    }
					 
				     tfUserID.setText("");
				     tfContactNo.setText("");
					 JOptionPane.showMessageDialog(null,"User Contact No. Edited Successfully");
					 EditUser bEditEmail=new EditUser();
					 bEditEmail.setVisible(true);
				}
				catch(Exception ae){
					ae.printStackTrace();
				}
	        }
		});
		
		bEditEmail.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();
				String a;
				a=(String)cbTypeOfUser.getSelectedItem();
				admin=new Admin();
				employee=new Employee();
				try{
					String sql="";
					
					if(a.equals("Admin")){
						sql="update adminlogin set  email=? where id=?";
						 PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
						 admin.setid(tfUserID.getText());
					     admin.setemail(tfEmail.getText());

					     pst.setString(1,admin.getemail());
					     pst.setString(2,admin.getid());
		
					     pst.executeUpdate();
					}
					
				    else if(a.equals("Employee")){
					    sql="update userlogin set  email=? where id=?";
					    PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					     employee.setid(tfUserID.getText());   
					     employee.setemail(tfEmail.getText());
					     
					     pst.setString(1,employee.getemail());
					     pst.setString(2,employee.getid());
					  
					     pst.executeUpdate();
				    }
					 
				     tfUserID.setText("");
				     tfEmail.setText("");
					 JOptionPane.showMessageDialog(null,"User Email Edited Successfully");
					 EditUser bEditEmail=new EditUser();
					 bEditEmail.setVisible(true);
				}
				catch(Exception ae){
					ae.printStackTrace();
				}
	        }
		});
		
		bEditAddress.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();
				String a;
				a=(String)cbTypeOfUser.getSelectedItem();
				admin=new Admin();
				employee=new Employee();
				try{
					String sql="";
					
					if(a.equals("Admin")){
						sql="update adminlogin set address=? where id=?";
						 PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
						 admin.setid(tfUserID.getText());
					     admin.setaddress(tfAddress.getText());

					     pst.setString(1,admin.getaddress());
					     pst.setString(2,admin.getid());
		
					     pst.executeUpdate();
					}
					
				    else if(a.equals("Employee")){
					    sql="update userlogin set address=? where id=?";
					     PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					    
					     employee.setid(tfUserID.getText());
					     employee.setaddress(tfAddress.getText());
					     
					     pst.setString(1,employee.getaddress());
					     pst.setString(2,employee.getid());
					  
					     pst.executeUpdate();
				    }
					 
				     tfUserID.setText("");
				     tfAddress.setText("");
					 JOptionPane.showMessageDialog(null,"User Address Edited Successfully");
					 EditUser bEditAddress=new EditUser();
					 bEditAddress.setVisible(true);
				}
				catch(Exception ae){
					ae.printStackTrace();
				}
	        }
		});
		
		bEditDateOfJoining.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();
				String a;
				a=(String)cbTypeOfUser.getSelectedItem();
				admin=new Admin();
				employee=new Employee();
				try{
					String sql="";
					
					if(a.equals("Admin")){
						sql="update adminlogin set dateofjoining=? where id=?";
						 PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
						 admin.setid(tfUserID.getText());
					     admin.setdate_of_joining(tfDateOfJoining.getText());
					     
					     pst.setString(1,admin.getdate_of_joining());
					     pst.setString(2,admin.getid());
		
					     pst.executeUpdate();
					}
					
				    else if(a.equals("Employee")){
					     sql="update userlogin set  dateofjoining=? where id=?";
					     PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					     
					     employee.setid(tfUserID.getText());
					     employee.setdate_of_joining(tfDateOfJoining.getText());
					     
					     pst.setString(1,employee.getdate_of_joining());
					     pst.setString(2,employee.getid());
					  
					     pst.executeUpdate();
				    }
				     tfUserID.setText("");
				     tfDateOfJoining.setText("");
					 JOptionPane.showMessageDialog(null,"User Date Of Joining Edited Successfully");
					 EditUser bEditDateOfJoining=new EditUser();
					 bEditDateOfJoining.setVisible(true);
				}
				catch(Exception ae){
					ae.printStackTrace();
				}
	        }
		});
		
		bBack.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e) {
				dispose();
				AdminFrame bLogout=new AdminFrame();
				bLogout.setVisible(true);		
	        }
		});
		
		bLogout.addActionListener(new ActionListener() 
			{
	        public void actionPerformed(ActionEvent e) {	
				dispose();
				LoginFrame bBack=new LoginFrame();
				bBack.setVisible(true);
	        }
		});
	}
	
	public void actionPerformed(ActionEvent e){
			
	}
	
}