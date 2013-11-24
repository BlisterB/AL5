package com.mklr.graphics.sprite;

import java.awt.Image;

import com.mklr.graphics.engine.Engine;

public class Bob extends Sprite implements Runnable{
	public static int BUBBLE_LEFT = 1, BUBBLE_RIGHT = 2, STANDING = 3, STRANGE = 4, WHOA = 5, HAPPY = 6;
	
	public Bob(int x, int y){
		super(x, y, 100, 100);
		
		//Chargement des images
		this.sprite_list = new Image[24];
		sprite_list[0] = openImage(Engine.PATH + "img/bob/bob-buble-left1.png");
		sprite_list[1] = openImage(Engine.PATH + "img/bob/bob-buble-left2.png");
		sprite_list[2] = openImage(Engine.PATH + "img/bob/bob-buble-left3.png");
		sprite_list[3] = openImage(Engine.PATH + "img/bob/bob-buble-left4.png");
		sprite_list[4] = openImage(Engine.PATH + "img/bob/bob-buble-left5.png");
		sprite_list[5] = openImage(Engine.PATH + "img/bob/bob-buble-right1.png");
		sprite_list[6] = openImage(Engine.PATH + "img/bob/bob-buble-right2.png");
		sprite_list[7] = openImage(Engine.PATH + "img/bob/bob-buble-right3.png");
		sprite_list[8] = openImage(Engine.PATH + "img/bob/bob-buble-right4.png");
		sprite_list[9] = openImage(Engine.PATH + "img/bob/bob-buble-right5.png");
		sprite_list[10] = openImage(Engine.PATH + "img/bob/bob-standing1.png");
		sprite_list[11] = openImage(Engine.PATH + "img/bob/bob-standing2.png");
		sprite_list[12] = openImage(Engine.PATH + "img/bob/bob-standing3.png");
		sprite_list[13] = openImage(Engine.PATH + "img/bob/bob-standing4.png");
		sprite_list[14] = openImage(Engine.PATH + "img/bob/bob-standing5.png");
		sprite_list[15] = openImage(Engine.PATH + "img/bob/bob-standing6.png");
		sprite_list[16] = openImage(Engine.PATH + "img/bob/bob-strange1.png");
		sprite_list[17] = openImage(Engine.PATH + "img/bob/bob-strange2.png");
		sprite_list[18] = openImage(Engine.PATH + "img/bob/bob-strange3.png");
		sprite_list[19] = openImage(Engine.PATH + "img/bob/bob-woa1.png");
		sprite_list[20] = openImage(Engine.PATH + "img/bob/bob-woa2.png");
		sprite_list[21] = openImage(Engine.PATH + "img/bob/bob-woa3.png");
		sprite_list[22] = openImage(Engine.PATH + "img/bob/bob-happy1.png");
		sprite_list[23] = openImage(Engine.PATH + "img/bob/bob-happy2.png");
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
					Engine.sleep(160);
				}
				for(int i = 4; i > 0 && animation == BUBBLE_LEFT; i--){
					image = sprite_list[i];
					Engine.sleep(160);
				}
			}

			while(animated && animation == BUBBLE_RIGHT){
				for(int i = 5; i < 10 && animation == BUBBLE_RIGHT; i++){
					image = sprite_list[i];
					Engine.sleep(160);
				}
				for(int i = 9; i > 4 && animation == BUBBLE_RIGHT; i--){
					image = sprite_list[i];
					Engine.sleep(160);
				}
			}
			
			while(animated && animation == STANDING){
				image = sprite_list[10];
				for(int i = 0; i < 3 && animation == STANDING; i++){//On decoupe l'attente pour permettre de meilleurs transition entre les attente
					Engine.sleep(500);
					if(animation != STANDING)
						break;
				}
				//Bub cligne des yeux
				for(int j = 0; j < 2 && animation == STANDING; j++){
					image = sprite_list[15];
					Engine.sleep(180);
					image = sprite_list[10];
					Engine.sleep(180);
				}
				for(int i = 0; i < 2 && animation == STANDING; i++){//On decoupe l'attente pour permettre de meilleurs transition entre les attente
					Engine.sleep(500);
					if(animation != STANDING)
						break;
				}
				//Bub regarde le joueur
				for(int i = 11; i < 13 && animation == STANDING; i++){
					image = sprite_list[i];
					Engine.sleep(180);
				}
				for(int i = 0; i < 2 && animation == STANDING; i++){//On decoupe l'attente pour permettre de meilleurs transition entre les attente
					Engine.sleep(500);
					if(animation != STANDING)
						break;
				}
				for(int i = 12; i > 10 && animation == STANDING; i--){
					image = sprite_list[i];
					Engine.sleep(180);
				}
			}
			
			if(animated && animation == STRANGE){
				for(int i = 16; i < 19 && animation == STRANGE; i++){
					image = sprite_list[i];
					Engine.sleep(180);
				}
				animation = STANDING;
			}
			if(animated && animation == WHOA){
				for(int i = 19; i < 22 && animation == WHOA; i++){
					image = sprite_list[i];
					Engine.sleep(180);
				}
				Engine.sleep(500);
				animation = STANDING;
			}
			if(animated && animation == HAPPY){
				//Un petit rire 2 fois
				for(int i = 0; i < 3; i++){
					for(int j = 22; j < 24; j++){
						image = sprite_list[j];
						Engine.sleep(180);
					}
				}
				//On revient sur l'animation de base
				animation = STANDING;
			}
		}
	}
}
