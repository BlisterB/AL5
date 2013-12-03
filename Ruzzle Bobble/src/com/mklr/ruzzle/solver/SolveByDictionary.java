package com.mklr.ruzzle.solver;

import com.mklr.collection.BinaryTree;
import com.mklr.collection.Tree;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;

import java.util.*;

public class SolveByDictionary extends Solver {
    private RuzzleDictionary dictionary;
    private Marble[][] marblesBoard;


    private HashMap<Character, ArrayList<Integer[]>> characterTable;
    private BinaryTree<String> __words = new BinaryTree<String>();

    public SolveByDictionary(Board b, byte algorithmType) {
        super(algorithmType);

        dictionary = b.getDico();
        marblesBoard = b.getBoard();

        wordsList = new ArrayList<SolutionWord>();
        characterTable = new HashMap<Character, ArrayList<Integer[]>>();
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
        fillCharacterTable();
        if ((algorithmType & Solver.DEPTH_FIRST_SEARCH) == Solver.DEPTH_FIRST_SEARCH) {
            initDfs(dictionary.getDictionaryTree());
        } else {
            bfs();
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

    private void initDfs(Tree<Character> firstPosition) {
        for (Tree<Character> child : firstPosition.getListOfChilds().values()) {
            ArrayList<Integer[]> positionOfEachCharacter = characterTable.get(child.getNodeValue());
            if (positionOfEachCharacter == null)
                continue;

            for (Integer[] positionInGameBoard : positionOfEachCharacter) {
                SolutionWord newWord = new SolutionWord();
                newWord.addLetter(child.getNodeValue());

                LinkedList<Integer[]> newPath = new LinkedList<Integer[]>();
                newPath.addFirst(positionInGameBoard);

                dfs(new AlgorithmsDatas(child, newWord, positionInGameBoard, newPath));
            }
        }
    }

    private void dfs(AlgorithmsDatas datas) {
        Tree<Character> currentPositionInTree = datas.getCurrentPositionInTree();
        SolutionWord currentWord = datas.getCurrentWord();
        LinkedList<Integer[]> currentPathToGetTheWord = datas.getCurrentPathToGetTheCurrentWord();
        Integer[] currentPositionInBoard = datas.getCurrentPositionInBoard();


        if (currentPositionInTree.isTerminal()) {
            currentWord.endWord(currentPathToGetTheWord, marblesBoard);
            addWord(currentWord);
        }

        Marble m = marblesBoard[currentPositionInBoard[0]][currentPositionInBoard[1]];

        for (Tree<Character> child : currentPositionInTree.getListOfChilds().values()) {
            Character c = child.getNodeValue();

            for (Integer[] neighbour : m.getNeighbours()) {
                if (containsNeighbour(currentPathToGetTheWord, neighbour))
                    continue;

                Character neighbourCharacter = marblesBoard[neighbour[0]][neighbour[1]].getLetter().getLetter();

                if (neighbourCharacter.equals('*') || neighbourCharacter.equals(c)) {
                    SolutionWord nextWord = new SolutionWord(currentWord);
                    nextWord.addLetter(child.getNodeValue());

                    LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>(currentPathToGetTheWord);
                    nextPath.addFirst(neighbour);

                    dfs(new AlgorithmsDatas(child, nextWord, neighbour, nextPath));
                }
            }
        }
    }

    private void bfs() {
        Queue<AlgorithmsDatas> queue = initQueue(dictionary.getDictionaryTree());

        while(queue.peek() != null) {
            AlgorithmsDatas datas = queue.poll();

            Tree<Character> currentPositionInTree = datas.getCurrentPositionInTree();
            SolutionWord currentWord = datas.getCurrentWord();
            LinkedList<Integer[]> currentPathToGetTheWord = datas.getCurrentPathToGetTheCurrentWord();
            Integer[] currentPositionInBoard = datas.getCurrentPositionInBoard();

            if (currentWord.getLength() > 1 && currentPositionInTree.isTerminal()) {
                currentWord.endWord(currentPathToGetTheWord, marblesBoard);
                addWord(currentWord);
            }


            Integer[] pos = datas.getCurrentPositionInBoard();
            Marble m = marblesBoard[pos[0]][pos[1]];

            for (Tree<Character> child : currentPositionInTree.getListOfChilds().values()) {
                Character c = child.getNodeValue();

                for (Integer[] neighbour : m.getNeighbours()) {
                    if (containsNeighbour(currentPathToGetTheWord, neighbour))
                            continue;

                    Character neighbourCharacter = marblesBoard[neighbour[0]][neighbour[1]].getLetter().getLetter();

                    if (neighbourCharacter.equals('*') || neighbourCharacter.equals(c)) {
                        SolutionWord nextWord = new SolutionWord(currentWord);
                        nextWord.addLetter(child.getNodeValue());

                        LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>(currentPathToGetTheWord);
                        nextPath.add(neighbour);

                        queue.add(new AlgorithmsDatas(child, nextWord, neighbour, nextPath));
                    }
                }
            }
        }
    }

    private Queue<AlgorithmsDatas> initQueue(Tree<Character> firstPosition) {
        Queue<AlgorithmsDatas> queue = new LinkedList<AlgorithmsDatas>();

        for (Tree<Character> child : firstPosition.getListOfChilds().values()) {
            ArrayList<Integer[]> positionsOfEachCurrentCharacter = characterTable.get(child.getNodeValue());
            if (positionsOfEachCurrentCharacter == null)
                continue;

            for (Integer[] positionInBoard : positionsOfEachCurrentCharacter) {
                SolutionWord newWord = new SolutionWord();
                newWord.addLetter(child.getNodeValue());

                LinkedList<Integer[]> newPath = new LinkedList<Integer[]>();
                newPath.addFirst(positionInBoard);

                queue.add(new AlgorithmsDatas(child, newWord, positionInBoard, newPath));
            }

        }

        return queue;
    }

    private void fillCharacterTable() {
        for (int i = 0; i < marblesBoard.length; i++) {
            for (int j = 0; j < marblesBoard[i].length; j++) {
                Character c = marblesBoard[i][j].getLetter().getLetter();

                if (c.equals('*')) {
                    for (int charCode = 97; charCode < 123; charCode++) {
                        addCharacterToCharacterTable((char)charCode, new Integer[]{i, j});
                    }
                    continue;
                } else {
                    addCharacterToCharacterTable(c, new Integer[]{i, j});
                }
            }
        }
    }

    private void addCharacterToCharacterTable(Character c, Integer[] position) {
        if (characterTable.containsKey(c)) {
            characterTable.get(c).add(position);
        } else {
            ArrayList<Integer[]> tmp = new ArrayList<Integer[]>();
            tmp.add(position);

            characterTable.put(c, tmp);
        }
    }

    private boolean containsNeighbour(LinkedList<Integer[]> path, Integer[] neighbour) {
        for (Integer[] i : path) {
            if (i[0].equals(neighbour[0]) && i[1].equals(neighbour[1]))
                return true;
        }
        return false;
    }

}
