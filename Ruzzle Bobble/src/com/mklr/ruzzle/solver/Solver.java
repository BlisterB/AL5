package com.mklr.ruzzle.solver;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Abstract class use as a template for the two differents algorithms
 * (ie. by dictionary and by grid) use in this project.
 */
public abstract class Solver {

    /**
     * Constant use to set the algorithm to BFS.
     */
    public static final byte BREADTH_FIRST_SEARCH = 0;

    /**
     * Constant use to set the algorithm to DFS.
     */
    public static final byte DEPTH_FIRST_SEARCH = 1;



    /**
     * Constant use to sort the word list by name (ie lexicographically).
     */
    public static final byte SORT_BY_NAME = 0;

    /**
     * Constant use to sort by the word's score.
     */
    public static final byte SORT_BY_SCORE = 1;

    /**
     * Constatnt use to sort by the word's length.
     */
    public static final byte SORT_BY_WORD_LENGTH = 2;



    /**
     * It contains the value of the algorithm to use.
     * (ie : BFS or DFS)
     */
    protected byte algorithmType;

    /**
     * The time used by algorithms to find each words.
     */
    protected double timer;

    /**
     * The number of words found by algorithms.
     */
    protected long wordCount;

    /**
     * List of words found by the algorithm.
     * @see SolutionWord
     */
    protected ArrayList<SolutionWord> wordsList;


    /**
     * Temporary tree which contains each words found by the algorithm.
     * It's usefull to save the each SolutionWord in this tree because it allows to
     * find if the word already exist (at a complexity of O(log n)), and to switch
     * SolutionWord if needed (new SolutionWord as an higher score for exemple...) at
     * low cost.
     * @see SolutionWord
     */
    protected TreeMap<String, SolutionWord> __tmp_wordsList;


    /**
     * Empty constructor.
     */
    protected Solver() {

    }

    /**
     * Create a solver with the algorithm given. It could only be
     * value given by constants 'BREADTH_FIRST_SEARCH' and 'DEPTH_FIRST_SEARCH'.
     * After creating the solver (initialized to an empty solver first), the function
     * solve has to be called.
     * @param algorithmType the type of algorithm
     */
    protected Solver(byte algorithmType) {
        this.algorithmType = algorithmType;
        wordsList = new ArrayList<SolutionWord>();

        timer = 0.0;
        wordCount = 0;

        __tmp_wordsList = new TreeMap<String, SolutionWord>();
    }

    /**
     * Function to call to solve the grid.
     * It will call the default sortType (ie. BY_NAME).
     */
    public abstract void solve();

    /**
     * Function to call to solve the grid.
     * It will sort the word list to the given sort type.
     * @param sortType
     */
    public abstract void solve(byte sortType);


    /**
     * @see SolutionWord
     * @return the words' list
     */
    public ArrayList<SolutionWord> getWordsList() {
        return wordsList;
    }

    /**
     * @return the number of words found
     */
    public long getWordCount() {
        return wordCount;
    }

    /**
     * @return the time it tooks to perform the algorithm
     */
    public double getTimer() {
        return timer;
    }


    /**
     * Add the word given to the temporary list.
     * If the word already exist, it will compare the word score,
     * and add the higher one.
     * @see SolutionWord
     * @param currentWord the word to add
     */
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
