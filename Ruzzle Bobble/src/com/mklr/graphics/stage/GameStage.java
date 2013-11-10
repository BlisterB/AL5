package com.mklr.graphics.stage;

import java.util.LinkedList;

import com.mklr.graphics.engine.Launcher;
import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.InterfaceSprite;
import com.mklr.graphics.sprite.Sprite;

public class GameStage extends Stage {
	private Bub bub = new Bub();
	
	public GameStage(){
		sprite_list = new LinkedList <Sprite>();
		
		//Background et interface
		background = new Sprite(Launcher.PATH + "img/template.png");
		sprite_list.add(new InterfaceSprite(Launcher.PATH + "img/interface/valider.png", 50, 375, 275, 50, 1)); //Bouton Valider
		sprite_list.add(new InterfaceSprite(Launcher.PATH + "img/interface/retour.png", 350, 375, 50, 50, 2)); //Bouton Valider
		
		
		//Personnages
		sprite_list.add(bub);
		/*Thread bubAnimation = new Thread(new Animation(bub));
		bubAnimation.run();*/
		bub.animation();
		System.out.println("Sortie de bub");
		
		//Lettres
	}
}
