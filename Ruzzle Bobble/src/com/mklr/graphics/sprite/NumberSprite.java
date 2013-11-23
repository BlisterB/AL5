package com.mklr.graphics.sprite;

import java.awt.Image;

import com.mklr.graphics.engine.Engine;

public class NumberSprite extends Sprite {
	public static Image[] NUMBER_IMAGE = getNumberImageArray();
	
	/** Fonction statique renvoyant un tableau de 11 cases contenant les images de nombres, la 11eme case contient une image de deux points */
	public static Image[] getNumberImageArray(){
		Image[] array = new Image[12];
		for(int i = 0; i < 10; i++){
			array[i] = openImage(Engine.PATH + "img/fonts/" + i + ".png");
		}
		array[11] = openImage(Engine.PATH + "img/fonts/colon.png");
		
		return array;
	}
	public NumberSprite(int x, int y){
		super(NUMBER_IMAGE[0], x, y, 25, 30);
	}
	
	public void changeNumber(int n){
		image = NUMBER_IMAGE[n];
	}
}
