package com.mklr.ruzzle.solver;

import java.util.ArrayList;

import java.util.LinkedList;

import com.mklr.collection.Tree;
import com.mklr.ruzzle.data.Letter;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;

public class SolveByMarbleGrid extends Solver {
    RuzzleDictionary dictionary;
    Board gameBoard;
    Marble[][] marblesBoard;
    ArrayList<SolutionWord> wordsList;

    public SolveByMarbleGrid(byte type, RuzzleDictionary d, Board b) {
        super(type);
        this.dictionary = d;
        this.gameBoard = b;
        marblesBoard = gameBoard.getGrid();
        wordsList = new ArrayList<SolutionWord>();
    }

    public void solve() {
        int i = 0;
        
        for (Marble[] marbleRow : marblesBoard) {
            int j = 0;
            for (Marble marble : marbleRow) {
                System.out.println("DFS MARBLE["+i+"]["+j+"]");
 //               toBlank();
                dfs(new Integer[]{i, j}, "", 0, dictionary.getDictionaryTree(), new LinkedList<Integer[]>());
                ++j;
            }
            ++i;
        }
    }

    private void toBlank() {
        Marble[][] marblesBoard = gameBoard.getGrid();

        for (Marble[] marbleRow : marblesBoard) {
            for (Marble marble : marbleRow) {
                marble.setState(Marble.WHITE_STATE);
            }
        }
    }

    private void dfs(Integer[] marbleCoo, String currentWord, 
            int wordLength, Tree<Character> position, LinkedList<Integer[]> path) {
        if (wordLength > dictionary.getMaxLength())
            return;

        Marble m = marblesBoard[marbleCoo[0]][marbleCoo[1]]; 
        Letter l = m.getLetter();
        LinkedList<Integer[]> path_cpy = new LinkedList<Integer[]>(path);
        Tree<Character> child = position.getChild(l.getLetter());
        if (child == null)
            return;

        currentWord += l.getLetter();

        if (child.isTerminal() && !wordsList.contains(currentWord))
            wordsList.add(new SolutionWord(currentWord, 1));

        m.setState(Marble.GREY_STATE);
        path_cpy.add(marbleCoo);

        for (Integer[] neighbours : m.getNeighbours()) {
            if (!path_cpy.contains(neighbours)) {
                dfs(neighbours, currentWord, wordLength+1, child, path_cpy);
            }
        }

        m.setState(Marble.BLACK_STATE);
    }

    private void bfs() {

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
