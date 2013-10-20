package Sequence;

import Sprite.Sprite;

/**Sequence correspondant à la phase d'écran de titre**/
public class GameTitle extends Sequence{
	public GameTitle(){
		//Generation du background
		this.background = new Sprite("./img/template.png");
	}
}
