import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import com.mxrck.autocompleter.TextAutoCompleter;

class EmployeeFrame extends JFrame implements ActionListener{
	
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
	Records records;
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	
	JLabel l1=new JLabel("Product Cart");
	JLabel l2=new JLabel("Enter Product Name:");
	JLabel l3=new JLabel("Add Quantity:");
	
	JLabel l5=new JLabel("Update Quantity:");
	JLabel l7=new JLabel("Total:");
	JLabel l8=new JLabel("VAT:");
	JLabel l9=new JLabel("Grand Total:");
	JLabel lTotal=new JLabel();
	JLabel lVat=new JLabel();
	JLabel lGrandTotal=new JLabel();

	JButton bAddToCart=new JButton("Add To Cart");
	JButton bLogout=new JButton("Logout");
	
	JButton bUpdate=new JButton("Update");
	JButton bRemove=new JButton("Remove");
	JButton bCheckout=new JButton("CheckOut");
	JButton bSearchProduct=new JButton("Search Product");
	JButton bRefresh=new JButton("Refresh");
	
	JTextField tfProductName=new JTextField();
	JTextField tfAddQuantity=new JTextField();
	JTextField tEditQuantity=new JTextField();
	
	
	JTable cartTable=new JTable();
	JTable tShowAllProduct=new JTable();
	
