package com.mklr.graphics.sprite;

import java.awt.AlphaComposite;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mklr.graphics.engine.Engine;

/**
 * La classe Sprite instancie des objets associé à des instances de Stage
 * Une instance de Sprite contient toutes les informations necessaires à l'affichage du Sprite par une instance de GameScreen
 * @author Mehdi
 * 
 */
public class Sprite{
	protected Image image;//L'image actuelle du sprite
	protected Rectangle rect;//Le rectangle symbolisant l'image dans le JPanel
	protected Image[] sprite_list;//Le tableau des images du sprite
	protected AlphaComposite alphaComposite = null;//L'alpha composite associe (referrence a la transparence)
	int animation; //Nombre representant le numero de l'animation a jouer (declararees comme static final)
	
	protected boolean animated;//Defini si le sprite est en animation (false pour arreter l'animation et le thread associé)
	protected boolean displayable = true;//Defini si on affiche le sprite ou non
	protected boolean inMove;//Defini si le Sprite est en mouvement (false pour arreter un mouvement et le thread associé)
	protected boolean inTransparenceChangement;//Defini si le Sprite est dans une animation de changement de transparence (false pour arreter le thread associer)
	
	
	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// CONSTRUCTEURS //////////////////////////
	 //////////////////////////////////////////////////////////////////	
	
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
	
	//FONCTIONS D'AFFICHAGE
	
	/** Ouvre une image dont le chemin est en parametre et leve l'eventuelle exception */
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
	
	/** Modifie la transparence du Sprite, t est un float représentant la nouvelle transparence (1 : opaque, O : invisible)*/
	public void setTransparency(float t){
		alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, t);
	}
	
	/** Modifie progressivement la transparence du Sprite (periode est en ms), t est un float représentant la nouvelle transparence (1 : opaque, O : invisible)*/
	public void setTransparency(float t, final int periode){
		//On baisse progressivement la transparence
		new Thread(new Runnable(){
			public void run(){
				inTransparenceChangement = true;
				float transparency;
				if(alphaComposite == null)
					transparency = 1f;
				else
					transparency = alphaComposite.getAlpha();
				while(transparency > 0 && inTransparenceChangement){
					if(transparency > 0.1f)
						transparency -= 0.1;
					else
						transparency = 0;
					alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
					Engine.sleep(periode);
				}
				inTransparenceChangement = false;
			}
		}).start();
	}
	
	/** Permet de stopper le thread modifiant progressivement la transparence si celui ci existe */
	public void stopTransparencyThread(){
		inTransparenceChangement = false;
	}
	
	//FONCTIONS DE MOUVEMENT/COLLISION
	/** Dirige le Sprite vers les coordonnees 'x' et 'y' avec une periode de raffraichissement 'periode' (en ms) */
	public void move(final int x, final int y, final int periode){
		inMove = true;
		new Thread(new Runnable(){
			public void run(){
				while((rect.x != x || rect.y != y) && inMove){
					int tempX = rect.x, tempY = rect.y;
					if(rect.x > x) tempX--;
					else if(rect.x < x)	tempX++;
					if(rect.y > y) tempY--;
					else if(rect.y < y)	tempY++;

					Engine.sleep(periode);
					rect = new Rectangle(tempX, tempY, (int)rect.getWidth(), (int)rect.getHeight());
				}
				inMove = false;
			}
		}).start();
	}
	
	/** Stoppe un mouvement en cours (si il y en a un) et ferme indirectement le thread associé*/
	public void stopMove(){
		inMove = false;
	}
	
	/** Stope l'animation du Sprite (si il y en a une) et ferme indirectement le thread associé*/
	public void stopAnimation(){
		animated = false;
	}
	
	/** Teste la collision entre le sprite courant et celui passé en parametre **/
	public boolean isInCollision(Sprite s){
		return this.rect.intersects(s.rect);
	}
	
	/** Teste la collision entre le sprite courant et le point dont les coordonnees sont entrées en parametre*/
	public boolean isInCollision(int x, int y){
		Rectangle rect = new Rectangle(x, y, 1, 1);
		return this.rect.intersects(rect);
	}
	
	
	//FONCTION DE GESTION DE LA SOURIS
	
	/** Definie l'action effectuee lors d'un clic sur le Sprite*/
	public void onClick(){
	}
	
	/** Definie l'action effectuee lors d'un passage sur le Sprite de la souris ayant un bouton enfoncé */
	public void onMousePressedWay(){
	}	

	//FONCTION DE FERMETURE DU SPRITE
	
	/** Fonction fermant indirectement tous les thread associé au sprite et permettant sa suppression par la garbage collector */
	public void close(){
		stopMove();
		stopAnimation();
		stopTransparencyThread();
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
		this.animated = true;
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
