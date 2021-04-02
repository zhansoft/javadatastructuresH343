import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * The classes Heap and Node are mutually defined. The heap is a
 * collection of nodes ordered by their values (min at the root).
 * Each node has a pointer back to its heap and it also maintains
 * a private variable specifying its index in the heap.
 */
public class Heap {
    private final ArrayList<Node> nodes;
    private int size;

    Heap (Collection<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
        this.size = nodes.size();
        for (int i=0; i<size; i++) {
            this.nodes.get(i).setHeap(this);
            this.nodes.get(i).setHeapIndex(i);
        }
        heapify();
    }

    ArrayList<Node> getNodes () { return nodes; }

    int getSize () { return size; }

    // main methods

    /**
     * The method iterates over the first half of the array towards 0
     * calling moveDown on each node.
     */
    void heapify () {
        // TODO
        for(int i = getNodes().size()/2; i >= 0; i--){
            moveDown(getNodes().get(i));
        }
    }

    Node getMin () { return nodes.get(0); }

    /**
     * The next few methods are self-explanatory. They either
     * return the appropriate node of empty if the request takes
     * you outside the array bounds.
     */
    Optional<Node> getParent (Node n) {
        // TODO
        // (k-1)/2
        try{
            //return Optional.of(n.followPrevious().get(0));
            return Optional.of(getNodes().get((n.getHeapIndex()-1)/2));
        }catch(IndexOutOfBoundsException e){
            return Optional.empty();
        }
    }

    Optional<Node> getLeftChild (Node n) {
        // TODO: array shid
        // 2*k + 1index
        try{
            return Optional.of(getNodes().get(2*n.getHeapIndex() + 1));
        }catch(IndexOutOfBoundsException e){
            return Optional.empty();
        }
    }

    Optional<Node> getRightChild (Node n) {
        // TODO
        // 2*k + 2
        try{
            return Optional.of(getNodes().get(2*n.getHeapIndex() + 2));
        }catch(IndexOutOfBoundsException e){
            return Optional.empty();
        }
    }

    Optional<Node> getMinChild (Node n) {
        // TODO
        // get leftchild and rightchild & compare return lesser value
        // check if empty
        Optional<Node> left = getLeftChild(n);
        Optional<Node> right = getRightChild(n);
        if(left.isEmpty()) {
            return right;
        }
        else if(right.isEmpty()){
            return left;
        }
        return (left.get().getValue() < right.get().getValue())? left: right;
    }

    /**
     * Simply swap the two nodes in the array.
     */
    void swap (Node n1, Node n2) {
        // TODO
        //Node temp = n1;
        int n1index = n1.getHeapIndex();
        int n2index = n2.getHeapIndex();
        nodes.set(n1index, n2);
        nodes.set(n2index, n1);
        n1.setHeapIndex(n2index);
        n2.setHeapIndex(n1index);
    }

    /**
     * If the minimum child is smaller than the current node, swap them, and continue
     * moving down recursively.
     *
     */
    void moveDown (Node n) {
        // TODO
        Optional<Node> min = getMinChild(n);
        // check if min is empty or not
//        Node temp = n;
        if(!min.isEmpty() && min.get().getValue() <  n.getValue()){
            swap(min.get(), n);
            // n becomes min
            moveDown(n);
        }
    }

    /**
     * If the current node is smaller than its parent, swap them, and continue
     * moving up recursively.
     */
    void moveUp (Node n) {
        // TODO
        Optional<Node> parent = getParent(n);
        if(!parent.isEmpty() && n.getValue() < parent.get().getValue()){
            swap(n, parent.get());
            moveUp(n);
        }
    }

    void update (Node n, int value) {
        n.setValue(value);
        moveUp(n);
    }

    /**
     * Deletes and returns the minimum node which is located
     * at position 0.
     * To delete the minimum node, we move the last node in the
     * array to the first position, and call move down.
     */
    Node extractMin () {
        // TODO
        Node min = getMin();
        if(this.size == 1){
            this.size -= 1;
            return min;
        }
        else{
            this.size -= 1;
            Node last = getNodes().remove(getNodes().size()-1);
            getNodes().set(0, last);
            last.setHeapIndex(0);
            moveDown(getNodes().get(0));
            return min;
        }
    }
}
