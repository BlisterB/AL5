package com.mklr.collection;

/**
 * Interface which gives method usefull for trees
 * @author Loic Runarvot
 * @author Mehdi Khelifi
 */
public interface BasicTree<T>
{

    /**
     * Add the next tree to the current, associated with the value given
     * @param newValue element associated to the tree
     * @param newTree tree to add
     */
    public void add(T newValue, BasicTree<T> newTree);
    

    /**
     * Remove the tree associated to the value given in argument
     * @param valueToRemove
     */
    public void remove(T valueToRemove);
   

    /**
     * Search in the child list if the tree associated with the value given
     * exist.
     * @param valueToSearch value associated to the tree
     * @return true if the child exists
     */
    public boolean childExist(T valueToSearch);

    /**
     * Return the child associated to the given element.
     * @param neededChild element searched
     * @return the child
     */
    public BasicTree<T> getChild(T neededChild);
}
