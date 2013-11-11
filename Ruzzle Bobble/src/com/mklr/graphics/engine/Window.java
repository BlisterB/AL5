package com.mklr.graphics.engine;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Window extends JFrame {
	private GameScreen gamescreen;
	private Engine engine;
	private MenuBar menubar;
	
	public Window(){
		//Proprietes
	    this.setTitle("Ruzzle Bobble");
	    //this.setSize(800, 500);//A MODIFIER
	    this.setIconImage(new ImageIcon(Launcher.PATH + "img/interface/icone.png").getImage());
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //Initialisation des composants
	    gamescreen = new GameScreen();
	    engine = new Engine(gamescreen);
	    menubar = new MenuBar(engine);
	    
	    //Barre de menu
	    this.setJMenuBar(menubar);
	    
	    //Conteneurs
	    JPanel conteneur = new JPanel();
	    conteneur.setLayout(new BoxLayout(conteneur, BoxLayout.PAGE_AXIS));
	    conteneur.add(gamescreen);
	    this.getContentPane().add(conteneur);
	    
	    //Affichage de la fenetre et ajustement de sa taille
	    setResizable(false);
	    this.setVisible(true);
	    setSize(800,450 + this.getHeight());
	    this.setLocationRelativeTo(null);
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
