package view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Model;
/**
 * Displaying the bar graph witch contains the data about the carpark.
 *
 * @author Jelle
 * @version 1
 * @since 27-01-2018
 */



public class BarView extends AbstractView{
	//creating a hashmap wich contains the color to be displayed in the bar and which size it gets.
	private Map<Color, Integer> bars =	new LinkedHashMap<Color, Integer>();
	public BarView(Model model){
		super(model);
		this.model = model;
		setVisible(true);
	}
	// adding the bars
	public void addBar(Color color, int value){
		bars.put(color, value);
		repaint();
	}
	//painting the actual bar.
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//getting the data and making them into percentages.
		double totalSpots =getModel().getTotalParkingSpots();
		double redBar = model.color[1]/totalSpots*100;
		double orangeBar = model.color[2]/totalSpots*100;
		double blueBar = model.color[0]/totalSpots*100;
		double whiteBar = model.color[3]/totalSpots*100;
		double yellowBar = model.color[4]/totalSpots*100;
		double cyanBar = model.color[5]/totalSpots*100;
		//rounding the data to fit it into integers.
		redBar = Math.round(redBar);
		orangeBar = Math.round(orangeBar);
		blueBar = Math.round(blueBar);
		whiteBar = Math.round(whiteBar);
		yellowBar = Math.round(yellowBar);
		cyanBar = Math.round(cyanBar);
		//adding the bars tot the graph.
		bars.put(Color.red, (int)redBar);
		bars.put(Color.blue, (int)blueBar);
		bars.put(Color.cyan, (int)cyanBar);
		bars.put(Color.orange, (int)orangeBar);
		bars.put(Color.yellow, (int)yellowBar);
		bars.put(Color.white, (int)whiteBar); 
		// determine longest bar
		int max = Integer.MIN_VALUE;

		for (Integer value : bars.values()){
			max = Math.max(max, value);
		}
		//creating the graph.
		int width = (getWidth() / bars.size()) - 2;
		int x = 1;
		for (Color color : bars.keySet()){
			int value = bars.get(color);
			int height = (int)((getHeight()) * ((double)value / max));
			height = value;
			g.setColor(color);
			g.fillRect(x, getHeight() - height, width, height);
			g.setColor(Color.black);
			g.drawRect(x, getHeight() - height, width, height);	
			g.drawString(height+"%", x+7, 40);
			x += (width + 2);
		}
		repaint();
 
	}


			
	
}
