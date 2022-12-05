package main.trees;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class RedBlackTree {
    private Node root;

    public RedBlackTree() {
        root = null;
    }

    /**
     * Loads the dictionary into the RedBlack Tree
     * supports file of any length as long as there is one word per line
     */
    public void loadDictionary() {
        try{
            BufferedReader readerBoi = new BufferedReader(new FileReader("./dictionary.txt"));
            String line = readerBoi.readLine();
            while(line !=null){
                System.out.println("adding: " + line.trim());
                addNode(line.trim());
                line = readerBoi.readLine();
            }
        } catch (IOException e) {
            System.out.println("paths rekt");
            throw new RuntimeException(e);
        }

    }

    public boolean isLeaf(Node n){
        if (n.equals(root) && n.getLeftChild() == null && n.getRightChild() == null) return true;
        if (n.equals(root)) return false;
        return n.getLeftChild() == null && n.getRightChild() == null;
    }

    public interface Visitor{
        /**
         This method is called at each node.
         @param n the visited node
         */
        void visit(Node n);
    }

    public void visit(Node n){
        System.out.println(n.getWord());
    }

    public void printTree(){  //preorder: visit, go left, go right
        Node currentNode = root;
        printTree(currentNode);
    }

    public void printTree(Node node){
        System.out.print(node.getWord());
        if (node.isLeaf()){
            return;
        }
        printTree(node.getLeftChild());
        printTree(node.getRightChild());
    }

    // place a new node in the RB tree with data the parameter and color it red.
    public void addNode(String data){  	//this < that  <0.  this > that  >0
        Node newNode = new Node();
        newNode.setWord(data);
        if (root == null) {
            root = newNode;
            return;
        }

        Node currentNode = root;

        while (currentNode != null && newNode.getParent() == null) {
            // we don't want duplicates
            if (Objects.equals(currentNode.getWord(), data)) return;

            else if (currentNode.getWord().compareTo(data) > 0) {
                if (currentNode.getLeftChild() == null) {
                    currentNode.setLeftChild(newNode);
                    newNode.setParent(currentNode);
                    newNode.setColor(false);
                }
                else
                    currentNode = currentNode.getLeftChild();
            }
            else if (currentNode.getWord().compareTo(data) < 0){
                if (currentNode.getRightChild() == null) {
                    currentNode.setRightChild(newNode);
                    newNode.setParent(currentNode);
                    newNode.setColor(false);
                }
                else
                    currentNode = currentNode.getRightChild();
            }
        }
        fixTree(newNode);
    }

    public void insert(String data){
        addNode(data);
    }

    public Node lookup(String k){
        Node searching = root;
        while (searching != null) {
            if (Objects.equals(searching.getWord(), k)) return searching;
            if (searching.getWord().compareTo(k) < 0)
                searching = searching.getLeftChild();
            if (searching.getWord().compareTo(k) > 0) {
                searching = searching.getRightChild();
            }
        }
        return null;
    }



    public Node getSibling(Node n){
        if (n.getParent().getRightChild() == n) {
            return n.getParent().getLeftChild();
        }
        return n.getParent().getRightChild();
    }


    public Node getAunt(Node n){
        Node grandParent = getGrandparent(n);
        if(grandParent != null ){
            if (isLeftChild(getGrandparent(n), n.getParent())) {
                return getGrandparent(n).getRightChild();
            }
            return getGrandparent(n).getLeftChild();
        }
        else return null;
    }

    public Node getGrandparent(Node n){
        return n.getParent().getParent();
    }

    public void rotateLeft(Node x){
        Node y = x.getRightChild();
        if (x == root) {
            root = y;
        }
        Node b = y.getLeftChild();
        x.setRightChild(b);
        if(b != null){
            b.setParent(x);
        }


        y.setParent(x.getParent());

        x.setParent(y);
        y.setLeftChild(x);
    }

    public void rotateRight(Node y){
        Node x = y.getLeftChild();
        if (y == root) {
            root = x;
        }
        y.setLeftChild(x.getRightChild());
        if(x.getRightChild() != null){
            x.getRightChild().setParent(y);
        }
        x.setParent(y.getParent());
        y.setParent(x);
        x.setRightChild(y);
    }

    public void fixTree(Node current) {
        //base case
        if(current.getParent() == null){
            return;
        }
        Node parent = current.getParent();
        //1) current is the root node. Make it black and quit.
        if(current == root){
            current.setBlack();
        }
        //2) Parent is black. Quit, the tree is a Red Black Tree.
        if(parent.getColor() == true){
            return;
        }
        Node grandparent = parent.getParent();
        //3) The current node is red and the parent node is red. The tree is unbalanced and you will have to modify it in the following way.
        //I. If the aunt node is empty or black, then there are four sub cases that you have to process.
        Node aunt = getAunt(current);
        if(getAunt(current) == null || aunt.getColor()){
            //A) grandparent –parent(is left child)— current (is right child) case.
            if(isLeftChild(grandparent,parent) && isRightChild(parent,current)){
                //Solution: rotate the parent left and then continue recursively fixing the tree starting with the original parent.
                rotateLeft(parent);
                fixTree(parent);
            }
            //B) grandparent –parent (is right child)— current (is left child) case.
            if(isRightChild(grandparent,parent) && isLeftChild(parent,current)){
                //Solution: rotate the parent right and then continue recursively fixing the tree starting with the original parent.
                rotateRight(parent);
                fixTree(parent);
            }
            //C) grandparent –parent (is left child)— current (is left child) case.
            if(isLeftChild(grandparent,parent) && isLeftChild(parent,current)){
                //Solution: make the parent black, make the grandparent red, rotate the grandparent to the right
                parent.setBlack();
                    grandparent.setRed();
                rotateRight(grandparent);
                //Quit tree is balanced.
            }
            //D) grandparent –parent (is right child)— current (is right child) case
            if(isRightChild(grandparent,parent) && isRightChild(parent,current)){
                // Solution: make the parent black, make the grandparent red, rotate the grandparent to the left
                parent.setBlack();
                    grandparent.setRed();
                rotateLeft(grandparent);
                //Quit tree is balanced.
            }
        }
        else if(aunt.getColor() == false){
            parent.setBlack();
            aunt.setBlack();
            if(grandparent != root){
                grandparent.setRed();
            }

            fixTree(grandparent);
        }
    }

    public boolean isEmpty(Node n){
        return n.getWord() == null;
    }

    public boolean isLeftChild(Node parent, Node child)
    {
        if(parent == null || child == null){
            return false;
        }
        //child is less than parent
        return child.compareTo(parent) < 0;
    }

    public boolean isRightChild(Node parent, Node child)
    {
        if(parent == null || child == null){
            return false;
        }
        //child is less than parent
        return !(child.compareTo(parent) < 0);
    }

    public void preOrderVisit(Visitor v) {
        preOrderVisit(root, v);
    }


    private static void preOrderVisit(Node n, Visitor v) {
        if (n == null) {
            return;
        }
        v.visit(n);
        preOrderVisit(n.getLeftChild(), v);
        preOrderVisit(n.getRightChild(), v);
    }

    public Node getRoot() { return root;}
    public void setRoot(Node root) { this.root = root;}

}
