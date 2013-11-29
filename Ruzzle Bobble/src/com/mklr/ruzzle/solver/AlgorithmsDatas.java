package com.mklr.ruzzle.solver;

import com.mklr.collection.Tree;

import java.util.LinkedList;

/**
 */
public class AlgorithmsDatas {
    private Tree<Character> currentPositionInTree;
    private SolutionWord currentWord;
    private Integer[] currentPositionInBoard;
    private LinkedList<Integer[]> currentPathToGetTheCurrentWord;

    public AlgorithmsDatas() {

    }

    public AlgorithmsDatas(Tree<Character> currentPositionInTree,
                           SolutionWord currentWord,
                           Integer[] currentPositionInBoard,
                           LinkedList<Integer[]> currentPathToGetTheCurrentWord) {
        this.currentPositionInTree = currentPositionInTree;
        this.currentWord = currentWord;
        this.currentPositionInBoard = currentPositionInBoard;
        this.currentPathToGetTheCurrentWord = currentPathToGetTheCurrentWord;
    }

    public Tree<Character> getCurrentPositionInTree() {
        return currentPositionInTree;
    }

    public void setCurrentPositionInTree(Tree<Character> currentPositionInTree) {
        this.currentPositionInTree = currentPositionInTree;
    }

    public SolutionWord getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(SolutionWord currentWord) {
        this.currentWord = currentWord;
    }

    public Integer[] getCurrentPositionInBoard() {
        return currentPositionInBoard;
    }

    public void setCurrentPositionInBoard(Integer[] currentPositionInBoard) {
        this.currentPositionInBoard = currentPositionInBoard;
    }

    public LinkedList<Integer[]> getCurrentPathToGetTheCurrentWord() {
        return currentPathToGetTheCurrentWord;
    }

    public void setCurrentPathToGetTheCurrentWord(LinkedList<Integer[]> currentPathToGetTheCurrentWord) {
        this.currentPathToGetTheCurrentWord = currentPathToGetTheCurrentWord;
    }

    public static AlgorithmsDatas getDatasCopy(AlgorithmsDatas datas) {
        return new AlgorithmsDatas(datas.currentPositionInTree,
                    new SolutionWord(datas.currentWord),
                    datas.currentPositionInBoard,
                    new LinkedList<Integer[]>(datas.currentPathToGetTheCurrentWord));
    }

}
