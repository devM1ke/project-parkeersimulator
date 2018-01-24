package view;

import java.awt.Color;
import java.awt.Graphics;
import model.Model;

public class PieView extends AbstractView{
	private static final long serialVersionUID = 1L;

	public PieView(Model model) {
		super(model);
	}

	public void paintComponent(Graphics g) {
		//amount of empty spots
		double emptySpots=getModel().getNumberOfOpenSpots();
		
		double totalSpots = getModel().getTotalParkingSpots();
		double filledSpots = totalSpots - emptySpots;
		emptySpots = (emptySpots * 360 / totalSpots);
		filledSpots = (filledSpots * 360 / totalSpots);

		emptySpots =Math.round(emptySpots);
		filledSpots = Math.round(filledSpots);
		
		System.out.println("empty: " + emptySpots + " | filled: " + filledSpots);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 500, 200, 200);
		g.setColor(Color.GRAY);
		g.fillArc(10, 510, 180, 180, 90, (int) emptySpots);
		g.setColor(Color.RED);
		g.fillArc(10, 510, 180, 180, (int) emptySpots + 90, (int) filledSpots);
	}	
}
