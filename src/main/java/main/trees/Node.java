package main.trees;

import java.util.Objects;

public class Node {
    private Node parent;
    private Node leftChild;
    private Node rightChild;
    private String word;
    private boolean black;

    public Node() {
        parent = null;
        leftChild = null;
        rightChild = null;
        word = "";
        black = true;
    }

    public boolean equals(Node node) {
        return Objects.equals(this.getWord(), node.getWord());
    }

    public Node getParent() {return  parent;}
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node getLeftChild() {return leftChild;}
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
    public Node getRightChild() {return rightChild;}
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
    public String getWord() {return word;}
    public void setWord(String word) {
        this.word = word;
    }
    public boolean getColor() {return black;}
    public void setColor(boolean black) {
        this.black = black;
    }
    public boolean isLeaf() {return leftChild == null && rightChild == null;}

    public int compareTo(Node parent) {
        return word.compareTo(parent.getWord());
    }
}
