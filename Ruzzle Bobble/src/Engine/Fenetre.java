package Engine;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private GameScreen gamescreen = new GameScreen();
	
	public Fenetre(){
		//Propriétés
	    this.setTitle("Ruzzle Bobble");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
	    
	    //Conteneurs
		JPanel pannel = new JPanel();
	    this.setContentPane(pannel);
	    pannel.add(gamescreen);
	    this.pack();
	}
}
