package com.mklr.graphics.sequence;

import com.mklr.graphics.sprite.Sprite;
import com.mklr.graphics.sequence.Stage;

/**Sequence correspondant a la phase d'ecran de titre**/
public class GameTitle extends Stage{
	public GameTitle(){
		//Generation du background
        //Switch to /path/to/template
		this.background = new Sprite("img/template.png");
	}
}
