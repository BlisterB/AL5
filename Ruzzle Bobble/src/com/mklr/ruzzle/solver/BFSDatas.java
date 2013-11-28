package com.mklr.ruzzle.solver;

import com.mklr.collection.Tree;

import java.util.LinkedList;

/**
 */
public class BFSDatas {
    private Tree<Character> currentPosition;
    private SolutionWord currentWord;
    private Integer[] positionBoard;
    private LinkedList<Integer[]> pathToGetCurrentWord;

    BFSDatas(Tree<Character> currentPosition, SolutionWord currentWord,
            Integer[] positionInBoard, LinkedList<Integer[]> pathToGetCurrentWord) {
        this.currentPosition = currentPosition;
        this.currentWord = currentWord;
        this.positionBoard = positionInBoard;
        this.pathToGetCurrentWord = pathToGetCurrentWord;
    }

    BFSDatas(BFSDatas data) {
        currentPosition = data.currentPosition;
        currentWord = new SolutionWord(data.currentWord);
        positionBoard = data.positionBoard;
        pathToGetCurrentWord = new LinkedList<Integer[]>(data.pathToGetCurrentWord);
    }

    public Tree<Character> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Tree<Character> currentPosition) {
        this.currentPosition = currentPosition;
    }

    public SolutionWord getCurrentWord() {
        return currentWord;
    }

    public Integer[] getPositionBoard() {
        return positionBoard;
    }

    public void setPositionBoard(Integer[] positionBoard) {
        this.positionBoard = positionBoard;
    }

    public LinkedList<Integer[]> getPathToGetCurrentWord() {
        return pathToGetCurrentWord;
    }
}
