public class Records {
	private String sellDate;
	private double totalSell;
	private double totalVat;
	
	public Records(){
		sellDate="null";
		totalSell=0.0;
		totalVat=0.0;
		
	}
	
	public Records(String a, double b, double c){
		sellDate=a;
		totalSell=b;
		totalVat=c;
		
	}
	
	
	
	public void setSellDate(String n){ sellDate = n; }
	public void setTotalSell(double n){ totalSell = n; }
	public void setTotalVat(double n){ totalVat = n; }
	
	public String getSellDate(){ return sellDate; }
	public double getTotalSell(){ return totalSell; }
	public double getTotalVat(){ return totalVat; }
	
}
