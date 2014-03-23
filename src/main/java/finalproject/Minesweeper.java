package finalproject;

public class Minesweeper implements IMinesweeper{
	
	Grid grid;
	
	public Minesweeper (int rows, int columns) {
		grid = new Grid(rows, columns);
		grid.createScene();
	}

	public void uncover(int row, int col) {

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
