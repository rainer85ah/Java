package Practica1;

/**
 * An interface for a tree where nodes can have an arbitrary number of children.
 * 
 * @author A. Duarte, J. Vélez
 */
public interface Tree<E> extends Iterable <Position<E>>
{
	/** Returns the number of nodes in the tree. */
	public int size();

	/** Returns whether the tree is empty. */
	public boolean isEmpty();

	/** Returns the root of the tree. */
	public Position<E> root() throws EmptyTreeException;

	/** Returns the parent of a given node. */
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException;

	/** Returns an iterable collection of the children of a given node. */
	public Iterable<? extends Position<E>> children(Position<E> v) throws InvalidPositionException;

	/** Returns whether a given node is internal. */
	public boolean isInternal(Position<E> v) throws InvalidPositionException;

	/** Returns whether a given node is external. */
	public boolean isLeaf(Position<E> v) throws InvalidPositionException;

	/** Returns whether a given node is the root of the tree. */
	public boolean isRoot(Position<E> v) throws InvalidPositionException;
}