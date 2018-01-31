package model;

import java.awt.*;
import java.util.Random;

public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    public int timeWillingToWait;

    /**
     * Constructor for objects of class Car
     */
    public Car() {
    	Random random = new Random();
    	timeWillingToWait = (int)(random.nextInt(200)+100)/100*5;
    	//timeWillingToWait = 5;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }

    public void tick() {
        minutesLeft--;
    }

    public int getTimeWillingToWait(){
    	return timeWillingToWait;
    }
    public void setTimeWillingToWait(int time){
    	time=timeWillingToWait;
    }
    public void tickTimeWillingToWait(){
    	timeWillingToWait--;
    }
    public void setTimeWillingToWaitNull(){
    	timeWillingToWait = 100;
    }
    
    public abstract Color getColor();
    
    public abstract int getNumberPlate();
}