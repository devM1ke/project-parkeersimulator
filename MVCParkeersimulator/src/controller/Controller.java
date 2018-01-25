package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Model;

public class Controller extends AbstractController implements ActionListener {
	protected Model model;
	
	private JButton buttonTick1;
	private JButton buttonTick100;
	
	public Controller(Model model) {
		super(model);
		this.model=model;

		setSize(50, 50);
		//initialize button with the actionlistener so it can peform somthing when the button is clicked
		buttonTick1=new JButton("+1");
		buttonTick100 = new JButton("+100");
		buttonTick1.addActionListener(this);
		buttonTick100.addActionListener(this);
		this.setLayout(null);
		
		add(buttonTick1);
		add(buttonTick100);
		
		buttonTick1.setBounds(410,650,100,30);
		buttonTick100.setBounds(600,650,100,30);

		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonTick1 &&  e.getSource() == buttonTick100) {
			//this works now tho
			System.out.println("passed");
		}
	}
}