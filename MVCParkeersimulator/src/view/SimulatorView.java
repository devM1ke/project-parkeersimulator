package view;

import javax.swing.*;

import java.awt.*;


import model.Model;

public class SimulatorView extends JPanel{


 
    private Model model;
    private CarParkView carParkView;

    /**
     * Constructor for objects of class CarPark
     */
    public SimulatorView(Model model) {
    	this.model = model;
        
    }
    
    public void updateView() {
    	carParkView.updateView();
    }
    

    }
    
    
