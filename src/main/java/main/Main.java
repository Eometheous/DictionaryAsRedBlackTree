package main;

import main.trees.RedBlackTree;

public class Main {
    public static void main(String[] args) {

        RedBlackTree rbt = new RedBlackTree();
        rbt.loadDictionary();
        rbt.spellCheck();
    }
}