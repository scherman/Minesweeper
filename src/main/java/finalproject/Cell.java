package finalproject;

public abstract class Cell {
	
//	public int x;
//	public int y;
	
	public Boolean isOpen = false;
	public Boolean hasFlag = false;
	
	public abstract Boolean isMine();
	
	protected Boolean isOpen () {
		return isOpen;
	}
	
	protected Boolean isFlagged () {
		return hasFlag;
	}
	
	protected Boolean setFlagged (Boolean hasFlag) {
		return this.hasFlag = hasFlag;
	}
}
