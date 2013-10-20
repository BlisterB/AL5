package Engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import Sequence.Sequence;

/**Le GameScreen est l'écran du jeu.
 * C'est un Canvas affichant les différents sprites de la Sequence en cours.
 * @author Mehdi
 *
 */
public class GameScreen extends Canvas{
	private Sequence sequence;
	
	public GameScreen(){
		this.setSize(800, 450);
		this.setBackground(Color.orange);
	}
	
	/**La methode paintComponent recupere la liste de sprite de la sequence en cours et les affiches successivement**/
	void paintComponent(Graphics g){
		if(sequence != null){
			//Dessin du background
			
			//Dessin des sprite
			
			//Dessin de l'interface
		}
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
