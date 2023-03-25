package reseturant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Restaurant {

	private String resetName;
	int workersCount = 0;
	private double restaurantCash = 0;
	private ArrayList<Workers> workers = new ArrayList<Workers>();
	private ArrayList<Table> tables = new ArrayList<Table>();
	private Menu menu;
	private Manager reseturantManeger;
	

	public Restaurant(String resetName) {
		this.resetName = resetName;

		createTables(16, 4);// add tables

		waiters(4);// add waiters
		cookers(3);// add cookers


		this.menu = new Menu();
		this.menu.addDish(new Dish("Pizza", 40, 10));
		this.menu.addDish(new Dish("Pasta", 40, 10));
		reseturantManeger = new Manager(7000, "Mosh", menu, workers, tables);// add manager
		System.out.println(reseturantManeger.getShift().getShiftCash());

	}

	private void createTables(int num, int numOfsits) {
		for (int i = 0; i < num; i++) {
			this.tables.add(new Table(tables.size()+1, numOfsits));
		}
	}

	private void waiters(int num) {
		for (int i = 0; i < num; i++) {
			workers.add(new Waiter(2500, "waiters" + workers.size()));
		}
	}

	private void cookers(int num) {
		for (int i = 0; i < num; i++) {
			workers.add(new Cooker(3000, "cooker" + workers.size()));
		}
	}



	public String getResetName() {
		return resetName;
	}

	public void setResetName(String resetName) {
		this.resetName = resetName;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;

	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public double getRestaurantCash() {
		return restaurantCash;
	}

	public void setRestaurantCash(double restaurantCash) {
		this.restaurantCash = restaurantCash;
	}

}
