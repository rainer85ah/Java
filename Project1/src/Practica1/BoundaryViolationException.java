package Practica1;

/**
 * Signals that the boundaries of a data structure have been illegally traversed
 * 
 * @author A. Duarte and J. Vélez
 */

public class BoundaryViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BoundaryViolationException(String message) {
		super(message);
	}
}
