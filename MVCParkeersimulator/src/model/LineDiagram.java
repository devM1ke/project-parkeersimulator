package model;

import java.util.ArrayList;

import view.LineDiagramView;

public class LineDiagram extends AbstractModel {
	private ArrayList<Integer> earnings;
	private ArrayList<Integer> oldearnings;
	private LineDiagramView linediagram;
	private int max = 500;
	private int highest = 0;
	private boolean oneTime= true;
	public LineDiagram() {
		earnings = new ArrayList<Integer>();
		oldearnings = new ArrayList<Integer>();
	}
	public void addToEarning(int dailyearning, int price) {
		if(oneTime) {
			max = (int) (dailyearning/100);
			oneTime = false;
			earnings.add(0);
		}
		
		int dailyearnings = dailyearning / 100;
		if(dailyearnings > highest) {
			highest = dailyearnings;
			max = (int) (dailyearnings*120/100);
		}
		earnings.add(dailyearnings);
		oldearnings.add(dailyearnings);
	}
	public ArrayList getEarnings() {
		return earnings;
	}
	public int getMax() {
		return max;
	}
	
	
}
