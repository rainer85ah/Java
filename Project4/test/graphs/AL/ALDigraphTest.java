/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs.AL;

import graphs.Edge;
import graphs.Vertex;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
public class ALDigraphTest{
    
    public ALDigraphTest() {
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
     * Test of endVertices method, of class ALDigraph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("opposite");
        ALDigraph<String,Integer> graph = new ALDigraph<> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        Edge<Integer> e1 = graph.insertEdge(v1,v2,495);
        Edge<Integer> e2 = graph.insertEdge(v1,v3,350);
        Edge<Integer> e3 = graph.insertEdge(v2,v3,190);
        
        Iterator<Vertex<String>> it = graph.endVertices(e1).iterator();
        assertEquals(it.hasNext(),true);
        assertEquals(it.next().getValue(),"Madrid");
        assertEquals(it.next().getValue(),"Sevilla");
        assertEquals(it.hasNext(),false);
        
        it = graph.endVertices(e2).iterator();
        assertEquals(it.hasNext(),true);
        assertEquals(it.next().getValue(),"Madrid");
        assertEquals(it.next().getValue(),"Badajoz");
        assertEquals(it.hasNext(),false);
        
        it = graph.endVertices(e3).iterator();
        assertEquals(it.hasNext(),true);
        assertEquals(it.next().getValue(),"Sevilla");
        assertEquals(it.next().getValue(),"Badajoz");
        assertEquals(it.hasNext(),false);        
    }

    /**
     * Test of areAdjacent method, of class ALDigraph.
     */
    @Test
    public void testAreAdjacent() 
    {
        System.out.println("areAdjacent");
        ALDigraph<String,Integer> graph = new ALDigraph<> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        graph.insertEdge(v1,v2,495);
        graph.insertEdge(v2,v3,190);
        
        assertTrue(graph.areAdjacent(v1, v2));
        assertTrue(graph.areAdjacent(v2, v3));
        assertFalse(graph.areAdjacent(v1, v3));
    }

    /**
     * Test of insertVertex method, of class ALDigraph.
     */
    @Test
    public void testInsertVertex() 
    {
        System.out.println("insertVertex");
        ALDigraph graph = new ALDigraph();
        
        Vertex<String> v1 = graph.insertVertex("Madrid");
        v1.setValue("Madrid");
        Vertex<String> v2 = graph.insertVertex("Sevilla");
        
        Edge<Integer> e = graph.insertEdge(v1,v2,495);       
        assertEquals((int)e.getValue(), 495);
        assertEquals(e.getSource().getValue(), "Madrid");
        assertEquals(e.getDestination().getValue(),"Sevilla");
    }

    /**
     * Test of outputEdges method, of class ALDigraph.
     */
    @Test
    public void testOutputEdges() 
    {
        System.out.println("outputEdges");
        ALDigraph graph = new ALDigraph();
        
        Vertex<String> v1 = graph.insertVertex("Madrid");
        Vertex<String> v2 = graph.insertVertex("Sevilla");
        Vertex<String> v3 = graph.insertVertex("Badajoz");
        
        Edge<String> e = graph.insertEdge(v1,v2,495);
        Edge<String> e1 = graph.insertEdge(v1,v3,350);        
        graph.insertEdge(v2,v3,190);
        
        Set<Edge<String>> list = new HashSet<>();
        list = (Set<Edge<String>>) graph.outputEdges(v1);             
        assertTrue(graph.vertices().contains(v1) && graph.vertices().contains(v2) && graph.vertices().contains(v3));
    }   
}