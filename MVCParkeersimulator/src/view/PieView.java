package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import model.Model;

public class PieView extends AbstractView {
	private static final long serialVersionUID = 1L;
	 private Image pieImage; 

	public PieView(Model model) {
		super(model);
		setSize(200, 200);
	}

	public void updateView() {
		//amount of empty spots
		double emptySpots=getModel().getNumberOfOpenSpots();
		
		double totalSpots = getModel().getTotalParkingSpots();
		double filledSpots = totalSpots - emptySpots;
		emptySpots = (emptySpots * 360 / totalSpots);
		filledSpots = (filledSpots * 360 / totalSpots);

		emptySpots =Math.round(emptySpots);
		filledSpots = Math.round(filledSpots);
		
		//System.out.println("empty: " + emptySpots + " | filled: " + filledSpots);

		pieImage = createImage(200, 200);
	    Graphics graphics = pieImage.getGraphics();
	    graphics.setColor(Color.WHITE);
	    graphics.fillRect(0, 500, 200, 200);
	    graphics.setColor(Color.GRAY);
	    graphics.fillArc(10, 510, 180, 180, 90, (int) emptySpots);
	    graphics.setColor(Color.RED);
		graphics.fillArc(10, 510, 180, 180, (int) emptySpots + 90, (int) filledSpots);
	}
	
	public void paintComponent(Graphics graphics) {
		graphics.drawImage(pieImage, 0, 500, 800, 500,null);
	}
}
