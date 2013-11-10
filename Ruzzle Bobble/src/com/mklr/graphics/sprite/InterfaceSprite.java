package com.mklr.graphics.sprite;


/** Instancie des Sprite representant un element d'interface graphique*/
public class InterfaceSprite extends Sprite {
	private int function; //1 : Valider, 2 : Retour
	
	public InterfaceSprite(String chemin, int x, int y, int width, int height, int function){
		super(chemin, x, y, width, height);
		this.function = function;
	}
	
	public int getFunction(){
		return function;
	}
}