	public EmployeeFrame(){
		conn=ConnectDB();
		
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane js=new JScrollPane();
		js.setBounds(60,360,650,150);
		contentPane.add(js);
		
		cartTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Product ID", "Product Name", "UnitPrice", "Quantity", "Amount"
				}
			));
		
		
		cartTable.getTableHeader().setReorderingAllowed(false);
		js.setViewportView(cartTable);
		cartTable.setDefaultEditor(Object.class, null);
		
	    //Create Show All Product Table
		
		JScrollPane jScrollPane2=new JScrollPane();
		jScrollPane2.setBounds(60,110,650,150);
		contentPane.add(jScrollPane2);
		
		tShowAllProduct.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Product ID", "Product Name", "UnitPrice", "Stock"
				}
			));
		
		tShowAllProduct.getTableHeader().setReorderingAllowed(false);
		jScrollPane2.setViewportView(tShowAllProduct);
		tShowAllProduct.setDefaultEditor(Object.class, null);
		
		add(l1);
		l1.setBounds(350,10,100,30);
		add(l2);
		l2.setBounds(60,40,120,30);
		add(l3);
		l3.setBounds(390,265,80,30);
		add(l5);
		l5.setBounds(390,293,100,30);
		add(l7);
		l7.setBounds(550,510,100,30);
		add(l8);
		l8.setBounds(550,542,100,30);
		add(l9);
		l9.setBounds(510,570,100,30);
		add(lTotal);
		lTotal.setBounds(660,510,100,30);
		add(lVat);
		lVat.setBounds(660,542,100,30);
		add(lGrandTotal);
		lGrandTotal.setBounds(660,570,100,30);
		
		add(tfProductName);
		tfProductName.setBounds(60,75,150,20);
		add(tfAddQuantity);
		tfAddQuantity.setBounds(500,271,100,20);
		add(tEditQuantity);
		tEditQuantity.setBounds(500,300,100,20);
		add(bAddToCart);
		bAddToCart.setBounds(610,270,100,20);
		add(bLogout);
		bLogout.setBounds(650,620,100,30);
		
		
		add(bUpdate);
		bUpdate.setBounds(610,300,100,20);
		add(bRemove);
		bRemove.setBounds(610,330,100,20);
		add(bCheckout);
		bCheckout.setBounds(320,620,100,30);
		add(bSearchProduct);
		bSearchProduct.setBounds(220,75,130,20);
		add(bRefresh);
		bRefresh.setBounds(360,75,100,20);
		
		showProductDetails();
		
		TextAutoCompleter complete=new TextAutoCompleter(tfProductName);
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
		
		bAddToCart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				product=new Product();
				try{
					int i = tShowAllProduct.getSelectedRow();
					String pid= (String) tShowAllProduct.getValueAt(i, 0);
					product.setId(pid);
					String query ="select * from productdetails where productID=('"+product.getId()+"')";
					showProductDetails3(query);
					tfProductName.setText("");
					tfAddQuantity.setText("");
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
		
			
		bUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				product=new Product();
				double unitPrice;
				double cartQuantity;
				double updateQuantity;
				double diffQuantity;
				try{
					int i = cartTable.getSelectedRow();
					Double s= (Double) cartTable.getValueAt(i, 4);
					cartQuantity=(double) cartTable.getValueAt(i, 3);
					product.minusTotal(s);
					if(i >= 0){
						double editQuantity=Double.parseDouble(tEditQuantity.getText());
						cartTable.setValueAt(editQuantity, i, 3);
					}
					unitPrice=(double)cartTable.getValueAt(i, 2);	
					updateQuantity= Double.parseDouble(tEditQuantity.getText());	
					
					String pid= (String) cartTable.getValueAt(i, 0);
					product.setId(pid);
					double price= product.calPrice(unitPrice, updateQuantity);
					cartTable.setValueAt(price, i, 4);
					double calTotal=product.calTotal(price);
					String calTotalx=Double.toString(calTotal);
					lTotal.setText(calTotalx);
					String vatTotalx=Double.toString(product.vatTotal());
					lVat.setText(vatTotalx);
					String gTotal=Double.toString(product.gTotal());
					lGrandTotal.setText(gTotal);
						
					if(cartQuantity>updateQuantity){
						diffQuantity=cartQuantity-updateQuantity;
						Statement st;
						ResultSet rs;
						String query ="select quantity from productdetails where productID=('"+product.getId()+"')";
						st=(Statement) conn.createStatement();
						rs=st.executeQuery(query);
						Double productStock=0.0;
	
						if(rs.next()){
							productStock=rs.getDouble("quantity");
						}
							
						double finalProductStock= productStock + diffQuantity;
						String sql="UPDATE productdetails SET quantity='"+finalProductStock+"' WHERE productID='"+product.getId()+"' ";
						st = (Statement) conn.prepareStatement(sql); 
						st.execute(sql);
						}
					else{
						diffQuantity=updateQuantity-cartQuantity;
					
						Statement st;
						ResultSet rs;	
						String query ="select quantity from productdetails where productID=('"+product.getId()+"')";
						st=(Statement) conn.createStatement();
						rs=st.executeQuery(query);
						Double productStock=0.0;
							
						if(rs.next()){
							productStock=rs.getDouble("quantity");
						}
							
						double finalProductStock=productStock-diffQuantity;
							
						String sql="UPDATE productdetails SET quantity='"+finalProductStock+"' WHERE productID='"+product.getId()+"' ";
						st = (Statement) conn.prepareStatement(sql); 	
						st.execute(sql);
						}
						
					 tEditQuantity.setText("");					
				}
				
				catch(Exception ac){
					ac.printStackTrace();
				}
			}
		});
		
		bRemove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				product=new Product();
				try{	
					tfProductName.setText("");
					tfAddQuantity.setText("");
					tEditQuantity.setText("");
					DefaultTableModel model = (DefaultTableModel)cartTable.getModel();
					int selRow = cartTable.getSelectedRow();
					
					double selectedRowAmount= (Double) cartTable.getValueAt(selRow, 4);
					double updatedGrandTotal=product.minusTotal(selectedRowAmount);
					String calTotalx=Double.toString(updatedGrandTotal);
					lTotal.setText(calTotalx);
					
					String vatTotalx=Double.toString(product.vatTotal());
					lVat.setText(vatTotalx);
					
					String gTotal=Double.toString(product.gTotal());
					lGrandTotal.setText(gTotal);
					
					double quantity=(Double)cartTable.getValueAt(selRow, 3);
					String pid= (String) cartTable.getValueAt(selRow, 0);
					product.setId(pid);
					if(selRow != -1) {
		                model.removeRow(selRow);
		            }
					
					Statement st;
					ResultSet rs;
					
					String query ="select quantity from productdetails where productID=('"+product.getId()+"')";
					st=(Statement) conn.createStatement();
					rs=st.executeQuery(query);
					Double productStock=0.0;
					
					if(rs.next()){
						productStock=rs.getDouble("quantity");
					}
					
					productStock=productStock+quantity;
					
					String sql="UPDATE productdetails SET quantity='"+productStock+"' WHERE productID='"+product.getId()+"' ";
					st = (Statement) conn.prepareStatement(sql);
				    st.execute(sql);
				}
				catch(Exception ac){
					ac.printStackTrace();
				}
			}
		});
		
		bCheckout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				records=new Records();
				JOptionPane.showMessageDialog(null,"Total: "+lTotal.getText()+"\nVat: "+lVat.getText()+"\nGrand Total: "+lGrandTotal.getText() );
				String sql="insert into dail values(?,?,?)";
				
				try{
				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
				records.setSellDate(dateFormat.format(date));
				double total=Double.parseDouble(lTotal.getText());
				records.setTotalSell(total);
				double vat=Double.parseDouble(lVat.getText());
				records.setTotalVat(vat);
	
				pst.setString(1,records.getSellDate());
				pst.setDouble(2,records.getTotalSell());
			    pst.setDouble(3,records.getTotalVat());
			    
		        pst.executeUpdate();
				}
				catch(Exception ac){
					ac.printStackTrace();
				}
				
				EmployeeFrame bCheckout=new EmployeeFrame();
				bCheckout.setVisible(true);
			}
		});
		
		bSearchProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DefaultTableModel model = (DefaultTableModel)tShowAllProduct.getModel();
				model.setRowCount(0);
				product=new Product();
				product.setName(tfProductName.getText());
				String query ="select * from productdetails where productName=('"+product.getName().toString()+"')";
				showProductDetails2(query);
				tfProductName.setText("");
			}
		});
		
		bRefresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DefaultTableModel model = (DefaultTableModel)tShowAllProduct.getModel();
				model.setRowCount(0);
				showProductDetails();
				tfProductName.setText("");
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
	
	public void showProductDetails3(String query){
		ArrayList<Product> list= getProductlist2(query);
		DefaultTableModel model = (DefaultTableModel)cartTable.getModel();
		Object[] row =new Object[6];
		double a= Double.parseDouble(tfAddQuantity.getText());
	    double b=0.0;
	    String pid="";
		Product p=new Product();
		p.setQuantityOnCart(a);
		if(list.size()==0){
			JOptionPane.showMessageDialog(null,"Wrong Product Name\nCheck Product Name Again ");
		}
		else{
			for(int i=0;i<list.size();i++){
				
				pid=list.get(i).getId();
				row[0]=pid;
				row[1]=list.get(i).getName();
				row[2]=list.get(i).getUnitPrice();
				row[3]=a;
				double total=p.calPrice(list.get(i).getUnitPrice(),p.getQuantityOnCart());
				row[4]=total;
				
				b=list.get(i).getQuantity();
						
				double calTotal=p.calTotal(total);
				String calTotalx=Double.toString(calTotal);
				lTotal.setText(calTotalx);
		
				String vatTotalx=Double.toString(p.vatTotal());
				lVat.setText(vatTotalx);
				
				String gTotal=Double.toString(p.gTotal());
				lGrandTotal.setText(gTotal);
				
				model.addRow(row);	
			}
			double c=b-a;
			String sql="UPDATE productdetails SET quantity='"+c+"' WHERE productID='"+pid+"' ";
			Statement st;
			try {
				st = (Statement) conn.prepareStatement(sql); 
			    st.execute(sql);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}
	
	public void actionPerformed(ActionEvent x){
		
	}
	

	 public static void main(String[] a){
		EmployeeFrame m=new EmployeeFrame();
	}
	 
}


