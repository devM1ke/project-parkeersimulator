package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import view.AbstractView;


public abstract class AbstractModel extends JFrame{
	private List<AbstractView> views;
	
	public AbstractModel() {
		views=new ArrayList<AbstractView>();
	}
	
	public void addView(AbstractView view) {
		views.add(view);
	}
	
	public void notifyViews() {
		for(AbstractView v: views) v.updateView();
	}
}
