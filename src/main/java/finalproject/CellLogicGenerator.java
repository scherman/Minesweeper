package finalproject;

import java.util.Random;

// Clase encargada exclusivamente para crear celdas vac�as o minas. 

public class CellLogic {
	
	int rows;
	int columns;
	int numberOfMines;
	
	public CellLogic (int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		calculateNumberOfMines();
		
	}
	
	private void calculateNumberOfMines () {
		numberOfMines = (int) (rows * columns * 0.15);
	}
	
	public Cell getNewObject() {
		Cell object;
		
		Random rand = new Random();
		int value = rand.nextInt(rows * columns);
		
		if (value <= numberOfMines-1) {
			object = new Mine ();
		} else {
			object = new EmptyCell ();
		}
		
		return object;
	}
	
}
