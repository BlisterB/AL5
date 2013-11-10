package com.mklr.graphics.engine;

import javax.swing.JFrame;

public class Window extends JFrame {
	private GameScreen gamescreen;
	private Engine engine;
	private MenuBar menubar;
	
	public Window(){
		//Proprietes
	    this.setTitle("Ruzzle Bobble");
	    this.setSize(800, 500);//A MODIFIER
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Initialisation des composants
	    gamescreen = new GameScreen();
	    engine = new Engine(gamescreen);
	    menubar = new MenuBar(engine);
	    
	    //Barre de menu
	    this.setJMenuBar(menubar);
	    
	    //Conteneurs
	    this.setContentPane(gamescreen);
	    
	    //Affichage de la fenetre
	    this.setVisible(true);
	}

	/**
	 * @return the gamescreen
	 */
	public GameScreen getGamescreen() {
		return gamescreen;
	}

	/**
	 * @param gamescreen the gamescreen to set
	 */
	public void setGamescreen(GameScreen gamescreen) {
		this.gamescreen = gamescreen;
	}

	/**
	 * @return the engine
	 */
	public Engine getEngine() {
		return engine;
	}

	/**
	 * @param engine the engine to set
	 */
	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	/**
	 * @return the menubar
	 */
	public MenuBar getMenubar() {
		return menubar;
	}

	/**
	 * @param menubar the menubar to set
	 */
	public void setMenubar(MenuBar menubar) {
		this.menubar = menubar;
	}
	
	
}
