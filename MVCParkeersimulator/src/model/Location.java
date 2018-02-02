package model;

public class Location {
	
	private int type = 0;
    private int floor;
    private int row;
    private int place;
    private int numberPlate;
    private int timer;

    /**
     * Constructor for objects of class Location
     */
    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    }

    /**
     * Implement content equality.
     */
    public boolean equals(Object obj) {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return floor == other.getFloor() && row == other.getRow() && place == other.getPlace();
        }
        else {
            return false;
        }
    }

    /**
     * Return a string of the form floor,row,place.
     * @return A string representation of the location.
     */
    public String toString() {
        return floor + "," + row + "," + place;
    }

    /**
     * Use the 10 bits for each of the floor, row and place
     * values. Except for very big car parks, this should give
     * a unique hash code for each (floor, row, place) tupel.
     * @return A hashcode for the location.
     */
    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }
    
    /**
     * sets the type of the location to 0 for normal spot, 1 for subscriber spot, 2 for reserved.
     * @param type
     */
    public void setType(int type) {
    	this.type = type;
    }
    
    /*
     * return the type
     */
    public int getType() {
    	return type;
    }
    /**
     * @return The floor.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @return The row.
     */
    public int getRow() {
        return row;
    }

    /**
     * @return The place.
     */
    public int getPlace() {
        return place;
    }
    
    /*
     * set the number plate of the location
     */
    public void setNumberPlate(int numberPlate) {
    	this.numberPlate = numberPlate;
    }
    
    /*
     * get the number plate of the location
     */
    public int getNumberPlate() {
    	return numberPlate;
    }
    
    /*
     * get the timer of the location for reservations
     */
    public int getTimer() {
    	return timer;
    }
    
    /*
     * set the timer of the location for reservations
     */
    public void setTimer() {
    	this.timer = 45;
    }
    
    /*
     * set the timer to zero
     */
    public void setTimerToZero() {
    	this.timer = 0;
    }
    
    /*
     * a tick count down from 45 to 0
     */
    public void tick() {
    	this.timer--;
    }
}