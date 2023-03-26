package reseturant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hostess extends Worker {

	public Hostess(int salary, String name) {
		super(salary, name);
	}

	public void hostessMenu(List<Table> tables, List<Customer> sittingCustomers, int costumersCount) {
		System.out.println(" 1. new costumers \n 2. show empty tables \n 3. show taken tables \n 4. exit");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		handleHostessChois(input, tables, sittingCustomers, costumersCount);
	}

	private void handleHostessChois(int input, List<Table> tables, List<Customer> sittingCustomers,
			int costumersCount) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {
			System.out.println(" How many customers came?");
			int numOfNewCostumers = in.nextInt();
			sitCustomer(numOfNewCostumers, tables, sittingCustomers);
			hostessMenu(tables, sittingCustomers, costumersCount);
			break;
		}

		case 2: {
			printEmptyTable(tables);
			hostessMenu(tables, sittingCustomers, costumersCount);
			break;
		}
		case 3: {
			printTakenTable(tables);
			hostessMenu(tables, sittingCustomers, costumersCount);
			break;
		}
		case 4: {
			// exit
			break;
		}

		default:
			hostessMenu(tables, sittingCustomers, costumersCount);
		}
	}

	private void printEmptyTable(List<Table> tables) {
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).isAvailable()) {
				System.out.println(i + ". table" + tables.get(i).getTableNumber());
			}
		}
	}
	private void printTakenTable(List<Table> tables) {
		for (int i = 0; i < tables.size(); i++) {
			if (!tables.get(i).isAvailable()) {
				System.out.println(i + ". table" + tables.get(i).getTableNumber());
			}
		}
	}

	private Table findTableAvailable(List<Table> tables) {
		for (int i = 0; i < tables.size(); i++) {
			Table table = tables.get(i);
			if (table.isAvailable()) {
				return table;
			}
		}
		return null;
	}

	private void sitCustomer(int numOfnewCustomers, List<Table> tables, List<Customer> sittingCustomers) {

		addCustomer(sittingCustomers);
		ArrayList<Customer> newCustomers = addCustomers(numOfnewCustomers, sittingCustomers);

		sitCustomers(newCustomers, tables);

	}

	private ArrayList<Customer> addCustomers(int numOfCostumers, List<Customer> sittingCustomers) {
		ArrayList<Customer> newCustomer = new ArrayList<Customer>();
	
		for (int i = 0; i < numOfCostumers; i++) {
			newCustomer.add(addCustomer(sittingCustomers));
		}
		return newCustomer;
	}

	private Customer addCustomer(List<Customer> sittingCustomers) {
		Customer customer = new Customer("guest " + sittingCustomers.size());

			sittingCustomers.add(customer);
			return customer;
	}
	
	private Customer[] sitCustomers(ArrayList<Customer> newCustomers, List<Table> tables) {
		// (need to build search for smallest table)
		int alradySeats = 0;
		Customer[] seatCustomers = new Customer[newCustomers.size()];

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
					table.getWaiter().setHasCustomers(true);
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

	private int countAvailableChers(List<Table> tables) {
		int availableChers = 0;
		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).isAvailable()) {
				availableChers += tables.get(i).getSitsNumber();
			}
		}
		return availableChers;
	}
}
