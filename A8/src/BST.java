import java.util.Iterator;
import java.util.NoSuchElementException;

//-----------------------------------------------------------------------
// Empty BST exception

class EmptyBSTE extends Exception {}

//-----------------------------------------------------------------------
// Abstract BST class

abstract class BST implements TreePrinter.PrintableNode, Iterable<Integer> {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyBSTE EBSTX = new EmptyBSTE();

    static BST EBST = new EmptyBST();

    static BST BSTLeaf(int elem) {
        return new BSTNode(elem, EBST, EBST);
    }

    static AVL toAVL (BST bst) {
        AVL avl = AVL.EAVL;
        for (int d : bst) avl = avl.AVLinsert(d);
        return avl;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int BSTData() throws EmptyBSTE;

    abstract BST BSTLeft() throws EmptyBSTE;

    abstract BST BSTRight() throws EmptyBSTE;

    abstract int BSTHeight();

    abstract boolean isEmpty();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean BSTfind (int key);

    abstract BST BSTinsert(int key);

    abstract BST BSTdelete(int key) throws EmptyBSTE;

    abstract Pair<Integer, BST> BSTdeleteLeftMostLeaf() throws EmptyBSTE;
}

//-----------------------------------------------------------------------

class EmptyBST extends BST {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int BSTData() throws EmptyBSTE {
        throw EBSTX;
    }

    BST BSTLeft() throws EmptyBSTE {
        throw EBSTX;
    }

    BST BSTRight() throws EmptyBSTE {
        throw EBSTX;
    }

    int BSTHeight() {
        return 0;
    }

    boolean isEmpty () { return true; }

    //--------------------------
    // Main methods
    //--------------------------

    boolean BSTfind(int key) {
        return false;
    }

    BST BSTinsert(int key) {
        return BSTLeaf(key);
    }

    BST BSTdelete(int key) throws EmptyBSTE {
        throw EBSTX;
    }

    Pair<Integer, BST> BSTdeleteLeftMostLeaf() throws EmptyBSTE {
        throw EBSTX;
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

    //--------------------------
    // Iterable interface
    //--------------------------

    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            public boolean hasNext() {
                return false;
            }

            public Integer next() {
                throw new NoSuchElementException();
            }
        };
    }
}

//-----------------------------------------------------------------------
// Non-empty tree case

class BSTNode extends BST {
    private int data;
    private BST left, right;
    private int height;

    BSTNode(int data, BST left, BST right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(left.BSTHeight(), right.BSTHeight());
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int BSTData() {
        return data;
    }

    BST BSTLeft() {
        return left;
    }

    BST BSTRight() {
        return right;
    }

    int BSTHeight() {
        return height;
    }

    boolean isEmpty () { return false; }

    //--------------------------
    // Main methods
    //--------------------------

    boolean BSTfind(int key) {
        // TODO
        return data == key || left.BSTfind(key) || right.BSTfind(key);
    }

    BST BSTinsert(int key) {
        // TODO
        if (key < data) {
            left = left.BSTinsert(key);
            height = 1 + Math.max(left.BSTHeight(), right.BSTHeight());
        } else {
            right = right.BSTinsert(key);
            height = 1 + Math.max(left.BSTHeight(), right.BSTHeight());
        }
        return this;
    }

    BST BSTdelete(int key) throws EmptyBSTE {
        // TODO
        if (BSTfind(key)) {
            if (key < data) {
                return new BSTNode(data, left.BSTdelete(key), right);
            } else if (key > data) {
                return new BSTNode(data, left, right.BSTdelete(key));
            } else {
                try {
                    var leftmost = right.BSTdeleteLeftMostLeaf();
                    return new BSTNode(leftmost.getFirst(), this.left, leftmost.getSecond());
                } catch (EmptyBSTE e) {
                    return left;
                }
            }
        } else {
            return new BSTNode(data, left, right);
        }
    }

    Pair<Integer, BST> BSTdeleteLeftMostLeaf() {
        // TODO
        try {
            if (!BSTLeft().isEmpty()) {
                var p = left.BSTdeleteLeftMostLeaf();
                return new Pair<>(p.getFirst(), new BSTNode(data, p.getSecond(), right));
            } else {
                return new Pair<>(data, right);
            }
        } catch (EmptyBSTE e) {
            return new Pair<>(data, right);
        }
    }

    public boolean equals (Object o) {
        if (o instanceof BSTNode) {
            BSTNode other = (BSTNode) o;
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
        return String.valueOf(data);
    }

    //--------------------------
    // Iterable interface
    //--------------------------

    public Iterator<Integer> iterator() {
        // TODO
        return new Iterator<>() {
            boolean visited = false;
            boolean hasNext = true;
            Iterator<Integer> left = BSTLeft().iterator();
            Iterator<Integer> right = BSTRight().iterator();

            public boolean hasNext() {
                return hasNext;
            }

            public Integer next() {
                if (left.hasNext()) {
                    return left.next();
                } else if (!visited) {
                    visited = true;
                    hasNext = right.hasNext();
                    return BSTNode.this.BSTData();
                } else {
                    Integer ans = right.next();
                    hasNext = right.hasNext();
                    return ans;
                }
            }
        };
    }
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
