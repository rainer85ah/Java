package Practica2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Owner
 */
public class HashTableMapSCTest 
{
    private HashTableMap<String, Integer> listin;
    
    public HashTableMapSCTest(Solver solver) {
        solver = solver;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of size method, of class HashTableMapSC.
     */
    @Test
    public void testSize() 
    {
        System.out.print("Test size: ");
        HashTableMapSC<String, Integer> listin = new HashTableMapSC<>();	
        assertEquals(listin.size(), 0);

        listin.put("Jose", new Integer(912127659));
        listin.put("Angel", new Integer(912127658));
        listin.put("Abraham", new Integer(912127657));
        listin.put("Mayte", new Integer(912127656));
        listin.put("Raul", new Integer(912127655));	
        listin.put("Jose", new Integer(1));
        listin.put("Angel", new Integer(2));
        listin.put("Abraham", new Integer(3));

        //this.defaultMap();
        assertEquals(listin.size(), 5);

        listin.remove("Angel");
        listin.remove("Mayte");
        assertEquals(listin.size(), 3);
    }

    /**
     * Test of isEmpty method, of class HashTableMapSC.
     */
    @Test
    public void testIsEmpty() 
    {
        System.out.print("Test isEmpty: ");
        HashTableMapSC instance = new HashTableMapSC();
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

    /**
     * Test of get method, of class HashTableMapSC.
     */
    @Test
    public void testGet() 
    {
        System.out.print("Test get: ");
        Object k = null;
        HashTableMapSC instance = new HashTableMapSC();
        Object expResult = null;
        Object result = instance.get(k);
        
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

    /*
     * Test of put method, of class HashTableMapSC.
     */
    @Test
    public void testPut() 
    {
        System.out.print("Test put: ");
        Object key = null;
        Object val = null;
        HashTableMapSC instance = new HashTableMapSC();
        Object expResult = instance.put(1, "Rainer");
        
        Object result = instance.get(1);
        
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
     * Test of remove method, of class HashTableMapSC.
     */
    @Test
    public void testRemove() 
    {
        System.out.print("Test remove: ");
        Object key = null;
        HashTableMapSC instance = new HashTableMapSC();
        Object expResult = null;
        Object result = instance.remove(key);
        
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
     * Test of entrySet method, of class HashTableMapSC.
     */
    @Test
    public void testEntrySet() 
    {
        System.out.print("Test entrySet: ");
        HashTableMapSC instance = new HashTableMapSC();
        Iterable expResult = null;
        Iterable result = instance.entrySet();
        
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
     * Test of keys method, of class HashTableMapSC.
     */
    @Test
    public void testKeysSet() 
    {
        System.out.print("Test keySet: ");
        HashTableMapSC instance = new HashTableMapSC();
        Iterable expResult = null;
        Iterable result = instance.keySet();
        
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
     * Test of values method, of class HashTableMapSC.
     */
    @Test
    public void testValues() 
    {
        System.out.print("Test values: ");
        HashTableMapSC instance = new HashTableMapSC();
        Iterable expResult = null;
        Iterable result = instance.values();
        
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
     * Test of rehash method, of class HashTableMap.
     */
    @Test
    public void testRehash() 
    {
        System.out.print("Test rehash: ");
        HashTableMapSC instance = new HashTableMapSC();
        
        int expResult = instance.size(); 
        instance.rehash();
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
