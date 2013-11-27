package com.mklr.graphics.engine;

import java.util.HashMap;

import com.mklr.graphics.stage.GameStage;
import com.mklr.graphics.stage.GameTitle;
import com.mklr.graphics.stage.Stage;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Game;

/**La classe Engine represente le moteur de jeu.
 * C'est elle qui charge les sequences du jeu et commande le raffraichissement du GameScreen.
 *
 */
public class Engine implements Runnable{
	public static final String PATH = "";
	
	private GameScreen gamescreen;
	private Stage stage;
	HashMap<String, RuzzleDictionary> dicList;
	private Window window;
	
	/**Constructeur, prend en argument la liste des dictionnaires disponibles*/
	public Engine(HashMap<String, RuzzleDictionary> dicList){
		this.gamescreen = new GameScreen();
		this.dicList = dicList;
		
		//On cree lance le thread de raffraichissement de l'ecran
		Thread t = new Thread(this);
		t.start();
	}
	
	
	   //////////////////////////////////////////////////////////////////
	  ////////////////////////////// METHODES //////////////////////////
	 //////////////////////////////////////////////////////////////////	

	//Methode de chargement des sequences
	/**Methode de chargement de l'ecran de titre*/
	public void setGameTitle(){
		//Arret des Thread de GameTitle
		if(stage != null){
			stage.close();
		}
		
		//Re agencement de la menuBar
		if(window != null){
			window.getMenubar().getiQuitterPartie().setEnabled(false);
			window.getMenubar().getmChoixLangue().setEnabled(true);
		}
		
		//Lanche de la phase d ecran de titre
		this.stage = new GameTitle(this);
		gamescreen.setStage(stage);
	}
	/**Methode de chargement de la phase de jeu, si le parametre Option est null, on charge une partie non personalisee*/
	public void setGameStage(Option option){
		//Arret des Thread de GameTitle
		if(stage != null)
			this.stage.close();
		
		//Re agencement de la menuBar
		if(window != null){
			window.getMenubar().getiQuitterPartie().setEnabled(true);
			window.getMenubar().getmChoixLangue().setEnabled(false);
		}
		
		//Creation du jeu
		Board board = null;
		if(option == null){//On doit creer une partie normale
			option = new Option();
			//Timer
			option.setTimer_time(1000*60*2);
			
			//TODO Recuperer le dico et la langue saisie dans la menubar
			String lang = window.getMenubar().langSelected();
			System.out.println(lang);
			
			//Initialisation du board
			board = new Board(lang, dicList.get(lang));
			board.init();
			
		}
		else{//On doit creer une partie personalisee
			board = new Board(option.getBoard(), option.getLang(), option.getDico());
			
		}
		
		
		Game game = new Game(this, board);
		//Lancement de la phase de jeu
		MusicPlayer.playSound(Engine.PATH + "sound/ready_go.wav");
		this.stage = new GameStage(this, board, game, option.getTimer_time());
		gamescreen.setStage(stage);
	}
	
	/**Appel le thread de raffraichissement de l'ecran*/
	public void run(){
		refresh_gamescreen();
	}
	
	//Methode de raffraichissement
	public void refresh_gamescreen(){
		while(true){
			if(stage != null)
				stage.update();
			gamescreen.repaint();
			try{
				  Thread.sleep(32); //Ici, une pause d'une seconde
			}catch(InterruptedException e) {
			  e.printStackTrace();
			}
		}
	}
	public static void sleep(int temps){
		try{
			Thread.sleep(temps);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}	
	}
	/**Methode fermant la sequence de jeu en cours et fermant son sequenceur midi proprement**/
	public int exit(){
		if(stage != null)
			stage.close();
		System.exit(0);
		return 1;
	}
	
	public void dictAreLoaded(){
		if(stage instanceof GameTitle){
			((GameTitle)stage).dictAreLoaded();
		}
	}
	
	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// ACCESSEURS MODIFIEURS///////////////////
	 //////////////////////////////////////////////////////////////////
	/**
	 * @return the sequence
	 */
	public Stage getStage() {
		return stage;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Stage stage) {
		this.stage = stage;
	}


	/**
	 * @return the gamescreen
	 */
	public GameScreen getGamescreen() {
		return gamescreen;
	}


	/**
	 * @param gamescreen the gamescreen to set
	 */
	public void setGamescreen(GameScreen gamescreen) {
		this.gamescreen = gamescreen;
	}


	/**
	 * @return the dicList
	 */
	public HashMap<String, RuzzleDictionary> getDicList() {
		return dicList;
	}


	/**
	 * @param dicList the dicList to set
	 */
	public void setDicList(HashMap<String, RuzzleDictionary> dicList) {
		this.dicList = dicList;
	}


	/**
	 * @return the window
	 */
	public Window getWindow() {
		return window;
	}


	/**
	 * @param window the window to set
	 */
	public void setWindow(Window window) {
		this.window = window;
	}

	
}
