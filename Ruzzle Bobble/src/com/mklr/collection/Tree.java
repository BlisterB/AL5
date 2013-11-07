package com.mklr.collection;

import java.util.ArrayList;

public class Tree<T> implements BasicTree<T>
{
    private T nodeValue;
    private ArrayList<Tree<T>> listOfChilds;

    Tree() {
        this(null);
    }

    Tree(T nodeValue) {
        this.nodeValue = nodeValue;
        listOfChilds = new ArrayList<Tree<T>>();
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
    public ArrayList<Tree<T>> getListOfChilds() {
        return listOfChilds;
    }

    /**
     * @param listOfChilds the listOfChilds to set
     */
    public void setListOfChilds(ArrayList<Tree<T>> listOfChilds) {
        this.listOfChilds = listOfChilds;
    }

    @Override
    public void add(T newValue, BasicTree<T> newTree) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(T valueToRemove) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean search(T valueToSearch) {
        // TODO Auto-generated method stub
        return false;
    }

}
