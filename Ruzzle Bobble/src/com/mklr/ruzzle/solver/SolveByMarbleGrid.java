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

    public SolveByMarbleGrid(RuzzleDictionary d, Board b) {
        this.dictionary = d;
        marblesBoard = b.getGrid();
        wordsList = new ArrayList<SolutionWord>();
    }

    public void solve() {
        solve(Solver.SORT_BY_WORD_LENGTH);
    }

    public void solve(byte sortType) {
        if (!wordsList.isEmpty())
            return;

        for (int i = 0; i < marblesBoard.length; i++) {
            for (int j = 0; j < marblesBoard[i].length; j++){
                dfs(new Integer[]{i, j}, new SolutionWord(), 0,
                        dictionary.getDictionaryTree(), new LinkedList<Integer[]>());
            }
        }

        sort(sortType);
    }

    public void sort(byte sortType) {
        byte initialSortType = SolutionWord.SORT_TYPE;
        SolutionWord.changeSortType(sortType);
        Collections.sort(wordsList, new SolutionWord());
        SolutionWord.changeSortType(initialSortType);
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
                            .getLetter(child.getNodeValue()), true);
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
}
