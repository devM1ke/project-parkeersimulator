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
import view.QueueView;
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
<<<<<<< HEAD
	private QueueView queueview;
	//private TimeView timeview;
=======
	private TimeView timeview;
>>>>>>> 90d0910c5c875e5b5594321163a05419847f6040
	
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
<<<<<<< HEAD
		queueview = new QueueView(model);
		//timeview = new TimeView(model); 
=======
		timeview = new TimeView(model); 
>>>>>>> 90d0910c5c875e5b5594321163a05419847f6040
		//legendview.setBackground(Color.BLACK);
		
		BarView chart = new BarView(model);

		
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				int i = source.getValue();
				i = i * -1 + 100;
				model.setTickPause(i);
			}
		});
		slider.setValue(1);
		screen.getContentPane().add(slider);
		screen.getContentPane().add(managementview);
		screen.getContentPane().add(pieview);
		screen.getContentPane().add(controller);
		screen.getContentPane().add(carparkview);
		screen.getContentPane().add(chart);
		screen.getContentPane().add(legendview);
<<<<<<< HEAD
		screen.getContentPane().add(queueview);
		//screen.getContentPane().add(timeview);
=======
		screen.getContentPane().add(timeview);
>>>>>>> 90d0910c5c875e5b5594321163a05419847f6040
		
		queueview.setBounds(1000, 300, 175, 210);
		slider.setBounds(1050, 600, 100, 20);
		managementview.setBounds(1000, 20, 175, 210);
		carparkview.setBounds(150, 20, 800, 400);
		pieview.setBounds(20, 470, 200, 200);
		controller.setBounds(20, 20, 100, 150);
		chart.setBounds(250, 470, 150, 150);
		legendview.setBounds(0, 200, 150, 90);
		timeview.setBounds(550, 0, 150,50);


		
		
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);   
		

	}
}
