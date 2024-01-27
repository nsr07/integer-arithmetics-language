package interpreter.exceptions;

@SuppressWarnings("serial")
public class VariableIsAlreadyDefined extends ProgramException {

	public VariableIsAlreadyDefined(String variableName) {
		super("The variable \"" + variableName + "\" is already defined. "
				+ "You can not define a varialbe multiple times.");
	}

}
