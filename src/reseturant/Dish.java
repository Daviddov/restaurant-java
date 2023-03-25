package reseturant;

public class Dish {
	private String name;
	private double price;
	private int amount;
	private boolean available;
    private boolean isRady = false;
    private boolean isDone = false;
    
	public Dish(String name, int price, int amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.available = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isRady() {
		return isRady;
	}

	public void setRady(boolean isRady) {
		this.isRady = isRady;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}
