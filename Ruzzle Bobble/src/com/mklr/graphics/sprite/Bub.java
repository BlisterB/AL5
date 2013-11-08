package com.mklr.graphics.sprite;

import java.awt.Image;

public class Bub extends Sprite {
	public Bub(){
		this.animation = new Image[4];
		//Chargement des images
		animation[0] = openImage("img/Bub/Bub-standing_pose1.png");
		animation[1] = openImage("img/Bub/Bub-standing_pose2.png");
		animation[2] = openImage("img/Bub/Bub-standing_pose3.png");
		animation[3] = openImage("img/Bub/Bub-standing_pose4.png");
		
		//Image par defaut
		this.image = animation[1];
	}
}
