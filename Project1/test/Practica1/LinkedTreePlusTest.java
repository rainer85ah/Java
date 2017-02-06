package Practica1;

import static org.junit.Assert.*;

/**
 *
 * @author Rainer Arencibia
 */
public class LinkedTreePlusTest 
{
    private LinkedTree<String> tree = new LinkedTree<>();

    public void setTree() {

            Position<String> p = tree.addRoot("A");
            tree.add("B", p);
            Position<String> p1 = tree.add("C", p);
            tree.add("D", p);

            tree.add("E", p1);
            Position<String> p2 = tree.add("F", p1);

            tree.add("G", p2);
            Position<String> p3 = tree.add("H", p2);

            tree.add("I", p3);
            tree.add("J", p3);
            tree.add("K", p3);
            tree.add("L", p3);
    }

    public void testSize() {
            Position<String> p = this.tree.addRoot("+");
            this.tree.add("2", p);
            Position<String> h = this.tree.add("*", p);
            this.tree.add("3", h);
            this.tree.add("5", h);
            assertEquals(this.tree.size(), 5);
    }

    public void testSize2() {
            this.setTree();
            assertEquals(this.tree.size(), 12);
    }

    public void testRoot() {
            this.setTree();
            assertEquals(this.tree.root().getElement(), "A");

    }

    public void testIsEmpty() {
            assertEquals(this.tree.isEmpty(), true);
    }

    public void testIsEmpty2() {
            Position<String> p = this.tree.addRoot("B");
            this.tree.add("C", p);
            assertEquals(this.tree.isEmpty(), false);
    }

    public void testParent() {
            this.setTree();

            try {
                    Position<String> p = this.tree.root();
                    this.tree.parent(p);
            } catch (BoundaryViolationException e) {
                    assertTrue(true);
            }
    }

    public void testParent2() {
            Position<String> p = tree.addRoot("A");
            tree.add("B", p);
            Position<String> p1 = tree.add("C", p);
            tree.add("D", p);
            tree.add("E", p1);
            Position<String> p2 = tree.add("F", p1);
            tree.add("G", p2);
            Position<String> p3 = tree.add("H", p2);
            tree.add("I", p3);
            tree.add("J", p3);
            tree.add("K", p3);
            tree.add("L", p3);
            assertEquals(p2, tree.parent(p3));
    }

    public void testParent3() {
            this.setTree();

            try {
                    this.tree.parent(null);
            } catch (InvalidPositionException e) {
                    assertTrue(true);
            }
    }

    public void testPositions() {
            Position<String> p = this.tree.addRoot("+");
            this.tree.add("2", p);
            this.tree.add("3", p);
            String salida = "";
            for (Position<String> e : this.tree) {
                    salida += e.getElement();
            }
            assertEquals(salida, "+23");
    }

    public void testRemove() {
            Position<String> p = this.tree.addRoot("+");
            Position<String> q = this.tree.add("2", p);
            Position<String> h = this.tree.add("*", p);
            this.tree.add("3", h);
            this.tree.add("5", h);
            this.tree.remove(h);
            assertEquals(this.tree.size(), 2);

    }

    public void testRemove2() {
            this.setTree();
            this.tree.remove(this.tree.root());
            assertEquals(this.tree.size(), 0);
    }

    public void testRemove3() {
            Position<String> p = tree.addRoot("A");
            tree.add("B", p);
            Position<String> p1 = tree.add("C", p);
            tree.add("D", p);
            tree.add("E", p1);
            Position<String> p2 = tree.add("F", p1);
            tree.add("G", p2);
            Position<String> p3 = tree.add("H", p2);
            tree.add("I", p3);
            tree.add("J", p3);
            tree.add("K", p3);
            tree.add("L", p3);

            this.tree.remove(p2);

            StringBuilder s = new StringBuilder();
            for (Position<String> pos : this.tree) {
                    s.append(pos.getElement());
            }
            assertEquals(s.toString(), "ABCDE");
    }

    public void testGetUnmodifiableChildren() {
            Position<String> p = this.tree.addRoot("+");
            this.tree.add("2", p);
            this.tree.add("3", p);
            Iterable<? extends Position<String>> l = this.tree.children(p);
            try {
                    l.iterator().remove();
                    fail("The children collection has been modified");
            } catch (Exception e) {
                    assertTrue(true);
            }
    }

    public void testGetChildren() {
            Position<String> p = this.tree.addRoot("+");
            this.tree.add("2", p);
            this.tree.add("3", p);

            String salida = "";
            for (Position<String> e : this.tree.children(p)) {
                    salida += e.getElement();
            }
            assertEquals(salida, "23");
    }

