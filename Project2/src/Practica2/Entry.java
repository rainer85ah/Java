package Practica2;

/**
* @author  R. Cabido, A. Duarte, and J. Velez
* 
**/

/** Interface for a key-value pair entry
 * @param <K>
 * @param <V> **/
public interface Entry<K,V> 
{
    /** Returns the key stored in this entry.
     * @return  */
    public K getKey();
    
    /** Returns the value stored in this entry.
     * @return  */
    public V getValue();
}