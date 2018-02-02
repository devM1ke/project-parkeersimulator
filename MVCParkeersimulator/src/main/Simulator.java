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
/**
 * It creates the whole GUI (graphic user interface). 
 * The component are included initialized and put on the right place on the screen.
 * Also the model is initialized and is added as a parameter to the views and controllers
 * The simulator constructor isn't containing any parameters.
 * @author Joeri
 */
public class Simulator{
	private JFrame screen;
	
	private Model model;
	
	private Controller controller;
	
	//all the views at one place
	private CarParkView carparkview;
	private PieView pieview;
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
		//Set resizable to false. If you can resize it, it wil break
		screen.setResizable(false);
		screen.setLayout(null);
		
		//initilize all the views and controllers
		menubarview = new MenuBarView(model);
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
		//add all the JPanel components to the JFrame screen
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

		//give all the components a position to be displayed
		queueview.setBounds(1100, 300, 175, 210);
		pietitle.setBounds(1100, 580, 200, 20);
		slider.setBounds(1050, 600, 200, 20);
		managementview.setBounds(1100, 20, 175, 210);
		carparkview.setBounds(250, 20, 800, 380);
		pieview.setBounds(20, 450, 160, 220);
		controller.setBounds(45, 90, 100, 200);
		barview.setBounds(250, 470, 200, 150);
		legendview.setBounds(20, 280, 220, 135);
		timeview.setBounds(600, 0, 200,50);
		imageview.setBounds(10,10,250,80);
		queuelistview.setBounds(400, 415, 500, 70);
		lineDiagramView.setBounds(500,470,400,200);
		
		//A few more settings for the JFrame screen
		//The program exits on close and the screen is set to visible
		//Also the menu is added to the JFrame screen
		screen.setJMenuBar(menubarview.CreateMenuBar());
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.setVisible(true);   
	}
}
