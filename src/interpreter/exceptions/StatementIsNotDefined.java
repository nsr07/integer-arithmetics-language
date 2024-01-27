package interpreter.exceptions;

@SuppressWarnings("serial")
public class StatementIsNotDefined extends ProgramException {

	public StatementIsNotDefined(String statementTag) {
		super(statementTag + " statement is not defined");
	}

}
