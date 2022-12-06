package main;

import main.trees.Node;
import main.trees.RedBlackTree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        RedBlackTree rbt = new RedBlackTree();
        rbt.loadDictionary();
        //
        rbt.spellCheck();

        //Printing dictionary causes issues
        //rbt.printTree();
        rbt.rotateRight(rbt.getRoot());
        System.out.println("");
        //rbt.printTree();

        rbt.rotateLeft(rbt.getRoot());
        System.out.println("");
        //bt.printTree();
    }
}