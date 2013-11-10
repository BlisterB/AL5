package com.mklr.graphics.sprite;

public class LetterSprite extends Sprite {
	private boolean selected;
	private char letter;
	
	public LetterSprite(String chemin, int x, int y, int width, int height, char letter){
		super(chemin, x, y, width, height);
		this.letter = letter;
		this.selected = false;
	}
	
	public boolean isSelected(){
		return selected;
	}
	public char getLetter(){
		return letter;
	}
}
