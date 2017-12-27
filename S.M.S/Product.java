public class Product{
	private String p_name;
	private String p_id;
	private Double p_unitPrice;
	private Double p_quantity;
	private Double p_quantity_on_cart;
	private static double total=0.0; 
	private static double vat=0.0; 
	private static double gTotal=0.0; 
	
	public Product(){
		p_name="";
		p_id="0";
		p_unitPrice=0.0;
		p_quantity=0.0;
	}

	public Product(String p_id ,String p_name ,Double p_unitPrice,Double p_quantity){
		this.p_name=p_name;
		this.p_id=p_id;
		this.p_unitPrice=p_unitPrice;
		this.p_quantity=p_quantity;
	}
	
	public Product(String p_id ,String p_name ,Double p_unitPrice){
		this.p_name=p_name;
		this.p_id=p_id;
		this.p_unitPrice=p_unitPrice;
	
	}
	public void setName(String p_name){
		this.p_name=p_name;
	}
	
	public void setId(String p_id){
		this.p_id=p_id;
	}
	
	public void setUnitPrice(Double p_unitPrice){
		this.p_unitPrice=p_unitPrice;
	}
	
	public void setQuantity(Double p_quantity){
		this.p_quantity=p_quantity;
	}
	
	public void setQuantityOnCart(Double p_quantity_on_cart){
		this.p_quantity_on_cart=p_quantity_on_cart;
	}
	public String getName(){
		return p_name;
	}
	
	public String getId(){
		return p_id;
	}
	
	public Double getUnitPrice(){
		return p_unitPrice;
	}
	
	public Double getQuantity(){
		return p_quantity;
	}
	
	public double getQuantityOnCart(){
		return p_quantity_on_cart;
	}
	
	public double calPrice(double unitPrice,double quantity){
		double a = unitPrice*quantity;
		return a;
	}
	public double calTotal(double x){
		total=total+x;
		return total; 
	}
	public double vatTotal(){
		vat=(total*.15);
		return vat; 
	}
	public double gTotal(){
		gTotal=total+vat;
		return gTotal; 
	}
	
	public double minusTotal(double y){
		total=total-y;
		return total;
	}
	
}


