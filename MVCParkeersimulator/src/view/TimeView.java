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
    private JPanel Time;

    /**
     * Creating the date/time panel
     */
    public TimeView(Model model){
    	super(model);
    	timeController = new TimeController(model);
        // Getting the current Day, Week and Month
        String dayString = "Day: "+model.getDay();


        setSize(250, 50);

        //Creating the Time panel and the date/time JLabels
        day = new JLabel(dayString);
        time = new JLabel(timeController.getTime());
        

        //Adding the date/time labels to the Time panel

        add(day);
        add(time);


        setVisible(true);
    }

    /**
     * Updating the date/time view
     */
    public void paintComponent(Graphics g){
        //Updating the date/time with every tick()
        day.setText("Day: " + model.getDay());
        time.setText("Hour: " + timeController.getTime());
        System.out.println(timeController.getTime());
        repaint();
    }

    public void setVisibility(boolean visibility)
    {
        setVisible(visibility);
    }
}

