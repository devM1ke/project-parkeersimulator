package view;

import javax.swing.*;

import model.Model;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SettingsView extends AbstractView{

    private JFrame settingsFrame;
    private JTextField speedtext;

    public SettingsView(Model model)
    {
    	super(model);

    }

    private JPanel CreatePanel()
    {
        JPanel content = new JPanel(new GridLayout(3,2));

        JLabel speedlabel = new JLabel("Snelheid");
        //speedtext = new JTextField(String.valueOf(Model.getTickPause()+"") ,5);

        content.add(speedlabel);
        content.add(speedtext);


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

                    String speed = speedtext.getText();
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
