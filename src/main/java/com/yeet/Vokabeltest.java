package com.yeet;
//import java.util.ArrayList;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;


public class Vokabeltest{
	public DynArray Vokabelliste = new DynArray();
	private int counter;
	VokabelWort voc;
	String vokJsonPath = "Vokabeln.json";
	Jsonloader jsonload = new Jsonloader();

	public Vokabeltest(){
		
	}

	public void saveToJson(){
		ArrayList<VokabelWort> vokArrLst = loadAsArrLst();
		try {
			Jsonloader.writeToFile(vokJsonPath, vokArrLst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void loadFromJson() throws StreamReadException, DatabindException, IOException{
		VokabelWort[] vokabelnArr = Jsonloader.fromJsonFile(vokJsonPath);

		while(!Vokabelliste.isEmpty()){
			Vokabelliste.delete(0);
		}

		for (VokabelWort vokabelWort : vokabelnArr) {
			System.out.println(vokabelWort.word + "   " + vokabelWort.translation);
			Vokabelliste.append(vokabelWort);
		}

	}

	public void addVoc(String word, String translation){
		VokabelWort newVoc = new VokabelWort(word, translation);
		Vokabelliste.append(newVoc); 
	}


	public VokabelWort getNextVoc(){
		VokabelWort voc = (VokabelWort) Vokabelliste.getItem(counter);
		this.voc = voc;
		counter++;
		if (counter >= Vokabelliste.getLength()){
			counter = 0;
		}
		return voc;
	}


	public void shuffleVocList(){
		ArrayList<VokabelWort> vokArrLst = new ArrayList<VokabelWort>();
		
		while(!Vokabelliste.isEmpty()){
			VokabelWort vok = (VokabelWort) Vokabelliste.getItem(0);
			Vokabelliste.delete(0);
			vokArrLst.add(vok);
		}

		Collections.shuffle(vokArrLst);
		System.out.println(vokArrLst.size());
		for (VokabelWort vokabel : vokArrLst) {
			//System.out.println(vokabel.word);
			Vokabelliste.append(vokabel);
		}


	}

	public ArrayList<VokabelWort> loadAsArrLst(){
		ArrayList<VokabelWort> vokArrLst = new ArrayList<VokabelWort>();
		int laengeListe = Vokabelliste.getLength();
		for (int i = 0; i < laengeListe; i++) {	
			VokabelWort vok = (VokabelWort) Vokabelliste.getItem(i);
			vokArrLst.add(vok);
		}
		

		return vokArrLst;
	}


	public boolean guess(String guess){
		if (this.voc.translation.equals(guess)){
			this.voc.guessedRight += 1;
			return true;
		}
		else{
			this.voc.guessedWrong += 1;
			return false;
		}
	}
}