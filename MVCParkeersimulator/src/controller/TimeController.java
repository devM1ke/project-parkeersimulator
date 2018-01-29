package controller;

import controller.AbstractController;
import model.Model;

/**
 *
 * @author Mike
 * @version 1
 * @since 29-01-2018
 */
public class TimeController extends AbstractController{

    private String resetTime = "23:59";
    private int hour = model.hour;
    private int minute = model.minute;
    private int day = model.day;
    private String time;
    public TimeController(Model model) {
    	super(model);
    }

   
    
    
    /**
     * Setting the time in a HH:MM format
     * @return return the value of hour+minute
     */
    public String getTime() {
        if (hour < 10 && minute < 10) {
            time = "0" + hour + ":0" + minute;
        } else if (hour < 10 && minute > 10) {
            time = "0" + hour + ":" + minute;
        } else if (hour > 10 && minute < 10 ) {
            time = hour + ":0" + minute;
        } else {
            time = hour + ":" + minute;
        }
        return time;
    }
    

    public void resetTime()
    {
        minute = 0;
        hour = 0;
        day = 1;

    }
}