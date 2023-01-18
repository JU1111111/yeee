package com.yeet.views;

import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource; 
import javax.swing.*;
import java.awt.*;   

public class PruefungView extends JPanel{
	public JLabel englischLabel; // Übersetzung Eingeben Text
	public JTextField englischWortInput; //EingabeFeld Übersetzung
	public JButton enterButton; 
	public JLabel vokabelText;// Vokabel zum abfragen
	public JButton switchPanelButton; // Button zurück zum Hauptmenu
	public JComboBox<String> sortComboBox;
	public JLabel sortierungText;
	public JLabel noVocText;
	


	public PruefungView(){

		this.setLayout(null);

		vokabelText = new JLabel();
		vokabelText.setFont(new Font("Serif", Font.PLAIN, 30));
		vokabelText.setBounds(250, 10, 250, 50);
		vokabelText.setVisible(false);
		this.add(vokabelText);

		noVocText = new JLabel();
		noVocText.setBounds(250, 10, 250, 50);
		noVocText.setText("Keine Vokabeln");
		noVocText.setForeground(new ColorUIResource(255, 0, 0));
		noVocText.setFont(new Font("Serif", Font.PLAIN, 30));
		noVocText.setVisible(false);
		this.add(noVocText);

	
		vokabelText.setVisible(true);

		englischLabel = new JLabel("Übersetzung");
		englischLabel.setBounds(30, 60, 250, 25);
		this.add(englischLabel);
		
		englischWortInput = new JTextField(30);
		englischWortInput.setBounds(175, 60, 250, 25);
		this.add(englischWortInput);

		String[] sorts = {"random", "percentage"};
		sortComboBox = new JComboBox<String>(sorts);
		sortComboBox.setBounds(700, 10, 100, 20);
		sortComboBox.setSelectedIndex(0);
		this.add(sortComboBox);

		sortierungText = new JLabel();
		sortierungText.setText("Sortierung");
		sortierungText.setBounds(600, 10, 150, 20);
		this.add(sortierungText);

		enterButton = new JButton("Enter Word");
		enterButton.setBounds(250, 100, 120, 30);
		this.add(enterButton);

		switchPanelButton = new JButton("Beende Prüfung");
		switchPanelButton.setBounds(250, 140, 120, 25);
		this.add(switchPanelButton);
		
	}

	public void noVocab(Boolean noVoc, String neueVok){
		if (noVoc){
			vokabelText.setVisible(false);
			noVocText.setVisible(true);
		}
		else if(neueVok != null){
			vokabelText.setVisible(true);
			vokabelText.setText(neueVok);
			noVocText.setVisible(false);
		}
	}
}
