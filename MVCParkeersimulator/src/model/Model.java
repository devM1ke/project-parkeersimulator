package model;

import java.util.Random;

import javax.swing.JMenuBar;

import view.ManagementView;
import view.MenuBarView;
import model.AdHocCar;
import model.Car;
import model.CarQueue;
import model.Location;
import model.ParkingPassCar;
import model.LocationManager;
import view.SimulatorView;
import java.awt.*;

public class Model extends AbstractModel implements Runnable {
	public int price = 5;
	public int howmanydays = 7;
	public int[] dailyearningdays;
	public int dailyearnings = 0;
	public int[] color = new int[4];
	
	private int numberOfFloors = 3;
    private int numberOfRows = 6;
    private int numberOfPlaces = 30;
    private int numberOfOpenSpots;
    private Car[][][] cars;
    volatile private boolean run;
    
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private SimulatorView simulatorView;
    private LocationManager locationManager;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 20; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
    
    Thread runner;
    
    public Model() {
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        dailyearningdays = new int[howmanydays];
        locationManager = new LocationManager(numberOfFloors, numberOfRows, numberOfPlaces);

        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        this.runner = new Thread(this);
        this.runner.start();
		//MenuBarView menuBarView = new MenuBarView(this.model);
    }
	
	public void start() {
		run=true;
	}

	public void stop() {
		run=false;
	}

	public void runFor(int tick) {
		run=false;
		int buffTickPause = this.tickPause;
		tickPause = 0;
        for(int i = 0; i < tick; i++) {
        	tick();
        }
        tickPause = buffTickPause;
	}
	
	@Override
	public void run() {
        tick();
		while(true) {
			if(run) {
				tick(); 
			}
		}
	}
    
    public void tick() {
		try {
			Thread.sleep(this.tickPause);
		} catch (Exception e) {}
		
    	advanceTime();
    	handleExit();
    	notifyViews();
    	handleEntrance();
    	
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = locationManager.getLocation(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }
    
    public void setGarage(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
    	this.numberOfFloors = numberOfFloors;
    	this.numberOfRows = numberOfRows;
    	this.numberOfPlaces = numberOfPlaces;
    }
    
    public int getTickPause()
    {
        return this.tickPause;
    }
    
    public void setTickPause(int ticks)
    {
        this.tickPause = ticks;
    }
    
    public int getDay(){
    	return day;
    }
    
    public int getHour(){
    	return hour;
    }
    
    public int getMinute(){
    	return minute;
    }
    
    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
			setDailyEarningZero();            
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }
    
	public void setDailyEarningZero(){
		for (int i = 0; i < howmanydays; i++)
		{
        if (getDay()==i) {
        	dailyearningdays[i] = dailyearnings;
        	dailyearnings = 0;       
        }}

		
	}
	
	public int stillToBeEarned(){
		return (getTotalParkingSpots()-getNumberOfOpenSpots())*price;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
    public void updateViews() {
    	simulatorView.updateView();
    }
    
	public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    
    public int getTotalParkingSpots() {
    	return numberOfFloors * numberOfRows * numberOfPlaces;
    }
    
    public LocationManager getLocationManager() {
    	return locationManager;
    }
    
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);
        numberOfOpenSpots++;
        return car;
    }

    public Location getFirstFreeLocation(Color color) {
    	if(color == Color.blue) {
    		for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                for (int row = 0; row < getNumberOfRows(); row++) {
                    for (int place = 0; place < getNumberOfPlaces(); place++) {
                    	Location location = getLocationManager().getLocation(floor, row, place);
	                    if (getCarAt(location) == null && location.getType() == 1) {
	                        return location;
	                    }
                	}
                }
            }
		}
    	
    	for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = getLocationManager().getLocation(floor, row, place);
                	if(color == Color.blue) {
	                    if (getCarAt(location) == null) {
	                        return location;
	                    }
                	} else {
	                    if (getCarAt(location) == null && location.getType() == 0) {
	                        return location;
	                    }
                	}
                }
            }
        }
        return null;
    }

    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = getLocationManager().getLocation(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);    	
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
    		Car car = queue.getRef();
            Location freeLocation = getFirstFreeLocation(car.getColor());
            if(freeLocation != null) {
	            setCarAt(freeLocation, car);
	            i++;
	            car = queue.removeCar();
            } else {
	            i++;
            }
        }
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            dailyearnings = dailyearnings + price;
	            paymentCarQueue.addCar(car);
        	}
        	else {
        		carLeavesSpot(car);
        	}
            car = getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
            }
            break;
    	case PASS:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new ParkingPassCar());
            }
            break;	            
    	}
    }
    
    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

    public void getTypeCar(){
    	int blauw = 0;
    	int wit = 0;
    	int rood = 0;
    	int oranje = 0;
    for(int floor = 0; floor < getNumberOfFloors(); floor++) {	
        for(int row = 0; row < getNumberOfRows(); row++) {
            for(int place = 0; place < getNumberOfPlaces(); place++) {
                Location location = getLocationManager().getLocation(floor, row, place);
                Car car = getCarAt(location);
                Color color = car == null ? Color.white : car.getColor();
                	if(color==Color.blue ){
                		blauw = blauw + 1;
                	}
                	if(color==Color.red ){
                		rood = rood + 1;
                	}
                	if(color==Color.orange ){
                		oranje = oranje + 1;
                	}
                	if(color==Color.white ){
                		wit = wit + 1;
                	}
                }
            }
    	}
		color[0] = blauw;
		color[1] = rood;
		color[2] = oranje;
		color[3] = wit;
    }
}