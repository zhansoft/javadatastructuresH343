import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void followPrevious(){
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        a.setValue(0);
        b.setValue(3);
        c.setValue(5);
        d.setValue(8);
        d.setPrevious(c);
        c.setPrevious(b);
        b.setPrevious(a);

        System.out.println(d.followPrevious());
    }

    @Test
    void minChild () {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        a.setValue(0);
        b.setValue(3);
        c.setValue(5);
        d.setValue(8);

        Heap h = new Heap(Arrays.asList(a,b,c,d));
        ArrayList<Node> nodes = h.getNodes();

        assertEquals(a,nodes.get(0));
        assertEquals(b,nodes.get(1));
        assertEquals(c,nodes.get(2));
        assertEquals(d,nodes.get(3));

        assertEquals(b,h.getLeftChild(a).get());
        assertEquals(c,h.getRightChild(a).get());
        assertEquals(d,h.getLeftChild(b).get());
        assertEquals(b,h.getMinChild(a).get());
        assertEquals(d,h.getMinChild(b).get());
    }

    @Test
    public void update () {
        ArrayList<Node> items = new ArrayList<>();

        Node n;

        n = new Node("a1");
        n.setValue(6);
        items.add(n);

        n = new Node("a2");
        n.setValue(1);
        items.add(n);

        n = new Node("a3");
        n.setValue(8);
        items.add(n);

        n = new Node("a4");
        n.setValue(2);
        items.add(n);

        n = new Node("a5");
        n.setValue(4);
        items.add(n);

        n = new Node("a6");
        n.setValue(7);
        items.add(n);

        n = new Node("a7");
        n.setValue(9);
        items.add(n);

        n = new Node("a8");
        n.setValue(3);
        items.add(n);

        n = new Node("a9");
        n.setValue(5);
        items.add(n);

        Heap h = new Heap(items);
        TreePrinter.print(h.getMin());

        h.update(h.getNodes().get(7),0);
        TreePrinter.print(h.getMin());
    }

    @Test
    public void sort () {
        ArrayList<Node> items = new ArrayList<>();

        Node n;

        n = new Node("a1");
        n.setValue(6);
        items.add(n);

        n = new Node("a2");
        n.setValue(1);
        items.add(n);

        n = new Node("a3");
        n.setValue(8);
        items.add(n);

        n = new Node("a4");
        n.setValue(2);
        items.add(n);

        n = new Node("a5");
        n.setValue(4);
        items.add(n);

        n = new Node("a6");
        n.setValue(7);
        items.add(n);

        n = new Node("a7");
        n.setValue(9);
        items.add(n);

        n = new Node("a8");
        n.setValue(3);
        items.add(n);

        n = new Node("a9");
        n.setValue(5);
        items.add(n);

        Heap h = new Heap(items);
        TreePrinter.print(h.getMin());

        for (int i = 1; i < 10; i++){
            assertEquals(i, h.extractMin().getValue());
        }
    }

    // Node tests: followPrevious()
    @Test
    void followPreviousExtra(){
        // tests for one node with no previous
        Node a = new Node("A");
        a.setValue(0);
        ArrayList<Node> areturn = new ArrayList<>();
        areturn.add(a);
        assertEquals(areturn, a.followPrevious());
    }

    // Heap tests: getParent(), extractMin(), getLeftChild(), getRightChild(), getMinChild()
    @Test
    void getParent(){
        // test for a node without a previous
        Node a = new Node("A");
        Heap a1 = new Heap(Arrays.asList(a));
        assertEquals(Optional.of(a), a1.getParent(a));
        // tests for an incorrect heap index
        a.setHeapIndex(-1);
        assertEquals(Optional.empty(), a1.getParent(a));
        //test for a node with a parent
        a.setHeapIndex(0);
        Node b = new Node("B");
        a1 = new Heap(Arrays.asList(a,b));
        b.setPrevious(a);
        assertEquals(Optional.of(a), a1.getParent(b));
    }

    @Test
    void getLeftChild(){
        // test for a node without a leftChild
        Node a = new Node("A");
        Heap a1 = new Heap(Arrays.asList(a));
        assertEquals(Optional.empty(), a1.getLeftChild(a));
        // test for a node with a leftChild
        Node b = new Node("B");
        a1 = new Heap(Arrays.asList(a,b));
        b.setPrevious(a);
        assertEquals(Optional.of(b), a1.getLeftChild(a));
    }

    @Test
    void getRightChild(){
        // test for a node without a rightChild
        Node a = new Node("A");
        Heap a1 = new Heap(Arrays.asList(a));
        assertEquals(Optional.empty(), a1.getRightChild(a));
        // test for a node with a rightChild
        Node b = new Node("B");
        Node c = new Node("C");
        a1 = new Heap(Arrays.asList(a,b,c));
        assertEquals(Optional.of(c), a1.getRightChild(a));
    }

    @Test
    void getMinChild(){
        // test for a node without children
        Node a = new Node("A");
        Heap a1 = new Heap(Arrays.asList(a));
        assertEquals(Optional.empty(), a1.getMinChild(a));
        // test for an actual min child with the right being empty
        Node b = new Node("B");
        a1 = new Heap(Arrays.asList(a,b));
        b.setValue(1);
        assertEquals(Optional.of(b), a1.getMinChild(a));
        // test for an actual min child with their values being genuinely compared
        Node c = new Node("C");
        a.setValue(2);
        b.setValue(7);
        c.setValue(3);
        a1 = new Heap(Arrays.asList(a,b,c));
        assertEquals(Optional.of(c), a1.getMinChild(a));
    }

    @Test
    void extractMin(){
        // heap with one node
        Node a = new Node("A");
        a.setValue(10);
        Heap a1 = new Heap(Arrays.asList(a));
        assertEquals(a, a1.extractMin());
        // heap with 3 nodes :D
        Node b = new Node("B");
        Node c = new Node("C");
        b.setValue(9);
        c.setValue(19);
        ArrayList<Node> nodes = new ArrayList<>();
        a1 = new Heap(Arrays.asList(a,b,c));
        for(int i = 0; i < 3; i++){
            nodes.add(a1.extractMin());
        }
        assertEquals(b,nodes.get(0));
        assertEquals(a,nodes.get(1));
        assertEquals(c,nodes.get(2));
    }
}