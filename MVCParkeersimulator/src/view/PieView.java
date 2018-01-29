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
		
		
		blueSpots = (blueSpots * 360 / totalSpots);
		redSpots = (redSpots * 360 / totalSpots);
		orangeSpots = (orangeSpots * 360 / totalSpots);
		whiteSpots = (whiteSpots * 360 / totalSpots);
		

		blueSpots =Math.round(blueSpots);
		redSpots = Math.round(redSpots);
		orangeSpots =Math.round(orangeSpots);
		whiteSpots = Math.round(whiteSpots);
		
		//System.out.println("empty: " + whiteSpots + " | Abonnees: " + blueSpots+ " | Reserveringen: " + orangeSpots+ " | Normale: " + redSpots);
		//g.drawString("PieView", 10, 10);
		g.setColor(Color.RED);
		g.fillArc(0, 0, 160, 160, 90, (int) redSpots);
		g.setColor(Color.ORANGE);
		g.fillArc(0, 0, 160, 160, (int) redSpots + 90, (int) orangeSpots);
		g.setColor(Color.BLUE);
		g.fillArc(0, 0, 160, 160, (int) redSpots + (int) orangeSpots + 90, (int) blueSpots);
		g.setColor(Color.WHITE);
		g.fillArc(0, 0, 160, 160, (int) redSpots + (int) orangeSpots + (int) blueSpots + 90, (int) whiteSpots);
		
		//g.fillArc(10, 510, 180, 180, 90, 90);
		
		repaint();
	}	
}
