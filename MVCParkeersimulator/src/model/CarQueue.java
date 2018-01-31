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
    public Car getLastCar(){
    	return((LinkedList<Car>) queue).getLast();
    }
    public void removeSpecificCar(Car c){
    	queue.remove(c);
    }
    
    public Car getRef() {
    	return queue.element();
    }

    public int carsInQueue(){
    	return queue.size();
    }
    public int getPassInQueue(){
    	int pass = 0;
    	Queue<Car> clone = new LinkedList<>();
    	Iterator<Car> itr = queue.iterator();
    	while(itr.hasNext()){
    		Car c = itr.next();
    		clone.add(c);
    	}
    	 Iterator<Car> it = clone.iterator();
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
    	Queue<Car> clone = new LinkedList<>();
    	Iterator<Car> itr = queue.iterator();
    	while(itr.hasNext()){
    		Car c = itr.next();
    		clone.add(c);
    	}
    	 Iterator<Car> it = clone.iterator();
 	    while(it.hasNext()) {
 	    	Car c = it.next();
        	if(c.getColor() == Color.orange){
        		reserved++;
        	}
        }
    	return reserved;
    }
    public int advanceWaitingTime(){
    	int aantal = 0;
    	 Iterator<Car> it = queue.iterator();
 	    while(it.hasNext()) {
 	    	Car c = it.next();
 	    	if(c.getTimeWillingToWait()==0){
 	    		it.remove();
 	    		aantal = 1;
 	    	}else if(c.getTimeWillingToWait() > 0) {
 	    		c.tickTimeWillingToWait();
 	    		aantal = 0;
 	    	}
 	    }return aantal;
    }
}
