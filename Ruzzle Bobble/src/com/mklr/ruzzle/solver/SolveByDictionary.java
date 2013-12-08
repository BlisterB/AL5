package com.mklr.ruzzle.solver;

import com.mklr.collection.Tree;
import com.mklr.ruzzle.data.RuzzleDictionary;
import com.mklr.ruzzle.engine.Board;
import com.mklr.ruzzle.engine.Marble;

import java.util.*;

/**
 *  Classe qui implémente un algorithme de résolution par Dictionnaire.
 *  Cet algorithme va parcourir le dictionnaire afin de trouver dans le
 *  plateau si les mots existent.
 *  (Cette partie sera détaillée dans chacun des deux type d'algorithmes
 *  disponible...)
 *
 *  @author Loic Runarvot
 *  @author Mehdi Khelifi
 */
public class SolveByDictionary extends Solver {

    /**
     *  Le dictionnaire des mots existant.
     */
    private final RuzzleDictionary dictionary;

    /**
     *  Plateau dans lequel rechercher les mots.
     */
    private final Marble[][] marblesBoard;

    /**
     * Cet objet est utilisé pour "mapper" les caractères telle que chaque
     * caractère possède une liste de tableau d'entier correspondant à chacune
     * des positions dans le tableau.
     *
     * Exemple :
     *   {'c' : [liste de position (i, j) dans le plateau pour le caractère c]}
     */
    private final HashMap<Character, ArrayList<Integer[]>> characterTable;



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


    /**
     * Initialisation de l'algorithme DFS.
     * Pour chaque fils de firstPosition (dans la majorité des cas, on se
     * rapporte aux 26 lettres de l'alphabet), puis pour chaque position
     * de ces lettres on lance la partie principale de l'algorithme dfs.
     * (voir fonction 'dfs()')
     *
     * @param firstPosition
     */
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

    /**
     *  Partie principale de l'algorithme DFS.
     *
     *  Au départ on teste si le mot courant peut être ajouter.
     *  On tente d'ajouter le mot (voir la fonction 'Solver.addWord')
     *
     *  Puis ensuite, pour chaque fils de la position courante dans l'arbre
     *  on teste si la caractère existe (c'est à dire la lettre même, ou la
     *  lettre joker '*') parmi les voisins.
     *  Si cela existe, on créer une nouvelle donnée, et on relance le dfs.
     *  Sinon on ne fait absolument rien.
     *
     *  @see AlgorithmsDatas
     *  @param datas
     */
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
                Character neighbourCharacter = marblesBoard[neighbour[0]][neighbour[1]].getLetter().getLetter();
                if (containsNeighbour(currentPathToGetTheWord, neighbour))
                    continue;

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



    /**
     *  Initialise la file nécessaire pour l'algorithme BFS.
     *  Pour chaque fils de la première position, puis pour chaque position
     *  de ces fils dans le plateau on ajoute dans la file un objet de donnée.
     *
     *  Cette fonction est appelé au début de l'algoritme BFS.
     *
     *  @see AlgorithmsDatas
     *  @param firstPosition
     *  @return the initialized queue
     */
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

    /**
     *  Partie principal de l'algorithme BFS.
     *
     *  Cet algorithme se déroule jusqu'à ce que la file soit vide.
     *
     *  On teste d'abord si le mot existe.
     *  Si oui, alors on l'ajoute (voir la fonction 'Solver.addWord')
     *
     *  Ensuite pour chaque fils de la position courante de l'arbre, on teste
     *  chaque voisins de la position courante dans le plateau.
     *  Si la lettre du fils existe dans un des voisins (ie. lettre courante
     *  ainsi que le joker '*'), alors on ajoute un nouvel objet de donnée
     *  dans la file.
     */
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

    /**
     * Initialise la map characterTable, utilisé pour savoir dans quelles cases
     * se trouve une lettre.
     * Une lettre se caractérise soit par sa propre lettre, soit par un joker.
     *
     * Par exemple :
     *  Position de 'c' dans le plateau : (0,1) (1,0) (3,2) (1,4)
     *  Position de '*' dans le plateau : (2,2) (3,4)
     *  Cela ajoute dans characterTable :
     *      {'c' : [(0, 1); (1, 0); (3, 2); (1, 4); (2,2); (3,4)] }
     *
     * @see Tree
     */
    private void fillCharacterTable() {
        for (int i = 0; i < marblesBoard.length; i++) {
            for (int j = 0; j < marblesBoard[i].length; j++) {
                Character c = marblesBoard[i][j].getLetter().getLetter();

                if (c.equals('*')) {
                    for (int charCode = 97; charCode < 123; charCode++) {
                        addCharacterToCharacterTable((char)charCode, new Integer[]{i, j});
                    }
                } else if (c.equals('-')) {
                    /* does nothing here */
                }
                else {
                    addCharacterToCharacterTable(c, new Integer[]{i, j});
                }
            }
        }
    }

    /**
     * Ajoute la clé c dans characterTable, lié à la liste de sa position.
     * Si la clé existe déjà, alors on ajoute la position à la liste.
     * Sinon on créer une nouvelle liste avec la position.
     *
     * @param c la clé
     * @param position la valeur à ajouter dans la liste
     */
    private void addCharacterToCharacterTable(Character c, Integer[] position) {
        if (characterTable.containsKey(c)) {
            characterTable.get(c).add(position);
        } else {
            ArrayList<Integer[]> tmp = new ArrayList<Integer[]>();
            tmp.add(position);

            characterTable.put(c, tmp);
        }
    }

    /**
     * @param path le chemin a tester
     * @param neighbour
     * @return true si le voisin existe
     */
    private boolean containsNeighbour(LinkedList<Integer[]> path, Integer[] neighbour) {
        for (Integer[] i : path) {
            if (i[0].equals(neighbour[0]) && i[1].equals(neighbour[1]))
                return true;
        }
        return false;
    }

}
