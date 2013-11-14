package com.mklr.collection;

public class BinaryTree<T> implements BasicTree<T>
{
    public final static byte ORDINARY   = 0x0000;
    public final static byte ABRTREE    = 0x0001;
    public final static byte AVLTREE    = 0x0010;

    T nodeValue;
    BinaryTree<T> leftNode;
    BinaryTree<T> rightNode;

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
     * @return the leftNode
     */
    public BinaryTree<T> getLeftNode() {
        return leftNode;
    }

    /**
     * @param leftNode the leftNode to set
     */
    public void setLeftNode(BinaryTree<T> leftNode) {
        this.leftNode = leftNode;
    }

    /**
     * @return the rightNode
     */
    public BinaryTree<T> getRightNode() {
        return rightNode;
    }

    /**
     * @param rightNode the rightNode to set
     */
    public void setRightNode(BinaryTree<T> rightNode) {
        this.rightNode = rightNode;
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
    public boolean childExist(T valueToSearch) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public BasicTree<T> getChild(T neededChild) {
        // TODO Auto-generated method stub
        return null;
    }

}
