import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class TreeFunctionsTest {

    @Test
    public void trees () {

        BinTree<Integer,Integer> t123, t456, t7;

        t123 = new Node<>(1, new Leaf<>(2), new Leaf<>(3));
        t456 = new Node<>(4, new Leaf<>(5), new Leaf<>(6));
        t7 = new Node<>(7, t123, t456);

        TreePrinter.print(t7);

        assertEquals(7, TreeFunctions.countNodes(t7));
        assertEquals(4, TreeFunctions.countLeaves(t7));
        assertEquals(3, TreeFunctions.countInternalNodes(t7));
        assertEquals(2, TreeFunctions.height(t7));
        assertTrue(TreeFunctions.isBalanced(t7));
        assertEquals(List.singleton(6).push(5).push(4).push(3).push(2).push(1).push(7),
                TreeFunctions.preorder(t7));

        assertEquals(List.singleton(6).push(4).push(5).push(7).push(3).push(1).push(2),
                TreeFunctions.inorder(t7));

        assertEquals(List.singleton(7).push(4).push(6).push(5).push(1).push(3).push(2),
                TreeFunctions.postorder(t7));
    }

    @Test
    public void eval () {
        BinTree<BinaryOperator<Integer>,Integer> exp1, exp2, exp3;
        exp1 = new Node<>(Integer::sum, new Leaf<>(2), new Leaf<>(3));
        exp2 = new Node<>((a,b) -> a-b, new Leaf<>(5), new Leaf<>(6));
        exp3 = new Node<>(Math::max, exp1, exp2);

        TreePrinter.print(exp3);

        assertEquals(5, TreeFunctions.eval(exp1));
        assertEquals(-1, TreeFunctions.eval(exp2));
        assertEquals(5, TreeFunctions.eval(exp3));
    }
    // countNodes, countLeaves, countInternalNodes, height, isBalnanced, pre/post/in traversals
    // test case for one leaf
    @Test
    public void oneLeaf(){
        BinTree<Integer, Integer> t;
        t = new Leaf<>(4);
        TreePrinter.print(t);
        assertEquals(1, TreeFunctions.countNodes(t));
        assertEquals(1, TreeFunctions.countLeaves(t));
        assertEquals(0, TreeFunctions.countInternalNodes(t));
        assertEquals(0, TreeFunctions.height(t));
        assertTrue(TreeFunctions.isBalanced(t));
        assertEquals(List.singleton(4), TreeFunctions.preorder(t));
        assertEquals(List.singleton(4), TreeFunctions.inorder(t));
        assertEquals(List.singleton(4), TreeFunctions.postorder(t));

    }

    // test case for imbalanced right in general
    @Test
    public void imbalancedRightGeneral(){
        BinTree<Integer, Integer> t, tl, tr, trr, trl;
        trl = new Node<>(5, new Leaf<>(3), new Leaf<>(1));
        trr = new Node<>(6, new Leaf<>(2), new Leaf<>(10));
        tr = new Node<>(9, trl, trr);
        tl = new Leaf<>(2);
        t = new Node<>(1, tl, tr);
        TreePrinter.print(t);
        assertEquals(9, TreeFunctions.countNodes(t));
        assertEquals(5, TreeFunctions.countLeaves(t));
        assertEquals(4, TreeFunctions.countInternalNodes(t));
        assertEquals(3, TreeFunctions.height(t));
        assertFalse(TreeFunctions.isBalanced(t));
        assertEquals(List.singleton(10).push(2).push(6).push(1).push(3).push(5).push(9).push(2).push(1), TreeFunctions.preorder(t));
        assertEquals(List.singleton(10).push(6).push(2).push(9).push(1).push(5).push(3).push(1).push(2), TreeFunctions.inorder(t));
        assertEquals(List.singleton(1).push(9).push(6).push(10).push(2).push(5).push(1).push(3).push(2), TreeFunctions.postorder(t));
    }
    // test case for left child being horrible imablanced while right child looks pretty normal
    @Test
    public void rightNormalLeftImbalanced(){
        BinTree<Integer, Integer> t, tl, tr, trr, trl, r, rr, rrl, rrr;
        trl = new Node<>(5, new Leaf<>(3), new Leaf<>(1));
        trr = new Node<>(6, new Leaf<>(2), new Leaf<>(10));
        tr = new Node<>(9, trl, trr);
        tl = new Leaf<>(2);
        t = new Node<>(1, tl, tr);
        rrr = new Node<>(13, new Leaf<>(8), new Leaf<>(12));
        rrl = new Node<>(7, new Leaf<>(10), new Leaf<>(4));
        rr = new Node<>(3, rrl, rrr);
        r = new Node<>(1, t, rr);
        TreePrinter.print(r);
        assertEquals(17, TreeFunctions.countNodes(r));
        assertEquals(9, TreeFunctions.countLeaves(r));
        assertEquals(8, TreeFunctions.countInternalNodes(r));
        assertEquals(4, TreeFunctions.height(r));
        assertFalse(TreeFunctions.isBalanced(r));
        assertEquals(List.singleton(12).push(8).push(13).push(4).push(10).push(7).push(3).push(10).push(2).push(6).push(1).push(3).push(5).push(9).push(2).push(1).push(1), TreeFunctions.preorder(r));
        assertEquals(List.singleton(12).push(13).push(8).push(3).push(4).push(7).push(10).push(1).push(10).push(6).push(2).push(9).push(1).push(5).push(3).push(1).push(2), TreeFunctions.inorder(r));
        assertEquals(List.singleton(1).push(3).push(13).push(12).push(8).push(7).push(4).push(10).push(1).push(9).push(6).push(10).push(2).push(5).push(1).push(3).push(2), TreeFunctions.postorder(r));
    }

    // test case for eval on a tree (Integer product)
    @Test
    public void evalNode(){
        BinTree<BinaryOperator<Integer>,Integer> t, tr, tl;
        tl = new Node<>(Integer::sum, new Leaf<>(4), new Leaf<>(2));
        tr = new Node<>((a,b) -> a * b, new Leaf<>(3), new Leaf<>(7));
        t = new Node<>((a,b) -> Math.abs(a-b), tl, tr);

        TreePrinter.print(t);
        assertEquals(15, TreeFunctions.eval(t));

    }



}
