package tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class DefaultComparator<E> implements Comparator<E> 
{
  /** Compares two given elements
    *
    * @return a negative integer if <tt>a</tt> is less than <tt>b</tt>,
    * zero if <tt>a</tt> equals <tt>b</tt>, or a positive integer if
    * <tt>a</tt> is greater than <tt>b</tt>
    */

    @Override
    public int compare(E a, E b) throws ClassCastException 
    { 
        return ((Comparable<E>) a).compareTo(b);
    }
}

/**
 * Realization of a dictionary by means of a binary search tree.
 *
 * @author R. Cabido, A. Duarte and J. Vélez
 */
// Implementation of a dictionary by means of a binary search tree
public class LinkedBinarySearchTree<E> implements BinarySearchTree<E> 
{
    protected LinkedBinaryTree<E> binTree;
    protected Comparator<E> comparator; // comparator
    protected int size = 0; // number of entries

    /**
     * Creates a BinarySearchTree with a default comparator.
     */
    public LinkedBinarySearchTree() 
    {
        this(null);
    }

    /**
     * Creates a BinarySearchTree with the given comparator.
     */
    public LinkedBinarySearchTree(Comparator<E> c) 
    {
        if (c == null) 
        {
            this.comparator = new DefaultComparator<>();
        } 
        else 
        {
            this.comparator = c;
        }
        this.binTree = new LinkedBinaryTree<>();
        this.binTree.addRoot(null);
    }

    /**
     * Extracts the value of the entry at a given node of the tree.
     */
    protected E value(Position<E> position) 
    {
        return position.element();
    }

    /**
     * Replaces an entry with a new entry (and reset the entry's location)
     */
    protected void replacecValue(Position<E> pos, E ent) 
    {
        this.binTree.replace(pos, ent);
    }

    /**
     * Expand an external node into an internal node with two external node
     * children
     */
    protected void expandLeaf(Position<E> p, E e1, E e2) throws InvalidPositionException 
    {
        if (!this.binTree.isLeaf(p)) 
        {
            throw new InvalidPositionException("Node is not external");
        }
        this.binTree.insertLeft(p, e1);
        this.binTree.insertRight(p, e2);
    }

    /**
     * Remove an external node v and replace its parent with v's sibling
     */
    protected void removeAboveLeaf(Position<E> p) throws InvalidPositionException 
    {
        Position<E> u = this.binTree.parent(p);
        this.binTree.remove(p);
        this.binTree.remove(u);

    }

    /**
     * Auxiliary method for inserting an entry at an external node
     */
    protected Position<E> insertAtLeaf(Position<E> pos, E value) 
    {
        this.expandLeaf(pos, null, null);
        this.binTree.replace(pos, value);
        size++;
        return pos;
    }

    /**
     * Auxiliary method for removing an external node and its parent
     */
    protected void removeLeaf(Position<E> v) 
    {
        removeAboveLeaf(v);
        size--;
    }
   
    // Auxiliary method used by find, insert, and remove.  
    protected Position<E> treeSearch(E value, Position<E> pos)
            throws InvalidPositionException, BoundaryViolationException 
    {
        if (this.binTree.isLeaf(pos)) 
        {
            return pos; // key not found; return external(dummy) node
        } 
        else 
        {
            E curValue = pos.element();
            int comp = comparator.compare(value, curValue);
            if (comp < 0) 
            {
                pos = ((BTNode)pos).getLeft();
                //return treeSearch(value, this.binTree.left(pos)); // search left
            } // subtree
            else if (comp > 0) 
            {
                pos = ((BTNode)pos).getRight();
                //return treeSearch(value, this.binTree.right(pos)); // search
            }																	// right
            // subtree
            return pos; // return internal node where key is found
        }
    } 

    /**
     * Adds to L all entries in the subtree rooted at v having keys equal to k.
     */
    protected void addAll(List<Position<E>> l, Position<E> pos, E value) 
    {
        if (this.binTree.isLeaf(pos)) 
        {
            return;
        }
        Position<E> p = treeSearch(value, pos);
        if (!this.binTree.isLeaf(p)) { // we found an entry with key equal to k
            addAll(l, this.binTree.left(p), value);
            l.add(p); // add entries in inorder
            addAll(l, this.binTree.right(p), value);
        } // this recursive algorithm is simple, but it's not the fastest
    }

