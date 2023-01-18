package com.yeet.views;

import javax.swing.JPanel; 
import javax.swing.*;


public class AddVocView extends JPanel {
	public JLabel deutschesWortLabel;
	public JTextField deutschesWortInput;
	public JLabel englischLabel;
	public JTextField englischWortInput;
	public JButton enterButton;
	public JLabel warningText;
	public JButton switchPanelButton;
	public JButton saveToJsonButton;
	public JButton loadFromJsonButton;

	public AddVocView(){
		warningText = new JLabel(); //Text falls Übersetzung fehlt
		warningText.setBounds(200, 75, 2000, 25);
		this.add(warningText);
		warningText.setVisible(false);
		
		deutschesWortLabel = new JLabel("Deutsches Wort eingeben");
		deutschesWortLabel.setBounds(10, 20, 250, 25);
		this.add(deutschesWortLabel);
	
		deutschesWortInput = new JTextField(30);
		deutschesWortInput.setBounds(175, 20, 250, 25);
		this.add(deutschesWortInput);
		
		englischLabel = new JLabel("Übersetzung");
		englischLabel.setBounds(10, 50, 250, 25);
		this.add(englischLabel);
		
		englischWortInput = new JTextField(30);
		englischWortInput.setBounds(175, 50, 250, 25);
		this.add(englischWortInput);

		enterButton = new JButton("Enter Word"); 
		enterButton.setBounds(225, 85, 120, 25);
		this.add(enterButton);

		saveToJsonButton = new JButton("In JSON Speichern"); 
		saveToJsonButton.setBounds(10, 160, 200, 25);
		this.add(saveToJsonButton);

		loadFromJsonButton = new JButton("Aus gespeichert Laden");
		loadFromJsonButton.setBounds(10, 195, 200, 25);
		this.add(loadFromJsonButton);

		switchPanelButton = new JButton("Zurück");
		switchPanelButton.setBounds(450, 35, 120, 25);
		this.add(switchPanelButton);


		this.setLayout(null);
		this.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
	}

}
