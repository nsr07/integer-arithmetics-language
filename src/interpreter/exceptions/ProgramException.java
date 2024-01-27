package interpreter.exceptions;

/**
 * Integer Arithmetics Language exceptions must inherit from this custom exception.
 */
@SuppressWarnings("serial")
public class ProgramException extends Exception {
	public final static String EXCEPTION_TAG = "[Integer Arithmetics Language]: ";
	public ProgramException(String message) {
		super(EXCEPTION_TAG + message);
	}
}
