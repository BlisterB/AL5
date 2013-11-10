package com.mklr.graphics.sprite;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	protected Image image;//L'image actuelle du sprite
	protected Rectangle rect;//Le rectangle symbolisant l'image dans le JPanel
	protected Image[] sprite_list;//Le tableau des images du sprite

	public Sprite(){
		
	}
	public Sprite(int posX, int posY, int width, int height){
		this.rect = new Rectangle(posX, posY, width, height);
	}
	public Sprite(String chemin){
		this.image = openImage(chemin);
		this.rect = new Rectangle(0, 0, 0, 0);
	}
	public Sprite(String chemin, int posX, int posY, int width, int height){
		this.image = openImage(chemin);
		this.rect = new Rectangle(posX, posY, width, height);
	}

   //////////////////////////////////////////////////////////////////
  ////////////////////////////// METHODES //////////////////////////
 //////////////////////////////////////////////////////////////////	
	
	/** Ouvre une image et gere l'exception
	 * @return l'image
	 */
	public Image openImage(String path){
		Image image;
		try {
			image = ImageIO.read(new File(path));
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur dans l'ouverture de " + path);
		}
		
		return null;
	}
	
	/**@return Test de collision entre les deux sprites **/
	
	public boolean isInCollision(Sprite s){
		return this.rect.intersects(s.rect);
	}
	public boolean isInCollision(int x, int y){
		Rectangle rect = new Rectangle(x, y, 1, 1);
		return this.rect.intersects(rect);
	}
	
   //////////////////////////////////////////////////////////////////
  ///////////////////////// ACCESSEURS MODIFIEURS///////////////////
 //////////////////////////////////////////////////////////////////
	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	/**
	 * @return the rect
	 */
	public Rectangle getRect() {
		return rect;
	}
	/**
	 * @param rect the rect to set
	 */
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	
}
