package com.mklr.graphics.stage;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.Launcher;
import com.mklr.graphics.sprite.Bob;
import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.InterfaceSprite;
import com.mklr.graphics.sprite.Sprite;

/**Sequence correspondant a la phase d'ecran de titre**/
public class GameTitle extends Stage{
	private Bub bub;
	private Bob bob;
	private Sprite titleSprite;
	private InterfaceSprite jouerInterface;
	private boolean animation;//Definie si on continue a bouger les elements a l'ecran ou non
	
	public GameTitle(Engine engine){
		super(engine);
		
		//Initialisation des composants
		this.background = new Sprite(Launcher.PATH + "img/background/game_title.png");
		titleSprite = new Sprite(Launcher.PATH + "img/interface/title.png", 300, -150, 200, 150);
		jouerInterface = new InterfaceSprite(Launcher.PATH + "img/interface/jouer.png", 200, 450, 400, 100, 1, this);
		bub = new Bub(800, 150, Bub.FEAR);
		bob = new Bob(850, 150, Bob.BUBBLE_LEFT);
		
		//Ajout des composants a la sprite_list
		sprite_list.add(titleSprite);
		sprite_list.add(jouerInterface);
		sprite_list.add(bub);
		sprite_list.add(bob);
		
		//Mise en mouvement des composants
		animation = true;
		animation();
	}
	
	public void animation(){
		new Thread(new Runnable(){
			public void run(){
				//Arrive de l'ecran et du bouton jouer
				titleSprite.move(300, 0, 5);
				jouerInterface.move(200, 325, 5);
				
				//Boucle du gag de Bub et Bob
				while(animation){
					//Aller
					bub.setAnimation(Bub.FEAR);
					bub.move(-100, 150, 8);
					bob.setAnimation(Bob.BUBBLE_LEFT);
					bob.move(-150, 150, 8);
					sleep(10000);
					
					//Retour
					if(animation){
						bub.setAnimation(Bub.CRY);
						bub.move(800, 150, 8);
						bob.setAnimation(Bob.BUBBLE_RIGHT);
						bob.move(850, 150, 8);
						sleep(10000);
					}
				}
			}
		}).start();
		
	}
	
	public void interaction(int function){
		if (function == 1){
			engine.setGameStage();
		}
	}
	
	public void close(){
		animation = false;
	}
}
