package Engine;

import Sequence.GameTitle;
import Sequence.Sequence;

/**La classe Engine représente le moteur de jeu
 * C'est elle qui charge les séquences du jeu et commande le raffraichissement du GameScreen.
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
