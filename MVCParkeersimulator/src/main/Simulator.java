package main;

import model.Model;

import java.awt.*;
import javax.swing.*;
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
	private MenuBarView menubarview;
	private ManagementView managementview;
	//private SettingsView settingsview;
	
	public Simulator() {
		model = new Model();
		screen = new JFrame();
		carparkview = new CarParkView(model);
		pieview = new PieView(model);
		managementview = new ManagementView(model);
		
		screen.getContentPane().add(carparkview);
		screen.getContentPane().add(pieview);
		//screen.getContentPane().add(menubarview);
		//screen.getContentPane().add(settingsview);
		//screen.getContentPane().add(managementview);		

		screen.setVisible(true);
		screen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		screen.pack();
		screen.setSize(1000, 800);
	}
}
