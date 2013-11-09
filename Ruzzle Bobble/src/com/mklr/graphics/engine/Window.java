package com.mklr.graphics.engine;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	private GameScreen gamescreen = new GameScreen();
	private Engine engine = new Engine();
	
	public Window(){
		//Proprietes
	    this.setTitle("Ruzzle Bobble");
	    this.setSize(900, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    this.setResizable(false);
	    this.setVisible(true);
	    
	    //Conteneurs
	    this.setContentPane(gamescreen);
	    
	    gamescreen.setStage(engine.getSequence());
	    
	    gamescreen.repaint();
	}
}
