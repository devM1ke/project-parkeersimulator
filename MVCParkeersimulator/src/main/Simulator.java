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
import view.TimeView;
import view.BarView;
import view.CarParkView;
import view.JFrame;
import view.LegendView;
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
	private LegendView legendview;
	//private TimeView timeview;
	
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
		legendview = new LegendView(model);
		//timeview = new TimeView(model); 
		//legendview.setBackground(Color.BLACK);
		
		BarView chart = new BarView(model);

		
		JSlider slider = new JSlider();
		slider.setInverted(true);
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
		screen.getContentPane().add(legendview);
		//screen.getContentPane().add(timeview);
		
		
		slider.setBounds(1050, 600, 100, 20);
		managementview.setBounds(1000, 20, 150, 200);
		carparkview.setBounds(150, 20, 800, 400);
		pieview.setBounds(20, 470, 200, 200);
		controller.setBounds(20, 20, 100, 150);
		chart.setBounds(250, 470, 150, 150);
		legendview.setBounds(0, 200, 150, 50);
		//timeview.setBounds(600, 10, 150,50);
		
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);   
		

	}
}
