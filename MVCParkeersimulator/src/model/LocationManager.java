package model;

public class LocationManager extends AbstractModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numberOfOpenSpots;
	private int numberOfFloors;
	private int numberOfRows;
	private int numberOfPlaces;
	private Location[][][] locations;
	int placeNumberStart = 1; 
	
	/*
	 * LocationManager creates the locations and puts them into the array locations
	 */
	public LocationManager(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
		this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
		this.numberOfFloors = numberOfFloors;
		this.numberOfRows = numberOfRows;
		this.numberOfPlaces = numberOfPlaces;
		locations = new Location[numberOfFloors][numberOfRows][numberOfPlaces];
		
		for(int floor = 0; floor < numberOfFloors; floor++) {
	        for(int row = 0; row < numberOfRows; row++) {
	            for(int place = 0; place < numberOfPlaces; place++) {
	                locations[floor][row][place] = new Location(floor, row, place);
	            }
	        }
		}
		changeType(1, placeNumberStart, 60);
	}
	
	public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
	
	
	/* method for changing the location number in to the array location.
	 * the placeNumber is the number of the location.
	 * The rowsInFloor is for the amount of rows in one floor.
	 * The placesInRow is for the amount of places in one row.*/
	public int[] getLocationNumber(int placeNumber) {
		
		int[] locationArray;
		int place = 0;
		int row = 0;
		int floor = 0;
		
		for(int i = 1; placeNumber > i; i++) {
			place++;
			if(place == numberOfPlaces) {
				row++;
				place = 0;
				if(row == numberOfRows) {
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
	
	public Location getLocation(int floor, int row, int place) {
		return locations[floor][row][place];
	}
	
	public void changeType(int type, int placeNumberStart, int NumberOfPlaces) {
		for(int i = 0; i <= NumberOfPlaces - 1; i++) {
			int[] locationArray;
			locationArray = new int[3];
			locationArray = getLocationNumber(placeNumberStart + i);
			locations[locationArray[0]][locationArray[1]][locationArray[2]].setType(type);
		}
	}
	
	public void changeTypeTo(int typeFrom, int typeTo, int placeNumberStart, int NumberOfPlaces) {
		for(int i = 0; i <= NumberOfPlaces - 1; i++) {
			int[] locationArray;
			locationArray = new int[3];
			locationArray = getLocationNumber(placeNumberStart + i);
			if(locations[locationArray[0]][locationArray[1]][locationArray[2]].getType() == typeFrom) {
				locations[locationArray[0]][locationArray[1]][locationArray[2]].setType(typeTo);
			}
		}
	}
	
	public int getNumberOfPlaces() {
		return this.numberOfPlaces;
	}
	
	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
		
	}
	
	public int getPlaceNumberStart() {
		return this.placeNumberStart;
	}
	
	public void setPlaceNumberStart(int placeNumberStart) {
		this.placeNumberStart = placeNumberStart;
	}
	
	}
