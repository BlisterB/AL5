package com.mklr.graphics.sprite;

import java.awt.Image;
import java.awt.Rectangle;

import com.mklr.graphics.engine.Launcher;

public class Bub extends Sprite implements Runnable{
	public Bub(){
		rect = new Rectangle(550, 330, 100, 100);
		
		//Chargement des images
		this.sprite_list = new Image[4];
		sprite_list[0] = openImage(Launcher.PATH + "img/Bub/Bub-standing_pose1.png");
		sprite_list[1] = openImage(Launcher.PATH + "img/Bub/Bub-standing_pose2.png");
		sprite_list[2] = openImage(Launcher.PATH + "img/Bub/Bub-standing_pose3.png");
		sprite_list[3] = openImage(Launcher.PATH + "img/Bub/Bub-standing_pose4.png");
		
		//Image par defaut
		this.image = sprite_list[0];
	}
	
	public void animation(){
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run(){
		while(true)
		for(int i = 0; i < 3; i++){
			image = sprite_list[i];
		      try {
		          Thread.sleep(180);
		        } catch (InterruptedException e) {
		          e.printStackTrace();
		        }
		}
	}
}
