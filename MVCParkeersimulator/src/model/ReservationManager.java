package model;
import java.util.ArrayList;

/**
 * this class is used to create the reservations and reservation cars.
 * this way we can make sure that the reservation and the car are made at the same time and
 * get the same number plate.
 * 
 * @author BDMei
 * @version 02-02-2018
 */

public class ReservationManager extends AbstractModel {
	private ArrayList<Reservation> reservations;
	private ArrayList<ReservationCar> reservationCars;
	private int numberPlate = 0;
	
	/*
	 * creates a reservationManager object which in turn creates the arraylists for the 
	 * reservations and the reservation cars.
	 */
	public ReservationManager(){
		reservations = new ArrayList<Reservation>(numberPlate);
		reservationCars = new ArrayList<ReservationCar>(numberPlate);
	}
	
	/*
	 * return the array list for the reservations
	 */
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}
	
	/*
	 * return the array list for the reservation cars
	 */
	public ArrayList<ReservationCar> getReservationCars() {
		return reservationCars;
	}
	
	/*
	 * creates a new reservation and reservation car and adds them to the corresponding array
	 * lists. it gets the current day, hour and minute.
	 * it gives these to the reservation.
	 * it then gets the reservation time to give those to the reservation car.
	 * it also gives the reservation and reservation car the same number plate.
	 * after a number plate is given it creates the next number plate to give to a new reservation.
	 */
	public void addNewReservation(int day, int hour, int minute) {
		reservations.add(new Reservation(day, hour, minute, numberPlate));
		reservationCars.add(new ReservationCar(reservations.get(numberPlate).getReservationDay(),
												reservations.get(numberPlate).getReservationHour(),
												reservations.get(numberPlate).getReservationMinute(),
												numberPlate));
		numberPlate++;
	}
}
