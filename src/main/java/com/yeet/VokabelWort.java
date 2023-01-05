package com.yeet;
public class VokabelWort {
	public String word;
	public String translation;
	public int guessedRight;
	public int guessedWrong;

	public VokabelWort(String word, String translation){
		this.word = word;
		this.translation = translation;
		this.guessedRight = 0;
		this.guessedRight = 0;
	}

	public Float getPercentageRight(){
		float percentageright = 100 * (guessedRight / guessedWrong);
		return percentageright;
	}
}
