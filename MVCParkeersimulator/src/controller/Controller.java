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
	
	public Controller(Model model) {
		super(model);
		this.model=model;

		//setSize(50, 50);
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
		
		add(buttonTick1);
		add(buttonTickHour);
		add(buttonTickDay);
		add(start);
		add(stop);
		
		buttonTick1.setBounds(0,0,100,30);
		buttonTickHour.setBounds(0,35,100,30);
		buttonTickDay.setBounds(0,70,100,30);
		start.setBounds(0,105,100,30);
		stop.setBounds(0,140,100,30);

		//setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonTick1) {
			model.runFor(1);
		}
		
		if(e.getSource() == buttonTickHour) {
			model.runFor(60);
		} 

		if(e.getSource() == buttonTickDay) {
			model.runFor(1440);
		}
		
		if(e.getSource() == start) {
			model.start();
		} 
	
		if(e.getSource() == stop) {
			model.stop();
		} 
	}
}