package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import model.Model;

public class QueueListView extends AbstractView {
	private Map<Color, Integer> cars =	new LinkedHashMap<Color, Integer>();
	private Map<Color, Integer> cars2 =	new LinkedHashMap<Color, Integer>();
	public QueueListView(Model model) {
		super(model);
		this.model = model;
		setVisible(true);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		int x = 1;
		double total = 35;
		
		if(model.day <5){
			cars.put(Color.red, (int)(model.getSizeEntranceCarQueue()*500/model.getWeekDayArrivals()*60*1.3));
		} else {
			cars.put(Color.red, (int)(model.getSizeEntranceCarQueue()*500/model.getWeekendArrivals()*60*1.3));
		}
		
		int lastPosX = 0;
		g.setColor(Color.WHITE);
		g.fillRect(lastPosX, 0, 500, 10);
		for (Color color : cars.keySet()){
			int value = cars.get(color);
			g.setColor(color);
			g.fillRect(lastPosX, 0, value, 10);
			lastPosX = value;
			x += (20+2);
		}

		if(model.day <5){
		cars2.put(Color.orange, (int)(model.getSizeReservedQueue()*500/model.getWeekDayReservedArrivals()*60*1.3));
		cars2.put(Color.blue, (int)(model.getSizeEntrancePassQueue()*500/model.getWeekDayPassArrivals()*60*1.3));
		} else {
			cars2.put(Color.orange, (int)(model.getSizeReservedQueue()*500/model.getWeekendReservedArrivals()*60*1.3));
			cars2.put(Color.blue, (int)(model.getSizeEntrancePassQueue()*500/model.getWeekendPassArrivals()*60*1.3));
		}
		lastPosX = 0;
		g.setColor(Color.WHITE);
		g.fillRect(lastPosX, 15, 500, 10);
		for (Color color : cars2.keySet()){
			int value = cars2.get(color);
			g.setColor(color);
			g.fillRect(lastPosX, 15, value, 10);
			lastPosX = value;
			x += (20+2);
		}
		repaint();
	}
}
