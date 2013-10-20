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
			image = ImageIO.read(new File("img/template.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
