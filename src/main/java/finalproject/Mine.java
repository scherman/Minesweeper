package finalproject;

public class Mine extends Cell{

	public Mine () {
//		this.x = x;
//		this.y = y;
	}
	
	@Override
	public Boolean isMine() {
		return true;
	}

	@Override
	public String toString() {
		return "M";
	}
	
}
