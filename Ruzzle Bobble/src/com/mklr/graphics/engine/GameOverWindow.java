package com.mklr.graphics.engine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Game;

public class GameOverWindow extends JDialog {
	Engine engine;
	Board board;
	Game game;
	JFrame parent;
	
	public GameOverWindow(JFrame parent, Engine engine, Board board, Game game){
		super(parent, "Fin de partie !", true);
		this.engine = engine;
		this.board = board;
		this.game = game;
		this.parent = parent;
		JPanel container;
		
		
		//Le JLabel
		JPanel panLab = new JPanel();
		panLab.setLayout(new BoxLayout(panLab, BoxLayout.PAGE_AXIS));
		JLabel labScore = new JLabel("Bravo !\nVous avez obtenu un score de " + game.getScore() + " !");
		JLabel labEncouragement = new JLabel("Arriverez vous à faire mieux la prochaine fois ?");
		JLabel labLastWord = new JLabel("Voici la liste de mots que vous avez trouvé :");
		panLab.add(labScore);
		panLab.add(labEncouragement);
		panLab.add(labLastWord);
		
		//Les mots trouvés
		String[] tabTitre = {"Mots"};
		String[][] tabContenu = new String[game.getWordsGiven().size()][];
		for(int i = 0; i < game.getWordsGiven().size(); i++){
			tabContenu[i] = new String[1];
			tabContenu[i][0] = game.getWordsGiven().get(i);
		}
		JTable table = new JTable(tabContenu, tabTitre);
		
		//Le bouton pour l'affichage des mots
		JButton bouton = new JButton("Afficher tous les mots");
		bouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                    displayWords();
            }
        });
        
		//Container
		container = new JPanel();
		container.setLayout(new BorderLayout());
		getContentPane().add(container);
		container.add(panLab, BorderLayout.NORTH);
		container.add(new JScrollPane(table), BorderLayout.CENTER);
		container.add(bouton, BorderLayout.SOUTH);
		
		//Propriété de la fenetre
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void displayWords(){
		DisplayWordsWindow window = new DisplayWordsWindow(parent, engine, board);
	}
}
