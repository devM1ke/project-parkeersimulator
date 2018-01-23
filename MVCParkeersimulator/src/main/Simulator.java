package main;

import model.Model;

import java.awt.BorderLayout;

import javax.swing.*;
import view.SimulatorView;
import view.CarParkView;

public class Simulator{
	private Model model;
	private JFrame screen;
	private SimulatorView simulatorview;
	private CarParkView carparkview;
	
	public Simulator() {
		model = new Model();
		screen = new JFrame();
		simulatorview = new SimulatorView(model);
		carparkview = new CarParkView(model);
		screen.setSize(450, 285);
		screen.setResizable(true);
		screen.setLayout(null);
		screen.getContentPane().add(simulatorview);
		screen.getContentPane().add(carparkview, BorderLayout.CENTER);
		screen.pack();
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
		updateView();
	}
	public void updateView(){
		carparkview.updateView();
	}
}
