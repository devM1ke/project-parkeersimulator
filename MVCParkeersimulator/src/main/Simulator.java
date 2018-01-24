package main;

import model.Model;

import java.awt.*;
import javax.swing.*;

import controller.Controller;
import view.SimulatorView;
import view.CarParkView;
import view.MenuBarView;
import view.PieView;
import view.SettingsView;
import view.ManagementView; 

public class Simulator{
	private Model model;
	private JFrame screen;
	private CarParkView carparkview;
	private PieView pieview;
	private Controller controller;
	//private MenuBarView menubarview;
	//private ManagementView managementview;
	//private SettingsView settingsview;
	
	public Simulator() {
		model = new Model();
		screen = new JFrame();
		
		carparkview = new CarParkView(model);
		pieview = new PieView(model);
		controller = new Controller(model);
		//menubarview = new MenuBarView(model);
		//managementview = new ManagementView(model);
		//settingsview = new SettingsView(model);
		
		screen.getContentPane().add(carparkview);
		screen.getContentPane().add(pieview);
		screen.getContentPane().add(controller);
		//screen.getContentPane().add(menubarview);
		//screen.getContentPane().add(settingsview);
		//screen.getContentPane().add(managementview);

		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.pack();
		screen.setSize(1000, 800);
		screen.setVisible(true);
	}
}
