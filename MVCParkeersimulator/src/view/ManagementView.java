package view;
import java.awt.Graphics;


import javax.swing.JLabel;

import model.Model;
/**
 * Displays the management data.
 *
 * @author Jelle
 * @version 1
 * @since 27-01-2018
 */
public class ManagementView extends AbstractView{
	//making the labels to show the data.
	private JLabel dagOmzetLabel, verwachtOmzetLabel, weekOmzetLabel;
	private JLabel dagLabel[]= new JLabel[model.howmanydays];
	private String dag;
	
	public ManagementView(Model model){
		super(model);
		setSize(200,200);
        dagOmzetLabel = new JLabel("dagomzet: ");
        verwachtOmzetLabel = new JLabel("Verwachte omzet");
        weekOmzetLabel = new JLabel("Huidige weekomzet:");

        //adding the labels
        add(dagOmzetLabel);
        setBounds(0, 10, 200, 10);
        add(verwachtOmzetLabel);
        //adding the label in a loop because the number of labels are variabel.
        for (int i = 0; i < model.howmanydays; i++){
        	this.dagLabel[i] = new JLabel();
        	add(dagLabel[i]);
        	dagLabel[i].setBounds(0, 100, 200, 10);
        }
        add(weekOmzetLabel);
        setVisible(true);
	}
	// painting the data every tick.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		  int dagOmzet = model.dailyearnings;
		  int huidigeWeekOmzet = 0;
		  int[] dagOmzetdag = new int[model.howmanydays];
		  for (int i = 0; i < model.howmanydays; i++){
				dagOmzetdag[i] =  model.dailyearningdays[i];
				huidigeWeekOmzet = huidigeWeekOmzet + dagOmzetdag[i];
		  }
		  
	        String VerwachteOmzet = model.stillToBeEarned() + "";
	       // adding the text to the labels.
	        dagOmzetLabel.setText("Dagomzet: "+dagOmzet );
	        // displaying the actual day in words rather then numbers.
	        for (int i = 0; i < model.howmanydays; i++){
	        	
	        	if(i > 6){
	        		int j = i % 7;
	        		if(j==0){
	        			this.dag = "Maandag";
	        		}else if(j==1){
	        			this.dag = "Dinsdag";
	        		}else if(j==2){
	        			this.dag = "Woensdag";
	        		}else if(j==3){
	        			this.dag = "Donderdag";
	        		}else if(j==4){
	        			this.dag = "Vrijdag";
	        		}else if(j==5){
	        			this.dag = "Zaterdag";
	        		}else if(j==6){
	        			this.dag = "Zondag";
	        		}
	        	} else {
	        		if(i==0){
	        			this.dag = "Maandag";
	        		}else if(i==1){
	        			this.dag = "Dinsdag";
	        		}else if(i==2){
	        			this.dag = "Woensdag";
	        		}else if(i==3){
	        			this.dag = "Donderdag";
	        		}else if(i==4){
	        			this.dag = "Vrijdag";
	        		}else if(i==5){
	        			this.dag = "Zaterdag";
	        		}else if(i==6){
	        			this.dag = "Zondag";
	        		}
	        		
	        	}
	        dagLabel[i].setText("Omzet dag "+ dag + ": "+dagOmzetdag[i]);
	        }
	        verwachtOmzetLabel.setText("Nog niet betaald: "+VerwachteOmzet);
	        weekOmzetLabel.setText("Huidige weekomzet: " + huidigeWeekOmzet);


	}
}
