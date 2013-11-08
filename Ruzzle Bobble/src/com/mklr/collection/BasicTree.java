package com.mklr.collection;

public interface BasicTree<T>
{
    public void add(T newValue, BasicTree<T> newTree);
    public void remove(T valueToRemove);
    
    public boolean childExist(T valueToSearch);

    public BasicTree<T> getChild(T neededChild);
}
