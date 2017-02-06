/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import graphs.AL.ALGraph;
import graphs.AL.ALVertex;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
public class BreadthSearchTest {
    
    public BreadthSearchTest() {
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
     * Test of getPath method, of class BreadthSearch.
     */
    @Test
    public void testGetPath() 
    {
        System.out.println("getPath Trivial");
        ALGraph<String,Integer> graph = new ALGraph<>(); 
        
        Vertex<String> v1 = graph.insertVertex("A");
        Vertex<String> v2 = graph.insertVertex("B");
        Vertex<String> v3 = graph.insertVertex("C");
        Vertex<String> v4 = graph.insertVertex("D");
        Vertex<String> v5 = graph.insertVertex("E");
        Vertex<String> v6 = graph.insertVertex("F");
        Vertex<String> v7 = graph.insertVertex("G");
        Vertex<String> v8 = graph.insertVertex("H");
        //A
        graph.insertEdge(v1, v2, 1);
        graph.insertEdge(v1, v5, 1);
        graph.insertEdge(v1, v6, 1);
        //B
        graph.insertEdge(v2, v3, 1);
        graph.insertEdge(v2, v6, 1);
        //C
        graph.insertEdge(v3, v4, 1);
        //D
        graph.insertEdge(v4, v7, 1);
        graph.insertEdge(v4, v8, 1);
        //E
        graph.insertEdge(v5, v6, 1);
        
        ALVertex<String,Integer> start = (ALVertex<String,Integer>) v2;
        ALVertex<String,Integer> end = (ALVertex<String,Integer>) v8;
        BreadthSearch<String,Integer> DFS = new BreadthSearch<>();
        
        List<Vertex> expResult = new LinkedList<>();
        expResult.add(start);
        Collection<? extends Vertex> result = DFS.getPath(graph, start, end);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPath method, of class BreadthSearch.
     */
    @Test
    public void testGetPath1() 
    {
        System.out.println("getPath Figura");
        ALGraph<String,Integer> graph = new ALGraph<>(); 
        
        Vertex<String> v1 = graph.insertVertex("A");
        Vertex<String> v2 = graph.insertVertex("B");
        Vertex<String> v3 = graph.insertVertex("C");
        Vertex<String> v4 = graph.insertVertex("D");
        Vertex<String> v5 = graph.insertVertex("E");
        Vertex<String> v6 = graph.insertVertex("F");
        Vertex<String> v7 = graph.insertVertex("G");
        Vertex<String> v8 = graph.insertVertex("H");
        Vertex<String> v9 = graph.insertVertex("I");
        Vertex<String> v10 = graph.insertVertex("J");
        Vertex<String> v11 = graph.insertVertex("K");
        Vertex<String> v12 = graph.insertVertex("L");
        Vertex<String> v13 = graph.insertVertex("M");
        Vertex<String> v14 = graph.insertVertex("N");
        Vertex<String> v15 = graph.insertVertex("O");
        Vertex<String> v16 = graph.insertVertex("P");
        //A
        graph.insertEdge(v1, v2, 1);
        graph.insertEdge(v1, v5, 1);
        graph.insertEdge(v1, v6, 1);
        //B
        graph.insertEdge(v2, v3, 1);
        graph.insertEdge(v2, v6, 1);
        //C
        graph.insertEdge(v3, v4, 1);
        //D
        graph.insertEdge(v4, v7, 1);
        graph.insertEdge(v4, v8, 1);
        //E
        graph.insertEdge(v5, v6, 1);
        graph.insertEdge(v5, v9, 1);
        //F
        graph.insertEdge(v6, v9, 1);
        //G
        graph.insertEdge(v7, v10, 1);
        graph.insertEdge(v7, v11, 1);
        graph.insertEdge(v7, v12, 1);
        //H
        graph.insertEdge(v8, v12, 1);
        //I
        graph.insertEdge(v9, v10, 1);
        graph.insertEdge(v9, v13, 1);
        //J
        graph.insertEdge(v10, v11, 1);
        //K
        graph.insertEdge(v11, v14, 1);
        graph.insertEdge(v11, v15, 1);
        //L
        graph.insertEdge(v12, v16, 1);
        //M
        graph.insertEdge(v13, v14, 1);
        //N
        //v14
        
        //O
        graph.insertEdge(v15, v16, 1);
        
        ALVertex<String,Integer> start = (ALVertex<String,Integer>) v2;
        ALVertex<String,Integer> end = (ALVertex<String,Integer>) v12;
        BreadthSearch<String,Integer> DFS = new BreadthSearch<>();
        
        List<Vertex> expResult = new LinkedList<>();
        expResult.add(start);
        Collection<? extends Vertex> result = DFS.getPath(graph, start, end);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPath method, of class BreadthSearch.
     */
    @Test
    public void testGetPath2() 
    {
        System.out.println("getPath conBucle");
        ALGraph<String,Integer> graph = new ALGraph<>(); 
        
        Vertex<String> v1 = graph.insertVertex("A");
        Vertex<String> v2 = graph.insertVertex("B");
        Vertex<String> v3 = graph.insertVertex("C");
        Vertex<String> v4 = graph.insertVertex("D");
        Vertex<String> v5 = graph.insertVertex("E");
        Vertex<String> v6 = graph.insertVertex("F");
        Vertex<String> v7 = graph.insertVertex("G");
        Vertex<String> v8 = graph.insertVertex("H");
        Vertex<String> v9 = graph.insertVertex("I");
        Vertex<String> v10 = graph.insertVertex("J");
        Vertex<String> v11 = graph.insertVertex("K");
        Vertex<String> v12 = graph.insertVertex("L");
        Vertex<String> v13 = graph.insertVertex("M");
        Vertex<String> v14 = graph.insertVertex("N");
        Vertex<String> v15 = graph.insertVertex("O");
        Vertex<String> v16 = graph.insertVertex("P");
        //A
        graph.insertEdge(v1, v2, 1);
        graph.insertEdge(v1, v5, 1);
        graph.insertEdge(v1, v6, 1);
        //B
        graph.insertEdge(v2, v3, 1);
        graph.insertEdge(v2, v6, 1);
        //C
        graph.insertEdge(v3, v4, 1);
        //D
        graph.insertEdge(v4, v7, 1);
        graph.insertEdge(v4, v8, 1);
        //E
        graph.insertEdge(v5, v6, 1);
        graph.insertEdge(v5, v9, 1);
        //F Hacen otro cuadrado con el grafo de la figura
        graph.insertEdge(v6, v9, 1);
        graph.insertEdge(v6, v10, 1);
        //G
        graph.insertEdge(v7, v10, 1);
        graph.insertEdge(v7, v11, 1);
        graph.insertEdge(v7, v12, 1);
        //H
        graph.insertEdge(v8, v12, 1);
        //I
        graph.insertEdge(v9, v10, 1);
        graph.insertEdge(v9, v13, 1);
        //J
        graph.insertEdge(v10, v11, 1);
        //K
        graph.insertEdge(v11, v14, 1);
        graph.insertEdge(v11, v15, 1);
        //L
        graph.insertEdge(v12, v16, 1);
        //M
        graph.insertEdge(v13, v14, 1);
        //N Hacen un cuadrado con el grafo de la figura.
        graph.insertEdge(v14, v15, 1);        
        //O
        graph.insertEdge(v15, v16, 1);
        
        ALVertex<String,Integer> start = (ALVertex<String,Integer>) v2;
        ALVertex<String,Integer> end = (ALVertex<String,Integer>) v12;
        BreadthSearch<String,Integer> DFS = new BreadthSearch<>();
        
        List<Vertex> expResult = new LinkedList<>();
        expResult.add(start);
        List<? extends Vertex> result = DFS.getPath(graph, start, end);
        assertEquals(expResult, result);
    }
}
