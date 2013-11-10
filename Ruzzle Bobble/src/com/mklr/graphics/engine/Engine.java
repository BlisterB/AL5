package com.mklr.graphics.engine;

import com.mklr.graphics.stage.GameStage;
import com.mklr.graphics.stage.GameTitle;
import com.mklr.graphics.stage.Stage;

/**La classe Engine represente le moteur de jeu
 * C'est elle qui charge les sequences du jeu et commande le raffraichissement du GameScreen.
 * @author Mehdi
 *
 */
public class Engine implements Runnable{
	private GameScreen gamescreen;
	private Stage stage;
	
	public Engine(GameScreen gamescreen){
		this.gamescreen = gamescreen;
		
		//On cree lance le thread de raffraichissement de l'ecran
		Thread t = new Thread(this);
		t.start();
	}
	
	
	   //////////////////////////////////////////////////////////////////
	  ////////////////////////////// METHODES //////////////////////////
	 //////////////////////////////////////////////////////////////////	

	//Methode de chargement des sequences
	public void setGameTitle(){
		this.stage = new GameTitle();
		gamescreen.setStage(stage);
	}
	public void run_game(){
		this.stage = new GameStage();
		gamescreen.setStage(stage);
		gamescreen.repaint();
	}
	
	public void run(){
		while(true){
			gamescreen.repaint();
			try{
				  Thread.sleep(1000); //Ici, une pause d'une seconde
			}catch(InterruptedException e) {
			  e.printStackTrace();
			}
		}
		//refresh_gamescreen();
	}
	
	//Methode de raffraichissement
	public void refresh_gamescreen(){
		while(true){
			gamescreen.repaint();
			try{
				  Thread.sleep(1); //Ici, une pause d'une seconde
			}catch(InterruptedException e) {
			  e.printStackTrace();
			}
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
}
