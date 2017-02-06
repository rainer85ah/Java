package Practica1;

/**
 * Runtime exception thrown when one tries to access the root of an empty tree.
 * @author A. Duarte and J. Vélez
 *
 */

public class NonEmptyTreeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NonEmptyTreeException(String err) {
		super(err);
	}
}