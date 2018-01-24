package controller;

import model.Model;

public abstract class AbstractController{
	private Model model;

	public AbstractController(Model model) {
		this.model=model;
	}
}