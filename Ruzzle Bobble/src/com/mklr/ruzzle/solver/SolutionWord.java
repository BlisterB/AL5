package com.mklr.ruzzle.solver;

import java.util.Comparator;
import java.util.LinkedList;

import com.mklr.ruzzle.data.Letter;
import com.mklr.ruzzle.engine.Marble;

/**
 * Les informations essentiels du mots.
 */
public class SolutionWord 
        implements Comparator<SolutionWord>, Comparable<SolutionWord> {

    /**
     * Le type de tri effectué à la fin de la recherche des mots.
     */
    public static byte SORT_TYPE = Solver.SORT_BY_WORD_LENGTH;


    /**
     * Le chemin parcouru pour retrouver le mot.
     */
    private LinkedList<Integer[]> solutionPath;

    /**
     * Le mot courant.
     */
    private String word;

    /**
     * La taille du mot courant.
     */
    private int length;

    /**
     * Le score du mot courant.
     */
    private int score;

    public SolutionWord() {
    	word = "";
    	length = 0;
    	score = 0;
    }

    public SolutionWord(SolutionWord s) {
    	this.word = s.word;
    	this.length = s.length;
    	this.score = s.score;
    }

    /**
     * @return le mot.
     */
    public String getWord() {
        return word;
    }

    /**
     * @return la taille du mot.
     */
    public int getLength() {
        return length;
    }

    /**
     * @return le score du mot.
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score le nouveau score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return le chemin parcouru.
     */
    public LinkedList<Integer[]> getSolutionPath() {
        return solutionPath;
    }

    @Override
    public int compareTo(SolutionWord o) {
        if (SORT_TYPE == Solver.SORT_BY_WORD_LENGTH) {
            if (length != o.length) {
                return o.length - length;
            } else {
                return word.compareTo(o.word);
            }
        } else if (SORT_TYPE == Solver.SORT_BY_NAME) {
            return word.compareTo(o.word);
        } else {
            if (score == o.score) {
                return word.compareTo(o.word);
            } else {
                return o.score - score;
            }
        }
    }

    @Override
    public int compare(SolutionWord arg0, SolutionWord arg1) {
        return arg0.compareTo(arg1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SolutionWord) {
            return word.equals(((SolutionWord)obj).word);
        }
        return false;
    }

    /**
     * Ajoute la lettre au mot courant selon la case selectionnée.
     * @param m
     */
    public void addLetter(Marble m) {
    	addLetter(m.getLetter().getLetter());
    }

    /**
     * Ajoute la lettre au mot courant selon le Character donné.
     * @param c
     */
    public void addLetter(Character c) {
        word += c;
        ++length;
    }

    /**
     * Fini le mot et calcule le score.
     * Les case peuvent contenir des bonus (par exemple lettre compte double,
     * mot compte double, ...)
     * Selon la taille du mot, un bonus supplémentaire est ajouté.
     * @param path
     * @param gameBoard
     */
    public void endWord(LinkedList<Integer[]> path, Marble[][] gameBoard) {
    	int multiplicator = 1;
        int tmp_score = 0;

        solutionPath = path;


        for (Integer[] marble : path) {
            Marble m = gameBoard[marble[0]][marble[1]];
            int l_score = m.getLetter().getValue();

            switch (m.getBonus()) {
                case Marble.LETTER_COUNT_DOUBLE:
                    l_score *= 2;
                    break;
                case Marble.LETTER_COUNT_TRIPLE:
                    l_score *= 3;
                    break;
                case Marble.WORD_COUNT_DOUBLE:
                    multiplicator *= 2;
                    break;
                case Marble.WORD_COUNT_TRIPLE:
                    multiplicator *= 3;
                    break;
                default:
                    break;
            }

            tmp_score += l_score;
        }

        this.score = tmp_score * multiplicator;
    	
    	switch (length) {
    		case 8 :
    			score += 20;
    			break;
    		case 7 :
    			score += 15;
    			break;
    		case 6 :
    			score += 10;
    			break;
    		case 5 :
    			score += 5;
    			break;
    		default :
    			if (length >= 9)
    				score += 25;
    			break;
    	}
    }

    @Override
    public String toString() {
        String solution =  "\n" + word + "\t\tLength: " + length + "\tScore: " + score + "" + "\npath : ";
        for (Integer[] i : solutionPath) {
        	solution += ("[" + i[0] + "][" + i[1] + "], ");
        }
        return solution + "\n\n";
    }


    /**
     * Change le type de tri.
     * @param newSort
     */
    public static void changeSortType(byte newSort) {
        SORT_TYPE = newSort;
    }
}
