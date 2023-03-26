package reseturant;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShiftManager extends ManagerialPerson {
//	private ArrayList<Table> tables = new ArrayList<Table>();
//	private ArrayList<Workers> shiftWorkers;
	private int maxTablesPerWaiter = 4;

	public ShiftManager(int salary, String name) {
		super(salary, name);

	}
	
	public void shiftManagerMenu(List<Worker> shiftWorkers, List<Table> tables) {
		System.out.println(" 1. assign wiater to table  \n 2.exit");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		handleShfitManegerChois(input, shiftWorkers, tables);
		
	}
	
//	public void shfitManegerMenu(List<Worker> shiftWorkers, ArrayList<Table> tables) {
//		System.out.println(" 1. assign wiater to table  \n 2.exit");
//		Scanner in = new Scanner(System.in);
//		int input = in.nextInt();
//		handleShfitManegerChois(input, shiftWorkers, tables);
//	}

	private void handleShfitManegerChois(int input, List<Worker> shiftWorkers, List<Table> tables) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {
			Waiter waiter = chooseWaiter(shiftWorkers);
			printTables(tables);
			Table table = getTableByNumber(tables);
			assignWaiterToTable(waiter, table);

			shiftManagerMenu(shiftWorkers, tables);
			break;
		}
		case 2: {
			//exit
			break;
		}
		default:
			shiftManagerMenu(shiftWorkers, tables);
		}
		
	}
	
	private Waiter chooseWaiter(List<Worker> shiftWorkers) {
		printWaiters(shiftWorkers);
		Scanner in = new Scanner(System.in);
		System.out.println("select waiter");
		int waiterNum = in.nextInt();
		return (Waiter) shiftWorkers.get(waiterNum);
	}
	
	private void printWaiters(List<Worker> shiftWorkers) {
		for (int i = 0; i < shiftWorkers.size(); i++) {
			if (shiftWorkers.get(i) instanceof Waiter) {
				System.out.println(i + ". " + shiftWorkers.get(i).getName());
			}
		}
	}
	
	private Table getTableByNumber(List<Table> tables) {
		Scanner in = new Scanner(System.in);
		System.out.println("select table");
		int tableNum = in.nextInt();
		return tables.get(tableNum);
	}

	private void printTables(List<Table> tables) {
		for (int i = 0; i < tables.size(); i++) {
			System.out.println(i + ". table " + tables.get(i).getTableNumber());
		}
	}
	
	public void assignWorkersShift(List<Worker> workers, List<Worker> shiftWorkers) {

		// add cookers to the shift
		assignCookersToShift(shiftWorkers, workers);
		
			// add waiters to the shift
		assignWaitersToShift(shiftWorkers, workers);
	}

	private void assignCookersToShift(List<Worker> workers, List<Worker> shiftWorkers) {
		for (int j = 0; j < workers.size(); j++) {
			if (workers.get(j).isAvailable() && workers.get(j) instanceof Cooker) {
				workers.get(j).setAvailable(false);
				shiftWorkers.add(workers.get(j));
				System.out.println("set cooker "+ workers.get(j).getName());

			}
		}
	}
	private void assignWaitersToShift(List<Worker> workers, List<Worker> shiftWorkers) {
		
		for (int j = 0; j < workers.size(); j++) {
			if (workers.get(j).isAvailable() && workers.get(j) instanceof Waiter) {
				workers.get(j).setAvailable(false);
				shiftWorkers.add(workers.get(j));
				System.out.println("set waiter "+ workers.get(j).getName());
				
			}
		}
	}
	
	public void assignTablesWaiters(List<Worker> shiftWorkers, List<Table> tables ) {
		int numberOfTables = tables.size();
		int tablesAssigned = 0;

		for (int i = 0; i < shiftWorkers.size(); i++) {
			if (tablesAssigned == numberOfTables) {
				return;
			}
			if (shiftWorkers.get(i) instanceof Waiter) {
				Waiter waiter = (Waiter) shiftWorkers.get(i);
				TablesForWaiter(waiter, maxTablesPerWaiter, tables);
			}

		}
	}

	public void TablesForWaiter(Waiter waiter, int TablesForWaiter, List<Table> tables) {
		for (int k = 0; k < TablesForWaiter; k++) {
			for (int j = 0; j < tables.size(); j++) {
				if (!isWaiterToTable(tables.get(j))) {
					assignWaiterToTable(waiter, tables.get(j));
					break;
				}
			}
		}
	}

	private boolean isWaiterToTable(Table table) {
		if (table.getWaiter() == null) {
			return false;
		}
		return true;
	}

	public void assignWaiterToTable(Waiter waiter, Table table) {
		table.setWaiter(waiter);
		System.out.println("assign waiter " + waiter.getName() + " to table " + table.getTableNumber());
	}






}
