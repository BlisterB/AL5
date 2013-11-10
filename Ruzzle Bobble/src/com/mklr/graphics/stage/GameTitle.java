package com.mklr.graphics.stage;

import com.mklr.graphics.engine.Launcher;
import com.mklr.graphics.sprite.Sprite;

/**Sequence correspondant a la phase d'ecran de titre**/
public class GameTitle extends Stage{
	public GameTitle(){
		//Initialisation des composants
		this.background = new Sprite(Launcher.PATH + "img/background/game_title.png");
		Sprite titleSprite = new Sprite(Launcher.PATH + "img/interface/title.png", 300, -150, 200, 150);
		
		//Ajout des composants ˆ la sprite_list
		sprite_list.add(titleSprite);
		
		//Mise en mouvement des composants
		titleSprite.move(300, 50, 5);
		
	}
}
