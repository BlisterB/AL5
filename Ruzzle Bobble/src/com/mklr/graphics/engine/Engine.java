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
	private Stage sequence;
	
	public Engine(){
		this.sequence = new GameStage();
	    
	    gamescreen.setStage(engine.getSequence());
	    
	    gamescreen.repaint();
	    
	}


	/**
	 * @return the sequence
	 */
	public Stage getSequence() {
		return sequence;
	}


	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Stage sequence) {
		this.sequence = sequence;
	}
}
