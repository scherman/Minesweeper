package finalproject;

public class Minesweeper implements IMinesweeper{
	
	Grid grid;
	
	public static void main (String[] args) {
		Minesweeper ms = new Minesweeper(5,5);

		ms.flagAsMine(1,2);
		
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
			display();
		} else {
			isGameOver();
		}
	}

	public void flagAsMine(int row, int col) {
		grid.getCell(row, col).setFlagged(true);
		display();
	}

	public void clearFlag(int row, int col) {
		grid.getCell(row, col).setFlagged(false);
		display();
		
	}

	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isWinningGame() {
		// TODO Auto-generated method stub
		return false;
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
