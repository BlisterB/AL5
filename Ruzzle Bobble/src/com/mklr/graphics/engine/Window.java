package com.mklr.graphics.engine;

import javax.swing.JFrame;

public class Window extends JFrame {
	private GameScreen gamescreen = new GameScreen();
	private Engine engine = new Engine();
	private MenuBar menuBar = new MenuBar();
	
	public Window(){
		//Proprietes
	    this.setTitle("Ruzzle Bobble");
	    this.setSize(1000, 600);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Barre de menu
	    this.setJMenuBar(menuBar);
	    
	    //Conteneurs
	    this.setContentPane(gamescreen);

	    this.setVisible(true);
	}
}
