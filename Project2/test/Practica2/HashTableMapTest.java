package Practica2;

import java.util.ArrayList;
import java.util.Iterator;
import junit.framework.TestCase;
import java.util.Collections;

public class HashTableMapTest extends TestCase
{
	private HashTableMap<String, Integer> listin;
        Solver solver = new SolverDouble();
	
	public void defaultMap(){
	}
	

	public void testSize() 
        {
		listin = new HashTableMap<String, Integer>();	
		assertEquals(listin.size(), 0);	
		
		listin.put("Jose", new Integer(912127659));
		listin.put("Angel", new Integer(912127658));
		listin.put("Abraham", new Integer(912127657));
		listin.put("Mayte", new Integer(912127656));
		listin.put("Raul", new Integer(912127655));	
		listin.put("Jose", new Integer(1));
		listin.put("Angel", new Integer(2));
		listin.put("Abraham", new Integer(3));
		
		this.defaultMap();
		assertEquals(listin.size(), 5);

		listin.remove("Angel");
		listin.remove("Mayte");
		assertEquals(listin.size(), 3);
	}

	
	public void testIsEmpty() {
		HashTableMap<String, Integer> listin = new HashTableMap<String, Integer>(solver);
		assertEquals(listin.isEmpty(), true);
		listin.put("Jose", new Integer(912127654));
		assertEquals(listin.isEmpty(), false);
		listin.remove("Jose");
		assertEquals(listin.isEmpty(), true);
	}

	
	public void testPutAndGet() {
		HashTableMap<String, Integer> listin = new HashTableMap<String, Integer>(solver);

		try {
			listin.get(null);
			fail("Deberia lanzar: InvalidKeyException");

		} catch (InvalidKeyException e) {
			// OK
		}

		try {
			listin.put(null, new Integer(1213123));
			fail("Deberia lanzar: InvalidKeyException");

		} catch (InvalidKeyException e) {
			// OK
		}
		
		assertEquals(listin.get("Jose"), null);
				
		listin.put("Jose", new Integer(912127654));
		listin.put("Jose", new Integer(912127651));
		listin.put("Andres", new Integer(912127624));
		assertEquals(listin.size(), 2);
		assertEquals(listin.get("Jose"), new Integer(912127651));
		assertEquals(listin.get("Andres"), new Integer(912127624));
	}

	
	public void testRemove() {
		HashTableMap<String, Integer> listin = new HashTableMap<String, Integer>(solver);
		listin.put("Jose", new Integer(912127651));
		listin.put("Andres", new Integer(912127624));
		listin.remove("Andres");
		assertEquals(listin.size(), 1);
		assertEquals(listin.get("Jose"), new Integer(912127651));
		assertEquals(listin.get("Andres"), null);
		
		try {
			listin.remove(null);
			fail("Deberia lanzar: InvalidKeyException");

		} catch (InvalidKeyException e) {
			// OK
		}
	}

	
	public void testValues() {
		HashTableMap<String, Integer> listin = new HashTableMap<String, Integer>(solver);
		listin.put("Angel", new Integer(912127654));
		listin.put("Jose", new Integer(912127651));
		listin.put("Andres", new Integer(912127624));
		Iterable<Integer> values = listin.values();
		Iterator<Integer> it = values.iterator();
		
		ArrayList<Integer> l = new ArrayList<Integer>();

		
		while(it.hasNext()){
			l.add(it.next());		
		}
		
		
		assertEquals(l.size(), 3);
		assertEquals(l.contains(new Integer(912127654)), true);
		assertEquals(l.contains(new Integer(912127651)), true);
		assertEquals(l.contains(new Integer(912127624)), true);
	}

	
	public void testEntries() {
		HashTableMap<String, Integer> listin = new HashTableMap<String, Integer>(solver);
		listin.put("Angel", new Integer(912127654));
		listin.put("Jose", new Integer(912127651));
		listin.put("Andres", new Integer(912127624));
		Iterable<Entry<String,Integer>> entries = listin.entrySet();
		ArrayList<Entry<String,Integer>> l = new ArrayList<Entry<String,Integer>> ();
		
		
		for (Entry<String,Integer> i : entries) {
			l.add(i);
		}
		
		assertEquals(l.size(), 3);
		
		Iterator<Entry<String,Integer>> it = entries.iterator();
		
		while(it.hasNext()){
			Entry<String,Integer> e = it.next();
			assertEquals(l.contains(e), true);
		}
	}
	
	
	public void testKeys() {
		HashTableMap<String, Integer> listin = new HashTableMap<>(solver);
		listin.put("Angel", new Integer(912127654));
		listin.put("Jose", new Integer(912127651));
		listin.put("Andres", new Integer(912127624));
		Iterable<String> keys = listin.keySet();
		
		
		ArrayList<String> l = new ArrayList<>();

		for (String k : keys) {
			l.add(k);
		}
		
		assertEquals(l.size(), 3);
		
		Iterator<String> it = keys.iterator();
		
		while(it.hasNext()){
			String s = it.next();
			assertEquals(l.contains(s), true);
		}
	}


	
	public void testRehash() {
		HashTableMap<Integer, Integer> listin1 = new HashTableMap<>(10, solver);

		final int NUM_ENTRIES = 1000;

		// Testing size
		for (int cont = 0; cont < NUM_ENTRIES; cont++) {
			listin1.put(cont, cont);
		}
		assertEquals(listin1.size(), NUM_ENTRIES);

		// Testing elements
		listin1 = new HashTableMap<Integer, Integer>();
		int cont2, cont;
		for (cont = 1; cont <= NUM_ENTRIES; cont++) {
			listin1.put(cont, cont);
			cont2 = 1;
			while ((listin1.get(cont2) != null) && (cont2 <= cont)) {
				cont2++;
			}
			assertEquals(cont2, cont + 1);
		}
	}
}
