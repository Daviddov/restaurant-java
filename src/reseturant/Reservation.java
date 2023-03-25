package reseturant;

import java.util.ArrayList;

public class Reservation {
	private ArrayList<Dish> dishes = new ArrayList<>();
	private int tableNum;
	private double totalPrice = 0;

public Reservation(int tableNum) {
	this.setTableNum(tableNum);
}


	public ArrayList<Dish> getDishes() {
		return dishes;
	}

	


	public int getTableNum() {
		return tableNum;
	}


	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}


	public double getTotalPrice() {
		calculationTotalPrice();
		return totalPrice;
	}
private void calculationTotalPrice() {
	for (int i = 0; i < dishes.size(); i++) {
	if(	dishes.get(i).isDone()) {
		this.totalPrice+= dishes.get(i).getPrice();
	}
	}
}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public void setDishes(ArrayList<Dish> reservationDishes) {
	this.dishes =reservationDishes;
		
	}


	
	
}
