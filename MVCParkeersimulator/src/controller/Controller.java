package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;

public class Controller extends AbstractController implements ActionListener {
	protected Model model;
	
	private JButton buttonTick1;
	private JButton buttonTickHour;
	private JButton buttonTickDay;
	private JButton start;
	private JButton stop;
	/**
	 * @param  model  the model contains all the important information with calculations
	 * @author Joeri
	 */
	public Controller(Model model) {
		super(model);
		this.model=model;

		//initialize button with the actionlistener so it can peform somthing when the button is clicked
		buttonTick1=new JButton("+1 minuut");
		buttonTick1.addActionListener(this);
		buttonTickHour = new JButton("+1 uur");
		buttonTickHour.addActionListener(this);
		buttonTickDay = new JButton("+1 dag");
		buttonTickDay.addActionListener(this);
		start = new JButton("Start");
		start.addActionListener(this);
		stop = new JButton("Stop");
		stop.addActionListener(this);
		this.setLayout(null);
		
		//adds the buttons to the JPanel
		add(buttonTick1);
		add(buttonTickHour);
		add(buttonTickDay);
		add(start);
		add(stop);
		
		//Give the buttons a position
		buttonTick1.setBounds(0,0,100,30);
		buttonTickHour.setBounds(0,35,100,30);
		buttonTickDay.setBounds(0,70,100,30);
		start.setBounds(0,105,100,30);
		stop.setBounds(0,140,100,30);
	}
	/**
	 * It's listens to the buttons shown on the screen and if a button is pushed
	 * the actionlistener is triggerd and it perfoms a given action 
	 *
	 * @param  e 	it contains the data from the button that has been pushed
	 * @author Joeri
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonTick1) {
			//It can peform a one step foward
			model.runFor(1);
		}
		
		if(e.getSource() == buttonTickHour) {
			//It fast fowards for an hour
			model.runFor(60);
		} 

		if(e.getSource() == buttonTickDay) {
			//It fast fowards for one day
			model.runFor(1440);
		}
		
		if(e.getSource() == start) {
			//It starts the program 
			model.start();
		} 
	
		if(e.getSource() == stop) {
			//It stops the program from running
			model.stop();
		} 
	}
}