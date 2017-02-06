package Practica1;

import java.util.*;

public class LCRSTree<E> implements Tree<E> 
{
    TreeNode<E> root; 
    private int size;
    
    public class TreeNode<T> implements Position<T> 
    {
        private T element; 
	private TreeNode<T> parent; 
        private TreeNode<T> son; 
	private List<TreeNode<T>> brothers;

	/** Main constructor */
	public TreeNode(T element, TreeNode<T> parent, TreeNode<T> son, List<TreeNode<T>> brothers) 
        {
            setElement(element);
            setParent(parent);
            setSon(son);
            setBrothers(brothers);
	}

        /** Returns the element stored at this position */
        @Override
        public T getElement() 
        {
            return this.element;
        }

        /** Sets the element stored at this position */
        public final void setElement(T e) 
        {
            this.element = e;
        }

        /** Returns the children of this position */
        public List<TreeNode<T>> getBrothers() 
        {
            return brothers;
        }

        /** Sets the right child of this position */
        public final void setBrothers(List<TreeNode<T>> c) 
        {
            brothers = c;
        }

        /** Returns the parent of this position */
        public TreeNode<T> getParent() 
        {
            return parent;
        }

        /** Sets the parent of this position */
        public final void setParent(TreeNode<T> v) 
        {
            parent = v;
        }

        /** Sets the son of this position */
        private void setSon(TreeNode<T> s) 
        {
            son = s;
        }
                
        /** Returns the son of this position */
        public TreeNode<T> getSon() 
        {
            return son;
        }
    }

    private class LCRSTreeIterator<T> implements Iterator<Position<T>> 
    {
        private Queue<TreeNode<T>> nodeQueue = new ArrayDeque<>();

        private LCRSTreeIterator(TreeNode<T> root) 
        {
            nodeQueue.add(root);
        }

        @Override
        public boolean hasNext() 
        {
            return (nodeQueue.size() != 0);
        }

        /** This method visits the nodes of a tree by following a breath-first search  */
        @Override
        public Position<T> next() 
        {
            TreeNode<T> aux = nodeQueue.remove();                        
            if (aux == root)
                nodeQueue.add(aux.getSon());
            else
            {
                if (aux.getBrothers() != null)
                {
                    for (TreeNode<T> node : aux.getBrothers())
                        if (nodeQueue.contains(node))
                            nodeQueue.add(node);
                }
                else
                    if (nodeQueue.contains(aux.getSon()))
                        nodeQueue.add(aux.getSon());
            }
            return aux;
        }

        @Override
        public void remove() 
        {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }/*FIN .. LinkedTreeIterator<T>*/ 

    public LCRSTree() 
    {
        root = null; 
        size = 0;
    }

    public void setSize(int size) 
    {
        this.size = size;
    }    

    @Override
    public int size() 
    {
        return size;
    }

    @Override
    public boolean isEmpty() 
    {
        return (size == 0);
    }

    @Override
    public boolean isInternal(Position<E> p) throws InvalidPositionException 
    {
        TreeNode<E> node = checkPosition(p); 
        return (node.getSon() != null);
    }

    @Override
    public boolean isLeaf(Position<E> p) throws InvalidPositionException 
    {
        TreeNode<E> node = checkPosition(p); 
        return (node.getSon() == null);
    }

    @Override
    public boolean isRoot(Position<E> p) throws InvalidPositionException 
    {
        TreeNode<E> node = checkPosition(p);
        return (node == this.root());
    }

