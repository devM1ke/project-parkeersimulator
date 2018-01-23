package model;

public class locationManager {
	
	private int numberOfOpenSpots;
	
	public locationManager(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
		this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
		for(int floor = 0; floor < numberOfFloors; floor++) {
	        for(int row = 0; row < numberOfRows; row++) {
	            for(int place = 0; place < numberOfPlaces; place++) {
	                Location location = new Location(floor, row, place);
	            }
	        }
		}
	}

	
	public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
	
	/* method for changing the location number in to the array location.
	 * the placeNumber is the number of the location.
	 * The rowsInFloor is for the amount of rows in one floor.
	 * The placesInRow is for the amount of places in one row.*/
	public int[] getLocation(int placeNumber, int placesInRow, int rowsInFloor) {
		
		int[] locationArray;
		int place = 0;
		int row = 0;
		int floor = 0;
		
		for(int i = 1; placeNumber > i; i++) {
			place++;
			if(place == placesInRow) {
				row++;
				place = 0;
				if(row == rowsInFloor) {
					floor++;
					row = 0;
				}
			}
		}
		locationArray = new int[3];
		locationArray[0]= floor;
		locationArray[1]= row;
		locationArray[2]= place;
		return locationArray;
	}
	
}
