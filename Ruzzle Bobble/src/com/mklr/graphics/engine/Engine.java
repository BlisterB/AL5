package com.mklr.graphics.engine;

import com.mklr.graphics.stage.GameStage;
import com.mklr.graphics.stage.GameTitle;
import com.mklr.graphics.stage.Stage;

/**La classe Engine represente le moteur de jeu
 * C'est elle qui charge les sequences du jeu et commande le raffraichissement du GameScreen.
 * @author Mehdi
 *
 */
public class Engine {
	private GameScreen gamescreen;
	private Stage stage;
	
	public Engine(GameScreen gamescreen){
		this.gamescreen = gamescreen;
		
		//LANCEMENT DU JEU
		this.stage = new GameStage();
	    
	}


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
