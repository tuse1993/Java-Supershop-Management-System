import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import com.mxrck.autocompleter.TextAutoCompleter;

class EditProduct extends JFrame implements ActionListener{
	
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
	Connection conn=null;
	Product product;
	
	JLabel lEditProduct=new JLabel("Edit Product");
	JLabel lEnterProductName=new JLabel("Enter Product Name:");
	JLabel lProductId=new JLabel("Product Id:");
	JLabel lProductName=new JLabel("Product Name:");
	JLabel lUnitPrice=new JLabel("UnitPrice:");
	JLabel lStock=new JLabel("Stock:");
	JButton bSearchProduct=new JButton("Search Product");
	JButton bLogout=new JButton("Logout");
	JButton bBack=new JButton("Back");
	JButton bEditId=new JButton("Update");
	JButton bEditName=new JButton("Update");
	JButton bEditUnitPrice=new JButton("Update");
	JButton bEditQuantity=new JButton("Update");
	
	JTextField tfEnterProductName=new JTextField();
	JTextField tfProductId=new JTextField();
	JTextField tfProductName=new JTextField();
	JTextField tfUnitPrice=new JTextField();
	JTextField tfStock=new JTextField();
	
	JTable tshowProduct=new JTable();
	
	public EditProduct(){
		conn=ConnectDB();
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane ScrollPane=new JScrollPane();
		ScrollPane.setBounds(60,150,650,39);
		contentPane.add(ScrollPane);
		
		tshowProduct.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Product ID", "Product Name", "UnitPrice", "Stock"
				}
			));
		
		
		tshowProduct.getTableHeader().setReorderingAllowed(false);
		ScrollPane.setViewportView(tshowProduct);
		tshowProduct.setDefaultEditor(Object.class, null);
		
		add(lEditProduct);
		lEditProduct.setBounds(350,20,100,30);
		add(lEnterProductName);
		lEnterProductName.setBounds(60,70,120,30);
		add(lProductId);
		lProductId.setBounds(60,250,100,30);
		add(lProductName);
		lProductName.setBounds(60,290,100,30);
		add(lUnitPrice);
		lUnitPrice.setBounds(60,330,100,30);
		add(lStock);
		lStock.setBounds(60,370,100,30);
		add(tfEnterProductName);
		tfEnterProductName.setBounds(60,100,150,20);
		add(tfProductId);
		tfProductId.setBounds(180,254,150,20);
		add(tfProductName);
		tfProductName.setBounds(180,294,150,20);
		add(tfUnitPrice);
		tfUnitPrice.setBounds(180,334,150,20);
		add(tfStock);
		tfStock.setBounds(180,374,150,20);
		add(bSearchProduct);
		bSearchProduct.setBounds(230,100,150,20);
		add(bLogout);
		bLogout.setBounds(630,500,100,30);
		add(bBack);
		bBack.setBounds(50,500,100,30);
		add(bEditId);
		bEditId.setBounds(350,254,100,20);
		add(bEditName);
		bEditName.setBounds(350,294,100,20);
		add(bEditUnitPrice);
		bEditUnitPrice.setBounds(350,334,100,20);
		add(bEditQuantity);
		bEditQuantity.setBounds(350,374,100,20);
		
		
		TextAutoCompleter complete=new TextAutoCompleter(tfEnterProductName);
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
				try{
					DefaultTableModel model = (DefaultTableModel)tshowProduct.getModel();
					model.setRowCount(0);
					product=new Product();
					product.setName(tfEnterProductName.getText());
					String query ="select * from productdetails where productName=('"+product.getName().toString()+"')";
					showProductDetails(query);
				}
				catch(Exception ac){
					ac.printStackTrace();
				}
			}
		});
		
		bLogout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				LoginFrame bLogout=new LoginFrame();
				bLogout.setVisible(true);
			}
		});
		
		bBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				AdminFrame bBack=new AdminFrame();
				bBack.setVisible(true);
			}
		});
			
		bEditId.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				String sql="update productdetails set productID=? where productName=?";
				String sql2="Select * from productdetails where productID=?";
				PreparedStatement pst,pst2;
				ResultSet rs;
				product=new Product();
				try{
					product.setId(tfProductId.getText());
					pst2 = (PreparedStatement) conn.prepareStatement(sql2);
					pst2.setString(1,product.getId());
					rs=pst2.executeQuery();
				    if(!product.getId().equals("")&& rs.next()){
				    	JOptionPane.showMessageDialog(null,"Product ID Is Already Used");
				    	tfEnterProductName.setText("");
						tfProductId.setText("");
						tfProductName.setText("");
						EditProduct bUpdate=new EditProduct();
						bUpdate.setVisible(true);
				    }
				    
				    else{
				    	pst = (PreparedStatement) conn.prepareStatement(sql); 
					   
					    product.setName(tfProductName.getText());
					  
						pst.setString(1,product.getId());
						pst.setString(2,product.getName());
						
						pst.executeUpdate();
						
						DefaultTableModel model = (DefaultTableModel)tshowProduct.getModel();
						model.setRowCount(0);
						tfEnterProductName.setText("");
						tfProductId.setText("");
						tfProductName.setText("");
						JOptionPane.showMessageDialog(null,"Product ID Updated");
						EditProduct bUpdate=new EditProduct();
						bUpdate.setVisible(true);
				    }
					
				}
				catch(Exception ee){
					ee.printStackTrace();
				}
				
			}
		});	
		bEditName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				String sql="update productdetails set productName=? where productID=?";
				PreparedStatement pst;
				product=new Product();
				try{
				    pst = (PreparedStatement) conn.prepareStatement(sql); 
				    product.setName(tfProductName.getText());
					product.setId(tfProductId.getText());

					pst.setString(1,product.getName());
					pst.setString(2,product.getId());
					
					pst.executeUpdate();
					
					DefaultTableModel model = (DefaultTableModel)tshowProduct.getModel();
					model.setRowCount(0);
					tfEnterProductName.setText("");
					tfProductId.setText("");
					tfProductName.setText("");
					JOptionPane.showMessageDialog(null,"Product Name Updated");
					EditProduct bUpdate=new EditProduct();
					bUpdate.setVisible(true);
				}
				catch(Exception ee){
					ee.printStackTrace();
				}
				
			}
		});	
		
		bEditUnitPrice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				String sql="update productdetails set unitPrice=? where productName=?";
				PreparedStatement pst;
				product=new Product();
				try{
					Double up;
				    pst = (PreparedStatement) conn.prepareStatement(sql); 
				    product.setName(tfProductName.getText());
					up=Double.parseDouble(tfUnitPrice.getText());
					product.setUnitPrice(up);
					
					pst.setDouble(1,product.getUnitPrice());
					pst.setString(2,product.getName());
					
					pst.executeUpdate();
					
					DefaultTableModel model = (DefaultTableModel)tshowProduct.getModel();
					model.setRowCount(0);
					tfEnterProductName.setText("");
					tfProductName.setText("");
					tfUnitPrice.setText("");
					JOptionPane.showMessageDialog(null,"Product Unit Price Updated");
					EditProduct bUpdate=new EditProduct();
					bUpdate.setVisible(true);
				}
				catch(Exception ee){
					ee.printStackTrace();
				}
				
			}
		});	
		
		bEditQuantity.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				String sql="update productdetails set quantity=? where productName=?";
				PreparedStatement pst;
				product=new Product();
				try{
					Double quantity;
				    pst = (PreparedStatement) conn.prepareStatement(sql); 
				    product.setName(tfProductName.getText());
					quantity=Double.parseDouble(tfStock.getText());
					product.setQuantity(quantity);

					pst.setDouble(1,product.getQuantity());
					pst.setString(2,product.getName());
					
					pst.executeUpdate();
					
					DefaultTableModel model = (DefaultTableModel)tshowProduct.getModel();
					model.setRowCount(0);
					tfEnterProductName.setText("");
					tfProductName.setText("");
					tfUnitPrice.setText("");
					JOptionPane.showMessageDialog(null,"Product Quantity Updated");
					EditProduct bUpdate=new EditProduct();
					bUpdate.setVisible(true);
				}
				catch(Exception ee){
					ee.printStackTrace();
				}
				
			}
		});	

	}
	
	public void showProductDetails(String query){
		ArrayList<Product> list= getProductlist(query);
		DefaultTableModel model = (DefaultTableModel)tshowProduct.getModel();
		Object[] row =new Object[4];
		
		for(int i=0;i<list.size();i++){
			row[0]=list.get(i).getId();
			tfProductId.setText(list.get(i).getId());
			
			row[1]=list.get(i).getName();
			tfProductName.setText(list.get(i).getName());
			
			row[2]=list.get(i).getUnitPrice();
			String unitPrice = Double.toString(list.get(i).getUnitPrice());
			tfUnitPrice.setText(unitPrice);
			
			row[3]=list.get(i).getQuantity();
			String quantity = Double.toString(list.get(i).getQuantity());
			tfStock.setText(quantity);

			model.addRow(row);
		}
		
	}
	
	
	public ArrayList <Product> getProductlist(String query){
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


