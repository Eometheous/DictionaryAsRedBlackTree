package main;

import main.trees.Node;
import main.trees.RedBlackTree;

public class Main {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        rbt.insert("b");
        rbt.insert("a");
        rbt.insert("g");
        rbt.insert("c");
        rbt.insert("h");
        rbt.printTree();
        rbt.rotateLeft(rbt.getRoot());
        System.out.println("");
        rbt.printTree();

        rbt.rotateLeft(rbt.getRoot());
        System.out.println("");
        rbt.printTree();
    }
}