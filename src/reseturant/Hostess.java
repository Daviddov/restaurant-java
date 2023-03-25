package reseturant;

import java.util.ArrayList;

public class Hostess extends Workers {

	public Hostess(int salary, String name) {
		super(salary, name);
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


	public Customer[] sitCustomers(Customer[] newCustomers, ArrayList<Table> tables) {
		// (need to build search for smallest table)
		int alradySeats = 0;
		Customer[] seatCustomers = new Customer[newCustomers.length];

//		int numOfAvailableTables = countAvailableTables(tables);
		int numOfAvailableChers = countAvailableChers(tables);
		
		if (newCustomers.length <= numOfAvailableChers) {
			while (alradySeats != newCustomers.length) {
				Table table = findTableAvailable(tables);
				if(table != null) {
				for (int j = 0; j < table.getSitsNumber(); j++) {
					if(alradySeats == newCustomers.length){
						break;
					}
//					seatCustomers costumer in seat
					table.getSitsCustomers()[j] = newCustomers[alradySeats];
//					insert the costumers they alrady Seats to new array
					seatCustomers[j] = newCustomers[alradySeats];
					System.out.println(newCustomers[alradySeats].getName()+" costomer seat in table "+table.getTableNumber());
					alradySeats++;
				}
				table.setAvailable(false);
			}
			}
		} else {
			System.out.println("Sorry no place to seat");
		}
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
