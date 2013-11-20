package com.mklr.graphics.sprite;

import java.awt.AlphaComposite;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mklr.graphics.engine.Engine;

public class Sprite{
	protected Image image;//L'image actuelle du sprite
	protected Rectangle rect;//Le rectangle symbolisant l'image dans le JPanel
	protected Image[] sprite_list;//Le tableau des images du sprite
	boolean animated;
	boolean displayable = true;//Defini si on affiche le sprite ou non
	int animation;
	boolean inMove;
	protected AlphaComposite alphaComposite = null;

	public Sprite(){
		
	}
	public Sprite(int posX, int posY, int width, int height){
		this.rect = new Rectangle(posX, posY, width, height);
	}
	public Sprite(String chemin){
		this.image = openImage(chemin);
	}
	public Sprite(String chemin, int posX, int posY, int width, int height){
		this(chemin);
		this.rect = new Rectangle(posX, posY, width, height);
	}
	public Sprite(Image image, int posX, int posY, int width, int height){
		this.image = image;
		this.rect = new Rectangle(posX, posY, width, height);
	}

   //////////////////////////////////////////////////////////////////
  ////////////////////////////// METHODES //////////////////////////
 //////////////////////////////////////////////////////////////////	
	
	/** Ouvre une image et gere l'exception
	 * @return l'image
	 */
	public static Image openImage(String path){
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
	
	public static Image[] getNumberImageArray(){
		Image[] array = new Image[12];
		for(int i = 0; i < 10; i++){
			array[i] = openImage(Engine.PATH + "img/fonts/" + i + ".png");
		}
		array[11] = openImage(Engine.PATH + "img/fonts/colon.png");
		
		return array;
	}
	
	public void setTransparency(float t){
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, t);
	}
	
	public void setTransparency(float t, final int periode){
		//On baisse progressivement la transparence
		new Thread(new Runnable(){
			public void run(){
				float transparency;
				if(alphaComposite == null)
					transparency = 1f;
				else
					transparency = alphaComposite.getAlpha();
				while(transparency > 0){
					if(transparency > 0.1f)
						transparency -= 0.1;
					else
						transparency = 0;
					alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
					sleep(periode);
				}
			}
		}).start();
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
	/**
	 * @return the alphaComposite
	 */
	public AlphaComposite getAlphaComposite() {
		return alphaComposite;
	}
	/**
	 * @param alphaComposite the alphaComposite to set
	 */
	public void setAlphaComposite(AlphaComposite alphaComposite) {
		this.alphaComposite = alphaComposite;
	}
	/**
	 * @return the displayable
	 */
	public boolean isDisplayable() {
		return displayable;
	}
	/**
	 * @param displayable the displayable to set
	 */
	public void setDisplayable(boolean displayable) {
		this.displayable = displayable;
	}
	/**
	 * @return the displayable
	 */
	public boolean getDisplayable() {
		return displayable;
	}
}
