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
        if (!wordsList.isEmpty()) {
            sort(sortType);
            return;
        }

        long beg = System.currentTimeMillis();
        for (int i = 0; i < marblesBoard.length; i++) {
            for (int j = 0; j < marblesBoard[i].length; j++){
                if ((algorithmType & Solver.DEPTH_FIRST_SEARCH) == Solver.DEPTH_FIRST_SEARCH) {
                    dfs(new AlgorithmsDatas(dictionary.getDictionaryTree(), new SolutionWord(),
                            new Integer[]{i, j}, new LinkedList<Integer[]>()));
                } else {
                    bfs(new Integer[]{i, j});
                }
            }
        }
        wordsList = new ArrayList<SolutionWord>(__tmp_wordsList.values());
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

    private void dfs(AlgorithmsDatas datas) {
        if (datas.getCurrentWord().getLength() > dictionary.getMaxLength())
            return;

        Tree<Character> currentPositionInTree =
                datas.getCurrentPositionInTree();
        SolutionWord currentWord =
                datas.getCurrentWord();
        LinkedList<Integer[]> currentPathToGetTheWord =
                datas.getCurrentPathToGetTheCurrentWord();
        Integer[] currentPositionInBoard =
                datas.getCurrentPositionInBoard();

        if (currentPositionInTree.isTerminal()) {
            currentWord.endWord(currentPathToGetTheWord, marblesBoard);
            addWord(currentWord);
        }

        Marble currentMarble =
                marblesBoard[currentPositionInBoard[0]][currentPositionInBoard[1]];
        Letter currentLetterInMarble = currentMarble.getLetter();

        LinkedList<Integer[]> nextPath =
                new LinkedList<Integer[]>(currentPathToGetTheWord);
        nextPath.add(currentPositionInBoard);

        if (currentLetterInMarble.getLetter() == '*') {
            for (Tree<Character> child : currentPositionInTree.getListOfChilds().values()) {
                SolutionWord nextWord = new SolutionWord(currentWord);
                nextWord.addLetter(child.getNodeValue());

                for (Integer[] neighbour : currentMarble.getNeighbours()) {
                    if (!containsNeighbour(nextPath, neighbour)) {
                        dfs(new AlgorithmsDatas(child, nextWord, neighbour, nextPath));
                    }
                }
            }
        } else {
            Tree<Character> child =
                    currentPositionInTree.getChild(currentLetterInMarble.getLetter());
            if (child == null)
                return;

            SolutionWord nextWord = new SolutionWord(currentWord);
            nextWord.addLetter(currentMarble);

            for (Integer[] neighbour : currentMarble.getNeighbours()) {
                if (!containsNeighbour(nextPath, neighbour)) {
                    dfs(new AlgorithmsDatas(child, nextWord, neighbour, nextPath));
                }
            }

        }
    }


    private void bfs(Integer[] startMarble) {
        Queue<AlgorithmsDatas> queue = initQueue(startMarble);

        while (queue.peek() != null) {
            AlgorithmsDatas currentDatas = queue.poll();

            Tree<Character> currentPositionInTree = currentDatas.getCurrentPositionInTree();
            SolutionWord currentWord = currentDatas.getCurrentWord();
            LinkedList<Integer[]> currentPathToGetTheWord = currentDatas.getCurrentPathToGetTheCurrentWord();
            Integer[] currentPositionInBoard = currentDatas.getCurrentPositionInBoard();

            if (currentPositionInTree.isTerminal()) {
                currentWord.endWord(currentPathToGetTheWord, marblesBoard);
                addWord(currentWord);
            }
;
            Marble m = marblesBoard[currentPositionInBoard[0]][currentPositionInBoard[1]];

            for (Integer[] neighbour : m.getNeighbours()) {
                Marble neighbourMarble = marblesBoard[neighbour[0]][neighbour[1]];
                Letter l = neighbourMarble.getLetter();

                LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>(currentPathToGetTheWord);
                nextPath.add(neighbour);

                if (containsNeighbour(currentPathToGetTheWord, neighbour))
                    continue;

                if (l.getLetter() == '*') {
                    for (Tree<Character> child : currentPositionInTree.getListOfChilds().values()) {
                        SolutionWord nextWord = new SolutionWord(currentDatas.getCurrentWord());
                        nextWord.addLetter(child.getNodeValue());

                        queue.add(new AlgorithmsDatas(child, nextWord, neighbour, nextPath));
                    }
                } else {
                    Tree<Character> child = currentPositionInTree.getChild(l.getLetter());
                    if (child == null)
                        continue;

                    SolutionWord nextWord = new SolutionWord(currentDatas.getCurrentWord());
                    nextWord.addLetter(neighbourMarble);

                    queue.add(new AlgorithmsDatas(child, nextWord, neighbour, nextPath));
                }
            }
        }
    }

    private Queue<AlgorithmsDatas> initQueue(Integer[] startMarble) {
        Queue<AlgorithmsDatas> queue = new LinkedList<AlgorithmsDatas>();

        Marble m = marblesBoard[startMarble[0]][startMarble[1]];

        LinkedList<Integer[]> path = new LinkedList<Integer[]>();
        path.add(startMarble);

        if (m.getLetter().getLetter() == '*') {
            for (int i = 97; i <= 122; i++) {
                char c = (char)i;

                SolutionWord sw = new SolutionWord();
                sw.addLetter(c);

                queue.add(new AlgorithmsDatas(dictionary.getDictionaryTree().getChild(c), sw, startMarble, path));
            }
        } else {
            SolutionWord sw = new SolutionWord();
            sw.addLetter(m);

            queue.add(new AlgorithmsDatas(dictionary.getDictionaryTree().getChild(m.getLetter().getLetter()), sw, startMarble, path));
        }

        return queue;
    }

    private boolean containsNeighbour(LinkedList<Integer[]> path, Integer[] neighbour) {
    	for (Integer[] i : path) {
    		if (i[0].equals(neighbour[0]) && i[1].equals(neighbour[1]))
    			return true;
    	}
    	return false;
    }
}
