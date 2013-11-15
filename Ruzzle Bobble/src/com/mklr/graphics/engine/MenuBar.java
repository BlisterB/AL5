package com.mklr.graphics.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	Engine engine;
	
	//Fichier
	private JMenu mFichier = new JMenu("Fichier");
	private JMenuItem iLancerPartie = new JMenuItem("Lancer une partie");
	private JMenuItem iQuitter = new JMenuItem("Quitter");
	
	//Options
	private JMenu mOptions = new JMenu("Options");
	private JMenu mChoixLangue = new JMenu("Choix de la langue du dictionnaire");
	private JMenu mChoixAlgo = new JMenu("Choix de l'algorithme de resolution");
	private JMenuItem iAPropos = new JMenuItem("A propos");
	
	public MenuBar(final Engine engine){
		super();
		this.engine = engine;
		
		//Menu Fichier
		this.add(mFichier);
		mFichier.add(iLancerPartie);
		mFichier.add(iQuitter);
		
		//Menu Option
		this.add(mOptions);
		mOptions.add(mChoixLangue);
		mOptions.add(mChoixAlgo);
		mOptions.addSeparator();
		mOptions.add(iAPropos);
		
		
		//Ajout des Action Listener
        iLancerPartie.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent arg0){
                        run_game();
                }
        });
        iQuitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                engine.exit();
            }
        });
	}
	
	public void run_game(){
		iLancerPartie.setEnabled(false);
		engine.setGameStage();
	}
}
