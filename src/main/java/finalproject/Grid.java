package finalproject;

public class Grid {
	
	Cell [][] scene;
	int x;
	int y;
	int numberOfMines, cellSelectedNumberOfMines;
		
	public Grid (int x, int y) {
		this.x = x;
		this.y = y;
		scene = new Cell [x][y];
		numberOfMines = 0;
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
		
		// Analizo el mapa de juego: cuantas minas tiene cada celda al rededor y cuantas minas se generaron en total
		for (int row = 0; row < x; row ++) {
			 for (int column = 0; column < y; column++) {
				 scene[row][column].setSurroundingMines(countSurroundingMines(row, column));
			 }
		}
	}

	public Cell getCell (int row, int column) {
		return scene[row][column];
	}
	
	private int countSurroundingMines (int row, int column) {
		Boolean isPeak = false;
		Boolean surroundsBorder = false;
		cellSelectedNumberOfMines = 0;
		
		// Si la celda esta en algúna punta...
		if (row == 0 & column == 0) {
			// Punta superior izquierda
			isPeak = true;
			verifyIsMine(scene[row + 1][column]);
			verifyIsMine(scene[row][column + 1]);
			verifyIsMine(scene[row + 1][column + 1]);
		} else if (row == x & column == y) {
			// Punta inferior derecha
			isPeak = true;
			verifyIsMine(scene[row - 1][column]);
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row - 1][column - 1]);
		} else if (row == x & column == 0) {
			// Punta inferior izquierda
			isPeak = true;
			verifyIsMine(scene[row - 1][column]);
			verifyIsMine(scene[row][column + 1]);
			verifyIsMine(scene[row - 1][column + 1]);
		} else if (row == 0 & column == y) {
			// Punta inferior izquierda
			isPeak = true;
			verifyIsMine(scene[row + 1][column]);
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row + 1][column - 1]);
		}
		
		
		// Si la celda esta pegada a algún borde...
		if (row != 0 & column == 0) {
			// Pegada al borde izquierdo
			surroundsBorder = true;
			verifyIsMine(scene[row + 1][column]);
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row][column + 1]);
			verifyIsMine(scene[row + 1][column - 1]);
			verifyIsMine(scene[row + 1][column + 1]);
		} else if (row != 0 & column == y) {
			// Pegada al borde derecho
			surroundsBorder = true;
			verifyIsMine(scene[row - 1][column]);
			verifyIsMine(scene[row - 1][column - 1]);
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row + 1][column]);
			verifyIsMine(scene[row + 1][column - 1]);
		} else if (row == 0 & column !=0) {
			// Pegada al borde superior
			surroundsBorder = true;
			verifyIsMine(scene[row][column + 1]);
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row + 1][column]);
			verifyIsMine(scene[row + 1][column + 1]);
			verifyIsMine(scene[row + 1][column - 1]);
		} else if (row == x & column !=0) {
			// Pegada al borde inferior
			surroundsBorder = true;
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row][column + 1]);
			verifyIsMine(scene[row - 1][column]);
			verifyIsMine(scene[row - 1][column - 1]);
			verifyIsMine(scene[row - 1][column + 1]);
		}
		
		// Si está por el medio...
		if (!surroundsBorder & !isPeak) {
			verifyIsMine(scene [row + 1][column + 1]);
			verifyIsMine(scene [row + 1][column]);
			verifyIsMine(scene [row + 1][column - 1]);
			verifyIsMine(scene [row - 1][column + 1]);
			verifyIsMine(scene [row - 1][column]);
			verifyIsMine(scene [row - 1][column - 1]);
			verifyIsMine(scene [row][column - 1]);
			verifyIsMine(scene [row][column + 1]);
		}
		
		return cellSelectedNumberOfMines;
	}
	
	private void verifyIsMine (Cell selectedCell) {
		if (selectedCell.isMine()) {
			this.cellSelectedNumberOfMines ++;
		}
	}

}
