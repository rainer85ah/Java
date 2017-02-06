package Practica2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTableMapSC<K,V> implements Map<K,V>
{
    protected static final int INIT_CAPACITY = 1000;
    protected static final double LOAD_FACTOR = 0.75;
    private int n;         // number of ENTRIES(K,V)
    private int capacity; // hash table size, sometimes differents from de INIT_CAPACITY
    protected ArrayList<LinkedList<HashEntry<K,V>>> bucket[]; 
    
    //Constructor con bucket de tamano 1000 elementos
    public HashTableMapSC() 
    {
        capacity = INIT_CAPACITY;
        bucket = new ArrayList[INIT_CAPACITY];   
        n = 0;
        for (int i=0; i < capacity; i++)
            bucket[i].add(new LinkedList<>());
    }
    
    //Constructor con bucket de tamano la cantidad especificada
    public HashTableMapSC(int cap) 
    {
        capacity = cap;
        this.bucket = new ArrayList[capacity];
        this.n = 0;                 
        for (int i=0; i < capacity; i++)
            this.bucket[i].add(new LinkedList<>());
    } 
    
    protected class HashEntry<K,V> implements Entry<K,V>
    {
        protected K key;
        protected V value;

        public HashEntry(K k, V v) 
        {
            this.key = k;
            this.value = v;
        }

        public V getValue() 
        {
            return value;
        }

        public K getKey() 
        {
            return key;
        }

        //Devuelve el valor que habia anteriormente.
        public V setValue(V val) 
        {
            V oldValue = value;
            this.value = val;
            return oldValue;
        }

        //Compara por llave y valor
        public boolean equals(HashEntry<K,V> entry) 
        {            
            if (entry.getClass() != this.getClass())
                return false;
            
            HashEntry<K, V> ent;
            try {
                ent = (HashEntry<K, V>) entry;
            } catch (ClassCastException ex) 
            {
                return false;
            }
            return (ent.getKey().equals(this.key));
        }

        @Override
        public int hashCode() 
        {//Double
            final int PRIMO = 997;
            int hash = 97;
            hash = 37 * hash + hashKey(key);
            int index = PRIMO - (hash % PRIMO);
            hash = index % capacity;
            return hash;
        }
                
        public int hashKey(K key)
        {
            return (key.hashCode() & 0x7fffffff) % capacity;
        }
        
        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    /**
     * Returns the number of entries in the hash table.
     * @return the size
     */
    public int size() 
    {
        return n;
    }

    /**
     * Returns whether or not the table is empty.
     * @return true if the size is 0
     */
    public boolean isEmpty() 
    {
        return (size() == 0);
    }
    
    // return value associated with key, the firstone of the list, null if no such key
    public V get(K k) 
    {
        int i = hashCode();
        return (this.bucket[i].get(0).getFirst().getValue());
    } 

    /**
     * Put a key-value pair in the map, replacing previous one if it exists.
     * @param key
     * @param value
     * @return 
     */
    // insert key-value pair into the table
    public V put(K key,V val) 
    {
        if (val == null) 
        {
            remove(key);
            return val;
        }

        // double table size if average length of list >= 10
        if (n > capacity*LOAD_FACTOR)
            rehash();

        int i = hashCode();
        if (!this.bucket[i].contains(key)) 
            n++;
            
        HashEntry<K,V> e = null;
        e.setValue(val);
        e.key = key;
        bucket[i].get(0).add(e);
        return(val);
    } 

    /**
     * Doubles the size of the hash table and rehashes all the entries.
     * Calling when the load factor is 50%
     */
    // resize the hash table to have the given number of chains b rehashing all of the keys
    // Resize..
    public void rehash() 
    {
        int newCapacity = (capacity*2) + 1;   
        ArrayList<LinkedList<HashEntry<K,V>>> newBucket[];
        newBucket = new ArrayList[newCapacity];  
        
        for (int i = 0; i < newCapacity; i++) 
            newBucket[i].add(new LinkedList<>());
        
        for (int i = 0; i < capacity; i++) 
        {
            for (Entry<K,V> iEntry : this.entrySet()) {
                newBucket[i].add((LinkedList<HashEntry<K, V>>) iEntry);
            }
        }
        capacity = newCapacity;
        bucket = newBucket;
    }

    /**
     * Removes the key-value pair with a specified key.
     * @param key
     * @return 
     */    
    // delete key (and associated value) if key is in the table
    public V remove(K key) 
    {
        int i = hashCode();
        if (bucket[i].contains(key)) 
            n--;
        V val = bucket[i].get(0).get(0).getValue();
        bucket[i].remove(key);
        return val;
    } 

    /**
     * Returns an iterable object containing all of the entries.
     * @return 
     */     
    public Iterable<Entry<K, V>> entrySet() 
    {
        List<HashEntry<K,V>> entries = new ArrayList<>();
        
        for (int i=0; i < capacity; i++) 
        {
            if (bucket[i] != null) 
            {
                HashEntry<K,V> e = null;
                e.key = bucket[i].get(0).get(0).getKey();
                e.value = bucket[i].get(0).get(0).getValue();
                entries.add(e);
            }
        }        
        return (Iterable<Entry<K, V>>) (HashEntry<K,V>) entries;
    }

    // return keys in symbol table as an Iterable
    public Iterable<K> keySet() 
    {
        List<K> keys = new ArrayList<>();
        for (int i=0; i < capacity; i++) 
        {
            if (bucket[i] != null) 
            {
                K k = null;
                k = bucket[i].get(0).get(0).getKey();
                keys.add(k);
            }
        }
        return keys;
    }
    
    /**
     * Returns an iterable object containing all of the values.
     * @return 
     */    
    public Iterable<V> values() 
    {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < capacity; i++) 
        {
            if (bucket[i] != null) 
            {
                V v = null;
                v = bucket[i].get(0).get(0).getValue();
                values.add(v);
            }
        }
        return values;
    }
}