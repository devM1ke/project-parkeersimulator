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
import view.ImageView;
import view.LegendView;
import view.LineChartView;
import view.LineDiagramView;
//import view.LineChartView;
import view.MenuBarView;
import view.PieView;
import view.QueueListView;
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

	private QueueView queueview;

	private TimeView timeview;
	private QueueListView queuelistview;
	private BarView barview;
	private ImageView imageview;
	private LineDiagramView lineDiagramView;
	
	public Simulator() {
		model = new Model();
		screen=new JFrame();
		screen.setSize(1300, 700);
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
		queueview = new QueueView(model);
		barview = new BarView(model);
		timeview = new TimeView(model); 
		queuelistview = new QueueListView(model);
		imageview = new ImageView(model);
		lineDiagramView = new LineDiagramView(model);

		//legendview.setBackground(Color.BLACK);
		


		
		JSlider slider = new JSlider();
		JLabel pietitle = new JLabel("Snelheid bepalen");
		
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
		screen.getContentPane().add(barview);
		screen.getContentPane().add(legendview);

		screen.getContentPane().add(queueview);
		screen.getContentPane().add(timeview);
		screen.getContentPane().add(queuelistview);
		screen.getContentPane().add(imageview);
		screen.getContentPane().add(lineDiagramView);
		screen.getContentPane().add(pietitle);

		
		queueview.setBounds(1100, 300, 175, 210);
		pietitle.setBounds(1100, 580, 200, 20);
		slider.setBounds(1050, 600, 200, 20);
		managementview.setBounds(1100, 20, 175, 210);
		carparkview.setBounds(250, 20, 800, 400);
		pieview.setBounds(20, 450, 160, 220);
		controller.setBounds(45, 90, 100, 200);
		barview.setBounds(250, 470, 200, 150);
		legendview.setBounds(20, 280, 220, 135);
		timeview.setBounds(600, 0, 200,50);
		imageview.setBounds(10,10,250,80);
		queuelistview.setBounds(400, 445, 500, 50);
		lineDiagramView.setBounds(500,470,400,200);

		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);   
		

	}
}
