package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JLabel;

import model.AdHocCar;
import model.Model;
import model.ParkingPassCar;

public class LegendView extends AbstractView{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int y= 1;
	public LegendView(Model model){
		super(model);
		this.model = model;
		setVisible(true);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawLabel(g, "Lege plekken: ", Color.WHITE, 0);
		drawLabel(g, "non-abbo bezet: ", new AdHocCar().getColor(), 20);
		drawLabel(g, "abonnee plekken: ", Color.CYAN, 40);
		drawLabel(g, "abonnee plekken bezet: ", new ParkingPassCar().getColor(), 60);
		drawLabel(g, "gereserveerde plekken: ", new ParkingPassCar().getColor(), 80);
		drawLabel(g, "gereserveerde plekken bezet: ", Color.ORANGE, 100);
	}
	private void drawLabel(Graphics g, String label, Color color, int posX) {
		JLabel carLabel = new JLabel(label);
		add(carLabel);
		g.setColor(color);
	    g.fillRect(165, posX+10, 20, 10);
	    repaint();
	}

}
