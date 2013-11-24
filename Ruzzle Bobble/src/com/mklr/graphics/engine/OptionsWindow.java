package com.mklr.graphics.engine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

import com.mklr.ruzzle.data.RuzzleDictionary;

public class OptionsWindow extends JDialog {
	private JPanel container = new JPanel();
	private JSpinner spinTimer;
	private JFormattedTextField[][] boardCharArray;
	private JComboBox<Integer> comboBoxBonusLetter2;
	private JComboBox<Integer> comboBoxBonusLetter3;
	private JComboBox<Integer> comboBoxBonusWord2;
	private JComboBox<Integer> comboBoxBonusWord3;
	private JComboBox<String> comboBoxAlgo;
	private JComboBox<String> comboBoxDict;
	
	public OptionsWindow(JFrame parent, HashMap<String, RuzzleDictionary> dicList){
		super(parent, "Partie personnalisée", true);
		this.setResizable(false);
		this.initComponent(dicList);
		//this.setSize(1000, 500);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void initComponent(HashMap<String, RuzzleDictionary> dicList){
		
		   //////////////////////////////////////////////////////////////////
		  ///////////   VOLET GAUCHE (Saisie du board et des bonus)   //////
		 //////////////////////////////////////////////////////////////////
		
		JPanel panGauche = new JPanel();
		panGauche.setBorder(BorderFactory.createTitledBorder("Plateau de jeu"));
		panGauche.setLayout(new BoxLayout(panGauche, BoxLayout.PAGE_AXIS));
		
		//I/ Board : Saisie des caracteres du Board
		//1)Le JLabel
		JLabel labBoard = new JLabel("Caractères du plateau :");
		
		//2)Le board
		JPanel panBoard = new JPanel();
		panBoard.setLayout(new BoxLayout(panBoard, BoxLayout.PAGE_AXIS));
		
		//Creation des JFormatedTextField
		MaskFormatter format = null;
		try{
			format = new MaskFormatter("?");//On accepte un seul caractere
		}catch(ParseException e){e.printStackTrace();}
		boardCharArray = new JFormattedTextField[4][];
		if(format != null){
			for(int i = 0; i < 4; i++){
				//On defini le nombre de cases
				if(i == 0 || i == 3)
					boardCharArray[i] = new JFormattedTextField[5];
				else
					boardCharArray[i] = new JFormattedTextField[7];
				//On cree un sous panel utilisant un BoxLayout comme Layout
				JPanel sousPanel = new JPanel();
			    sousPanel.setLayout(new BoxLayout(sousPanel, BoxLayout.LINE_AXIS));
			    panBoard.add(sousPanel);
				
				//On cree les JFormattedTextField puis on les ajoute
				for(int j = 0; j < boardCharArray[i].length; j++){
					boardCharArray[i][j] = new JFormattedTextField(format);
					boardCharArray[i][j].setMaximumSize(new Dimension(50, 50));
					boardCharArray[i][j].setMinimumSize(new Dimension(50, 50));
					boardCharArray[i][j].setPreferredSize(new Dimension(50, 50));
					sousPanel.add(boardCharArray[i][j]);
				}
			}
		}
		
		
		//II/ Les Bonus a positionner dans le Board
		JLabel labBonus = new JLabel("Position des bonus (de gauche à droite, de haut en bas) :");
		
		//Initialisation du tableau d'Integer servant de choix pour les Combobox
		Integer[] numCaseBoard = new Integer[24];
		for(int i = 0; i < 24; i++){
			numCaseBoard[i] = i;
		}
		
		//1) Lettre compte double
		JPanel panBonusLetter2 = new JPanel();
		JLabel labBonusLetter2 = new JLabel("Lettre compte double :");
		comboBoxBonusLetter2 = new JComboBox<Integer>(numCaseBoard);
		panBonusLetter2.add(labBonusLetter2);
		panBonusLetter2.add(comboBoxBonusLetter2);
		
		//2) Lettre compte triple
		JPanel panBonusLetter3 = new JPanel();
		JLabel labBonusLetter3 = new JLabel("Lettre compte triple :");
		comboBoxBonusLetter3 = new JComboBox<Integer>(numCaseBoard);
		panBonusLetter3.add(labBonusLetter3);
		panBonusLetter3.add(comboBoxBonusLetter3);
		
		//3) Lettre compte double
		JPanel panBonusWord2 = new JPanel();
		JLabel labBonusWord2 = new JLabel("Mot compte double :");
		comboBoxBonusWord2 = new JComboBox<Integer>(numCaseBoard);
		panBonusWord2.add(labBonusWord2);
		panBonusWord2.add(comboBoxBonusWord2);
		
		//4) Lettre compte double
		JPanel panBonusWord3 = new JPanel();
		JLabel labBonusWord3 = new JLabel("Mot compte triple :");
		comboBoxBonusWord3 = new JComboBox<Integer>(numCaseBoard);
		panBonusWord3.add(labBonusWord3);
		panBonusWord3.add(comboBoxBonusWord3);
		
		//Ajout des composant au JPanel Gauche
		panGauche.add(labBoard); 		labBoard.setAlignmentX(LEFT_ALIGNMENT);
		panGauche.add(panBoard); 		panBoard.setAlignmentX(LEFT_ALIGNMENT);
		panGauche.add(labBonus); 		labBonus.setAlignmentX(LEFT_ALIGNMENT);
		panGauche.add(panBonusLetter2); panBonusLetter2.setAlignmentX(LEFT_ALIGNMENT);
		panGauche.add(panBonusLetter3); panBonusLetter3.setAlignmentX(LEFT_ALIGNMENT);
		panGauche.add(panBonusWord2); 	panBonusWord2.setAlignmentX(LEFT_ALIGNMENT);
		panGauche.add(panBonusWord3); 	panBonusWord3.setAlignmentX(LEFT_ALIGNMENT);
		
		   //////////////////////////////////////////////////////////////////
		  ///////////                VOLET DROIT                      //////
		 //////////////////////////////////////////////////////////////////
		JPanel panDroit = new JPanel();
		panDroit.setLayout(new BoxLayout(panDroit, BoxLayout.PAGE_AXIS));
		
		//Algorithme de Parcours
		JPanel panAlgo = new JPanel();
		panAlgo.setBorder(BorderFactory.createTitledBorder("Algorithme"));
		JLabel labAlgo = new JLabel("Algorithme de parcours :");
		String[] stringAlgo = {"Algo 1", "Algo 2", "Algo 3", "Algo 4"};
		comboBoxAlgo = new JComboBox<String>(stringAlgo);
		panAlgo.add(labAlgo);
		panAlgo.add(comboBoxAlgo);
		panDroit.add(panAlgo);
		
		//Dictionnaire
		JPanel panDict = new JPanel();
		panDict.setBorder(BorderFactory.createTitledBorder("Dictionnaire"));
		JLabel labDict = new JLabel("Langue du dictionnaire :");
		//On creer le tableau de string des cle de 
		String[] stringDict = new String[dicList.size()];
		int i =0;
		for(Map.Entry<String, RuzzleDictionary> entry : dicList.entrySet()) {
		    stringDict[i] = entry.getKey();
		    i++;
		}
		comboBoxDict = new JComboBox<String>(stringDict);
		panDict.add(labDict);
		panDict.add(comboBoxDict);
		panDroit.add(panDict);
		//Timer
		JPanel panTimer = new JPanel();
		panTimer.setBorder(BorderFactory.createTitledBorder("Timer"));
		JLabel labTimer = new JLabel("Temps en secondes :");
		SpinnerNumberModel spinModelTimer = new SpinnerNumberModel(120, 10, 35640, 10);
		spinTimer = new JSpinner(spinModelTimer);
		panTimer.add(labTimer);
		panTimer.add(spinTimer);
		panDroit.add(panTimer);
		
		//VOLET BAS (bouton ok/buton annuler)
		JPanel panButtons = new JPanel();
		JButton buttonValider = new JButton("Valider");
		JButton buttonAnnuler = new JButton("Annuler");
		panButtons.add(buttonValider);
		panButtons.add(buttonAnnuler);
		
		//Container
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(panGauche, BorderLayout.CENTER);
		container.add(panDroit, BorderLayout.EAST);
		container.add(panButtons, BorderLayout.SOUTH);
		this.getContentPane().add(container);
	}
}
