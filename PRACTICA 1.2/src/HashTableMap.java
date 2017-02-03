package practica2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A hash table with linear probing and the MAD hash function
 */
/**
 * A hash table data structure that uses linear probing to handle collisions.
 * The hash function uses the built-in hashCode method and the
 * multiply-add-and-divide method. The load factor kept less than or
 * equal to 0.5. When the load factor reaches 0.5, the entries are rehashed into
 * a new bucket array with twice the capacity.
 *
 * @author R. Cabido, A. Duarte, and J. Velez
 * @param <K> Key type
 * @param <V> Value type
 */
public class HashTableMap<K, V> implements Map<K, V> {

    public enum OperationType {
        found, notFound
    };

    /**
     * @param <K> Key type
     * @param <V> Value type
     *
     */
    protected class HashEntry<K, V> implements Entry<K, V> {

        protected K key;
        protected V value;

        public HashEntry(K k, V v) {
            key = k;
            value = v;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public K getKey() {
            return key;
        }

        public V setValue(V val) {
            V oldValue = value;
            value = val;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            
            if (o.getClass() != this.getClass())
                return false;
            
            HashEntry<K, V> ent;
            try {
                ent = (HashEntry<K, V>) o;
            } catch (ClassCastException ex) {
                return false;
            }
            return (ent.getKey().equals(this.key)) && (ent.getValue().equals(this.value));
        }

        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    protected class HashEntryIndex {

        int index;
        OperationType operation;

        public HashEntryIndex(int index, OperationType operation) {
            this.index = index;
            this.operation = operation;
        }
    }

    protected final Entry<K, V> AVAILABLE = new HashEntry<>(null, null);
    protected int n = 0; // number of entries in the dictionary

    protected int prime, capacity; // prime factor and capacity of bucket array
    protected HashEntry<K, V>[] bucket;// bucket array
    protected long scale, shift; // the shift and scaling factors

    /**
     * Creates a hash table with prime factor 109345121 and capacity 1000.
     */
    public HashTableMap() {
        this(109345121, 1000); // reusing the constructor HashTableMap(int p, int cap)
    }

    /**
     * Creates a hash table with prime factor 109345121 and given capacity.
     * @param cap initial capacity
     */
    public HashTableMap(int cap) {
        this(109345121, cap); // reusing the constructor HashTableMap(int p, int cap)
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     * @param p prime number
     * @param cap initial capacity
     */
    public HashTableMap(int p, int cap) {
        this.n = 0;

        this.prime = p;
        this.capacity = cap;

        this.bucket = (HashEntry<K, V>[]) new HashEntry[capacity]; // safe cast
        Random rand = new Random();
        this.scale = rand.nextInt(prime - 1) + 1;
        this.shift = rand.nextInt(prime);
    }

    /**
     * Determines whether a key is valid.
     * @param k Key
     */
    protected void checkKey(K k) {
        //We cannot check the second test (i.e., k instanceof K) since we do not know the class K 
        if (k == null) {
            throw new InvalidKeyException("Invalid key: null.");
        }
    }

    /**
     * Hash function applying MAD method to default hash code.
     * @param key Key
     * @return 
     */
    protected int hashValue(K key) {
        return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    /**
     * Returns the number of entries in the hash table.
     * @return the size
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns whether or not the table is empty.
     * @return true if the size is 0
     */
    @Override
    public boolean isEmpty() {
        return (n == 0);
    }

    /**
     * Returns an iterable object containing all of the keys.
     * @return 
     */
    @Override
    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if ((bucket[i] != null) && (bucket[i] != AVAILABLE)) {
                keys.add(bucket[i].getKey());
            }
        }
        return keys;
    }

    /**
     * Collision solved with linear probe - returns index of found key or -(a +
     * 1), where a is * the index of the first empty or available slot found.
     * The index value is negative because it is needed to distinguish when the
     * key is in the table (positive) and when is not (negative)
     * @param key
     * @return 
     */
    protected HashEntryIndex findEntry(K key) throws InvalidKeyException {
        int avail = -1;
        checkKey(key);
        int i = hashValue(key);
        final int j = i;
        do {
            Entry<K, V> e = bucket[i];
            if (e == null) {
                if (avail < 0) {
                    avail = i; // key is not in table
                }
                break;
            } else if (key.equals(e.getKey())) // we have found our key
            {
                return new HashEntryIndex(i, OperationType.found); // key found
            } else if (e == AVAILABLE) { // bucket is deactivated
                if (avail < 0) {
                    avail = i; // remember that this slot is available
                }
            }
            i = (i + 1) % capacity; // keep looking
        } while (i != j);
        return new HashEntryIndex(avail, OperationType.notFound); // first empty or available slot
    }

    /**
     * Returns the value associated with a key.
     * @param key
     * @return 
     */
    @Override
    public V get(K key) throws InvalidKeyException {
        HashEntryIndex i = findEntry(key); // helper method for finding a key
        if (i.operation == OperationType.notFound) {
            return null; // there is no value for this key, so return null
        }
        return bucket[i.index].getValue(); // return the found value in this case
    }

    /**
     * Put a key-value pair in the map, replacing previous one if it exists.
     * @param key
     * @param value
     * @return 
     */
    @Override
    public V put(K key, V value) throws InvalidKeyException {
        HashEntryIndex i = findEntry(key); // find the appropriate spot for this entry
        if (i.operation == OperationType.found) // this key has a previous value
        {
            return bucket[i.index].setValue(value); // set new value
        } else if (n >= capacity / 2) {
            rehash(); // rehash to keep the load factor <= 0.5
            i = findEntry(key); // find again the appropriate spot for this entry
        }

        bucket[i.index] = new HashEntry<>(key, value); // convert to proper
        // index
        n++;
        return null; // there was no previous value
    }

    /**
     * Doubles the size of the hash table and rehashes all the entries.
     */
    protected void rehash() {

    }

    /**
     * Removes the key-value pair with a specified key.
     * @param key
     * @return 
     */
    @Override
    public V remove(K key) throws InvalidKeyException {
        HashEntryIndex i = findEntry(key); // find this key first
        if (i.operation == OperationType.notFound) {
            return null; // nothing to remove
        }
        V toReturn = bucket[i.index].getValue();
        bucket[i.index] = (HashEntry<K, V>) AVAILABLE; // mark this slot as reactivated
        n--;
        return toReturn;
    }

    /**
     * Returns an iterable object containing all of the entries.
     * @return 
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        List<Entry<K, V>> entries = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if ((bucket[i] != null) && (bucket[i] != AVAILABLE)) {
                entries.add(bucket[i]);
            }
        }
        return entries;
    }

    /**
     * Returns an iterable object containing all of the values.
     * @return 
     */
    @Override
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            if ((bucket[i] != null) && (bucket[i] != AVAILABLE)) {
                values.add(bucket[i].getValue());
            }
        }
        return values;
    }
}
