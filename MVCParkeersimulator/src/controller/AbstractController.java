package controller;

import javax.swing.JPanel;

import model.Model;

public abstract class AbstractController extends JPanel {
	private static final long serialVersionUID = 1788345174314929175L;
	protected Model model;

	public AbstractController(Model model) {
		this.model=model;
	}
}