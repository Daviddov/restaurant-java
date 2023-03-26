package reseturant;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Waiter extends Worker {
	private boolean hasCustomers;
	  private List<Dish> newReservation = new ArrayList<>();
	    public Waiter(int salary, String name) {
	        super(salary, name);
	        this.setHasCustomers(false);
	    }

	    public void waiterMenu(List<Table> tables, Menu menu, Queue<Reservation> allReservations, List<Reservation> readyToTake, double shiftCash) {
	        Scanner scanner = new Scanner(System.in);
	        if (hasCustomers(tables)) {
	            System.out.println(
	                "1. Make a reservation\n" +
	                "2. Show ready dishes\n" +
	                "3. Take dishes to table\n" +
	                "4. End diner\n" +
	                "5. Exit"
	            );
	            int input = scanner.nextInt();
	            handleWaiterChoice(input, tables, menu, allReservations, readyToTake, shiftCash);
	        } else {
	            System.out.println("No customers at your tables.");
	        }
	    }
	    
	    public boolean hasCustomers(List<Table> tables) {
	        for (Table table : tables) {
	            if (!table.isAvailable() &&table.getWaiter().getName().equals(this.getName()) ) {
	                return true;
	            }
	        }
	       
	        return false;
	    }

	    private void handleWaiterChoice(int input, List<Table> tables, Menu menu, Queue<Reservation> allReservations, List<Reservation> readyToTake, double shiftCash) {
	        switch (input) {
	            case 1:
	                printTakenWaiterTables(tables);
	                int tableNum = chooseTable(tables.size());
	                showMenu(menu);
	                makeReservation(tableNum, allReservations, newReservation, tables, menu);
	                waiterMenu(tables, menu, allReservations, readyToTake, shiftCash);
	                break;
	            case 2:
	                printReadyDishes(tables, readyToTake);
	                waiterMenu(tables, menu, allReservations, readyToTake, shiftCash);
	                break;
	            case 3:
	                takeReadyDishesToTable(readyToTake, tables);
	                waiterMenu(tables, menu, allReservations, readyToTake, shiftCash);
	                break;
	            case 4:
	                printTakenWaiterTables(tables);
	                tableNum = chooseTable(tables.size());
	                endOfMeal(tableNum, tables, shiftCash);
	                waiterMenu(tables, menu, allReservations, readyToTake, shiftCash);
	                System.out.println("Table " + tableNum + " has finished.");
	                break;
	            case 5:
	                //exit
	                break;
	            default:
	                waiterMenu(tables, menu, allReservations, readyToTake, shiftCash);
	        }
	    }

	    private void makeReservation(int tableNum, Queue<Reservation> allReservations, List<Dish> newReservation, List<Table> tables, Menu menu) {
	        addDishToReservation((ArrayList<Dish>) newReservation, menu);
	        Waiter tableWaiter = tables.get(tableNum).getWaiter();
	        tableWaiter.takeReservation(tables.get(tableNum), (ArrayList<Dish>) newReservation);
	        allReservations.add(tables.get(tableNum).getReservation());
	    }
	    private void addDishToReservation(ArrayList<Dish> newReservation, Menu menu) {
	        Scanner in = new Scanner(System.in);

	        while (true) {
	            System.out.println("Choose a dish:");
	            showMenu(menu);

	            int input = in.nextInt();
	            Dish dish =menu.getDishs().get(input);
	            System.out.println("How many servings of "+ dish.getName()+" do you want?:");
	            int amount = in.nextInt();
	            for (int i = 0; i < amount; i++) {
	            	newReservation.add(dish);
	            	System.out.println(dish.getName() +" add to your reservation");
				}

	            System.out.println("Do you want another one?\n1. Yes\n2. No");
	            input = in.nextInt();
	            if (input != 1) {
	                break;
	            }
	        }
	    }

	    private void showMenu(Menu menu) {
	        ArrayList<Dish> menuDishes = menu.getDishs();

	        for (int i = 0; i < menuDishes.size(); i++) {
	            System.out.println(i + ". " + menuDishes.get(i).getName());
	        }
	    }

	    private int chooseTable(int numOfTables) {
	        Scanner in = new Scanner(System.in);

	        while (true) {
	            System.out.println("Select a table:");
	            int tableNum = in.nextInt();

	            if (tableNum >= 0 && tableNum < numOfTables) {
	                return tableNum;
	            } else {
	                System.out.println("Invalid table number.");
	            }
	        }
	    }

	    private void printReadyDishes(List<Table> tables, List<Reservation> readyToServe) {
	        for (Reservation reservation : readyToServe) {
	            int tableNum = reservation.getTableNum();
	            String waiterName = tables.get(tableNum).getWaiter().getName();

	            if (waiterName.equals(this.getName())) {
	                ArrayList<Dish> readyDishes = reservation.getDishes();

	                for (Dish dish : readyDishes) {
	                    if (dish.isRady()) {
	                        System.out.println(dish.getName() + " for table " + tableNum);
	                    }
	                }
	            }
	        }
	    }

	    private void printTakenWaiterTables(List<Table> tables) {
	        for (int i = 0; i < tables.size(); i++) {
	            Table table = tables.get(i);

	            if (table.getWaiter().getName().equals(this.getName()) && !table.isAvailable()) {
	                System.out.println(i + ". Table " + table.getTableNumber());
	            }
	        }
	    }

	    public void takeReservation(Table table, ArrayList<Dish> newReservation) {
	        Reservation reservation = table.getReservation();
	        ArrayList<Dish> reservationDishes = reservation.getDishes();
	        reservationDishes.addAll(newReservation);
	        reservation.setDishes(reservationDishes);
	    }

	    private void takeReadyDishesToTable(List<Reservation> readyToServe, List<Table> tables) {
	        for (Reservation reservation : readyToServe) {
	            int tableNum = reservation.getTableNum();
	            String waiterName = tables.get(tableNum).getWaiter().getName();

	            if (waiterName.equals(this.getName())) {
	                ArrayList<Dish> readyDishes = reservation.getDishes();

	                for (Dish dish : readyDishes) {
	                    if (dish.isRady()) {
	                        dish.setDone(true);
	                        System.out.println(dish.getName() + " taken to table " + tableNum);
	                    }
	                }

	                readyToServe.remove(reservation);
	            }
	        }
	    }

	    public void serveDishes(Reservation reservation) {
	        ArrayList<Dish> dishes = reservation.getDishes();

	        for (Dish dish : dishes) {
	            if (dish.isRady() && !dish.isDone()) {
	                dish.setDone(true);
	            }
	        }
	    }
	    
	    private int findTableIndexByNum(int tableNum, List<Table> tables) {
	        for (int i = 0; i < tables.size(); i++) {
	            if (tables.get(i).getTableNumber() == tableNum) {
	                return i;
	            }
	        }
	        return -1; // table not found
	    }
	    
	    private void endOfMeal(int tableNum, List<Table> tables, double shiftCash) {
	        int index = findTableIndexByNum(tableNum, tables);
	        if (index == -1) {
	            System.out.println("Table " + tableNum + " not found.");
	            return;
	        }
	        
	        double totalPrice = tables.get(index).getReservation().getTotalPrice();
	        shiftCash += totalPrice;
	        System.out.println("The total price of table " + tableNum + " is " + totalPrice);
	        tables.get(index).cleanTable();
	    }

		public boolean isHasCustomers() {
			return hasCustomers;
		}

		public void setHasCustomers(boolean hasCustomers) {
			this.hasCustomers = hasCustomers;
		}

	

}
