package eightqueensGA;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;


public class QueenGaTest {
	//QueenGa r= new QueenGa();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOpSolution(){
		QueenGa r= new QueenGa();

	        assertFalse(r.equals(1));
	}
	@Test
	public void testFintness() {
		//QueenGa r= new QueenGa();
		String x="01234567";
		assertEquals("01234567", QueenGa.evolutionfint((String) x));
	}

	@Test
	
	public void testSelect() {
		QueenGa r= new QueenGa();
		int x=01234567;
		//assertEquals(10, r.fintness((String) x));
	}

	@Test
	public void testRun() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
