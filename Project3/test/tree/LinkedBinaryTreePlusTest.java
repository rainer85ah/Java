/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Owner
 */
public class LinkedBinaryTreePlusTest 
{
    
    public LinkedBinaryTreePlusTest() 
    {
        
    }   


    /**
     * Test of sibling method, of class LinkedBinaryTree.
     */
    @Test
    public void testSibling() 
    {
        System.out.print("Test sibling: ");
        LinkedBinaryTree tree = new LinkedBinaryTree();
        Position pRoot = tree.addRoot('+');
        
        Position pBro = tree.insertLeft(pRoot,2);  
        Position pV = tree.insertRight(pRoot,4); 
        
        Position expResult = pBro;
        Position result = tree.sibling(pV);
        
        if (expResult == result)
        {
            System.out.println("OK.");
            assertEquals(expResult, result);
        }
        else
        {
            System.out.println("KO.");
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of remove method, of class LinkedBinaryTree.
     */
    @Test
    public void testRemove() 
    {
        System.out.print("Test remove: ");
        LinkedBinaryTree tree = new LinkedBinaryTree();
        Position pRoot = tree.addRoot('+');
        Position pLeft = tree.insertLeft(pRoot,2);      
        Position pMin = tree.insertLeft(pLeft,1);
        tree.insertRight(pRoot,4);   
                
        Object expResult = 1;
        Object result = tree.remove(pMin);//puede borrarlo porque no tiene hijos
        //tree.remove(pRoot);//Devuleve null, ya que tiene dos hijos
        //tree.remove(pLeft);//Puede Borrarlo porque tiene 1 hijo
        //
        
        if (expResult == result)
        {
            System.out.println("OK.");
            assertEquals(expResult, result);
        }
        else
        {
            System.out.println("KO.");
            fail("The test case is a prototype.");
        }
    }
    
    /**
     * Test of attach method, of class LinkedBinaryTree.
     */
    @Test
    public void testAttach() 
    {
        System.out.print("Test attach: ");
        LinkedBinaryTree tree = new LinkedBinaryTree();
        LinkedBinaryTree treeLower = new LinkedBinaryTree();
        LinkedBinaryTree treeTopper = new LinkedBinaryTree();
        Position pRoot = tree.addRoot('+');
        treeLower.addRoot(2);
        treeTopper.addRoot(4);
        boolean expResult = true;
        boolean result;
        tree.attach(pRoot, treeLower, treeTopper);
        
        if (tree.root().element().equals('+') && tree.left(pRoot).element().equals(2) && tree.right(pRoot).element().equals(4))
            result = true;
        else
            result = false;       
        
        if (expResult == result)
        {
            System.out.println("OK.");
            assertEquals(expResult, result);
        }
        else
        {
            System.out.println("KO.");
            fail("The test case is a prototype.");
        }
    }
}
