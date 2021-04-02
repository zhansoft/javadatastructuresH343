import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {

    @Test
    void simpleGraph () {
        Node s = new Node("s");
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node f = new Node("f");
        Node t = new Node("t");

        Edge sa = new Edge(s,a,3);
        Edge sb = new Edge(s,b,4);
        Edge ab = new Edge(a,b,6);
        Edge bf = new Edge(b,f,5);
        Edge af = new Edge(a,f,7);
        Edge ac = new Edge(a,c,2);
        Edge cf = new Edge(c,f,1);
        Edge ct = new Edge(c,t,8);
        Edge ft = new Edge(f,t,4);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(s, new ArrayList<>(Arrays.asList(sa,sb)));
        neighbors.put(a, new ArrayList<>(Arrays.asList(ab,af,ac)));
        neighbors.put(b, new ArrayList<>(Collections.singletonList(bf)));
        neighbors.put(c, new ArrayList<>(Arrays.asList(cf,ct)));
        neighbors.put(f, new ArrayList<>(Collections.singletonList(ft)));
        neighbors.put(t, new ArrayList<>());

        DirectedGraph graph = new DirectedGraph(neighbors);

        ArrayList<Node> path = graph.shortestPath(s,t);
        assertEquals(5,path.size());
        System.out.println(path);
        assertEquals(s,path.get(0));
        assertEquals(a,path.get(1));
        assertEquals(c,path.get(2));
        assertEquals(f,path.get(3));
        assertEquals(t,path.get(4));

    }


    // graph essentially tests shortest path, heapify, moveUp, moveDown, swap :D
    // so i do one more test >:D
    // spells kailen
    @Test
    void yeeeee(){
        Node k = new Node("k");
        Node a = new Node("a");
        Node i = new Node("i");
        Node l = new Node("l");
        Node e = new Node("e");
        Node n = new Node("n");
        Node p = new Node("p");
        Node s = new Node("s");

        Edge ka = new Edge(k,a,1);
        Edge ap = new Edge(a,p,6);
        Edge ai = new Edge(a,i,1);
        Edge al = new Edge(a,l, 10);
        Edge is = new Edge(i,s, 9);
        Edge il = new Edge(i,l, 1);
        Edge le = new Edge(l,e,1);
        Edge en = new Edge(e,n, 1);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(k, new ArrayList<>(Arrays.asList(ka)));
        neighbors.put(a, new ArrayList<>(Arrays.asList(ap,ai,al)));
        neighbors.put(i, new ArrayList<>(Arrays.asList(is,il)));
        neighbors.put(l, new ArrayList<>(Collections.singletonList(le)));
        neighbors.put(e, new ArrayList<>(Collections.singletonList(en)));

        DirectedGraph graph = new DirectedGraph(neighbors);

        ArrayList<Node> path = graph.shortestPath(k,n);
        assertEquals(6,path.size());
        System.out.println(path);
        assertEquals(k,path.get(0));
        assertEquals(a,path.get(1));
        assertEquals(i,path.get(2));
        assertEquals(l,path.get(3));
        assertEquals(e,path.get(4));
        assertEquals(n,path.get(5));
    }

    @Test
    void minPath(){
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");

        // abde, acde, abe, ace
        Edge ab = new Edge(a, b,1);
        Edge ac = new Edge(a,c,1);
        Edge bc = new Edge(b,c,1);
        Edge bd = new Edge(b,d, 1);
        Edge be = new Edge(b,e, 1);
        Edge cd = new Edge(c,d, 1);
        Edge ce = new Edge(c,e, 1);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(ab,ac)));
        neighbors.put(b, new ArrayList<>(Arrays.asList(bc,bd,be)));
        neighbors.put(c, new ArrayList<>(Arrays.asList(cd,ce)));

        DirectedGraph graph = new DirectedGraph(neighbors);

        ArrayList<Node> path = graph.minPath(a,e);
        assertEquals(3,path.size());
        System.out.println(path);
    }
}