package model;

/**
 * this class is used to create the locations and store them.
 * this allows us to change the type of a location and allocate cars according to the type.
 * 
 * @author Ben Meijer
 * @version 02-02-2018
 */
public class LocationManager extends AbstractModel {
	
	
	private static final long serialVersionUID = 1L;
	private int numberOfOpenSpots;
	private int numberOfFloors;
	private int numberOfRows;
	private int numberOfPlaces;
	private Location[][][] locations;
	int placeNumberStart = 1; 
	int placeNumberAmount = 60;
	
	/*
	 * LocationManager creates the locations and puts them into the array locations
	 * during the creation process it also allocates a number of locations the type 1.
	 * this creates the pass holder locations.
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
		changeType(1, placeNumberStart, placeNumberAmount);
	}
	
	/*
	 * returns the number of open spots.
	 */
	public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
	
	/* method for changing the location number to the array location.
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
	
	/*
	 * this returns the object location
	 */
	public Location getLocation(int floor, int row, int place) {
		return locations[floor][row][place];
	}
	
	/*
	 * this method changes the type of a number of locations to the given type.
	 * it does this based on the number where to start and the number of places that need to change
	 */
	public void changeType(int type, int placeNumberStart, int NumberOfPlaces) {
		for(int i = 0; i <= NumberOfPlaces - 1; i++) {
			int[] locationArray;
			locationArray = new int[3];
			locationArray = getLocationNumber(placeNumberStart + i);
			locations[locationArray[0]][locationArray[1]][locationArray[2]].setType(type);
		}
	}
	
	/*
	 * this method makes us able to change the type of locations with a specific type into another type.
	 * mainly made so that reservations are unaffected when we change things.
	 */
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
	
	/*
	 * return the number of places.
	 */
	public int getNumberOfPlaces() {
		return this.numberOfPlaces;
	}
	
	/*
	 * set the number of places.
	 */
	public void setNumberOfPlaces(int numberOfPlaces) {
		this.numberOfPlaces = numberOfPlaces;
		
	}
	
	/*
	 * return the number of the place where the change starts.
	 */
	public int getPlaceNumberStart() {
		return this.placeNumberStart;
	}
	
	/*
	 * return the number of places that need to change.
	 */
	public int getPlaceNumberAmount() {
		return this.placeNumberAmount;
	}
	
	/*
	 * set the number of places to start
	 */
	public void setPlaceNumberStart(int placeNumberStart) {
		this.placeNumberStart = placeNumberStart;
	}
	
	/*
	 * set the number of places that have to change.
	 */
	public void setPlaceNumberAmount(int placeNumberAmount) {
		this.placeNumberAmount= placeNumberAmount;
	}
	
	}
