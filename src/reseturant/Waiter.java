package reseturant;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Waiter extends Workers {
	private ArrayList<Dish> newResavtion = new ArrayList<Dish>();
	public Waiter(int salary, String name) {
		super(salary, name);

	}

	public void waiterMenu(ArrayList<Table> tables, Menu menu, Queue<Reservation> allReservations, ArrayList<Reservation> radyToTake, double shiftCash) {
//		Waiter waiter = chooseWaiter();
		Scanner in = new Scanner(System.in);
		if (haveSittingCostumer(this.getName(), tables)) {
			System.out.println(
					" 1. make resavetion \n 2.show rady dishes \n 3. take dishes to table \n 4.end diner \n 5. exit");
			int input = in.nextInt();
			handleWaiterChois( input,  tables,  menu, allReservations, radyToTake, shiftCash);
		} else {
			//exit
		}
	}


	private boolean haveSittingCostumer(String waiterName , ArrayList<Table> tables) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getWaiter().getName() == waiterName && !tables.get(i).isAvailable()) {
				return true;
			}
		}
		System.out.println("no costumers in your tables");
		return false;
	}
	
	private void handleWaiterChois(int input, ArrayList<Table> tables, Menu menu, Queue<Reservation> allReservations, ArrayList<Reservation> radyToTake,double shiftCash) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {

			printTakenWaiterTables( tables, menu);
			int tableNum = chooseTable();
			showMenu(menu);
			makeRasvation(tableNum, allReservations, newResavtion, tables , menu);
			waiterMenu(tables, menu, allReservations, radyToTake, shiftCash);
			break;
		}
		case 2: {
			printRadyDishes( tables, radyToTake);
			waiterMenu(tables, menu, allReservations, radyToTake, shiftCash);
			break;
		}
		case 3: {
			takeRadyDishesToTable(radyToTake, tables);
			waiterMenu(tables, menu, allReservations, radyToTake, shiftCash);
			break;
		}
		case 4: {
			printTakenWaiterTables(tables, menu);
			int tableNum = chooseTable();
			endOfMeal(tableNum, tables, shiftCash);
			waiterMenu(tables, menu, allReservations, radyToTake, shiftCash);
			System.out.println("table number " + tableNum + " finish");
			break;
		}
		case 5: {
			//exit
			break;
		}
		default:
			waiterMenu(tables, menu, allReservations, radyToTake, shiftCash);
		}
	}

	private void makeRasvation(int tableNum , Queue<Reservation> allReservations, ArrayList<Dish> newResavtion, ArrayList<Table> tables ,Menu menu) {

		addDishToResavtion(newResavtion, menu);
		Waiter tableWaiter = tables.get(tableNum).getWaiter();
		tableWaiter.takeReservation(tables.get(tableNum), newResavtion);
		allReservations.add(tables.get(tableNum).getReservation());
	}
	
	private void addDishToResavtion(ArrayList<Dish> newResavtion, Menu menu) {
		System.out.println("choose a dish");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		newResavtion.add((Dish) menu.getDishs().toArray()[input]);

		System.out.println("Do you want another one \n 1. Yes \n 2. No");
		input = in.nextInt();
		if (input == 1) {
			addDishToResavtion(newResavtion, menu);
		}
	}
	private void showMenu(Menu menu) {
		ArrayList<Dish> menuDishs = menu.getDishs();
		for (int i = 0; i < menuDishs.size(); i++) {
			System.out.println(i + "." + menuDishs.get(i).getName());
		}
	}
	
	private int chooseTable() {
		Scanner in = new Scanner(System.in);
		System.out.println("select table");
		int tableNum = in.nextInt();
		return tableNum;
	}
	

	private void printRadyDishes( ArrayList<Table> tables, ArrayList<Reservation> radyToTake) {
		for (int i = 0; i < radyToTake.size(); i++) {
			int tableNum = radyToTake.get(i).getTableNum();
			String nameOfTableWaiter = tables.get(tableNum).getWaiter().getName();
			if (nameOfTableWaiter == this.getName()) {
				ArrayList<Dish> radyDishesToTake = new ArrayList<Dish>();
				radyDishesToTake = radyToTake.get(i).getDishes();

				for (int j = 0; j < radyDishesToTake.size(); j++) {
					System.out.println(
							radyDishesToTake.get(j).getName() + " for table " + radyToTake.get(i).getTableNum());

				}
			}
		}
	}
	
	private void printTakenWaiterTables( ArrayList<Table> tables, Menu menu) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getWaiter().getName() == this.getName() && !tables.get(i).isAvailable()) {
				System.out.println(i + ". table " + tables.get(i).getTableNumber());
			}
		}
	}
	public void takeReservation(Table table, ArrayList<Dish> newReservation) {
		Reservation reservation = table.getReservation();
		ArrayList<Dish> reservationDishes = reservation.getDishes();
		for (int i = 0; i < newReservation.size(); i++) {
			Dish newDish = newReservation.get(i);
			reservationDishes.add(newDish);
		}
		// Update the reservation with the new array of dishes
		reservation.setDishes(reservationDishes);
	}

	private void takeRadyDishesToTable(ArrayList<Reservation> radyToTake,  ArrayList<Table> tables) {
		for (int i = 0; i < radyToTake.size(); i++) {
			int tableNum = radyToTake.get(i).getTableNum();
			String nameOfTableWaiter = tables.get(tableNum).getWaiter().getName();
			if (nameOfTableWaiter == this.getName()) {
				ArrayList<Dish> radyDishesToTake = new ArrayList<Dish>();
				radyDishesToTake = radyToTake.get(i).getDishes();

				for (int j = 0; j < radyDishesToTake.size(); j++) {
					radyDishesToTake.get(j).setDone(true);
					System.out.println(
							radyDishesToTake.get(j).getName() + " taken to table " + radyToTake.get(i).getTableNum());
				}
			}
			radyToTake.remove(i);
		}
	}
	public void serveDishs(Reservation reservation) {
		ArrayList<Dish> dishResavtion = reservation.getDishes();
		for (int i = 0; i < dishResavtion.size(); i++) {
			if (!dishResavtion.get(i).isDone() && dishResavtion.get(i).isRady()) {
				dishResavtion.get(i).setRady(true);
				dishResavtion.get(i).setDone(true);
			}
		}
	}
	
	private void endOfMeal(int index,  ArrayList<Table> tables, double shiftCash) {
//		int index = findTableIndexByNum(tableNum);
		double totalPrice = tables.get(index).getReservation().getTotalPrice();
		shiftCash += totalPrice;
		System.out.println("the total price of table " + tables.get(index).getTableNumber() + " is " + totalPrice);
		tables.get(index).cleanTable();
	}
}
