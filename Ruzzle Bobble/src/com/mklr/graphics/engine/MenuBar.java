package com.mklr.graphics.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.mklr.ruzzle.data.RuzzleDictionary;

public class MenuBar extends JMenuBar {
	Engine engine;
	HashMap<String, RuzzleDictionary> dicList;
	
	//Fichier
	private JMenu mFichier = new JMenu("Fichier");
	private JMenuItem iLancerPartie = new JMenuItem("Lancer une partie personalisée");
	private JMenuItem iQuitter = new JMenuItem("Quitter");
	
	//Options
	private JMenu mOptions = new JMenu("Options");
	private JMenu mChoixLangue = new JMenu("Choix de la langue du dictionnaire");
	private JRadioButtonMenuItem[] dicoRadioArray;
	private JMenu mChoixAlgo = new JMenu("Choix de l'algorithme de resolution");
	private LinkedList <JRadioButtonMenuItem> AlgoList;
	//Algo
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
		
		//Generation des choix de dictionnaire
		dicList = engine.getDicList();
		dicoRadioArray = new JRadioButtonMenuItem[dicList.size()];
	    ButtonGroup dicoRadioGroup = new ButtonGroup();
		int i =0;
		for(Map.Entry<String, RuzzleDictionary> entry : dicList.entrySet()) {
		    String cle = entry.getKey();
		    dicoRadioArray[i] = new JRadioButtonMenuItem(cle);
		    if(i == 0)
		    	dicoRadioArray[i].setSelected(true);
		    dicoRadioGroup.add(dicoRadioArray[i]);
		    mChoixLangue.add(dicoRadioArray[i]);
		    i++;
		}
		
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
