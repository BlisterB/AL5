package Engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**Le GameScreen est l'écran du jeu.
 * C'est un Canvas affichant les différents sprites de la Sequence en cours.
 * @author Mehdi
 *
 */
public class GameScreen extends Canvas{
	GameScreen(){
		this.setSize(800, 450);
		this.setBackground(Color.orange);
	}
	
	
	/**La methode paintComponent recupere la liste de sprite de la sequence en cours et les affiches successivement**/
	void paintComponent(Graphics g){
		//Dessin du background
		
		//Dessin des sprite
		
		//Dessin de l'interface
	}
}
