package reseturant;

import java.util.ArrayList;

public class Cooker extends Workers{

	public Cooker(int salary, String name) {
		super(salary, name);
		
	}


	public void makeDish(Dish dish) {
		dish.setRady(true);
	}
	
	public void makeTheBon(Reservation bon) {
		ArrayList<Dish> dishResavtion = bon.getDishes();
		for (int i=0; i < dishResavtion.size() ;i++) {
			if(!dishResavtion.get(i).isDone()) {
				dishResavtion.get(i).setRady(true);
				System.out.println("dishe "+dishResavtion.get(i).getName()+" is rady for table "+ bon.getTableNum());
			}
		}
	}
}
