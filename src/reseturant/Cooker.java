package reseturant;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Cooker extends Workers {

	public Cooker(int salary, String name) {
		super(salary, name);

	}

	public void cookerMenu(Queue<Reservation> allReservations, ArrayList<Reservation> radyToTake) {
		Scanner in = new Scanner(System.in);
		System.out.println(" 1. show resavetion \n 2.cook dishes \n 3. exit");
		int input = in.nextInt();
		handleCookerChois(input, allReservations, radyToTake);
	}

	private void handleCookerChois(int input, Queue<Reservation> allReservations, ArrayList<Reservation> radyToTake) {
		Scanner in = new Scanner(System.in);
		switch (input) {
		case 1: {
			showDishesResevation(allReservations.peek().getDishes());
			cookerMenu(allReservations, radyToTake);
			break;
		}
		case 2: {
			makeTheBon(allReservations.peek());
			radyToTake.add(allReservations.remove());
			cookerMenu(allReservations, radyToTake);
			break;
		}
		case 3: {
			// exit
			break;
		}
		default:
			cookerMenu(allReservations, radyToTake);
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
