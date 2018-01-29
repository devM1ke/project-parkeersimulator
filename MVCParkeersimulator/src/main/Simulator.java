package main;

import model.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;
import view.SimulatorView;
import view.BarView;
import view.CarParkView;
import view.JFrame;
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
	private ManagementView managementview;
	
	public Simulator() {
		model = new Model();
		screen=new JFrame();
		screen.setSize(1200, 700);
		//never set this to true!!! or else it will break
		screen.setResizable(false);
		screen.setLayout(null);
		menubarview = new MenuBarView(model);
		screen.setJMenuBar(menubarview.CreateMenuBar());
		pieview = new PieView(model);
		controller = new Controller(model);
		carparkview = new CarParkView(model);
		managementview = new ManagementView(model);
		
		BarView chart = new BarView(model);

		
		//carparkview.setBackground(Color.black);
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				model.setTickPause(source.getValue());
			}
		});
		slider.setValue(100);
		screen.getContentPane().add(slider);
		screen.getContentPane().add(managementview);
		screen.getContentPane().add(pieview);
		screen.getContentPane().add(controller);
		screen.getContentPane().add(carparkview);
		screen.getContentPane().add(chart);
		
		slider.setBounds(700, 650, 100, 20);
		managementview.setBounds(1000, 20, 150, 200);
		carparkview.setBounds(150, 20, 800, 400);
		pieview.setBounds(20, 470, 200, 200);
		controller.setBounds(20, 20, 100, 200);
		chart.setBounds(250, 470, 150, 150);
		
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);   
		

	}
}
