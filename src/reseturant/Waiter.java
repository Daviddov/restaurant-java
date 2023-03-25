package reseturant;

import java.util.ArrayList;

public class Waiter extends Workers {

	public Waiter(int salary, String name) {
		super(salary, name);

	}
	public void takeReservation(Table table, ArrayList<Dish> newReservation) {
		Reservation reservation = table.getReservation();
		ArrayList<Dish> reservationDishes = reservation.getDishes();
		for (int i = 0; i < newReservation.size(); i++) {
			Dish newDish = newReservation.get(i);
			reservationDishes.add(newDish);
		}
		// Update the reservation with the new array of dishes
		reservation.setDishes(reservationDishes);
	}

	public void serveDishs(Reservation reservation) {
		ArrayList<Dish> dishResavtion = reservation.getDishes();
		for (int i = 0; i < dishResavtion.size(); i++) {
			if (!dishResavtion.get(i).isDone() && dishResavtion.get(i).isRady()) {
				dishResavtion.get(i).setRady(true);
				dishResavtion.get(i).setDone(true);
			}
		}
	}
	
	
}
