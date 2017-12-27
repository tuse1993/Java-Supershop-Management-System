import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class ViewRecords extends JFrame implements ActionListener {
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
	Records records;
	JLabel lViewRecordforDay =new JLabel("View Records for Day (dd/MM/yyyy)");
	JLabel lViewRecordforMonth =new JLabel("View Records for Month (/MM/yyyy)");
	JTextField tfViewRecordforDay =new JTextField();
	JTextField tfViewRecordforMonth =new JTextField();
	JButton bBack=new JButton("Back");
	JButton bLogout=new JButton("Logout");
	JButton bViewDate=new JButton("View");
	JButton bViewMonth=new JButton("View");
	JTable trecords=new JTable();
	
	public ViewRecords(){
	conn=ConnectDB();
	setSize(800,600);
	setVisible(true);
	setLayout(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	JPanel contentPane=new JPanel();
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JScrollPane js=new JScrollPane();
	js.setBounds(50,160,650,300);
	contentPane.add(js);
	
	trecords.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Total Sold", "Total Vat"
			}
		));
	
	
	trecords.getTableHeader().setReorderingAllowed(false);
	js.setViewportView(trecords);
	trecords.setDefaultEditor(Object.class, null);
	
	add(bBack);
	bBack.setBounds(50,500,100,30);
	add(bLogout);
	bLogout.setBounds(630,500,100,30);
	add(lViewRecordforDay);
	lViewRecordforDay.setBounds(50, 50, 200, 50);
	add(lViewRecordforMonth);
	lViewRecordforMonth.setBounds(50, 100, 200, 50);
	add(tfViewRecordforDay);
	tfViewRecordforDay.setBounds(270, 65,130, 25);
	add(tfViewRecordforMonth);
	tfViewRecordforMonth.setBounds(270,115,130, 25);
	add(bViewDate);
	bViewDate.setBounds(420, 65,80,25);
	add(bViewMonth);
	bViewMonth.setBounds(420, 115,80,25);
	
	bViewDate.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e2){
			 records= new Records();
			 records.setSellDate(tfViewRecordforDay.getText());
			 String query ="SELECT * FROM dail WHERE selldate = ('"+records.getSellDate().toString()+"')";
			 showProductDetails(query,records.getSellDate());
		}
	});
	
	bViewMonth.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e2){
			 records= new Records();
			 records.setSellDate(tfViewRecordforMonth.getText());
			 String query ="select * from dail where selldate like '%" + records.getSellDate().toString() + "' ";
			 showProductDetails(query,records.getSellDate());
			
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
	public void showProductDetails(String query,String dm){
		ArrayList<Records> list= getRecordlist(query);
		DefaultTableModel model = (DefaultTableModel)trecords.getModel();
		Object[] row =new Object[3];
		double sumTotal=0.0;
		double sumVat=0.0;
		for(int i=0;i<list.size();i++){
			sumTotal=list.get(i).getTotalSell()+sumTotal;
			sumVat=list.get(i).getTotalVat()+sumVat;
			
		}
		row[0]=dm;
		row[1]=sumTotal;
		row[2]=sumVat;

		model.addRow(row);
	}

	public ArrayList <Records> getRecordlist(String query){
		ArrayList <Records> recordlist=new ArrayList<Records>();
		Statement st;
		ResultSet rs;	
		try{
			st=(Statement) conn.createStatement();
			rs=st.executeQuery(query);		
			while(rs.next()){
				records=new Records(rs.getString("selldate"),rs.getDouble("totalsell"),rs.getDouble("totalvat"));
				recordlist.add(records);
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return recordlist;		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
