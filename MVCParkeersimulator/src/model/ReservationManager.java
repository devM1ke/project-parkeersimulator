package model;

public class ReservationManager {
	private Reservation[] reservations;
	int numberPlate = 0;
	int reservationId= 0;
	
	ReservationManager(int day, int hour, int minute){
		reservations = new Reservation[reservationId];
		reservations[reservationId] = new Reservation(day, hour, minute, numberPlate);
		// plek om reserveringsauto's aan te maken.
		reservationId++;
		numberPlate++;
	
	}
}
