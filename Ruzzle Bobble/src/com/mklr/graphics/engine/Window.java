package com.mklr.graphics.engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window extends JFrame {
	private GameScreen gamescreen;
	private Engine engine;
	private MenuBar menubar;
	
	public Window(final Engine engine){
		//Proprietes
	    this.setTitle("Ruzzle Bobble");
	    //this.setSize(800, 500);//A MODIFIER
	    this.setIconImage(new ImageIcon(Engine.PATH + "img/interface/icone.png").getImage());
	    
	    //Initialisation des composants
	    this.engine = engine;
	    this.gamescreen = engine.getGamescreen();
	    menubar = new MenuBar(engine);
	    
	    //Barre de menu
	    this.setJMenuBar(menubar);
	    
	    //On definie l'action a la fermeture de la fenetre
	    this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		engine.exit();
        	}
        };
        this.addWindowListener(exitListener);
	    
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
