package main;

import model.Model;
import javax.swing.*;
import view.SimulatorView;
import view.CarParkView;

public class Simulator{
	private Model model;
	private JFrame screen;
	private SimulatorView simulatorview;
	private CarParkView carParkView;
	
	public Simulator() {
		model = new Model();
		screen = new JFrame();
		simulatorview = new SimulatorView();
		carParkView = new CarParkView();
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.setVisible(true);
	}
}
