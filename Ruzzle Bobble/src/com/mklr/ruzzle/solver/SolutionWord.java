package com.mklr.ruzzle.solver;

import java.util.Comparator;
import java.util.LinkedList;

import com.mklr.ruzzle.data.Letter;
import com.mklr.ruzzle.engine.Marble;

/**
 * Stock needed informations about the word.
 */
public class SolutionWord 
        implements Comparator<SolutionWord>, Comparable<SolutionWord> {

    /**
     * The sort type use to sort the word list after performing the algorithm.
     */
    public static byte SORT_TYPE = Solver.SORT_BY_WORD_LENGTH;


    /**
     * The path used to get the word.
     */
    private LinkedList<Integer[]> solutionPath;

    /**
     * The current word.
     */
    private String word;

    /**
     * Word's length.
     */
    private int length;

    /**
     * Word's score.
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
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the solutionPath
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
     * add the letter to the current word according to the marble's letter
     * @param m
     */
    public void addLetter(Marble m) {
    	addLetter(m.getLetter().getLetter());
    }

    /**
     * add the letter to the current word
     * @param c
     */
    public void addLetter(Character c) {
        word += c;
        ++length;
    }

    /**
     * End the word, and calculate the score.
     * Marble can have bonuses. (2 * the score of letter...)
     * According to the length of the word, it gives an extra bonus (max 25)
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

    public String toString() {
        String solution =  "\n" + word + "\t\tLength: " + length + "\tScore: " + score + "" + "\npath : "; 
        for (Integer[] i : solutionPath) {
        	solution += ("[" + i[0] + "][" + i[1] + "], ");
        }
        return solution + "\n\n";
    }


    /**
     * Change the sort type.
     * @param newSort
     */
    public static void changeSortType(byte newSort) {
        SORT_TYPE = newSort;
    }
}
