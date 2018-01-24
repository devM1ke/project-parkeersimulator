package controller;

import javax.swing.JPanel;

import model.Model;

public abstract class AbstractController extends JPanel {
	protected Model model;

	public AbstractController(Model model) {
		this.model=model;
	}
}