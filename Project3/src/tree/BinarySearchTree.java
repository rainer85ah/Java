package tree;
/**
 *
 * @author jvelez
 */
public interface BinarySearchTree<E> 
{
    /**
     * Returns an entry containing the given key. Returns null if no such entry
     * exists.
     */
    Position<E> find(E value);

    /**
     * Returns an iterable collection of all the entries containing the given
     * key.
     */
    Iterable<? extends Position<E>> findAll(E value);

    /**
     * Inserts an entry into the tree and returns the newly created entry.
     */
    Position<E> insert(E value);

    /**
     * Returns whether the tree is empty.
     */
    boolean isEmpty();

    Iterable<? extends Position<E>> positions();

    /**
     * Removes and returns a given entry.
     */
    E remove(Position<E> pos) throws InvalidPositionException;

    /**
     * Returns the number of entries in the tree.
     */
    int size();

    /**
     * Returns an iterator containing all entries in the tree.
     */
    Iterable<E> values();    
}