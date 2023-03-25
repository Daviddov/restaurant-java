package reseturant;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Shift {

	private String name;
	private ArrayList<Workers> shiftWorkers = new ArrayList<Workers>();

	private Customer[] customers;
	private final int MAX_CUSTOMERS = 100;
	private final int MAX_CUSTOMERS_sitS = 50;
	private ArrayList<Customer> sitingCustomers = new ArrayList<Customer>();
	private int customersCount = 0;
	private ShiftManager shfitManeger;
	private int waitersNum = 4;
	private int cookersNum = 3;
	private Hostess hostess;
	private ArrayList<Table> tables = new ArrayList<Table>();
	private Queue<Reservation> allReservations = new ArrayDeque<>();
	private ArrayList<Reservation> radyToTake = new ArrayList<Reservation>();
	private Cooker chaf;
	private double shiftCash = 0;
	private Menu menu;
	private ArrayList<Dish> newResavtion = new ArrayList<Dish>();

	public Shift(String Shift, ArrayList<Workers> workers, ShiftManager shiftManager, Hostess hostess,
			ArrayList<Table> tables, Menu menu) {
		this.setName(Shift);
		this.shfitManeger = shiftManager;
		this.hostess = hostess;
		this.tables = tables;
		this.menu = menu;
//1 Auto
		shfitManeger.assignWorkersShift(workers, shiftWorkers, cookersNum, waitersNum, tables);
		// 2 Auto
		shiftManager.assignTablesWaiters(shiftWorkers);
////3 Auto
		this.chaf = setChaf();
		customers = new Customer[MAX_CUSTOMERS];
//		// 4
//		sitCustomer(4);
//		// 5 make resavetion
//
//		// 6 cook bon
//
//		// 7 wiater take the dishs to the customer	

//		// 8 end diner --> pay and live
//		endOfMeal(tables.get(0).getTableNumber());
		menu();
	}

	public void menu() {
		System.out.println(" 1. hostes \n 2. shift manager \n 3. waiter \n 4. cooker \n 5. total shift cash");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		handleChois(input);

	}

	private void handleChois(int input) {
		switch (input) {
		case 1: {
			hostessMenu();
			break;
		}
		case 2: {
			shfitManegerMenu();
			break;
		}
		case 3: {
			Waiter waiter = chooseWaiter();
			waiterMenu(waiter);
			break;
		}
		case 4: {
			cookerMenu();
			break;
		}
		case 5: {
//			end shift
			System.out.println("$" + shiftCash);
//			menu();
			break;
		}
//		case 6: {
//		test --->
//			this.shiftCash+=60;
//			System.out.println("$" + shiftCash);
//			menu();
//			break;
//		}

		default:
			menu();
		}
	}

	private void hostessMenu() {
		System.out.println(" 1. new costumers \n 2. show empty tables \n 3. exit");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		handleHostessChois(input);
	}

	private void handleHostessChois(int input) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {
			System.out.println(" How many customers came?");
			int input2 = in.nextInt();
			sitCustomer(input2);
			hostessMenu();
			break;
		}

		case 2: {
			printEmptyTable();
			break;
		}
		case 3: {
			menu();
			break;
		}

		default:
			hostessMenu();
		}
	}

	private void shfitManegerMenu() {
		System.out.println(" 1. assign wiater to table  \n 2.exit");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		handleShfitManegerChois(input);
	}

	private void handleShfitManegerChois(int input) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {
			Waiter waiter = chooseWaiter();
			printTables();
			Table table = getTableByNumber();
			shfitManeger.assignWaiterToTable(waiter, table);

			shfitManegerMenu();
			break;
		}
		case 2: {
			menu();
			break;
		}
		default:
			shfitManegerMenu();
		}
	}

	private void printWaiters() {
		for (int i = 0; i < shiftWorkers.size(); i++) {
			if (shiftWorkers.get(i) instanceof Waiter) {
				System.out.println(i + ". " + shiftWorkers.get(i).getName());
			}
		}
	}

	private Waiter chooseWaiter() {
		printWaiters();
		Scanner in = new Scanner(System.in);
		System.out.println("select waiter");
		int waiterNum = in.nextInt();
		return (Waiter) shiftWorkers.get(waiterNum);
	}

	private boolean haveSittingCostumer(String waiterName) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getWaiter().getName() == waiterName && !tables.get(i).isAvailable()) {
				return true;
			}
		}
		System.out.println("no costumers in your tables");
		return false;
	}

	private void printTables() {
		for (int i = 0; i < tables.size(); i++) {
			System.out.println(i + ". table " + tables.get(i).getTableNumber());
		}
	}

	private void printAllWaiterTables(String waiterName) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getWaiter().getName() == waiterName) {
				System.out.println(i + ". table " + tables.get(i).getTableNumber());
			}
		}
	}

	private void printTakenWaiterTables(String waiterName) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getWaiter().getName() == waiterName && !tables.get(i).isAvailable()) {
				System.out.println(i + ". table " + tables.get(i).getTableNumber());
			}
		}
	}

	private int chooseTable() {
		Scanner in = new Scanner(System.in);
		System.out.println("select table");
		int tableNum = in.nextInt();
		return tableNum;
	}

	private Table getTableByNumber() {
		Scanner in = new Scanner(System.in);
		System.out.println("select table");
		int tableNum = in.nextInt();
		return tables.get(tableNum);
	}

	private void waiterMenu(Waiter waiter) {
//		Waiter waiter = chooseWaiter();
		Scanner in = new Scanner(System.in);
		if (haveSittingCostumer(waiter.getName())) {
			System.out.println(
					" 1. make resavetion \n 2.show rady dishes \n 3. take dishes to table \n 4.end diner \n 5. exit");
			int input = in.nextInt();
			handleWaiterChois(input, waiter);
		} else {
			menu();
		}
	}

	private void handleWaiterChois(int input, Waiter waiter) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {

			printTakenWaiterTables(waiter.getName());
			int tableNum = chooseTable();
			showMenu(menu);
			makeRasvation(tableNum);
			waiterMenu(waiter);
			break;
		}
		case 2: {
			printRadyDishes(waiter.getName());
			waiterMenu(waiter);
			break;
		}
		case 3: {
			takeRadyDishesToTable(waiter.getName());
			waiterMenu(waiter);
			break;
		}
		case 4: {
			printTakenWaiterTables(waiter.getName());
			int tableNum = chooseTable();
			endOfMeal(tableNum);
			waiterMenu(waiter);
			System.out.println("table number " + tableNum + " finish");
			break;
		}
		case 5: {
			menu();
			break;
		}
		default:
			waiterMenu(waiter);
		}
	}

	private void printRadyDishes(String waiterName) {
		for (int i = 0; i < radyToTake.size(); i++) {
			int tableNum = radyToTake.get(i).getTableNum();
			String nameOfTableWaiter = tables.get(tableNum).getWaiter().getName();
			if (nameOfTableWaiter == waiterName) {
				ArrayList<Dish> radyDishesToTake = new ArrayList<Dish>();
				radyDishesToTake = radyToTake.get(i).getDishes();

				for (int j = 0; j < radyDishesToTake.size(); j++) {
					System.out.println(
							radyDishesToTake.get(j).getName() + " for table " + radyToTake.get(i).getTableNum());

				}
			}
		}
	}

	private void takeRadyDishesToTable(String waiterName) {
		for (int i = 0; i < radyToTake.size(); i++) {
			int tableNum = radyToTake.get(i).getTableNum();
			String nameOfTableWaiter = tables.get(tableNum).getWaiter().getName();
			if (nameOfTableWaiter == waiterName) {
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

	private void cookerMenu() {
		Scanner in = new Scanner(System.in);
		System.out.println(" 1. show resavetion \n 2.cook dishes \n 3. exit");
		int input = in.nextInt();
		handleCookerChois(input);
	}

	private void handleCookerChois(int input) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {
			showDishesResevation(allReservations.peek().getDishes());
			cookerMenu();
			break;
		}
		case 2: {
			chaf.makeTheBon(allReservations.peek());
			this.radyToTake.add(allReservations.remove());
			cookerMenu();
			break;
		}
		case 3: {
			menu();
			break;
		}
		default:
			cookerMenu();
		}
	}

	private void showDishesResevation(ArrayList<Dish> dishesResevation) {
		for (int i = 0; i < dishesResevation.size(); i++) {
			System.out.println(dishesResevation.get(i).getName());
		}
	}

	private void printEmptyTable() {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).isAvailable()) {
				System.out.println(i + ". table" + tables.get(i).getTableNumber());
			}
		}
	}

	private void printTakenTable() {
		for (int i = 0; i < tables.size(); i++) {
			if (!tables.get(i).isAvailable()) {
				System.out.println(i + ". table" + tables.get(i).getTableNumber());
			}
		}
	}

	private void showMenu(Menu menu) {
		ArrayList<Dish> menuDishs = menu.getDishs();
		for (int i = 0; i < menuDishs.size(); i++) {
			System.out.println(i + "." + menuDishs.get(i).getName());
		}
	}

	private void makeRasvation(int tableNum) {

		addDishToResavtion(newResavtion);
		Waiter tableWaiter = tables.get(tableNum).getWaiter();
		tableWaiter.takeReservation(tables.get(tableNum), newResavtion);
		allReservations.add(tables.get(tableNum).getReservation());
	}

	private void addDishToResavtion(ArrayList<Dish> newResavtion) {
		System.out.println("choose a dish");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		newResavtion.add((Dish) menu.getDishs().toArray()[input]);

		System.out.println("Do you want another one \n 1. Yes \n 2. No");
		input = in.nextInt();
		if (input == 1) {
			addDishToResavtion(newResavtion);
		}
	}

	private void endOfMeal(int index) {
//		int index = findTableIndexByNum(tableNum);
		double totalPrice = tables.get(index).getReservation().getTotalPrice();
		this.shiftCash += totalPrice;
		System.out.println("the total price of table " + tables.get(index).getTableNumber() + " is " + totalPrice);
		tables.get(index).cleanTable();
	}

	private int findTableIndexByNum(int tableNum) {
		for (int i = 0; i < tables.size(); i++) {
			if (tableNum == tables.get(i).getTableNumber()) {
				return i;
			}
		}
		return -1;
	}

	private void sitCustomer(int newCustomers) {
		Customer[] newsitCustomers;
		newsitCustomers = new Customer[newCustomers];
		newsitCustomers = hostess.sitCustomers(customers(newCustomers), tables);
//update all sit customers
		addSitingCustomers(newsitCustomers);

	}

	public Customer[] customers(int num) {
		Customer[] newCustomer = new Customer[num];
		Customer customer;
		for (int i = 0; i < num; i++) {
			customer = new Customer("guest " + customersCount + 1);
			newCustomer[i] = customer;
			customers[i + customersCount++] = newCustomer[i];
		}
		return newCustomer;
	}

	public Customer[] getCustomers() {
		return customers;
	}

	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private Cooker setChaf() {
		for (int i = 0; i < shiftWorkers.size(); i++) {
			if (shiftWorkers.get(i) instanceof Cooker) {
				return (Cooker) shiftWorkers.get(i);
			}
		}
		return null;
	}

	public ArrayList<Customer> getsitingCustomer() {
		return sitingCustomers;
	}

	public void setSitingCustomer(ArrayList<Customer> sitingCustomer) {
		this.sitingCustomers = sitingCustomer;
	}

	public void addSitingCustomers(Customer[] customers) {
		for (int i = 0; i < customers.length; i++) {
			sitingCustomers.add(customers[i]);
		}
	}

	public void addSitingCustomer(Customer customer) {
		sitingCustomers.add(customer);
	}

	public int getMAX_CUSTOMERS_sitS() {
		return MAX_CUSTOMERS_sitS;
	}

	public double getShiftCash() {
		return shiftCash;
	}

	public void setShiftCash(double restaurantCash) {
		this.shiftCash = restaurantCash;
	}

}
