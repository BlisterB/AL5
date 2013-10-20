package Engine;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private GameScreen gamescreen = new GameScreen();
	private Engine engine = new Engine();
	
	public Fenetre(){
		//Propriétés
	    this.setTitle("Ruzzle Bobble");
	    this.setSize(900, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    this.setResizable(false);
	    this.setVisible(true);
	    
	    //Conteneurs
	    this.setContentPane(gamescreen);
	    
	    gamescreen.setSequence(engine.getSequence());
	    
	    gamescreen.repaint();
	}
}
