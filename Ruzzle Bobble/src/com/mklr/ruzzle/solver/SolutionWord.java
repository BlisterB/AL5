package com.mklr.ruzzle.solver;

import java.util.Comparator;

public class SolutionWord 
        implements Comparator<SolutionWord>, Comparable<SolutionWord> {

    public static byte SORT_TYPE = Solver.SORT_BY_WORD_LENGTH;    

    private String word;
    private int length;
    private int score;

    public SolutionWord() {

    }

    public SolutionWord(String word, int score) {
        this.word = word;
        length = word.length();
        this.score = score;
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

    public String toString() {
        return "\n" + word + "\t\tLength: " + length + "\tScore: " + score + "";
    }

    public static void changeSortType(byte newSort) {
        SORT_TYPE = newSort;
    }
}
