package com.mklr.collection;

import java.util.HashMap;

import javax.management.BadAttributeValueExpException;


/**
 * The Tree class take an element...
 * It keep a node value, a list of childs and the status.
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public class Tree<T>  implements BasicTree<T>  {
    
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

    /**
     *	Create an empty tree without value, childs.
     *	The status is set on non terminal value. 
     */
    public Tree() {
        this(null);
    }

    /**
     * Create a tree with a value.
     * It has no childs, and the status is set on non terminal.
     * @param nodeValue value of the new tree
     */
    public Tree(T nodeValue) {
        this(nodeValue, NON_TERMINAL, null);
    }

    /**
     * Create a tree with a value, and a status.
     * It has no childs.
     * @param nodeValue value of the new tree
     * @param status status of the current tree
     */
    public Tree(T nodeValue, int status) {
        this(nodeValue, status, null);
    }

    /**
     * Create a tree with a value, and a list of childs.
     * The status is set as non terminal.
     * @param nodeValue value of the new tree
     * @param listOfChilds childs of the new tree
     */
    public Tree(T nodeValue, HashMap<T, Tree<T>> listOfChilds) {
        this(nodeValue, NON_TERMINAL, listOfChilds);
    }

    /**
     * Create a tree with a value, a status and a list of childs.
     * @param nodeValue value of the new tree
     * @param status status of the new tree
     * @param listOfChilds childs of the new tree
     */
    public Tree(T nodeValue, int status, HashMap<T, Tree<T>> listOfChilds)  {
    	try {
    		testStatus(status);
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("STATUS VALUE WILL BE SET AS `NON_TERMINAL`");
    		this.status = NON_TERMINAL;
    	}
    	
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

    /**
     * It test the status values, to see if it correspond to one of 
     * the available constants
     * @param status
     * @throws BadAttributeValueExpException
     */
    private void testStatus(int status) 
    		throws BadAttributeValueExpException {  
    	if (status != TERMINAL && status != NON_TERMINAL)
    		throw new BadAttributeValueExpException(status);
    }
}
