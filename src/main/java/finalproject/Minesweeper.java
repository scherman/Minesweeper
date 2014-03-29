package finalproject;

import java.awt.DisplayMode;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.despegar.highflight.utils.Matrix2DCellPosition;
import com.despegar.highflight.utils.MatrixUtils;

public class Minesweeper implements IMinesweeper{
	
	Grid grid;
	Cell uncoveredCell;
	
	public Minesweeper (int rows, int columns) {
		grid = new Grid(rows, columns);
		grid.createScene();
		
//		System.out.println(grid.numberOfMines + " minas generadas.");
	}

	public void uncover(int row, int col) {
		if (row >= grid.maxRow | col >= grid.maxColumn) {
			System.out.println("Valor fuera de la matriz");
		} else {
			uncoveredCell = grid.getCell(row, col);
			uncoveredCell.setOpened(true);
			grid.cascade(row, col);
		}

	}

	public void flagAsMine(int row, int col) {
		// Me fijo si le quedan banderas. Si es así la celda debe estar cerrada para poner la bandera
		if (grid.getNumberOfFlags() < grid.getNumberOfMines()) {
			if (!grid.getCell(row, col).isOpen()) {
				grid.getCell(row, col).setFlagged(true);
				grid.setNumberOfFlags(grid.getNumberOfFlags() + 1);
				display();
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
		
		try {
			if (uncoveredCell.isMine()) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	public boolean isWinningGame() {
		if (grid.allCellsUncovered()) {
			return true;
		} else {
			return false;
		}
	}

	public void display() {
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
		for (int row = 0; row < grid.maxRow; row ++) {
			for (int column = 0; column < grid.maxColumn; column ++) {
				System.out.print(grid.getCell(row, column));
			}
			System.out.print("\n");
		}
	}

	public void displayRaw() {
		for (int row = 0; row < grid.maxRow; row ++) {
			for (int column = 0; column < grid.maxColumn; column ++) {
				System.out.print(grid.binaryScene[row][column]);
			}
			System.out.print("\n");
		}
	}
	
}
