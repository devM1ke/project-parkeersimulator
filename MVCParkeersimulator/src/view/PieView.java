package view;

import java.awt.*;
import javax.swing.*;
import model.Model;

public class PieView extends AbstractView{
	private static final long serialVersionUID = 1L;
	private Model model;

	private double blueSpots, redSpots, orangeSpots, whiteSpots, yellowSpots, lightblueSpots;
	public PieView(Model model) {
		super(model);
		this.model = model;
		
		JLabel pietitle = new JLabel("Taart Diagram");
		add(pietitle);
	}
	public Dimension getPreferredSize() {
	    return new Dimension(200, 200);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		double totalSpots = getModel().getTotalParkingSpots();
		blueSpots = Math.round((model.color[0] * 360 / totalSpots));
		redSpots = Math.round((model.color[1] * 360 / totalSpots));
		orangeSpots = Math.round((model.color[2] * 360 / totalSpots));
		whiteSpots = Math.round((model.color[3] * 360 / totalSpots));
		yellowSpots = Math.round((model.color[4] * 360 / totalSpots));
		lightblueSpots = Math.round((model.color[5] * 360 / totalSpots));
		g.setColor(Color.RED);
		g.fillArc(0, 20, 160, 160, 90, (int) redSpots);
		g.setColor(Color.ORANGE);
		g.fillArc(0, 20, 160, 160, (int) redSpots + 90, (int) orangeSpots);
		g.setColor(Color.BLUE);
		g.fillArc(0, 20, 160, 160, (int) redSpots + (int) orangeSpots + 90, (int) blueSpots);
		g.setColor(Color.yellow);
		g.fillArc(0, 20, 160, 160, (int) redSpots + (int) orangeSpots + (int) blueSpots + 90, (int) yellowSpots);
		g.setColor(Color.cyan);
		g.fillArc(0, 20, 160, 160, (int) redSpots + (int) orangeSpots + (int) blueSpots + (int)yellowSpots + 90, (int) lightblueSpots);
		g.setColor(Color.white);
		g.fillArc(0, 20, 160, 160, (int) redSpots + (int) orangeSpots + (int) blueSpots + (int)yellowSpots + (int) lightblueSpots + 90, (int) whiteSpots);
		repaint();
	}	
}
