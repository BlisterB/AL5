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
}
