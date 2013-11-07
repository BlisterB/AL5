package com.mklr.graphics.engine;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.mklr.graphics.sequence.Sequence;

/**Le GameScreen est l'ecran du jeu.
 * C'est un Canvas affichant les differents sprites de la Sequence en cours.
 * @author Mehdi
 *
 */
public class GameScreen extends JPanel{
	private Sequence sequence;
	
	public GameScreen(){
		this.setSize(800, 450);
		this.setBackground(Color.orange);
	}
	
	/**La methode paintComponent recupere la liste de sprite de la sequence en cours et les affiches successivement**/
	public void paintComponent(Graphics g){
		if(sequence != null){
			//Dessin du background
			g.drawImage(sequence.getBackground().getImage(), 0, 0, this);
			//Dessin des sprite
			
			//Dessin de l'interface
		}
		else{
			System.out.println("Pas de sequence chargee");
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
