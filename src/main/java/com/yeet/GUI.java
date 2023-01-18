package com.yeet;
import javax.swing.plaf.ColorUIResource;
import com.yeet.views.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.event.*;  
import javax.swing.*;
import java.awt.*;    


public class GUI{
	private static Vokabeltest voc;
	private static JFrame mainFrame;
	JPanel hinzufuegenPanel = new JPanel();
	JPanel lernPanel = new JPanel();;
	JPanel hauptMenuPanel = new JPanel();
	JPanel deck = new JPanel();
	PruefungView pruefungView;
	AddVocView addVocView;
	private CardLayout layout = new CardLayout();

	
	private void initializeAddFrame(){
		addVocView = new AddVocView();

		addVocView.enterButton.addActionListener(new ActionListener(){  
			String deutschesWort;
			String uebersetzung;  
			public void actionPerformed(ActionEvent e){
				deutschesWort = addVocView.deutschesWortInput.getText();  //Vokabel Hinzufügen
				uebersetzung = addVocView.englischWortInput.getText();
				System.out.printf("Übersetzung: %s ", uebersetzung);
				System.out.printf("Deutsch: %s ", deutschesWort);
				addVocView.deutschesWortInput.setText("");
				addVocView.englischWortInput.setText("");
	
				if (uebersetzung.isBlank() || deutschesWort.isBlank()){ // Zeige Fehlermeldung falls nur ein Wort eingegeben wurde
					System.out.println("Nicht Vollständig");
					addVocView.warningText.setText("Wort muss vollständig mit Übersetzung eingegeben werden");
					addVocView.warningText.setForeground(new ColorUIResource(255, 0, 0));
					addVocView.warningText.setVisible(true);
				}
				else{
					addVocView.warningText.setVisible(false);
					voc.addVoc(deutschesWort, uebersetzung);
				}
				}  
				}
			);

		addVocView.saveToJsonButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				voc.saveToJson(); // alle ganze Vokabelliste wird gespeichert
				}  }
			);

		addVocView.loadFromJsonButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
					try {
						voc.loadFromJson(); //Vokabelliste abrufen
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} }
			);

		addVocView.switchPanelButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				layout.show(deck, "main");
				}  
				}
			);
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
		deck.add(addVocView, "hinzufuegen"); //Auswahlmöglichkeiten für den Costumer
		
		mainFrame = new JFrame(); //Fenster der App
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(deck);
		mainFrame.setPreferredSize(new Dimension(1000, 1000));
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
}
