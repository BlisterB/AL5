package com.mklr.graphics.engine;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.mklr.graphics.sprite.LetterSprite;
import com.mklr.graphics.sprite.Sprite;
import com.mklr.graphics.stage.GameStage;
import com.mklr.graphics.stage.Stage;

/**Le GameScreen est l'ecran du jeu.
 * C'est un Canvas affichant les differents sprites de la Sequence en cours.
 * @author Mehdi
 *
 */
public class GameScreen extends Canvas implements MouseMotionListener, MouseListener {
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
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g ;
		
		Sprite sprite;//Tampon
		if(stage != null){
			//Raffraichissement du stage
			stage.update();
			
			//Dessin du background
			g.drawImage(stage.getBackground().getImage(), 0, 0, this);
			
			//Dessin des sprite
			if(stage.getSpriteList() != null){
				for(int i = 0; i < stage.getSpriteList().size(); i++){
					sprite = stage.getSpriteList().get(i);
					//On affiche le sprite seulement si son attribut displayble le permet
					if(sprite.getDisplayable()){
						//On distingue le cas ou on doit gérer la transparence ou non (pour economiser de la memoire)
						if(sprite.getAlphaComposite() == null)
							g.drawImage(sprite.getImage(), sprite.getRect().x, sprite.getRect().y, this);
						else{
							g2.setComposite(sprite.getAlphaComposite());
							g2.drawImage(sprite.getImage(), sprite.getRect().x, sprite.getRect().y, this);
							g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
						}
					}
				}
			}
			
			//Affichage des derniers mots en GameStage :
			if(stage instanceof GameStage){
				String mot;
				for(int i = 0; i < 6 && i < ((GameStage)stage).getLastWords().size(); i++){
					mot = ((GameStage)stage).getLastWords().get(i);
					g2.setColor(Color.WHITE);
					g2.setFont(new Font("Comic sans ms", Font.BOLD, 40));
					g2.drawString(mot, 580, 120 + i*40);
				}
			}
		}
	}

	public void drawNumber(int n, int x, int y){
		
	}
	
	//Gestion de la souris
	
    public void mousePressed(MouseEvent e) {
    }

	public void mouseReleased(MouseEvent e) {
		if(stage instanceof GameStage){
			((GameStage)stage).sendCurrentWord();
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
			        				if(((LetterSprite)s).addLettertoCurrentWorld((LetterSprite)lastSpritePointed))
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

	/**
	 * @return the lastSpritePointed
	 */
	public Sprite getLastSpritePointed() {
		return lastSpritePointed;
	}

	/**
	 * @param lastSpritePointed the lastSpritePointed to set
	 */
	public void setLastSpritePointed(Sprite lastSpritePointed) {
		this.lastSpritePointed = lastSpritePointed;
	}
	
	
}
