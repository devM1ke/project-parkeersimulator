package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JLabel;

import model.Model;

public class LegendView extends AbstractView{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LegendView(Model model){
		super(model);
		this.model = model;
		setVisible(true);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawLabel(g, "Lege plekken: ", Color.WHITE);
		drawLabel(g, "non-abbo bezet: ", Color.RED);
	}
	private void drawLabel(Graphics g, String label, Color color) {
		JLabel carLabel = new JLabel(label);
		add(carLabel);
		g.setColor(color);
	    g.fillRect(carLabel.getX()+130, carLabel.getY()+9, 20, 10);
	    repaint();
	}

}
