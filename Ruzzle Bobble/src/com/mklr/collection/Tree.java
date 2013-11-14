package com.mklr.collection;

import java.util.HashMap;


/**
 * The Tree class take an element...
 * It keep a node value, a list of childs and the status.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class Tree<T> implements BasicTree<T>
{
    /**
     *  Some algorithm could use this constant to determine if the current
     *  tree is non terminal (ie. finish nothing in the tree).
     *  It's the default value when creating a tree
     */
    public static final int NON_TERMINAL    = 0;
    
    /**
     *  Some algorithm could use this constant to determine if the current
     *  tree is terminal (ie. could finish something in the tree)
     */
    public static final int TERMINAL = 1;

    private T nodeValue;
    private HashMap<T, Tree<T>> listOfChilds;
    private int status;

    public Tree() {
        this(null);
    }

    public Tree(T nodeValue) {
        this(nodeValue, NON_TERMINAL, new HashMap<T, Tree<T>>());
    }

    public Tree(T nodeValue, int status) {
        this(nodeValue, status, new HashMap<T, Tree<T>>());
    }

    public Tree(T nodeValue, HashMap<T, Tree<T>> listOfChilds) {
        this(nodeValue, NON_TERMINAL, listOfChilds);
    }

    public Tree(T nodeValue, int status, HashMap<T, Tree<T>> listOfChilds) {
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
    public void add(T newValue, BasicTree<T> newTree) {
        if (newTree instanceof Tree<?>)
            listOfChilds.put(newValue, (Tree<T>)newTree); 
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
