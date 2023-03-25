package reseturant;

public class Customer extends Person{
private String name;
	private boolean customerSeat;
	
	public Customer(String name) {
		super(name);
		this.name = name;
		this.setCustomerSeat(false);
	}

	public boolean isCustomerSeat() {
		return customerSeat;
	}

	public void setCustomerSeat(boolean customerSeat) {
		this.customerSeat = customerSeat;
	}
	

}
