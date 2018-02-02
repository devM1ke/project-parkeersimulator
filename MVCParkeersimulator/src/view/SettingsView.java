package view;

import javax.swing.*;

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
    private JTextField reserveCarsWeektext;
    private JTextField reserveCarsWeekendtext;
    private JTextField pricetext;
    private JTextField subscriptiontext;
    private JTextField startnumbertext;
    private JTextField queueNormalSizeText;
    private JTextField queuePassSizeText;
    private JTextField maxQueuePassSizeText;
    
    public SettingsView(Model model)
    {
    	super(model);
    	this.CreateView();

    }
    
    /** 
     * aan maken van de JFrame
     */
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
    /**
     * aanmaken van de labels en de tekst velden
     * @return content
     */
    public JPanel CreatePanel()
    {
        JPanel content = new JPanel(new GridLayout(17,1));
        
        JLabel carsWeeklabel = new JLabel("Auto's per uur doordeweeks");
        carsWeektext = new JTextField(String.valueOf(model.getWeekDayArrivals()+"") ,5);

        JLabel carsWeekendlabel = new JLabel("Auto's per uur weekend");
        carsWeekendtext = new JTextField(String.valueOf(model.getWeekendArrivals()+"") ,7);
        
        JLabel passCarsWeeklabel = new JLabel("Abonnees per uur doordeweeks");
        passCarsWeektext = new JTextField(String.valueOf(model.getWeekDayPassArrivals()+"") ,9);
        
        JLabel passCarsWeekendlabel = new JLabel("Abonnees per uur weekend");
        passCarsWeekendtext = new JTextField(String.valueOf(model.getWeekendPassArrivals()+"") ,11);
        
        JLabel reserveCarsWeeklabel = new JLabel("Reserveringen per uur doordeweeks");
        reserveCarsWeektext = new JTextField(String.valueOf(model.getWeekdayReserveArrivals()+"") ,13);
        
        JLabel reserveCarsWeekendlabel = new JLabel("Reserveringen per uur weekend");
        reserveCarsWeekendtext = new JTextField(String.valueOf(model.getWeekendReserveArrivals()+"") ,15);

    	JLabel whiteLine = new JLabel(""); JLabel whiteLine2 = new JLabel("");
        
        JLabel priceLabel = new JLabel("Prijs per auto");
        pricetext = new JTextField(String.valueOf(model.getPrice()+""),17);

    	JLabel whiteLine3 = new JLabel(""); JLabel whiteLine4 = new JLabel("");
        
        JLabel numberStartLabel = new JLabel("Start plaats abonnements plaatsen");
        startnumbertext = new JTextField(String.valueOf(model.getLocationManager().getPlaceNumberStart() +""),19);
        
        JLabel subscriptionLabel = new JLabel("Aantal abonnements plaatsen");
        subscriptiontext = new JTextField(String.valueOf(model.getLocationManager().getPlaceNumberAmount() +""),21);

    	JLabel whiteLine5 = new JLabel(""); JLabel whiteLine6 = new JLabel("");
        
        JLabel queueNormalLabel = new JLabel("Max lengte normale rij");
        queueNormalSizeText = new JTextField(String.valueOf(model.getQueueNormalSize() +""),23);
        
        JLabel queuePassLabel = new JLabel("Max lengte abonnements rij");
        queuePassSizeText = new JTextField(String.valueOf(model.getQueuePassSize() +""),25);
        
        JLabel maxQueuePassLabel = new JLabel("Max aantal Abonnees");
        maxQueuePassSizeText = new JTextField(String.valueOf(model.getMaxNumberofPassCars() +""),27);
        
    	JLabel whiteLine11 = new JLabel(""); JLabel whiteLine12 = new JLabel("");
        
        // labels en tekst worden aan de JFrame toegevoegd.
    	content.add(carsWeeklabel);
        content.add(carsWeektext);
        
        content.add(carsWeekendlabel);
        content.add(carsWeekendtext);
        
        content.add(passCarsWeeklabel);
        content.add(passCarsWeektext);
        
        content.add(passCarsWeekendlabel);
        content.add(passCarsWeekendtext);
        
        content.add(reserveCarsWeeklabel);
        content.add(reserveCarsWeektext);
        
        content.add(reserveCarsWeekendlabel);
        content.add(reserveCarsWeekendtext);

        content.add(whiteLine);
        content.add(whiteLine2);
        
        content.add(priceLabel);
        content.add(pricetext);
        
        content.add(whiteLine3);
        content.add(whiteLine4);
        
        content.add(numberStartLabel);
        content.add(startnumbertext);
        
        content.add(subscriptionLabel);
        content.add(subscriptiontext);
        
        content.add(whiteLine5);
        content.add(whiteLine6);
        
        content.add(queueNormalLabel);
        content.add(queueNormalSizeText);
        
        content.add(queuePassLabel);
        content.add(queuePassSizeText);
        
        content.add(maxQueuePassLabel);
        content.add(maxQueuePassSizeText);

        content.add(whiteLine11);
        content.add(whiteLine12);
        
        content.add(updateButton());
        content.add(cancelButton());

        return content;
    }
    
    /**
     * aanmaken van de JButton cancel en een actionlistener daar aan toevoegen
     *die ervoor zorgt dat de JFrame wordt afgesloten als er op Cancel wordt geklikt.
     * @return cancel button
     */
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
/**
 * aanmaken van de JButton Update toevoegen van een actionListener dit in een try catch constructie.
 * zodat als je een ongeldige invoer geeft dit op het scherm wordt weergeven. 
 * @return update button
 */
    public JButton updateButton()
    {

        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                	/**
                	 * ophalen van de waarde in carsWeektext en deze vervolgens omzetten naar een int.
                	 * Deze int vervolgens meegeven aan de methode setWeekDayArrivals.
                	 */
                	String weekSpots = carsWeektext.getText();
                	model.setWeekDayArrivals(Integer.parseInt(weekSpots));
                 
                	String weekendSpots = carsWeekendtext.getText();
                	model.setWeekendArrivals(Integer.parseInt(weekendSpots));
                	
                	String weekPassSpots = passCarsWeektext.getText();
                	model.setWeekDayPassArrivals(Integer.parseInt(weekPassSpots));
                	
                	String weekendPassSpots = passCarsWeekendtext.getText();
                	model.setWeekendPassArrivals(Integer.parseInt(weekendPassSpots));
                	
                	String weekReserveSpots = reserveCarsWeektext.getText();
                	model.setWeekdayReserveArrivals(Integer.parseInt(weekReserveSpots));
                	
                	String weekendReserveSpots = reserveCarsWeekendtext.getText();
                	model.setWeekendReserveArrivals(Integer.parseInt(weekendReserveSpots));
                	
                	String price = pricetext.getText();
                	if(Integer.parseInt(price) < 1) {
                		throw new Exception();
                	}
                	model.setPrice(Integer.parseInt(price));

                	String numberStart = startnumbertext.getText();
                	String numberOfPlaces = subscriptiontext.getText();
                    
                	int am = model.getNumberOfFloors() * model.getNumberOfRows() * model.getNumberOfPlaces();
                	model.getLocationManager().changeTypeTo(1, 0, 1, am);
                	model.getLocationManager().setPlaceNumberStart(Integer.parseInt(numberStart));
                	model.getLocationManager().setPlaceNumberAmount(Integer.parseInt(numberOfPlaces));
                	model.getLocationManager().changeType(1, Integer.parseInt(numberStart), Integer.parseInt(numberOfPlaces));
                	model.notifyViews();
                	
                	String queueNormalSize = queueNormalSizeText.getText();
                	model.setQueueNormalSize(Integer.parseInt(queueNormalSize));

                	String queuePassSize = queuePassSizeText.getText();
                	model.setQueuePassSize(Integer.parseInt(queuePassSize));

                	String maxQueuePassSize = maxQueuePassSizeText.getText();
                	model.setMaxNumberofPassCars(Integer.parseInt(maxQueuePassSize));
                }
                catch(Exception ex)
                {
                	//foutmelding als er geen geldige waarde wordt ingevoerd.
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
