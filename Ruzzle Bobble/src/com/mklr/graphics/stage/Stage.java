package com.mklr.graphics.stage;

import java.util.LinkedList;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.MusicPlayer;
import com.mklr.graphics.sprite.Sprite;

/**La sequence est l'objet definissant la sequence de jeu : ecran de titre, ecran de jeu etc.
 * Elle contient la liste des sprites a l'ecran et gere leur apparence en fonction du contexte
 * @author Mehdi
 *
 */
public class Stage {
	protected Engine engine;
	protected LinkedList <Sprite> sprite_list = new LinkedList <Sprite>();
	protected Sprite background;
	protected MusicPlayer musicPlayer;
	public static final int VALIDATE = 1;
	public static final int CANCEL = 2;
	public static final int TIMMER_END = 2;
	
	public Stage(Engine engine){
		this.engine = engine;
	}
	
	public void sleep(int temps){
		try{
			Thread.sleep(temps);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}	
	}
	
	public void update(){};
	
	public void interaction(int function){
		
	}
	
	public void close(){
		if(musicPlayer != null)
			musicPlayer.stopPlaying();
	}
	
	/**
	 * @return the liste_sprite
	 */
	public LinkedList <Sprite> getSpriteList() {
		return sprite_list;
	}


	/**
	 * @param liste_sprite the liste_sprite to set
	 */
	public void setSpriteList(LinkedList <Sprite> liste_sprite) {
		this.sprite_list = liste_sprite;
	}


	/**
	 * @return the background
	 */
	public Sprite getBackground() {
		return background;
	}


	/**
	 * @param background the background to set
	 */
	public void setBackground(Sprite background) {
		this.background = background;
	}
}
