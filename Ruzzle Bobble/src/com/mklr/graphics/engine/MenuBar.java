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
	private ButtonGroup dicoRadioGroup;
	
	private JMenuItem iStatCreationMot = new JMenuItem("Statistique de generation des mots");
	private JMenuItem iCommentJouer = new JMenuItem("Comment jouer");
	
	private JMenuItem iAPropos = new JMenuItem("A propos");
	
	public MenuBar(final Engine engine){
		super();
		this.engine = engine;
		
		//Menu Fichier
		this.add(mFichier);
		mFichier.add(iPartiePerso);
		mFichier.add(iQuitterPartie);
		iQuitterPartie.setEnabled(false);
		mFichier.addSeparator();
		mFichier.add(iQuitter);
		
		//Menu Option
		this.add(mOptions);
		mOptions.add(mChoixLangue);
		mOptions.add(iStatCreationMot);
		mOptions.addSeparator();
		mOptions.add(iCommentJouer);
		mOptions.add(iAPropos);
		
		//Generation des choix de dictionnaire
		dicList = engine.getDicList();
		dicoRadioArray = new JRadioButtonMenuItem[dicList.size()];
	    dicoRadioGroup = new ButtonGroup();
		int i =0;
		for(Map.Entry<String, RuzzleDictionary> entry : dicList.entrySet()){
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
                        OptionsWindow optwindow = new OptionsWindow(null, engine.getDicList(), engine);
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
        iCommentJouer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
            	String info = "Ruzzle Bubble est un puzzle game se jouant à la souris.\n"
            			+ "Lancez une partie normale en cliquant sur le bouton \"Jouer\" à l'écran de titre,\nou créant une partie personnalisée à l'aide du bouton \"Partie personalisée\" dans le menu Fichier.\n"
            			+ "Lors d'une partie, maintenez le clic de souris sur les lettre du plateau pour former un mot.\n"
            			+ "Des bulles sont affichées à côté de certaines lettres et correspondent à des bonus :\n"
		            	+ "\t\t-Bulle verte : lettre compte double\n"
		            	+ "\t\t-Bulle bleue : lettre compte triple\n"
		            	+ "\t\t-Bulle rouge : mot compte double\n"
		            	+ "\t\t-Bulle dorée : mot compte triple\n";
            	JOptionPane.showMessageDialog(null, info, "A propos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        iAPropos.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
            	String info = "Ruzzle Bubble est un projet universitaire développé par deux étudiants :\n-Mehdi Khelifi\n-Loic Runarvot\n\n Les sprites des mascottes du projet ainsi que les deux pistes musicales sont issus de la série Puzzle Bobble.";
            	JOptionPane.showMessageDialog(null, info, "A propos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
	}
	
	public void run_game(){
		iPartiePerso.setEnabled(false);
		engine.setGameStage(null);
	}

	public String langSelected(){
		for(JRadioButtonMenuItem i : dicoRadioArray){
			if(i.isSelected())
				return i.getText();
		}
		return "French";
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

	/**
	 * @return the dicoRadioGroup
	 */
	public ButtonGroup getDicoRadioGroup() {
		return dicoRadioGroup;
	}

	/**
	 * @param dicoRadioGroup the dicoRadioGroup to set
	 */
	public void setDicoRadioGroup(ButtonGroup dicoRadioGroup) {
		this.dicoRadioGroup = dicoRadioGroup;
	}
	
	
	
}
