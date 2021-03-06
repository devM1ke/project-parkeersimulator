package view;
/**
 * Displaying data about the queues
 *
 * @author Jelle
 * @version 1
 * @since 28-01-2018
 */
import java.awt.Graphics;

import javax.swing.JLabel;

import model.Model;

public class QueueView extends AbstractView {
private JLabel wachtrijLabel, gewoneIngangLabel, abonneesWachtrij, reserveringWachtrij, betalingsrijLabel, uitgangsLabel, vertrokkenLabel;
	public QueueView(Model model){
		super(model);
		this.model = model;
		//making the labels to show the data.
		wachtrijLabel = new JLabel("Wachtrijen: ");
		gewoneIngangLabel = new JLabel("Normale auto's in de rij: ");
		abonneesWachtrij = new JLabel("Abonnees in de rij: ");
		reserveringWachtrij = new JLabel("Reserveringen in de rij: ");
        betalingsrijLabel = new JLabel("Huidige betalingsrij: ");
        uitgangsLabel = new JLabel("Uitgang wachtrij: ");
        vertrokkenLabel = new JLabel("Mensen vertrokken uit de rij: ");
		// adding the labels.
        add(wachtrijLabel);
		add(gewoneIngangLabel);
		add(abonneesWachtrij);
		add(reserveringWachtrij);
		add(betalingsrijLabel);
		add(uitgangsLabel);
		add(vertrokkenLabel);
	}
	//painting the labels every tick.
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// adding the text to the labels.
		wachtrijLabel.setText("Wachtrijen: ");
		gewoneIngangLabel.setText("Normale auto's in de rij: " + model.getSizeEntranceCarQueue());
		try {abonneesWachtrij.setText("Abonnees in de rij: " + model.getSizeEntrancePassQueue());} catch (Exception ex) {}
		try {reserveringWachtrij.setText("Reserveringen in de rij: " + model.getSizeReservedQueue());}catch (Exception ex) {}
		betalingsrijLabel.setText("Huidige betalingsrij: " + model.getSizePaymentCarQueue());
		uitgangsLabel.setText("Uitgang wachtrij: " + model.getSizeExitCarQueue());
		vertrokkenLabel.setText("Vertrokken uit de rij: " +model.getLeft());
	}
}
