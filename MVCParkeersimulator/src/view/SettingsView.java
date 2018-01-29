package view;

import javax.swing.*;
import javax.swing.JFrame;

import model.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsView extends AbstractView{

    private JFrame settingsFrame;
    private JTextField carsWeektext;
    private JTextField carsWeekendtext;
    private JTextField passCarsWeektext;
    private JTextField passCarsWeekendtext;

    public SettingsView(Model model)
    {
    	super(model);
    	this.CreateView();

    }
    
    private void CreateView()
    {
        settingsFrame = new JFrame();
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel content = CreatePanel();

        settingsFrame.getContentPane().add("Center", content);
        settingsFrame.pack();
        settingsFrame.setResizable(false);
        settingsFrame.setVisible(true);
    }
    
    public JPanel CreatePanel()
    {
        JPanel content = new JPanel(new GridLayout(5,1));
        
        JLabel carsWeeklabel = new JLabel("Auto's per uur week");
        carsWeektext = new JTextField(String.valueOf(model.getWeekDayArrivals()+"") ,5);

        JLabel carsWeekendlabel = new JLabel("Auto's per uur weekend");
        carsWeekendtext = new JTextField(String.valueOf(model.getWeekendArrivals()+"") ,7);
        
        JLabel passCarsWeeklabel = new JLabel("Abbonees per uur doordeweeks");
        passCarsWeektext = new JTextField(String.valueOf(model.getWeekDayPassArrivals()+"") ,9);
        
        JLabel passCarsWeekendlabel = new JLabel("Abbonees per uur weekend");
        passCarsWeekendtext = new JTextField(String.valueOf(model.getWeekendPassArrivals()+"") ,11);
        
        content.add(carsWeeklabel);
        content.add(carsWeektext);
        
        content.add(carsWeekendlabel);
        content.add(carsWeekendtext);
        
        content.add(passCarsWeeklabel);
        content.add(passCarsWeektext);
        
        content.add(passCarsWeekendlabel);
        content.add(passCarsWeekendtext);


        content.add(updateButton());
        content.add(cancelButton());

        return content;
    }

    public JButton cancelButton()
    {
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.dispose();
            }
        });

        return cancel;
    }

    public JButton updateButton()
    {

        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {

                    String speed = carsWeektext.getText();
                    //Model.setTickPause(Integer.parseInt(speed));

                    //AbstractView.updateView();
                    settingsFrame.dispose();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(settingsFrame,
                            "Geen geldig nummer.",
                            "OPGELET",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        return update;
    }

    public void updateView()
    {
        repaint();
    }

    public void setVisibility(boolean visibility)
    {
        setVisible(visibility);
    }
}
