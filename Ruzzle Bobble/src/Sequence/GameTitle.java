package Sequence;

import Sprite.Sprite;

/**Sequence correspondant � la phase d'�cran de titre**/
public class GameTitle extends Sequence{
	public GameTitle(){
		//Generation du background
		this.background = new Sprite("./img/template.png");
	}
}
