import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.*;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

class LoginFrame extends JFrame implements ActionListener{
	
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
	
	User user;
	Employee employee;
	Admin admin;
	
	Font f1=new Font ("Rockwell",Font.BOLD,25);
	Font f2=new Font ("Sylfaen",Font.BOLD,15);
	Font f3=new Font ("Sylfaen",Font.BOLD,15);
	
	JLabel l1=new JLabel("Supershop Management System(S.M.S)");
	JLabel lId=new JLabel("ID:");
	JLabel lPassword=new JLabel("Password:");
	
	
	JTextField tfUserName = new JTextField();
	JPasswordField tPasswordField = new JPasswordField(20);
	
	JButton bLogin=new JButton("Login");
	
	Connection conn=null;
	
	public LoginFrame(){
		conn=ConnectDB();
		
		setSize(800,600);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(l1);
		l1.setFont(f1);
		l1.setBounds(160,40,500,40);
		add(lId);
		lId.setFont(f2);
		lId.setBounds(200,110,95,50);
		add(lPassword);
		lPassword.setFont(f3);
		lPassword.setBounds(200,180,100,50);
		
		add(tfUserName);
		tfUserName.setBounds(300,122,200,30);
		add(tPasswordField);
		tPasswordField.setBounds(300,192,200,30);
	
		add(bLogin);
		bLogin.setBounds(350,250,100,30);
	
		bLogin.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
			
		    PreparedStatement pst,pst2;
			ResultSet rs,rs2;
			
			String sql="Select * from userlogin where id=? and password =?";
			String sql2="Select * from adminlogin where id=? and password =?";
			//id and password is the column name and
			//userlogin is the table name..change it according to your database table
			//adminlogin is the table name..change it according to your database table
				user=new User();
				employee=new Employee();
				admin=new Admin();
			try{
				
				pst=(PreparedStatement)conn.prepareStatement(sql); 
				user.setid(tfUserName.getText());
				user.setpassword(tPasswordField.getText());
				pst.setString (1,user.getid());
				pst.setString (2,user.getpassword());
				rs=pst.executeQuery();
			
				pst2=(PreparedStatement)conn.prepareStatement(sql2); 
				pst2.setString (1,user.getid());
				pst2.setString (2,user.getpassword());
				rs2=pst2.executeQuery();
				
				if(!user.getid().equals("") && !user.getpassword().equals("") && rs.next() ){
				//textFieldUsername and passwordField is the respective textboxes..change it according to your code
					
					EmployeeFrame btnLogin=new EmployeeFrame();
					btnLogin.setVisible(true);
					employee.setname(rs.getString("name"));
					JOptionPane.showMessageDialog(null, "Welcome "+employee.getname());
					
					}	
				
			    else if(!user.getid().equals("") && !user.getpassword().equals("") && rs2.next()){
					
			    	AdminFrame btnLogin=new AdminFrame();
					btnLogin.setVisible(true);
					//admin.setname(rs2.getString("name"));
					JOptionPane.showMessageDialog(null,"Welcome Admin");
								
			    	}
				else {
					JOptionPane.showMessageDialog(null, "Incorrect username/password. Please try again.");
					LoginFrame m=new LoginFrame();
				}
			}
			catch(Exception e1){
				e1.printStackTrace();
			} 
        }
		
		});
	
	}
    public void actionPerformed(ActionEvent e){
			
	}
	
}


