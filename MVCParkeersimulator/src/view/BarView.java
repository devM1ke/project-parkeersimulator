package view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.Map;
import model.Model;




public class BarView extends AbstractView{
	private Map<Color, Integer> bars =	new LinkedHashMap<Color, Integer>();
	public BarView(Model model){
		super(model);
		this.model = model;
		setVisible(true);
	}
	
	public void addBar(Color color, int value){
		bars.put(color, value);
		repaint();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		double totalSpots =getModel().getTotalParkingSpots();
		double redBar = model.color[1]/totalSpots*100;
		double orangeBar = model.color[2]/totalSpots*100;
		double blueBar = model.color[0]/totalSpots*100;
		double whiteBar = model.getNumberOfOpenSpots()/totalSpots*100;
		redBar = Math.round(redBar);
		orangeBar = Math.round(orangeBar);
		blueBar = Math.round(blueBar);
		whiteBar = Math.round(whiteBar);
		System.out.println(whiteBar);
		bars.put(Color.red, (int)redBar);
		bars.put(Color.orange, (int)orangeBar);
		bars.put(Color.blue, (int)blueBar);
		bars.put(Color.white, (int)whiteBar); 
		//System.out.println(model.getNumberOfOpenSpots());
		// determine longest bar
		int max = Integer.MIN_VALUE;

		for (Integer value : bars.values()){
			max = Math.max(max, value);
		}
		
		int width = (getWidth() / bars.size()) - 2;
		int x = 1;
		for (Color color : bars.keySet()){
			int value = bars.get(color);
			int height = (int)((getHeight()) * ((double)value / max));
			g.setColor(color);
			g.fillRect(x, getHeight() - height, width, height);
			g.setColor(Color.black);
			g.drawRect(x, getHeight() - height, width, height);
			x += (width + 2);
			
		}
		repaint();
 
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(bars.size() * 10 + 2, 50);
	}

			
	
}
