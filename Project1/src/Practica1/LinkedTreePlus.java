package Practica1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedTreePlus<E> extends LinkedTree<E>
{      
    public Iterable<Position<E>> front() throws EmptyTreeException
    {
        ArrayList list = new ArrayList();

        for (Position<E> e : this) 
        {
            if (this.isLeaf(e))
                list.add(e);
        }
        return (list);
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
            
            for (int i = 0; i < node.getChildren().size(); i++)
            {
                if (node.getChildren().get(i) != null) 
                {
                    queue.add(node.getChildren().get(i));
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
        
    public int degree()
    {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        TreeNode<E> node;
        int degree = 0, cont = 0;        
        
        if (this.isEmpty())
            return 0;
        
        queue.add(this.root);
        
        while (!queue.isEmpty()) 
        {
            node = queue.remove();
            
            for (int i = 0; i < node.getChildren().size(); i++)
            {
                cont = node.getChildren().size();
                if (cont > degree)
                    degree = cont;
                queue.add(node.getChildren().get(i));
            }        
        }
        return degree;
    }
}