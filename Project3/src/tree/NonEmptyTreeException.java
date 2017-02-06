package tree;

/**
 * Runtime exception thrown when one tries to create the root of a
 * tree that is not empty.
 */

public class NonEmptyTreeException extends RuntimeException {  
  /**
	 * 
	 */
	private static final long serialVersionUID = -5768620021566679504L;

public NonEmptyTreeException(String err) {
    super(err);
  }
}
