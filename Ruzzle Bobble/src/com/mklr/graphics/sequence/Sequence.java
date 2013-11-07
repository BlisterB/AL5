package com.mklr.graphics.sequence;

import java.util.LinkedList;

import com.mklr.graphics.sprite.Sprite;

/**La sequence est l'objet definissant la sequence de jeu : ecran de titre, ecran de jeu etc.
 * Elle contient la liste des sprites a l'ecran et gere leur apparence en fonction du contexte
 * @author Mehdi
 *
 */
public class Sequence {
	protected LinkedList <Sprite>liste_sprite;
	protected Sprite background;

	/**
	 * @return the liste_sprite
	 */
	public LinkedList <Sprite> getListe_sprite() {
		return liste_sprite;
	}


	/**
	 * @param liste_sprite the liste_sprite to set
	 */
	public void setListe_sprite(LinkedList <Sprite> liste_sprite) {
		this.liste_sprite = liste_sprite;
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
