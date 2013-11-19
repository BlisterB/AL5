package com.mklr.ruzzle.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import com.mklr.collection.Tree;
import com.mklr.ruzzle.data.Letter;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;

public class SolveByMarbleGrid extends Solver {
    RuzzleDictionary dictionary;
    Marble[][] marblesBoard;
    ArrayList<SolutionWord> wordsList;

    public SolveByMarbleGrid(byte type, RuzzleDictionary d, Board b) {
        super(type);
        this.dictionary = d;
        marblesBoard = b.getGrid();
        wordsList = new ArrayList<SolutionWord>();
    }

    public void solve() {
        int i = 0;
        
        for (Marble[] marbleRow : marblesBoard) {
            int j = 0;
            for (Marble marble : marbleRow) {
                dfs(new Integer[]{i, j}, new SolutionWord(), 0, 
                		dictionary.getDictionaryTree(), new LinkedList<Integer[]>());
                ++j;
            }
            ++i;
        }
        SolutionWord.changeSortType(Solver.SORT_BY_SCORE);
        Collections.sort(wordsList, new SolutionWord());
    }

    private void dfs(Integer[] marbleCoo, SolutionWord currentWord, 
            int wordLength, Tree<Character> position, LinkedList<Integer[]> path) {
        if (wordLength > dictionary.getMaxLength())
            return;

        SolutionWord nextWord;
        Marble m = marblesBoard[marbleCoo[0]][marbleCoo[1]]; 
        Letter l = m.getLetter();
        
        LinkedList<Integer[]> path_cpy = new LinkedList<Integer[]>(path);
        path_cpy.add(marbleCoo);

        if (l.getLetter() == '*') {
            for (Tree<Character> child : position.getListOfChilds().values()) {
                nextWord = new SolutionWord(currentWord);
                nextWord.addLetter(dictionary.getLetterSet()
                            .getLetter(child.getNodeValue()));
                for (Integer[] neighbours : m.getNeighbours()) {
                    if (!containsNeighbour(path_cpy, neighbours)) {
                        dfs(neighbours, nextWord, 
                                wordLength + 1, child, path_cpy);
                    }
                }
            }
        } else {
            nextWord = new SolutionWord(currentWord);
            Tree<Character> child = position.getChild(l.getLetter());
            if (child == null)
                return;

            nextWord.addLetter(m);

            if (child.isTerminal() && !containsWord(nextWord)) {
        	    nextWord.endWord(path_cpy);
                wordsList.add(nextWord);
            }

            for (Integer[] neighbours : m.getNeighbours()) {
                if (!containsNeighbour(path_cpy, neighbours)) {
                    dfs(neighbours, nextWord, wordLength+1, child, path_cpy);
                }
            }
        }
    }

    private void bfs() {

    }

    private boolean containsWord(SolutionWord s) {
    	String word = s.getWord();
    	for (SolutionWord as : wordsList) {
    		if (word.equals(as.getWord()))
    			return true;
    	}
    	return false;
    }
    
    private boolean containsNeighbour(LinkedList<Integer[]> path, Integer[] neighbour) {
    	for (Integer[] i : path) {
    		if (i[0].equals(neighbour[0]) && i[1].equals(neighbour[1]))
    			return true;
    	}
    	return false;
    }
    
    /**
     * @return the wordsList
     */
    public ArrayList<SolutionWord> getWordsList() {
        return wordsList;
    }

    /**
     * @param wordsList the wordsList to set
     */
    public void setWordsList(ArrayList<SolutionWord> wordsList) {
        this.wordsList = wordsList;
    }

}
