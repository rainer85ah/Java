package Practica1;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

public class LCRSTreeTest<E> extends TestCase
{   
    public void testSize() 
    {
        System.out.print("Test size: ");
        LCRSTree instance = new LCRSTree();
        
        T datosRoot = new T("Root",1);
        instance.addRoot(datosRoot);
        
        int expResult = 1;
        int result = instance.size();
        
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

    public void testIsEmpty() 
    {
        System.out.print("Test isEmpty: ");
        LCRSTree instance = new LCRSTree();
        
        T datosRoot = new T("Root",1);
        instance.addRoot(datosRoot);
        
        boolean expResult = false;
        boolean result = instance.isEmpty();
        
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

    public void testIsInternal() 
    {
        System.out.print("Test isInternal: ");
        LCRSTree instance = new LCRSTree();
        
        T datosRoot = new T("Root",1);
        Position<E> pRoot = instance.addRoot(datosRoot);
        
        T datosHijo = new T("Hijo",2);
        instance.add(datosHijo, pRoot);
        
        boolean expResult = true;
        boolean result = instance.isInternal(pRoot);
        
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

    public void testIsLeaf() 
    {
        System.out.print("Test isLeaf: ");
        LCRSTree instance = new LCRSTree();
        
        T datosRoot = new T("Root",1);
        Position<E> pRoot = instance.addRoot(datosRoot);
        
        T datosHijo = new T("Hijo",2);
        Position<E> pHijo = instance.add(datosHijo, pRoot);
        
        boolean expResult = true;
        boolean result = instance.isLeaf(pHijo);
        
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

    public void testIsRoot() 
    {
        System.out.print("Test isRoot: ");
        LCRSTree instance = new LCRSTree();
        
        Position<E> p = instance.addRoot(1);
        boolean expResult = true;        
        boolean result = instance.isRoot(p);
        
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

    public void testRoot() 
    {
        System.out.print("Test root: ");
        LCRSTree instance = new LCRSTree();
        
        T datosRoot = new T("Root",1);
        instance.addRoot(datosRoot);
        
        T expResult = datosRoot;
        T result = (T) instance.root().getElement();
        
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

    public void testParent() 
    {
        System.out.print("Test parent: ");
        LCRSTree instance = new LCRSTree();
        
        T datosRoot = new T("Root",1);        
        Position<E> pRoot = instance.addRoot(datosRoot);
        
        T datoshijo = new T("Hijo",2);        
        Position<E> pHijo = instance.add(datoshijo, pRoot);
        
        Position<E> result = instance.parent(pHijo);
        Position<E> expResult = pRoot;
        
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

    public void testBrothers() 
    {
        System.out.print("Test brothers: ");
        LCRSTree instance = new LCRSTree();        
        
        T datosRoot = new T("Root",1);        
        Position<E> pRoot = instance.addRoot(datosRoot);
        
        T datoshijo1 = new T("Hijo 1",2);        
        Position<E> pHijo1 = instance.add(datoshijo1, pRoot);
        
        T datoshijo2 = new T("Hijo 2",2);        
        Position<E> pHijo2 = instance.add(datoshijo2, pRoot);
        
        Iterable<? extends Position<E>> expResult = instance.brothers(pHijo1);
        Iterable<? extends Position<E>> result = instance.brothers(pHijo2);
        boolean encHijo1 = false;
        boolean encHijo2 = false;
        
        while(result.iterator().hasNext() && !(result.iterator().equals(pHijo1)))
            result.iterator().next();
        if (result.iterator().equals(pHijo2))
            encHijo1 = true;
        
        while(expResult.iterator().hasNext() && !(expResult.iterator().equals(pHijo2)))
            expResult.iterator().next();        
        if (expResult.iterator().equals(pHijo2))
            encHijo2 = true;
        
        if (encHijo1 == encHijo2)
        {
            System.out.println("OK.");
            assertEquals(encHijo1, encHijo2);
        }
        else
        {
            System.out.println("KO.");
            fail("The test case is a prototype.");
        }
    }

    public void testAddRoot() 
    {
        System.out.print("Test addRoot: ");
        LCRSTree instance = new LCRSTree();
        
        T datosRoot = new T("Root",1);        
        Position<E> result = instance.addRoot(datosRoot);
        Position expResult = instance.root();
        
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
 
    public void testAdd() 
    {
        System.out.print("Test add: ");
        LCRSTree instance = new LCRSTree();
        
        T datosRoot = new T("Root",1);        
        Position<E> pRoot = instance.addRoot(datosRoot);
        
        T datosHijo = new T("Hijo",2);
        Position<E> expResult = instance.add(datosHijo, pRoot);

        Position<E> result = instance.son(pRoot);
        
        if (result == expResult)
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