package com.mklr.graphics.stage;

import java.awt.Rectangle;

import com.mklr.graphics.engine.Launcher;
import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.Sprite;

/**Sequence correspondant a la phase d'ecran de titre**/
public class GameTitle extends Stage{
	private Bub bub;
	private Sprite titleSprite;
	private boolean animation;//Definie si on continue a bouger les elements a l'ecran ou non
	
	public GameTitle(){
		//Initialisation des composants
		this.background = new Sprite(Launcher.PATH + "img/background/game_title.png");
		titleSprite = new Sprite(Launcher.PATH + "img/interface/title.png", 300, -150, 200, 150);
		bub = new Bub(800, 150, Bub.FEAR);
		
		//Ajout des composants ˆ la sprite_list
		sprite_list.add(titleSprite);
		sprite_list.add(bub);
		
		//Mise en mouvement des composants
		animation = true;
		animation();
	}
	
	public void animation(){
		new Thread(new Runnable(){
			public void run(){
				//Arrive de l'ecran et du bouton jouer
				titleSprite.move(300, 0, 5);
				
				//Boucle du gag de Bub et Bob
				while(animation){
					//Aller
					bub.setAnimation(Bub.FEAR);
					bub.move(-100, 150, 8);
					sleep(10000);
					
					//Retour
					if(animation){
						bub.setAnimation(Bub.CRY);
						bub.move(800, 150, 8);
						sleep(10000);
					}
				}
			}
		}).start();
		
	}
}
