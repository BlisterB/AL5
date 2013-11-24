package com.mklr.graphics.stage;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.GameTimer;
import com.mklr.graphics.engine.MusicPlayer;
import com.mklr.graphics.sprite.Bob;
import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.InterfaceSprite;
import com.mklr.graphics.sprite.LetterSprite;
import com.mklr.graphics.sprite.NumberSprite;
import com.mklr.graphics.sprite.Sprite;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Game;
import com.mklr.ruzzle.engine.Marble;

public class GameStage extends Stage {
	private Bub bub = new Bub(560, 330, Bub.STANDING);
	private Bob bob = new Bob(610, 330, Bob.STANDING);
	private Board board;
	private String currentWord = "";
	private ArrayList<Marble> marblesOfTheCurrentWord = new ArrayList<Marble>() ;
	private LetterSprite[] letter_array = new LetterSprite[24];
	private GameTimer timer;
	private NumberSprite[] timeSprite;
	private NumberSprite[] scoreSprite; //0 est l'unite, 1 la dizaine etc.
	private Game game;
	
	public GameStage(Engine engine, Board board, Game game){
		super(engine);
		this.board = board;
		this.game = game;
		this.musicPlayer = new MusicPlayer("music/gamestage.mid", true);
		this.timer = new GameTimer(1000 * 60 * 2, this);
		
		//Background et interface
		background = new Sprite(Engine.PATH + "img/background/game_stage.png");
		sprite_list.add(new InterfaceSprite(Engine.PATH + "img/interface/afficher_les_mots.png", 50, 375, 275, 50, 1, this)); //Bouton Valider
		//sprite_list.add(new Sprite(Engine.PATH + "img/interface/interface_game.png", 0, 0, 0, 0));
		
		//Chargement des lettres du board
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
		
		//Chargement des nombres du timer et des 2 deux points
		timeSprite = new NumberSprite[5];
		for(int i = 0; i < 5; i++){
			timeSprite[i] = new NumberSprite(600 + 40*i, 10);
			sprite_list.add(timeSprite[i]);
		}
		sprite_list.add(new Sprite("img/fonts/colon.png", 658, 6, 30, 25));
		sprite_list.add(new Sprite("img/fonts/colon.png", 740, 6, 30, 25));
		
		//Chargement des nombres du score
		scoreSprite = new NumberSprite[12];
		for(int i = 0; i < scoreSprite.length; i++){
			scoreSprite[i] = new NumberSprite(740 - 22*i, 335);
			sprite_list.add(scoreSprite[i]);
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
	
	/** Ajoute c au mot en train d'être saisie par le joueur sur le plateau*/
	public void addToCurrentWord(char letter){
		currentWord = currentWord + letter;
	}
	
	/** Envoe le mot saisie par le joueur à game et gere les animations du score associé au mot*/
	public void sendCurrentWord(){
		//On envoie à game le mot actuel et la liste de marbles associée
		int scoreOfTheMove = game.getScoreofMove(marblesOfTheCurrentWord, currentWord);
		System.out.println("Score du mot : " + currentWord + " -> " + scoreOfTheMove);
		//On anime Bob et Bub en fonction du score obtenu par le mot
		if(scoreOfTheMove <= 0){
			bub.setAnimation(bub.FEAR2);
			bob.setAnimation(bob.STRANGE);
		}
		else if(scoreOfTheMove < 7){
			bub.setAnimation(bub.HAPPY);
			bob.setAnimation(bob.HAPPY);
		}
		else{
			bub.setAnimation(bub.JUMPING);
			bob.setAnimation(bob.WHOA);
		}
		
		//On efface le mot courant ainsi que sa liste de marble associée
		currentWord = "";
		flushSelectedLetter();
	}
	
	public void flushSelectedLetter(){
		marblesOfTheCurrentWord.clear();
        for(int i = 0; i < letter_array.length; i++){
        	letter_array[i].setSelected(false);
        }

	}
	
	public void update(){
		//Mise a jour du timer
		timeSprite[0].changeNumber(timer.getDizaineMinute());
		timeSprite[1].changeNumber(timer.getUniteMinute());
		timeSprite[2].changeNumber(timer.getDizaineSeconde());
		timeSprite[3].changeNumber(timer.getUniteSeconde());
		timeSprite[4].changeNumber(timer.getDiziemeSeconde());
		
		//Mise a jour du score
		for(int i = 0; i < scoreSprite.length; i++){
			int s = game.getScore();
			//On enleve les chiffre apres le chiffre qui nous interesse
			s /= (int)(Math.pow(10, i));
			//On affiche le chiffre seulement si c'est l'unité ou si le nombre apres son indice est superieur a 0
			if(i == 0 || s > 0){
				scoreSprite[i].setDisplayable(true);
				scoreSprite[i].changeNumber(s%10);
			}
			else
				scoreSprite[i].setDisplayable(false);
		}
	}
	
	public void close(){
		timer.close();
		super.close();
	}	

	public void interaction(int i){
		super.interaction(i);
		if(i == TIMMER_END)
			timeOut();
	}
	
	public void timeOut(){
    	String info = "Bravo !\nVous avez obtenu un score de " + game.getScore() + " !\nArriverez vous à faire mieux la prochaine fois ?";
    	JOptionPane aProposWindow = new JOptionPane();
    	aProposWindow.showMessageDialog(null, info, "Time out !", JOptionPane.INFORMATION_MESSAGE);
    	engine.setGameTitle();
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

       //////////////////////////////////////////////////////////////////
	  ///////////////////////// ACCESSEURS MODIFIEURS///////////////////
	 //////////////////////////////////////////////////////////////////
	/**
	 * @return the currentWorld
	 */
	public String getCurrentWorld() {
		return currentWord;
	}
	/**
	 * @param currentWorld the currentWorld to set
	 */
	public void setCurrentWorld(String currentWorld) {
		this.currentWord = currentWorld;
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
	/**
	 * @return the timer
	 */
	public GameTimer getTimer() {
		return timer;
	}
	/**
	 * @param timer the timer to set
	 */
	public void setTimer(GameTimer timer) {
		this.timer = timer;
	}

	public ArrayList<Marble> getMarblesOfTheCurrentWord() {
		return marblesOfTheCurrentWord;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the timeSprite
	 */
	public NumberSprite[] getTimeSprite() {
		return timeSprite;
	}

	/**
	 * @param timeSprite the timeSprite to set
	 */
	public void setTimeSprite(NumberSprite[] timeSprite) {
		this.timeSprite = timeSprite;
	}
	
}
