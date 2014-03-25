package finalproject;

public class Minesweeper implements IMinesweeper{
	
	Grid grid;
	
	public Minesweeper (int rows, int columns) {
		// Creaci�n del escenario de juego
		grid = new Grid(rows, columns);
		grid.createScene();
	}

	public void uncover(int row, int col) {
		Cell uncoveredCell = grid.getCell(row, col);
		uncoveredCell.setOpened(true);
		
		if (!uncoveredCell.isMine()) {
			
		} else {
			isGameOver();
		}
		
	}

	public void flagAsMine(int row, int col) {
		grid.getCell(row, col).setFlagged(true);
	}

	public void clearFlag(int row, int col) {
		grid.getCell(row, col).setFlagged(false);
		
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
		// TODO Auto-generated method stub
		
	}

	public void displayInternal() {
		// TODO Auto-generated method stub
		
	}

	public void displayRaw() {
		// TODO Auto-generated method stub
		
	}

}
