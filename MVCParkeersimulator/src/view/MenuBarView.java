package view;

import javax.swing.*;

import view.SettingsView;
import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuBarView extends AbstractView {
	
	public MenuBarView(Model model) 
	{
		super(model);
		
	}

    /**
     * aanmaken van de menubar
     * @return menubar
     */
	public JMenuBar CreateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(options());

        return menuBar;
    }

	/**
	 * opties toevoegen aan menubar.
	 * @return menu
	 */
    private JMenu options()
    {
        JMenu menu = new JMenu("Opties");
        JMenuItem settings = settings();


        menu.add(settings);
 
        return menu;
    }

    /**
     * subtabje aan opties toevoegen
     * @return settings
     */
    private JMenuItem settings()
    {
        JMenuItem settings = new JMenuItem("Instellingen");
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsView(model);
            }
        });
        return settings;
  }
}
  

