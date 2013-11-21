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
	JComboBox<String> comboBoxAlgo;
	JComboBox<String> comboBoxDict;
	JComboBox<Integer> comboBoxBonusLetter2;
	
	public OptionsWindow(JFrame parent, HashMap<String, RuzzleDictionary> dicList){
		super(parent, "Partie personnalisée", true);
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initComponent(dicList);
		this.setVisible(true);
	}
	
	private void initComponent(HashMap<String, RuzzleDictionary> dicList){
		//VOLET GAUCHE (Saisie du board et des bonus)
		JPanel panGauche = new JPanel();
		panGauche.setMinimumSize(new Dimension(700, 200));
		panGauche.setBorder(BorderFactory.createTitledBorder("Plateau de jeu"));
		//Board
		JPanel panBoard = new JPanel();
		panGauche.add(panBoard);
		panBoard.setLayout(new BoxLayout(panBoard, BoxLayout.PAGE_AXIS));
		//Creation des JFormatedTextField
		MaskFormatter format = null;
		try{
			format = new MaskFormatter("?");
		}catch(ParseException e){e.printStackTrace();}
		JFormattedTextField[][] boardCharArray = new JFormattedTextField[4][];
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
		//Bonus
		int[] numCaseBoard = new int[24];
		for(int i = 0; i < 24; i++){
			numCaseBoard[i] = i;
		}
		//Emplacement du Bonus Lettre compte double
		JPanel panBonusLetter2 = new JPanel();
		panGauche.add(panBonusLetter2);
		//comboBoxBonusLetter2 = new JComboBox<String>(stringDict);
		
		
		
		//VOLET DROIT
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
