package com.mklr.graphics.sprite;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite{
	protected Image image;//L'image actuelle du sprite
	protected Rectangle rect;//Le rectangle symbolisant l'image dans le JPanel
	protected Image[] sprite_list;//Le tableau des images du sprite
	boolean animated;
	int animation;
	boolean inMove;

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
	
	public void move(final int x, final int y, final int periode){
		inMove = true;
		new Thread(new Runnable(){
			public void run(){
				while(rect.x != x || rect.y != y){
					int tempX = rect.x, tempY = rect.y;
					if(rect.x > x) tempX--;
					else if(rect.x < x)	tempX++;
					if(rect.y > y) tempY--;
					else if(rect.y < y)	tempY++;

					sleep(periode);
					rect = new Rectangle(tempX, tempY, (int)rect.getWidth(), (int)rect.getHeight());
				}
				System.out.println("Fin du mouvement de " + this);
			}
		}).start();
		inMove = false;
	}
	
	public void sleep(int temps){
		try{
			Thread.sleep(temps);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}	
	}
	
	public void onClick(){
		System.out.println("Clic sur " + this);
	}
	
	public void onMousePressedWay(){
		
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
	/**
	 * @return the sprite_list
	 */
	public Image[] getSprite_list() {
		return sprite_list;
	}
	/**
	 * @param sprite_list the sprite_list to set
	 */
	public void setSprite_list(Image[] sprite_list) {
		this.sprite_list = sprite_list;
	}
	/**
	 * @return the animated
	 */
	public boolean isAnimated() {
		return animated;
	}
	/**
	 * @param animated the animated to set
	 */
	public void setAnimated(boolean animated) {
		this.animated = animated;
	}
	/**
	 * @return the animation
	 */
	public int getAnimation() {
		return animation;
	}
	/**
	 * @param animation the animation to set
	 */
	public void setAnimation(int animation) {
		this.animation = animation;
	}
	/**
	 * @return the inMove
	 */
	public boolean isInMove() {
		return inMove;
	}
	
}
