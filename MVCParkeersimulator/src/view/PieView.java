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
		double emptySpots=getModel().getNumberOfOpenSpots();
		
		double totalSpots = getModel().getTotalParkingSpots();
		double filledSpots = totalSpots - emptySpots;
		emptySpots = (emptySpots * 360 / totalSpots);
		filledSpots = (filledSpots * 360 / totalSpots);

		emptySpots =Math.round(emptySpots);
		filledSpots = Math.round(filledSpots);
		
		//System.out.println("empty: " + emptySpots + " | filled: " + filledSpots);
		//g.drawString("PieView", 10, 10);
		g.setColor(Color.GRAY);
		g.fillArc(0, 0, 160, 160, 90, (int) emptySpots);
		g.setColor(Color.RED);
		g.fillArc(0, 0, 160, 160, (int) emptySpots + 90, (int) filledSpots);
		//g.fillArc(10, 510, 180, 180, 90, 90);
		
		repaint();
	}	
}
