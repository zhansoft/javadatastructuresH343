import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Red-black tree invariants:
 *
 * The root and the empty nodes of the tree are black.
 *
 * A red node has only black children.
 *
 * Every path from the root node to an empty node contains the same
 * number of black nodes.
 */

class EmptyRBE extends Exception {
}

enum Color {RED, BLACK}

abstract class RedBlackT<E> implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyRBE ERBX = new EmptyRBE();

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract E RBData() throws EmptyRBE;

    abstract RedBlackT<E> RBLeft() throws EmptyRBE;

    abstract RedBlackT<E> RBRight() throws EmptyRBE;

    abstract boolean isEmpty();

    abstract int RBHeight();

    //-------------
    // Main methods
    //-------------

    // The method does two things simultaneously:
    // * it checks that the tree is a valid black tree; if not it returns "empty"
    // * if the tree is valid, it returns the black height of the node
    abstract Optional<Integer> isValidBlackTree();

    // The method does two things simultaneously:
    // * it checks that the tree is a valid red tree; if not it returns "empty"
    // * if the tree is valid, it returns the black height of the node
    abstract Optional<Integer> isValidRedTree();

    // A red-black tree is valid if the current node is black or red
    // and the rest of the tree is valid
    Optional<Integer> isValidTree() {
        Optional<Integer> b = isValidBlackTree();
        Optional<Integer> r = isValidRedTree();
        if (b.isPresent()) return b;
        return r;
    }
}

class EmptyRB<E> extends RedBlackT<E> {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    E RBData() throws EmptyRBE {
        throw ERBX;
    }

    RedBlackT<E> RBLeft() throws EmptyRBE {
        throw ERBX;
    }

    RedBlackT<E> RBRight() throws EmptyRBE {
        throw ERBX;
    }

    boolean isEmpty() {
        return true;
    }

    // Note the convention. The black height of an empty tree is 1 because
    // empty trees are black
    int RBHeight() {
        return 1;
    }

    //--------------------------
    // Main methods
    //--------------------------

    Optional<Integer> isValidBlackTree() {
        return Optional.of(1);
    }

    Optional<Integer> isValidRedTree() {
        return Optional.empty();
    }

    //--------------------------
    // Override
    //--------------------------

    public boolean equals(Object o) {
        return (o instanceof EmptyRB);
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }

    public TreePrinter.PrintableNode getRight() {
        return null;
    }

    public String getText() {
        return "";
    }
}

//-----------------------------------------------------------------------

class RBNode<E> extends RedBlackT<E> {
    private final E data;
    private final Color color;
    private final RedBlackT<E> left;
    private final RedBlackT<E> right;
    private final int height; // this is the regular height --- not the black height

    RBNode(E data, Color color, RedBlackT<E> left, RedBlackT<E> right) {
        this.data = data;
        this.color = color;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(left.RBHeight(), right.RBHeight());
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    E RBData() {
        return data;
    }

    RedBlackT<E> RBLeft() {
        return left;
    }

    RedBlackT<E> RBRight() {
        return right;
    }

    boolean isEmpty() {
        return false;
    }

    int RBHeight() {
        return height;
    }

    String getColor(){
        return (this.color == Color.BLACK)? "Black" : "Red";
    }

    //--------------------------
    // Main methods
    //--------------------------

    Optional<Integer> isValidBlackTree() {
        // TODO
        // The method does two things simultaneously:
        // * it checks that the tree is a valid black tree; if not it returns "empty"
        // * if the tree is valid, it returns the black height of the node
        // testing* & add 1 to height
        // black trees are not valid if it's not black
        // Optional is a slot for a value; if it does have something in it then it has to eb an integer in this case

        if(this.color.equals(Color.RED)){
            return Optional.empty();
        }
        else{
            try {
                int l = this.left.isValidTree().get();
                int r = this.right.isValidTree().get();
                if (l == r) {
                    return Optional.of(l+ 1);
                } else {
                    return Optional.empty();
                }
            }
            catch(NoSuchElementException e){
                return Optional.empty();
            }
        }
    }

    Optional<Integer> isValidRedTree() {
        // TODO
        // The method does two things simultaneously:
        // * it checks that the tree is a valid red tree; if not it returns "empty"
        // * if the tree is valid, it returns the black height of the node
        // testing*
        // red trees is not valid if it's not red
        if(this.color.equals(Color.BLACK)){
            return Optional.empty();
        }else{
            try {
                // children valid trees
                int l = this.left.isValidBlackTree().get();
                int r = this.right.isValidBlackTree().get();
                if (l == r) {
                    return Optional.of(l);
                } else {
                    return Optional.empty();
                }
            }
            catch(NoSuchElementException e){
                return Optional.empty();
            }
        }
    }

    //--------------------------
    // Override
    //--------------------------

    public boolean equals(Object o) {
        if (o instanceof RBNode) {
            RBNode other = (RBNode) o;
            return data == other.data && left.equals(other.left) && right.equals(other.right);
        }
        return false;
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return left.isEmpty() ? null : left;
    }

    public TreePrinter.PrintableNode getRight() {
        return right.isEmpty() ? null : right;
    }

    public String getText() {
        return data+"_"+color;
    }
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
