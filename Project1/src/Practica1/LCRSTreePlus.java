package Practica1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LCRSTreePlus<E> extends LCRSTree<E> 
{
    public Iterable<Position<E>> front() throws EmptyTreeException
    {
        ArrayList<Position<E>> list = new ArrayList<>();
        TreeNode<E> node;
        
        if (this.isEmpty())
            return null; 
        else
        {
            if (root.getSon() == null)
                return (Iterable<Position<E>>)root;
            else
            {
                node = root.getSon();
                while (node != null) 
                {
                    for (Position<E> pNode : node.getBrothers())
                    {
                        if (isLeaf(pNode))
                            if(!list.contains(pNode))
                                list.add(pNode);
                    }
                    if (isLeaf(node))
                        if(!list.contains(node))
                            list.add(node);
                    node = node.getSon();
                }
                return (list);
            }            
        }        
    } 
    
    /*
    The Breath-First-Search (BFS) algorithm consists of the following steps:
1.  Enqueue the start node in queue Q.
2.  While Q is not empty repeat the following two steps:
-    Dequeue the next node v from Q and print it.
-    Add all children of v in the queue.
    */
        
    public int depth()
    {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        TreeNode<E> node;
        int currentLevel, nextLevel, depth;        
        
        if (this.root == null)
            return 0;
        
        queue.add(this.root);
        currentLevel = 1;
        nextLevel = depth = 0;
        
        while (!queue.isEmpty()) 
        {
            node = queue.remove();
            currentLevel --;
            
            for (int i = 0; i < node.getSon().getBrothers().size(); i++)
            {
                if (node.getSon().getBrothers().get(i) != null) 
                {
                    queue.add(node.getSon().getBrothers().get(i));
                    nextLevel++;
                }
            }        
            if (currentLevel == 0) 
            {
                depth++;
                currentLevel = nextLevel;
                nextLevel = 0;
            }
        }
        return depth;
    }
        
    /*Devuelve el grado del arbol*/
    public int degree()
    {
        TreeNode<E> node = this.root.getSon();
        int degree = 0, cont;        
                
        while (node != null)
        {
            cont = node.getBrothers().size();
            if (cont > degree)
                degree = cont;
            node = node.getSon();
        }
        return degree;
    }
}