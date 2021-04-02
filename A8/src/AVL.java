//-----------------------------------------------------------------------
// Empty AVL exception

class EmptyAVLE extends Exception {}

//-----------------------------------------------------------------------
// Abstract AVL class

abstract class AVL implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------

    static EmptyAVLE EAVLX = new EmptyAVLE();

    static AVL EAVL = new EmptyAVL();

    static AVL AVLLeaf(int elem) {
        return new AVLNode(elem, EAVL, EAVL);
    }

    static BST toBST (AVL avl) {
        try {
            int data = avl.AVLData();
            AVL left = avl.AVLLeft();
            AVL right = avl.AVLRight();
            return new BSTNode(data, toBST(left), toBST(right));
        }
        catch (EmptyAVLE e) {
            return BST.EBST;
        }
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract int AVLData() throws EmptyAVLE;

    abstract AVL AVLLeft() throws EmptyAVLE;

    abstract AVL AVLRight() throws EmptyAVLE;

    abstract int AVLHeight();

    abstract boolean isEmpty ();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean AVLfind (int key);

    abstract AVL AVLinsert(int key);

    abstract AVL AVLeasyRight();

    abstract AVL AVLrotateRight();

    abstract AVL AVLeasyLeft();

    abstract AVL AVLrotateLeft();

    abstract AVL AVLdelete(int key) throws EmptyAVLE;

    abstract Pair<Integer, AVL> AVLshrink() throws EmptyAVLE;
}

//-----------------------------------------------------------------------

class EmptyAVL extends AVL {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLLeft() throws EmptyAVLE {
        throw EAVLX;
    }

    AVL AVLRight() throws EmptyAVLE {
        throw EAVLX;
    }

    int AVLHeight() {
        return 0;
    }

    boolean isEmpty () { return true; }

    //--------------------------
    // Main methods
    //--------------------------

    boolean AVLfind (int key) {
        return false;
    }

    AVL AVLinsert(int key) {
        return AVLLeaf(key);
    }

    AVL AVLeasyRight() {
        throw new Error("Internal bug: should never call easyRight on empty tree");
    }

    AVL AVLrotateRight() {
        throw new Error("Internal bug: should never call rotateRight on empty tree");
    }

    AVL AVLeasyLeft() {
        throw new Error("Internal bug: should never call easyLeft on empty tree");
    }

    AVL AVLrotateLeft() {
        throw new Error("Internal bug: should never call rotateLeft on empty tree");
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        throw EAVLX;
    }

    Pair<Integer, AVL> AVLshrink() throws EmptyAVLE {
        throw EAVLX;
    }

    //--------------------------
    // Override
    //--------------------------

    public boolean equals (Object o) {
        return (o instanceof EmptyAVL);
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

class AVLNode extends AVL {
    private int data;
    private AVL left, right;
    private int height;

    AVLNode(int data, AVL left, AVL right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    int AVLData() {
        return data;
    }

    AVL AVLLeft() {
        return left;
    }

    AVL AVLRight() {
        return right;
    }

    int AVLHeight() {
        return height;
    }

    boolean isEmpty () { return false; }

    //--------------------------
    // Main methods
    //--------------------------

    boolean AVLfind(int key) {
        // TODO
        return data == key || left.AVLfind(key) || right.AVLfind(key);
    }

    AVL AVLinsert(int key) {
        // TODO
        // binary insert
        // rebalance by comparing heights
        if (key <  this.AVLData()) {
            this.left = this.left.AVLinsert(key);
            this.height = 1 + Math.max(this.left.AVLHeight(), this.right.AVLHeight());
            if (this.left.AVLHeight() > this.right.AVLHeight() + 1) {
                return this.AVLrotateRight();
            }
        } else {
            this.right = this.right.AVLinsert(key);
            this.height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
            if (this.right.AVLHeight() > this.left.AVLHeight() + 1) {
                return this.AVLrotateLeft();
            }
        }
        return this;
    }

    AVL AVLeasyRight() {
        // TODO
        try{
            //this.height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
            return new AVLNode(this.AVLLeft().AVLData(),this.AVLLeft().AVLLeft(), new AVLNode(this.AVLData(), this.AVLLeft().AVLRight(), this.AVLRight()));
        } catch(EmptyAVLE e){
            return new EmptyAVL();
        }
    }

    AVL AVLrotateRight() {
        // TODO
        try{
            // if left child height > right child height
            var p = this.AVLLeft();
            if(p.AVLLeft().AVLHeight() < p.AVLRight().AVLHeight()){
                p = p.AVLeasyLeft();
            }
            return new AVLNode(this.AVLData(), p, this.AVLRight()).AVLeasyRight();
        }catch(EmptyAVLE e) {
            return new EmptyAVL();
        }
    }

    AVL AVLeasyLeft() {
        // TODO
        // try catch to see if there is no right or no left
        try{
            //this.height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
            return new AVLNode(this.AVLRight().AVLData(), new AVLNode(this.AVLData(), this.AVLLeft(), this.AVLRight().AVLLeft()), this.AVLRight().AVLRight());
        } catch(EmptyAVLE e){
            return new EmptyAVL();
        }
    }

    AVL AVLrotateLeft() {
        // TODO
        try{
            // if left child height > right child height
            var p = this.AVLRight();
            if(p.AVLLeft().AVLHeight() > p.AVLRight().AVLHeight()){
                p = p.AVLeasyRight();
            }
            return new AVLNode(this.AVLData(), this.AVLLeft(), p).AVLeasyLeft();
        }catch(EmptyAVLE e) {
            return new EmptyAVL();
        }
    }

    AVL AVLdelete(int key) throws EmptyAVLE {
        // TODO
        AVLNode tree;
        if (key < this.data) {
            tree = new AVLNode(this.data, this.left.AVLdelete(key), this.right);
            if(Math.abs(tree.left.AVLHeight()) - this.right.AVLHeight() > 1){
                return tree.AVLrotateLeft();
            }
        } else if (key > this.data) {
            tree = new AVLNode(this.data, this.left, this.right.AVLdelete(key));
            this.height = 1 + Math.max(left.AVLHeight(), right.AVLHeight());
            if (Math.abs(this.left.AVLHeight()) - tree.right.AVLHeight() > 1) {
                return tree.AVLrotateRight();
            }
        } else {
            try {
                Pair<Integer, AVL> shrank = left.AVLshrink();
                tree = new AVLNode(shrank.getFirst(), shrank.getSecond(), this.right);
                if(Math.abs(tree.left.AVLHeight()) - this.right.AVLHeight() > 1){
                    return tree.AVLrotateLeft();
                }
            } catch (EmptyAVLE e) {
                return right;
            }
        }
        return tree;
    }

    Pair<Integer, AVL> AVLshrink() {
        // TODO
        // deletes the rightmost of the left subtree; returns the left subtree at that rightmost node ?
        // return data, left subtree
        try {
            if (!AVLRight().isEmpty()) {
                var p = right.AVLshrink();
                Pair<Integer, AVL> temp = new Pair<>(p.getFirst(), new AVLNode(data, left, p.getSecond()));
                if (left.AVLHeight() > temp.getSecond().AVLRight().AVLHeight() + 1) {
                    temp = new Pair<>(temp.getFirst(), temp.getSecond().AVLrotateRight());
                }
                return temp;
            } else {
                return new Pair<>(data, left);
            }
        } catch (EmptyAVLE e) {
            return new Pair<>(data, left);
        }
    }

    //--------------------------
    // Override
    //--------------------------

    public boolean equals (Object o) {
        if (o instanceof AVLNode) {
            AVLNode other = (AVLNode) o;
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
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
