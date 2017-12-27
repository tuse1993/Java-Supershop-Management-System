import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import com.mxrck.autocompleter.TextAutoCompleter;

class ShowAllProducts extends JFrame implements ActionListener{
	
	Product product;
	Connection conn=null;
	JLabel l1=new JLabel("Product Details");	
	
	JTextField tfSearchProduct=new JTextField();
	
	JButton bBack=new JButton("Back");
	JButton bLogout=new JButton("Logout");
	JButton bSearchProduct=new JButton("Search Product");
	JButton bRefresh=new JButton("Refresh");
	
	JTable tShowAllProduct=new JTable();
	
	public static Connection ConnectDB(){
        try{
        	Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sms","root","");
        	return conn;
        }
		catch(Exception e1){
            JOptionPane.showMessageDialog(null,e1);
        	return null;
        }
    }
	
	public ShowAllProducts(){
		conn=ConnectDB();
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane jScrollPane=new JScrollPane();
		jScrollPane.setBounds(90,120,600,300);
		contentPane.add(jScrollPane);
		tShowAllProduct.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Product ID", "Product Name", "UnitPrice", "Stock"
				}
			));
		
		tShowAllProduct.getTableHeader().setReorderingAllowed(false);
		jScrollPane.setViewportView(tShowAllProduct);
		tShowAllProduct.setDefaultEditor(Object.class, null);
		
		add(l1);
		l1.setBounds(350,10,100,30);
		add(bBack);
		bBack.setBounds(50,500,100,30);
		add(bLogout);
		bLogout.setBounds(630,500,100,30);
		add(tfSearchProduct);
		tfSearchProduct.setBounds(90, 80, 140, 20);
		add(bSearchProduct);
		bSearchProduct.setBounds(250, 80, 130, 20);
		add(bRefresh);
		bRefresh.setBounds(400, 80, 130, 20);
		
		showProductDetails();
		
		TextAutoCompleter complete=new TextAutoCompleter(tfSearchProduct);
		String query3 ="select productName from productdetails";
		Statement st2;
		ResultSet rs2;
			
		try{
			st2=(Statement) conn.createStatement();
			rs2=st2.executeQuery(query3);
				
			while(rs2.next()){
				complete.addItem(rs2.getString("productName"));
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		bSearchProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DefaultTableModel model = (DefaultTableModel)tShowAllProduct.getModel();
				model.setRowCount(0);
				product=new Product();
				product.setName(tfSearchProduct.getText());
				String query ="select * from productdetails where productName=('"+product.getName().toString()+"')";
				showProductDetails2(query);
				tfSearchProduct.setText("");
			}
		});
		
		bRefresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DefaultTableModel model = (DefaultTableModel)tShowAllProduct.getModel();
				model.setRowCount(0);
				showProductDetails();
			}
		});
				
		bBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e2){
				
				dispose();
				AdminFrame bBack=new AdminFrame();
				bBack.setVisible(true);
			}
		});
		
		bLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				dispose();
				LoginFrame bLogout=new LoginFrame();
				bLogout.setVisible(true);
			}
		});	

	}
	
	public void showProductDetails(){
		ArrayList<Product> list= getProductlist();
		DefaultTableModel model = (DefaultTableModel)tShowAllProduct.getModel();
		Object[] row =new Object[4];
		
		for(int i=0;i<list.size();i++){
			row[0]=list.get(i).getId();
			row[1]=list.get(i).getName();
			row[2]=list.get(i).getUnitPrice();
			row[3]=list.get(i).getQuantity();

			model.addRow(row);
		}
		
	}
	
	public ArrayList <Product> getProductlist(){
		ArrayList <Product> productlist=new ArrayList<Product>();
		Connection conn=ConnectDB();
		String query ="select * from `productdetails` ";
		Statement st;
		ResultSet rs;
			
		try{
			st=(Statement) conn.createStatement();
			rs=st.executeQuery(query);
			Product p;
				
			while(rs.next()){
				p=new Product(rs.getString("productID"),rs.getString("productName"),rs.getDouble("unitPrice"),rs.getDouble("quantity"));
				productlist.add(p);
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return productlist;		
	}
	
	public void showProductDetails2(String query){
		ArrayList<Product> list= getProductlist2(query);
		DefaultTableModel model = (DefaultTableModel)tShowAllProduct.getModel();
		Object[] row =new Object[4];
		
		for(int i=0;i<list.size();i++){
			row[0]=list.get(i).getId();
			row[1]=list.get(i).getName();
			row[2]=list.get(i).getUnitPrice();
			row[3]=list.get(i).getQuantity();

			model.addRow(row);
		}
		
	}

	public ArrayList <Product> getProductlist2(String query){
		ArrayList <Product> productlist=new ArrayList<Product>();
		Statement st;
		ResultSet rs;
			
		try{
			st=(Statement) conn.createStatement();
			rs=st.executeQuery(query);		
			while(rs.next()){
				product=new Product(rs.getString("productID"),rs.getString("productName"),rs.getDouble("unitPrice"),rs.getDouble("quantity"));
				productlist.add(product);
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return productlist;		
	}
	
	public void actionPerformed(ActionEvent x){
		
	}
	
	
}

