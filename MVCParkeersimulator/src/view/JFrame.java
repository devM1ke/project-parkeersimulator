package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

public class JFrame extends javax.swing.JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton btnNewButton_2 = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_2, 11, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_2, 0, SpringLayout.EAST, contentPane);
		contentPane.add(btnNewButton_2);
		
		JPanel carparkview = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, carparkview, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, carparkview, -176, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, carparkview, -6, SpringLayout.WEST, btnNewButton_2);
		contentPane.add(carparkview);
		
		JPanel pieview = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pieview, 34, SpringLayout.SOUTH, carparkview);
		sl_contentPane.putConstraint(SpringLayout.WEST, pieview, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pieview, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(pieview);
		
		JLabel lblGereserveerdePlaatsen = new JLabel("Gereserveerde plaatsen");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblGereserveerdePlaatsen, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblGereserveerdePlaatsen);
		
		JLabel lblBezettePlaatsen = new JLabel("Normale auto's");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBezettePlaatsen, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblBezettePlaatsen, -6, SpringLayout.NORTH, lblGereserveerdePlaatsen);
		contentPane.add(lblBezettePlaatsen);
		
		JLabel lblAbboneePlaatsen = new JLabel("Abbonee plaatsen");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAbboneePlaatsen, 113, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAbboneePlaatsen, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblGereserveerdePlaatsen, -6, SpringLayout.NORTH, lblAbboneePlaatsen);
		contentPane.add(lblAbboneePlaatsen);
		
		JLabel lblBezetteAbboneePlaatsen = new JLabel("Bezette abbonee plaatsen");
		sl_contentPane.putConstraint(SpringLayout.WEST, carparkview, 19, SpringLayout.EAST, lblBezetteAbboneePlaatsen);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblBezetteAbboneePlaatsen, 6, SpringLayout.SOUTH, lblAbboneePlaatsen);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblBezetteAbboneePlaatsen, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblBezetteAbboneePlaatsen);
		
		JLabel lblLegePlekken = new JLabel("Lege plekken");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblLegePlekken, 6, SpringLayout.SOUTH, lblBezetteAbboneePlaatsen);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLegePlekken, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblLegePlekken);
		
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		slider.setValue(0);
		sl_contentPane.putConstraint(SpringLayout.EAST, pieview, -423, SpringLayout.WEST, slider);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, slider, 0, SpringLayout.SOUTH, pieview);
		sl_contentPane.putConstraint(SpringLayout.EAST, slider, -10, SpringLayout.EAST, contentPane);
		contentPane.add(slider);
		
		JPanel buttonPanel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, buttonPanel, 39, SpringLayout.SOUTH, carparkview);
		sl_contentPane.putConstraint(SpringLayout.WEST, buttonPanel, 11, SpringLayout.EAST, pieview);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, buttonPanel, 337, SpringLayout.NORTH, lblBezettePlaatsen);
		sl_contentPane.putConstraint(SpringLayout.EAST, buttonPanel, 271, SpringLayout.WEST, carparkview);
		contentPane.add(buttonPanel);
	}
}
