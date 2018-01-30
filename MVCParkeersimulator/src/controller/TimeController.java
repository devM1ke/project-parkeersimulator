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

<<<<<<< HEAD
    private int hour = model.hour;
    private int minute = model.minute;
    private int day = model.day;
    private String time;
=======

    
    

>>>>>>> 13007f84d22bb5e5b82ce2a4959ad35b8b4c1fe2
    public TimeController(Model model) {
    	super(model);
    }

   
    
    
    /**
     * Setting the time in a HH:MM format
     * @return return the value of hour+minute
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
<<<<<<< HEAD
}

=======
    

}
>>>>>>> 13007f84d22bb5e5b82ce2a4959ad35b8b4c1fe2