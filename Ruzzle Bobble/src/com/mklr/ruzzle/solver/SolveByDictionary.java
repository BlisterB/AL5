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
        long beg = System.currentTimeMillis();

        fillCharacterTable();
        if ((algorithmType & Solver.DEPTH_FIRST_SEARCH) == Solver.DEPTH_FIRST_SEARCH) {
            dfs(dictionary.getDictionaryTree(), new SolutionWord(), 0, null, null);
        } else {
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

    private void dfs(Tree<Character> curPos, SolutionWord curWord, int wordLength, LinkedList<Integer[]> path, Integer[] curPosInGrid) {
        if (path == null) {
            for (Tree<Character> child : curPos.getListOfChilds().values()) {
                ArrayList<Integer[]> arrayOfPosInGameBoard = characterTable.get('*');
                if (arrayOfPosInGameBoard == null)
                    continue;

                for (Integer[] posInGameBoard : arrayOfPosInGameBoard) {
                    SolutionWord nextWord = new SolutionWord(curWord);
                    LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>();

                    nextWord.addLetter(child.getNodeValue());
                    nextPath.addFirst(posInGameBoard);

                    dfs(child, nextWord, wordLength + 1, nextPath, posInGameBoard);
                }

                arrayOfPosInGameBoard = characterTable.get(child.getNodeValue());
                if (arrayOfPosInGameBoard == null)
                    continue;

                for (Integer[] posInGameBoard : arrayOfPosInGameBoard) {
                    SolutionWord nextWord = new SolutionWord(curWord);
                    LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>();

                    nextWord.addLetter(marblesBoard[posInGameBoard[0]][posInGameBoard[1]]);
                    nextPath.addFirst(posInGameBoard);

                    dfs(child, nextWord, wordLength + 1, nextPath, posInGameBoard);
                }

            }
        } else {
            if (curPos.isTerminal() /*&& !containsWord(curWord)*/ && !__words.childExist(curWord.getWord())) {
                curWord.endWord(path, marblesBoard);
                wordsList.add(curWord);
                __words.add(curWord.getWord(), null);
            }

            Marble m = marblesBoard[curPosInGrid[0]][curPosInGrid[1]];

            for (Tree<Character> child : curPos.getListOfChilds().values()) {
                Character c = child.getNodeValue();

                for (Integer[] neighbour : m.getNeighbours()) {
                    if (containsNeighbour(path, neighbour))
                        continue;

                    Character neighbourCharacter = marblesBoard[neighbour[0]][neighbour[1]].getLetter().getLetter();

                    if (neighbourCharacter.equals('*')) {
                        SolutionWord nextWord = new SolutionWord(curWord);
                        LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>(path);

                        nextWord.addLetter(child.getNodeValue());
                        nextPath.addFirst(neighbour);

                        dfs(child, nextWord, wordLength + 1, nextPath, neighbour);
                    }

                    if (c.equals(neighbourCharacter)) {
                        SolutionWord nextWord = new SolutionWord(curWord);
                        LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>(path);

                        nextWord.addLetter(marblesBoard[neighbour[0]][neighbour[1]]);
                        nextPath.addFirst(neighbour);

                        dfs(child, nextWord, wordLength + 1, nextPath, neighbour);
                    }
                }
            }
        }
    }

    private void bfs() {
        Queue<BFSDatas> queue = new LinkedList<BFSDatas>();

        queue.add(new BFSDatas(dictionary.getDictionaryTree(), new SolutionWord(), null, new LinkedList<Integer[]>()));
        while(queue.peek() != null) {
            BFSDatas bfsd = queue.poll();

            if (bfsd.getCurrentWord().getLength() > 1
                    && bfsd.getCurrentPosition().isTerminal()
                    && !__words.childExist(bfsd.getCurrentWord().getWord())) {
                bfsd.getCurrentWord().endWord(bfsd.getPathToGetCurrentWord(), marblesBoard);
                wordsList.add(bfsd.getCurrentWord());
                __words.add(bfsd.getCurrentWord().getWord(), null);
            }

            if (bfsd.getPositionBoard() == null) {
                for (Tree<Character> child : bfsd.getCurrentPosition().getListOfChilds().values()) {
                    ArrayList<Integer[]> posOfCharacterInBoard = characterTable.get('*');

                    if (posOfCharacterInBoard == null)
                        continue;

                    for (Integer[] boardPosition : posOfCharacterInBoard) {
                        BFSDatas nextDatas = new BFSDatas(bfsd);

                        nextDatas.getCurrentWord().addLetter(child.getNodeValue());
                        nextDatas.getPathToGetCurrentWord().addFirst(boardPosition);
                        nextDatas.setPositionBoard(boardPosition);
                        nextDatas.setCurrentPosition(child);

                        queue.add(nextDatas);
                    }

                    posOfCharacterInBoard = characterTable.get(child.getNodeValue());

                    if (posOfCharacterInBoard == null)
                        continue;

                    for (Integer[] boardPosition : posOfCharacterInBoard) {
                        BFSDatas nextDatas = new BFSDatas(bfsd);

                        nextDatas.getCurrentWord().addLetter(marblesBoard[boardPosition[0]][boardPosition[1]]);
                        nextDatas.getPathToGetCurrentWord().addFirst(boardPosition);
                        nextDatas.setPositionBoard(boardPosition);
                        nextDatas.setCurrentPosition(child);

                        queue.add(nextDatas);
                    }
                }
            } else {
                Integer[] pos = bfsd.getPositionBoard();
                Marble m = marblesBoard[pos[0]][pos[1]];

                for (Tree<Character> child : bfsd.getCurrentPosition().getListOfChilds().values()) {
                    Character c = child.getNodeValue();

                    for (Integer[] neighbour : m.getNeighbours()) {
                        if (containsNeighbour(bfsd.getPathToGetCurrentWord(), neighbour))
                            continue;

                        Character neighbourCharacter = marblesBoard[neighbour[0]][neighbour[1]].getLetter().getLetter();

                        if (neighbourCharacter.equals('*')) {
                            BFSDatas nextData = new BFSDatas(bfsd);

                            nextData.getCurrentWord().addLetter(child.getNodeValue());
                            nextData.getPathToGetCurrentWord().addFirst(neighbour);
                            nextData.setPositionBoard(neighbour);
                            nextData.setCurrentPosition(child);

                            queue.add(nextData);
                        }

                        if (c.equals(neighbourCharacter)) {
                            BFSDatas nextData = new BFSDatas(bfsd);

                            nextData.getCurrentWord().addLetter(marblesBoard[neighbour[0]][neighbour[1]]);
                            nextData.getPathToGetCurrentWord().addFirst(neighbour);
                            nextData.setPositionBoard(neighbour);
                            nextData.setCurrentPosition(child);

                            queue.add(nextData);
                        }
                    }
                }
            }
        }
    }

    private void fillCharacterTable() {
        for (int i = 0; i < marblesBoard.length; i++) {
            for (int j = 0; j < marblesBoard[i].length; j++) {
                Character c = marblesBoard[i][j].getLetter().getLetter();

                if (characterTable.containsKey(c)) {
                    characterTable.get(c).add(new Integer[]{i, j});
                } else {
                    ArrayList<Integer[]> tmp = new ArrayList<Integer[]>();
                    tmp.add(new Integer[]{i, j});
                    characterTable.put(c, tmp);
                }
            }
        }
    }

    private boolean containsWord(SolutionWord word) {
        String _w = word.getWord();
        for (SolutionWord sw : wordsList) {
            if (_w.equals(sw.getWord()))
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
