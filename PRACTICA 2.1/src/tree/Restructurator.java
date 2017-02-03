package tree;

public class Restructurator<E> 
{
    /**
     * Performs a tri-posBTNode restructuring. Assumes the posBTNodes are in one of
     * following configurations:
     * 
     * <pre>
     *          z=c       z=c        z=a         z=a
     *         /  \      /  \       /  \        /  \
     *       y=b  t4   y=a  t4    t1  y=c     t1  y=b
     *      /  \      /  \           /  \         /  \
     *    x=a  t3    t1 x=b        x=b  t4       t2 x=c
     *   /  \          /  \       /  \             /  \
     *  t1  t2        t2  t3     t2  t3           t3  t4
     * </pre>
     * 
     * @return the new root of the restructured subtree
     */
    public Position<E> restructure(Position<E> grandChildPos, LinkedBinarySearchTree<E> bst) 
    {
        BTNode<E> lowKey = null, midKey = null, highKey = null, t1 = null, t2 = null, t3 = null, t4 = null;
        BTNode<E> grandChildNode = bst.checkPosition(grandChildPos);
        BTNode<E> parentChild = (BTNode<E>) bst.binTree.parent(grandChildNode);        
        BTNode<E> gpChild = (BTNode<E>) bst.binTree.parent(parentChild);
        
        if (parentChild.getLeft() == grandChildNode && gpChild.getLeft() == parentChild) 
        {//case if the given node is the left son of the father and the father is the left son of the grandpa.
            lowKey = grandChildNode;
            midKey = parentChild;
            highKey = gpChild;
            t1 = grandChildNode.getLeft();
            t2 = grandChildNode.getRight();
            t3 = parentChild.getRight();
            t4 = gpChild.getRight();
        } 
        else if (parentChild.getLeft() == grandChildNode && gpChild.getRight() == parentChild) 
        {//case if the given node is the left son of the father and the father is the Right son of the grandpa.
            lowKey = gpChild;
            midKey = grandChildNode;
            highKey = parentChild;
            t1 = gpChild.getLeft();
            t2 = grandChildNode.getLeft();
            t3 = grandChildNode.getRight();
            t4 = parentChild.getRight();
        } 
        else if(parentChild.getRight() == grandChildNode && gpChild.getLeft() == parentChild) 
        {//case if the given node is the Right son of the father and the father is the left son of the grandpa.
            lowKey = parentChild;
            midKey = grandChildNode;
            highKey = gpChild;
            t1 = parentChild.getLeft();
            t2 = grandChildNode.getLeft();
            t3 = grandChildNode.getRight();
            t4 = gpChild.getRight();
        } 
        else if(parentChild.getRight() == grandChildNode && gpChild.getRight() == parentChild)
        {//case if the given node is the Right son of the father and the father is the Right son of the grandpa.
            lowKey = gpChild;
            midKey = parentChild;
            highKey = grandChildNode;
            t1 = gpChild.getLeft();
            t2 = parentChild.getLeft();
            t3 = grandChildNode.getLeft();
            t4 = grandChildNode.getRight();
        }
         
        if(bst.binTree.isRoot(gpChild)) 
        {  
            bst.binTree.subTree(midKey);
            midKey.setParent(null);
        }
        else
        {
            BTNode<E> gpChildParent = gpChild.getParent();
            midKey.setParent(gpChildParent);
            
            if (gpChildParent.getLeft() == gpChild) 
                gpChildParent.setLeft(midKey);
            else
                gpChildParent.setRight(midKey);
        }
        
        midKey.setLeft(lowKey);
        lowKey.setParent(midKey);
        midKey.setRight(highKey);
        highKey.setParent(midKey);
        lowKey.setLeft(t1);
        t1.setParent(lowKey);
        lowKey.setRight(t2);
        t2.setParent(lowKey);
        highKey.setLeft(t3);
        t3.setParent(highKey);
        highKey.setRight(t4);
        t4.setParent(highKey);        
        return midKey;
    }
}