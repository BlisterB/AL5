package Sprite;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	Image image;
	int posX;
	int posY;
	int zoom;
	
	public Sprite(String chemin){
		try {
			this.image = ImageIO.read(new File(chemin));
			System.out.println("Ca a marché\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur dans l'ouverture de l'image");
		}
	}
	public Sprite(String chemin, int posX, int posY){
		this(chemin);
		this.posX = posX;
		this.posY = posY;
	}
	public Sprite(String chemin, int posX, int posY, int zoom){
		this(chemin, posX, posY);
		this.zoom = zoom;
	}
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
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}
	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}
	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	/**
	 * @return the zoom
	 */
	public int getZoom() {
		return zoom;
	}
	/**
	 * @param zoom the zoom to set
	 */
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}
}
