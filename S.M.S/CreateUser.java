import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import com.mysql.jdbc.*;
import javax.swing.*;

class CreateUser extends JFrame implements ActionListener{
	
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
	JLabel l3=new JLabel("User ID:");
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
	JButton bAdd=new JButton("Add");
	JButton bBack=new JButton("Back");
	JButton bLogout=new JButton("Logout");
	
	public CreateUser(){
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
		tfUserID.setBounds(350,155,200,30);
		add(tfUserPassword);
		tfUserPassword.setBounds(350,205,200,30);
		add(tfUserName);
		tfUserName.setBounds(350,255,200,30);
		add(tfContactNo);
		tfContactNo.setBounds(350,305,200,30);
		add(tfEmail);
		tfEmail.setBounds(350,355,200,30);
		add(tfAddress);
		tfAddress.setBounds(350,405,200,30);
	    add(tfDateOfJoining);
	    tfDateOfJoining.setBounds(350,455,200,30);
		 
		add(cbTypeOfUser);
		cbTypeOfUser.setBounds(400,100,100,20);
		add(bAdd);
		bAdd.setBounds(620,280,90,25);
		add(bBack);
		bBack.setBounds(75,500,70,23);
		add(bLogout);
		bLogout.setBounds(620,500,90,25);
		
		cbTypeOfUser.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
		
	        }
		
		});
		
		bAdd.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				dispose();
				String a;
				a=(String)cbTypeOfUser.getSelectedItem();
				admin=new Admin();
				employee =new Employee();
				try{
					String sql="";
					
					if(a.equals("Admin")){
						
						sql="insert into adminlogin values(?,?,?,?,?,?,?)";
						PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
						admin.setid(tfUserID.getText());
						admin.setpassword(tfUserPassword.getText());
						admin.setname(tfUserName.getText());
						admin.setcontact_no(tfContactNo.getText());
						admin.setemail(tfEmail.getText());
						admin.setaddress(tfAddress.getText());
						admin.setdate_of_joining(tfDateOfJoining.getText());
						
						pst.setString(1,admin.getid());
						pst.setString(2,admin.getpassword());
					    pst.setString(3,admin.getname());
					    pst.setString(4,admin.getcontact_no());
					    pst.setString(5,admin.getemail());
					    pst.setString(6,admin.getaddress());
				        pst.setString(7,admin.getdate_of_joining());  
					    
				        pst.executeUpdate();
					}
					
				    else if(a.equals("Employee")){
					    sql="insert into userlogin values(?,?,?,?,?,?,?)";
					    PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
					    employee.setid(tfUserID.getText());
					    employee.setpassword(tfUserPassword.getText());
					    employee.setname(tfUserName.getText());
					    employee.setcontact_no(tfContactNo.getText());
					    employee.setemail(tfEmail.getText());
					    employee.setaddress(tfAddress.getText());
						employee.setdate_of_joining(tfDateOfJoining.getText());
						
						pst.setString(1,employee.getid());
						pst.setString(2,employee.getpassword());
					    pst.setString(3,employee.getname());
					    pst.setString(4,employee.getcontact_no());
					    pst.setString(5,employee.getemail());
					    pst.setString(6,employee.getaddress());
				        pst.setString(7,employee.getdate_of_joining()); 
				        
				        pst.executeUpdate();
					}
					
					//employee is the table name..change it according to your database
					
					JOptionPane.showMessageDialog(null,"User Added Successfully");
					CreateUser bAdd=new CreateUser();
					bAdd.setVisible(true);
				}
				catch(Exception ae){
					ae.printStackTrace();
				}
				
	        }
		
		});
		
		bBack.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e) {
				dispose();
				AdminFrame bBack=new AdminFrame();
				bBack.setVisible(true);
	        }
		
		});
	
		bLogout.addActionListener(new ActionListener() 
			{
	        public void actionPerformed(ActionEvent e) {	
				dispose();
				LoginFrame bLogout=new LoginFrame();
				bLogout.setVisible(true);
	        }
		});
	}
	
	public void actionPerformed(ActionEvent e){
			
	}

}