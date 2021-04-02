import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 *
 * A undirected graph represented using adjacency lists. There is one method
 * to implement in this class which computes a minimum spanning tree.
 *
 */
public class UndirectedGraph {
    private final Hashtable<Node, ArrayList<Edge>> neighbors;
    private final Set<Node> nodes;

    UndirectedGraph(Hashtable<Node,ArrayList<Edge>> neighbors) {
        this.neighbors = neighbors;
        this.nodes = neighbors.keySet();
    }

    /**
     * The outline of this algorithm is quite similar to Dijkstra's
     * shortest path algorithm:
     *
     *   - Initialize the nodes values and put them in a heap
     *   - Repeatedly extract the min node from the heap and
     *     relax its outgoing edges. If the weight of an edge
     *     is less than the current value at its destination,
     *     then the destination's value is updated and its
     *     previous pointer is updated.
     *
     * The result will be the collection of all edges stored
     * in the previous pointers.
     *
     */

    // isp with cables underground: least amount spent going to every node ?
    // shortest edge to that node so long as it's visited once
    Set<Edge> minimumSpanningTree (Node source) {
        // TODO
        for (Node n : nodes) n.reset(Integer.MAX_VALUE);
        source.setValue(0);
        Heap heap = new Heap(nodes);
        Set<Edge> newedge = new HashSet<>();


        while (heap.getSize() > 0) {
            Node current = heap.extractMin();
            if (current.isVisited()) break;
            current.setVisited();
            if(neighbors.get(current).isEmpty() && current.getPreviousEdge() == null){
                throw new Error("No edge");
            }
            for (Edge edge : neighbors.get(current)) {
                if(!edge.getDestination().isVisited()){
                    Node neighbor = edge.getDestination();
                    int newWeight = edge.getWeight();
                    if (newWeight < neighbor.getValue()) {
                        heap.update(neighbor,newWeight);
                        neighbor.setPreviousEdge(edge);
                    }
                }
            }
        }
        // empty set down here
        for(Node n:nodes){
            System.out.println(n);
            if(n.getPreviousEdge()!= null){
                newedge.add(n.getPreviousEdge());
            }
            System.out.println(newedge);
        }
        return newedge;


    }

    public String toString () {
        return neighbors.toString();
    }
}
