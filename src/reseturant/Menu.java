package reseturant;

import java.util.ArrayList;
import java.util.LinkedList;

public class Menu {
	
	private ArrayList<Dish> dishs = new ArrayList<Dish>();

	public ArrayList<Dish> getDishs() {
		return dishs;
	}
	

	public void setDishs(ArrayList<Dish> dishs) {
		this.dishs = dishs;
	}
	
	public void addDish(Dish dish){
		dishs.add(dish);
	}
	
}