    @Override
    public Position<E> root() throws EmptyTreeException 
    {
        if (this.root == null) 
        {
            throw new EmptyTreeException("The tree is empty!");
        }
        return this.root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws InvalidPositionException, BoundaryViolationException 
    {
        TreeNode<E> node = checkPosition(p);
        Position<E> parentPos = (Position<E>) node.getParent();
        
        if (parentPos == null) 
            throw new BoundaryViolationException("No parent.");
        return parentPos;
    }

    public Position<E> son(Position<E> p) throws InvalidPositionException 
    {
        TreeNode<E> node = checkPosition(p);
        Position<E> sonPos = (Position<E>) node.getSon();
        if (sonPos == null) 
        {
            throw new BoundaryViolationException("No Son.");
        }
        return sonPos; 
    }

    /**
    * Returns an iterable collection of the brothers of a node.
    */
    public Iterable<? extends Position<E>> brothers(Position<E> p) throws InvalidPositionException 
    {
        TreeNode<E> node = checkPosition(p);
        if (node == null) 
        {
            throw new InvalidPositionException("Position don't exists!");
        }
        return Collections.unmodifiableCollection(node.getBrothers());
        /* Devuelve una copia de la lista pero NO modificable!! */
    }

    /**
    * Returns an iterator of the elements stored at the nodes. 
    * The nodes are visited according to a breath-first search
    */
    @Override
    public Iterator<Position<E>> iterator() 
    {        
        TreeNode<E> nodeRoot = checkPosition(this.root());
        return new LCRSTreeIterator<>(nodeRoot);
    }
    
    @Override 
    //Devuelve una lista no modificable del hijo ++ la lista de hermanos.
    public Iterable<? extends Position<E>> children(Position<E> p) throws InvalidPositionException 
    {
        ArrayList<TreeNode<E>> allsons = new ArrayList<>();
        TreeNode<E> node = checkPosition(p);
        TreeNode<E> son = node.getSon();
        
        if (isLeaf(node)) 
        { 
            throw new InvalidPositionException("External nodes have no children and brothers");
        }
        /* Devuelve una lista NO modificable con el hijo y!! */
        allsons.add(son);
        allsons.addAll(son.getBrothers());
        return Collections.unmodifiableCollection(allsons);        
    }

    /**
    * Replaces the element at a node.
    */
    public E replace(Position<E> p, E e) throws InvalidPositionException 
    {
        TreeNode<E> node = checkPosition(p);
        E temp = p.getElement();
        node.setElement(e);
        return temp;
    }

    public Position<E> addRoot(E e) throws NonEmptyTreeException 
    {
        if (!this.isEmpty()) 
        {
            throw new NonEmptyTreeException("Tree already has a root");
        }
        this.size = 1;
        this.root = new TreeNode<>(e, null, null, null);
        return root;
    }

    /**
    * Swap the elements at two nodes
    */
    public void swapElements(Position<E> p1, Position<E> p2)
        throws InvalidPositionException 
    {
        TreeNode<E> node1 = checkPosition(p1);
        TreeNode<E> node2 = checkPosition(p2);
        E temp = p2.getElement();
        node2.setElement(p1.getElement());
        node1.setElement(temp);
    }

    /**
    * If v is a good tree node, cast to TreePosition, else throw exception
    */
    private TreeNode<E> checkPosition(Position<E> p) throws InvalidPositionException 
    {
        if (p == null || !(p instanceof TreeNode)) 
        {
            throw new InvalidPositionException("The position is invalid");
        }
        return (TreeNode<E>) p;
    }


    /**
    * Add a new node whose parent is pointed by a given position.
    * @param p The position of the parent, e the element stored in the new created node.
    * @throws InvalidPositionException 
    */
    public Position<E> add(E element, Position<E> p) 
    {//(nodoPadre solo con datos, lo demas a null)
        TreeNode<E> parent = checkPosition(p);
        TreeNode<E> newNode = new TreeNode<>(element, parent, null, new LinkedList<>());
        List<TreeNode<E>> list = new LinkedList<>();
        
        if (parent.getSon() == null)
            parent.setSon(newNode);  
        else
        {
            if (parent.getSon().getBrothers() == null)
                list.add(parent.getSon());
            else
            {
                list = parent.getSon().getBrothers();
                list.add(parent.getSon());
            }
            parent.getSon().getBrothers().add(newNode);
        }        
        newNode.setBrothers(list);       
        this.size++;
        return newNode;
    }

    /**
    * Remove a node and its corresponding subtree rooted at node.
    * @param p The position of the node to be removed.
    * @throws InvalidPositionException 
    */
    public void remove(Position<E> p) throws InvalidPositionException 
    {
        TreeNode<E> node = checkPosition(p);
        LCRSTreeIterator<E> it = new LCRSTreeIterator<>(node);
        int cont = 0;
        while (it.hasNext()) 
        {
            it.next();
            cont++;
        }
        this.setSize(this.size() - cont);
    }
}