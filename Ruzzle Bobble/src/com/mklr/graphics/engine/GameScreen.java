package com.mklr.graphics.engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.mklr.graphics.sprite.LetterSprite;
import com.mklr.graphics.sprite.Sprite;
import com.mklr.graphics.stage.GameStage;
import com.mklr.graphics.stage.Stage;

/**Le GameScreen est l'ecran du jeu.
 * C'est un Canvas affichant les differents sprites de la Sequence en cours.
 * @author Mehdi
 *
 */
public class GameScreen extends JPanel implements MouseMotionListener, MouseListener {
	private Stage stage;
	private Sprite lastSpritePointed;
	
	public GameScreen(){
		this.setSize(800, 450);
		this.setPreferredSize(new Dimension(800, 450));
		this.setMaximumSize(new Dimension(800, 450));
		this.setMinimumSize(new Dimension(800, 450));
		addMouseListener(this);
		addMouseMotionListener(this);
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
		}
	}

    public void mousePressed(MouseEvent e) {
    }

	public void mouseReleased(MouseEvent e) {
		if(stage instanceof GameStage){
			if(((GameStage) stage).getCurrentWorld() != "")
				System.out.println(((GameStage) stage).getCurrentWorld());
			((GameStage) stage).setCurrentWorld("");
			((GameStage) stage).flushSelectedLetter();
			lastSpritePointed = null;
		}
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
     
 	//Controle du mouvement de la souris
     //Clic continue (avec mouvement) de la souris (ne concerne que le clic gauche)
     public void mouseDragged(final MouseEvent e) {
    	 new Thread(new Runnable(){
			public void run(){
		        if(stage.getSpriteList() != null){
		        	Sprite s;
			        for(int i = 0; i < stage.getSpriteList().size(); i++){
			        	s = stage.getSpriteList().get(i);
			        	if(s.isInCollision(e.getX(), e.getY())){
			        		s = stage.getSpriteList().get(i);
			        		if(s instanceof LetterSprite){
			        			if(lastSpritePointed instanceof LetterSprite || lastSpritePointed == null)
			        				((LetterSprite)s).onMousePressedWay((LetterSprite)lastSpritePointed);
			        				lastSpritePointed = s;
			        		}
			        	}
			        }
		        }
			}
		}).start();
     }

     public void mouseMoved(MouseEvent e) {
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
