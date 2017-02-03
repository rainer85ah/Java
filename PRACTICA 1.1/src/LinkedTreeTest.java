/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package material.tree.linkedtree;

import junit.framework.TestCase;
import material.tree.Position;

/**
 *
 * @author jfrancisco.velez
 */
public class LinkedTreeTest extends TestCase {

    public void testSize() {
        LinkedTree<String> t = new LinkedTree<>();
        Position<String> p = t.addRoot("+");
        t.add("2", p);
        Position<String> h = t.add("*", p);
        t.add("3", h);
        t.add("5", h);
        assertEquals(t.size(), 5);
    }

    
    public void testIsEmpty() {
        LinkedTree<String> t = new LinkedTree<>();
        assertEquals(t.isEmpty(), true);
    }

    
    public void testPositions() {
        LinkedTree<String> t = new LinkedTree<>();
        Position<String> p = t.addRoot("+");
        t.add("2", p);
        t.add("3", p);
        String salida = "";
        for (Position<String> e : t) {
            salida += e.getElement();
        }
        assertEquals(salida, "+23");
    }

    
    public void testRemove() {
        LinkedTree<String> t = new LinkedTree<>();
        Position<String> p = t.addRoot("+");
        Position<String> q = t.add("2", p);
        Position<String> h = t.add("*", p);
        t.add("3", h);
        t.add("5", h);
        t.remove(h);
        assertEquals(t.size(), 2);

    }
    
    public void testGetUnmodifiableChildren() {
        LinkedTree<String> t = new LinkedTree<>();
        Position<String> p = t.addRoot("+");
        t.add("2", p);
        t.add("3", p);
        Iterable<? extends Position<String>> l = t.children(p);
        try {
            l.iterator().remove();
            fail("The children collection has been modified");
        } catch (Exception e) {
            assertTrue(true);
        }
    }
    
    public void testgetChildren() {
        LinkedTree<String> t = new LinkedTree<>();
        Position<String> p = t.addRoot("+");
        t.add("2", p);
        t.add("3", p);
        
        String salida = "";
        for (Position<String> e : t.children(p)) {
            salida += e.getElement();
        }
        assertEquals(salida, "23");
    }
    
}