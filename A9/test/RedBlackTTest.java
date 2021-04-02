import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTTest {

    @Test
    public void test1 () {
        AVL<Character> avl = new EmptyAVL<>();
        avl = avl.AVLinsert('J');
        avl = avl.AVLinsert('F');
        avl = avl.AVLinsert('P');
        avl = avl.AVLinsert('D');
        avl = avl.AVLinsert('G');
        avl = avl.AVLinsert('L');
        avl = avl.AVLinsert('V');
        avl = avl.AVLinsert('C');
        avl = avl.AVLinsert('N');
        avl = avl.AVLinsert('S');
        avl = avl.AVLinsert('X');
        avl = avl.AVLinsert('Q');
        avl = avl.AVLinsert('U');

        TreePrinter.print(avl);
    }

    @Test
    public void test2 () {
        RedBlackT<Character> rb;

        rb = new RBNode<>(
                'J',
                Color.BLACK,
                new RBNode<>(
                        'F',
                        Color.BLACK,
                        new RBNode<>(
                                'D',
                                Color.BLACK,
                                new RBNode<>(
                                        'C',
                                        Color.RED,
                                        new EmptyRB<>(),
                                        new EmptyRB<>()
                                ),
                                new EmptyRB<>()
                        ),
                        new RBNode<>(
                                'G',
                                Color.BLACK,
                                new EmptyRB<>(),
                                new EmptyRB<>()
                        )
                ),
                new RBNode<>(
                        'P',
                        Color.BLACK,
                        new RBNode<>(
                                'L',
                                Color.BLACK,
                                new EmptyRB<>(),
                                new RBNode<>(
                                        'N',
                                        Color.RED,
                                        new EmptyRB<>(),
                                        new EmptyRB<>()
                                )
                        ),
                        new RBNode<>(
                                'V',
                                Color.RED,
                                new RBNode<>(
                                        'S',
                                        Color.BLACK,
                                        new RBNode<>(
                                                'Q',
                                                Color.RED,
                                                new EmptyRB<>(),
                                                new EmptyRB<>()
                                        ),
                                        new RBNode<>(
                                                'U',
                                                Color.RED,
                                                new EmptyRB<>(),
                                                new EmptyRB<>()
                                        )
                                ),
                                new RBNode<>(
                                        'X',
                                        Color.BLACK,
                                        new EmptyRB<>(),
                                        new EmptyRB<>()
                                )
                        )
                ));

        TreePrinter.print(rb);

        Optional<Integer> opth = rb.isValidBlackTree();
        int h = opth.orElseThrow();
        assertEquals(4,h);
    }

    @Test
    public void test3 () {
        AVL<Character> avl = new EmptyAVL<>();
        avl = avl.AVLinsert('J');
        avl = avl.AVLinsert('F');
        avl = avl.AVLinsert('P');
        avl = avl.AVLinsert('D');
        avl = avl.AVLinsert('G');
        avl = avl.AVLinsert('L');
        avl = avl.AVLinsert('V');
        avl = avl.AVLinsert('C');
        avl = avl.AVLinsert('N');
        avl = avl.AVLinsert('S');
        avl = avl.AVLinsert('X');
        avl = avl.AVLinsert('Q');
        avl = avl.AVLinsert('U');

        RedBlackT<Character> rb = avl.toRB();

        TreePrinter.print(rb);

        Optional<Integer> opth = rb.isValidBlackTree();
        int h = opth.orElseThrow();
        assertEquals(4,h);

    }

    // test for EmptyAVL methods: toRB, colorBlackEven, colorBlackOdd, colorRed, AVLfind, AVLinsert, AVLeasyRight, AVLeasyLeft,
    // AVLrotateRight, AVLrotateLeft, isValidBlackTree, isValidRedTree
    // test types: empty, leaf, reg
    // data types: integer, booleans, string types?
    @Test
    public void emptyAVL(){
        // integers: empty
        AVL<Integer> ei = new EmptyAVL<>();
        // boolean: empty
        AVL<Boolean> eb = new EmptyAVL<>();
        // string: empty
        AVL<String> es = new EmptyAVL<>();

        // toRB
        RedBlackT<Integer> rbei = ei.toRB();
        RedBlackT<Boolean> rbeb = eb.toRB();
        RedBlackT<String> rbes = es.toRB();
        assertEquals(rbei, new EmptyRB<Integer>());
        assertEquals(rbeb, new EmptyRB<Boolean>());
        assertEquals(rbes, new EmptyRB<String>());

        // colorBlackEven
        rbei = ei.colorBlackEven();
        rbeb = eb.colorBlackEven();
        rbes = es.colorBlackEven();
        assertEquals(rbei, new EmptyRB<Integer>());
        assertEquals(rbeb, new EmptyRB<Boolean>());
        assertEquals(rbes, new EmptyRB<String>());

        // colorBlackOdd
        rbei = ei.colorBlackOdd();
        rbeb = eb.colorBlackOdd();
        rbes = es.colorBlackOdd();
        assertEquals(rbei, new EmptyRB<Integer>());
        assertEquals(rbeb, new EmptyRB<Boolean>());
        assertEquals(rbes, new EmptyRB<String>());

        // colorRed
        assertThrows(Error.class, () -> {
            AVL<Integer> rbei2 = new EmptyAVL<>();
            rbei2.colorRed();
        });
        assertThrows(Error.class, () -> {
            AVL<Boolean> rbeb2 = new EmptyAVL<>();
            rbeb2.colorRed();
        });
        assertThrows(Error.class, () -> {
            AVL<String> rbes2 = new EmptyAVL<>();
            rbes2.colorRed();
        });

        // AVLfind
        assertFalse(ei.AVLfind(69));
        assertFalse(eb.AVLfind(false));
        assertFalse(es.AVLfind("happiness"));

        // AVLeasyLeft
        assertThrows(Error.class, () -> {
            AVL<Integer> rbei2 = new EmptyAVL<>();
            rbei2.AVLeasyLeft();
        });
        assertThrows(Error.class, () -> {
            AVL<Boolean> rbeb2 = new EmptyAVL<>();
            rbeb2.AVLeasyLeft();
        });
        assertThrows(Error.class, () -> {
            AVL<String> rbes2 = new EmptyAVL<>();
            rbes2.AVLeasyLeft();
        });

        // AVLrotateRight
        assertThrows(Error.class, () -> {
            AVL<Integer> rbei2 = new EmptyAVL<>();
            rbei2.AVLrotateRight();
        });
        assertThrows(Error.class, () -> {
            AVL<Boolean> rbeb2 = new EmptyAVL<>();
            rbeb2.AVLrotateRight();
        });
        assertThrows(Error.class, () -> {
            AVL<String> rbes2 = new EmptyAVL<>();
            rbes2.AVLrotateRight();
        });

        // AVLrotateLeft
        assertThrows(Error.class, () -> {
            AVL<Integer> rbei2 = new EmptyAVL<>();
            rbei2.AVLrotateLeft();
        });
        assertThrows(Error.class, () -> {
            AVL<Boolean> rbeb2 = new EmptyAVL<>();
            rbeb2.AVLrotateLeft();
        });
        assertThrows(Error.class, () -> {
            AVL<String> rbes2 = new EmptyAVL<>();
            rbes2.AVLrotateLeft();
        });

        // isValidBlackTree
        assertEquals(rbei.isValidBlackTree(), Optional.of(1));
        assertEquals(rbeb.isValidBlackTree(), Optional.of(1));
        assertEquals(rbes.isValidBlackTree(), Optional.of(1));

        // isValidRedTree
        assertEquals(rbei.isValidRedTree(), Optional.empty());
        assertEquals(rbeb.isValidRedTree(), Optional.empty());
        assertEquals(rbes.isValidRedTree(), Optional.empty());

        // AVLeasyRight
        assertThrows(Error.class, () -> {
            AVL<Integer> rbei2 = new EmptyAVL<>();
            rbei2.AVLeasyRight();
        });
        assertThrows(Error.class, () -> {
            AVL<Boolean> rbeb2 = new EmptyAVL<>();
            rbeb2.AVLeasyRight();
        });
        assertThrows(Error.class, () -> {
            AVL<String> rbes2 = new EmptyAVL<>();
            rbes2.AVLeasyRight();
        });

        // AVLinsert
        ei = ei.AVLinsert(22);
        assertEquals(ei, new AVLNode<Integer>(22, new EmptyAVL<>(), new EmptyAVL<>()));

        eb = eb.AVLinsert(true);
        assertEquals(eb, new AVLNode<Boolean>(true, new EmptyAVL<>(), new EmptyAVL<>()));

        es = es.AVLinsert("cool");
        assertEquals(es, new AVLNode<String>("cool", new EmptyAVL<>(), new EmptyAVL<>()));
    }

    @Test
    public void AVLLeafmethods() throws EmptyRBE{
        // integers: leaf
        AVL<Integer> li = new EmptyAVL<>();
        li = li.AVLinsert(420);
        // boolean: leaf
        AVL<Boolean> lb = new EmptyAVL<>();
        lb = lb.AVLinsert(true);
        // string: leaf
        AVL<String> ls = new EmptyAVL<>();
        ls = ls.AVLinsert("joe biden");

        // printing
        System.out.println("Leaf");
        System.out.println("Integer");
        TreePrinter.print(li);
        System.out.println("Boolean");
        TreePrinter.print(lb);
        System.out.println("String");
        TreePrinter.print(ls);
        System.out.println();

        // toRB
        RedBlackT<Integer> rbli = li.toRB();
        RedBlackT<Boolean> rblb = lb.toRB();
        RedBlackT<String> rbls = ls.toRB();
        System.out.println("Comparing toRB() for Integer");
        TreePrinter.print(rbli);
        TreePrinter.print(li.toRB());
        System.out.println("Comparing toRB() for Boolean");
        TreePrinter.print(rblb);
        TreePrinter.print(lb.toRB());
        System.out.println("Comparing toRB() for String");
        TreePrinter.print(rbls);
        TreePrinter.print(ls.toRB());
        System.out.println();

        // colorBlackEven
        rbli = li.colorBlackEven();
        rblb = lb.colorBlackEven();
        rbls = ls.colorBlackEven();
        System.out.println("Comparing colorBlackEven() for Integer");
        TreePrinter.print(rbli);
        TreePrinter.print(li.colorBlackEven());
        System.out.println("Comparing colorBlackEven() for Boolean");
        TreePrinter.print(rblb);
        TreePrinter.print(lb.colorBlackEven());
        System.out.println("Comparing colorBlackEven() for String");
        TreePrinter.print(rbls);
        TreePrinter.print(ls.colorBlackEven());
        System.out.println();

        // colorBlackOdd
        rbli = li.colorBlackOdd();
        rblb = lb.colorBlackOdd();
        rbls = ls.colorBlackOdd();
        System.out.println("Comparing colorBlackOdd() for Integer");
        TreePrinter.print(rbli);
        TreePrinter.print(li.colorBlackOdd());
        System.out.println("Comparing colorBlackOdd() for Boolean");
        TreePrinter.print(rblb);
        TreePrinter.print(lb.colorBlackOdd());
        System.out.println("Comparing colorBlackOdd() for String");
        TreePrinter.print(rbls);
        TreePrinter.print(ls.colorBlackOdd());
        System.out.println();

        // colorRed
        rbli = li.colorRed();
        rblb = lb.colorRed();
        rbls = ls.colorRed();
        System.out.println("Comparing colorRed() for Integer");
        TreePrinter.print(rbli);
        TreePrinter.print(li.colorRed());
        System.out.println("Comparing colorRed() for Boolean");
        TreePrinter.print(rblb);
        TreePrinter.print(lb.colorRed());
        System.out.println("Comparing colorRed() for String");
        TreePrinter.print(rbls);
        TreePrinter.print(ls.colorRed());
        System.out.println();

        // AVLeasyRight
        assertEquals(new EmptyAVL(), li.AVLeasyRight());
        assertEquals(new EmptyAVL(), lb.AVLeasyRight());
        assertEquals(new EmptyAVL(), ls.AVLeasyRight());

        // AVLeasyLeft
        assertEquals(new EmptyAVL(), li.AVLeasyLeft());
        assertEquals(new EmptyAVL(), lb.AVLeasyLeft());
        assertEquals(new EmptyAVL(), ls.AVLeasyLeft());

        // AVLrotateRight
        assertEquals(new EmptyAVL(), li.AVLrotateRight());
        assertEquals(new EmptyAVL(), lb.AVLrotateRight());
        assertEquals(new EmptyAVL(), ls.AVLrotateRight());

        // AVLrotateLeft
        assertEquals(new EmptyAVL(), li.AVLrotateLeft());
        assertEquals(new EmptyAVL(), lb.AVLrotateLeft());
        assertEquals(new EmptyAVL(), ls.AVLrotateLeft());

        // isValidBlackTree
        rbli = li.toRB();
        rblb = lb.toRB();
        rbls = ls.toRB();
        assertEquals(Optional.of(2),rbli.isValidBlackTree());
        assertEquals(Optional.of(2),rblb.isValidBlackTree());
        assertEquals(Optional.of(2),rbls.isValidBlackTree());

        // isValidRedTree
        assertEquals(Optional.empty(),rbli.isValidRedTree());
        assertEquals(Optional.empty(),rblb.isValidRedTree());
        assertEquals(Optional.empty(),rbls.isValidRedTree());
        // but then...if i do colorRed hehe
        rbli = li.colorRed();
        rblb = lb.colorRed();
        rbls = ls.colorRed();
        assertEquals(Optional.of(1),rbli.isValidRedTree());
        assertEquals(Optional.of(1),rblb.isValidRedTree());
        assertEquals(Optional.of(1),rbls.isValidRedTree());

        // AVLfind & AVLinsert
        rbli = li.toRB();
        rblb = lb.toRB();
        rbls = ls.toRB();
        li = li.AVLinsert(200);
        lb = lb.AVLinsert(false);
        ls = ls.AVLinsert("please let him win");
        assertTrue(li.AVLfind(200));
        assertTrue(lb.AVLfind(false));
        assertTrue(ls.AVLfind("please let him win"));

    }

    // regular test cases :D

    @Test
    public void toRB(){
        // also tests colorBlackEven, colorBlackOdd, colorRed
        AVL<Integer> i = new EmptyAVL<>();
        i = i.AVLinsert(6);
        i = i.AVLinsert(10);
        i = i.AVLinsert(11);
        i = i.AVLinsert(4);
        i = i.AVLinsert(3);

        AVL<Boolean> b = new EmptyAVL<>();
        b = b.AVLinsert(true);
        b = b.AVLinsert(true);
        b = b.AVLinsert(false);
        b = b.AVLinsert(true);
        b = b.AVLinsert(true);
        b = b.AVLinsert(true);

        AVL<String> s = new EmptyAVL<>();
        s = s.AVLinsert("puppy");
        s = s.AVLinsert("cow");
        s = s.AVLinsert("kitten");
        s = s.AVLinsert("woof");

        RedBlackT<Integer> rbi = i.toRB();
        RedBlackT<Boolean> rbb = b.toRB();
        RedBlackT<String> rbs = s.toRB();
        TreePrinter.print(i);
        TreePrinter.print(rbi);
        TreePrinter.print(b);
        TreePrinter.print(rbb);
        TreePrinter.print(s);
        TreePrinter.print(rbs);
    }

    @Test
    public void AVLinsertAVLFind(){
        AVL<Integer> i = new EmptyAVL<>();
        i = i.AVLinsert(6);
        i = i.AVLinsert(10);
        i = i.AVLinsert(11);

        AVL<Boolean> b = new EmptyAVL<>();
        b = b.AVLinsert(true);

        AVL<String> s = new EmptyAVL<>();
        s = s.AVLinsert("puppy");
        s = s.AVLinsert("cow");
        s = s.AVLinsert("kitten");

        i = i.AVLinsert(2);
        b = b.AVLinsert(false);
        s = s.AVLinsert("woof");

        assertTrue(i.AVLfind(2));
        assertTrue(b.AVLfind(false));
        assertTrue(s.AVLfind("woof"));
    }

    @Test
    public void AVLeasyRight(){
        AVL<Integer> i = new EmptyAVL<>();
        i = i.AVLinsert(6);
        i = i.AVLinsert(5);
        TreePrinter.print(i);
        i = i.AVLinsert(4);
        TreePrinter.print(i);

        AVL<Boolean> b = new EmptyAVL<>();
        b = b.AVLinsert(true);
        b = b.AVLinsert(false);
        TreePrinter.print(b);
        b = b.AVLinsert(false);
        TreePrinter.print(b);

    }

    @Test
    public void AVLeasyLeft(){
        AVL<Integer> i = new EmptyAVL<>();
        i = i.AVLinsert(6);
        i = i.AVLinsert(10);
        TreePrinter.print(i);
        i = i.AVLinsert(11);
        TreePrinter.print(i);
    }

    @Test
    public void AVLrotateRight() throws EmptyAVLE{
        AVL<Integer> avl = new EmptyAVL<>();
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
    public void AVLrotateLeft() throws EmptyAVLE{
        AVL<Integer> reg = new EmptyAVL<>();
        reg = reg.AVLinsert(30);
        reg = reg.AVLinsert(25);
        reg = reg.AVLinsert(20);
        reg = reg.AVLinsert(40);
        reg = reg.AVLinsert(39);
        reg = reg.AVLinsert(41);
        TreePrinter.print(reg);
        AVL left = reg.AVLLeft();
        AVL right = reg.AVLRight();
        assertEquals(39, reg.AVLData());
        assertEquals(25,left.AVLData());
        assertEquals(30, left.AVLRight().AVLData());
        assertEquals(20, left.AVLLeft().AVLData());
        assertEquals(40,right.AVLData());
        assertEquals(41, right.AVLRight().AVLData());

    }

    @Test
    public void isValidBlackTree(){
        AVL<Integer> i = new EmptyAVL<>();
        i = i.AVLinsert(6);
        i = i.AVLinsert(10);
        i = i.AVLinsert(11);
        i = i.AVLinsert(12);
        i = i.AVLinsert(50);

        AVL<Boolean> b = new EmptyAVL<>();
        b = b.AVLinsert(true);
        b = b.AVLinsert(true);
        b = b.AVLinsert(false);
        b = b.AVLinsert(true);
        b = b.AVLinsert(false);
        b = b.AVLinsert(true);
        b = b.AVLinsert(false);
        b = b.AVLinsert(false);
        b = b.AVLinsert(true);
        b = b.AVLinsert(true);
        b = b.AVLinsert(false);
        b = b.AVLinsert(true);
        b = b.AVLinsert(true);
        b = b.AVLinsert(true);
        b = b.AVLinsert(false);
        b = b.AVLinsert(true);
        b = b.AVLinsert(true);


        AVL<String> s = new EmptyAVL<>();
        s = s.AVLinsert("puppy");
        s = s.AVLinsert("cow");
        s = s.AVLinsert("kitten");
        s = s.AVLinsert("acab");
        s = s.AVLinsert("blm");
        s = s.AVLinsert("joe biden :D");
        s = s.AVLinsert("djt can rot");
        s = s.AVLinsert("oopsy");

        RedBlackT<Integer> rbi = i.toRB();
        RedBlackT<Boolean> rbb = b.toRB();
        RedBlackT<String> rbs = s.toRB();
        assertEquals(Optional.of(3), rbi.isValidBlackTree());
        assertEquals(Optional.of(4), rbb.isValidBlackTree());
        assertEquals(Optional.of(3), rbs.isValidBlackTree());
    }

    @Test
    public void isValidRedTree() throws EmptyRBE{
        AVL<Integer> i = new EmptyAVL<>();
        i = i.AVLinsert(6);
        i = i.AVLinsert(7);
        i = i.AVLinsert(8);
        i = i.AVLinsert(10);
        i = i.AVLinsert(25);
        i = i.AVLinsert(55);
        i = i.AVLinsert(45);
        i = i.AVLinsert(75);

        AVL<Boolean> b = new EmptyAVL<>();
        b = b.AVLinsert(true);
        b = b.AVLinsert(false);
        b = b.AVLinsert(false);

        AVL<String> s = new EmptyAVL<>();
        s = s.AVLinsert("acab");
        s = s.AVLinsert("peepee");
        s = s.AVLinsert("screw12");

        RedBlackT<Integer> rbi = i.toRB();
        RedBlackT<Boolean> rbb = b.toRB();
        RedBlackT<String> rbs = s.toRB();


        assertEquals(Optional.of(2), rbi.RBRight().isValidRedTree());
        assertEquals(Optional.of(1), rbb.RBRight().isValidRedTree());
        assertEquals(Optional.of(1), rbs.RBLeft().isValidRedTree());

    }
}