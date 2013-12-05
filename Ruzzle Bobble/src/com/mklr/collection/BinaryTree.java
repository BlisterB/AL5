package com.mklr.collection;

import java.util.Comparator;
import java.lang.Comparable;

public class BinaryTree<T extends Comparable<T>> implements BasicTree<T>
{
    public final static byte ORDINARY   = 0x0000;
    public final static byte ABRTREE    = 0x0001;
    public final static byte AVLTREE    = 0x0010;

    T nodeValue;
    BinaryTree<T> leftNode;
    BinaryTree<T> rightNode;

    public BinaryTree() {
        this(null, null, null);
    }

    public BinaryTree(T nodeValue) {
        this(nodeValue, null, null);
    }

    public BinaryTree(T nodeValue, BinaryTree<T> leftNode, BinaryTree<T> rightNode) {
        this.nodeValue = nodeValue;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
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
    public void add(T newValue) {
        if (nodeValue == null) {
            nodeValue = newValue;
            rightNode = null;
            leftNode = null;
            return;
        }


        int res = newValue.compareTo(nodeValue);
        if (res < 0) {
            if (leftNode == null) {
                leftNode = new BinaryTree<T>(newValue);
                return;
            } else {
                leftNode.add(newValue);
            }
        } else if (res == 0) {
            return;
        } else {
            if (rightNode == null) {
                rightNode = new BinaryTree<T>(newValue);
                return;
            } else {
                rightNode.add(newValue);
            }
        }
    }

    @Override
    public void remove(T valueToRemove) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean childExist(T valueToSearch) {
        if (nodeValue == null)
            return false;

        int res = valueToSearch.compareTo(nodeValue);
        if (res < 0) {
            if (leftNode == null) {
                return false;
            } else {
                return leftNode.childExist(valueToSearch);
            }
        } else if (res == 0) {
            return true;
        } else {
            if (rightNode == null) {
                return false;
            } else {
                return rightNode.childExist(valueToSearch);
            }
        }
    }


    @Override
    public BasicTree<T> getChild(T neededChild) {
        // TODO Auto-generated method stub
        return null;
    }

}


