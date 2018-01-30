package model;

import java.awt.Color;
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
    	for(int i=0; i < this.carsInQueue();i++){
        	if(queue.element().getColor() == Color.blue){
        		System.out.println(queue.element().getColor());
        		pass++;
        	}
        }
    	return pass;
    }
    public int getReservedInQueue(){
    	int reserved = 0;
    	for(int i=0; i < this.carsInQueue();i++){
        	if(queue.element().getColor() == Color.orange){
        		reserved++;
        	}
        }
    	return reserved;
    }
}
