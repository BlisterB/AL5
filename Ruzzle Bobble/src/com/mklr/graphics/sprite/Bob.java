package com.mklr.graphics.sprite;

import java.awt.Image;
import java.awt.Rectangle;

import com.mklr.graphics.engine.Launcher;

public class Bob extends Sprite implements Runnable{
	public static int BUBBLE_LEFT = 1, BUBBLE_RIGHT = 2;
	
	public Bob(int x, int y){
		super(x, y, 100, 100);
		
		//Chargement des images
		this.sprite_list = new Image[10];
		sprite_list[0] = openImage(Launcher.PATH + "img/bob/bob-buble-left1.png");
		sprite_list[1] = openImage(Launcher.PATH + "img/bob/bob-buble-left2.png");
		sprite_list[2] = openImage(Launcher.PATH + "img/bob/bob-buble-left3.png");
		sprite_list[3] = openImage(Launcher.PATH + "img/bob/bob-buble-left4.png");
		sprite_list[4] = openImage(Launcher.PATH + "img/bob/bob-buble-left5.png");
		sprite_list[5] = openImage(Launcher.PATH + "img/bob/bob-buble-right1.png");
		sprite_list[6] = openImage(Launcher.PATH + "img/bob/bob-buble-right2.png");
		sprite_list[7] = openImage(Launcher.PATH + "img/bob/bob-buble-right3.png");
		sprite_list[8] = openImage(Launcher.PATH + "img/bob/bob-buble-right4.png");
		sprite_list[9] = openImage(Launcher.PATH + "img/bob/bob-buble-right5.png");
	}
	
	public Bob(int x, int y,int animation){
		this(x,y);
		this.animated = true;
		this.animation = animation;
		animation();
	}
	
	public void animation(){
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run(){
		while(animated){
			while(animated && animation == BUBBLE_LEFT){
				for(int i = 0; i < 5 && animation == BUBBLE_LEFT; i++){
					image = sprite_list[i];
					sleep(160);
				}
				for(int i = 4; i > 0 && animation == BUBBLE_LEFT; i--){
					image = sprite_list[i];
					sleep(160);
				}
			}

			while(animated && animation == BUBBLE_RIGHT){
				for(int i = 5; i < 10 && animation == BUBBLE_RIGHT; i++){
					image = sprite_list[i];
					sleep(160);
				}
				for(int i = 9; i > 4 && animation == BUBBLE_RIGHT; i--){
					image = sprite_list[i];
					sleep(160);
				}
			}
		}
	}
}
