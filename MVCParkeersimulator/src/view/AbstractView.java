package view;

import javax.swing.JPanel;

import model.Model;


public abstract class AbstractView extends JPanel {
	private static final long serialVersionUID = -2767764579227738552L;
	protected Model model;

	public AbstractView(Model model) {
		this.model=model;
		model.addView(this);
	}
	
	public Model getModel() {
		return model;
	}
	
	public void updateView() {
		repaint();
	}
	
}
