import java.util.*;

/**
 *
 * A directed graph represented using adjacency lists.
 *
 * There are two methods to implement in this class: topological sorting
 * maximum flow.
 *
 */
class NoPathE extends Exception {}

public class DirectedGraph {
    private final Set<Node> nodes;
    private Hashtable<Node,ArrayList<Edge>> neighbors;

    DirectedGraph(Hashtable<Node,ArrayList<Edge>> neighbors) {
        this.nodes = neighbors.keySet();
        this.neighbors = neighbors;
    }

    /**
     *
     * This constructor is used to clone the given graph creating
     * new copies of the edges. So the edges in either graph can be
     * updated without affecting the other graph.
     *
     */
    DirectedGraph(DirectedGraph graph) {
        this.nodes = graph.nodes;
        this.neighbors = new Hashtable<>();
        for (Node n : nodes) {
            ArrayList<Edge> ns = new ArrayList<>();
            for (Edge e : graph.neighbors.get(n)) {
                ns.add(new Edge(e.getSource(), e.getDestination(), e.getWeight()));
            }
            neighbors.put(n,ns);
        }
    }

    void setNeighbors (Hashtable<Node,ArrayList<Edge>> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     *
     * An implementation of Dijkstra's algorithm that throws an exception of the
     * destination is not reachable from the source. Otherwise it returns the list
     * of edges forming the shortest path from source to destination.
     *
     */
    ArrayList<Edge> shortestPath (Node source, Node destination) throws NoPathE {
        for (Node n : nodes) n.reset(Integer.MAX_VALUE);
        source.setValue(0);
        Heap heap = new Heap(nodes);
        assert heap.getMin().equals(source);

        while (heap.getSize() > 0) {
            Node current = heap.extractMin();
            if(current.getValue() == Integer.MAX_VALUE){
                break;
            }
            if (current.equals(destination) && destination.getValue() != Integer.MAX_VALUE) {
                return destination.followPreviousEdge();
            }
            if (current.isVisited()) break;
            current.setVisited();
            for (Edge edge : neighbors.get(current)) {
                Node neighbor = edge.getDestination();
                int newWeight =  current.getValue() + edge.getWeight();
                if (newWeight < neighbor.getValue()) {
                    heap.update(neighbor,newWeight);
                    neighbor.setPreviousEdge(edge);
                }
            }
        }
        throw new NoPathE ();
    }

    /**
     *
     * This method has a similar structure to Dijkstra's algorithm with the
     * following differences:
     *
     *   - in the initialization phase, the value of each node is set to
     *     its in-degree (number of edges pointing to the node)
     *   - when extracting the minimum node from the heap, if the value is
     *     not 0 the algorithm throws an error as this indicates a cycle in
     *     the graph
     *   - instead of marking the extracted nodes as visited, we collect
     *     them into a queue
     *   - when relaxing an edge v --> w, the value of w is reduced by 1.
     *
     */
    public Queue<Node> topologicalSort () {
        // TODO
        // so basically you can't go backwards but only forwards
        // think like prerequisites before taking a class like 212 before 343
        // initialization phase: set to n-degree
        for (Node n : nodes){
            int ndegree = 0;
            // this double checks
            for(Node x: nodes){
                for(Edge e: neighbors.get(x)){
                    if(e.getDestination().equals(n)){
                        ndegree++;
                    }
                }
            }
            n.reset(ndegree);
        }
        Heap heap = new Heap(nodes);
        Queue<Node> q = new LinkedList<>();
        //when extracting the min node form heap, throws error if value is not 0
        while (heap.getSize() > 0) {
            Node current = heap.extractMin();
            if(current.getValue() != 0){
                throw new Error("cycle");
            }
            q.add(current); // offer could work
            // reduce the n degree of that neighbor [update the heap] so it subrtacts 1 from the value of the neighbors
            for (Edge edge : neighbors.get(current)) {
                Node neighbor = edge.getDestination();
                heap.update(neighbor, neighbor.getValue() - 1);
            }
        }
        // return queue here
        return q;
    }

    /**
     *
     * This algorithm maintains two intermediate structures:
     *   - a Hashtable<Edge,Edge> to represent the flow graph
     *   - a DirectedGraph to represent the residual graph
     *
     * The flow graph is represented in an unusual way as
     * a Hashtable<Edge,Edge>. The key to this hashtable is an
     * edge where we only care about the identity of the edge
     * (i.e., its source and destination but not its weight).
     * The values in the hashtable are edges in which the weights
     * are significant. So if the original graph had an edge 'e'
     * between nodes A and B, the key 'e' may be associated with
     * edges from A to B with different weights at different times.
     * Initially all these edges would have weight 0. The final
     * result of the algorithm is the collection of values in the
     * hashtable.
     *
     * The residual graph is initially created by cloning the
     * current graph using the special copy constructor.
     *
     * We then start a while (true) loop that terminates when
     * Dijkstra's algorithm fails to find a path from source
     * to destination in the residual graph.
     *
     * Each iteration of the loop performs the following actions:
     *   - compute the shortest path P from source to destination
     *     in the residual graph
     *   - get the minimum weight of any edge along this path P
     *   - update the flow graph by adding that minimum weight to
     *     all edges in P
     *   - recompute the residual graph as follows:
     *       - create a new hashtable of adjacency lists which
     *         will be used to update the residual graph by calling
     *         setNeighbors.
     *       - for each edge in the original graph, compute its new
     *         weight as original weight minus its weight in the
     *         flow graph
     *       - if the new weight is not zero, add the edge with its
     *         weight to the residual graph [subtraction]
     *       - for each edge A->B in the flow graph whose weight is
     *         not 0, add the edge B->A to the residual graph
     *
     *
     */
    Collection<Edge> maximumFlow (Node source, Node destination) {
        // TODO
        // hashtable use - store edges
        Hashtable<Edge,Edge> flow = new Hashtable<>();
        // sets the edge in flow and sets to 0
        if(source == destination){
            throw new Error("Max flow 0 dummy");
        }
        for(Node n : nodes){
            for(Edge e: neighbors.get(n)){
                Edge newedge = new Edge(e.getSource(), e.getDestination(), 0);
                flow.put(newedge, newedge);
            }
        }
        DirectedGraph residual = new DirectedGraph(this);
        ArrayList<Edge> sp;
        int curflow;
        while(true){
            // if shortestPath throws an error
            try{
                // sp = original table
                sp = residual.shortestPath(source, destination);
                // getting minimum weight
                curflow = Integer.MAX_VALUE;
                for(Edge e:sp){
                    if(e.getWeight() < curflow){
                        curflow = e.getWeight();
                    }
                }

                // updating the weight of the path & updates the flow??
                // key: edge we want to find the flow of
                // value: edge another edge but the weight of the edge contains the new updated flow
                Edge updatededge;
                for(Edge e: sp){
                    updatededge = new Edge(e.getSource(), e.getDestination(), flow.get(e).getWeight() + curflow);
                    flow.put(e,updatededge);
                }

                // recomputing the residual graph - recreates the neighbors bc max capacity has been reduced
                Hashtable<Node, ArrayList<Edge>> temp = new Hashtable<>();
                for(Node n: nodes){
                    ArrayList<Edge> nodeedge = new ArrayList<>();
                    int newcapacity = 0;
                    for(Edge e: neighbors.get(n)){
                        newcapacity = e.getWeight() - flow.get(e).getWeight(); // not always the currentflow could be more from previous
                        updatededge = new Edge(e.getSource(), e.getDestination(), newcapacity);
                        if(newcapacity != 0){
                            nodeedge.add(updatededge);
                        }
                    }
                    temp.put(n, nodeedge);
                }

                // flip and adding to temp because when u flip it adds the reverse edge but also doesn't necessitate a cycle
                // adds the current flow (with previous shit) in it
                for(Edge e:flow.values()){
                    if(e.getWeight() != 0){
                        temp.get(e.getDestination()).add(e.flip()); // gets the destinations' arraylist, add's to it, a reversed edge
                    }
                }

                // setting the whatever
                residual.setNeighbors(temp);
            }catch(NoPathE e){
                break;
            }
        }
        // return hashtable values
        return flow.values();
    }

    public String toString () {
        return neighbors.toString();
    }
    
}
