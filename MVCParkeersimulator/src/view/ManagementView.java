package view;
import java.awt.Graphics;

import javax.swing.JLabel;

import model.Model;

public class ManagementView extends AbstractView{
	private JLabel dagOmzetLabel, verwachtOmzetLabel;
	private JLabel[] dagLabel;
	public ManagementView(Model model){
		super(model);
		setSize(200,200);
        //Creating labels to show the profits
        

		dagLabel = new JLabel[model.howmanydays];	
        dagOmzetLabel = new JLabel("dagomzet: ");
        verwachtOmzetLabel = new JLabel("Verwachte omzet");

        add(dagOmzetLabel);
        for (int i = 0; i < model.howmanydays; i++){
        add(dagLabel[i]);
        }
        add(verwachtOmzetLabel);


        setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		  int dagOmzet = model.dailyearnings;
		  int[] dagOmzetdag = new int[model.howmanydays];
		  for (int i = 0; i < model.howmanydays; i++){
				dagOmzetdag[i] =  model.dailyearningdays[i];
		  }

	        String dagOmzetS = dagOmzet + "";
	        String MaandagOmzet = "";
	        for (int i = 0; i < model.howmanydays; i++){
	        MaandagOmzet = dagOmzetdag[i] + "";
	        	        }
	        String VerwachteOmzet = model.stillToBeEarned() + "";
	        

	        dagOmzetLabel.setText("Dagomzet: "+dagOmzetS);
	        for (int i = 0; i < model.howmanydays; i++){
	        dagLabel[i].setText("Omzet dag "+ i + ": "+MaandagOmzet);
	        }

	        verwachtOmzetLabel.setText("Nog niet betaald: "+VerwachteOmzet);


	}
}
