package main;

import model.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;
import view.SimulatorView;
import view.CarParkView;
import view.JFrame;
import view.MenuBarView;
import view.PieView;
import view.SettingsView;
import view.ManagementView; 

public class Simulator extends JFrame{
	private Model model;
	private JFrame screen;
	private CarParkView carparkview;
	private PieView pieview;
	private Controller controller;
	private MenuBarView menubarview;
	private ManagementView managementview;
	//private SettingsView settingsview;
	GridLayout experimentLayout = new GridLayout(0,2);
	private JPanel contentPane;
	
	public Simulator() {
		model = new Model();
		//screen = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 600);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton btnNewButton_2 = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_2, 11, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_2, 0, SpringLayout.EAST, contentPane);
		contentPane.add(btnNewButton_2);
		
		JPanel carparkview = new CarParkView(model);
		sl_contentPane.putConstraint(SpringLayout.NORTH, carparkview, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, carparkview, -176, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, carparkview, -6, SpringLayout.WEST, btnNewButton_2);
		contentPane.add(carparkview);
		
		JPanel pieview = new PieView(model);
		sl_contentPane.putConstraint(SpringLayout.NORTH, pieview, 0, SpringLayout.SOUTH, carparkview);
		sl_contentPane.putConstraint(SpringLayout.WEST, pieview, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pieview, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(pieview);
		
		JLabel lblGereserveerdePlaatsen = new JLabel("Reserveringen");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblGereserveerdePlaatsen, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblGereserveerdePlaatsen);
		
		JLabel lblBezettePlaatsen = new JLabel("Normale auto's");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBezettePlaatsen, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblBezettePlaatsen, -6, SpringLayout.NORTH, lblGereserveerdePlaatsen);
		contentPane.add(lblBezettePlaatsen);
		
		JLabel lblAbboneePlaatsen = new JLabel("Abbonees");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAbboneePlaatsen, 113, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAbboneePlaatsen, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblGereserveerdePlaatsen, -6, SpringLayout.NORTH, lblAbboneePlaatsen);
		contentPane.add(lblAbboneePlaatsen);
		
		JLabel lblLegePlekken = new JLabel("Lege plekken");
		sl_contentPane.putConstraint(SpringLayout.WEST, carparkview, 19, SpringLayout.EAST, lblAbboneePlaatsen);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblLegePlekken, 6, SpringLayout.SOUTH, lblAbboneePlaatsen);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLegePlekken, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblLegePlekken);
		
		JSlider slider = new JSlider();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, slider, -33, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, slider, -10, SpringLayout.EAST, contentPane);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				model.setTickPause(source.getValue());
			}
		});
		slider.setValue(100);
		contentPane.add(slider);
		
		JPanel buttonPanel = new Controller(model);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonPanel, 645, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pieview, -467, SpringLayout.WEST, buttonPanel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonPanel, 39, SpringLayout.SOUTH, carparkview);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonPanel, 337, SpringLayout.NORTH, lblBezettePlaatsen);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonPanel, -825, SpringLayout.EAST, contentPane);
		contentPane.add(buttonPanel);
		model.start();
		
		JPanel managementview = new ManagementView(model);
		sl_contentPane.putConstraint(SpringLayout.NORTH, managementview, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, managementview, -400, SpringLayout.EAST, contentPane);


		contentPane.add(managementview);
		//screen.setSize(1000, 800);
//		screen.setVisible(true);
//		screen.setTitle("Parkeersimulator");
//		screen.setLayout(new FlowLayout());
//		carparkview = new CarParkView(model);
//		//carparkview.setLayout(new BoxLayout(carparkview, BoxLayout.PAGE_AXIS));
//		pieview = new PieView(model);
//		//pieview.setLayout(new BoxLayout(pieview, BoxLayout.X_AXIS));
		
//		//controller.setLayout(new BoxLayout(controller, BoxLayout.X_AXIS));
		menubarview = new MenuBarView(model);
		setJMenuBar(menubarview.CreateMenuBar());
//		screen.add(pieview);
//		screen.add(carparkview);
		
//		
//		screen.revalidate();
//		screen.repaint();
//		
//		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		screen.pack();
//		screen.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//screen.setVisible(true);
	}
}
