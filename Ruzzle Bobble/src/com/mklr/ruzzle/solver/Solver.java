package com.mklr.ruzzle.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 * Classe abstraite utilisée comme template pour les deux algorithmes
 * proposés dans le projet.
 */
public abstract class Solver {

    /**
     * Constante utilisée pour affecter l'algorithme BFS au solver.
     */
    public static final byte BREADTH_FIRST_SEARCH = 0;

    /**
     * Constante utilisée pour affecter l'algorithme DFS au solver.
     */
    public static final byte DEPTH_FIRST_SEARCH = 1;



    /**
     * Constante utilisée pour trier les mots lexicographiquement.
     */
    public static final byte SORT_BY_NAME = 0;

    /**
     * Constante utilisée pour trier les mots par leur score.
     */
    public static final byte SORT_BY_SCORE = 1;

    /**
     * Constante utilisées pour trier les mots par leur taille.
     */
    public static final byte SORT_BY_WORD_LENGTH = 2;



    /**
     * Contient al type d'algorithme utilisé.
     * (ie : BFS or DFS)
     */
    protected byte algorithmType;

    /**
     * Le temps utilisé par l'algorithme pour trouver tout les mots.
     */
    protected double timer;

    /**
     * Le nombre de mots trouvé.
     */
    protected long wordCount;

    /**
     * La liste des mots trouvés par l'algorithme.
     * Ils sont par défaut trié par ordre lexicographique.
     * @see SolutionWord
     */
    protected ArrayList<SolutionWord> wordsList;


    /**
     * Arbre temporaire permettant de sauvegarder l'état des mots avant
     * la fin de l'algorihtme.
     *
     * En effet, le fait de passer par un arbre, permet de stocker les mots
     * par ordre lexicographique, et donc une plus grande rapidité pour
     * vérifier si le mot existe déjà (par rapport à la liste ci dessus).
     * (de l'ordre O(log n) pour la recherche).
     *
     * De plus, si le mot existe déjà mais que le nouveau a un plus grand sore
     * que le second, cela permet son remplacement immédiat, sans reparcourir
     * quoi que ce soit.
     * @see SolutionWord
     */
    protected TreeMap<String, SolutionWord> __tmp_wordsList;


    /**
     * Empty constructor.
     */
    protected Solver() {

    }

    /**
     * Créer un Solver selon l'algorithme donné.
     * Après sa création, la fonction '.solve()' doit être appelé par le
     * solver.
     * @param algorithmType the type of algorithm
     */
    protected Solver(byte algorithmType) {
        this.algorithmType = algorithmType;
        wordsList = new ArrayList<SolutionWord>();

        timer = 0.0;
        wordCount = 0;

        __tmp_wordsList = new TreeMap<String, SolutionWord>();
    }

    /**
     * Résoud l'algorithme.
     * Trie par défaut dans l'ordre lexicographique.
     */
    public abstract void solve();

    /**
     * Résoud l'algorithme et trie selon l'argument.
     * @param sortType
     */
    public abstract void solve(byte sortType);

/**
     * @see SolutionWord
     * @return La liste des mots.
     */
    public ArrayList<SolutionWord> getWordsList() {
        return wordsList;
    }

    /**
     * @return Le nombre de mots trouvé.
     */
    public long getWordCount() {
        return wordCount;
    }

    /**
     * @return Le temps pris par l'algorithme.
     */
    public double getTimer() {
        return timer;
    }


    /**
     * Ajoute le mot courant à l'arbre temporaire.
     * Si le mot existe déjà, mais que le mot courant a un score plus élevé,
     * alors on remplace.
     * @see SolutionWord
     * @param currentWord le mot à ajouter
     */
    protected void addWord(SolutionWord currentWord) {
        if (__tmp_wordsList.containsKey(currentWord.getWord())) {
            SolutionWord tmpWord = __tmp_wordsList.get(currentWord.getWord());
            if (tmpWord.getScore() < currentWord.getScore()) {
                __tmp_wordsList.put(currentWord.getWord(), currentWord);
            }
        } else {
            __tmp_wordsList.put(currentWord.getWord(), currentWord);
        }
    }

    /**
     * Trie les mots selon le type choisi
     * (ie. par la taille, lexicographique, ou par le score)
     * @param sortType
     */
    public void sort(byte sortType) {
        byte initialSortType = SolutionWord.SORT_TYPE;
        SolutionWord.changeSortType(sortType);
        Collections.sort(wordsList, new SolutionWord());
        SolutionWord.changeSortType(initialSortType);
    }
}
