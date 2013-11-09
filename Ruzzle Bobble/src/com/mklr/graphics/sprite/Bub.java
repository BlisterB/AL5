package com.mklr.graphics.sprite;

import java.awt.Image;
import java.awt.Rectangle;

public class Bub extends Sprite {
	
	
	public Bub(){
		rect = new Rectangle(550, 330, 100, 100);
		
		//Chargement des images
		this.sprite_list = new Image[4];
		sprite_list[0] = openImage("img/Bub/Bub-standing_pose1.png");
		sprite_list[1] = openImage("img/Bub/Bub-standing_pose2.png");
		sprite_list[2] = openImage("img/Bub/Bub-standing_pose3.png");
		sprite_list[3] = openImage("img/Bub/Bub-standing_pose4.png");
		
		//Image par defaut
		this.image = sprite_list[0];
	}
}
