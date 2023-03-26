package reseturant;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

public class ShiftManager extends ManagerialPerson {
	private ArrayList<Table> tables = new ArrayList<Table>();
//	private ArrayList<Workers> shiftWorkers;
	private int tablesForWaiter = 4;

	public ShiftManager(int salary, String name) {
		super(salary, name);

	}

	public void shfitManegerMenu(ArrayList<Workers> shiftWorkers) {
		System.out.println(" 1. assign wiater to table  \n 2.exit");
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		handleShfitManegerChois(input, shiftWorkers);
	}

	private void handleShfitManegerChois(int input, ArrayList<Workers> shiftWorkers) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {
			Waiter waiter = chooseWaiter(shiftWorkers);
			printTables();
			Table table = getTableByNumber();
			assignWaiterToTable(waiter, table);

			shfitManegerMenu(shiftWorkers);
			break;
		}
		case 2: {
			//exit
			break;
		}
		default:
			shfitManegerMenu(shiftWorkers);
		}
	}
	
	private Waiter chooseWaiter(ArrayList<Workers> shiftWorkers) {
		printWaiters(shiftWorkers);
		Scanner in = new Scanner(System.in);
		System.out.println("select waiter");
		int waiterNum = in.nextInt();
		return (Waiter) shiftWorkers.get(waiterNum);
	}
	
	private void printWaiters(ArrayList<Workers> shiftWorkers) {
		for (int i = 0; i < shiftWorkers.size(); i++) {
			if (shiftWorkers.get(i) instanceof Waiter) {
				System.out.println(i + ". " + shiftWorkers.get(i).getName());
			}
		}
	}
	
	private Table getTableByNumber() {
		Scanner in = new Scanner(System.in);
		System.out.println("select table");
		int tableNum = in.nextInt();
		return tables.get(tableNum);
	}

	private void printTables() {
		for (int i = 0; i < tables.size(); i++) {
			System.out.println(i + ". table " + tables.get(i).getTableNumber());
		}
	}
	
//	private Waiter lookeForWaiter(ArrayList<Workers> shiftWorkers, String waiterName) {
//		for (int i = 0; i < shiftWorkers.size(); i++) {
//			if(shiftWorkers.get(i).getName() == waiterName ) {
//				return (Waiter) shiftWorkers.get(i);
//			}
//		}
//		return null;
//	}
//	
//	private Table lookForTable(int tableNum) {
//		for (int i = 0; i < tables.size(); i++) {
//			if(tables.get(i).getTableNumber() == tableNum ) {
//				return  tables.get(i);
//			}
//		}
//		return null;
//	}
	
	public void assignWorkersShift(ArrayList<Workers> workers, ArrayList<Workers> shiftWorkers, int cookers,
			int waiters, ArrayList<Table> tables) {
		this.tables = tables;
//		this.setShfitWorkers(shiftWorkers);

		// add cookers to the shift
		for (int i = 0; i < cookers; i++) {
			for (int j = 0; j < workers.size(); j++) {
				if (workers.get(j).isAvailable() && workers.get(j) instanceof Cooker) {
					shiftWorkers.add(workers.get(j));
					workers.get(j).setAvailable(false);
					System.out.println("set cooker");
				}
			}
			// add waiters to the shift
			for (int j = 0; j < workers.size(); j++) {
				if (workers.get(j).isAvailable() && workers.get(j) instanceof Waiter) {
					shiftWorkers.add(workers.get(j));
					workers.get(j).setAvailable(false);
					System.out.println("set waiter");

				}
			}
		}

	}

	public void assignTablesWaiters(ArrayList<Workers> shiftWorkers) {
		int numberOfTables = tables.size();
		int tablesAssigned = 0;

		for (int i = 0; i < shiftWorkers.size(); i++) {
			if (tablesAssigned == numberOfTables) {
				return;
			}
			if (shiftWorkers.get(i) instanceof Waiter) {
				Waiter waiter = (Waiter) shiftWorkers.get(i);
				TablesForWaiter(waiter, tablesForWaiter);
			}

		}
	}

	public void TablesForWaiter(Waiter waiter, int TablesForWaiter) {
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
