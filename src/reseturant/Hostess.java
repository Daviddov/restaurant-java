package reseturant;

import java.util.ArrayList;
import java.util.Scanner;

public class Hostess extends Workers {

	public Hostess(int salary, String name) {
		super(salary, name);
	}

	public void hostessMenu(ArrayList<Table> tables, ArrayList<Customer> sitingCustomers, int costumersCount) {
		System.out.println(" 1. new costumers \n 2. show empty tables \n 3. show taken tables \n 4. exit");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		handleHostessChois(input, tables, sitingCustomers, costumersCount);
	}

	private void handleHostessChois(int input, ArrayList<Table> tables, ArrayList<Customer> sitingCustomers,
			int costumersCount) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {
			System.out.println(" How many customers came?");
			int numOfNewCostumers = in.nextInt();
			sitCustomer(numOfNewCostumers, tables, sitingCustomers);
			hostessMenu(tables, sitingCustomers, costumersCount);
			break;
		}

		case 2: {
			printEmptyTable(tables);
			hostessMenu(tables, sitingCustomers, costumersCount);
			break;
		}
		case 3: {
			printTakenTable(tables);
			hostessMenu(tables, sitingCustomers, costumersCount);
			break;
		}
		case 4: {
			// exit
			break;
		}

		default:
			hostessMenu(tables, sitingCustomers, costumersCount);
		}
	}

	private void printEmptyTable(ArrayList<Table> tables) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).isAvailable()) {
				System.out.println(i + ". table" + tables.get(i).getTableNumber());
			}
		}
	}
	private void printTakenTable(ArrayList<Table> tables) {
		for (int i = 0; i < tables.size(); i++) {
			if (!tables.get(i).isAvailable()) {
				System.out.println(i + ". table" + tables.get(i).getTableNumber());
			}
		}
	}

	private Table findTableAvailable(ArrayList<Table> tables) {
		for (int i = 0; i < tables.size(); i++) {
			Table table = tables.get(i);
			if (table.isAvailable()) {
				return table;
			}
		}
		return null;
	}

	private void sitCustomer(int numOfnewCustomers, ArrayList<Table> tables, ArrayList<Customer> sitingCustomers) {
//		ArrayList<Customer> newsitCustomers = new ArrayList<Customer>();
		addCustomer(sitingCustomers);
		ArrayList<Customer> newCustomers = addCustomers(numOfnewCustomers, sitingCustomers);

		sitCustomers(newCustomers, tables);
//update all sit customers
//		addSitingCustomers(newsitCustomers, sitingCustomers);

	}

	private void addSitingCustomers(Customer[] newsitCustomers, ArrayList<Customer> sitingCustomers) {
		for (int i = 0; i < newsitCustomers.length; i++) {
			sitingCustomers.add(newsitCustomers[i]);
		}
	}

	private ArrayList<Customer> addCustomers(int numOfCostumers, ArrayList<Customer> sitingCustomers) {
		ArrayList<Customer> newCustomer = new ArrayList<Customer>();
	
		for (int i = 0; i < numOfCostumers; i++) {
			newCustomer.add(addCustomer(sitingCustomers));
		}
		return newCustomer;
	}

	private Customer addCustomer(ArrayList<Customer> sitingCustomers) {
		Customer customer = new Customer("guest " + sitingCustomers.size());

			sitingCustomers.add(customer);
			return customer;
	}
	
	private Customer[] sitCustomers(ArrayList<Customer> newCustomers, ArrayList<Table> tables) {
		// (need to build search for smallest table)
		int alradySeats = 0;
		Customer[] seatCustomers = new Customer[newCustomers.size()];

//		int numOfAvailableTables = countAvailableTables(tables);
		int numOfAvailableChers = countAvailableChers(tables);

		if (newCustomers.size() <= numOfAvailableChers) {
			while (alradySeats != newCustomers.size()) {
				Table table = findTableAvailable(tables);
				if (table != null) {
					for (int j = 0; j < table.getSitsNumber(); j++) {
						if (alradySeats == newCustomers.size()) {
							break;
						}
//					seatCustomers costumer in seat
						table.getSitsCustomers()[j] = newCustomers.get(alradySeats);
//					insert the costumers they alrady Seats to new array
						seatCustomers[j] = newCustomers.get(alradySeats);
						System.out.println(newCustomers.get(alradySeats).getName() + " costomer seat in table "
								+ table.getTableNumber());
						alradySeats++;
					}
					table.setAvailable(false);
				}
			}
		} else {
			System.out.println("Sorry no place to seat");
		}
		//return the new customers
		return seatCustomers;

	}

	private int countAvailableTables(ArrayList<Table> tables) {
		int availableTables = 0;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).isAvailable()) {
				availableTables++;
			}
		}
		return availableTables;
	}

	private int countAvailableChers(ArrayList<Table> tables) {
		int availableChers = 0;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).isAvailable()) {
				availableChers += tables.get(i).getSitsNumber();
			}
		}
		return availableChers;
	}
}
