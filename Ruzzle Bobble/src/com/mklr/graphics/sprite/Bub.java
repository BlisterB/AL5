package com.mklr.graphics.sprite;

import java.awt.Image;

import com.mklr.graphics.engine.Engine;

public class Bub extends Sprite implements Runnable{
	public static int STANDING = 1, FEAR = 2, CRY = 3, HAPPY = 4, JUMPING = 5, FEAR2 = 6;
	
	public Bub(int x, int y){
		super(x, y, 100, 100);
		
		//Chargement des images
		this.sprite_list = new Image[30];
		sprite_list[0] = openImage(Engine.PATH + "img/bub/bub-standing1.png");
		sprite_list[1] = openImage(Engine.PATH + "img/bub/bub-standing2.png");
		sprite_list[2] = openImage(Engine.PATH + "img/bub/bub-standing3.png");
		sprite_list[3] = openImage(Engine.PATH + "img/bub/bub-standing4.png");
		sprite_list[4] = openImage(Engine.PATH + "img/bub/bub-fear1.png");
		sprite_list[5] = openImage(Engine.PATH + "img/bub/bub-fear2.png");
		sprite_list[6] = openImage(Engine.PATH + "img/bub/bub-fear4.png");
		sprite_list[7] = openImage(Engine.PATH + "img/bub/bub-fear5.png");
		sprite_list[8] = openImage(Engine.PATH + "img/bub/bub-fear6.png");
		sprite_list[9] = openImage(Engine.PATH + "img/bub/bub-cry1.png");
		sprite_list[10] = openImage(Engine.PATH + "img/bub/bub-cry2.png");
		sprite_list[11] = openImage(Engine.PATH + "img/bub/bub-cry3.png");
		sprite_list[12] = openImage(Engine.PATH + "img/bub/bub-cry4.png");
		sprite_list[13] = openImage(Engine.PATH + "img/bub/bub-cry5.png");
		sprite_list[14] = openImage(Engine.PATH + "img/bub/bub-cry6.png");
		sprite_list[15] = openImage(Engine.PATH + "img/bub/bub-cry7.png");
		sprite_list[16] = openImage(Engine.PATH + "img/bub/bub-cry8.png");
		sprite_list[17] = openImage(Engine.PATH + "img/bub/bub-happy1.png");
		sprite_list[18] = openImage(Engine.PATH + "img/bub/bub-happy2.png");
		sprite_list[19] = openImage(Engine.PATH + "img/bub/bub-happy3.png");
		sprite_list[20] = openImage(Engine.PATH + "img/bub/bub-happy4.png");
		sprite_list[21] = openImage(Engine.PATH + "img/bub/bub-happy5.png");
		sprite_list[22] = openImage(Engine.PATH + "img/bub/bub-happy6.png");
		sprite_list[23] = openImage(Engine.PATH + "img/bub/bub-happy7.png");
		sprite_list[24] = openImage(Engine.PATH + "img/bub/bub-happy8.png");
		sprite_list[25] = openImage(Engine.PATH + "img/bub/bub-happy9.png");
		sprite_list[26] = openImage(Engine.PATH + "img/bub/bub-happy10.png");
		sprite_list[27] = openImage(Engine.PATH + "img/bub/bub-happy11.png");
		sprite_list[28] = openImage(Engine.PATH + "img/bub/bub-happy12.png");
		sprite_list[29] = openImage(Engine.PATH + "img/bub/bub-happy13.png");
	}
	
	public Bub(int x, int y,int animation){
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
		System.out.println("Bub !");
	}
	
	public void run(){
		while(animated){
			while(animated && animation == STANDING){
				for(int i = 0; i < 3 && animation == STANDING; i++){
					image = sprite_list[i];
					Engine.sleep(180);
				}
				for(int i = 1; i > 0 && animation == STANDING; i--){
					image = sprite_list[i];
					Engine.sleep(180);
				}
			}
			while(animated && animation == FEAR){
				for(int i = 4; i < 9 && animation == FEAR; i++){
					image = sprite_list[i];
					Engine.sleep(180);
				}
				for(int i = 7; i >3 && animation == FEAR; i--){
					image = sprite_list[i];
					Engine.sleep(180);
				}
			}
			if(animated && animation == CRY){
				//Retient ses larme (on repete l'animation deux fois)
				image = sprite_list[9];
				Engine.sleep(180);
				image = sprite_list[10];
				Engine.sleep(180);
				for(int i = 9; i < 14 && animation == CRY; i++){
					image = sprite_list[i];
					Engine.sleep(180);
				}
				//Pleure indefiniment
				while(animated && animation == CRY)
					for(int i = 14; i < 17 && animation == CRY; i++){
						image = sprite_list[i];
						Engine.sleep(180);
					}
			}
			if(animated && animation == HAPPY){
				//Un petit rire 2 fois
				for(int i = 0; i < 3; i++){
					for(int j = 17; j < 19; j++){
						image = sprite_list[j];
						Engine.sleep(180);
					}
				}
				//On revient sur l'animation de base
				animation = STANDING;
			}
			if(animated && animation == JUMPING){
				for(int i = 17; i < 30; i++){
					image = sprite_list[i];
					Engine.sleep(90);
				}
				//On revient sur l'animation de base
				animation = STANDING;
			}
			if(animated && animation == FEAR2){
				for(int i = 4; i < 9 && animation == FEAR2; i++){
					image = sprite_list[i];
					Engine.sleep(180);
				}
				for(int i = 7; i >3 && animation == FEAR2; i--){
					image = sprite_list[i];
					Engine.sleep(180);
				}
				//On revient sur l'animation de base
				animation = STANDING;
			}
		}
	}
}
