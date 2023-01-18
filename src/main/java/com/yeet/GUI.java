package com.yeet;
import javax.swing.plaf.ColorUIResource;
import com.yeet.views.PruefungView;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.event.*;  
import javax.swing.*;
import java.awt.*;    


public class GUI{
	private static Vokabeltest voc;
	private static JFrame mainFrame;
	JPanel hinzufuegenPanel = new JPanel();
	JPanel pruefungPanel = new JPanel();
	JPanel lernPanel = new JPanel();;
	JPanel hauptMenuPanel = new JPanel();
	JPanel deck = new JPanel();
	PruefungView pruefungView;
	private CardLayout layout = new CardLayout();

	
	private void initializeAddFrame(){
		JLabel deutschesWortLabel;
		JTextField deutschesWortInput;
		JLabel englischLabel;
		JTextField englischWortInput;
		JButton enterButton;
		JLabel warningText;
		JButton switchPanelButton;
		JButton saveToJsonButton;
		JButton loadFromJsonButton;
		hinzufuegenPanel = new JPanel();
	
		warningText = new JLabel(); //Text falls Übersetzung fehlt
		warningText.setBounds(200, 75, 2000, 25);
		hinzufuegenPanel.add(warningText);
		warningText.setVisible(false);
		
		deutschesWortLabel = new JLabel("Deutsches Wort eingeben");
		deutschesWortLabel.setBounds(10, 20, 250, 25);
		hinzufuegenPanel.add(deutschesWortLabel);
	
		deutschesWortInput = new JTextField(30);
		deutschesWortInput.setBounds(175, 20, 250, 25);
		hinzufuegenPanel.add(deutschesWortInput);
		
		englischLabel = new JLabel("Übersetzung");
		englischLabel.setBounds(10, 50, 250, 25);
		hinzufuegenPanel.add(englischLabel);
		
		englischWortInput = new JTextField(30);
		englischWortInput.setBounds(175, 50, 250, 25);
		hinzufuegenPanel.add(englischWortInput);

		enterButton = new JButton("Enter Word"); 
		enterButton.setBounds(225, 85, 120, 25);
		enterButton.addActionListener(new ActionListener(){  
			String deutschesWort;
			String uebersetzung;  
			public void actionPerformed(ActionEvent e){
				deutschesWort = deutschesWortInput.getText();  //Vokabel Hinzufügen
				uebersetzung = englischWortInput.getText();
				System.out.printf("Übersetzung: %s ", uebersetzung);
				System.out.printf("Deutsch: %s ", deutschesWort);
				deutschesWortInput.setText("");
				englischWortInput.setText("");
	
				if (uebersetzung.isBlank() || deutschesWort.isBlank()){ // Zeige Fehlermeldung falls nur ein Wort eingegeben wurde
					System.out.println("Nicht Vollständig");
					warningText.setText("Wort muss vollständig mit Übersetzung eingegeben werden");
					warningText.setForeground(new ColorUIResource(255, 0, 0));
					warningText.setVisible(true);
				}
				else{
					warningText.setVisible(false);
					voc.addVoc(deutschesWort, uebersetzung);
				}
				}  
				}
			);
		hinzufuegenPanel.add(enterButton);
		
		saveToJsonButton = new JButton("In JSON Speichern"); 
		saveToJsonButton.setBounds(10, 160, 200, 25);
		saveToJsonButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				voc.saveToJson(); // alle ganze Vokabelliste wird gespeichert
				}  
				}
			);
		hinzufuegenPanel.add(saveToJsonButton);
		
		loadFromJsonButton = new JButton("Aus gespeichert Laden");
		loadFromJsonButton.setBounds(10, 195, 200, 25);
		loadFromJsonButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				try {
					voc.loadFromJson(); //Vokabelliste abrufen
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				}  
				}
			);
		hinzufuegenPanel.add(loadFromJsonButton);

		switchPanelButton = new JButton("Zurück");
		switchPanelButton.setBounds(450, 35, 120, 25);
		switchPanelButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				layout.show(deck, "main");
				}  
				}
			);
		hinzufuegenPanel.add(switchPanelButton);

		hinzufuegenPanel.setLayout(null);
		hinzufuegenPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
	}

	private void initializePruefungFrame(){
		pruefungView = new PruefungView();
		pruefungView.sortComboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				String sort = (String)cb.getSelectedItem();
				voc.sortBy(sort); //Sortierung der Vokabeln entweder zufällig oder nach Fehlerhäufigkeit
			}
		});

		pruefungView.enterButton.addActionListener(new ActionListener(){  
			String enteredWord;
			public void actionPerformed(ActionEvent e){
				enteredWord = pruefungView.englischWortInput.getText();
				pruefungView.englischWortInput.setText("");
				System.out.println(enteredWord);
				
				Boolean guessedRight = voc.guess(enteredWord);
				if(guessedRight){ //Zeige Nächste Vokabel falls die Eingabe richtig war
					System.out.println("richtig");
					VokabelWort newVoc = voc.getNextVoc();
					String newVocWord = newVoc.word;
					System.out.println(newVocWord);
					//richtigHaken.setVisible(true);
					try {
						Thread.sleep(1000);
					} catch (Exception ex) {
					}
					//richtigHaken.setVisible(false);
					pruefungView.vokabelText.setText(newVocWord);
				}
				else{
					System.out.println("falsch");
				}
			}

		});

		pruefungView.switchPanelButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				layout.show(deck, "main"); //Prüfung wird beendet
				}  
				}
			);

	}


	private void initializeLernFrame(){
		lernPanel = new JPanel();
		JButton zurueckButton;
		zurueckButton = new JButton("Zurück");
		zurueckButton.setBounds(250, 100, 120, 30);
		zurueckButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				layout.show(deck, "main");
			}});

		lernPanel.add(zurueckButton);
		int anzahlVokabeln = voc.Vokabelliste.getLength();
		String vokabeln[][] = new String[anzahlVokabeln][3];


		for (int i = 0; i < anzahlVokabeln; i++){
			VokabelWort vok = (VokabelWort) voc.Vokabelliste.getItem(i);
			vokabeln[i][0] = vok.word;
			vokabeln[i][1] = vok.translation;
			vokabeln[i][2] = Float.toString(vok.calcPercentageRight());
		}
		String[] heading = {"Deutsch", "Englisch", "% Richtig"};
		JTable tabelle = new JTable(vokabeln, heading);
		tabelle.setBounds(30, 40, 200, 300);
		JScrollPane sp = new JScrollPane(tabelle);
		lernPanel.add(sp); // Vokabeln mit Richtigskeit-Prozentsatz
	}
	

	private void initializeHomescreenFrame(){
		JButton startePruefungButton;
		JButton vokHinzufuegenButton;
		JButton listeVokButton;

		startePruefungButton = new JButton("Starte Prüfung");
		startePruefungButton.setBounds(10, 85, 120, 25);
		startePruefungButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if (voc.Vokabelliste.isEmpty()){
					pruefungView.noVocab(true, null);
					layout.show(deck, "pruefung");
				}
				else{
					voc.getNextVoc();
					pruefungView.noVocab(false, voc.voc.word);
					layout.show(deck, "pruefung");
				}	
				}  
				}
			);
		hauptMenuPanel.add(startePruefungButton);

		vokHinzufuegenButton = new JButton("Füge Vokabeln hinzu");
		vokHinzufuegenButton.setBounds(10, 120, 120, 25);
		vokHinzufuegenButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				layout.show(deck, "hinzufuegen");
				}  
				}
			);
		hauptMenuPanel.add(vokHinzufuegenButton);
		
		listeVokButton = new JButton("Liste der Vokabeln");
		listeVokButton.setBounds(10, 150, 120, 25);
		listeVokButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				initializeLernFrame();
				deck.add(lernPanel, "lernen");
				layout.show(deck, "lernen");
			}  
		}
		);
		hauptMenuPanel.add(listeVokButton);
	}
	
	
	public GUI(){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e){
			e.printStackTrace();
		}
		voc = new Vokabeltest();
		initializeAddFrame();
		initializePruefungFrame();
		initializeHomescreenFrame();
			
		deck.setLayout(layout); //Deck für die verschiedenen Ansichten
		deck.setBounds(0, 0, 1000, 1000);
		deck.add(hauptMenuPanel, "main");
		deck.add(pruefungView, "pruefung");
		deck.add(hinzufuegenPanel, "hinzufuegen"); //Auswahlmöglichkeiten für den Costumer
		
		mainFrame = new JFrame(); //Fenster der App
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(deck);
		mainFrame.setPreferredSize(new Dimension(1000, 1000));
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
}