    /**
     * Returns the number of entries in the tree.
     */
    @Override
    public int size() 
    {
        return size;
    }

    /**
     * Returns whether the tree is empty.
     */
    @Override
    public boolean isEmpty() 
    {
        return size() == 0;
    }

    /**
     * Returns an entry containing the given key. Returns null if no such entry
     * exists.
     */
    @Override
    public Position<E> find(E value) 
    {
        Position<E> curPos = treeSearch(value, this.binTree.root());
        if (this.binTree.isInternal(curPos)) 
        {
            return curPos;
        }
        return null;
    }

    /**
     * Returns an iterable collection of all the entries containing the given
     * key.
     */
    @Override
    public Iterable<Position<E>> findAll(E value) 
    {
        List<Position<E>> l = new ArrayList<>();
        addAll(l, this.binTree.root(), value);
        return l;
    }
    
    @Override
    public Position<E> insert(E value)
    {
        Position insPos = treeSearch(value, this.binTree.root());
        
        while (!this.binTree.isLeaf(insPos))  // iterative search for insertion position
            insPos = treeSearch(value, this.binTree.right(insPos));
        
        //for     
        //insert in position
        BTNode<E> actionPos = checkPosition(insPos);
        if (actionPos == null)
        {
            while (actionPos != null)
            {                
                int cmp = comparator.compare(value,actionPos.element());
                if(cmp < 0)
                {
                    if(actionPos.getLeft() == null)
                    {
                        actionPos = new BTNode<>(value,null,null,null);
                        break; 
                    }
                    else 
                        actionPos = actionPos.getLeft();
                }
                else if(cmp > 0)
                {
                    if(actionPos.getRight() == null)
                    { 
                        actionPos = new BTNode<>(value,null,null,null);
                        break; 
                    }
                    else 
                        actionPos = actionPos.getRight();
                }
            }     
            insPos = (Position<E>)actionPos;
        }        
        return insertAtLeaf(insPos, value);
    }

    protected Position<E> getLeafToRemove(Position<E> pos) 
    {
        Position<E> remPos = pos;

        if (this.binTree.isLeaf(this.binTree.left(remPos))) 
        {
            remPos = this.binTree.left(remPos); // left easy case
        } 
        else if (this.binTree.isLeaf(this.binTree.right(remPos))) 
        {
            remPos = this.binTree.right(remPos); // right easy case
        } 
        else
        { // entry is at a node with internal children
            Position<E> swapPos = remPos; // find node for moving
            // entry
            remPos = this.binTree.right(swapPos);
            do 
            {
                remPos = this.binTree.left(remPos);
            } while (this.binTree.isInternal(remPos));
            replacecValue(swapPos, this.binTree.parent(remPos).element());
        }
        return remPos;
    }

    /**
     * Removes and returns a given entry.
     */
    @Override
    public E remove(Position<E> pos) throws InvalidPositionException 
    {
        this.checkPosition(pos); // may throw an InvalidPositionException
        E toReturn = pos.element(); // entry to be returned
        Position<E> remPos = getLeafToRemove(pos);
        removeLeaf(remPos);
        return toReturn;
    }

    /**
     * Returns an iterator containing all entries in the tree.
     */
    @Override
    public Iterable<E> values() 
    {
        List<E> entries = new ArrayList<>();
        Iterable<Position<E>> positer = this.binTree;
        for (Position<E> cur : positer) 
        {
            if (this.binTree.isInternal(cur)) 
            {
                entries.add(cur.element());
            }
        }
        return entries;
    }

    @Override
    public Iterable<Position<E>> positions() 
    {
        ArrayList<Position<E>> output = new ArrayList<>();
        
        for (Position<E> cur : binTree) 
        {
            if (this.binTree.isInternal(cur))
                output.add(cur);
        }

        return output;
    }

    /**
     * If v is a good tree node, cast to Position, else throw exception
     */
    protected BTNode<E> checkPosition(Position<E> p)
            throws InvalidPositionException 
    {
        if (p == null || !(p instanceof BTNode)) 
        {
            throw new InvalidPositionException(
                    "The position of the BTNode in the BST is not valid");
        }
        return (BTNode<E>) p;
    }
}