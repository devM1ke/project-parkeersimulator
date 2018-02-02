package controller;

import controller.AbstractController;
import model.Model;

/**
 * The TimeController controls the time of the clock
 * @author Mike
 * @version 1
 * @since 29-01-2018
 */
public class TimeController extends AbstractController{


    public TimeController(Model model) {
    	super(model);
    } 
    
    /**
     * Converting the time in a HH:MM format
     * @return return the time in a HH:MM format
     */
    public String getTime() {
        String time;
        int hour = model.hour;
        int minute = model.minute;
        if (hour < 10 && minute < 10) {
            time = "0" + hour + ":0" + minute;
            return time;
        } else if (hour < 10 && minute > 10) {
            time = "0" + hour + ":" + minute;
            return time;
        } else if (hour > 10 && minute < 10 ) {
            time = hour + ":0" + minute;
            return time;
        } else {
            time = hour + ":" + minute;
            return time;
        }        
    }
}


