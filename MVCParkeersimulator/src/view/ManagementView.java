package view;
import java.awt.Graphics;

import javax.swing.JLabel;

import model.Model;

public class ManagementView extends AbstractView{
	private JLabel dagOmzetLabel, dinsdagOmzetLabel, woensdagOmzetLabel, donderdagOmzetLabel, vrijdagOmzetLabel, zaterdagOmzetLabel, zondagOmzetLabel, maandagOmzetLabel, verwachtOmzetLabel;
	public ManagementView(Model model){
		super(model);
		setSize(200,200);
        //Creating labels to show the profits
        


        dagOmzetLabel = new JLabel("dagomzet: ");
        maandagOmzetLabel = new JLabel("Maandagomzet");
        dinsdagOmzetLabel = new JLabel("Dinsdagomzet");
        woensdagOmzetLabel = new JLabel("Woensdagomzet");
        donderdagOmzetLabel = new JLabel("Dondagomzet");
        vrijdagOmzetLabel = new JLabel("Vrijdagomzet");
        zaterdagOmzetLabel = new JLabel("Zaterdagomzet");
        zondagOmzetLabel = new JLabel("Zondagomzet");
        verwachtOmzetLabel = new JLabel("Verwachte omzet");

        add(dagOmzetLabel);
        add(maandagOmzetLabel);
        add(dinsdagOmzetLabel);
        add(woensdagOmzetLabel);
        add(donderdagOmzetLabel);
        add(vrijdagOmzetLabel);
        add(zaterdagOmzetLabel);
        add(zondagOmzetLabel);
        add(verwachtOmzetLabel);


        setVisible(true);
	}


	
	
	public void paintComponent(Graphics g) {
		  int dagOmzet = model.dailyearnings;
	      int maandagOmzet = model.dailyearningsday1;
	      int dinsdagOmzet = model.dailyearningsday2;
	      int woensdagOmzet = model.dailyearningsday3;
	      int donderdagOmzet = model.dailyearningsday4;
	      int vrijdagOmzet = model.dailyearningsday5;
	      int zaterdagOmzet = model.dailyearningsday6;
	      int zondagOmzet = model.dailyearningsday7;
	      int verwachteOmzet = model.stillToBeEarned();


	        String dagOmzetS = dagOmzet + "";
	        String MaandagOmzet = maandagOmzet + "";
	        String DinsdagOmzet = dinsdagOmzet + "";
	        String WoensdagOmzet = woensdagOmzet + "";
	        String DonderdagOmzet = donderdagOmzet + "";
	        String VrijdagOmzet = vrijdagOmzet + "";
	        String ZaterdagOmzet = zaterdagOmzet + "";
	        String ZondagOmzet = zondagOmzet + "";
	        String VerwachteOmzet = verwachteOmzet + "";
	        

	        dagOmzetLabel.setText("Dagomzet: "+dagOmzetS);
	        maandagOmzetLabel.setText("Omzet maandag: "+MaandagOmzet);
	        dinsdagOmzetLabel.setText("Omzet dinsdag: "+DinsdagOmzet);
	        woensdagOmzetLabel.setText("Omzet woensdag: "+WoensdagOmzet);
	        donderdagOmzetLabel.setText("Omzet donderdag: "+DonderdagOmzet);
	        vrijdagOmzetLabel.setText("Omzet vrijdag: "+VrijdagOmzet);
	        zaterdagOmzetLabel.setText("Omzet zaterdag: "+ZaterdagOmzet);
	        zondagOmzetLabel.setText("Omzet zondag: "+ZondagOmzet);
	        verwachtOmzetLabel.setText("Nog niet betaald: "+VerwachteOmzet);


	}
}
