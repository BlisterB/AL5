package com.mklr.collection;

import java.util.HashMap;

import javax.management.BadAttributeValueExpException;


/**
 * La classe Tree prend un élément...
 * Elle garde en mémoire une valeur de noeud, une liste d'enfant
 * ainsi qu'un status détérminant la qualité d'un noeud.
 * (Terminal ou Non Terminal).
 *
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class Tree<T>  implements BasicTree<T>  {
    
	/**
     *  Les algorithmes utilisent cette constante pour déterminer si le noeud
     *  courant se trouve être un état NON TERMINAL (c'est à dire que le
     *  parcours effectué dans l'arbre ne correspond pas à un mot du
     *  dictionnaire).
     *  C'est la valeur par défaut lors de la création d'un noeud.
     */
    public static final int NON_TERMINAL = 0;
    
    /**
     *  Les algorithmes utilisent cette constante pour déterminer si le noeud
     *  courant se trouve être un été TERMINAL (c'est à dire que le parcours
     *  effectué dans l'arbre correspond à un mot du dictionnaire)/
     */
    public static final int TERMINAL = 1;


    private T nodeValue;
    private HashMap<T, Tree<T>> listOfChilds;
    private int status;


    /**
     *  Créer un arbre vide, ne comportant aucune valeur, aucun fils
     *  et le status correspondant à NON_TERMINAL.
     */
    public Tree() {
        this(null);
    }

    /**
     * Créer un arbre comportant une valeur.
     * Il n'a pour le moment pas de fils, et un status correspondant à la
     * constant NON_TERMINAL.
     * @param nodeValue value of the new tree
     */
    public Tree(T nodeValue) {
        this(nodeValue, NON_TERMINAL, null);
    }

    /**
     * Créer un arbre comportant une valeur.
     * Il comporte le status donné en paramètre.
     * Il n'a pas de fils.
     * @param nodeValue value of the new tree
     * @param status status of the current tree
     */
    public Tree(T nodeValue, int status) {
        this(nodeValue, status, null);
    }

    /**
     * Créer un arbre comportant une valeur et une liste de fils.
     * Il a le status NON_TERMINAL.
     * @param nodeValue value of the new tree
     * @param listOfChilds childs of the new tree
     */
    public Tree(T nodeValue, HashMap<T, Tree<T>> listOfChilds) {
        this(nodeValue, NON_TERMINAL, listOfChilds);
    }

    /**
     * Créer un arbre comportant la valeur, le status et la liste
     * des fils donnés en paramètre.
     * @param nodeValue value of the new tree
     * @param status status of the new tree
     * @param listOfChilds childs of the new tree
     */
    public Tree(T nodeValue, int status, HashMap<T, Tree<T>> listOfChilds)  {
        if (listOfChilds == null) {
    		listOfChilds = new HashMap<T, Tree<T>>();
    	}
    	
        this.nodeValue = nodeValue;
        this.status = status;
        this.listOfChilds = listOfChilds;
    }

    /**
     * @return the nodeValue
     */
    public T getNodeValue() {
        return nodeValue;
    }

    /**
     * @param nodeValue the nodeValue to set
     */
    public void setNodeValue(T nodeValue) {
        this.nodeValue = nodeValue;
    }

    /**
     * @return the listOfChilds
     */
    public HashMap<T, Tree<T>> getListOfChilds() {
        return listOfChilds;
    }

    /**
     * @param listOfChilds the listOfChilds to set
     */
    public void setListOfChilds(HashMap<T, Tree<T>> listOfChilds) {
        this.listOfChilds = listOfChilds;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return true if the current status is terminal
     */
    public boolean isTerminal() {
        return status == TERMINAL;
    }

    @Override
    public void add(T newValue) {
        listOfChilds.put(newValue, new Tree<T>(newValue));
    }

    @Override
    public void remove(T valueToRemove) {
        listOfChilds.remove(valueToRemove);
    }

    @Override
    public boolean childExist(T valueToSearch) {
        return listOfChilds.containsKey(valueToSearch);
    }

    @Override
    public Tree<T> getChild(T neededChild) {
        return listOfChilds.get(neededChild);
    }
}
