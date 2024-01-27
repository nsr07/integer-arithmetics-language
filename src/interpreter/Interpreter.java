package interpreter;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import interpreter.exceptions.ProgramException;
import interpreter.statements.Statement;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

/**
 * This is a simple interpreter for an integer arithmetics language.
 * The language supports three types of statements:
 * <ol>
 * <li>var to define a new variable, </li>
 * <li>add to add integers, </li>
 * <li>and print to print to the standard output stream (i.e. System.out in Java). </li>
 * </ol>
 *
 * An example program written in the language appears below:
 * <pre>
 * {@code 
 * <program>
 * 	<var name="a" value="5"/>
 * 	<var name="b" value="6"/>
 * 	<var name="c"/>
 * 	<add n1="a" n2="b" to="c"/>
 * 	<print n="c"/>
 * </program>
 * }
 * </pre>
 */
public class Interpreter implements Iterable<Statement> {
	
	// interpreter steps:
	// START
	// STEP 1: read the XML file
	// STEP 2: check root tag is "program"
	// STEP 3: loop  for each node tag in the root node:
	// STEP 3.1: if <var /> store variable in a hashMap 
	// STEP 3.2: if <add /> do addition and store result
	// STEP 3.2: if <print /> print the specified var
	// END
	
	// first argument must be the program XML file path
	public static void main(String[] args) {
		
		try {
			Interpreter interpreter = new Interpreter(args[0]);
			for(Statement s: interpreter) {
				s.execute();
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	// XML Document
	Document xmlDoc;
	
	public Interpreter(String xmlFilePath) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		xmlDoc = builder.parse(new File(xmlFilePath));
		xmlDoc.normalize();
		xmlDoc.getDocumentElement().normalize();
	}


	@Override
	public Iterator<Statement> iterator() {
		try {
			return new Program(xmlDoc);
		} catch (ProgramException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
