package view;

import javax.swing.*;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuBarView extends AbstractView {
	
	public MenuBarView(Model model) 
	{
		super(model);
		
	}

    public JMenuBar CreateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(options());
        //setVisible(true);
        return menuBar;
    }

    private JMenu options()
    {
        JMenu menu = new JMenu("Options");
        JMenuItem settings = settings();
        //JMenuItem reset = reset();

        menu.add(settings);
        //menu.add(reset);
        return menu;
    }


    private JMenuItem settings()
    {
        JMenuItem settings = new JMenuItem("Settings");
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				new SettingsView(null);
            }
        });
        return settings;
    }

   /* private JMenuItem reset()
    {
   
      return reset();
    }*/


}