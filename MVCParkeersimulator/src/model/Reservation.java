package model;

import java.util.Random;

public class Reservation extends AbstractModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5338343337563772550L;
	private int reservationDay;
	private int reservationHour;
	private int reservationMinute;
	private int numberPlate;
	private boolean isSet = false;
	Random rand = new Random();
	
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

	public int getReservationDay(){
		return reservationDay;
	}
	
	public int getReservationHour(){
		return reservationHour;
	}
	
	public int getReservationMinute(){
		return reservationMinute;
	}
	
	public int getReservationNumberPlate(){
		return numberPlate;
	}
	
	public void setIsSet() {
		isSet = true;
	}
	
	public boolean getIsSet() {
		return isSet;
	}
	
}
