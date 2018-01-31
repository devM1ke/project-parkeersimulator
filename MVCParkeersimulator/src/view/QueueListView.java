package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import model.Model;

public class QueueListView extends AbstractView {
	private Map<Color, Integer> cars =	new LinkedHashMap<Color, Integer>();
	public QueueListView(Model model) {
		super(model);
		this.model = model;
		setVisible(true);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		int x = 1;
		double total = model.getTotalParkingSpots();
		//cars.put(Color.red, model.getSizeEntranceCarQueue());
		//cars.put(Color.orange, (int) (model.color[2]/total*100));
		//cars.put(Color.blue, model.getSizeEntrancePassQueue());
		//cars.put(Color.WHITE, (int) (model.getTotalParkingSpots()/total*100));
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
		repaint();
	}
}
