package view;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
		// determine longest bar
		int max = Integer.MIN_VALUE;

		for (Integer value : bars.values()){
			max = Math.max(max, value);
		}
		
		int width = (getWidth() / bars.size()) - 2;
		int x = 1;
		for (Color color : bars.keySet()){
			int value = bars.get(color);
			int height = (int)
			((getHeight()-5) * ((double)value / max));
			g.setColor(color);
			g.fillRect(x, getHeight() - height, width, height);
			g.setColor(Color.black);
			g.drawRect(x, getHeight() - height, width, height);
			x += (width + 2);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(bars.size() * 10 + 2, 50);
	}

			
	
}
