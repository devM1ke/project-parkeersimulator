package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;

public class Controller extends AbstractController implements ActionListener {
	protected Model model;
	
	private JButton buttonTick1;
	private JButton buttonTick100;
	private JButton start;
	private JButton stop;
	
	public Controller(Model model) {
		super(model);
		this.model=model;

		//setSize(50, 50);
		//initialize button with the actionlistener so it can peform somthing when the button is clicked
		buttonTick1=new JButton("+1");
		buttonTick1.addActionListener(this);
		buttonTick100 = new JButton("+100");
		buttonTick100.addActionListener(this);
		start = new JButton("Start");
		start.addActionListener(this);
		stop = new JButton("Stop");
		stop.addActionListener(this);
		this.setLayout(null);
		
		add(buttonTick1);
		add(buttonTick100);
		add(start);
		add(stop);
		
		buttonTick1.setBounds(0,0,100,30);
		buttonTick100.setBounds(100,0,100,30);
		start.setBounds(200,0,100,30);
		stop.setBounds(300,0,100,30);

		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == buttonTick1) {
			model.runFor(1);
		}
		
		if(e.getSource() == buttonTick100) {
			model.runFor(100);
		} 
	
		if(e.getSource() == start) {
			model.start();
		} 
	
		if(e.getSource() == stop) {
			model.stop();
		} 
	}
}