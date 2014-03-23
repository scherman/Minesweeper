package finalproject;

public class EmptyCell extends Cell{

	public EmptyCell () {
//		this.x = x;
//		this.y = y;
	}
	
	@Override
	public Boolean isMine() {
		return false;
	}
	
}
