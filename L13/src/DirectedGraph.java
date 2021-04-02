import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class DirectedGraph {
    private final Hashtable<Node,ArrayList<Edge>> neighbors;
    private final Set<Node> nodes;

    DirectedGraph(Hashtable<Node,ArrayList<Edge>> neighbors) {
        this.neighbors = neighbors;
        this.nodes = neighbors.keySet();
    }

    /**
     * This implements Dijkstra's algorithm
     */
    ArrayList<Node> shortestPath (Node source, Node destination) {
        // TODO
        ArrayList<Edge> edges = new ArrayList<>();
        Node current;
        // reset all the nodes & set value of the source to 0
        for(Node n: nodes){
            n.reset();
        }
        source.setValue(0);
        // creates heap here
        Heap unvisited = new Heap(nodes);
        while(unvisited.getSize() != 0){
            current = unvisited.extractMin();
            if(current.equals(destination)){
                return reverse(destination.followPrevious());
            }
            if(current.isVisited()){
                break;
            }else{
                current.setVisited();
                edges = neighbors.get(current);
                for(Edge e: edges){
                    if(e.getWeight() + current.getValue() < e.getDestination().getValue()){
                        // so you update value of destination and then later compare the value to itself and update if smaller
                        //e.getDestination().setValue(e.getWeight() + current.getValue());
                        unvisited.update(e.getDestination(), e.getWeight()+current.getValue());
                        // so previous is separate of extracting mins
                        e.getDestination().setPrevious(current);
                    }
                }
            }
        }
        // reverses the followPrevious because it is actually reversed from what is wanted
        return reverse(destination.followPrevious());
    }

    static ArrayList<Node> reverse(ArrayList<Node> path){
        ArrayList<Node> rev = new ArrayList<>();
        Node end = path.remove(path.size()-1);
        while(path.size() > 0){
            rev.add(path.remove(path.size()-1));
        }
        rev.add(end);
        return rev;
    }

    // min paths
    ArrayList<Node> minPath(Node source, Node destination){
        for (Node n : nodes) {
            n.reset();
        }
        source.setValue(0);
        source.setPaths(1);
        Heap h = new Heap(nodes);
        // while heap nonempty
        while (h.getSize() != 0) {
            // v = extract min
            var v = h.extractMin();
            // if v visited or if it is dest, break
            if (v.equals(destination)) {
                break;
            }
            if (v.isVisited()) {
                break;
            }
            // mark as visited
            v.setVisited();
            // relax edges
            var edges = neighbors.get(v);
            for (Edge e : edges) {
                var val = v.getValue() + e.getWeight();
                if (val < e.getDestination().getValue()) {
                    h.update(e.getDestination(), val);
                    int amtP = v.getPaths();
                    e.getDestination().setPaths(amtP);
                    e.getDestination().setPrevious(v);
                } else if (val == e.getDestination().getValue()) {
                    e.getDestination().setPaths(e.getDestination().getPaths() + v.getPaths());
                    if (v.getEdges() < e.getDestination().getEdges()) {
                        e.getDestination().setEdges(v.getEdges());
                    }
                }
            }
        }
        return reverse(destination.followPrevious());
    }





    public String toString () {
        return neighbors.toString();
    }
}
