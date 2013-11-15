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
	
	//Algorithme
	private JMenu mAlgorithme = new JMenu("Algorithme");
	private JMenuItem iConnasse = new JMenuItem("Truc de la conasse");
	
	public MenuBar(final Engine engine){
		super();
		this.engine = engine;
		
		//Menu Fichier
		this.add(mFichier);
		mFichier.add(iLancerPartie);
		iLancerPartie.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		mFichier.add(iQuitter);
		
		//Menu Algorithme
		this.add(mAlgorithme);
		mAlgorithme.add(iConnasse);
		
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
