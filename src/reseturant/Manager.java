package reseturant;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends ManagerialPerson{
	private Shift shift;
	private ArrayList<Worker> workers = new ArrayList<Worker>();
	private ArrayList<Table> tables = new ArrayList<Table>();
	private Menu menu;
	
	public Manager(int salary, String name, Menu menu, ArrayList<Worker> workers, ArrayList<Table> tables ) {
		super(salary, name);
		this.workers =workers;
		this.menu = menu;
		this.tables = tables;
		this.workers.add(new ShiftManager(1111, "workerName"));
		this.workers.add(new Hostess(1111, "workerName"));
		
		menu();
	}
	public void menu() {
		Scanner in = new Scanner(System.in);
		System.out.println(" 1. open shift \n 2. add shift manger \n 3. add hostes \n 4. add cooker \n 5. add waiter \n 6. add dish \n 7. add table" );
		int input = in.nextInt();
		handleMenagerChois(input);
	}
	
	private void handleMenagerChois(int input) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {//open shift
			this.setShift(new Shift("day shift", workers, getShiftManegar(), getHostess(), tables, menu));
			
		}
		break;
		case 2: {// add shift manager
			System.out.println("enter the name of shift maneger");
			String workerName = in.next();
			System.out.println("enter the amount of salery");
			int salery = in.nextInt();
			workers.add(new ShiftManager(salery, workerName));
			System.out.println("Done");
			menu();
			
		}
		break;
		case 3: {//add hostes 
			System.out.println("enter the name of hostes");
			String workerName = in.next();
			System.out.println("enter the amount of salery");
			int salery = in.nextInt();
			workers.add(new Hostess(salery, workerName));
			System.out.println("Done");
			menu();
			
		}
		break;
		case 4: {//add cooker
			System.out.println("enter the name of cooker");
			String workerName = in.next();
			System.out.println("enter the amount of salery");
			int salery = in.nextInt();
			workers.add(new Cooker(salery, workerName));
			System.out.println("Done");
			menu();
			
		}
		break;
		case 5: {// add waiter
			System.out.println("enter the name of waiter");
			String workerName = in.next();
			System.out.println("enter the amount of salery");
			int salery = in.nextInt();
			workers.add(new Waiter(salery, workerName));
			System.out.println("Done");
			menu();
			
		}
		break;
		case 6: {//add dish
			System.out.println("enter the name of dish");
			String dishName = in.next();
			System.out.println("enter the price of dish");
			int price = in.nextInt();
			System.out.println("enter the amount of dishs");
			int amount = in.nextInt();
			menu.addDish(new Dish(dishName, price, amount));
			System.out.println("Done");
			menu();
			
		}
		break;
		case 7: { //add table
			System.out.println("enter num of sits");
			int numOfsits = in.nextInt();
			addTable(numOfsits);
			System.out.println("Done");
			menu();
		}
		break;
		
		default:
			menu();
		}
	}
	
	
	private void addTable(int numOfsits) {
		this.tables.add(new Table(tables.size(), numOfsits));
	}
	
	public void addDishToMenu(String name, int price, int amount) {
		menu.addDish(new Dish(name, price, amount));
	}
	
	private Hostess getHostess() {
		for (int i = 0; i < workers.size(); i++) {
			if (workers.get(i) instanceof Hostess && workers.get(i).isAvailable()) {
				workers.get(i).setAvailable(false);
				return (Hostess) workers.get(i);
			}
		}
		return null;
	}

	private ShiftManager getShiftManegar() {
		for (int i = 0; i < workers.size(); i++) {
			if (workers.get(i) instanceof ShiftManager && workers.get(i).isAvailable()) {
				workers.get(i).setAvailable(false);
				return (ShiftManager) workers.get(i);
			}
		}
		return null;
	}
	public Shift getShift() {
		return shift;
	}
	public void setShift(Shift shift) {
		this.shift = shift;
	}

	
}
