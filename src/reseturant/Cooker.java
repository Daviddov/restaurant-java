package reseturant;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Cooker extends Worker {

	public Cooker(int salary, String name) {
		super(salary, name);

	}

	public void cookerMenu(Queue<Reservation> allReservations, List<Reservation> readyToTake) {
		Scanner in = new Scanner(System.in);
		System.out.println(" 1. show resavetion \n 2.cook dishes \n 3. exit");
		int input = in.nextInt();
		handleCookerChois(input, allReservations, readyToTake);
	}

	private void handleCookerChois(int input, Queue<Reservation> allReservations, List<Reservation> readyToTake) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {
			showDishesResevation(allReservations.peek().getDishes());
			cookerMenu(allReservations, readyToTake);
			break;
		}
		case 2: {
			makeTheBon(allReservations.peek());
			readyToTake.add(allReservations.remove());
			cookerMenu(allReservations, readyToTake);
			break;
		}
		case 3: {
			// exit
			break;
		}
		default:
			cookerMenu(allReservations, readyToTake);
		}
	}

	private void showDishesResevation(ArrayList<Dish> dishesResevation) {
		for (int i = 0; i < dishesResevation.size(); i++) {
			System.out.println(dishesResevation.get(i).getName());
		}

	}

	public void makeDish(Dish dish) {
		dish.setRady(true);
	}

	public void makeTheBon(Reservation bon) {
		ArrayList<Dish> dishResavtion = bon.getDishes();

		for (int i = 0; i < dishResavtion.size(); i++) {
			if (!dishResavtion.get(i).isDone()) {
				dishResavtion.get(i).setRady(true);
				System.out
						.println("dishe " + dishResavtion.get(i).getName() + " is rady for table " + bon.getTableNum());
			}
		}
	}
}
