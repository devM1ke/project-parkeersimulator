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
	private MenuBarView menubarview;
	//private ManagementView managementview;
	//private SettingsView settingsview;
	GridLayout experimentLayout = new GridLayout(0,2);
	
	public Simulator() {
		model = new Model();
		screen = new JFrame();
		//screen.setLayout(experimentLayout);
		carparkview = new CarParkView(model);
		pieview = new PieView(model);
		controller = new Controller(model);
		menubarview = new MenuBarView(model);
		screen.setJMenuBar(menubarview.CreateMenuBar());
		
		//managementview = new ManagementView(model);
		//settingsview = new SettingsView(model);
		//screen.setLayout(new GridLayout(2, 10));
		screen.add(carparkview);
		screen.add(pieview);
		screen.add(controller);
		//screen.setJMenuBar(menubarview);
		//screen.getContentPane().add(menubarview);
		//screen.getContentPane().add(settingsview);
		//screen.getContentPane().add(managementview);
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.pack();
		screen.setSize(1000, 800);
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		screen.setVisible(true);
	}
}
