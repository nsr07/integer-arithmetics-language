package interpreter.statements;

import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import interpreter.exceptions.InvalidStatement;
import interpreter.exceptions.UndefinedVariable;

public class PrintStatement extends Statement {

	// Print statement attributes. (language specific)
	public final static String variableTag = "n";

	// Variable name (user defined)
	private String variableName;

	// variables map
	Map<String, Integer> variablesMapRef;

	// output
	Integer output;

	// check flag
	boolean isOutputRef = true;

	public PrintStatement(Map<String, Integer> variablesMap, Node node) throws InvalidStatement, UndefinedVariable {
		// get node attributes
		NamedNodeMap attributes = node.getAttributes();

		// check variable are defined
		Node variableAttribute = attributes.getNamedItem(variableTag);

		// check all attributes are exist if not throw invalid statement
		if (variableAttribute == null) {
			throw new InvalidStatement("Print statement must contains \"" + variableTag + "\" attribute.");
		}

		// if any are not in the variables map then throw undefined exception
		variableName = variableAttribute.getNodeValue();
		if (!variablesMap.containsKey(variableName)) {
			try {
				output = Integer.parseInt(variableName);
				isOutputRef = false;
			} catch (NumberFormatException e) {
				throw new UndefinedVariable(variableName);
			}
		}

		variablesMapRef = variablesMap;

	}

	@Override
	public void execute() {
		if (isOutputRef) {
			output = variablesMapRef.get(variableName);
		}
		System.out.println(output);
	}

}
