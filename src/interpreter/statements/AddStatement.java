package interpreter.statements;

import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import interpreter.exceptions.InvalidStatement;
import interpreter.exceptions.UndefinedVariable;

public class AddStatement extends Statement {

	// Add statement attributes. (language specific)
	public final static String firstNumberTag = "n1";
	public final static String secondNumberTag = "n2";
	public final static String resultVariableTag = "to";

	// Variables name (user defined)
	private String firstNumberName;
	private String secondNumberName;
	private String resultVariableName;

	// variables map
	Map<String, Integer> variablesMapRef;

	// operands
	Integer firstNumber;
	Integer secondNumber;

	// check flags
	boolean isFirstNumberRef = true;
	boolean isSecondNumberRef = true;

	public AddStatement(Map<String, Integer> variablesMap, Node node) throws InvalidStatement, UndefinedVariable {
		// get node attributes
		NamedNodeMap attributes = node.getAttributes();

		// check all variable are defined
		Node firstVariableAttribute = attributes.getNamedItem(firstNumberTag);
		Node secondVariableAttribute = attributes.getNamedItem(secondNumberTag);
		Node resulatAttribute = attributes.getNamedItem(resultVariableTag);

		// check all attributes are exist if not throw invalid statement
		if (firstVariableAttribute == null) {
			throw new InvalidStatement("Add statement must contains \"" + firstNumberTag + "\" attribute.");
		}
		if (secondVariableAttribute == null) {
			throw new InvalidStatement("Add statement must contains \"" + secondNumberTag + "\" attribute.");
		}
		if (resulatAttribute == null) {
			throw new InvalidStatement("Add statement must contains \"" + resultVariableTag + "\" attribute.");
		}

		// if any are not in the variables map then throw undefined exception
		firstNumberName = firstVariableAttribute.getNodeValue();
		if (!variablesMap.containsKey(firstNumberName)) {
			try {
				firstNumber = Integer.parseInt(firstNumberName);
				isFirstNumberRef = false;
			} catch (NumberFormatException e) {
				throw new UndefinedVariable(firstNumberName);
			}
		}

		secondNumberName = secondVariableAttribute.getNodeValue();
		if (!variablesMap.containsKey(secondNumberName)) {
			try {
				secondNumber = Integer.parseInt(secondNumberName);
				isSecondNumberRef = false;
			} catch (NumberFormatException e) {
				throw new UndefinedVariable(secondNumberName);
			}
		}

		resultVariableName = resulatAttribute.getNodeValue();
		if (!variablesMap.containsKey(resultVariableName)) {
			throw new UndefinedVariable(resultVariableName);
		}
		variablesMap.put(resultVariableName, null);

		variablesMapRef = variablesMap;

	}

	@Override
	public void execute() {
		// get operands' values if reference variable
		if (isFirstNumberRef) {
			firstNumber = variablesMapRef.get(firstNumberName);
		}
		if (isSecondNumberRef) {
			secondNumber = variablesMapRef.get(secondNumberName);
		}

		// addition
		variablesMapRef.put(resultVariableName, firstNumber + secondNumber);
	}

}
