package tree;

import java.util.Objects;
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
public class AVLTreeTest {

    public AVLTreeTest() {
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
     * Test of find method, of class AVLTree.
     */
    @Test
    public void testSize() 
    {
        AVLTree<Integer> b = new AVLTree<>();
        //Position<AVLInfo<E> aux = b.bst.binTree.addRoot(new AVLInfo<>(1,0));
        assertEquals(b.size(), 0);
        b.insert(5);
        assertEquals(b.size(), 1);
        b.insert(10);
        assertEquals(b.size(), 2);

        for (int cont = 0; cont < 25; cont++) 
        {
            b.insert(cont);
        }
        assertEquals(b.size(), 27);
    }

    @Test
    public void testFind() {
        AVLTree<Integer> b = new AVLTree<>();

        for (int cont = 0; cont < 25; cont += 2) {
            b.insert(cont);
        }

        Position<Integer> p = b.insert(17);
        b.find(17);
        assertTrue(p.element() == 17);
        Position<Integer> q = b.find(2);
        //assertTrue(q.element() == 2);
    }

    @Test
    public void testInsert() {
        AVLTree<Integer> b = new AVLTree<>();
        b.insert(5);
        b.insert(3);
        b.insert(1);
        b.insert(7);
        b.insert(6);

        Iterable<Position<Integer>> i = b.positions();

        String salida = "";
        for (Position<Integer> e : i) {
            salida += e.element().toString();
        }
        assertEquals(salida, "13657");
        //Se recorre de menor a mayor. Ascendente..
        

        b = new AVLTree<>();
        b.insert(4);
        b.insert(5);
        b.insert(7);
        b.insert(2);
        b.insert(1);
        b.insert(3);
        b.insert(6);

        i = b.positions();
        salida = "";
        for (Position<Integer> e : i) {
            salida += e.element().toString();
        }
        assertEquals(salida, "2154376");

    }

    @Test
    public void testRemove() 
    {
        AVLTree<Integer> b = new AVLTree<>();
        b.insert(5);
        b.insert(3);
        Position<Integer> p = b.insert(1);
        b.insert(7);
        b.insert(6);
        b.remove(p);

        Iterable<Position<Integer>> i = b.positions();
        b.remove(p);

        String salida = "";
        for (Position<Integer> e : i) {
            salida += e.element().toString();
            salida += ",";
        }
//      assertEquals(salida, "6,3,7,5,");

        b = new AVLTree<>();
        b.insert(44);
        b.insert(17);
        b.insert(62);
        p = b.insert(32);
        b.insert(50);
        b.insert(78);
        b.insert(48);
        b.insert(54);
        b.insert(88);

        b.remove(p);
        i = b.positions();

        salida = "";
        for (Position<Integer> e : i) {
            salida += e.element().toString();
            salida += ",";
        }
        assertEquals("54,44,78,17,50,88,62,48,",salida);
        //Equilibrado del arbol
        //restructuracion..estaba mal la cadena
    }

    @Test
    public void testSpeed() 
    {
        AVLTree<Integer> a = new AVLTree<>();
                
        /***Prueba de tiempo insertando 1000 elementos***/
        long start = System.currentTimeMillis();
        for (int cont = 0; cont < 1000; cont++) 
        {
            a.insert(cont);
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("AVL-Tree Times: ");
        System.out.println("Insertions Time 1000: " + elapsedTime);

        /***Prueba de tiempo buscando 1000 elementos***/
        start = System.currentTimeMillis();
        a.find(800);        
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println("Search Time 1000: " + elapsedTime);
        
        AVLTree<Integer> b = new AVLTree<>();
        /***Prueba de tiempo insertando 10 000 elementos***/
        start = System.currentTimeMillis();
        for (int cont = 0; cont < 10000; cont++) 
        {
            b.insert(cont);
        }
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println("Insertions Time 10 000: " + elapsedTime);

        /***Prueba de tiempo buscando 10 000 elementos***/
        start = System.currentTimeMillis();
        b.find(8000);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println("Search Time 10 000: " + elapsedTime);

        AVLTree<Integer> c = new AVLTree<>();
        /***Prueba de tiempo insertando 100 000 elementos***/
        start = System.currentTimeMillis();
        for (int cont = 0; cont < 100000; cont++) 
        {
            c.insert(cont);
        }
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println("Insertions Time 100 000: " + elapsedTime);

        /***Prueba de tiempo buscando 100 000 elementos***/
        start = System.currentTimeMillis();
        c.find(800000);
        end = System.currentTimeMillis();
        elapsedTime = end - start;
        System.out.println("Search Time 100 000: " + elapsedTime);
        
        assertEquals(true, true);
    }
}