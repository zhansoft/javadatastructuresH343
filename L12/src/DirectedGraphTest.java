import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {

    @org.junit.jupiter.api.Test
    void BFS() {
        Hashtable<Integer, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(0, new ArrayList<>(Arrays.asList(new Edge(0,1),new Edge(0,2))));
        neighbors.put(1, new ArrayList<>(Collections.singletonList(new Edge(1,2))));
        neighbors.put(2, new ArrayList<>(Arrays.asList(new Edge(2,0),new Edge(2,3))));
        neighbors.put(3, new ArrayList<>(Collections.singletonList(new Edge(3,3))));
        DirectedGraph g = new DirectedGraph(neighbors);

        ArrayList<Integer> actual = g.BFS(0);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,1,2,3));
        assertEquals(expected, actual);

        actual = g.BFS(2);
        expected = new ArrayList<>(Arrays.asList(2,0,3,1));
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void DFS() {
        Hashtable<Integer, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(0, new ArrayList<>(Arrays.asList(new Edge(0,1),new Edge(0,2))));
        neighbors.put(1, new ArrayList<>(Collections.singletonList(new Edge(1,2))));
        neighbors.put(2, new ArrayList<>(Arrays.asList(new Edge(2,0),new Edge(2,3))));
        neighbors.put(3, new ArrayList<>(Collections.singletonList(new Edge(3,3))));
        DirectedGraph g = new DirectedGraph(neighbors);

        ArrayList<Integer> actual = g.DFS(0);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,2,3,1));
        assertEquals(expected, actual);

        actual = g.DFS(2);
        expected = new ArrayList<>(Arrays.asList(2,3,0,1));
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void canvasbfs() {
        Hashtable<Integer, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(0, new ArrayList<>(Arrays.asList(new Edge(0,3), new Edge(0, 1), new Edge(0,2))));
        neighbors.put(1, new ArrayList<>(Arrays.asList(new Edge(1,0), new Edge(1, 5))));
        neighbors.put(2, new ArrayList<>(Collections.singletonList(new Edge(2,5))));
        neighbors.put(3, new ArrayList<>(Collections.singletonList(new Edge(3,4))));
        neighbors.put(4, new ArrayList<>(Collections.singletonList(new Edge(4,6))));
        neighbors.put(5, new ArrayList<>(Collections.singletonList(new Edge(5,1))));
        neighbors.put(6, new ArrayList<>(Collections.singletonList(new Edge(6,5))));
        DirectedGraph g = new DirectedGraph(neighbors);

        ArrayList<Integer> actual = g.BFS(0);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,3,1,2,4,5,6));
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void canvasdfs() {
        Hashtable<Integer, ArrayList<Edge>> neighbors = new Hashtable<>();
        neighbors.put(0, new ArrayList<>(Arrays.asList(new Edge(0,3), new Edge(0, 1), new Edge(0,2))));
        neighbors.put(1, new ArrayList<>(Arrays.asList(new Edge(1,0), new Edge(1, 5))));
        neighbors.put(2, new ArrayList<>(Collections.singletonList(new Edge(2,5))));
        neighbors.put(3, new ArrayList<>(Collections.singletonList(new Edge(3,4))));
        neighbors.put(4, new ArrayList<>(Collections.singletonList(new Edge(4,6))));
        neighbors.put(5, new ArrayList<>(Collections.singletonList(new Edge(5,1))));
        neighbors.put(6, new ArrayList<>(Collections.singletonList(new Edge(6,5))));
        DirectedGraph g = new DirectedGraph(neighbors);

        ArrayList<Integer> actual = g.DFS(0);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,2,5,1,3,4,6));
        assertEquals(expected, actual);
    }
}