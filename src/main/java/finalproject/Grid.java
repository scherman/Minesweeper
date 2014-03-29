package finalproject;

import java.util.List;
import java.util.Set;

import com.despegar.highflight.utils.Matrix2DCellPosition;
import com.despegar.highflight.utils.MatrixUtils;

public class Grid {
	
	Cell [][] scene;
	int [][] binaryScene;
	int maxRow;
	int maxColumn;
	int numberOfMines, cellSelectedNumberOfMines;
	int numberOfFlags;
	Boolean gameOver;
	Boolean gridComplete;
	
	public Grid (int x, int y) {
		scene = new Cell [x][y];
		binaryScene = new int[x][y];
		this.maxRow = x;
		this.maxColumn = y;
		numberOfMines = 0;
		numberOfFlags = 0;
	}
	
	public void createScene () {
		MinesLocationGenerator msGenerator = new MinesLocationGenerator(maxRow, maxColumn);
		Set<Location> minesLocation = msGenerator.generateMinesLocations();
		for (Location mineLocation : minesLocation) {
			scene[mineLocation.getRow()][mineLocation.getColumn()] = new Mine();
		}
		
		for (int row = 0; row < maxRow; row ++) {
			 for (int column = 0; column < maxColumn; column++) {
				 if (scene[row][column] == null) {
					 scene[row][column] = new EmptyCell();
				 }
				 
			 }
		}
		
		// Analizo el mapa de juego: cuantas minas tiene cada celda alrededor y cuantas minas se generaron en total. Tambien lo parseo en la binaryScene
		for (int row = 0; row < maxRow; row ++) {
			 for (int column = 0; column < maxColumn; column++) {
				 scene[row][column].setSurroundingMines(countSurroundingMines(row, column));
				 if (scene[row][column].isMine()) {
					 this.numberOfMines ++;
					 binaryScene [row][column] = 1;
				 } else {
					 binaryScene [row][column] = 0;
				 }
			 }
		}
	}
	
	public Cell getCell (int row, int column) {
		return scene[row][column];
	}
	
	public int getNumberOfFlags() {
		return numberOfFlags;
	}

	public void setNumberOfFlags(int numberOfFlags) {
		this.numberOfFlags = numberOfFlags;
	}
	
	public int getNumberOfMines() {
		return numberOfMines;
	}

	public void setNumberOfMines(int numberOfMines) {
		this.numberOfMines = numberOfMines;
	}
	
	public Boolean allCellsUncovered () {
		Boolean allCellsUncovered = true;
		
		// Recorro las celdas y me fijo si todas las minas estan flaggeadas
		for (int row = 0; row < maxRow; row ++) {
			 for (int column = 0; column < maxColumn; column++) {
				 if (!scene[row][column].isMine() && !scene[row][column].isOpen()) {
					 allCellsUncovered = false;
				 }
			 }
		}
		
		return allCellsUncovered;
	}
	
	public void cascade (int row, int column) {
		Set<Matrix2DCellPosition> matrix = MatrixUtils.cascade(binaryScene, row, column);
		for (Matrix2DCellPosition matrix2dCellPosition : matrix) {
			scene[matrix2dCellPosition.getRow()][matrix2dCellPosition.getColumn()].setOpened(true);
		}
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
		} else if (row == maxRow - 1 & column == maxColumn - 1) {
			// Punta inferior derecha
			isPeak = true;
			verifyIsMine(scene[row - 1][column]);
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row - 1][column - 1]);
		} else if (row == maxRow - 1 & column == 0) {
			// Punta inferior izquierda
			isPeak = true;
			verifyIsMine(scene[row - 1][column]);
			verifyIsMine(scene[row][column + 1]);
			verifyIsMine(scene[row - 1][column + 1]);
		} else if (row == 0 & column == maxColumn - 1 ) {
			// Punta superior derecha
			isPeak = true;
			verifyIsMine(scene[row + 1][column]);
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row + 1][column - 1]);
		}
		
		if (!isPeak) {
			// Si la celda esta pegada a algún borde...
			if (row != 0 & column == 0) {
				// Pegada al borde izquierdo
				surroundsBorder = true;
				verifyIsMine(scene[row + 1][column]);
				verifyIsMine(scene[row + 1][column + 1]);
				verifyIsMine(scene[row][column + 1]);
				verifyIsMine(scene[row - 1][column]);
				verifyIsMine(scene[row - 1][column + 1]);
			} else if (row != 0 & column == maxColumn - 1 ) {
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
			} else if (row == maxRow - 1  & column !=0) {
				// Pegada al borde inferior
				surroundsBorder = true;
				verifyIsMine(scene[row][column - 1]);
				verifyIsMine(scene[row][column + 1]);
				verifyIsMine(scene[row - 1][column]);
				verifyIsMine(scene[row - 1][column - 1]);
				verifyIsMine(scene[row - 1][column + 1]);
			}
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