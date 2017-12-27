import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import com.mysql.jdbc.*;
import javax.swing.*;

class AddProduct extends JFrame implements ActionListener{
	
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
	Product product;

	Font f1=new Font ("Rockwell",Font.BOLD,20);
	Font f2=new Font ("Sylfaen",Font.BOLD,15);
	Font f3=new Font ("Sylfaen",Font.BOLD,15);
	Font f4=new Font ("Sylfaen",Font.BOLD,15);
	Font f5=new Font ("Sylfaen",Font.BOLD,15);
	Font f6=new Font ("Sylfaen",Font.BOLD,15);
	Font f7=new Font ("Sylfaen",Font.BOLD,15);
	Font f8=new Font ("Sylfaen",Font.BOLD,15);
	Font f9=new Font ("Sylfaen",Font.BOLD,15);
	
	JLabel lSms=new JLabel("Supershop Management System(S.M.S)");
	JLabel lProductId=new JLabel("Product ID:");
	JLabel lProductName=new JLabel("Product Name:");
	JLabel lUnitPrice=new JLabel("Unit Price:");
	JLabel lQuantity=new JLabel("Quantity:");
	
	JTextField tfProductId=new JTextField();
	JTextField tfProductName=new JTextField();
	JTextField tfUnitPrice=new JTextField();
	JTextField tfQuantity=new JTextField();
	
	
	JButton bAdd=new JButton("Add");
	JButton bBack=new JButton("Back");
	JButton bLogout=new JButton("Logout");
	
	public AddProduct(){
		conn=ConnectDB();
		
		setSize(800,600);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(lSms);
		lSms.setFont(f1);
		lSms.setBounds(200,10,400,20);
		add(lProductId);
		lProductId.setFont(f3);
		lProductId.setBounds(200,160,200,20);
		add(lProductName);
		lProductName.setFont(f4);
		lProductName.setBounds(200,210,200,20);
		add(lUnitPrice);
		lUnitPrice.setFont(f5);
		lUnitPrice.setBounds(200,260,200,20);
		add(lQuantity);
		lQuantity.setFont(f6);
		lQuantity.setBounds(200,310,200,20);
	
		add(tfProductId);
		tfProductId.setBounds(350,155,200,30);
		add(tfProductName);
		tfProductName.setBounds(350,205,200,30);
		add(tfUnitPrice);
		tfUnitPrice.setBounds(350,255,200,30);
		add(tfQuantity);
		tfQuantity.setBounds(350,305,200,30);
		
		add(bAdd);
		bAdd.setBounds(620,400,90,25);
		add(bBack);
		bBack.setBounds(75,500,70,23);
		add(bLogout);
		bLogout.setBounds(620,500,90,25);
		
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				dispose();
				String sql="insert into productdetails values(?,?,?,?)";
				PreparedStatement pst;
				product=new Product();
				try{
					Double q,up;
					
				    pst = (PreparedStatement) conn.prepareStatement(sql);
				    product.setId(tfProductId.getText());
				    product.setName(tfProductName.getText());
					up=Double.parseDouble(tfUnitPrice.getText());
					product.setUnitPrice(up);
					q=Double.parseDouble(tfQuantity.getText());
					product.setQuantity(q);
				
					pst.setString(1,product.getId());
					pst.setString(2,product.getName());
					pst.setDouble(3,product.getUnitPrice());
					pst.setDouble(4,product.getQuantity());
		
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Product Added ");
					AddProduct bAdd=new AddProduct();
					bAdd.setVisible(true);
				}
				catch(Exception ac){
					ac.printStackTrace();
				}	
			}
		
		});
	
		bBack.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
			dispose();
			AdminFrame bBack=new AdminFrame();
			bBack.setVisible(true);
        }
		
		});
		
		bLogout.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
				dispose();
				LoginFrame bLogout=new LoginFrame();
				bLogout.setVisible(true);	
	        }
		});
	}
	
	public void actionPerformed(ActionEvent e){
			
	}	
}