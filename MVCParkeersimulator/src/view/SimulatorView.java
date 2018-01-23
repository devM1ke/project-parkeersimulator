package view;

import javax.swing.*;

import java.awt.*;


import model.Model;

public class SimulatorView extends JFrame{


 
    private Model model;
    private CarParkView carParkView;

    /**
     * Constructor for objects of class CarPark
     */
    public SimulatorView() {

        
        carParkView = new CarParkView();
	
	    Container contentPane = getContentPane();
	    contentPane.add(carParkView, BorderLayout.CENTER);
	    pack();
	    setVisible(true);
    }
    
    public void updateView() {
    	carParkView.updateView();
    }
    
    public void getRef(Model simRef) {
    	this.model = simRef;
    	carParkView.getRef(simRef);
    }
    
    }
