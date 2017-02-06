package tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * AVLTree class - implements an AVL Tree by extending a binary search tree.
 *
 * @author Michael Goodrich, Roberto Tamassia, Eric Zamore
 */
public class AVLTree<E> implements BinarySearchTree<E> 
{
    public class AVLInfo<E> implements Comparable<AVLInfo<E>> 
    {
        private int height; // we add a height field to a BTNode
        private E element;

        AVLInfo(E element) 
        {
            this.element = element;
        }

        AVLInfo(E element, int height) 
        {
            this.height = height;
            this.element = element;
        }

        public void setHeight(int height) 
        {
            this.height = height;
        }

        public int getHeight() 
        {
            return height;
        }

        public E getElement() 
        {
            return element;
        }

        @Override
        public int compareTo(AVLInfo<E> o) 
        {
            if (element instanceof Comparable) 
            {
                Comparable c1 = (Comparable) element;
                Comparable c2 = (Comparable) o.element;
                return c1.compareTo(c2);
            } else 
            {
                throw new ClassCastException("Element is not comparable");
            }
        }
    }

    private class AVLPosition<E> implements Position<E> 
    {
        private Position<AVLInfo<E>> pos;
        /**
         * @return the pos
         */
        public Position<AVLInfo<E>> getPos() 
        {
            return pos;
        }

        /**
         * @param pos the pos to set
         */
        public AVLPosition(Position<AVLInfo<E>> pos) 
        {
            this.pos = pos;
        }

        @Override
        public E element() 
        {
            return pos.element().getElement();
        }
    }

    private class AVLTreeIterable<E> implements Iterable<Position<E>> 
    {

        private Iterator<Position<E>> it;

        public AVLTreeIterable(Iterable<Position<AVLInfo<E>>> iterable) 
        {
            it = new AVLTreeIterator(iterable.iterator());
        }

        @Override
        public Iterator<Position<E>> iterator() 
        {
            return it;
        }
    }

    private class AVLTreeIterator<E> implements Iterator<Position<E>> 
    {
        private Iterator<Position<AVLInfo<E>>> it;

        public AVLTreeIterator(Iterator<Position<AVLInfo<E>>> it) 
        {
            this.it = it;
        }

        @Override
        public boolean hasNext() 
        {
            return it.hasNext();
        }

        @Override
        public Position<E> next() 
        {
            Position<AVLInfo<E>> aux = it.next();
            return new AVLPosition<> (aux);
        }

        @Override
        public void remove() 
        {
            it.remove();
        }
    }
    
    LinkedBinarySearchTree<AVLInfo<E>> bst = new LinkedBinarySearchTree<>();
    
    @Override
    public Position<E> find(E value) 
    {
        AVLInfo<E> searchedValue = new AVLInfo<>(value);
        Position<AVLInfo<E>> output = bst.find(searchedValue);

        if (output == null) 
        {
            return null;
        }
        return new AVLPosition(output);
    }

    @Override
    public Iterable<Position<E>> findAll(E value) 
    {
        AVLInfo<E> searchedValue = new AVLInfo<>(value);
        Iterable<Position<AVLInfo<E>>> output = bst.findAll(searchedValue);
        return new AVLTreeIterable(output);
    }

    @Override
    public boolean isEmpty() 
    {
        return bst.isEmpty();
    }

    @Override
    public Iterable<Position<E>> positions() 
    {
        Iterable<Position<AVLInfo<E>>> output = bst.positions();
        return new AVLTreeIterable(output);
    }

    @Override
    public int size() 
    {
        return bst.size();
    }

    @Override
    public Iterable<E> values() 
    {
        Iterable<AVLInfo<E>> output = bst.values();

        ArrayList<E> outputArray = new ArrayList<>();
        for (AVLInfo<E> e : output) 
        {
            outputArray.add(e.getElement());
        }
        return outputArray;
    }

    /**
     * Returns whether a node has balance factor between -1 and 1.
     */
    private boolean isBalanced(Position<AVLInfo<E>> p) 
    {
        AVLInfo<E> right = bst.binTree.right(p).element();
        AVLInfo<E> left = bst.binTree.left(p).element();
        final int leftHeight = (left == null) ? 0 : left.getHeight();
        final int rightHeight = (right == null) ? 0 : right.getHeight();
        final int bf = leftHeight - rightHeight;
        return ((-1 <= bf) && (bf <= 1));
    }

