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

class DeleteUser extends JFrame implements ActionListener{	
	
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
	
	//Employee employee;
	User user;

	Connection conn=null;
	
	JLabel lDeleteUser=new JLabel("Delete User");
	
	JButton bSearchUser=new JButton("Search User");
	JButton bLogout=new JButton("Logout");
	JButton bBack=new JButton("Back");
	JButton bDelete=new JButton("Delete");
	
	JTextField tfEnterUserId=new JTextField();

	JTable tshowUser=new JTable();

	public DeleteUser(){
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
	
		tshowUser.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"User ID", "Password", "User Name", "Contact No","Email",
					"Address", "Date Of Joining"
				}
			));
		
		tshowUser.getTableHeader().setReorderingAllowed(false);
		js.setViewportView(tshowUser);
		tshowUser.setDefaultEditor(Object.class, null);
		
		add(lDeleteUser);
		lDeleteUser.setBounds(350,20,100,30);
		add(tfEnterUserId);
		tfEnterUserId.setBounds(60,100,150,20);
		add(bSearchUser);
		bSearchUser.setBounds(230,100,150,20);
		add(bLogout);
		bLogout.setBounds(630,500,100,30);
		add(bBack);
		bBack.setBounds(50,500,100,30);
		add(bDelete);
		bDelete.setBounds(300,300,100,30);
			
		bSearchUser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//employee=new Employee();
				user = new User();
				try{
					DefaultTableModel model = (DefaultTableModel)tshowUser.getModel();
					model.setRowCount(0);
					user.setid(tfEnterUserId.getText());
					//JOptionPane.showMessageDialog(null, employee.getid().toString());
					String query ="select * from userlogin where id=('"+user.getid().toString()+"')";
					showEmployeeDetails(query);	
					//JOptionPane.showMessageDialog(null, query);
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
				String sql="delete from userlogin where id=?";
				//employee=new Employee();
				user = new User();
				
				try{
					pst=(PreparedStatement)conn.prepareStatement(sql); 
					user.setid(tfEnterUserId.getText());
					pst.setString(1,user.getid());						
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"User Deleted");
					DeleteUser bDelete = new DeleteUser();
					bDelete.setVisible(true);
				}
				catch(Exception ex){
							ex.printStackTrace();
				}	
			}
		});
	}
	
	public void showEmployeeDetails(String query){
		//JOptionPane.showMessageDialog(null, query);
		ArrayList<User> list = getEmployeelist(query);
		//JOptionPane.showMessageDialog(null, getEmployeelist(query));
		DefaultTableModel model = (DefaultTableModel)tshowUser.getModel();
		Object[] row =new Object[7];
		
		for(int i=0;i<list.size();i++){
			row[0]=list.get(i).getid();
			row[1]=list.get(i).getpassword();
			row[2]=list.get(i).getname();
			//JOptionPane.showMessageDialog(null, list.get(i).getname());
			row[3]=list.get(i).getcontact_no();
			row[4]=list.get(i).getemail();
			row[5]=list.get(i).getaddress();
			row[6]=list.get(i).getdate_of_joining();

			model.addRow(row);
		}
	}
	
	public ArrayList <User> getEmployeelist(String query){
		ArrayList <User> Employeelist = new ArrayList<User>();
		Statement st;
		ResultSet rs;	
		try{
			st=(Statement) conn.createStatement();
			rs=st.executeQuery(query);
			//employee=new Employee();
			user = new User();

			while(rs.next()){
				user=new User(rs.getString("id"),rs.getString("password"),rs.getString("name"),rs.getString("contact_no"),rs.getString("email"),rs.getString("address"),rs.getString("date_of_joining"));
				//JOptionPane.showMessageDialog(null, rs.getString("id"));
				Employeelist.add(user);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return Employeelist;		
	}
	
	public void actionPerformed(ActionEvent x){
		
	}
	
	
}


