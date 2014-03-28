package finalproject;

public class Mine extends Cell{
	
	@Override
	public Boolean isMine() {
		return true;
	}

	@Override
	public String toString() {
		return "M";
	}
	
}
