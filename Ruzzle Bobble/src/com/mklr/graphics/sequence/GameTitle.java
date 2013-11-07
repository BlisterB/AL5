package com.mklr.graphics.sequence;

import com.mklr.graphics.sprite.Sprite;
import com.mklr.graphics.sequence.Sequence;

/**Sequence correspondant a la phase d'ecran de titre**/
public class GameTitle extends Sequence{
	public GameTitle(){
		//Generation du background
        //Switch to /path/to/template
		this.background = new Sprite("img/template.png");
	}
}
