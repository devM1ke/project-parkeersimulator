package model;

import java.awt.Color;
import java.util.Random;

public class ReservationCar extends Car {
	private static final Color COLOR=Color.orange;

		private int arriveDay;
		private int arriveHour;
		private int arriveMinute;
		private int numberPlate;
		
		public ReservationCar(int day, int hour, int minute, int numberPlate) {
			arriveDay = day;
			arriveHour = hour;
			arriveMinute = minute;
			this.numberPlate = numberPlate;
			Random random = new Random();
	    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
	        this.setMinutesLeft(stayMinutes);
	        this.setHasToPay(true);
	        int timeDifference = random.nextInt(44) + 1;
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

		public int getArriveDay() {
			return arriveDay;
		}
		
		public int getArriveHour() {
			return arriveHour;
		}
		
		public int getArriveMinute() {
			return arriveMinute;
		}
		
		public int getNumberPlate() {
			return numberPlate;
		}
		
		public Color getColor(){
	    	return COLOR;
	    }
	}

