import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The classes Heap and Node are mutually defined. The heap is a
 * collection of nodes ordered by their values (min at the root).
 * Each node has a pointer back to its heap and it also maintains
 * a private variable specifying its index in the heap.
 *
 * When calculating shortest paths, the value field maintains the
 * best distance from the distance that has been computed so far.
 * A node maintains a few other private variables that are
 * used in the calculation of shortest paths: a flag
 * 'visited' that determines if the node has already been
 * visited in this graph traversal, and a variable 'previous'
 * that points to the previous node along the current
 * best known shortest path.
 *
 * There is only one method to write in this class: followPrevious.
 *
 */
public class Node implements Comparable<Node>, TreePrinter.PrintableNode {
    private final String id;
    private boolean visited;
    private int value;
    private Node previous;
    private Heap heap;
    private int heapIndex;
    private int paths;
    private int edges;

    Node (String id) {
        this.id = id;
        reset();
    }

    void reset () {
        this.visited = false;
        this.value = Integer.MAX_VALUE;
        this.previous = null;
        this.heap = null;
        this.heapIndex = -1;
        this.paths = 0;
        this.edges = 0;
    }

    boolean isVisited () { return visited; }

    void setVisited () { visited = true; }

    void setHeap (Heap heap) { this.heap = heap; }

    int getValue() { return value; }

    void setValue(int distance) { this.value = distance; }

    void setPrevious (Node previous) { this.previous = previous; }

    int getHeapIndex () { return heapIndex; }

    void setHeapIndex (int index) { this.heapIndex = index; }

    void setPaths(int num){
        this.paths = num;
    }

    int getPaths(){
        return this.paths;
    }

    void setEdges(int num){
        this.edges = num;
    }

    int getEdges(){
        return this.edges;
    }

    /**
     * Follows the previous pointers from this node until we encounter a null
     * pointer. Returns the list of nodes encountered with the current node
     * at the end of the list.
     */
    ArrayList<Node> followPrevious () {
        // TODO
        ArrayList<Node> prev = new ArrayList<>();
        Node current = this;
        Node temp = current;
        /*
        try{
            temp = this;
            while(temp.previous.getValue() != Integer.MAX_VALUE){
                prev.add(temp.previous);
                temp = temp.previous;
            }
        }catch(NullPointerException e){
            prev.add(current);
            return prev;
        }
        return prev;
         */
    while(temp.previous != null){
        prev.add(temp.previous);
        temp = temp.previous;
    }
    prev.add(current);
    return prev;
    }

    public int compareTo (Node other) {
        return Integer.compare(value,other.value);
    }

    public boolean equals (Object o) {
        if (o instanceof Node) {
            Node other = (Node)o;
            return id.equals(other.id);
        }
        return false;
    }

    public String toString () { return id; }

    // static methods

    static Node min (Node a, Node b) {
        return a.compareTo(b) < 0 ? a : b;
    }

    // Printable

    public TreePrinter.PrintableNode getLeft() {
        return heap.getLeftChild(this).orElse(null);
    }

    public TreePrinter.PrintableNode getRight() {
        return heap.getRightChild(this).orElse(null);
    }

    public String getText() {
        return String.format("%s[%d]", id, value);
    }
}
