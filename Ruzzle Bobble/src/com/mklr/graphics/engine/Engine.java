package com.mklr.graphics.engine;

import com.mklr.graphics.sequence.GameTitle;
import com.mklr.graphics.sequence.Sequence;

/**La classe Engine represente le moteur de jeu
 * C'est elle qui charge les sequences du jeu et commande le raffraichissement du GameScreen.
 * @author Mehdi
 *
 */
public class Engine {
	private Sequence sequence;
	
	public Engine(){
		this.sequence = new GameTitle();
	}


	/**
	 * @return the sequence
	 */
	public Sequence getSequence() {
		return sequence;
	}


	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
}
