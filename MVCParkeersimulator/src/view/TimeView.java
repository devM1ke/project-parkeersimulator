package view;

import controller.TimeController;
import model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * Shows date/time in GUI
 *
 * @author Mike
 * @version 1
 * @since 29-01-2018
 */
public class TimeView extends AbstractView {

    
	private TimeController timeController;
    private JLabel day,time;


    /**
     * Creating the date/time panel
     */
    public TimeView(Model model){
    	super(model);
    	timeController = new TimeController(model);
        // Getting the current Day, Week and Month
        String dayString = "Dag: "+ model.getDay();

        setSize(250, 50);

        //Creating the Time panel and the date/time JLabels
        day = new JLabel(dayString);
        time = new JLabel(timeController.getTime());
        

        //Adding the date/time labels to the Time panel

        add(day);
        add(time);


        setVisible(true);
    }

    public String getDay() {
    	String typeOfDay;
        int numberDay = model.getDay();
    	switch(numberDay) {
    	case 0:
    		typeOfDay = "Maandag";
    		break;
    		
    	case 1:
    		typeOfDay = "Dinsdag";
    		break;
    		
    	case 2:
    		typeOfDay = "Woensdag";
    		break;
    		
    	case 3:
    		typeOfDay = "Donderdag";
    		break;
    		
    	case 4:
    		typeOfDay = "Vrijdag";
    		break;
    		
    	case 5:
    		typeOfDay = "Zaterdag";
    		break;
    		
    	case 6:
    		typeOfDay = "Zondag";
    		break;
    		
    	default:
    		throw new IllegalArgumentException("Invalid day of the week: " + numberDay);
    	
    	}
    	
    	return typeOfDay;
    }
    /**
     * Updating the date/time view
     */
    public void paintComponent(Graphics g){ 
    	super.paintComponent(g);
        //Updating the date/time with every tick()

        day.setText("Dag: " + getDay());
        time.setText("Tijd: " + timeController.getTime());


        repaint();
    }

    public void setVisibility(boolean visibility)
    {
        setVisible(visibility);
    }
}

