package model;

import java.awt.Color;
import java.util.Random;

/**
 * this class creates the reservation cars.
 * these cars get the orange color and can only be placed in the spot with the same number plate
 * 
 * @author Ben Meijer
 * @version 02-02-2018
 */
public class ReservationCar extends Car {
	private static final Color COLOR=Color.orange;

		private int arriveDay;
		private int arriveHour;
		private int arriveMinute;
		private int numberPlate;
		
		/*
		 * Create a reservationCar object.
		 * it is given the day, hour, minute and number plate of the corresponding
		 * reservation.
		 * it then changes those integers to a difference of -20 minutes to +40 minutes.
		 * this allows the car to come too early for the reservation or too late.
		 */
		public ReservationCar(int day, int hour, int minute, int numberPlate) {
			arriveDay = day;
			arriveHour = hour;
			arriveMinute = minute -20;
			this.numberPlate = numberPlate;
			Random random = new Random();
	    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
	        this.setMinutesLeft(stayMinutes);
	        this.setHasToPay(true);
	        int timeDifference = random.nextInt(60) - 20;
			for(int i = 0; i < timeDifference; i++) {
				arriveMinute++;
		        while (arriveMinute > 59) {
		        	arriveMinute -= 60;
		        	arriveHour++;
		        }
		        while (arriveHour > 23) {
		        	arriveHour -= 24;           
		        	arriveDay++;
		        }
		        while (arriveDay > 6) {
		        	arriveDay -= 7;
		        }
			}
		}

		/*
		 * return the arrive day
		 */
		public int getArriveDay() {
			return arriveDay;
		}
		
		/*
		 * return the arrive hour
		 */
		public int getArriveHour() {
			return arriveHour;
		}
		
		/*
		 * return the arrive minute
		 */
		public int getArriveMinute() {
			return arriveMinute;
		}
		
		/*
		 * return the number plate
		 */
		public int getNumberPlate() {
			return numberPlate;
		}
		
		/*
		 * return the color
		 */
		public Color getColor(){
	    	return COLOR;
	    }
	}

