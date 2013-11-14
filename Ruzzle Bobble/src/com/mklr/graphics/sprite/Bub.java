package com.mklr.graphics.sprite;

import java.awt.Image;

import com.mklr.graphics.engine.Engine;

public class Bub extends Sprite implements Runnable{
	public static int STANDING = 1, FEAR = 2, CRY = 3;
	
	public Bub(int x, int y){
		super(x, y, 100, 100);
		
		//Chargement des images
		this.sprite_list = new Image[17];
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
					sleep(180);
				}
				for(int i = 1; i > 0 && animation == STANDING; i--){
					image = sprite_list[i];
					sleep(180);
				}
			}
			while(animated && animation == FEAR){
				for(int i = 4; i < 9 && animation == FEAR; i++){
					image = sprite_list[i];
				    sleep(180);
				}
				for(int i = 7; i >3 && animation == FEAR; i--){
					image = sprite_list[i];
				    sleep(180);
				}
			}
			if(animated && animation == CRY){
				//Retient ses larme (on repete l'animation deux fois)
				image = sprite_list[9];
				sleep(180);
				image = sprite_list[10];
				sleep(180);
				for(int i = 9; i < 14 && animation == CRY; i++){
					image = sprite_list[i];
					sleep(180);
				}
				//Pleure indefiniment
				while(animated && animation == CRY)
					for(int i = 14; i < 17 && animation == CRY; i++){
						image = sprite_list[i];
						sleep(180);
					}
			}
		}
	}
}
