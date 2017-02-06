package Practica1;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Rainer Arencibia
 */
public class LCRSTreePlusTest 
{
    
    public LCRSTreePlusTest() 
    {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
    }
    
    @Before
    public void setUp() 
    {
    }
    
    @After
    public void tearDown() 
    {
    }

    /**
     * Test of front method, of class LCRSTreePlus.
     */
    @Test
    public void testFront() 
    {
        System.out.print("Test front: ");
        LCRSTreePlus instance = new LCRSTreePlus();
        
        T datosRoot = new T("Root",0);
        Position pRoot = instance.addRoot(datosRoot);        
        T datosHijo = new T("Hijo",1);
        Position pHijo = instance.add(datosHijo, pRoot);                
        T datosHijo2 = new T("Hijo2",2);
        Position pHijo2 = instance.add(datosHijo2, pRoot);        
        List<Position> expResult = new LinkedList<>();
        expResult.add(pHijo);
        expResult.add(pHijo2);             
        Iterable<Position> result = instance.front();
        
        if (expResult.equals(result))
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
     * Test of depth method, of class LCRSTreePlus.
     */
    @Test
    public void testDepth() 
    {
        System.out.print("Test depth: ");
        LCRSTreePlus instance = new LCRSTreePlus();
        
        T datosRoot = new T("Root",0);
        Position pRoot = instance.addRoot(datosRoot);        
        T datosHijo = new T("Hijo",1);
        instance.add(datosHijo, pRoot);
        
        int expResult = 1;
        int result = instance.depth();
        
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
     * Test of degree method, of class LCRSTreePlus.
     */
    @Test
    public void testDegree() 
    {
        System.out.print("Test degree: ");
        LCRSTreePlus instance = new LCRSTreePlus();
        
        T datosRoot = new T("Root",0);
        Position pRoot = instance.addRoot(datosRoot);        
        T datosHijo = new T("Hijo",1);
        Position pHijo = instance.add(datosHijo, pRoot);        
        T datosHijo2 = new T("Hijo2",2);
        instance.add(datosHijo2, pHijo);        
        T datosHijo3 = new T("Hijo3",3);
        instance.add(datosHijo3, pHijo);
        
        int expResult = 2;
        int result = instance.degree();
        
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