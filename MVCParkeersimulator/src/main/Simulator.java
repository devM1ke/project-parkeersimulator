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
		screen.setSize(1000, 800);
		screen.setVisible(true);
		screen.setTitle("Parkeersimulator");
		screen.setLayout(new FlowLayout());
		//screen.setLayout(new GridBagLayout());
		carparkview = new CarParkView(model);
		//carparkview.setBounds(200, 100, 800, 500);
		carparkview.setLayout(new BoxLayout(carparkview, BoxLayout.PAGE_AXIS));
		pieview = new PieView(model);
		//pieview.setBackground(Color.RED);
		//pieview.setVisible(true);
		pieview.setLayout(new BoxLayout(pieview, BoxLayout.X_AXIS));
		//pieview.setBounds(900, 100, 200, 200);
		controller = new Controller(model);
		controller.setLayout(new BoxLayout(controller, BoxLayout.X_AXIS));
		menubarview = new MenuBarView(model);
		screen.setJMenuBar(menubarview.CreateMenuBar());
		//controller.setBounds(200, 600, 800, 100);
		//managementview = new ManagementView(model);
		//settingsview = new SettingsView(model);
		//screen.setLayout(new GridLayout(2, 10));
		screen.add(pieview);
		screen.add(carparkview);
		screen.add(controller);
		
		screen.revalidate();
		screen.repaint();
		//screen.setJMenuBar(menubarview);
		//screen.getContentPane().add(menubarview);
		//screen.getContentPane().add(settingsview);
		//screen.getContentPane().add(managementview);
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.pack();
		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//screen.setVisible(true);
	}
}
