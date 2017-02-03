package material.tree.linkedtree;

import java.util.*;

import material.tree.Position;
import material.tree.Tree;
import material.tree.exceptions.BoundaryViolationException;
import material.tree.exceptions.EmptyTreeException;
import material.tree.exceptions.InvalidPositionException;
import material.tree.exceptions.NonEmptyTreeException;

/**
 * A linked class for a tree where nodes have an arbitrary number of children.
 *
 * @author Raul Cabido, Abraham Duarte, Jose Velez
 */
public class LinkedTree<E> implements Tree<E> {


	private class TreeNode<T> implements Position<T> {
		private T element; 
		private TreeNode<T> parent; 
		private List<TreeNode<T>> children; // children nodes

	    /** Main constructor */
		public TreeNode(T element, TreeNode<T> parent, List<TreeNode<T>> children) {
			setElement(element);
			setParent(parent);
			setChildren(children);
		}

		/** Returns the element stored at this position */
	        @Override
		public T getElement() {
			return element;
		}

		/** Sets the element stored at this position */
		public final void setElement(T o) {
			element = o;
		}

		/** Returns the children of this position */
		public List<TreeNode<T>> getChildren() {
			return children;
		}

		/** Sets the right child of this position */
		public final void setChildren(List<TreeNode<T>> c) {
			children = c;
		}

		/** Returns the parent of this position */
		public TreeNode<T> getParent() {
			return parent;
		}

		/** Sets the parent of this position */
		public final void setParent(TreeNode<T> v) {
			parent = v;
		}
	}	
	
	private class LinkedTreeIterator<T> implements Iterator<Position<T>> {
        private Queue<TreeNode<T>> nodeQueue = new ArrayDeque<> ();

        private LinkedTreeIterator(TreeNode<T> root) {
            nodeQueue.add(root);
        }

        @Override
        public boolean hasNext() {
            return (nodeQueue.size() != 0);
        }

        /** This method visits the nodes of a tree by following a breath-first search  */
        @Override
        public Position<T> next() {
            TreeNode<T> aux = nodeQueue.remove();
            for (TreeNode<T> node : aux.getChildren()) {
                nodeQueue.add(node);
            }
            return aux;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private TreeNode<E> root; 
    private int size; 

    /**
     * Creates an empty tree.
     */
    public LinkedTree() {
        root = null; 
        size = 0;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return The size.
	 *
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the tree is empty.
     *
     * @return True if is empty.
	 *
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns whether a node is internal.
     */
    @Override
    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        return !isLeaf(v);
    }

    /**
     * Returns whether a node is external.
     */
    @Override
    public boolean isLeaf(Position<E> p) throws InvalidPositionException {
        TreeNode<E> node = checkPosition(p); 
        return (node.getChildren() == null) || (node.getChildren().isEmpty());
    }

    /**
     * Returns whether a node is the root.
     */
    @Override
    public boolean isRoot(Position<E> p) throws InvalidPositionException {
    	TreeNode<E> node = checkPosition(p);
        return (node == this.root());
    }

    /**
     * Returns the root of the tree.
     */
    @Override
    public Position<E> root() throws EmptyTreeException {
        if (root == null) {
            throw new EmptyTreeException("The tree is empty");
        }
        return root;
    }

    /**
     * Returns the parent of a node.
     */
    @Override
    public Position<E> parent(Position<E> p) throws InvalidPositionException,
            BoundaryViolationException {
        TreeNode<E> node = checkPosition(p);
        Position<E> parentPos = (Position<E>) node.getParent();
        if (parentPos == null) {
            throw new BoundaryViolationException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     */
    @Override
    public Iterable<? extends Position<E>> children(Position<E> p)
            throws InvalidPositionException {
        TreeNode<E> node = checkPosition(p);
        if (isLeaf(p)) {
            throw new InvalidPositionException("External nodes have no children");
        }
        
        return Collections.unmodifiableCollection(node.getChildren());
    }

    /**
     * Returns an iterator of the elements stored at the nodes. The nodes are visited according to a breath-first search
     */
    @Override
    public Iterator<Position<E>> iterator() {        
        return new LinkedTreeIterator<E>(root); // An iterator of elements
    }

    /**
     * Replaces the element at a node.
     */
    public E replace(Position<E> p, E e) throws InvalidPositionException {
        TreeNode<E> node = checkPosition(p);
        E temp = p.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Adds a root node to an empty tree
     */
    public Position<E> addRoot(E e) throws NonEmptyTreeException {
        if (!isEmpty()) {
            throw new NonEmptyTreeException("Tree already has a root");
        }
        size = 1;
        root = new TreeNode<E>(e, null, new ArrayList<TreeNode<E>>());
        return root;
    }

    /**
     * Swap the elements at two nodes
     */
    public void swapElements(Position<E> p1, Position<E> p2)
            throws InvalidPositionException {
        TreeNode<E> node1 = checkPosition(p1);
        TreeNode<E> node2 = checkPosition(p2);
        E temp = p2.getElement();
        node2.setElement(p1.getElement());
        node1.setElement(temp);
    }

     /**
     * If v is a good tree node, cast to TreePosition, else throw exception
     */
    private TreeNode<E> checkPosition(Position<E> p)
            throws InvalidPositionException {
        if (p == null || !(p instanceof TreeNode)) {
            throw new InvalidPositionException("The position is invalid");
        }
        return (TreeNode<E>) p;
    }

    
    /**
     * Add a new node whose parent is pointed by a given position.
     * @param p The position of the parent, e the element stored in the new created node.
     * @throws InvalidPositionException 
     */
    public Position<E> add(E element, Position<E> p) {
    	TreeNode<E> parent = checkPosition(p);
    	TreeNode<E> newNode = new TreeNode<E>(element, parent, new LinkedList<TreeNode<E>>());
        List<LinkedTree<E>.TreeNode<E>> l = parent.getChildren();
        l.add(newNode);
        size++;
        return newNode;
    }

    /**
     * Remove a node and its corresponding subtree rooted at node.
     * @param p The position of the node to be removed.
     * @throws InvalidPositionException 
     */
    public void remove(Position<E> p) throws InvalidPositionException {
        TreeNode<E> node = checkPosition(p);
        LinkedTreeIterator <E> it = new LinkedTreeIterator<>(node);
        int cont = 0;
        while (it.hasNext()) {
            it.next();
            cont++;
        }
        size = size - cont;
    }
}
