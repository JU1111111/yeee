package com.yeet;

public class VokabelWort {
	public String word;
	public String translation;
	public int guessedRight;
	public int guessedWrong;

	public VokabelWort(){

	}
	
	public VokabelWort(String word, String translation){
		this.word = word;
		this.translation = translation;
		this.guessedRight = 0;
		this.guessedRight = 0;
	}

	public VokabelWort(String word, String translation, int guessedRight, int guessedWrong, int percentageright){
		this.word = word;
		this.translation = translation;
		this.guessedRight = guessedRight;
		this.guessedRight = guessedWrong;
	}

	public Float calcPercentageRight(){
		float percentageright;
		if (guessedWrong != 0){
			percentageright = 100 * (guessedRight / guessedWrong);
		}
		else if(guessedRight == 0){
			percentageright = 0;
		}
		else{
			percentageright = 100;
		}
		return percentageright;
	}
}
