package com.mklr.graphics.engine;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	public MenuBar(){
		super();
		
		//Menu
		JMenu fichier = new JMenu("Fichier");
		JMenu algo = new JMenu("Algorithme");
		
		//Item de Fichier
		JMenuItem quitter = new JMenuItem("Quitter");
		fichier.add(quitter);
		
		//Item de Algo
		JMenuItem algoitem = new JMenuItem("Truc de la conasse");
		algo.add(algoitem);
		
		//Ajout des elements
		this.add(fichier);
		this.add(algo);
	}
}
