package tree;

/**
 * Thrown when a position is determined to be invalid.
 * 
 * @author A. Duarte, J. Vélez
 */
// A run-time exception for invalid positions
public class InvalidPositionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPositionException(String err) {
		super(err);
	}
}
