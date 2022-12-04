package main.trees;

import java.util.Objects;

public class RedBlackTree {
    private Node root;

    public RedBlackTree() {
        root = null;
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
//                    return;
                }
                else
                    currentNode = currentNode.getLeftChild();
            }
            else if (currentNode.getWord().compareTo(data) < 0){
                if (currentNode.getRightChild() == null) {
                    currentNode.setRightChild(newNode);
                    newNode.setParent(currentNode);
                    newNode.setColor(false);
//                    return;
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
        if (isLeftChild(getGrandparent(n), n.getParent())) {
            return getGrandparent(n).getRightChild();
        }
        return getGrandparent(n).getLeftChild();
    }

    public Node getGrandparent(Node n){
        return n.getParent().getParent();
    }

    public void rotateLeft(Node n){
        //
    }

    public void rotateRight(Node y){
        Node x = y.getLeftChild();
        if (y == root) {
            root = x;
        }
        y.setLeftChild(x.getRightChild());
        x.getRightChild().setParent(y);

        x.setParent(y.getParent());

        y.setParent(x);
        x.setRightChild(y);
    }

    public void fixTree(Node current) {
        //
    }

    public boolean isEmpty(Node n){
        return n.getWord() == null;
    }

    public boolean isLeftChild(Node parent, Node child)
    {
        //child is less than parent
        return child.compareTo(parent) < 0;
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
