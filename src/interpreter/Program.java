package interpreter;

import java.util.*;
import org.w3c.dom.*;

import interpreter.exceptions.InvalidStatement;
import interpreter.exceptions.StatementIsNotDefined;
import interpreter.exceptions.UndefinedVariable;
import interpreter.exceptions.VariableIsAlreadyDefined;
import interpreter.statements.*;

public class Program implements Iterator<Statement> {

	// Program state:
	// Variables: Key -> Variable Name, Value -> the variable's value
	Map<String, Integer> variables = new HashMap<>();

	// Statements (var, add, and print)
	Queue<Statement> statements = new LinkedList<>();

	public Program(Document doc)
			throws StatementIsNotDefined, InvalidStatement, VariableIsAlreadyDefined, UndefinedVariable {

		// check the main node is program
		String rootName = doc.getDocumentElement().getNodeName();
		if (rootName.equals("program")) {

			// parse each node to a statement
			NodeList nodes = doc.getDocumentElement().getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				
				Node node = nodes.item(i);
				// Escape whitespace nodes
				if(node.getNodeType() == Node.TEXT_NODE) {
					continue;
				}
				
				// get node (it should be var, add, or print)
				String name = node.getNodeName();
				switch (name) {
				case "var":
					statements.add(new VarStatement(variables, node));
					break;
				case "add":
					statements.add(new AddStatement(variables, node));
					break;
				case "print":
					statements.add(new PrintStatement(variables, node));
					break;
				default:
					throw new InvalidStatement(name + " is not defined statement!");

				}
			}

		} else {
			throw new StatementIsNotDefined(rootName);
		}

	}

	@Override
	public boolean hasNext() {
		return !statements.isEmpty();
	}

	@Override
	public Statement next() {
		return statements.remove();
	}

}
