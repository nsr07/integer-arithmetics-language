package interpreter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InterpreterTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	@BeforeEach
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@Test
	void testProgramOne() {
		Interpreter.main(new String[] { "src/test/resources/test_program_1.xml" });
		assertEquals("11\n", outContent.toString());
	}
	
	@Test
	void testProgramTwo() {
		Interpreter.main(new String[] { "src/test/resources/test_program_2.xml" });
		assertEquals("14\n", outContent.toString());
	}
	
	@Test
	void testProgramThree() {
		Interpreter.main(new String[] { "src/test/resources/test_program_3.xml" });
		assertEquals("14\n100\n50\n", outContent.toString());
	}
	
	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

}

