package Practica2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rainer Arencibia
 */
public class HashTableMapTestRainer 
{
    
    public HashTableMapTestRainer() 
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
     * Test of rehash method, of class HashTableMap.
     */
    @Test
    public void testRehash() 
    {
        System.out.print("Test rehash: ");
        Solver solver = new SolverDouble();
        HashTableMap instance = new HashTableMap(4,solver);        
        int expResult = instance.size(); 
        instance.put(1,"Rainer");
        instance.put(2,"Luis");
        instance.put(3,"Sofia");
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
}
