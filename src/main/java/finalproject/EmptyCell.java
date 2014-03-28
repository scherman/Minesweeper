package finalproject;

public class EmptyCell extends Cell{
	
	@Override
	public Boolean isMine() {
		return false;
	}

	@Override
	public String toString() {
		return String.valueOf(surroundingMines);
	}	
}
