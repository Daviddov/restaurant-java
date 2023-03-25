package reseturant;

public class Tables {
	private Table[][] tables;
	private int numOfTables = 0;
	private final int numOfSeats = 4;

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public int availableTables() {
		int availableTables = 0;
		for (int i = 0; i < tables.length; i++) {
			for (int j = 0; j < tables[i].length; j++) {
				Table table = tables[i][j];
				if (table.isAvailable()) {
					availableTables++;
				}
			}
		}
		return availableTables;
	}

	public Table[][] getTables() {
		return tables;
	}

	public void setTables(Table[][] tables) {
		this.tables = tables;
	}

	public int getNumOfTables() {
		return numOfTables;
	}

	public void setNumOfTables(int numOfTables) {
		this.numOfTables = numOfTables;
	}

	public Tables(int x, int y) {
		this.tables = new Table[x][y];
		for (int i = 0; i < this.tables.length; i++) {
			for (int j = 0; j < tables[i].length; j++) {
				this.tables[i][j] = new Table(++numOfTables, numOfSeats);
			}
		}
		drawTable();
	}

	public void drawTable() {
		for (int i = 0; i < tables.length; i++) {
			Table[] rewTables = tables[i];
			for (int j = 0; j < rewTables.length; j++) {
				System.out.print("+-----+   ");

			}
			System.out.println();

			for (int j = 0; j < rewTables.length; j++) {
				System.out.print("| " + (rewTables[j].getTableNumber() + 10) + "  |   ");

			}
			System.out.println();

			for (int j = 0; j < rewTables.length; j++) {
				System.out.print("+-----+   ");

			}
			System.out.println();

			for (int j = 0; j < rewTables.length; j++) {
				if (rewTables[j].isAvailable()) {
					System.out.print(" EMPTY    ");
				} else {
					System.out.print("unavailable");

				}

			}
			System.out.println();
			System.out.println();
		}
	}

}
