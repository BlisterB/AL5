package com.mklr.graphics.sprite;

import com.mklr.graphics.stage.Stage;

/**
 * Instancie des Sprite representant un element d'interface graphique
 * 
 * @author Mehdi
 * 
 */

public class InterfaceSprite extends Sprite {
	private int function; //1 : Valider, 2 : Retour
	private Stage associatedStage;
	
	public InterfaceSprite(String chemin, int x, int y, int width, int height, int function, Stage associatedStage){
		super(chemin, x, y, width, height);
		this.function = function;
		this.associatedStage = associatedStage;
	}
	
	public void onClick(){
		associatedStage.interaction(function);
	}
	
	public int getFunction(){
		return function;
	}
}
