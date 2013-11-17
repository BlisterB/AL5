package com.mklr.ruzzle.solver;

import java.util.Comparator;
import java.util.LinkedList;

import com.mklr.ruzzle.data.Letter;
import com.mklr.ruzzle.engine.Marble;

public class SolutionWord 
        implements Comparator<SolutionWord>, Comparable<SolutionWord> {

    public static byte SORT_TYPE = Solver.SORT_BY_WORD_LENGTH;    

    private LinkedList<Integer[]> solutionPath;
    private String word;
    private int length;
    private int score;
    private int wordMultiplicator = 1;
    
    public SolutionWord() {
    	word = new String();
    	length = 0;
    	score = 0;
    }

    public SolutionWord(String word, int score) {
        this.word = word;
        length = word.length();
        this.score = score;
    }
    
    public SolutionWord(SolutionWord s) {
    	this.word = s.word;
    	this.length = s.length;
    	this.score = s.score;
    	this.wordMultiplicator = s.wordMultiplicator;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
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

    @Override
    public int compareTo(SolutionWord o) {
        if (SORT_TYPE == Solver.SORT_BY_WORD_LENGTH) {
            return o.length - length;
        } else if (SORT_TYPE == Solver.SORT_BY_NAME) {
            return word.compareTo(o.word);
        } else {
            return o.score - score;
        }
    }

    @Override
    public int compare(SolutionWord arg0, SolutionWord arg1) {
        return arg0.compareTo(arg1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SolutionWord) {
            return word.equals(((SolutionWord)obj).word)
                && score == ((SolutionWord)obj).score
                && length == ((SolutionWord)obj).length;
        }
        return false;
    }
    
    public void addLetter(Marble m) {
    	Letter l = m.getLetter();
    	int score = l.getValue();
    	Character letter = l.getLetter();
    	
    	switch (m.getBonus()) {
    		case Marble.LETTER_COUNT_DOUBLE :
    			score *= 2;
    			break;
    		case Marble.LETTER_COUNT_TRIPLE :
    			score *= 3;
    			break;
    		case Marble.WORD_COUNT_DOUBLE :
    			wordMultiplicator *= 2;
    			break;
    		case Marble.WORD_COUNT_TRIPLE :
    			wordMultiplicator *= 3;
    			break;
    		default :
    			break;
    	}
    	
    	this.score += score;
    	word += letter;
    	++length;
    }
    
    public void endWord(LinkedList<Integer[]> path) {
    	solutionPath = path;
    	score *= wordMultiplicator;
    	
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

    public static void changeSortType(byte newSort) {
        SORT_TYPE = newSort;
    }
}
