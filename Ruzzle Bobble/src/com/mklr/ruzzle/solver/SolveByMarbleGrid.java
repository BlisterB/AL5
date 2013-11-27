package com.mklr.ruzzle.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import com.mklr.collection.BinaryTree;
import com.mklr.collection.Tree;
import com.mklr.ruzzle.data.Letter;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;

public class SolveByMarbleGrid extends Solver {
    RuzzleDictionary dictionary;
    Marble[][] marblesBoard;
    ArrayList<SolutionWord> wordsList;

    private double timer;
    private long wordCount;

    private byte algorithmType;

    private BinaryTree<String> __words = new BinaryTree<String>();


    public SolveByMarbleGrid(Board b, byte algorithmType) {
        this.dictionary = b.getDico();
        marblesBoard = b.getGrid();
        wordsList = new ArrayList<SolutionWord>();

        timer = 0.0;
        wordCount = 0;

        this.algorithmType = algorithmType;
    }

    public void solve() {
        solve(Solver.SORT_BY_WORD_LENGTH);
    }

    public void solve(byte sortType) {
        if (!wordsList.isEmpty())
            return;

        long beg = System.currentTimeMillis();
        if ((algorithmType & Solver.DEPTH_FIRST_SEARCH) == Solver.DEPTH_FIRST_SEARCH) {
            for (int i = 0; i < marblesBoard.length; i++) {
                for (int j = 0; j < marblesBoard[i].length; j++){
                    dfs(new Integer[]{i, j}, new SolutionWord(), 0,
                            dictionary.getDictionaryTree(), new LinkedList<Integer[]>());
                }
            }
        }   else {
            bfs();
        }
        long end = System.currentTimeMillis();

        timer = ((double)end - (double)beg)/1000.0;
        wordCount = wordsList.size();

        sort(sortType);
    }

    public void sort(byte sortType) {
        byte initialSortType = SolutionWord.SORT_TYPE;
        SolutionWord.changeSortType(sortType);
        Collections.sort(wordsList, new SolutionWord());
        SolutionWord.changeSortType(initialSortType);
    }

    public long getWordCount() {
        return wordCount;
    }

    public double getTimer() {
        return timer;
    }


    private void dfs(Integer[] marbleCoo, SolutionWord currentWord, 
            int wordLength, Tree<Character> position, LinkedList<Integer[]> path) {
        if (wordLength > dictionary.getMaxLength())
            return;

        SolutionWord nextWord;
        Marble m = marblesBoard[marbleCoo[0]][marbleCoo[1]];                       // Coût 1
        Letter l = m.getLetter();                                                  // Coût 1
        
        LinkedList<Integer[]> path_cpy = new LinkedList<Integer[]>(path);                  // Coût path_cpy size.
                                                                                   // Voir si CLONE existe pour LinkedList...
        path_cpy.addFirst(marbleCoo);

        if (l.getLetter() == '*') {
            for (Tree<Character> child : position.getListOfChilds().values()) {
                nextWord = new SolutionWord(currentWord);                  //Coût correspondant à la copie. (voir CLONE)
                nextWord.addLetter(child.getNodeValue());             //On doit parcourir la liste A CHAQUE FOIS.
                                                                           //Utiliser une HashMap à la place ?
                if (child.isTerminal() && !containsWord(nextWord)) {
                    nextWord.endWord(path_cpy, marblesBoard);
                    wordsList.add(nextWord);
                }


                for (Integer[] neighbours : m.getNeighbours()) {
                    //Coût constant.
                    if (!containsNeighbour(path_cpy, neighbours)) {
                        dfs(neighbours, nextWord, 
                                wordLength + 1, child, path_cpy);
                    }
                }
            }
        } else {
            nextWord = new SolutionWord(currentWord);                         //Coût correspondant à la copie. (Voir si CLONE
                                                                                // n'est pas une meilleure idée....)
            Tree<Character> child = position.getChild(l.getLetter());         //Coût dépendant du nombre de fils (voir HashMap.get(E))
            if (child == null)
                return;

            nextWord.addLetter(m);                                           //Coût 1

            //if (child.isTerminal() && !containsWord(nextWord)) {
            if (child.isTerminal() /*&& !containsWord(curWord)*/ && !__words.childExist(nextWord.getWord())) {

                    //containsWord() => Vérifie TOUTE LA LISTE.
            //Créer un avl pour vérifier si les mots existent ?
            //Bien que long à mettre en place, il permettra
            //De chercher les mots bien plus rapidement que une Arraylist. Mais pour afficher les mots à un utilisateur,
            //il est bien plus pratique d'utiliser une liste.
            //Implémenter les deux types alors ? Et trouver un moyen d'afficher correctement les AVL pour un utilisateur ?
            //Ou utiliser simplement l'AVL pour les STRING et non les SOLUTIONWORD ?

                //En tout cas, dans la fonction containsWord : ON PARCOURT TROP DE FOIS LA LISTE.
                //Si le mot N'EXISTE PAS (cas majoritaire dans un Ruzzle) ON DOIT LA PARCOURIR EN ENTIERE....
                nextWord.endWord(path_cpy, marblesBoard);
                wordsList.add(nextWord);
                __words.add(nextWord.getWord(), null);
            }

            for (Integer[] neighbours : m.getNeighbours()) {
                //Le coût est constant : de la taille de path_cpy. Qui ne peut pas être plus grande que maxWordLength.
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
