package finalproject;

public abstract class Cell {
	
//	public int x;
//	public int y;
	
	public Boolean isOpen = false;
	public Boolean hasFlag = false;
	public int surroundingMines;
	
	public abstract Boolean isMine();
	
	public abstract String toString();
	
	protected Boolean isOpen () {
		return isOpen;
	}
	
	protected void setOpened (Boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	protected Boolean isFlagged () {
		return hasFlag;
	}
	
	protected Boolean setFlagged (Boolean hasFlag) {
		return this.hasFlag = hasFlag;
	}
	
	protected int getSurroundingMines() {
		return surroundingMines;
	}

	protected void setSurroundingMines(int surroundingMines) {
		this.surroundingMines = surroundingMines;
	}
}
