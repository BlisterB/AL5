package com.mklr.graphics.stage;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.Launcher;
import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.InterfaceSprite;
import com.mklr.graphics.sprite.Sprite;

public class GameStage extends Stage {
	private Bub bub = new Bub(550, 330, Bub.STANDING);
	
	public GameStage(Engine engine){
		super(engine);
		
		//Background et interface
		background = new Sprite(Launcher.PATH + "img/background/game_title.png");
		sprite_list.add(new InterfaceSprite(Launcher.PATH + "img/interface/valider.png", 50, 375, 275, 50, 1, this)); //Bouton Valider
		sprite_list.add(new InterfaceSprite(Launcher.PATH + "img/interface/retour.png", 350, 375, 50, 50, 2, this)); //Bouton Valider
		
		
		//Personnages
		sprite_list.add(bub);
		/*Thread bubAnimation = new Thread(new Animation(bub));
		bubAnimation.run();*/
		bub.animation();
		
		//Lettres
	}
}
