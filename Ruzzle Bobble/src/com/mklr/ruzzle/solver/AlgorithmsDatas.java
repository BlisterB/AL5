package com.mklr.ruzzle.solver;

import com.mklr.collection.Tree;

import java.util.LinkedList;

/**
 * Cette classe contient les données nécessaire à la résolution des
 * algorithmes.
 * Elle contient la position courante dans l'arbre, le mot courant, le chemin
 * parcouru pour obtenir le mot, ainsi que la position courante dans le
 * plateau.
 *
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class AlgorithmsDatas {

    /**
     * La position courante dans l'arbre.
     * @see Tree
     */
    private Tree<Character> currentPositionInTree;

    /**
     * Le mot courant.
     * @see SolutionWord
     */
    private SolutionWord currentWord;

    /**
     * La position courante dans le plateau.
     */
    private Integer[] currentPositionInBoard;

    /**
     * Le chemin parcour pour obtenir le mot courant.
     * Utilisé pour vérifier si une case a déjà été parcourue.
     */
    private LinkedList<Integer[]> currentPathToGetTheCurrentWord;

    public AlgorithmsDatas() {

    }

    /**
     * Constructeur des nouvelles données.
     * @param currentPositionInTree
     * @param currentWord
     * @param currentPositionInBoard
     * @param currentPathToGetTheCurrentWord
     */
    public AlgorithmsDatas(Tree<Character> currentPositionInTree,
                           SolutionWord currentWord,
                           Integer[] currentPositionInBoard,
                           LinkedList<Integer[]> currentPathToGetTheCurrentWord) {
        this.currentPositionInTree = currentPositionInTree;
        this.currentWord = currentWord;
        this.currentPositionInBoard = currentPositionInBoard;
        this.currentPathToGetTheCurrentWord = currentPathToGetTheCurrentWord;
    }

    /**
     * @return La position courante dans l'arbre.
     */
    public Tree<Character> getCurrentPositionInTree() {
        return currentPositionInTree;
    }

    /**
     * @return Le mot courant.
     */
    public SolutionWord getCurrentWord() {
        return currentWord;
    }

    /**
     * @return La position courante dans le plateau.
     */
    public Integer[] getCurrentPositionInBoard() {
        return currentPositionInBoard;
    }

    /**
     * @return Le chemin parcouru pour avoir le mot courant.
     */
    public LinkedList<Integer[]> getCurrentPathToGetTheCurrentWord() {
        return currentPathToGetTheCurrentWord;
    }
}
