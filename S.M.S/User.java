public class User {
	private String id;
	private String password;
	private String name;
	private String contact_no;
	private String email;
	private String address;
	private String date_of_joining;
	
	public User(){
		id="";
		password="";
		name="";
		contact_no="";
		email="";
		address="";
		date_of_joining="";
	}
	
	public User(String id, String password, String name, String contact_no, String email, String address, String date_of_joining){
		this.id=id;
		this.password=password;
		this.name=name;
		this.contact_no=contact_no;
		this.email=email;
		this.address=address;
		this.date_of_joining=date_of_joining;
	}
	
	public User(String id, String name, String contact_no, String email, String address, String date_of_joining){
		this.id=id;
		this.name=name;
		this.contact_no=contact_no;
		this.email=email;
		this.address=address;
		this.date_of_joining=date_of_joining;
	}
	
	public void setid(String n){ id = n; }
	public void setpassword(String n){ password = n; }
	public void setname(String n){ name = n; }
	public void setcontact_no(String n){ contact_no = n; }
	public void setemail(String n){ email = n; }
	public void setaddress(String n){ address = n; }
	public void setdate_of_joining(String n){ date_of_joining = n; }
	
	public String getid(){ return this.id; }
	public String getpassword(){ return this.password; }
	public String getname(){ return this.name; }
	public String getcontact_no(){ return this.contact_no; }
	public String getemail(){ return this.email; }
	public String getaddress(){ return this.address; }
	public String getdate_of_joining(){ return this.date_of_joining; }
	
	
		
}
