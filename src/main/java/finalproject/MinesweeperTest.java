package finalproject;

import java.util.Scanner;

public class MinesweeperTest {
	public static void main (String[] args) {
		int maxRow, maxCol;
		IMinesweeper ms = new Minesweeper(5, 5);
		ms.displayInternal();
		System.out.println();
		ms.display();
		while (!ms.isGameOver()) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Ingrese filas:");
			int row = scanner.nextInt();
			System.out.println("Ingrese columnas");
			int column = scanner.nextInt();
			System.out.println("Ingrese acción");
			String action = scanner.next();
			
			if (row >= 0 && column >= 0) {
				if (action.equals("u")) { // uncover
					ms.uncover(row, column);
				} else if (action.equals("f")) { // flagAsMine
					ms.flagAsMine(row, column);
				} else if (action.equals("c")) { // clearFlag
					ms.clearFlag(row, column);
				} else {
					System.out.println("Acción incorrecta");;
				}
			}
			
			ms.display();
		}
		
		if (ms.isWinningGame()) {
			System.out.println("Ganaste");
		} else {
			System.out.println ("Perdiste");
		}
		
		
	}
}
