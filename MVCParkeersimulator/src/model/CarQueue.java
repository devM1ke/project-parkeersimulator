package model;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {
    private Queue<Car> queue = new LinkedList<>();

    public boolean addCar(Car car) {
        return queue.add(car);
    }

    public Car removeCar() {
        return queue.poll();
    }
    
    public Car getRef() {
    	return queue.element();
    }

    public int carsInQueue(){
    	return queue.size();
    }
    public int getPassInQueue(){
    	int pass = 0;
    	 Iterator<Car> it = queue.iterator();
    	    while(it.hasNext()) {
    	    	Car c = it.next();
        	if(c.getColor() == Color.blue){
        		pass++;
        	}
        }
    	return pass;
    }
    public int getReservedInQueue(){
    	int reserved = 0;
    	 Iterator<Car> it = queue.iterator();
 	    while(it.hasNext()) {
 	    	Car c = it.next();
        	if(c.getColor() == Color.orange){
        		reserved++;
        	}
        }
    	return reserved;
    }
    public void advanceWaitingTime(){
    	 Iterator<Car> it = queue.iterator();
 	    while(it.hasNext()) {
 	    	Car c = it.next();
 	    	
 	    	if(c.getTimeWillingToWait()==0){
 	    		
 	    		queue.remove(c);
 	    	}else if(c.getTimeWillingToWait() > 0) {
 	    		int time = c.getTimeWillingToWait();
 	    		time = time - 1;
 	    		c.setTimeWillingToWait(time);
 	    	}
 	    }
    }
}
