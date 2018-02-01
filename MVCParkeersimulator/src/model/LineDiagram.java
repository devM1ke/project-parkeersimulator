package model;

import java.util.ArrayList;

import view.LineDiagramView;

public class LineDiagram extends AbstractModel {
	private ArrayList earnings;
	private LineDiagramView linediagram;
	private int max = 10000;
	public LineDiagram() {
		earnings = new ArrayList<Integer>();
		
	}
	public void addToEarning(int dailyearning, int price) {
		int dailyearnings = dailyearning*100/max;
		earnings.add(dailyearnings);
	}
	public ArrayList getEarnings() {
		return earnings;
	}
	
	
}
