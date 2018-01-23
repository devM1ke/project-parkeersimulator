package main;

import model.Model;

import java.awt.*;
import javax.swing.*;
import view.SimulatorView;
import view.CarParkView;
import view.PieView;

public class Simulator{
	private Model model;
	private JFrame screen;
	private SimulatorView simulatorview;
	private CarParkView carparkview;
	private PieView pieview;
	
	public Simulator() {
		model = new Model();
		screen = new JFrame();
		carparkview = new CarParkView(model);
		pieview = new PieView(model);
		
		screen.getContentPane().add(carparkview);
		screen.getContentPane().add(pieview);

		screen.setVisible(true);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.pack();
		screen.setSize(1000, 800);
	}
}
