package com.mklr.graphics.engine;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.mklr.graphics.sequence.Stage;
import com.mklr.graphics.sprite.Sprite;

/**Le GameScreen est l'ecran du jeu.
 * C'est un Canvas affichant les differents sprites de la Sequence en cours.
 * @author Mehdi
 *
 */
public class GameScreen extends JPanel{
	private Stage stage;
	
	public GameScreen(){
		this.setSize(800, 450);
		this.setBackground(Color.orange);
	}
	
	/**La methode paintComponent recupere la liste de sprite de la sequence en cours et les affiches successivement**/
	public void paintComponent(Graphics g){
		Sprite sprite;//Tampon
		if(stage != null){
			//Dessin du background
			g.drawImage(stage.getBackground().getImage(), 0, 0, this);
			//Dessin des sprite
			for(int i = 0; i < stage.getSpriteList().size(); i++){
				sprite = stage.getSpriteList().get(i);
				g.drawImage(sprite.getImage(), sprite.getPosX(), sprite.getPosY(), this);
			}
			//Dessin de l'interface
		}
		else{
			System.out.println("Pas de sequence chargee");
		}
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
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
