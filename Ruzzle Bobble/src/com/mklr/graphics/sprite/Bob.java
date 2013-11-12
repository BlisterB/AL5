package com.mklr.graphics.sprite;

import java.awt.Image;
import java.awt.Rectangle;

import com.mklr.graphics.engine.Launcher;

public class Bob extends Sprite implements Runnable{
	public static int BUBBLE_LEFT = 1, BUBBLE_RIGHT = 2, STANDING = 3;
	
	public Bob(int x, int y){
		super(x, y, 100, 100);
		
		//Chargement des images
		this.sprite_list = new Image[16];
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
		sprite_list[10] = openImage(Launcher.PATH + "img/bob/bob-standing1.png");
		sprite_list[11] = openImage(Launcher.PATH + "img/bob/bob-standing2.png");
		sprite_list[12] = openImage(Launcher.PATH + "img/bob/bob-standing3.png");
		sprite_list[13] = openImage(Launcher.PATH + "img/bob/bob-standing4.png");
		sprite_list[14] = openImage(Launcher.PATH + "img/bob/bob-standing5.png");
		sprite_list[15] = openImage(Launcher.PATH + "img/bob/bob-standing6.png");
	}
	
	public Bob(int x, int y,int animation){
		this(x,y);
		this.animated = true;
		this.animation = animation;
		animation();
	}
	
	public void animation(){
		this.animated = true;
		Thread t = new Thread(this);
		t.start();
	}
	
	public void onClick(){
		System.out.println("Bobobobob, Kupo !");
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
			
			while(animated && animation == STANDING){
				image = sprite_list[10];
				sleep(1500);
				//Bub cligne des yeux
				for(int j = 0; j < 2 && animation == STANDING; j++){
					image = sprite_list[15];
					sleep(180);
					image = sprite_list[10];
					sleep(180);
				}
				sleep(1000);
				//Bub regarde le joueur
				for(int i = 11; i < 13 && animation == STANDING; i++){
					image = sprite_list[i];
					sleep(180);
				}
				sleep(1000);
				for(int i = 12; i > 10 && animation == STANDING; i--){
					image = sprite_list[i];
					sleep(180);
				}
				/*
				//Bub bouge la queue
				for(int j = 0; j < 2; j++){
					for(int i = 13; i < 15 && animation == STANDING; i++){
						image = sprite_list[i];
						sleep(180);
					}
					for(int i = 14; i > 12 && animation == STANDING; i--){
						image = sprite_list[i];
						sleep(180);
					}
				}
				*/
			}
		}
	}
}
