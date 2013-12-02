package com.mklr.ruzzle.solver;

import java.util.ArrayList;
import java.util.TreeMap;

public abstract class Solver {
    public static final byte BREADTH_FIRST_SEARCH = 0;
    public static final byte DEPTH_FIRST_SEARCH = 1;

    public static final byte SORT_BY_NAME = 0;
    public static final byte SORT_BY_SCORE = 1;
    public static final byte SORT_BY_WORD_LENGTH = 2;

    protected byte algorithmType;
    protected double timer;
    protected long wordCount;
    protected ArrayList<SolutionWord> wordsList;

    protected TreeMap<String, SolutionWord> __tmp_wordsList;

    protected Solver() {

    }

    protected Solver(byte algorithmType) {
        this.algorithmType = algorithmType;
        wordsList = new ArrayList<SolutionWord>();

        __tmp_wordsList = new TreeMap<String, SolutionWord>();

        timer = 0.0;
        wordCount = 0;
    }

    /**
     * @return the sortType
     */
    public byte getAlgorithmType() {
        return algorithmType;
    }

    /**
     * @param algorithmType the sortType to set
     */
    public void setAlgorithmType(byte algorithmType) {
        this.algorithmType = algorithmType;
    }

    public abstract void solve();
    public abstract void solve(byte sortType);

    public ArrayList<SolutionWord> getWordsList() {
        return wordsList;
    }

    public long getWordCount() {
        return wordCount;
    }

    public double getTimer() {
        return timer;
    }

    protected void addWord(SolutionWord currentWord) {
        if (__tmp_wordsList.containsKey(currentWord.getWord())) {
            SolutionWord tmpWord = __tmp_wordsList.get(currentWord.getWord());
            if (tmpWord.getScore() < currentWord.getScore()) {
                __tmp_wordsList.put(currentWord.getWord(), currentWord);
            }
        } else {
            __tmp_wordsList.put(currentWord.getWord(), currentWord);
        }
    }
}
