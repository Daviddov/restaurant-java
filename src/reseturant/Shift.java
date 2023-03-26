package reseturant;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Shift {
//
//	private String name;
//	private ArrayList<Workers> shiftWorkers = new ArrayList<Workers>();
//
//	private Customer[] customers;
//	private final int MAX_CUSTOMERS = 100;
//	private final int MAX_CUSTOMERS_sitS = 50;
//	private ArrayList<Customer> sitingCustomers = new ArrayList<Customer>();
//	private int customersCount = 0;
//	private ShiftManager shfitManeger;
//	private int waitersNum = 4;
//	private int cookersNum = 3;
//	private Hostess hostess;
//	private ArrayList<Table> tables = new ArrayList<Table>();
//	private Queue<Reservation> allReservations = new ArrayDeque<>();
//	private ArrayList<Reservation> radyToTake = new ArrayList<Reservation>();
//	private Cooker chaf;
//	private double shiftCash = 0;
//	private Menu menu;
////	private ArrayList<Dish> newResavtion = new ArrayList<Dish>();
//
//	public Shift(String Shift, ArrayList<Workers> workers, ShiftManager shiftManager, Hostess hostess,
//			ArrayList<Table> tables, Menu menu) {
//		this.setName(Shift);
//		this.shfitManeger = shiftManager;
//		this.hostess = hostess;
//		this.tables = tables;
//		this.menu = menu;
////1 Auto
//
//		shfitManeger.assignWorkersShift(workers, shiftWorkers, cookersNum, waitersNum, tables);
//		// 2 Auto
//		shiftManager.assignTablesWaiters(shiftWorkers);
//////3 Auto
//		this.chaf = setChaf();
//		customers = new Customer[MAX_CUSTOMERS];
////		// 4
////		sitCustomer(4);
////		// 5 make resavetion
////
////		// 6 cook bon
////
////		// 7 wiater take the dishs to the customer	
//
////		// 8 end diner --> pay and live
////		endOfMeal(tables.get(0).getTableNumber());
//		menu();
//	}
//
//	public void menu() {
//		System.out.println(" 1. hostes \n 2. shift manager \n 3. waiter \n 4. cooker \n 5. total shift cash");
//		Scanner in = new Scanner(System.in);
//		int input = in.nextInt();
//		handleChois(input);
//	}
//
//	private void handleChois(int input) {
//		switch (input) {
//		case 1: {
//			this.hostess.hostessMenu(tables, sitingCustomers, customersCount);
//			menu();
//			break;
//		}
//		case 2: {
//			this.shfitManeger.shfitManegerMenu(shiftWorkers);
//			menu();
//			break;
//		}
//		case 3: {
//			Waiter waiter = chooseWaiter();
//			waiter.waiterMenu(tables, menu, allReservations, radyToTake, shiftCash);
//			menu();
//			break;
//		}
//		case 4: {
//			if(allReservations.size() > 0) {
//			chaf.cookerMenu(allReservations, radyToTake);
//			} else {
//				System.out.println("go to sleep you have nothig to do");
//			}
//			menu();
//			break;
//		}
//		case 5: {
////			end shift
//			System.out.println("$" + shiftCash);
//			menu();
//			break;
//		}
////		case 6: {
////		test --->
////			this.shiftCash+=60;
////			System.out.println("$" + shiftCash);
////			menu();
////			break;
////		}
//		default:
//			menu();
//		}
//	}
//	

//

	private String name;
	private List<Worker> shiftWorkers;
	private List<Customer> sittingCustomers;
	private Queue<Reservation> allReservations;
	private List<Reservation> readyToTake;
	private List<Table> tables;
	private int customersCount;
	private ShiftManager shiftManager;
	private Hostess hostess;
	private Cooker chef;
	private Menu menu;
	private double shiftCash;
	private Customer[] customers;
	private List<Waiter> waiters ;
	
	private static final int MAX_CUSTOMERS = 100;
	private static final int MAX_SITTING_CUSTOMERS = 50;
	private static final int NUM_WAITERS = 4;
	private static final int NUM_COOKERS = 3;

	public Shift(String name, List<Worker> workers, ShiftManager shiftManager, Hostess hostess, List<Table> tables,
			Menu menu) {
		this.name = name;
		
		this.shiftWorkers = new ArrayList<>(workers);
		this.sittingCustomers = new ArrayList<>(MAX_SITTING_CUSTOMERS);
		this.allReservations = new LinkedList<>();
		this.readyToTake = new ArrayList<>();
		this.tables = new ArrayList<>(tables);
		this.customersCount = 0;
		this.shiftManager = shiftManager;
		this.hostess = hostess;
		this.chef = getChef();
		this.menu = menu;
		this.shiftCash = 0;
		this.waiters = new ArrayList<>();
		
		// assign workers to shift and tables
		shiftManager.assignWorkersShift(workers, shiftWorkers);
		shiftManager.assignTablesWaiters(shiftWorkers, tables);

		menu();
	}

	public void menu() {
		System.out.println(" 1. hostess \n 2. shift manager \n 3. waiter \n 4. chef \n 5. total shift cash");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		handleChoice(input);
	}

	private void handleChoice(int input) {
		switch (input) {
		case 1:
			hostess.hostessMenu(tables, sittingCustomers, customersCount);
			menu();
			break;
		case 2:
			shiftManager.shiftManagerMenu(shiftWorkers, tables);
			menu();
			break;
		case 3:
			if(this.sittingCustomers.size()>0) {
			Waiter waiter = chooseWaiter();
			waiter.waiterMenu(tables, menu, allReservations, readyToTake, shiftCash);
			}else {
				System.out.println("No customers for you");
			}
			menu();
			break;
		case 4:
			if (!allReservations.isEmpty()) {
				chef.cookerMenu(allReservations, readyToTake);
			} else {
				System.out.println("No orders to cook. Go to sleep.");
			}
			menu();
			break;
		case 5:
			System.out.println("$" + shiftCash);
			menu();
			break;
		default:
			menu();
		}
	}

	private Waiter chooseWaiter() {
		int index=0;
		
		for (int i = 0; i < shiftWorkers.size(); i++) {
		Worker worker = shiftWorkers.get(i);
			if (worker instanceof Waiter) {
				waiters.add((Waiter)worker );
			}
		}
		for (Waiter waiter : waiters) {
			if(waiter.isHasCustomers()) {
				System.out.println(index + ". " + waiter.getName());
			}
			index++;
		}
		
		System.out.println("Select a waiter:");
		Scanner in = new Scanner(System.in);
		int waiterNum = in.nextInt();

		return waiters.get(waiterNum);
	}

	
	public Customer[] createCustomers(int num) {
		Customer[] newCustomers = new Customer[num];
		for (int i = 0; i < num; i++) {
			Customer customer = new Customer("Guest " + (customersCount + 1));
			newCustomers[i] = customer;
			customers[customersCount++] = customer;
		}
		return newCustomers;
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

	private Cooker getChef() {
		for (Worker worker : shiftWorkers) {
			if (worker instanceof Cooker) {
				return (Cooker) worker;
			}
		}
		return null;
	}

	public List<Customer> getSittingCustomers() {
		return sittingCustomers;
	}

	public void setSittingCustomers(List<Customer> sittingCustomers) {
		this.sittingCustomers = sittingCustomers;
	}

	public void addSittingCustomers(Customer[] customers) {
		for (Customer customer : customers) {
			sittingCustomers.add(customer);
		}
	}

	public void addSittingCustomer(Customer customer) {
		sittingCustomers.add(customer);
	}

	public int getMaxSittingCustomers() {
		return MAX_SITTING_CUSTOMERS;
	}

	public double getShiftCash() {
		return shiftCash;
	}

	public void setShiftCash(double shiftCash) {
		this.shiftCash = shiftCash;
	}

}
