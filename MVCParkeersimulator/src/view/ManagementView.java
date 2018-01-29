package view;
import java.awt.Graphics;

import javax.swing.JLabel;

import model.Model;

public class ManagementView extends AbstractView{
	private JLabel dagOmzetLabel, verwachtOmzetLabel;
	private JLabel dagLabel[]= new JLabel[model.howmanydays];
	
	public ManagementView(Model model){
		super(model);
		setSize(200,200);
        dagOmzetLabel = new JLabel("dagomzet: ");
        verwachtOmzetLabel = new JLabel("Verwachte omzet");

        add(dagOmzetLabel);
        setBounds(0, 10, 200, 10);
        add(verwachtOmzetLabel);

        for (int i = 0; i < model.howmanydays; i++){
        	this.dagLabel[i] = new JLabel();
        	add(dagLabel[i]);
        	dagLabel[i].setBounds(0, 100, 200, 10);
        }
        
        //add(verwachtOmzetLabel);

        setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		  int dagOmzet = model.dailyearnings;
		  int[] dagOmzetdag = new int[model.howmanydays];
		  for (int i = 0; i < model.howmanydays; i++){
				dagOmzetdag[i] =  model.dailyearningdays[i];
		  }
	        String VerwachteOmzet = model.stillToBeEarned() + "";
	 
	        dagOmzetLabel.setText("Dagomzet: "+dagOmzet );
	        for (int i = 0; i < model.howmanydays; i++){
	        dagLabel[i].setText("Omzet dag "+ (i+1) + ": "+dagOmzetdag[i]);
	        }
	        verwachtOmzetLabel.setText("Nog niet betaald: "+VerwachteOmzet);


	}
}
