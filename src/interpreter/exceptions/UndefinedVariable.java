package interpreter.exceptions;

@SuppressWarnings("serial")
public class UndefinedVariable extends ProgramException {

	public UndefinedVariable(String variableName) {
		super(variableName + " is not defined before use.");
	}

}
