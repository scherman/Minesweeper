package finalproject;

public class Grid {
	
	Cell [][] scene;
	int maxRow;
	int maxColumn;
	int numberOfMines, cellSelectedNumberOfMines;
		
	public Grid (int x, int y) {
		scene = new Cell [x][y];
		this.maxRow = x;
		this.maxColumn = y;
		numberOfMines = 0;
	}
	
	public void createScene () {
		
		// Objecto delegado de la lógica de cuantas minas y celdas ubicar
		CellLogic objectsGenerator = new CellLogic(maxRow, maxColumn);
		
		// Recorre la matriz y va agregando los objetos que le pasa objectsGenerator
		for (int row = 0; row < maxRow; row ++) {
			 for (int column = 0; column < maxColumn; column++) {
				 scene[row][column] = objectsGenerator.getNewObject();
			 }
		}
		
		// Analizo el mapa de juego: cuantas minas tiene cada celda al rededor y cuantas minas se generaron en total
		for (int row = 0; row < maxRow; row ++) {
			 for (int column = 0; column < maxColumn; column++) {
				 scene[row][column].setSurroundingMines(countSurroundingMines(row, column));
				 if (scene[row][column].isMine()) {
					 this.numberOfMines ++;
				 }
			 }
		}
	}

	public Cell getCell (int row, int column) {
		return scene[row][column];
	}
	
	private int countSurroundingMines (int row, int column) {
		Boolean isPeak = false;
		Boolean surroundsBorder = false;
		cellSelectedNumberOfMines = 0;
		
		// Si la celda esta en algúna punta...
		if (row == 0 & column == 0) {
			// Punta superior izquierda
			isPeak = true;
			verifyIsMine(scene[row + 1][column]);
			verifyIsMine(scene[row][column + 1]);
			verifyIsMine(scene[row + 1][column + 1]);
		} else if (row == maxRow - 1 & column == maxColumn - 1) {
			// Punta inferior derecha
			isPeak = true;
			verifyIsMine(scene[row - 1][column]);
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row - 1][column - 1]);
		} else if (row == maxRow - 1 & column == 0) {
			// Punta inferior izquierda
			isPeak = true;
			verifyIsMine(scene[row - 1][column]);
			verifyIsMine(scene[row][column + 1]);
			verifyIsMine(scene[row - 1][column + 1]);
		} else if (row == 0 & column == maxColumn - 1 ) {
			// Punta superior derecha
			isPeak = true;
			verifyIsMine(scene[row + 1][column]);
			verifyIsMine(scene[row][column - 1]);
			verifyIsMine(scene[row + 1][column - 1]);
		}
		
		if (!isPeak) {
			// Si la celda esta pegada a algún borde...
			if (row != 0 & column == 0) {
				// Pegada al borde izquierdo
				surroundsBorder = true;
				verifyIsMine(scene[row + 1][column]);
				verifyIsMine(scene[row + 1][column + 1]);
				verifyIsMine(scene[row][column + 1]);
				verifyIsMine(scene[row - 1][column]);
				verifyIsMine(scene[row - 1][column + 1]);
			} else if (row != 0 & column == maxColumn - 1 ) {
				// Pegada al borde derecho
				surroundsBorder = true;
				verifyIsMine(scene[row - 1][column]);
				verifyIsMine(scene[row - 1][column - 1]);
				verifyIsMine(scene[row][column - 1]);
				verifyIsMine(scene[row + 1][column]);
				verifyIsMine(scene[row + 1][column - 1]);
			} else if (row == 0 & column !=0) {
				// Pegada al borde superior
				surroundsBorder = true;
				verifyIsMine(scene[row][column + 1]);
				verifyIsMine(scene[row][column - 1]);
				verifyIsMine(scene[row + 1][column]);
				verifyIsMine(scene[row + 1][column + 1]);
				verifyIsMine(scene[row + 1][column - 1]);
			} else if (row == maxRow - 1  & column !=0) {
				// Pegada al borde inferior
				surroundsBorder = true;
				verifyIsMine(scene[row][column - 1]);
				verifyIsMine(scene[row][column + 1]);
				verifyIsMine(scene[row - 1][column]);
				verifyIsMine(scene[row - 1][column - 1]);
				verifyIsMine(scene[row - 1][column + 1]);
			}
		}
		
		// Si está por el medio...
		if (!surroundsBorder & !isPeak) {
			verifyIsMine(scene [row + 1][column + 1]);
			verifyIsMine(scene [row + 1][column]);
			verifyIsMine(scene [row + 1][column - 1]);
			verifyIsMine(scene [row - 1][column + 1]);
			verifyIsMine(scene [row - 1][column]);
			verifyIsMine(scene [row - 1][column - 1]);
			verifyIsMine(scene [row][column - 1]);
			verifyIsMine(scene [row][column + 1]);
		}
		
		return cellSelectedNumberOfMines;
	}
	
	private void verifyIsMine (Cell selectedCell) {
		if (selectedCell.isMine()) {
			this.cellSelectedNumberOfMines ++;
		}
	}

}
//
//public static void main(String[] args) {
//	  Minesweeper m;
//	  m=new MinesweeperImpl(3,4);
//	//  m.display();
//	  m.displayInternal();
//	  
//	//  while( m.isGameOver()==false){
//	//	  m.uncover(0,2);
//
//	//  }
//	//  m.display();
//}
//
//public void display() {
//	// TODO Auto-generated method stub
//				
//	
//	for (int i=0; i<3; i++) { 
//      for (int j=0; j<4; j++){ 
//      	if(cm.thereIsUncovered(i, j)){
//      		if(cm.thereIsFlag(i, j)){
//      			System.out.println("F");
//      		}else{
//          		System.out.println(cm.getNumber(i, j));
//      		}
//     	}else{
//      		System.out.print("X");
//      	}
//      } 
//      System.out.println(); 
//  }
//
//}
