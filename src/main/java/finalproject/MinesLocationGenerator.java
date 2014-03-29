package finalproject;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MinesLocationGenerator {
	
	int rows, columns;
	int numberOfMines;
	Set<Location> minesLocations;

	
	public MinesLocationGenerator (int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		 minesLocations = new HashSet<Location>();
	}
	
	public Set<Location> generateMinesLocations() {

		Location mineLocation;
		calculateNumberOfMines();
		
		for (int i=0; i < numberOfMines; i++) {
			 mineLocation = new Location();
			 mineLocation.setRow(getRandom(rows));
			 mineLocation.setColumn(getRandom(columns));
			 if (!isMineAt(mineLocation.getRow(), mineLocation.getColumn())) {
				 minesLocations.add(mineLocation);
			 } else {
				 i--;
			 }
		}
		return minesLocations;
	}
	
	private void calculateNumberOfMines () {
		numberOfMines = (int) (rows * columns * 0.15);
	}
	
	private int getRandom (int max) {
		Random rand = new Random();
		int value = rand.nextInt(max);
		return value;
	}
	
	private Boolean isMineAt (int row, int col) {
		
		Boolean repeat = false;;
		// Si se repite la ubicación entonces hay mina
		
		for (Location mineLocation : minesLocations) {
			if (mineLocation.getRow() == row && mineLocation.getColumn() == col) {
				repeat = true;
			}
		}
		
		return repeat;
	}
}
