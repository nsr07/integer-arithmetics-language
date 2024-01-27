package interpreter.exceptions;

@SuppressWarnings("serial")
public class InvalidStatement extends ProgramException {

	public InvalidStatement(String message) {
		super(message);
	}

}
