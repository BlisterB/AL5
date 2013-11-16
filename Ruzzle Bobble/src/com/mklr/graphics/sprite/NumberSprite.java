package com.mklr.graphics.sprite;

import java.awt.Image;

public class NumberSprite extends Sprite {
	public static Image[] NUMBER_IMAGE = Sprite.getNumberImageArray();
	
	public NumberSprite(int x, int y){
		super(NUMBER_IMAGE[0], x, y, 25, 30);
	}
	
	public void changeNumber(int n){
		image = NUMBER_IMAGE[n];
	}
}
