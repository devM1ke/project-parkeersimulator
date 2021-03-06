package model;

/**
 * Model is the heart of our project.
 * It is the place where most things are done.
 * 
 *
 * @author Ben, Joeri, Kerwin, Mike and Jelle.
 * @version 8.5
 * @since 20-01-2018
 */
import java.util.ArrayList;
import java.util.Random;
import model.AdHocCar;
import model.Car;
import model.CarQueue;
import model.Location;
import model.ParkingPassCar;
import model.LocationManager;
import model.ReservationManager;
import java.awt.*;

public class Model extends AbstractModel implements Runnable {
	//adding variables for the managementview
	public int price = 5;
	public int howmanydays = 7;
	public int[] dailyearningdays;
	public int dailyearnings = 0;
	public int[] color = new int[6];
	// adding variables to limit the queue size and counting the cars that left the queue.
	public int queueNormalSize = 30;
	public int queuePassSize = 30;
	public int left;
	//variables for creating the carpark.
	private int numberOfFloors = 3;
    private int numberOfRows = 6;
    private int numberOfPlaces = 30;
    private int numberOfOpenSpots;
    private Car[][][] cars;
    volatile private boolean run;
    //variables for the different types of cars.
	private static final String AD_HOC = "1";
	private static final String PASS = "2";
	// variables for adding the different queues.
	private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    //creating the different managers used in model.
    private LocationManager locationManager;
    private ReservationManager reservationManager;
    private SoundManager soundmanager;
    private LineDiagram linediagram;
    // variables for keeping track of time.
    public int day = 0;
    public int hour = 0;
    public int minute = 0;
    // the delay in ticks.
    private int tickPause = 100;
    
    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 175; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour
    int weekDayReservations = 50; // average number of arriving cars per hour
    int weekendReservations = 50; // average number of arriving cars per hour
    int maxNumberofPassCars = 75; // max number of passholders there can be
    
    double partOfCar = 0;
    
    int enterSpeed = 20; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute
    // the runner thread
    Thread runner;
    
