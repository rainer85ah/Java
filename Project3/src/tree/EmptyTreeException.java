package tree;

/**
 * Runtime exception thrown when one tries to access the root of an empty tree.
 * @author A. Duarte and J. Vélez
 *
 */

public class EmptyTreeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyTreeException(String err) {
		super(err);
	}
}
