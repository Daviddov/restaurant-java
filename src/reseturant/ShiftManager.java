package reseturant;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

public class ShiftManager extends ManagerialPerson {
	private ArrayList<Table> tables = new ArrayList<Table>();
	private ArrayList<Workers> shfitWorkers;
	private int tablesForWaiter = 4;

	public ShiftManager(int salary, String name) {
		super(salary, name);

	}

	
//error -->
//	public void waiterToTableByName() { 
//		Scanner in = new Scanner(System.in);
//// wiater to table
//		for (int i = 0; i < shfitWorkers.size(); i++) {
//			if(shfitWorkers.get(i) instanceof Waiter) {
//				System.out.println(shfitWorkers.get(i).getName());
//			}
//		}
//			System.out.println("enter the waiter name");
//			String waiterName = in.nextLine();
//			System.out.println("enter table number");
//			for (int i = 0; i < tables.size(); i++) {
//					System.out.println(tables.get(i).getTableNumber());
//			}
//			int tableNum = in.nextInt();
//			Waiter waiter = lookeForWaiter(waiterName);
//			Table table = lookForTable(tableNum);
//			assignWaiterToTable(waiter, table);
//			System.out.println("Done");
//			
//	}
	
	private Waiter lookeForWaiter(String waiterName) {
		for (int i = 0; i < shfitWorkers.size(); i++) {
			if(shfitWorkers.get(i).getName() == waiterName ) {
				return (Waiter) shfitWorkers.get(i);
			}
		}
		return null;
	}
	
	private Table lookForTable(int tableNum) {
		for (int i = 0; i < tables.size(); i++) {
			if(tables.get(i).getTableNumber() == tableNum ) {
				return  tables.get(i);
			}
		}
		return null;
	}
	
	public void assignWorkersShift(ArrayList<Workers> workers, ArrayList<Workers> shiftWorkers, int cookers,
			int waiters, ArrayList<Table> tables) {
		this.tables = tables;
		this.setShfitWorkers(shiftWorkers);

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

	public ArrayList<Workers> getShfitWorkers() {
		return shfitWorkers;
	}

	public void setShfitWorkers(ArrayList<Workers> shiftWorkers) {
		this.shfitWorkers = shiftWorkers;
	}

}
