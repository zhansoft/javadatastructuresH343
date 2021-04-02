import com.sun.source.tree.EmptyStatementTree;

import java.util.Stack;

class Traversal<E> {

    static <E> List<E> inOrder(BinTree<E> tree){
        // check here
        List<E> traversal = new Empty<>();
        try{
            // left tree
            List<E> lefttree = inOrder(tree.getLeftT());
            traversal = traversal.append(lefttree);

            // root
            traversal = traversal.append(new Node<E>(tree.getData(), new Empty()));

            // right tree
            List<E> righttree = inOrder(tree.getRightT());
            return traversal.append(righttree);

        }catch(EmptyTreeE e){
            // base case
            return traversal;
        }
    }

    static <E> List<E> preOrder(BinTree<E> tree){
        // check ehre
        List<E> traversal = new Empty<>();
        try{
            // root
            traversal = traversal.append(new Node<E>(tree.getData(), new Empty()));

            // left
            List<E> lefttree = preOrder(tree.getLeftT());
            traversal = traversal.append(lefttree);

            // right
            List<E> righttree = preOrder(tree.getRightT());
            return traversal.append(righttree);

        }catch(EmptyTreeE e){
            // base case
            return traversal;
        }
    }

    static <E> List<E> postOrder(BinTree<E> tree) {
        // check here
        List<E> traversal = new Empty<>();
        try{
            // left
            List<E> lefttree = postOrder(tree.getLeftT());
            traversal = traversal.append(lefttree);

            // right
            List<E> righttree = postOrder(tree.getRightT());
            traversal = traversal.append(righttree);

            // root
            return traversal.append(new Node<E>(tree.getData(), new Empty()));

        }catch(EmptyTreeE e){
            // base case
            return traversal;
        }
    }

}