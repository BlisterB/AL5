package com.mklr.graphics.stage;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.MusicPlayer;
import com.mklr.graphics.sprite.Bob;
import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.BubLoading;
import com.mklr.graphics.sprite.InterfaceSprite;
import com.mklr.graphics.sprite.Sprite;

/**Sequence correspondant a la phase d'ecran de titre**/
public class GameTitle extends Stage{
	private Bub bub;
	private Bob bob;
	private BubLoading bubLoading;
	private Sprite titleSprite;
	private InterfaceSprite jouerInterface;
	private Sprite textLoadingSprite;
	private boolean animation;//Definie si on continue a bouger les elements a l'ecran ou non
	
	public GameTitle(Engine engine){
		super(engine);
		this.musicPlayer = new MusicPlayer("music/title.mid");
		
		//Initialisation des composants
		this.background = new Sprite(Engine.PATH + "img/background/game_title.png");
		titleSprite = new Sprite(Engine.PATH + "img/interface/title.png", 300, -150, 200, 150);
		jouerInterface = new InterfaceSprite(Engine.PATH + "img/interface/jouer.png", 200, 450, 400, 100, Stage.VALIDATE, this);
		bub = new Bub(800, 150, Bub.FEAR);
		bob = new Bob(850, 150, Bob.BUBBLE_LEFT);
		bubLoading = new BubLoading(0, 410, BubLoading.LOADING);
		textLoadingSprite = new Sprite(Engine.PATH + "img/bub_loading/chargement.png", 60, 425, 275, 25);
		
		//Ajout des composants a la sprite_list
		sprite_list.add(titleSprite);
		sprite_list.add(bub);
		sprite_list.add(bob);
		sprite_list.add(bubLoading);
		sprite_list.add(textLoadingSprite);
		sprite_list.add(jouerInterface);
		
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
					bub.move(-100, 150, 7);
					bob.setAnimation(Bob.BUBBLE_LEFT);
					bob.move(-150, 150, 7);
					sleep(9000);
					
					//Retour
					if(animation){
						bub.setAnimation(Bub.CRY);
						bub.move(800, 150, 7);
						bob.setAnimation(Bob.BUBBLE_RIGHT);
						bob.move(850, 150, 7);
						sleep(9000);
					}
				}
			}
		}).start();
		
	}
	
	public void dictAreLoaded() {
		System.out.println("Les dictionnaires sont chargés, on peut afficher le bouton jouer");
		bubLoading.setTransparency(0f, 10);
		textLoadingSprite.setTransparency(0f, 10);
		jouerInterface.move(200, 325, 5);
	}
	
	public void interaction(int function){
		if (function == 1){//Clic sur le bouton jouer
			engine.setGameStage();
		}
	}
	
	public void close(){
		super.close();
		animation = false;
	}
}