    /**
     * Return a child of p with height no smaller than that of the other child.
     */
    private Position<AVLInfo<E>> tallerChild(Position<AVLInfo<E>> p) 
    {
        Position<AVLInfo<E>> right = bst.binTree.right(p);
        Position<AVLInfo<E>> left = bst.binTree.left(p);
        final int leftHeight = (left.element() == null) ? 0 : left.element().getHeight();
        final int rightHeight = (right.element() == null) ? 0 : right.element().getHeight();

        if (leftHeight > rightHeight) 
        {
            return left;
        } 
        else if (leftHeight < rightHeight) 
        {
            return right;
        }

        // equal height children - break tie using parent's type
        if (bst.binTree.isRoot(p)) 
        {
            return left;
        }

        if (p == bst.binTree.left(bst.binTree.parent(p))) 
        {
            return left;
        } 
        else 
        {
            return right;
        }
    }

    private void calculateHeight(Position<AVLInfo<E>> p) 
    {
        Position<AVLInfo<E>> left = bst.binTree.left(p);
        Position<AVLInfo<E>> right = bst.binTree.right(p);
        final int leftHeight = (left.element() == null) ? 0 : left.element().getHeight();
        final int rightHeight = (right.element() == null) ? 0 : right.element().getHeight();
        p.element().setHeight(1 + Math.max(leftHeight, rightHeight));
    }

    /**
     * Rebalance method called by insert and remove. Traverses the path from p
     * to the root. For each node encountered, we recompute its height and
     * perform a trinode restructuring if it's unbalanced.
     */
    private void rebalance(Position<AVLInfo<E>> zPos) 
    {
        if (bst.binTree.isInternal(zPos)) 
        {
            calculateHeight(zPos);
        }

        Restructurator<AVLInfo<E>> restructurator = new Restructurator<>();

        while (!bst.binTree.isRoot(zPos)) 
        { // traverse up the tree towards the
            // root
            zPos = bst.binTree.parent(zPos);
            calculateHeight(zPos);
            if (!isBalanced(zPos)) 
            {
                // perform a trinode restructuring at zPos's tallest grandchild
                Position<AVLInfo<E>> xPos = tallerChild(tallerChild(zPos));
                zPos = restructurator.restructure(xPos, bst);
                calculateHeight(bst.binTree.left(zPos));
                calculateHeight(bst.binTree.right(zPos));
                calculateHeight(zPos);
            }
        }
    }

    /**
     * Inserts an item into the dictionary and returns the newly created entry.
     */
    @Override
    public Position<E> insert(E e) 
    {
        AVLInfo<E> aux = new AVLInfo<>(e);
        Position<AVLInfo<E>> internalNode = bst.insert(aux);
        rebalance(internalNode);
        return new AVLPosition(internalNode);
    }

    /**
     * Removes and returns an entry from the dictionary.
     */
    @Override
    public E remove(Position<E> pos) throws InvalidPositionException 
    {
        Position<AVLInfo<E>> aux = checkPosition(pos);
        E toReturn = pos.element(); // entry to be returned
        Position<AVLInfo<E>> remPos = bst.getLeafToRemove(aux);
        Position<AVLInfo<E>> actionPos = bst.binTree.sibling(remPos);
        bst.removeLeaf(remPos);
        rebalance(actionPos); // rebalance up the tree
        return toReturn;
    }

    /**
     * If v is a good tree node, cast to TreePosition, else throw exception
     */
    private Position<AVLInfo<E>> checkPosition(Position<E> p) throws InvalidPositionException 
    {
        if (p == null) 
        {
            throw new InvalidPositionException("The position of the AVL node is null");
        } 
        else if (!(p instanceof AVLPosition)) 
        {
            throw new InvalidPositionException("The position of the AVL node is not AVL");
        } 
        else 
        {
            AVLPosition<E> aux = (AVLPosition<E>) p;
            return aux.getPos();
        }
    }
} // end of AVLTree