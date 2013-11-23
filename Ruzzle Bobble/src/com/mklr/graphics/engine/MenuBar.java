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
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import com.mklr.ruzzle.data.RuzzleDictionary;

public class MenuBar extends JMenuBar {
	Engine engine;
	HashMap<String, RuzzleDictionary> dicList;
	
	//Fichier
	private JMenu mFichier = new JMenu("Fichier");
	private JMenuItem iPartiePerso = new JMenuItem("Lancer une partie personalisée");
	private JMenuItem iQuitterPartie = new JMenuItem("Quitter la partie");
	private JMenuItem iQuitter = new JMenuItem("Quitter");
	
	//Options
	private JMenu mOptions = new JMenu("Options");
	
	private JMenu mChoixLangue = new JMenu("Choix de la langue du dictionnaire");
	private JRadioButtonMenuItem[] dicoRadioArray;
	
	private JMenu mChoixAlgo = new JMenu("Choix de l'algorithme de resolution");
	private LinkedList <JRadioButtonMenuItem> AlgoList;
	
	private JMenuItem iStatCreationMot = new JMenuItem("Statistique de generation des mots");
	//Algo
	private JMenuItem iAPropos = new JMenuItem("A propos");
	
	public MenuBar(final Engine engine){
		super();
		this.engine = engine;
		
		//Menu Fichier
		this.add(mFichier);
		mFichier.add(iPartiePerso);
		mFichier.add(iQuitterPartie);
		iQuitterPartie.setEnabled(false);
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
        iPartiePerso.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent arg0){
                        OptionsWindow optwindow = new OptionsWindow(null, engine.getDicList());
                }
        });
        iQuitterPartie.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent arg0){
                	engine.setGameTitle();
                }
        });
        iQuitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                engine.exit();
            }
        });
        iAPropos.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
            	String info = "Ruzzle Bubble est un projet universitaire développé par deux étudiants :\n-Mehdi Khelifi\n-Loic Runarvot\n\n Les sprites des mascottes du projet ainsi que les deux pistes musicales sont issus de la série Puzzle Bobble.";
            	JOptionPane aProposWindow = new JOptionPane();
            	aProposWindow.showMessageDialog(null, info, "A propos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
	}
	
	public void run_game(){
		iPartiePerso.setEnabled(false);
		engine.setGameStage();
	}


	
	   //////////////////////////////////////////////////////////////////
	  ///////////////////////// ACCESSEURS MODIFIEURS///////////////////
	 //////////////////////////////////////////////////////////////////
	
	/**
	 * @return the mChoixLangue
	 */
	public JMenu getmChoixLangue() {
		return mChoixLangue;
	}

	/**
	 * @param mChoixLangue the mChoixLangue to set
	 */
	public void setmChoixLangue(JMenu mChoixLangue) {
		this.mChoixLangue = mChoixLangue;
	}

	/**
	 * @return the mChoixAlgo
	 */
	public JMenu getmChoixAlgo() {
		return mChoixAlgo;
	}

	/**
	 * @param mChoixAlgo the mChoixAlgo to set
	 */
	public void setmChoixAlgo(JMenu mChoixAlgo) {
		this.mChoixAlgo = mChoixAlgo;
	}

	/**
	 * @return the iPartiePerso
	 */
	public JMenuItem getiPartiePerso() {
		return iPartiePerso;
	}

	/**
	 * @param iPartiePerso the iPartiePerso to set
	 */
	public void setiPartiePerso(JMenuItem iPartiePerso) {
		this.iPartiePerso = iPartiePerso;
	}

	/**
	 * @return the iQuitterPartie
	 */
	public JMenuItem getiQuitterPartie() {
		return iQuitterPartie;
	}

	/**
	 * @param iQuitterPartie the iQuitterPartie to set
	 */
	public void setiQuitterPartie(JMenuItem iQuitterPartie) {
		this.iQuitterPartie = iQuitterPartie;
	}
	
	
	
}
