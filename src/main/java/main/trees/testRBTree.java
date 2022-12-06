package main.trees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testRBTree {
    @Test
    //Test the Red Black Tree
    public void test() {
        RedBlackTree rbt = new RedBlackTree();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        System.out.println(rbt.lookup("D"));
        assertEquals("DBACFEHGIJ", makeString(rbt));
        String str=     "Color: true, Key:D Parent: \n"+
                "Color: true, Key:B Parent: D\n"+
                "Color: true, Key:A Parent: B\n"+
                "Color: true, Key:C Parent: B\n"+
                "Color: true, Key:F Parent: D\n"+
                "Color: true, Key:E Parent: F\n"+
                "Color: false, Key:H Parent: F\n"+
                "Color: true, Key:G Parent: H\n"+
                "Color: true, Key:I Parent: H\n"+
                "Color: false, Key:J Parent: I\n";
        assertEquals(str, makeStringDetails(rbt));

    }

    //add tester for spell checker

    public static String makeString(RedBlackTree t)
    {
        class MyVisitor implements RedBlackTree.Visitor {
            String result = "";
            public void visit(Node n)
            {
                result = result + n.getWord();
            }
        };
        MyVisitor v = new MyVisitor();
        t.preOrderVisit(v);
        return v.result;
    }
    public static String makeStringDetails(RedBlackTree t) {
        {
            class MyVisitor implements RedBlackTree.Visitor {
                String result = "";
                public void visit(Node n)
                {
                    if (!(n.getWord()).equals("") && n.getParent() == null) {
                        result = result +"Color: "+n.getColor() +", Key:" + n.getWord() + " Parent: \n";
                    }
                    else if(!(n.getWord()).equals(""))
                        result = result +"Color: "+n.getColor() +", Key:" + n.getWord() + " Parent: " + n.getParent().getWord() + "\n";
                }
            }
            MyVisitor v = new MyVisitor();
            t.preOrderVisit(v);
            return v.result;
        }
    }
    // add this in your class
    //  public static interface Visitor
    //  {
    //   /**
    //     This method is called at each node.
    //     @param n the visited node
    //    */
    //   void visit(Node n);
    //  }


    // public void preOrderVisit(Visitor v)
    //  {
    //   preOrderVisit(root, v);
    //  }


    // private static void preOrderVisit(Node n, Visitor v)
    //  {
    //   if (n == null) return;
    //   v.visit(n);
    //   preOrderVisit(n.left, v);
    //   preOrderVisit(n.right, v);
    //  }
}
