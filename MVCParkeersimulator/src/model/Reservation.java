package model;

import java.util.Random;

/**
 * The class reservation creates reservation objects.
 * this is used to identify when we need to reserve a location and 
 * give the location a number plate.
 * 
 * @author Ben Meijer
 * @version 02-02-2018
 */

public class Reservation extends AbstractModel {
	
	private static final long serialVersionUID = -5338343337563772550L;
	private int reservationDay;
	private int reservationHour;
	private int reservationMinute;
	private int numberPlate;
	private boolean isSet = false;
	Random rand = new Random();
	
	/*
	 * create a Reservation Object. it get's the current day, hour and minute, 
	 * as well as a number plate.
	 * we then run the time advance loop to create a reservation between 20 and 95 minutes later.
	 */
	public Reservation(int day, int hour, int minute, int numberPlate) {
		reservationDay = day;
		reservationHour = hour;
		reservationMinute = minute;
		this.numberPlate = numberPlate;
		int time = rand.nextInt(75) + 20;
		for(int i = 0; i < time; i++) {
			reservationMinute++;
	        while (reservationMinute > 59) {
	        	reservationMinute -= 60;
	            reservationHour++;
	        }
	        while (reservationHour > 23) {
	        	reservationHour -= 24;           
	        	reservationDay++;
	        }
	        while (reservationDay > 6) {
	        	reservationDay -= 7;
	        }
		}
	}
	
	/*
	 * return the reservation day
	 */
	public int getReservationDay(){
		return reservationDay;
	}
	
	/*
	 * return the reservation hour
	 */
	public int getReservationHour(){
		return reservationHour;
	}
	
	/*
	 * return the reservation minute
	 */
	public int getReservationMinute(){
		return reservationMinute;
	}
	
	/*
	 * return the reservation's number plate
	 */
	public int getReservationNumberPlate(){
		return numberPlate;
	}
	
	/*
	 * change the boolean isSet to true to keep track of which reservation has been set.
	 */
	public void setIsSet() {
		isSet = true;
	}
	
	/*
	 * return whether the reservation has been set
	 */
	public boolean getIsSet() {
		return isSet;
	}
	
}
