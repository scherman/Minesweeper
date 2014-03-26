package finalproject;

import java.awt.DisplayMode;
import java.util.Set;

import com.despegar.highflight.utils.Matrix2DCellPosition;
import com.despegar.highflight.utils.MatrixUtils;

public class Minesweeper implements IMinesweeper{
	
	Grid grid;
	
	public static void main (String[] args) {
		Minesweeper ms = new Minesweeper(5, 5);
		ms.displayInternal();
		System.out.println("\n");
		ms.uncover(2, 3);
	}
	
	public Minesweeper (int rows, int columns) {
		// Creación del escenario de juego
		grid = new Grid(rows, columns);
		grid.createScene();
		
		System.out.println("Exitoso");
		System.out.println(grid.numberOfMines + " minas generadas.");
	}

	public void uncover(int row, int col) {
		Cell uncoveredCell = grid.getCell(row, col);
		uncoveredCell.setOpened(true);
		
		if (!uncoveredCell.isMine()) {
			grid.getCell(row, col).setOpened(true);
			grid.cascade(row, col);
			
			display();
			
			//isWinningGame();
		} else {
			isGameOver();
		}
	}

	public void flagAsMine(int row, int col) {
		if (grid.getNumberOfFlags() < grid.getNumberOfMines()) {
			if (!grid.getCell(row, col).isOpen()) {
				grid.getCell(row, col).setFlagged(true);
				grid.setNumberOfFlags(grid.getNumberOfFlags() + 1);
				display();
				isWinningGame();
			}
		}

	}

	public void clearFlag(int row, int col) {
		if (grid.getCell(row, col).isFlagged()) {
			grid.getCell(row, col).setFlagged(false);
			grid.setNumberOfFlags(grid.getNumberOfFlags() - 1);
			display();
		}
	}

	public boolean isGameOver() {
		System.out.println("Partida perdida");
		return false;
	}

	public boolean isWinningGame() {
		if (grid.allMinesHasFlag() & grid.allCellsUncovered()) {
			System.out.println("Partida ganada");
		}
		return true;
	}

	public void display() {
		// Muestro los que estan abiertos
		for (int row = 0; row < grid.maxRow; row ++) {
			for (int column = 0; column < grid.maxColumn; column ++) {
				if (grid.getCell(row, column).isOpen()) {
					System.out.print(grid.getCell(row, column));
				} else if (grid.getCell(row, column).isFlagged()){
					System.out.print("F");
				} else {
					System.out.print("-");
				}
			}
			System.out.print("\n");
		}
	}

	public void displayInternal() {
		// Muestro escenario completo
		for (int row = 0; row < grid.maxRow; row ++) {
			for (int column = 0; column < grid.maxColumn; column ++) {
				System.out.print(grid.getCell(row, column));
			}
			System.out.print("\n");
		}
	}

	public void displayRaw() {
		// Muestro minas en binario
		for (int row = 0; row < grid.maxRow; row ++) {
			for (int column = 0; column < grid.maxColumn; column ++) {
				if (grid.getCell(row, column).isMine()) {
					System.out.print("1");
				} else {
					System.out.print("0");
				}
			}
			System.out.print("\n");
		}
	}
	
}
