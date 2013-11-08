package com.mklr.collection;

import java.util.HashMap;

public class Tree<T> implements BasicTree<T>
{
    private T nodeValue;
    private HashMap<T, Tree<T>> listOfChilds;

    Tree() {
        this(null);
    }

    Tree(T nodeValue) {
        this(nodeValue, new HashMap<T, Tree<T>>());
    }

    Tree(T nodeValue, HashMap<T, Tree<T>> listOfChilds) {
        this.nodeValue = nodeValue;
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
