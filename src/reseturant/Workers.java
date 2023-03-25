package reseturant;

abstract class Workers extends Person {
private double salary;
private boolean available;

public Workers(int salary,String name) {
	super(name);
	this.salary =salary;
	this.available =true;
}

public double getSalary() {
	return salary;
}
public void setSalary(double salary) {
	this.salary = salary;
}

public boolean isAvailable() {
	return available;
}

public void setAvailable(boolean available) {
	this.available = available;
}
}
