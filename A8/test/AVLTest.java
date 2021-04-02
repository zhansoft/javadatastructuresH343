import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AVLTest {
    BST bst;
    AVL avl;

    @BeforeEach
    public void setUp() {
        List<Integer> nums = IntStream.range(0, 8).boxed().collect(Collectors.toList());
        Collections.shuffle(nums);
        bst = BST.EBST;
        avl = AVL.EAVL;
        for (int i : nums) {
            bst = bst.BSTinsert(i);
            avl = avl.AVLinsert(i);
        }
    }

    @Test
    public void toAVL() {
        BST bst2 = AVL.toBST(BST.toAVL(bst));
        Iterator<Integer> bstIter = bst.iterator();
        Iterator<Integer> bst2Iter = bst2.iterator();
        while (bstIter.hasNext() && bst2Iter.hasNext()) {
            assertEquals(bstIter.next(),bst2Iter.next());
        }
    }

    @Test
    public void AVLeasyRight() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(10);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(15);

        AVL left = avl.AVLLeft();
        AVL right = avl.AVLRight();
        assertEquals(20, avl.AVLData());
        assertEquals(10,left.AVLData());
        assertEquals(15, left.AVLRight().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(30, right.AVLLeft().AVLData());
        assertEquals(50, right.AVLRight().AVLData());
    }

    @Test
    public void AVLrotateRight() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(10);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(25);

        AVL left = avl.AVLLeft();
        AVL right = avl.AVLRight();
        assertEquals(30, avl.AVLData());
        assertEquals(20,left.AVLData());
        assertEquals(10, left.AVLLeft().AVLData());
        assertEquals(25, left.AVLRight().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(50, right.AVLRight().AVLData());
    }

    @Test
    public void AVLdelete() throws EmptyAVLE {
        avl = AVL.EAVL;
        avl = avl.AVLinsert(35);
        avl = avl.AVLinsert(20);
        avl = avl.AVLinsert(40);
        avl = avl.AVLinsert(7);
        avl = avl.AVLinsert(30);
        avl = avl.AVLinsert(50);
        avl = avl.AVLinsert(5);
        avl = avl.AVLinsert(10);

        AVL avl2 = avl.AVLdelete(35);
        AVL left = avl2.AVLLeft();
        AVL right = avl2.AVLRight();
        assertEquals(30, avl2.AVLData());
        assertEquals(7,left.AVLData());
        assertEquals(5, left.AVLLeft().AVLData());
        assertEquals(20, left.AVLRight().AVLData());
        assertEquals(10, left.AVLRight().AVLLeft().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(50, right.AVLRight().AVLData());
    }

    @Test
    public void FE () throws EmptyAVLE {
        AVL t = new EmptyAVL();
        t = t.AVLinsert(44);
        t = t.AVLinsert(17);
        t = t.AVLinsert(78);
        t = t.AVLinsert(32);
        t = t.AVLinsert(50);
        t = t.AVLinsert(88);
        t = t.AVLinsert(48);
        t = t.AVLinsert(62);
        TreePrinter.print(t);
        t = t.AVLdelete(32);
        TreePrinter.print(t);
    }

    // rotateRight()
    @Test
    public void rotateRightEmptyLeaf(){
        AVL e = new EmptyAVL();
        AVL l = AVL.AVLLeaf(4);
        assertThrows(Error.class, () -> {
            EmptyAVL empty = new EmptyAVL();
            empty.AVLrotateRight();
        });
        assertEquals(new EmptyAVL(), l.AVLrotateRight());
    }

    // rotateEasy()
    @Test
    public void rotateEasyRightEmptyLeaf(){
        AVL e = new EmptyAVL();
        AVL l = AVL.AVLLeaf(4);
        assertThrows(Error.class, () -> {
            EmptyAVL empty = new EmptyAVL();
            empty.AVLeasyRight();
        });
        assertEquals(new EmptyAVL(), l.AVLeasyRight());
    }

    // rotateLeft(): empty, leaf, reg
    @Test
    public void rotateLeftEmptyLeafReg() throws EmptyAVLE{
        AVL e = new EmptyAVL();
        AVL l = AVL.AVLLeaf(4);
        AVL reg = AVL.EAVL;
        reg = reg.AVLinsert(30);
        reg = reg.AVLinsert(25);
        reg = reg.AVLinsert(20);
        reg = reg.AVLinsert(40);
        reg = reg.AVLinsert(39);
        reg = reg.AVLinsert(41);
        TreePrinter.print(reg);
        assertThrows(Error.class, () -> {
            EmptyAVL empty = new EmptyAVL();
            empty.AVLrotateLeft();
        });
        assertEquals(new EmptyAVL(), l.AVLrotateLeft());
        AVL left = reg.AVLLeft();
        AVL right = reg.AVLRight();
        assertEquals(39, reg.AVLData());
        assertEquals(25,left.AVLData());
        assertEquals(30, left.AVLRight().AVLData());
        assertEquals(20, left.AVLLeft().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(41, right.AVLRight().AVLData());
    }

    // easyLeft(): empty, leaf, reg
    @Test
    public void easyLeftEmptyLeafReg() throws EmptyAVLE{
        AVL e = new EmptyAVL();
        AVL l = AVL.AVLLeaf(5);
        AVL reg = AVL.EAVL;
        reg = reg.AVLinsert(30);
        reg = reg.AVLinsert(35);
        reg = reg.AVLinsert(34);
        TreePrinter.print(reg);
        assertThrows(Error.class, () -> {
            EmptyAVL empty = new EmptyAVL();
            empty.AVLeasyLeft();
        });
        assertEquals(new EmptyAVL(), l.AVLeasyLeft());
        AVL left = reg.AVLLeft();
        AVL right = reg.AVLRight();
        assertEquals(34, reg.AVLData());
        assertEquals(30,left.AVLData());
        assertEquals(35,right.AVLData());
    }

    // delete()
    @Test
    public void deleteEmptyLeaf() throws EmptyAVLE{
        AVL e = new EmptyAVL();
        AVL l = AVL.AVLLeaf(6);
        assertThrows(EmptyAVLE.class, () -> {
            EmptyAVL empty = new EmptyAVL();
            empty.AVLdelete(7);
        });
        assertEquals(new EmptyAVL(),l.AVLdelete(6));
    }

    // shrink()
    @Test
    public void shrinkEmptyLeaf() throws EmptyAVLE{
        AVL e = new EmptyAVL();
        AVL l = AVL.AVLLeaf(6);
        assertThrows(EmptyAVLE.class, () -> {
            EmptyAVL empty = new EmptyAVL();
            empty.AVLshrink();
        });
        Pair<Integer, AVL> temp = l.AVLshrink();
        assertEquals(6,temp.getFirst());
        assertEquals(new EmptyAVL(),temp.getSecond());
    }

    // insert()
    @Test
    public void insertFindEmptyLeaf(){
        AVL e = new EmptyAVL();
        AVL l = AVL.AVLLeaf(6);
        AVL ecomp = AVL.AVLLeaf(1);
        AVL lcomp = AVL.AVLLeaf(6).AVLinsert(2);
        e = e.AVLinsert(1);
        assertTrue(e.AVLfind(1));
        assertEquals(ecomp, e);
        l = l.AVLinsert(2);
        assertTrue(l.AVLfind(2));
        assertEquals(lcomp, l);
    }



}

