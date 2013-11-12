package com.mklr.graphics.stage;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.Launcher;
import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.InterfaceSprite;
import com.mklr.graphics.sprite.LetterSprite;
import com.mklr.graphics.sprite.Sprite;
import com.mklr.ruzzle.data.Letter;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;

public class GameStage extends Stage {
	private Bub bub = new Bub(550, 330, Bub.STANDING);
	private Board board;
	
	public GameStage(Engine engine, Board board){
		super(engine);
		this.board = board;
		
		//Background et interface
		background = new Sprite(Launcher.PATH + "img/background/game_stage.png");
		sprite_list.add(new InterfaceSprite(Launcher.PATH + "img/interface/valider.png", 50, 375, 275, 50, 1, this)); //Bouton Valider
		sprite_list.add(new InterfaceSprite(Launcher.PATH + "img/interface/retour.png", 350, 375, 50, 50, 2, this)); //Bouton Valider
		sprite_list.add(new Sprite(Launcher.PATH + "img/interface/interface_game.png", 0, 0, 0, 0));
		
		//Chargement des lettres
		LetterSprite[] letter_array = new LetterSprite[24];
		Marble[][] tab = board.getBoard();
		int k = 0;
		for(int i = 0; i < tab.length; i++){
			for(int j = 0; j < tab[i].length; j++){
				sprite_list.add(new LetterSprite(Launcher.PATH + "img/fonts/" + tab[i][j].getLetter().getLetter().toString()+".png",
						getLetterX(i,j), getLetterY(i,j), 30,30, tab[i][j].getLetter().getLetter(), tab[i][j]));
			}
		}
		
		//Personnages
		sprite_list.add(bub);
		bub.animation();
		
		//Lettres
	}
	
	public int getLetterY(int i, int j){
		switch(i){
			case 0 :
				if(j == 0 || j == 2 || j == 4)
					return 65;
				else
					return 35;
			case 1 :
				if(j == 0 || j == 2 || j == 4|| j == 6)
					return 145;
				else
					return 115;
			case 2 :
				if(j == 0 || j == 2 || j == 4 || j == 6)
					return 197;
				else
					return 227;
			case 3 :
				if(j == 0 || j == 2 || j == 4)
					return 277;
				else
					return 310;
			default :
				return 0;
		}
	}
	
	public int getLetterX(int i, int j){
		if(i == 0 || i == 3){
			switch(j){
				case 0 :
					return 109;
				case 1 :
					return 159;
				case 2 :
					return 209;
				case 3 :
					return 259;
				case 4 :
					return 309;
				default :
					return 0;
			}
		}
		else{
			switch(j){
				case 0 :
					return 59;
				case 1 :
					return 109;
				case 2 :
					return 159;
				case 3 :
					return 209;
				case 4 :
					return 259;
				case 5 :
					return 309;
				case 6 :
					return 359;
				default :
					return 0;
			}
		}
	}
}
