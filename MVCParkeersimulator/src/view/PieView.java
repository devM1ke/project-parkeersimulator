package view;

import java.awt.*;
import javax.swing.*;
import model.Model;

public class PieView extends AbstractView{
	private static final long serialVersionUID = 1L;
	private Model model;
	private Dimension size;
	public PieView(Model model) {
		super(model);
		this.model = model;
		
		size = new Dimension(0,0);
		//setSize(200, 200);
		JLabel pietitle = new JLabel("Taart Diagram");
		add(pietitle);
	}
	public Dimension getPreferredSize() {
	    return new Dimension(200, 200);
	}
	public void paintComponent(Graphics g) {
		//amount of empty spots
		super.paintComponent(g);

		double totalSpots = getModel().getTotalParkingSpots();

		double blueSpots = model.color[0];
		double redSpots = model.color[1];
		double orangeSpots = model.color[2];
		double whiteSpots = model.color[3];
		double yellowSpots = model.color[4];
		double lightblueSpots = model.color[5];		
		
		blueSpots = (blueSpots * 360 / totalSpots);
		redSpots = (redSpots * 360 / totalSpots);
		orangeSpots = (orangeSpots * 360 / totalSpots);
		whiteSpots = (whiteSpots * 360 / totalSpots);
		yellowSpots = (yellowSpots * 360 / totalSpots);
		lightblueSpots = (lightblueSpots * 360 / totalSpots);

		blueSpots =Math.round(blueSpots);
		redSpots = Math.round(redSpots);
		orangeSpots =Math.round(orangeSpots);
		whiteSpots = Math.round(whiteSpots);
		yellowSpots = Math.round(yellowSpots);
		lightblueSpots = Math.round(lightblueSpots);
		g.setColor(Color.DARK_GRAY);
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
		//g.fillArc(10, 510, 180, 180, 90, 90);
		
		repaint();
	}	
}
