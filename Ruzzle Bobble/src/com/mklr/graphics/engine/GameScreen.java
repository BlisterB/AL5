package com.mklr.graphics.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.InterfaceSprite;
import com.mklr.graphics.sprite.LetterSprite;
import com.mklr.graphics.sprite.Sprite;
import com.mklr.graphics.stage.GameStage;
import com.mklr.graphics.stage.Stage;

/**Le GameScreen est l'ecran du jeu.
 * C'est un Canvas affichant les differents sprites de la Sequence en cours.
 * @author Mehdi
 *
 */
public class GameScreen extends JPanel implements MouseListener{
	private Stage stage;
	
	public GameScreen(){
		this.setSize(800, 450);
		this.setPreferredSize(new Dimension(800, 450));
		this.setMaximumSize(new Dimension(800, 450));
		this.setMinimumSize(new Dimension(800, 450));
		addMouseListener(this);
	}
	   //////////////////////////////////////////////////////////////////
	  ////////////////////////////// METHODES //////////////////////////
	 //////////////////////////////////////////////////////////////////	
	
	/**La methode paintComponent recupere la liste de sprite de la sequence en cours et les affiches successivement**/
	public void paintComponent(Graphics g){
		Sprite sprite;//Tampon
		if(stage != null){
			//Dessin du background
			g.drawImage(stage.getBackground().getImage(), 0, 0, this);
			//Dessin des sprite
			if(stage.getSpriteList() != null){
				for(int i = 0; i < stage.getSpriteList().size(); i++){
					sprite = stage.getSpriteList().get(i);
					g.drawImage(sprite.getImage(), sprite.getRect().x, sprite.getRect().y, this);
				}
			}
			//Agencement des lettres
			if(stage instanceof GameStage){
				//Ligne - Colonne
				//Ligne 1
				g.drawRect(104, 60, 40, 40);
				g.drawRect(204, 60, 40, 40);
				g.drawRect(304, 60, 40, 40);
				g.drawRect(154, 30, 40, 40);
				g.drawRect(254, 30, 40, 40);
				//Ligne 2
				g.drawRect(104, 110, 40, 40);
				g.drawRect(204, 110, 40, 40);
				g.drawRect(304, 110, 40, 40);
				g.drawRect(54, 140, 40, 40);
				g.drawRect(154, 140, 40, 40);
				g.drawRect(254, 140, 40, 40);
				g.drawRect(354, 140, 40, 40);
				//Ligne 3
				g.drawRect(104, 222, 40, 40);
				g.drawRect(204, 222, 40, 40);
				g.drawRect(304, 222, 40, 40);
				g.drawRect(54, 192, 40, 40);
				g.drawRect(154, 192, 40, 40);
				g.drawRect(254, 192, 40, 40);
				g.drawRect(354, 192, 40, 40);
				//Ligne 4
				g.drawRect(104, 272, 40, 40);
				g.drawRect(204, 272, 40, 40);
				g.drawRect(304, 272, 40, 40);
				g.drawRect(154, 305, 40, 40);
				g.drawRect(254, 305, 40, 40);
			}
		}
	}

    public void mousePressed(MouseEvent e) {
        //saySomething("Mouse pressed; # of clicks: " + e.getClickCount(), e);
     }

     public void mouseReleased(MouseEvent e) {
       //saySomething("Mouse released; # of clicks: " + e.getClickCount(), e);
     }

     public void mouseEntered(MouseEvent e) {
        //saySomething("Mouse entered", e);
     }

     public void mouseExited(MouseEvent e) {
       // saySomething("Mouse exited", e);
     }

     public void mouseClicked(MouseEvent e) {
        //saySomething("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);
        if(stage.getSpriteList() != null){
        	Sprite s;
	        for(int i = 0; i < stage.getSpriteList().size(); i++){
	        	s = stage.getSpriteList().get(i);
	        	if(s.isInCollision(e.getX(), e.getY())){
	        		s = stage.getSpriteList().get(i);
	        		//System.out.println("VOUS AVEZ CLIQUER SUR L OBJET DE REF" + stage.getSpriteList().get(i));
	        		//System.out.println("Souris : X "+ e.getX() + "Y " + e.getY() + "\nBub : " + stage.getSpriteList().get(i).getRect().toString());
	        		if(s instanceof Sprite){
	        			s.onClick();
	        		}
	        	}
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
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
