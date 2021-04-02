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
    
    public int kosaraju(){
        for(Node n: nodes){
            n.reset(0);
        }
        Stack<Node> stack = new Stack<>();
        int numSCC = 0;
        DirectedGraph residual = new DirectedGraph(this);

        // dfs here essentially to get the post-order
        for(Node n : residual.nodes){
            if(!n.isVisited()){
                stack = findOrder(n, stack, residual);
            }
        }

        //reverses all the edges
        Hashtable<Node, ArrayList<Edge>> temp = new Hashtable<>();
        for(Node n:residual.nodes){
            temp.put(n, new ArrayList<>());
        }
        for(Node n: residual.nodes){
            for(Edge e :residual.neighbors.get(n)){
                temp.get(e.getDestination()).add(new Edge(e.getDestination(), e.getSource(), 0));
            }
        }
        residual.setNeighbors(temp);

        // set the residual nodes to unvisited
        for(Node n: residual.nodes){
            n.reset(0);
        }

        //2nd dfs
        while(!stack.isEmpty()){
            Node curr = stack.pop();
            if(!curr.isVisited()){
                numSCC++;
                dfs(curr, stack, residual);
            }
        }
        // returns the number of SCC here
        return numSCC;
    }

    Stack<Node> findOrder(Node n, Stack<Node> stack, DirectedGraph g){
        n.setVisited();
        for(Edge e:g.neighbors.get(n)){
            if(!e.getDestination().isVisited()){
                findOrder(e.getDestination(), stack, g);
            }
        }
        stack.push(n);
        return stack;
    }

    void dfs(Node n, Stack<Node> stack, DirectedGraph residual){
        n.setVisited();
        for(Edge e: residual.neighbors.get(n)){
            if(!e.getDestination().isVisited()){
                dfs(e.getDestination(), stack, residual);
            }
        }
        /*if(!n.isVisited()){
            n.setVisited();
            for(Edge e:neighbors.get(n)){
                stack.push(e.getDestination());
            }
            while(!stack.isEmpty()){
                Node cur = stack.pop();
                if(!cur.isVisited()){
                    cur.setVisited();
                    for(Edge e:neighbors.get(cur)){
                        if(!e.getDestination().isVisited()){
                            stack.push(e.getDestination());
                        }
                    }
                }
            }
        }*/
    }

    public String toString () {
        return neighbors.toString();
    }
    
}
