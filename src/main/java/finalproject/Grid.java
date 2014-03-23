package finalproject;

public class Grid {
	
	Cell [][] scene;
	int x;
	int y;
	
	
	public Grid (int x, int y) {
		this.x = x;
		this.y = y;
		scene = new Cell [x][y];
	}
	
	public void createScene () {
		
		// Objecto delegado de la lógica de cuantas minas y celdas ubicar
		CellLogic objectsGenerator = new CellLogic(x, y);
		
		// Recorre la matriz y va agregando los objetos que le pasa objectsGenerator
		for (int row = 0; row < x; row ++) {
			 for (int column = 0; column < y; column++) {
				 scene[row][column] = objectsGenerator.getNewObject();
			 }
		}
	}
	
	public Cell getCell (int row, int column) {
		return scene[row][column];
	}

}
