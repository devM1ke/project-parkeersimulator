package view;

import javax.swing.*;
import javax.swing.JFrame;

import model.LocationManager;
import model.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Opties om gegevens te wijzigen
 * 
 * @author Mike
 * @version 1
 * @since 29-01-2018
 */
public class SettingsView extends AbstractView{

    private JFrame settingsFrame;
    private JTextField carsWeektext;
    private JTextField carsWeekendtext;
    private JTextField passCarsWeektext;
    private JTextField passCarsWeekendtext;
    private JTextField pricetext;
    private JTextField subscriptiontext;
    private JTextField startnumbertext;

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
        JPanel content = new JPanel(new GridLayout(8,1));
        
        JLabel carsWeeklabel = new JLabel("Auto's per uur week");
        carsWeektext = new JTextField(String.valueOf(model.getWeekDayArrivals()+"") ,5);

        JLabel carsWeekendlabel = new JLabel("Auto's per uur weekend");
        carsWeekendtext = new JTextField(String.valueOf(model.getWeekendArrivals()+"") ,7);
        
        JLabel passCarsWeeklabel = new JLabel("Abbonees per uur doordeweeks");
        passCarsWeektext = new JTextField(String.valueOf(model.getWeekDayPassArrivals()+"") ,9);
        
        JLabel passCarsWeekendlabel = new JLabel("Abbonees per uur weekend");
        passCarsWeekendtext = new JTextField(String.valueOf(model.getWeekendPassArrivals()+"") ,11);
        
        JLabel priceLabel = new JLabel("Prijs");
        pricetext = new JTextField(String.valueOf(model.getPrice()+""),13);
        
        JLabel numberStartLabel = new JLabel("Start plaats abbonement plaatsen");
        startnumbertext = new JTextField(String.valueOf(model.getLocationManager().getPlaceNumberStart() +""),15);
        
        JLabel subscriptionLabel = new JLabel("Abbonement plaatsen");
        subscriptiontext = new JTextField(String.valueOf(model.getLocationManager().getNumberOfPlaces() +""),17);
        
        content.add(carsWeeklabel);
        content.add(carsWeektext);
        
        content.add(carsWeekendlabel);
        content.add(carsWeekendtext);
        
        content.add(passCarsWeeklabel);
        content.add(passCarsWeektext);
        
        content.add(passCarsWeekendlabel);
        content.add(passCarsWeekendtext);
        
        content.add(priceLabel);
        content.add(pricetext);
        
        content.add(numberStartLabel);
        content.add(startnumbertext);
        
        content.add(subscriptionLabel);
        content.add(subscriptiontext);
        
        content.add(updateButton());
        content.add(cancelButton());

        return content;
    }
    

    public JButton cancelButton()
    {
        JButton cancel = new JButton("Annuleren");
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
                	String weekSpots = carsWeektext.getText();
                	model.setWeekDayArrivals(Integer.parseInt(weekSpots));
                 
                	String weekendSpots = carsWeekendtext.getText();
                	model.setWeekendArrivals(Integer.parseInt(weekendSpots));
                	
                	String weekPassSpots = passCarsWeektext.getText();
                	model.setWeekDayPassArrivals(Integer.parseInt(weekPassSpots));
                	
                	String weekendPassSpots = passCarsWeekendtext.getText();
                	model.setWeekendPassArrivals(Integer.parseInt(weekendPassSpots));
                	
                	String price = pricetext.getText();
                	model.setPrice(Integer.parseInt(price));

                	String numberStart = startnumbertext.getText();
                	String numberOfPlaces = subscriptiontext.getText();
                	model.getLocationManager().changeType(0, 1, 540);
                	model.getLocationManager().changeType(1, Integer.parseInt(numberStart), Integer.parseInt(numberOfPlaces));
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
