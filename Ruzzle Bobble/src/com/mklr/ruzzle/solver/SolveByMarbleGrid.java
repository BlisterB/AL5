package com.mklr.ruzzle.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.mklr.collection.BinaryTree;
import com.mklr.collection.Tree;
import com.mklr.ruzzle.data.Letter;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;

public class SolveByMarbleGrid extends Solver {
    RuzzleDictionary dictionary;
    Marble[][] marblesBoard;

    private BinaryTree<String> __words = new BinaryTree<String>();


    public SolveByMarbleGrid(Board b, byte algorithmType) {
        super(algorithmType);

        this.dictionary = b.getDico();
        marblesBoard = b.getGrid();
    }

    public void solve() {
        solve(Solver.SORT_BY_WORD_LENGTH);
    }

    public void solve(byte sortType) {
        if (!wordsList.isEmpty())
            return;

        long beg = System.currentTimeMillis();
        for (int i = 0; i < marblesBoard.length; i++) {
            for (int j = 0; j < marblesBoard[i].length; j++){
                if ((algorithmType & Solver.DEPTH_FIRST_SEARCH) == Solver.DEPTH_FIRST_SEARCH) {
                    dfs(new Integer[]{i, j}, new SolutionWord(), 0,
                        dictionary.getDictionaryTree(), new LinkedList<Integer[]>());
                } else {
                    bfs(new Integer[]{i, j});
                }
            }
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
                if (child.isTerminal() && /*!containsWord(nextWord))*/ !__words.childExist(nextWord.getWord())) {
                    nextWord.endWord(path_cpy, marblesBoard);
                    wordsList.add(nextWord);
                    __words.add(nextWord.getWord(), null);
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

    private void bfs(Integer[] startMarble) {
        Queue<BFSDatas> queue = initQueue(startMarble);

        while (queue.peek() != null) {
            BFSDatas currentDatas = queue.poll();

            if (currentDatas.getCurrentPosition().isTerminal()
                    && !__words.childExist(currentDatas.getCurrentWord().getWord())) {
                currentDatas.getCurrentWord().endWord(currentDatas.getPathToGetCurrentWord(), marblesBoard);
                wordsList.add(currentDatas.getCurrentWord());
                __words.add(currentDatas.getCurrentWord().getWord(), null);
            }

            Integer[] currentPosition = currentDatas.getPositionBoard();
            Marble m = marblesBoard[currentPosition[0]][currentPosition[1]];

            for (Integer[] neighbour : m.getNeighbours()) {
                Marble neighbourMarble = marblesBoard[neighbour[0]][neighbour[1]];
                Letter l = neighbourMarble.getLetter();

                LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>(currentDatas.getPathToGetCurrentWord());
                nextPath.add(neighbour);

                if (containsNeighbour(currentDatas.getPathToGetCurrentWord(), neighbour))
                    continue;

                if (l.getLetter() == '*') {
                    for (Tree<Character> child : currentDatas.getCurrentPosition().getListOfChilds().values()) {
                        SolutionWord nextWord = new SolutionWord(currentDatas.getCurrentWord());
                        nextWord.addLetter(child.getNodeValue());

                        queue.add(new BFSDatas(child, nextWord, neighbour, nextPath));
                    }
                } else {
                    Tree<Character> child = currentDatas.getCurrentPosition().getChild(l.getLetter());
                    if (child == null)
                        continue;

                    SolutionWord nextWord = new SolutionWord(currentDatas.getCurrentWord());
                    nextWord.addLetter(neighbourMarble);

                    queue.add(new BFSDatas(child, nextWord, neighbour, nextPath));
                }
            }
        }
    }

    private Queue<BFSDatas> initQueue(Integer[] startMarble) {
        Queue<BFSDatas> queue = new LinkedList<BFSDatas>();

        Marble m = marblesBoard[startMarble[0]][startMarble[1]];

        LinkedList<Integer[]> path = new LinkedList<Integer[]>();
        path.add(startMarble);


        if (m.getLetter().getLetter() == '*') {
            for (int i = 97; i <= 122; i++) {
                char c = (char)i;

                SolutionWord sw = new SolutionWord();
                sw.addLetter(c);

                queue.add(new BFSDatas(dictionary.getDictionaryTree().getChild(c), sw, startMarble, path));
            }
        } else {
            SolutionWord sw = new SolutionWord();
            sw.addLetter(m);

            queue.add(new BFSDatas(dictionary.getDictionaryTree().getChild(m.getLetter().getLetter()), sw, startMarble, path));
        }

        return queue;
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
}
