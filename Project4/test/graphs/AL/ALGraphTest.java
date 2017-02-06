package graphs.AL;

import graphs.Edge;
import graphs.Vertex;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jvelez
 */
public class ALGraphTest {
    
    public ALGraphTest() {
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
     * Test of vertices method, of class ALGraph.
     * ArrarList array[int][int];
     */
    @Test
    public void testVertices() 
    {
        System.out.println("vertices");
        ALGraph<String,Integer> graph = new ALGraph<>();
        
        graph.insertVertex("Madrid");
        graph.insertVertex("Sevilla");
        Collection<? extends Vertex<String>> vertices = (Collection<? extends Vertex<String>>) graph.vertices();

        ArrayList <String> cities = new ArrayList <>();
        for (Vertex<String> v : vertices) 
        {
            cities.add(v.getValue());
        }
                
        assertTrue(cities.size() == 2);
        assertTrue(!cities.get(0).equals(cities.get(1)));
        assertTrue(cities.get(0).equals("Madrid") || cities.get(0).equals("Sevilla"));
        assertTrue(cities.get(1).equals("Madrid") || cities.get(1).equals("Sevilla"));
    }

    /**
     * Test of edges method, of class ALGraph.
     */
    @Test
    public void testEdge() 
    {
        System.out.println("edges");
        ALGraph<String,Integer> graph = new ALGraph<>();
        
        Vertex<String> v1 = graph.insertVertex("Madrid");
        Vertex<String> v2 = graph.insertVertex("Sevilla");
        Vertex<String> v3 = graph.insertVertex("Badajoz");
        
        graph.insertEdge(v1,v2,495);
        graph.insertEdge(v1,v3,350);
        graph.insertEdge(v2,v3,190);
        
        Collection<? extends Edge<Integer>> edges = graph.edges();
        List<Integer> kms = new ArrayList<>();
        
        for (Edge<Integer> e : edges) 
        {
            kms.add(e.getValue());
        }
        
        assertTrue(kms.size() == 3);
        assertTrue(!kms.get(0).equals(kms.get(1)));
        assertTrue(!kms.get(0).equals(kms.get(2)));
        assertTrue(!kms.get(1).equals(kms.get(2)));

        assertTrue(kms.get(0) == 495 || kms.get(0) == 350 || kms.get(0) == 190);
        assertTrue(kms.get(1) == 495 || kms.get(1) == 350 || kms.get(1) == 190);
        assertTrue(kms.get(2) == 495 || kms.get(2) == 350 || kms.get(2) == 190);
    }

    /**
     * Test of opposite method, of class ALGraph.
     */
    @Test
    public void testOpposite() {
        System.out.println("opposite");
        ALGraph <String,Integer> graph = new ALGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        Edge<Integer> e1 = graph.insertEdge(v1,v2,495);
        Edge<Integer> e2 = graph.insertEdge(v1,v3,350);
        Edge<Integer> e3 = graph.insertEdge(v2,v3,190);
        
        assertEquals(graph.opposite(v1, e1),v2);
        assertEquals(graph.opposite(v2, e1),v1);
        
        assertEquals(graph.opposite(v1, e2),v3);
        assertEquals(graph.opposite(v3, e2),v1);

        assertEquals(graph.opposite(v2, e3),v3);
        assertEquals(graph.opposite(v3, e3),v2);                

        try {
            graph.opposite(v3, e1);
            fail("V3 is not vertex of e1");
        } catch (RuntimeException e) {
            assert true;
        }
    }

    /**
     * Test of insert method, of class ALGraph.
     */
    @Test
    public void testInsertDuplicateEdge() {
        System.out.println("insert");
        ALGraph <String,Integer> graph = new ALGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        graph.insertEdge(v1,v2,495);
        graph.insertEdge(v1,v2,5);

        assertEquals(graph.edges().size(),1);
    }
    
    /**
     * Test of incident method, of class ALGraph.
     */
    @Test
    public void testIncidentEdge() {
        System.out.println("incidentEdge");
        ALGraph <String,Integer> graph = new ALGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        Edge<Integer> e1 = graph.insertEdge(v1,v2,495);
        Edge<Integer> e2 = graph.insertEdge(v1,v3,350);
        Edge<Integer> e3 = graph.insertEdge(v2,v3,190);
        Edge<Integer> e4 = graph.insertEdge(v2,v2,0);
        
        assertEquals(graph.incidentEdges(v1).size(),2);
        assertTrue(graph.incidentEdges(v1).contains(e1));
        assertTrue(graph.incidentEdges(v1).contains(e2));
        
        assertEquals(graph.incidentEdges(v2).size(),3);
        assertTrue(graph.incidentEdges(v2).contains(e1));
        assertTrue(graph.incidentEdges(v2).contains(e3));
        assertTrue(graph.incidentEdges(v2).contains(e4));
    }

    /**
     * Test of endVertices method, of class ALGraph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("opposite");
        ALGraph <String,Integer> graph = new ALGraph <> ();
        
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
     * Test of areAdjacent method, of class ALGraph.
     */
    @Test
    public void testAreAdjacent() 
    {
        System.out.println("areAdjacent");
        ALGraph <String,Integer> graph = new ALGraph <> ();
        
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
     * Test of replace method, of class ALGraph.
     */
    @Test
    public void testReplace_Vertex_GenericType() {
        System.out.println("replace");
        ALGraph <String,Integer> graph = new ALGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        Edge<Integer> e1 = graph.insertEdge(v1,v2,495);
        Edge<Integer> e2 = graph.insertEdge(v1,v3,350);
        Edge<Integer> e3 = graph.insertEdge(v2,v3,190);
        
        String old = graph.replace(v1, "Jaen");
        
        assertEquals(old, "Madrid");
        assertEquals(v1.getValue(), "Jaen");
    }

    /**
     * Test of replace method, of class ALGraph.
     */
    @Test
    public void testReplace_Edge_GenericType() {
        System.out.println("replace");
        ALGraph <String,Integer> graph = new ALGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        Edge<Integer> e1 = graph.insertEdge(v1,v2,495);
        Edge<Integer> e2 = graph.insertEdge(v1,v3,350);
        Edge<Integer> e3 = graph.insertEdge(v2,v3,190);
        
        int old = graph.replace(e1,500);
        
        assertEquals(old, 495);
        assertEquals((int)e1.getValue(), 500);
    }

    /**
     * Test of removeVertex method, of class ALGraph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("removeVertex");
        ALGraph <String,Integer> graph = new ALGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        Edge<Integer> e1 = graph.insertEdge(v1,v3,495);
        Edge<Integer> e2 = graph.insertEdge(v2,v3,190);
        
        String old = graph.removeVertex(v3);        
        
        assertEquals(old, "Badajoz");
        assertEquals(graph.vertices().size(), 2);
        assertEquals(graph.edges().size(), 0);
    }

    /**
     * Test of removeEdge method, of class ALGraph.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("removeEdge");
        ALGraph <String,Integer> graph = new ALGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        graph.insertEdge(v1,v3,495);
        Edge<Integer> e = graph.insertEdge(v2,v3,190);
        
        int old = graph.removeEdge(e);
        
        assertEquals(190,old);
        assertEquals(3,graph.vertices().size());
        assertEquals(1,graph.edges().size());
    }
}