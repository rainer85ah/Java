package graphs.EL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import graphs.EL.ELGraph;
import graphs.Edge;
import graphs.Vertex;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author jvelez
 */
public class ELGraphTest {
    
    public ELGraphTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of vertices method, of class ELGraph.
     */
    @Test
    public void testVertices() {
        System.out.println("vertices");
        ELGraph<String,Integer> graph = new ELGraph<> ();
        
        graph.insertVertex("Madrid");
        graph.insertVertex("Sevilla");
        Collection<? extends Vertex<String>> vertices = graph.vertices();

        ArrayList <String> cities = new ArrayList <>();
        for (Vertex<String> v : vertices) {
            cities.add(v.getValue());
        }
                
        assertTrue(cities.size() == 2);
        assertTrue(!cities.get(0).equals(cities.get(1)));
        assertTrue(cities.get(0).equals("Madrid") || cities.get(0).equals("Sevilla"));
        assertTrue(cities.get(1).equals("Madrid") || cities.get(1).equals("Sevilla"));
    }

    /**
     * Test of edges method, of class ELGraph.
     */
    @Test
    public void testEdges() {
        System.out.println("edges");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
        Vertex<String> v1 = graph.insertVertex("Madrid");
        Vertex<String> v2 = graph.insertVertex("Sevilla");
        Vertex<String> v3 = graph.insertVertex("Badajoz");
        
        graph.insertEdge(v1,v2,495);
        graph.insertEdge(v1,v3,350);
        graph.insertEdge(v2,v3,190);
        
        Collection<? extends Edge<Integer>> edges = graph.edges();

        ArrayList <Integer> kms = new ArrayList <>();
        for (Edge<Integer> e : edges) {
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
     * Test of opposite method, of class ELGraph.
     */
    @Test
    public void testOpposite() {
        System.out.println("opposite");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
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
     * Test of insert method, of class ELGraph.
     */
    @Test
    public void testInsertDuplicateEdge() {
        System.out.println("insert");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
        Vertex <String> v1 = graph.insertVertex("Madrid");
        Vertex <String> v2 = graph.insertVertex("Sevilla");
        Vertex <String> v3 = graph.insertVertex("Badajoz");
        
        graph.insertEdge(v1,v2,495);
        graph.insertEdge(v1,v2,5);

        assertEquals(graph.edges().size(),1);
    }
    
    /**
     * Test of incident method, of class ELGraph.
     */
    @Test
    public void testIncidentEdges() {
        System.out.println("incidentEdges");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
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
     * Test of endVertices method, of class ELGraph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("opposite");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
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
     * Test of areAdjacent method, of class ELGraph.
     */
    @Test
    public void testAreAdjacent() {
        System.out.println("areAdjacent");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
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
     * Test of replace method, of class ELGraph.
     */
    @Test
    public void testReplace_Vertex_GenericType() {
        System.out.println("replace");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
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
     * Test of replace method, of class ELGraph.
     */
    @Test
    public void testReplace_Edge_GenericType() {
        System.out.println("replace");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
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
     * Test of removeVertex method, of class ELGraph.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("removeVertex");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
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
     * Test of removeEdge method, of class ELGraph.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("removeEdge");
        ELGraph <String,Integer> graph = new ELGraph <> ();
        
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
