import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Array;
import java.util.*;

public class DirectedGraph {
    private final Hashtable<Integer,ArrayList<Edge>> neighbors;
    private final Set<Integer> nodes;

    DirectedGraph(Hashtable<Integer,ArrayList<Edge>> neighbors) {
        this.neighbors = neighbors;
        this.nodes = neighbors.keySet();
    }

    ArrayList<Integer> BFS (int source) {
        //TODO
        /*
        Node -> Add edges to the back -> First node -> Add edges to back
         */
        ArrayList<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(source);
        //need to get the edges from the visited
        for(Edge e:neighbors.get(source)){
            queue.add(e.getDestination());
        }
        int node;
        while(!queue.isEmpty()){
            // visit a node
            node = queue.remove();
            if(!visited.contains(node)){
                visited.add(node);
                // add its edges to the queue
                for(Edge e:neighbors.get(node)){
                    // ensures that destination is not in the linkedlist
                    if(!visited.contains(e.getDestination())){
                        queue.add(e.getDestination());
                    }
                }
            }
        }
	    return visited;
    }

    ArrayList<Integer> DFS (int source) {
        //TODO
        //explores a node then its first edge and that first edge
        ArrayList<Integer> visited = new ArrayList<>();
        Stack<Integer> queue = new Stack<>();
        visited.add(source);
        //need to get the edges from the visited
        for(Edge e:neighbors.get(source)){
            queue.push(e.getDestination());
        }
        int node;
        while(!queue.isEmpty()){
            // visit a node
            node = queue.pop();
            if(!visited.contains(node)){
                visited.add(node);
                // add its edges to the stack i n front of the previous edges
                for(Edge e:neighbors.get(node)){
                    // ensures that destination is not in the linkedlist
                    if(!visited.contains(e.getDestination())){
                        queue.push(e.getDestination());
                    }
                }
            }
        }
        return visited;
    }

    public String toString () {
        return neighbors.toString();
    }
}
