import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UndirectedGraphTest {

    @Test
    void simpleGraph() {
        int inf = Integer.MAX_VALUE;
        Node s = new Node("s", inf);
        Node a = new Node("a", inf);
        Node b = new Node("b", inf);
        Node c = new Node("c", inf);
        Node d = new Node("d", inf);
        Node t = new Node("t", inf);

        Edge sa = new Edge(s, a, 7);
        Edge sc = new Edge(s, c, 8);
        Edge ab = new Edge(a, b, 6);
        Edge ac = new Edge(a, c, 3);
        Edge cb = new Edge(c, b, 4);
        Edge cd = new Edge(c, d, 3);
        Edge bd = new Edge(b, d, 2);
        Edge bt = new Edge(b, t, 5);
        Edge dt = new Edge(d, t, 2);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(s, new ArrayList<>(Arrays.asList(sa, sc)));
        neighbors.put(a, new ArrayList<>(Arrays.asList(ac, ab, sa.flip())));
        neighbors.put(b, new ArrayList<>(Arrays.asList(bd,bt,ab.flip(),cb.flip())));
        neighbors.put(c, new ArrayList<>(Arrays.asList(cb, cd, ac.flip(), sc.flip())));
        neighbors.put(d, new ArrayList<>(Arrays.asList(dt,bd.flip(),cd.flip())));
        neighbors.put(t, new ArrayList<>(Arrays.asList(bt.flip(),dt.flip())));

        UndirectedGraph graph = new UndirectedGraph(neighbors);

        Set<Edge> tree = graph.minimumSpanningTree(s);
        assertEquals(5, tree.size());
        assertTrue(tree.contains(sa));
        assertTrue(tree.contains(ac));
        assertTrue(tree.contains(cd));
        assertTrue(tree.contains(bd.flip()));
        assertTrue(tree.contains(dt));
    }


    @Test
    void primGraph() {
        int inf = Integer.MAX_VALUE;

        Node a = new Node("a", inf); // 0
        Node b = new Node("b", inf); // 1
        Node c = new Node("c", inf); // 2
        Node d = new Node("d", inf); // 3
        Node e = new Node("e", inf); // 4
        Node f = new Node("f", inf); // 5
        Node g = new Node("g", inf); // 6
        Node h = new Node("h", inf); // 7
        Node i = new Node("i", inf); // 8

        Edge ai = new Edge(a, i, 4);
        Edge ad = new Edge(a, d, 2);
        Edge ab = new Edge(a, b ,3);
        Edge bh = new Edge(b, h, 4);
        Edge cf = new Edge(c, f, 1);
        Edge cd = new Edge(c, d, 6);
        Edge de = new Edge(d, e, 1);
        Edge ei = new Edge(e, i , 8);
        Edge fg = new Edge(f, g, 8);
        Edge hc = new Edge(h, c, 2);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(ai, ad, ab)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(ab.flip(), bh)));
        neighbors.put(c, new ArrayList<>(Arrays.asList(cf, cd, hc.flip())));
        neighbors.put(d, new ArrayList<>(Arrays.asList(ad.flip(), cd.flip(), de)));
        neighbors.put(e, new ArrayList<>(Arrays.asList(de.flip(), ei)));
        neighbors.put(f, new ArrayList<>(Arrays.asList(cf.flip(), fg)));
        neighbors.put(g, new ArrayList<>(Collections.singletonList(fg.flip())));
        neighbors.put(h, new ArrayList<>(Arrays.asList(bh.flip(), hc)));
        neighbors.put(i, new ArrayList<>(Arrays.asList(ai.flip(), ei.flip())));

        UndirectedGraph graph = new UndirectedGraph(neighbors);
        Set<Edge> tree = graph.minimumSpanningTree(a);

        for (Edge ed : tree) {
            System.out.println(ed);
        }
        assertEquals(8, tree.size());
        assertTrue(tree.contains(ai));
        assertTrue(tree.contains(ad));
        assertTrue(tree.contains(de));
        assertTrue(tree.contains(ab));
        assertTrue(tree.contains(bh));
        assertTrue(tree.contains(hc));
        assertTrue(tree.contains(cf));
        assertTrue(tree.contains(fg));

    }

    // regular case
    @Test
    void regMST(){
        /*
                a
               / \
              b - c
              /    /
              d   /
              /  /
              e
         */
        int inf = Integer.MAX_VALUE;
        Node a = new Node("a", inf);
        Node b = new Node ("b", inf);
        Node c = new Node ("c", inf);
        Node d = new Node ("d", inf);
        Node e = new Node("e", inf);

        Edge ab = new Edge(a, b, 8);
        Edge ac = new Edge(a, c, 2);
        Edge bc = new Edge(b, c, 3);
        Edge ce = new Edge(c, e, 5);
        Edge bd = new Edge(b, d, 4);
        Edge de = new Edge(d, e, 6);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();

        neighbors.put(a, new ArrayList<>(Arrays.asList(ab, ac)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(bc, bd, ab.flip())));
        neighbors.put(c, new ArrayList<>(Arrays.asList(ce, ac.flip(), bc.flip())));
        neighbors.put(d, new ArrayList<>(Arrays.asList(de, bd.flip())));
        neighbors.put(e, new ArrayList<>(Arrays.asList(de.flip(), ce.flip())));
        UndirectedGraph graph = new UndirectedGraph(neighbors);
        Set<Edge> tree = graph.minimumSpanningTree(a);

        for (Edge ed : tree) {
            System.out.println(ed);
        }
        assertEquals(4, tree.size());
        assertTrue(tree.contains(bc.flip()));
        assertTrue(tree.contains(ce));
        assertTrue(tree.contains(ac));
        assertTrue(tree.contains(bd));

    }

    // testing that it sets the value to INTEGER.MAXVALUE
    @Test
    void settingthevalueatfirstMST(){
         /*
                a
               / \
              b - c
              /    /
              d   /
              /  /
              e
         */
        int inf = Integer.MAX_VALUE;
        Node a = new Node("a", 8);
        Node b = new Node ("b", 10);
        Node c = new Node ("c", 11);

        Edge ab = new Edge(a, b, 8);
        Edge ac = new Edge(a, c, 2);
        Edge bc = new Edge(b, c, 3);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();

        neighbors.put(a, new ArrayList<>(Arrays.asList(ab, ac)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(bc, ab.flip())));
        neighbors.put(c, new ArrayList<>(Arrays.asList(ac.flip(), bc.flip())));
        UndirectedGraph graph = new UndirectedGraph(neighbors);

        assertTrue(neighbors.get(a).get(0).getDestination().getValue() == 10);
        Set<Edge> tree = graph.minimumSpanningTree(a);

        // it wouldn't run the way it does if it didn't set it.
        assertEquals(2, tree.size());
    }

    // a node without any edges >:D
    @Test
    void lonelyNodeMST(){
        int inf = Integer.MAX_VALUE;
        Node a = new Node("a", inf);
        Node b = new Node ("b", inf);
        Node c = new Node ("c", inf);
        Node d = new Node ("d", inf);

        Edge ab = new Edge(a, b, 8);
        Edge ac = new Edge(a, c, 2);
        Edge bc = new Edge(b, c, 3);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();

        neighbors.put(a, new ArrayList<>(Arrays.asList(ab, ac)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(bc, ab.flip())));
        neighbors.put(c, new ArrayList<>(Arrays.asList(ac.flip(), bc.flip())));
        neighbors.put(d, new ArrayList<>());
        UndirectedGraph graph = new UndirectedGraph(neighbors);

        assertThrows(Error.class, () -> {
            Set<Edge> tree = graph.minimumSpanningTree(a);
        });
    }

    // ensures that it picks the smallest edge
    @Test
    void smallestEdgesMST(){
        /*
             B
         4  /    \ 1
        A    1 |     D
          2 \    / 3
            C
         */
        int inf = Integer.MAX_VALUE;
        Node a = new Node("a", inf);
        Node b = new Node ("b", inf);
        Node c = new Node ("c", inf);
        Node d = new Node ("d", inf);

        Edge ab = new Edge(a, b, 4);
        Edge ac = new Edge(a, c, 2);
        Edge bc = new Edge(b, c, 1);
        Edge bd = new Edge(b, d, 1);
        Edge cd = new Edge(c, d, 3);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();

        neighbors.put(a, new ArrayList<>(Arrays.asList(ab, ac)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(bc, ab.flip(), bd)));
        neighbors.put(c, new ArrayList<>(Arrays.asList(ac.flip(), bc.flip(), cd)));
        neighbors.put(d, new ArrayList<>(Arrays.asList(bd.flip(), cd.flip())));

        UndirectedGraph graph = new UndirectedGraph(neighbors);
        Set<Edge> tree = graph.minimumSpanningTree(a);
        assertEquals(3, tree.size());
        assertTrue(tree.contains(bc.flip()));
        assertTrue(tree.contains(ac));
        assertTrue(tree.contains(bd));

        tree = graph.minimumSpanningTree(b);
        assertEquals(3, tree.size());
        assertTrue(tree.contains(bc));
        assertTrue(tree.contains(ac.flip()));
        assertTrue(tree.contains(bd));

    }

}