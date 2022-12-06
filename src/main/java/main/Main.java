package main;

import main.trees.RedBlackTree;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {

        RedBlackTree rbt = new RedBlackTree();
        rbt.loadDictionary();
        rbt.spellCheck();
    }
}