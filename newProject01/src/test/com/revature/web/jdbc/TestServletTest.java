package test.com.revature.web.jdbc;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * Quick test with misspelled package Returned error:
 * The declared package "com.revature.web.jdbc" does not match the expected package "test.com.revature.web.jdbc"
 * 
 */

public class TestServletTest {

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		fail("Not yet implemented"); //sends an error message that you haven't entered the code to be tested yet!
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionIsThrown() {
		//MyClass tester = new MyClass();
	//	tester.multiply(1000, 5);
	}

	@Test
	public void testMultiply() {
		//MyClass tester = new MyClass();
		//assertEquals("10 x 5 must be 50", 50, tester.multiply(10, 5));
	}

}
