package view;

import java.awt.Graphics;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Model;

public class ImageView extends AbstractView{

    private ImageIcon image;
    private JLabel label;
    public ImageView(Model model) {
    	super(model);
    //image = new ImageIcon(getClass().getResource("WBCG-CityParking-Logo.jpg"));
    
    }
    
    public void paintComponent(Graphics g) {
    	image = new ImageIcon(getClass().getResource("WBCG-CityParking-Logo.jpg"));
    	label = new JLabel(image);
    	add(label);
    }
}   
