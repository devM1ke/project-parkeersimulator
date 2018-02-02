package view;

import java.awt.Graphics;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Model;
/**
 * klasse voor het toevoegen van een afbeelding aan de GUI
 * 
 * @author Mike
 * @version 1
 * @since 29-01-2018
 */
public class ImageView extends AbstractView{

    private ImageIcon image;
    private JLabel label;
    
    /**
     * image aanmaken deze vervolgens aan een label meegeven en deze vervolgens toevoegen
     * @param model
     */
    public ImageView(Model model) {
    	super(model);
  
    	image = new ImageIcon(getClass().getResource("WBCG-CityParking-Logo.jpg"));
    	label = new JLabel(image);
    	add(label);
    }
    
}   
