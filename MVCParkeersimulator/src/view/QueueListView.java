package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JLabel;

import model.Model;

public class QueueListView extends AbstractView {
	private Map<Color, Integer> cars =	new LinkedHashMap<Color, Integer>();
	private Map<Color, Integer> cars2 =	new LinkedHashMap<Color, Integer>();
	public QueueListView(Model model) {
		super(model);
		this.model = model;
		setVisible(true);
		JLabel queuelabel = new JLabel("Wachtrijen");
		add(queuelabel);
	}
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		int x = 1;
		double total = 35;
		try{cars.put(Color.red, (int)(model.getSizeEntranceCarQueue()*500/model.queueNormalSize));} catch (Exception ex) {}
		int lastPosX = 0;
		g.setColor(Color.WHITE);
		g.fillRect(lastPosX, 30, 500, 10);
		for (Color color : cars.keySet()){
			int value = cars.get(color);
			g.setColor(color);
			g.fillRect(lastPosX, 30, value, 10);
			lastPosX = value;
			x += (20+2);
		}try{cars2.put(Color.orange, (int)(model.getSizeReservedQueue()*500/model.queuePassSize));} catch (Exception ex) {}
		try{cars2.put(Color.blue, (int)(model.getSizeEntrancePassQueue()*500/model.queuePassSize));} catch (Exception ex) {}
		lastPosX = 0;
		g.setColor(Color.WHITE);
		g.fillRect(lastPosX, 45, 500, 10);
		for (Color color : cars2.keySet()){
			int value = cars2.get(color);
			g.setColor(color);
			g.fillRect(lastPosX, 45, value, 10);
			lastPosX = value;
			x += (20+2);
		}
		repaint();
	}
}
