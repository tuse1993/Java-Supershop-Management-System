import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mxrck.autocompleter.TextAutoCompleter;

class DeleteProduct extends JFrame implements ActionListener{	
	
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
	
	Product product;
	Connection conn=null;
	
	JLabel lDeleteProdcut=new JLabel("Delete Product");
	
	JButton bSearchProduct=new JButton("Search Product");
	JButton bLogout=new JButton("Logout");
	JButton bBack=new JButton("Back");
	JButton bDelete=new JButton("Delete");
	
	JTextField tfEnterProductId=new JTextField();

	JTable tshowProduct=new JTable();

	public DeleteProduct(){
		conn=ConnectDB();
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane js=new JScrollPane();
		js.setBounds(60,150,650,39);
		contentPane.add(js);
	
		tshowProduct.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Product ID", "Product Name", "UnitPrice", "Stock"
				}
			));
		
		tshowProduct.getTableHeader().setReorderingAllowed(false);
		js.setViewportView(tshowProduct);
		tshowProduct.setDefaultEditor(Object.class, null);
		
		add(lDeleteProdcut);
		lDeleteProdcut.setBounds(350,20,100,30);
		add(tfEnterProductId);
		tfEnterProductId.setBounds(60,100,150,20);
		add(bSearchProduct);
		bSearchProduct.setBounds(230,100,150,20);
		add(bLogout);
		bLogout.setBounds(630,500,100,30);
		add(bBack);
		bBack.setBounds(50,500,100,30);
		add(bDelete);
		bDelete.setBounds(300,300,100,30);
		
		TextAutoCompleter complete=new TextAutoCompleter(tfEnterProductId);
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
				product=new Product();
				try{
					DefaultTableModel model = (DefaultTableModel)tshowProduct.getModel();
					model.setRowCount(0);
					product.setName(tfEnterProductId.getText());
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
			
		bDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				PreparedStatement pst;
				String sql="delete from productdetails where productName=?";
				product=new Product();
				try{
					pst=(PreparedStatement)conn.prepareStatement(sql); 
					product.setName(tfEnterProductId.getText());
					pst.setString(1,product.getName());						
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Product Deleted");
					DeleteProduct bbDelete = new DeleteProduct();
					bbDelete.setVisible(true);
				}
				catch(Exception ex){
							ex.printStackTrace();
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
			row[1]=list.get(i).getName();
			//JOptionPane.showMessageDialog(null, list.get(i).getName());
			row[2]=list.get(i).getUnitPrice();
			row[3]=list.get(i).getQuantity();

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
			product=new Product();		
			while(rs.next()){
				product=new Product(rs.getString("productID"),rs.getString("productName"),rs.getDouble("unitPrice"),rs.getDouble("quantity"));
				//JOptionPane.showMessageDialog(null, rs.getString("productID"));
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