    public Model() {
    	//initialising the variables discribed above
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();
        dailyearningdays = new int[howmanydays];
        locationManager = new LocationManager(numberOfFloors, numberOfRows, numberOfPlaces);
        reservationManager = new ReservationManager();
        soundmanager = new SoundManager();
        linediagram = new LineDiagram();
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        this.runner = new Thread(this);
        this.runner.start();
    }
	//starts the simulation
	public void start() {
		run=true;
	}
	// stops the simulation
	public void stop() {
		run=false;
	}
	// runs the simulation for the given ticks given to the parameter
	public void runFor(int tick) {
		run=false;
		int buffTickPause = this.tickPause;
		tickPause = 0;
        for(int i = 0; i < tick; i++) {
        	tick();
        }
        tickPause = buffTickPause;
	}
	// overriding the run method from thread to be used in our project.
	@Override
	public void run() {
        minute--;
        tick();
		while(true) {
			if(run) {
				tick(); 
			}
		}
	}
    // advances everything at every tick.
    public void tick() {
		try {
			Thread.sleep(this.tickPause);
		} catch (Exception e) {}
		
    	advanceTime();
    	handleExit();
    	setReservation();
    	notifyViews();
    	handleEntrance();
    	getTypeCar();
    	removeReservations();


        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = locationManager.getLocation(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                    location.tick();
                    
                }
            }
        }
    }
    // gets the max of the numbers of cars
    public int getMaxNumberofPassCars() {
    	return this.maxNumberofPassCars;
    }
    // sets the max of the numbers of cars
    public void setMaxNumberofPassCars(int c) {
    	this.maxNumberofPassCars = c;
    }
    // gets the size of the normal queue
    public int getQueueNormalSize() {
    	return this.queueNormalSize;
    }
    // sets the size of the normal queue
    public void setQueueNormalSize(int queueNormalSize)
    {
    	this.queueNormalSize = queueNormalSize;
    }
    // gets the size of the pass queue
    public int getQueuePassSize() {
    	return this.queuePassSize;
    }
    // sets the size of the pass queue  
    public void setQueuePassSize(int queuePassSize)
    {
    	this.queuePassSize = queuePassSize;
    }
    // gets the average number per hour of reserved cars during the week days
    public int getWeekdayReserveArrivals() {
    	return this.weekDayReservations;
    }
    // sets the average number per hour of reserved cars during the week days
    public void setWeekdayReserveArrivals(int c) {
    	this.weekDayReservations = c;
    }
    // gets the average number per hour of reserved cars during the weekend
    public int getWeekendReserveArrivals() {
    	return this.weekendReservations;
    }
    // sets the average number per hour of reserved cars during the weekend
    public void setWeekendReserveArrivals(int c) {
    	this.weekendReservations = c;
    }
    // gets the average number per hour of normal cars during the week
    public int getWeekDayArrivals()
    {
    	return this.weekDayArrivals;
    }
    // sets the average number per hour of normal cars during the week
    public void setWeekDayArrivals(int weekDayArrivals)
    {
    	this.weekDayArrivals = weekDayArrivals;
    }
    // gets the average number per hour of normal cars during the weekend
    public int getWeekendArrivals()
    {
    	return this.weekendArrivals;
    }
    // sets the average number per hour of normal cars during the weekend
    public void setWeekendArrivals(int weekendArrivals)
    {
    	this.weekendArrivals = weekendArrivals;
    }
    // gets the average number per hour of pass cars during the week
    public int getWeekDayPassArrivals()
    {
    	return this.weekDayPassArrivals;
    }
    // sets the average number per hour of pass cars during the week
    public void setWeekDayPassArrivals(int weekDayPassArrivals)
    {
    	this.weekDayPassArrivals = weekDayPassArrivals;
    }
    // gets the average number per hour of pass cars during the weekend
    public int getWeekendPassArrivals()
    {
    	return this.weekendPassArrivals;
    }
    // sets the average number per hour of pass cars during the weekend
    public void setWeekendPassArrivals(int weekendPassArrivals)
    {
    	this.weekendPassArrivals = weekendPassArrivals;
    }
    // gets the tickpause
    public int getTickPause()
    {
        return this.tickPause;
    }
    // sets the tickpause
    public void setTickPause(int ticks)
    {
        this.tickPause = ticks;
    }
    // gets the day
    public int getDay(){
    	return day;
    }
    // gets the hour
    public int getHour(){
    	return hour;
    }
    // gets the minute
    public int getMinute(){
    	return minute;
    }
    // gets the earnings for the linediagram
    public ArrayList getEarnings() {
    	return linediagram.getEarnings();
    }
    // get the highest value of earnings
    public int getMax() {
    	return linediagram.getMax();
    }
    // advances the time by 1 minute.
    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            linediagram.addToEarning(dailyearnings, price);
			soundmanager.play("Coin_Sound.wav");			

			setDailyEarningZero();
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }
    // sets the dailyearnings to zero and adds them to the specified weekday earnings.
	public void setDailyEarningZero(){
		for (int i = 0; i < howmanydays; i++)
		{
        if (getDay()==i) {
        	dailyearningdays[i] = dailyearnings;
        	dailyearnings = 0;       
        }}

		
	}
	// calculates the earnings still to be earned by getting the number of cars in the garage and multiplies them by the price.
	public int stillToBeEarned(){
		return (getTotalParkingSpots()-getNumberOfOpenSpots())*price;
	}
	// gets the price
	public int getPrice() {
		
		return this.price;
	}
	// sets the price
	public void setPrice(int price){
		this.price = price;
	}
    // gets the number of floors
	public int getNumberOfFloors() {
        return numberOfFloors;
    }
	//gets the number of rows
    public int getNumberOfRows() {
        return numberOfRows;
    }
	// gets the number of places
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }
    // gets the number of open spots
    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    // gets the total number of parkingspots
    public int getTotalParkingSpots() {
    	return numberOfFloors * numberOfRows * numberOfPlaces;
    }
    //gets the locationmanager
    public LocationManager getLocationManager() {
    	return locationManager;
    }
    // gets the car at a specified location
    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }
    // sets the car at a specified location
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
    // removes the car at a specified location
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
    // gets the first free location in the garage
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
    		if(color == Color.blue) {
        		for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                    for (int row = 0; row < getNumberOfRows(); row++) {
                        for (int place = 0; place < getNumberOfPlaces(); place++) {
                        	Location location = getLocationManager().getLocation(floor, row, place);
    	                    if (getCarAt(location) == null && location.getType() == 0) {
    	                        return location;
    	                    }
    	                    
    	                }
    	            }
    			}
        	}return null;	
    	}
    	else {
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
    }
    // gets the rigth reservation spot for the specified car by looking at their numberplates
    public Location getReservationLocation(int numberPlate) {
    	
    	for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = getLocationManager().getLocation(floor, row, place);
                	if(location.getType() == 2 && location.getNumberPlate() == numberPlate) {
                		return location;
                	}
                }
            }
    	}
		return null;
    }
    // gets the first leaving cars
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
    //removes the reservation
    public void removeReservations() {
    	for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = getLocationManager().getLocation(floor, row, place);
                    if (location.getTimer() == 0 && location.getType() == 2) {
                        location.setType(0);
                    }
                }
            }
        }
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
    	left = left + entrancePassQueue.advanceWaitingTime();
    	left = left + entranceCarQueue.advanceWaitingTime();
    	int numberOfCars=getNumberOfCars(weekDayReservations, weekendReservations, 2);
    	addReservations(numberOfCars);
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);
    }
 
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void carsArriving(){
    	ArrayList<ReservationCar> reservationCars;
    	reservationCars = reservationManager.getReservationCars();
    	
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals, 0);
    	addArrivingCars(numberOfCars, AD_HOC);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals, 1);
    	addArrivingCars(numberOfCars, PASS);  
        for(int i = 0; i < reservationCars.size(); i++) {
        	if(reservationCars.get(i).getArriveDay() == day &&
        			reservationCars.get(i).getArriveHour() == hour &&
        				reservationCars.get(i).getArriveMinute() == minute) {
        		 if(entrancePassQueue.carsInQueue() <= queuePassSize){
        		addReservationCar(reservationCars.get(i), reservationCars.get(i).getNumberPlate());
        		 }
        	}
        }
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
    		Car car = queue.getRef();
    		if(car.getColor() == Color.orange) {
    			Location freeLocation = getReservationLocation(car.getNumberPlate());
                if(freeLocation != null) {
    	            setCarAt(freeLocation, car);
    	            i++;
    	            car = queue.removeCar();
    	            freeLocation.setType(0);
    	            freeLocation.setNumberPlate(-1);
    	            freeLocation.setTimerToZero();
    	            break;
                }
                else {
                	car = queue.removeCar();
                }
    		}
    		else {
	            Location freeLocation = getFirstFreeLocation(car.getColor());
	            if(freeLocation != null) {
		            setCarAt(freeLocation, car);
		            i++;
		            car = queue.removeCar();
	            } 
	            else {
		            i++;
	            }
    		}
        }
    }
    
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
        	if (car.getHasToPay()){
	            car.setIsPaying(true);
	            if(car.getColor()==Color.orange){
	            dailyearnings = (int) (dailyearnings + price*1.2);
	            }else {
	            	dailyearnings = dailyearnings + price;
	            }
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
    
    private int getNumberOfCars(int weekDay, int weekend, int typeCar){
        Random random = new Random();        
        
        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;
        
        /*
        change the number of incoming cars based on the day and time of day
        day 0 is monday, day 1 is tuesday, day 2 is wednesday, day 3 is thursday,
        day 4 is friday, day 5 is saturday, day 6 is sunday.
        */
        switch(day) {
	    	case 0:
	    		if(hour < 8 || hour > 16) { 
	    			switch(typeCar) {
    				case 0:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.25);
    					break;
    				case 1:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.15);
    					break;
    				case 2:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0);
    					break;
	    			}
		        }
	    		break;
	    	case 1:
	    		if(hour < 8 || hour > 16) { 
	    			switch(typeCar) {
    				case 0:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.25);
    					break;
    				case 1:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.15);
    					break;
    				case 2:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0);
    					break;	    			
	    			}
		        }
	    		break;
	    	case 2:
	    		if(hour < 8 || hour > 16) { 
	    			switch(typeCar) {
    				case 0:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.25);
    					break;
    				case 1:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.15);
    					break;
    				case 2:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0);
    					break;	    			
	    			}
		        }
	    		break;
	    	case 3:
	    		if(hour < 8 || hour >= 21) { 
	    			switch(typeCar) {
    				case 0:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.25);
    					break;
    				case 1:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.15);
    					break;
    				case 2:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0);
    					break;	    			
	    			}
	    		}
	    		else if(hour > 16) {
	    			switch(typeCar) {
	    				case 0:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 1.25);
	    					break;
	    				case 1:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.15);
	    					break;
	    				case 2:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 1.25);
	    					break;
		    		}
	    			
	    		}
	    		break;
	    	case 4:
	    		if(hour < 8 || hour > 21) { 
	    			switch(typeCar) {
	    				case 0:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.25);
	    					break;
	    				case 1:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.15);
	    					break;
	    				case 2:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0);
	    					break;
		    			}
		        }
	    		else if(hour > 18) {
	    			switch(typeCar) {
	    				case 0:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 3);
	    					break;
	    				case 1:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.2);
	    					break;
	    				case 2:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 1.5);
	    					break;
		    		}
	    		}
	    		break;
	    	case 5:
	    		if(hour < 8 || hour > 22) { 
	    			switch(typeCar) {
    				case 0:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.25);
    					break;
    				case 1:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.15);
    					break;
    				case 2:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0);
    					break;	    			
	    			}
		        }
	    		else if(hour >= 18) {
	    			switch(typeCar) {
	    				case 0:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 1.5);
	    					break;
	    				case 1:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 2);
	    					break;
	    				case 2:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 1);
	    					break;		    			
		    		}
	    		}
	    		break;
	    	case 6:
	    		if(hour < 8 || hour > 17) { 
	    			switch(typeCar) {
    				case 0:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.25);
    					break;
    				case 1:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0.15);
    					break;
    				case 2:
    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 0);
    					break;	    			
	    			}
		        }
	    		else if(hour >= 12) {
	    			switch(typeCar) {
	    				case 0:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 1.5);
	    					break;
	    				case 1:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 2);
	    					break;
	    				case 2:
	    					averageNumberOfCarsPerHour = (int) (averageNumberOfCarsPerHour * 1);
	    					break;		    			
		    		}
	    		}
	    		break;
        }
        
        //Calculate the number of cars that arrive per minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        double numberOfCarsPerMinute = numberOfCarsPerHour / 60;
        int roundNumberOfCarsPerMinute = (int)Math.round(numberOfCarsPerMinute);
        if(roundNumberOfCarsPerMinute == 0) {
        	partOfCar = partOfCar + numberOfCarsPerMinute;
        	if(partOfCar >= 1) {
        		int carNumber = 1;
        		partOfCar = 0;
        		return carNumber;
        	}
        	else return roundNumberOfCarsPerMinute;
        }
        else {
        	return roundNumberOfCarsPerMinute;
        }        
    }
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case AD_HOC: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new AdHocCar());
                if(entranceCarQueue.carsInQueue() >= queueNormalSize){
                Car c = entranceCarQueue.getLastCar();
                	entranceCarQueue.removeSpecificCar(c);
                	left++;
                }
            }
            break;
    	case PASS:
        	int newNumberOfCars =checkNumberOfPassCars(numberOfCars);
            for (int i = 0; i < newNumberOfCars; i++) { 
	            	entrancePassQueue.addCar(new ParkingPassCar());
	                if(entrancePassQueue.carsInQueue() >= queuePassSize){
	            	  Car c = entrancePassQueue.getLastCar();
	            	  entrancePassQueue.removeSpecificCar(c);
	              	left++;
	                }
            	
            }
            break;	
    	}
    }
    
    private void addReservationCar(ReservationCar car, int numberPlate) {
    	entrancePassQueue.addCar(car);
    }
    
    private void addReservations(int numberOfCars) {
    	for (int i = 0; i < numberOfCars; i++) {
        	reservationManager.addNewReservation(day, hour, minute);
        }
    }
    
    private void carLeavesSpot(Car car){
    	removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }

    public void getTypeCar(){
    	int lichtblauw = 0;
    	int geel = 0;
    	int blauw = 0;
    	int rood = 0;
    	int oranje = 0;
    	int wit = 0;
    for(int floor = 0; floor < getNumberOfFloors(); floor++) {	
        for(int row = 0; row < getNumberOfRows(); row++) {
            for(int place = 0; place < getNumberOfPlaces(); place++) {
                Location location = getLocationManager().getLocation(floor, row, place);
                if(location.getType()== 0 && getCarAt(location) == null){
                	wit = wit +1;
                }
                if(location.getType()== 1 && getCarAt(location) == null){
                	lichtblauw = lichtblauw +1;
                }
                if(location.getType()== 2 && getCarAt(location) == null){
                	geel = geel +1;
                }
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
                	
                }
            }
    	}
    	
		color[0] = blauw;
		color[1] = rood;
		color[2] = oranje;
		color[3] = wit;
		color[4] = geel;
		color[5] = lichtblauw;
    }
    
    public void setReservation() {
    	ArrayList<Reservation> reservations = reservationManager.getReservations();
    	int reservationMinute = minute;
    	int reservationHour = hour;
    	int reservationDay = day;
    	for(int teller = 0; teller < 15; teller++) {
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
    	
    	for(int i = 0; i < reservations.size(); i++) {
    		
    		
    		if(reservations.get(i).getReservationDay() == reservationDay && 
    				reservations.get(i).getReservationHour() == reservationHour && 
    						reservations.get(i).getReservationMinute() == reservationMinute &&
    						reservations.get(i).getIsSet() == false) {
    			outerloop: for (int floor = 0; floor < getNumberOfFloors(); floor++) {
                    for (int row = 0; row < getNumberOfRows(); row++) {
                        for (int place = 0; place < getNumberOfPlaces(); place++) {
                        	Location location = getLocationManager().getLocation(floor, row, place);
    	                    if (getCarAt(location) == null && location.getType() == 0) {
    	                    	location.setType(2);
    	                    	location.setNumberPlate(reservations.get(i).getReservationNumberPlate());
    	                    	location.setTimer();
    	                    	reservations.get(i).setIsSet();
    	                    	break outerloop;
    	                    }
                    	}
                    }
                }
    		}
    	}
    }
    
    public int checkNumberOfPassCars(int newCars) {
    	if(newCars == 0) {
    		return 0;
    	}
    	int numberOfPassCars;
    	numberOfPassCars = 0;
    	for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                	Location location = getLocationManager().getLocation(floor, row, place);
                	Car car = getCarAt(location);
                	Color color = car == null ? Color.white : car.getColor();
                	if(color == Color.blue) {
                		numberOfPassCars++;
                	}
                }
            }
    	}
    	if(numberOfPassCars + getSizeEntrancePassQueue() + newCars < maxNumberofPassCars) {
    		return newCars;
    	}
    	else {
    		int difference = maxNumberofPassCars - getSizeEntrancePassQueue() - numberOfPassCars;
    		if(difference > 0) {
    			return difference;
    		}
    		else {
    			return 0;
    		}
    	}
    }
    
    public int getSizeEntranceCarQueue(){
    	return entranceCarQueue.carsInQueue();
    }
    public int getSizeEntrancePassQueue(){
    	return entrancePassQueue.getPassInQueue().size();
    }
    public int getSizeExitCarQueue(){
    	return exitCarQueue.carsInQueue();
    }
    public int getSizePaymentCarQueue(){
    	return paymentCarQueue.carsInQueue();
    }
    public int getSizeReservedQueue(){
    	return entrancePassQueue.getReservedInQueue().size();
    }
    public int getLeft(){
    	return left;
    }
    public int getWeekendReservedArrivals()
    {
    	return this.weekendReservations;
    }
    
    public void setWeekendReservedArrivals(int weekendReservations)
    {
    	this.weekendReservations = weekendReservations;
    }
    public int getWeekDayReservedArrivals()
    {
    	return this.weekDayReservations;
    }
    
    public void setWeekDayReservedArrivals(int weekDayReservations)
    {
    	this.weekDayReservations = weekDayReservations;
    }
}