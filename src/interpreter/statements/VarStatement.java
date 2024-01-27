package interpreter.statements;

import java.util.Map;

import org.w3c.dom.*;

import interpreter.exceptions.InvalidStatement;
import interpreter.exceptions.VariableIsAlreadyDefined;

public class VarStatement extends Statement {
	
	// Var statements attributes. (language specific)
	public final static String variableNameTag = "name";
	public final static String variableValueTag = "value";
	
	// Variables name (user defined)
	private String variableName;
	private Integer variableValue = 0;
	
	// variables map
	Map<String, Integer> variablesMapRef;
	
	public VarStatement(Map<String, Integer> variablesMap, Node node) throws InvalidStatement, VariableIsAlreadyDefined {
		
		// check attributes (name)
		NamedNodeMap attributes = node.getAttributes();
		Node name = attributes.getNamedItem(variableNameTag);
		
		if(name == null) {
			throw new InvalidStatement("var node must contain " + variableNameTag + " tag.");
		}
		
		// optional attribute (might be null)
		Node value = attributes.getNamedItem(variableValueTag);
		
		// check if variable already defined
		variableName = name.getNodeValue(); 
		if(variablesMap.containsKey(variableName)) {
			throw new VariableIsAlreadyDefined(variableName);
		}
		
		variablesMapRef = variablesMap;
		if(value != null)
		variableValue = Integer.valueOf(value.getNodeValue());
		
		// declare variable
		variablesMapRef.put(variableName, null);

		
	}
	
	@Override
	public void execute() {
		// value assignment
		variablesMapRef.put(variableName, variableValue);

	}

}
