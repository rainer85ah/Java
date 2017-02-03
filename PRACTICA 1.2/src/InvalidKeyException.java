package practica2;

/**
 * Thrown when a key is determined to be invalid.
 * @author Roberto Tamassia
 */
public class InvalidKeyException  extends RuntimeException {
  public InvalidKeyException (String message) {
    super (message);
  }
}
