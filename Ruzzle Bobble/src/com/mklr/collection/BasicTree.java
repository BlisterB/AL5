package com.mklr.collection;

public interface BasicTree<T>
{
    public void add(T newValue, BasicTree<T> newTree);
    public void remove(T valueToRemove);
    public boolean search(T valueToSearch);
}
