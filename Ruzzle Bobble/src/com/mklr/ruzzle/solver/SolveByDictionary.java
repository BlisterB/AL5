package com.mklr.ruzzle.solver;

import com.mklr.collection.Tree;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class SolveByDictionary extends Solver {
    private RuzzleDictionary dictionary;
    private Marble[][] marblesBoard;

    private ArrayList<SolutionWord> wordsList;
    private HashMap<Character, ArrayList<Integer[]>> characterTable;

    public SolveByDictionary(Board b) {
        dictionary = b.getDico();
        marblesBoard = b.getBoard();

        wordsList = new ArrayList<SolutionWord>();
        characterTable = new HashMap<Character, ArrayList<Integer[]>>();
    }

    public void solve() {
        solve(Solver.SORT_BY_WORD_LENGTH);
    }

    public void solve(byte sortType) {
        fillCharacterTable();
        dfs(dictionary.getDictionaryTree(), new SolutionWord(), 0, null);

        sort(sortType);
    }

    public void sort(byte sortType) {
        byte initialSortType = SolutionWord.SORT_TYPE;
        SolutionWord.changeSortType(sortType);
        Collections.sort(wordsList, new SolutionWord());
        SolutionWord.changeSortType(initialSortType);
    }

    public ArrayList<SolutionWord> getWordsList() {
        return wordsList;
    }

    private void dfs(Tree<Character> curPos, SolutionWord curWord, int wordLength, LinkedList<Integer[]> path) {
        if (path == null) {
            for (Tree<Character> child : curPos.getListOfChilds().values()) {
                ArrayList<Integer[]> arrayOfPosInGameBoard = characterTable.get(child.getNodeValue());

                if (arrayOfPosInGameBoard == null)
                    continue;

                for (Integer[] posInGameBoard : arrayOfPosInGameBoard) {
                    SolutionWord nextWord = new SolutionWord(curWord);
                    LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>();

                    nextWord.addLetter(marblesBoard[posInGameBoard[0]][posInGameBoard[1]]);
                    nextPath.addFirst(posInGameBoard);

                    dfs(child, nextWord, wordLength + 1, nextPath);
                }

                arrayOfPosInGameBoard = characterTable.get('*');
                if (arrayOfPosInGameBoard == null)
                    continue;

                for (Integer[] posInGameBoard : arrayOfPosInGameBoard) {
                    SolutionWord nextWord = new SolutionWord(curWord);
                    LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>();

                    nextWord.addLetter(child.getNodeValue());
                    nextPath.addFirst(posInGameBoard);

                    dfs(child, nextWord, wordLength + 1, nextPath);
                }
            }
        } else {
            if (curPos.isTerminal() && !containsWord(curWord)) {
                curWord.endWord(path, marblesBoard);
                wordsList.add(curWord);
            }

            Integer[] lastPos = path.getFirst();
            Marble m = marblesBoard[lastPos[0]][lastPos[1]];

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

                        dfs(child, nextWord, wordLength + 1, nextPath);
                    }

                    if (c.equals(neighbourCharacter)) {
                        SolutionWord nextWord = new SolutionWord(curWord);
                        LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>(path);

                        nextWord.addLetter(marblesBoard[neighbour[0]][neighbour[1]]);
                        nextPath.addFirst(neighbour);

                        dfs(child, nextWord, wordLength + 1, nextPath);
                    }
                }
            }
                        /*
            for (Integer[] neighbour : m.getNeighbours()) {
                Character c = marblesBoard[neighbour[0]][neighbour[1]].getLetter().getLetter();

                if (containsNeighbour(path, neighbour))
                    continue;

                if (c.equals('*')) {
                    for (Tree<Character> child : curPos.getListOfChilds().values()) {
                        SolutionWord nextWord = new SolutionWord(curWord);
                        LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>(path);

                        nextWord.addLetter(child.getNodeValue());
                        nextPath.addFirst(neighbour);

                        dfs(child, nextWord, wordLength + 1, nextPath);
                    }
                } else {
                    for (Tree<Character> child : curPos.getListOfChilds().values())  {
                        if (c.equals(child.getNodeValue())) {
                            SolutionWord nextWord = new SolutionWord(curWord);
                            LinkedList<Integer[]> nextPath = new LinkedList<Integer[]>(path);

                            nextWord.addLetter(marblesBoard[neighbour[0]][neighbour[1]]);
                            nextPath.addFirst(neighbour);

                            dfs(child, nextWord, wordLength + 1, nextPath);
                        }
                    }
                }
            }
            */
        }
    }

    private void bfs() {

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
