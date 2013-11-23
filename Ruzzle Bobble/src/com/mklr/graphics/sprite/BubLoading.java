package com.mklr.graphics.sprite;

import java.awt.Image;

import com.mklr.graphics.engine.Engine;

public class BubLoading extends Sprite implements Runnable{
	public static final int LOADING = 0;

	public BubLoading(int x, int y){
		super(x, y, 100, 100);
		
		//Chargement des images
		this.sprite_list = new Image[8];
		sprite_list[0] = openImage(Engine.PATH + "img/bub_loading/bub_loading1.png");
		sprite_list[1] = openImage(Engine.PATH + "img/bub_loading/bub_loading2.png");
		sprite_list[2] = openImage(Engine.PATH + "img/bub_loading/bub_loading3.png");
		sprite_list[3] = openImage(Engine.PATH + "img/bub_loading/bub_loading4.png");
		sprite_list[4] = openImage(Engine.PATH + "img/bub_loading/bub_loading5.png");
		sprite_list[5] = openImage(Engine.PATH + "img/bub_loading/bub_loading6.png");
		sprite_list[6] = openImage(Engine.PATH + "img/bub_loading/bub_loading7.png");
		sprite_list[7] = openImage(Engine.PATH + "img/bub_loading/bub_loading8.png");	
	}
	
	public BubLoading(int x, int y,int animation){
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
	
	public void run(){
		while(animated){
			while(animated && animation == LOADING){
				for(int i = 0; i < 8 && animation == LOADING; i++){
					image = sprite_list[i];
					Engine.sleep(80);
				}
			}
		}
	}
}
