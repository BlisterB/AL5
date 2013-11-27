package com.mklr.graphics.stage;

import java.awt.AlphaComposite;
import java.util.LinkedList;

import com.mklr.graphics.engine.Engine;
import com.mklr.graphics.engine.MusicPlayer;
import com.mklr.graphics.sprite.Sprite;

/**La sequence est l'objet definissant la sequence de jeu : ecran de titre, ecran de jeu etc.
 * Elle contient la liste des sprites a l'ecran et gere leur apparence en fonction du contexte
 * 
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
	
	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// CONSTRUCTEURS //////////////////////////
	 //////////////////////////////////////////////////////////////////	
	
	public Stage(Engine engine){
		this.engine = engine;
	}
	
	   //////////////////////////////////////////////////////////////////
	  ////////////////////////////// METHODES //////////////////////////
	 //////////////////////////////////////////////////////////////////	
	
	/** Fonction appelée avant chaque raffraichissement de l'image, a redefinir pour mettre a jour des informations a l'ecran */
	public void update(){};
	
	/** Fonction liées aux interaction avec les autres methode, elle prend en parametre un entier representant une action particuliere 
	 * pour l'objet (clic sur un bouton valider, fin d'un timer etc) et permet de definir des actions associées */
	public void interaction(int function){}
	
	/** Ferme les Threads associé au stage (musique, sprite etc.) afin d'être éliminé par le garbage collector */ 
	public void close(){
		//Parcours de la liste de sprite et fermeture de tous les sprites associés au stageif(stage.getSpriteList() != null){
		if(sprite_list != null){
			for(int i = 0; i < sprite_list.size(); i++){
				sprite_list.get(i).close();
			}
		}
		
		//Fermeture du lecteur de musique
		if(musicPlayer != null)
			musicPlayer.close();
	}
	
	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// ACCESSEURS MODIFIEURS///////////////////
	 //////////////////////////////////////////////////////////////////
	
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
