package com.mklr.graphics.engine;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.solver.SolveByDictionary;
import com.mklr.ruzzle.solver.SolveByMarbleGrid;
import com.mklr.ruzzle.solver.Solver;


public class DisplayWordsWindow extends JDialog {
	Engine engine;
	Board board;
	String[] algoTabString = {"DFS Grille", "BFS Grille", "DFS Arbre", "BFS Arbre"};
	JRadioButton[] sortTypeTab = new JRadioButton[3];
	JComboBox<String> comboBoxAlgo;
	JPanel container;
	Solver tabSolver[];
	
	public DisplayWordsWindow(JFrame parent, Engine engine, Board board){
		super(parent, "Partie personnalisée", true);

		this.engine = engine;
		this.board = board;
		
		tabSolver = new Solver[4];
		tabSolver[0] = new SolveByMarbleGrid(board,Solver.DEPTH_FIRST_SEARCH);
		tabSolver[1] = new SolveByMarbleGrid(board,Solver.BREADTH_FIRST_SEARCH);
		tabSolver[2] = new SolveByDictionary(board,Solver.DEPTH_FIRST_SEARCH);
		tabSolver[3] = new SolveByDictionary(board,Solver.BREADTH_FIRST_SEARCH);
		
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
		sortTypeTab[0] = new JRadioButton("Par ordre alphabetique");	groupOption.add(sortTypeTab[0]);
		sortTypeTab[1] = new JRadioButton("Par taille du mot");			groupOption.add(sortTypeTab[1]);
		sortTypeTab[2] = new JRadioButton("Par score");					groupOption.add(sortTypeTab[2]);
		
		panOption.add(labOption);
		panOption.add(panTri);
		panTri.add(sortTypeTab[0]);
		panTri.add(sortTypeTab[1]);
		panTri.add(sortTypeTab[2]);
		
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
		
		//Ajout des Action Listener
        buttonValider.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent arg0){
                        solve();
                }
        });
	}
	
	public void addWordArray(){
		
	}
	
	public void solve(){
		//On recupere l'id de l'algo choisi
		int solverChosed = comboBoxAlgo.getSelectedIndex();
		//On recupere l'option de tri
		int optionTri;
		if(sortTypeTab[0].isSelected()) optionTri = Solver.SORT_BY_NAME;
		else if(sortTypeTab[1].isSelected())
		tabSolver[solverChosed].solve();
	}
}
