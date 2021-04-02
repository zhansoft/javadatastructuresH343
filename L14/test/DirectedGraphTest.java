import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {

    @Test
    void kosaraju(){
        /*
        a -> b
        |^   |
        v \  v
        d <- c
         */
        Node a = new Node("a", 0);
        Node b = new Node("b", 0);
        Node c = new Node("c", 0);
        Node d = new Node("d", 0);

        Edge ab = new Edge(a, b, 0);
        Edge ad = new Edge(a,d, 0);
        Edge bc = new Edge(b, c, 0);
        Edge ca = new Edge(c, a, 0);
        Edge cd = new Edge(c,d,0);

        Hashtable<Node, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(ab, ad)));
        neighbors.put(b, new ArrayList<>(Collections.singletonList(bc)));
        neighbors.put(c, new ArrayList<>(Arrays.asList(ca, cd)));
        neighbors.put(d, new ArrayList<>());

        DirectedGraph graph = new DirectedGraph(neighbors);
        int flow =  graph.kosaraju();
        assertEquals(flow, 2);

    }
}