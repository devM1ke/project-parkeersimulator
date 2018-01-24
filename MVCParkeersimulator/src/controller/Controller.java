package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;

public class Controller extends AbstractController implements ActionListener {
	protected Model model;
	
	private JButton buttonTick;
	
	public Controller(Model model) {
		super(model);
		this.model=model;

		setSize(50, 50);
		buttonTick=new JButton("Tick");

		this.setLayout(null);
		
		add(buttonTick);
		
		buttonTick.setBounds(410,650,100,30);

		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonTick ) {
			
		}
	}
}