package Practica2;
/**
 * An interface for a map which binds a key uniquely to a value.
 * 
 * @author  R. Cabido, A. Duarte, and J. Velez
 * @param <K>
 * @param <V>
 */

// A simple Map interface
public interface Map<K,V> 
{
    /** Returns the number of items in the map.
     * @return  */
    public int size();

    /** Returns whether the map is empty.
     * @return  */
    public boolean isEmpty();

    /**
     * If there is an entry with the specified key, replaces the value of this
     * entry with the specified value and returns the old value. Else, adds a
     * new entry with the specified key and value and returns null.
     * @param key
     * @param value
     * @return 
     */
    public V put(K key, V value) throws InvalidKeyException;

    /**
     * Returns the value of the entry containing the given key. Returns null if
     * no such entry exists.
     * @param key
     * @return 
     */
    public V get(K key) throws InvalidKeyException;

    /**
     * If there is an entry with the specified key, removes this entry and
     * returns its value. Else, returns null.
     * @param key
     * @return 
     */
    public V remove(K key) throws InvalidKeyException;

    /** Returns an iterable object containing all the keys in the map.
     * @return  */
    public Iterable<K> keySet();

    /** Returns an iterable object containing all the values in the map.
     * @return  */
    public Iterable<V> values();

    /** Returns an iterable object containing all the entries in the map.
     * @return  */
    public Iterable<Entry<K, V>> entrySet();
}
