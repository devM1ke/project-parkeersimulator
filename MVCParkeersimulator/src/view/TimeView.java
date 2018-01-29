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
    private JLabel month,week,day,time;
    private JPanel Time;

    /**
     * Creating the date/time panel
     */
    public TimeView(Model model){
    	super(model);

        // Getting the current Day, Week and Month
        String dayString = "Day: "+timeController.getDay();
        String weekString = "Week: "+timeController.getWeek();
        String monthString = "Month: "+timeController.getMonth();


        setSize(250, 50);
        setLayout(new GridLayout(1,3));

        //Creating the Time panel and the date/time JLabels
        Time = new JPanel(new GridLayout(1,4));
        month = new JLabel(monthString);
        week = new JLabel(weekString);
        day = new JLabel(dayString);
        time = new JLabel(timeController.getTime());

        //Adding the date/time labels to the Time panel
        Time.add(month);
        Time.add(week);
        Time.add(day);
        Time.add(time);
        
        add(Time);


        setVisible(true);
    }

    /**
     * Updating the date/time view
     */
    public void updateView(){
        //Updating the date/time with every tick()
        day.setText("Day: " + timeController.getDay());
        week.setText("Week: " + timeController.getWeek());
        month.setText("Month: " + timeController.getMonth());
        time.setText("Hour: " + timeController.getTime());
        repaint();
    }

    public void setVisibility(boolean visibility)
    {
        setVisible(visibility);
    }
}

