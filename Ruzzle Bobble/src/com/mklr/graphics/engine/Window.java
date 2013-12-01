package com.mklr.graphics.engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.mklr.ruzzle.data.RuzzleDictionary;

public class Window extends JFrame {
	private GameScreen gamescreen;
	private Engine engine;
	private MenuBar menubar;

	HashMap<String, RuzzleDictionary> dicList;

	public Window(final Engine engine) {
		// Proprietes
		this.setTitle("Ruzzle Bobble");
		this.setIconImage(new ImageIcon(Engine.PATH + "img/interface/icone.png")
				.getImage());
		// On utilise le look and feel du system si possible
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Liaison a l'engine
		engine.setWindow(this);

		// Initialisation des composants
		this.engine = engine;
		this.gamescreen = engine.getGamescreen();

		// Barre de menu
		menubar = new MenuBar(engine);
		this.setJMenuBar(menubar);

		// On definie l'action a la fermeture de la fenetre
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				engine.exit();
			}
		};
		this.addWindowListener(exitListener);

		// Ajout du gamescreen au content pane
		this.getContentPane().add(gamescreen);

		// Affichage de la fenetre et ajustement de sa taille
		setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * @return the gamescreen
	 */
	public GameScreen getGamescreen() {
		return gamescreen;
	}

	/**
	 * @param gamescreen
	 *            the gamescreen to set
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
	 * @param engine
	 *            the engine to set
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
	 * @param menubar
	 *            the menubar to set
	 */
	public void setMenubar(MenuBar menubar) {
		this.menubar = menubar;
	}

}
