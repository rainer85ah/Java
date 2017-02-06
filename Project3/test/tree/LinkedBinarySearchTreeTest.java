package tree;

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
public class LinkedBinarySearchTreeTest {

    public LinkedBinarySearchTreeTest() {
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

    @Test
    public void testSize() {
        LinkedBinaryTree<String> t = new LinkedBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        t.insertRight(h, "5");
        assertEquals(t.size(), 5);
    }

    @Test
    public void testIsEmpty() {
        LinkedBinaryTree<String> t = new LinkedBinaryTree<>();
        assertEquals(t.isEmpty(), true);
    }

    @Test
    public void testPositions() {
        LinkedBinaryTree<String> t = new LinkedBinaryTree<>();
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        t.insertRight(p, "3");
        String salida = "";
        for (Position<String> e : t) {
            salida += e.element();
        }
        assertEquals(salida, "+23");
    }

    @Test
    public void testRemove() {
        LinkedBinaryTree<String> t = new LinkedBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> q = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        t.insertRight(h, "5");
        t.remove(q);
        assertEquals(t.size(), 4);

    }

    @Test
    public void testSwapElements() {
        LinkedBinaryTree<String> t = new LinkedBinaryTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> p1 = t.insertLeft(p, "2");
        Position<String> p2 = t.insertRight(p, "3");
        t.swapElements(p1, p2);
        String salida = "";
        for (Position<String> e : t) {
            salida += e.element();
        }
        assertEquals(salida, "+32");
    }

    @Test
    public void testBigSize() {
        LinkedBinarySearchTree<Integer> b = new LinkedBinarySearchTree<>();
        assertEquals(b.size(), 0);
        b.insert(5);
        assertEquals(b.size(), 1);
        b.insert(10);
        assertEquals(b.size(), 2);

        for (int cont = 0; cont < 25; cont++) {
            b.insert(cont);
        }

        assertEquals(b.size(), 27);
    }

    @Test
    public void testFind() 
    {
        LinkedBinarySearchTree<Integer> b = new LinkedBinarySearchTree<>();

        for (int cont = 0; cont < 25; cont += 2) {
            b.insert(cont);
        }

        Position<Integer> p = b.insert(17);
        b.find(17);
        assertTrue(p.element() == 17);
    }

    @Test
    public void testInsert() {
        LinkedBinarySearchTree<Integer> b = new LinkedBinarySearchTree<>();
        b.insert(5);
        b.insert(3);
        b.insert(6);
        b.insert(7);
        b.insert(1);
        b.insert(6);

        Iterable<Position<Integer>> i = b.positions();

        String salida = "";
        for (Position<Integer> e : i) {
            salida += e.element().toString();
        }
        assertEquals(salida, "536176");
    }

    @Test
    public void testSpeed() 
    {
        System.out.println("BinarySearchTree Times: ");
        LinkedBinarySearchTree<Integer> a = new LinkedBinarySearchTree<>();
             
        /***Prueba de tiempo insertando 1000 elementos***/
        long start = System.currentTimeMillis();
        for (int cont = 0; cont < 1000; cont++) 
        {
            a.insert(cont);
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        
        System.out.print("Insertions Time 1000: ");
        System.out.println(elapsedTime);

        /***Prueba de tiempo buscando 1000 elementos***/
        start = System.currentTimeMillis();
        a.find(800);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.print("Search Time 1000: ");
        System.out.println(elapsedTime);
        
        LinkedBinarySearchTree<Integer> b = new LinkedBinarySearchTree<>();
        /***Prueba de tiempo insertando 10 000 elementos***/
        start = System.currentTimeMillis();
        for (int cont = 0; cont < 10000; cont++) 
        {
            b.insert(cont);
        }
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.print("Insertions Time 10 000: ");
        System.out.println(elapsedTime);

        /***Prueba de tiempo buscando 10 000 elementos***/
        start = System.currentTimeMillis();
        b.find(8000);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.print("Search Time 10 000: ");
        System.out.println(elapsedTime);

        LinkedBinarySearchTree<Integer> c = new LinkedBinarySearchTree<>();
        /***Prueba de tiempo insertando 100 000 elementos***/
        start = System.currentTimeMillis();
        for (int cont = 0; cont < 100000; cont++) 
        {
            c.insert(cont);
        }
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.print("Insertions Time 100 000: ");
        System.out.println(elapsedTime);

        /***Prueba de tiempo buscando 100 000 elementos***/
        start = System.currentTimeMillis();
        c.find(80000);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.print("Search Time 100 000: ");
        System.out.println(elapsedTime);
        
        assertEquals(elapsedTime == 0, true);
    }
}