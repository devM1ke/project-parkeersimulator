package view;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuBarView {
	
	public MenuBarView() 
	{
		
	}

    public JMenuBar CreateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(options());

        return menuBar;
    }

    private JMenu options()
    {
        JMenu menu = new JMenu( "Options");
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