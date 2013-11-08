package com.mklr.graphics.sequence;

import java.util.LinkedList;

import com.mklr.graphics.sprite.Bub;
import com.mklr.graphics.sprite.Sprite;

public class GameStage extends Stage {
	private Bub bub = new Bub();
	
	public GameStage(){
		sprite_list = new LinkedList <Sprite>();
		
		background = new Sprite("img/template.png");
		sprite_list.add(bub);
	}
}