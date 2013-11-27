package com.mklr.graphics.engine;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class DisplayWordsWindow extends JDialog {
	Engine engine;
	String[] algoTabString = {"DFS Grille", "BFS Grille", "DFS Arbre", "BFS Arbre"};
	JComboBox<String> comboBoxAlgo;

	public DisplayWordsWindow(JFrame parent, Engine engine){
		super(parent, "Partie personnalisée", true);

		this.engine = engine;
		
		this.setResizable(false);
		this.addSearshPanel();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void addSearshPanel(){
		
		   //////////////////////////////////////////////////////////////////
		  //   VOLET DU HAUT (choix de l'algo et des options de tri )    ///
		 //////////////////////////////////////////////////////////////////
		
		JPanel panHaut = new JPanel();
		panHaut.setBorder(BorderFactory.createTitledBorder("Génération des solutions"));
		panHaut.setLayout(new BoxLayout(panHaut, BoxLayout.PAGE_AXIS));
		
		//I/ Choix de l'algo
		JPanel panAlgo = new JPanel();
		JLabel labAlgo = new JLabel("Choix de l'algorithme :");
		comboBoxAlgo = new JComboBox<String>(algoTabString);
		panAlgo.add(labAlgo);
		panAlgo.add(comboBoxAlgo);
		
		//II/Choix des options de tri
		JPanel panOption = new JPanel();
		JLabel labOption = new JLabel("Trier par :");
		panOption.setLayout(new BoxLayout(panOption, BoxLayout.PAGE_AXIS));
		
		JPanel panTri = new JPanel();
		panTri.setLayout(new BoxLayout(panTri, BoxLayout.LINE_AXIS));
		ButtonGroup groupOption = new ButtonGroup();
		JRadioButton radioAlphabetique = new JRadioButton("Par ordre alphabetique");	groupOption.add(radioAlphabetique);
		JRadioButton radioTaille = new JRadioButton("Par taille du mot");				groupOption.add(radioTaille);
		JRadioButton radioScore = new JRadioButton("Par score");						groupOption.add(radioScore);
		
		panOption.add(labOption);
		panOption.add(panTri);
		panTri.add(radioAlphabetique);
		panTri.add(radioTaille);
		panTri.add(radioScore);
		
		//III/Bouton recherche
		JButton buttonValider = new JButton("Valider");
		
		//Ajouts au panHaut
		panHaut.add(panAlgo);
		panHaut.add(panOption);
		panHaut.add(buttonValider);
		
		//Container
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(panHaut, BorderLayout.SOUTH);
		this.getContentPane().add(container);
	}
}
