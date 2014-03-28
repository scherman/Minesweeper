package finalproject;

public class MinesweeperTest {
	public static void main (String[] args) {
		Minesweeper ms = new Minesweeper(5, 5);
		ms.displayInternal();
		System.out.println("\n");
		ms.flagAsMine(3, 2);
		ms.flagAsMine(4, 2);
	}
}
