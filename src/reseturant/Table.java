package reseturant;

public class Table {

	private int tableNumber;
	private int sitsNumber;
	private Waiter waiter;

	private boolean didReservation = false;
	private boolean available;
	private Customer[] sitsCustomers; 
	private Reservation reservation;
	
	
	public Table(int tableNumber, int numOfsits) {
		this.tableNumber = tableNumber;
		this.available = true;
		this.sitsNumber = numOfsits;
		this.sitsCustomers = new Customer[sitsNumber]; 
		this.reservation = new Reservation(tableNumber);
	}

	public int getSitsNumber() {
		return sitsNumber;
	}
	
	public void setSitNumber(int sitNumber) {
		this.sitsNumber = sitNumber;
	}
	
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public Customer[] getSitsCustomers() {
		return sitsCustomers;
	}

	public void setsitsCustomers(Customer[] sitsCustomers) {
		this.sitsCustomers = sitsCustomers;
	}
	public Waiter getWaiter() {
		return waiter;
	}
	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	
	public boolean isDidReservation() {
		return didReservation;
	}
	public void setDidReservation(boolean didReservation) {
		this.didReservation = didReservation;
	}
	
	public void cleanTable() {
		this.available = true;
		this.didReservation = false;
		this.sitsCustomers = null;
		this.reservation = new Reservation(this.tableNumber);
System.out.println("table " +this.tableNumber +" is clean");
	}

}
