package model;
import java.util.ArrayList;

public class ReservationManager extends AbstractModel {
	private ArrayList<Reservation> reservations;
	private ArrayList<ReservationCar> reservationCars;
	private int numberPlate = 0;
	
	public ReservationManager(){
		reservations = new ArrayList<Reservation>(numberPlate);
		reservationCars = new ArrayList<ReservationCar>(numberPlate);
	}
	
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}
	
	public ArrayList<ReservationCar> getReservationCars() {
		return reservationCars;
	}
	
	public void addNewReservation(int day, int hour, int minute) {
		reservations.add(new Reservation(day, hour, minute, numberPlate));
		reservationCars.add(new ReservationCar(reservations.get(numberPlate).getReservationDay(), reservations.get(numberPlate).getReservationHour(), reservations.get(numberPlate).getReservationMinute(), numberPlate));
		numberPlate++;
	}
}
