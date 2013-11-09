package com.mklr.graphics.engine;

import javax.swing.JFrame;

public class Window extends JFrame {
	private GameScreen gamescreen;
	private Engine engine;
	private MenuBar menubar;
	
	public Window(){
		//Proprietes
	    this.setTitle("Ruzzle Bobble");
	    this.setSize(1000, 600);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Initialisation des composants
	    menubar = new MenuBar();
	    gamescreen = new GameScreen();
	    engine = new Engine(gamescreen);
	    
	    //Barre de menu
	    this.setJMenuBar(menubar);
	    
	    //Conteneurs
	    this.setContentPane(gamescreen);
	    
	    //Affichage de la fenetre
	    this.setVisible(true);
	    
	    engine.refresh_gamescreen();
	}
}
