package com.mklr.graphics.stage;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.MusicPlayer;
import com.mklr.graphics.sprite.Bob;
import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.InterfaceSprite;
import com.mklr.graphics.sprite.LetterSprite;
import com.mklr.graphics.sprite.Sprite;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;

public class GameStage extends Stage {
	private Bub bub = new Bub(550, 330, Bub.STANDING);
	private Bob bob = new Bob(600, 330, Bob.STANDING);
	private Board board;
	private String currentWorld = "";
	private LetterSprite[] letter_array = new LetterSprite[24];
	
	public GameStage(Engine engine, Board board){
		super(engine);
		this.board = board;
		this.musicPlayer = new MusicPlayer("music/gamestage.mid");
		
		//Background et interface
		background = new Sprite(Engine.PATH + "img/background/game_stage.png");
		sprite_list.add(new InterfaceSprite(Engine.PATH + "img/interface/afficher_les_mots.png", 50, 375, 275, 50, 1, this)); //Bouton Valider
		sprite_list.add(new Sprite(Engine.PATH + "img/interface/interface_game.png", 0, 0, 0, 0));
		
		//Chargement des lettres
		Marble[][] tab = board.getBoard();
		int k = 0;
		for(int i = 0; i < tab.length; i++){
			for(int j = 0; j < tab[i].length; j++){
				letter_array[k] = new LetterSprite(Engine.PATH + "img/fonts/" + tab[i][j].getLetter().getLetter().toString()+".png", getLetterX(i,j), getLetterY(i,j), 30,30, tab[i][j].getLetter().getLetter(),i, j, tab[i][j], this);

				//Ajout de la lettre
				sprite_list.add(letter_array[k]);
				
				//Ajout de la vignette Bonus
				String bonus_path = "";
				if(letter_array[k].getMarble().getBonus() == Marble.LETTER_COUNT_DOUBLE){
					bonus_path = Engine.PATH + "img/interface/green_buble.png";
				}
				else if(letter_array[k].getMarble().getBonus() == Marble.LETTER_COUNT_TRIPLE){
					bonus_path = Engine.PATH + "img/interface/blue_buble.png";
				}
				else if(letter_array[k].getMarble().getBonus() == Marble.WORD_COUNT_DOUBLE){
					bonus_path = Engine.PATH + "img/interface/red_buble.png";
				}
				else if(letter_array[k].getMarble().getBonus() == Marble.WORD_COUNT_TRIPLE){
					bonus_path = Engine.PATH + "img/interface/yellow_buble.png";
				}
				if(!(letter_array[k].getMarble().getBonus() == Marble.NO_BONUS)){
					sprite_list.add(new Sprite(bonus_path, 20 + getLetterX(i,j), getLetterY(i,j) - 10, 25, 25));
				}
				k++;
			}
		}
		
		//Personnages
		sprite_list.add(bub);
		sprite_list.add(bob);
		bub.animation();
		bob.animation();
	}
	   //////////////////////////////////////////////////////////////////
	  ////////////////////////////// METHODES //////////////////////////
	 //////////////////////////////////////////////////////////////////	
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
	
	public void addToCurrentWord(char letter){
		currentWorld = currentWorld + letter;
	}
	
	public void flushSelectedLetter(){
        for(int i = 0; i < letter_array.length; i++){
        	letter_array[i].setSelected(false);
        }

	}
	
	public void close(){
		super.close();
	}
	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// ACCESSEURS MODIFIEURS///////////////////
	 //////////////////////////////////////////////////////////////////
	/**
	 * @return the currentWorld
	 */
	public String getCurrentWorld() {
		return currentWorld;
	}
	/**
	 * @param currentWorld the currentWorld to set
	 */
	public void setCurrentWorld(String currentWorld) {
		this.currentWorld = currentWorld;
	}
	/**
	 * @return the letter_array
	 */
	public LetterSprite[] getLetter_array() {
		return letter_array;
	}
	/**
	 * @param letter_array the letter_array to set
	 */
	public void setLetter_array(LetterSprite[] letter_array) {
		this.letter_array = letter_array;
	}
	
}
