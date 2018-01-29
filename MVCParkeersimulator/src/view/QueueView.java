package view;

import java.awt.Graphics;

import javax.swing.JLabel;

import model.Model;

public class QueueView extends AbstractView {
private JLabel wachtrijLabel, gewoneIngangLabel, abonneesWachtrij, reserveringWachtrij, betalingsrijLabel, uitgangsLabel, vertrokkenLabel;
	public QueueView(Model model){
		super(model);
		this.model = model;
		wachtrijLabel = new JLabel("Wachtrijen: ");
		gewoneIngangLabel = new JLabel("Normale auto's in de rij: ");
		abonneesWachtrij = new JLabel("Abonnees in de rij: ");
		reserveringWachtrij = new JLabel("Reserveringen in de rij: ");
        betalingsrijLabel = new JLabel("Huidige betalingsrij: ");
        uitgangsLabel = new JLabel("Uitgang wachtrij: ");
        vertrokkenLabel = new JLabel("Mensen vertrokken uit de rij: ");
		add(wachtrijLabel);
		add(gewoneIngangLabel);
		add(abonneesWachtrij);
		add(reserveringWachtrij);
		add(betalingsrijLabel);
		add(uitgangsLabel);
		add(vertrokkenLabel);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		wachtrijLabel.setText("Wachtrijen: ");
		gewoneIngangLabel.setText("Normale auto's in de rij: " + model.getSizeEntranceCarQueue());
		abonneesWachtrij.setText("Abonnees in de rij: " + model.getSizeEntrancePassQueue());
		reserveringWachtrij.setText("Reserveringen in de rij: Poep!");
		betalingsrijLabel.setText("Huidige betalingsrij: " + model.getSizePaymentCarQueue());
		uitgangsLabel.setText("Uitgang wachtrij: " + model.getSizeExitCarQueue());
		vertrokkenLabel.setText("Vertrokken uit de rij: Poepie!");
	}
}
