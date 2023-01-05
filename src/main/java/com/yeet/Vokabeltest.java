package com.yeet;
//import java.util.ArrayList;



import java.util.*;


public class Vokabeltest{
	public DynArray Vokabelliste = new DynArray();
	DynArray shuffeledList= new DynArray();
	private int counter;
	VokabelWort voc;

	public Vokabeltest(){
		
	}


	public void addVoc(String word, String translation){
		VokabelWort newVoc = new VokabelWort(word, translation);
		Vokabelliste.append(newVoc); 
	}


	public VokabelWort getNextVoc(){
		VokabelWort voc = (VokabelWort) Vokabelliste.getItem(counter);
		this.voc = voc;
		counter++;
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