    public void testGetChildren2() {
            Position<String> p = tree.addRoot("A");
            tree.add("B", p);
            Position<String> p1 = tree.add("C", p);
            tree.add("D", p);
            tree.add("E", p1);
            Position<String> p2 = tree.add("F", p1);
            tree.add("G", p2);
            Position<String> p3 = tree.add("H", p2);
            tree.add("I", p3);
            tree.add("J", p3);
            tree.add("K", p3);
            tree.add("L", p3);

            String salida = "";
            for (Position<String> e : this.tree.children(p3)) {
                    salida += e.getElement();
            }
            assertEquals(salida, "IJKL");
    }

    public void testIterator() {
            this.setTree();

            StringBuilder s = new StringBuilder();
            for (Position<String> pos : this.tree) {
                    s.append(pos.getElement());
            }
            assertEquals(s.toString(), "ABCDEFGHIJKL");
    }

    public void testIsRoot() {
            this.setTree();
            assertEquals(this.tree.root().getElement(), "A");

    }

    public void testIsRoot2() {
            try {
                    this.tree.isRoot(null);
            } catch (InvalidPositionException e) {
                    assertTrue(true);
            }
    }

    public void testSwapElements() {
            Position<String> p = tree.addRoot("A");
            tree.add("B", p);
            Position<String> p1 = tree.add("C", p);
            tree.add("D", p);
            tree.add("E", p1);
            Position<String> p2 = tree.add("F", p1);
            tree.add("G", p2);
            Position<String> p3 = tree.add("H", p2);
            tree.add("I", p3);
            tree.add("J", p3);
            tree.add("K", p3);
            tree.add("L", p3);

            this.tree.swapElements(p, p1);
            this.tree.swapElements(p2, p3);

            String salida = "";
            for (Position<String> e : this.tree) {
                    salida += e.getElement();
            }
            assertEquals(salida, "CBADEHGFIJKL");
    }


    public void testReplace() {
            Position<String> p = tree.addRoot("A");
            tree.add("B", p);
            Position<String> p1 = tree.add("C", p);
            tree.add("D", p);
            tree.add("E", p1);
            Position<String> p2 = tree.add("F", p1);
            tree.add("G", p2);
            Position<String> p3 = tree.add("H", p2);
            tree.add("I", p3);
            tree.add("J", p3);
            tree.add("K", p3);
            tree.add("L", p3);

            this.tree.replace(p, "X");
            this.tree.replace(p1, "Y");
            this.tree.replace(p2, "Z");
            this.tree.replace(p3, "W");

            String salida = "";
            for (Position<String> e : this.tree) {
                    salida += e.getElement();
            }
            assertEquals(salida, "XBYDEZGWIJKL");
    }

    /*
    public void testPreOrder() {
            this.setTree();
            String salida = "";
            for (Position<String> e : this.tree.preOrder()) {
                    salida += e.getElement();
            }
            assertEquals(salida, "ABCEFGHIJKLD");

    }

    public void testPosOrder() {
            this.setTree();
            String salida = "";
            for (Position<String> e : this.tree.posOrder()) {
                    salida += e.getElement();
            }
            assertEquals(salida, "BEGIJKLHFCDA");

    }
    */

    /**
    public LinkedTreePlusTest() 
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
     * Test of front method, of class LinkedTreePlus.
     
    @Test
    public void testFront() 
    {
        System.out.print("Test front: ");
        LinkedTreePlus instance = new LinkedTreePlus();
        
        T datosRoot = new T("Root",0);
        Position pRoot = instance.addRoot(datosRoot);        
        T datosHijo = new T("Hijo",1);
        Position pHijo = instance.add(datosHijo, pRoot);                
        T datosHijo2 = new T("Hijo2",2);
        Position pHijo2 = instance.add(datosHijo2, pRoot);        
        T datosHijo3 = new T("Hijo3",3);
        Position pHijo3 = instance.add(datosHijo3, pRoot);        
        List<Position> expResult = new LinkedList<>();
        expResult.add(pHijo);
        expResult.add(pHijo2);
        expResult.add(pHijo3);
        
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
     * Test of depth method, of class LinkedTreePlus.
     
    @Test
    public void testDepth() 
    {
        System.out.print("Test depth: ");
        LinkedTreePlus instance = new LinkedTreePlus();
        
        T datosRoot = new T("Root",0);
        Position pRoot = instance.addRoot(datosRoot);        
        T datosHijo = new T("Hijo",1);
        instance.add(datosHijo, pRoot);
        
        int expResult = 2;
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
     * Test of degree method, of class LinkedTreePlus.
     
    @Test
    public void testDegree() 
    {
        System.out.print("Test degree: ");
        LinkedTreePlus instance = new LinkedTreePlus();
        
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
     */
}