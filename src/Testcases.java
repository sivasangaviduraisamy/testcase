import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class Testcases {
	@BeforeAll
	public static void m1() {
		System.out.println("Executed BeforeALL and once");
	}
	@AfterAll
	public static void m2() {
		System.out.println("Execute AfterALL and once");
	}
	@AfterEach
	public  void m3() {
		System.out.println("Execute AfterEach testcase ");
	}
	@BeforeEach
	public  void m4() {
		System.out.println("Execute BeforeEach testcase ");
	}
	@Test
	public void testAdd()
	{
		System.out.println("test1");
		int addition=Calculator.add(123, 124);
	
		assertEquals(257,addition);
	}
	@Test
	 public void testdiv()
		{
			System.out.println("test3");
			int result=Calculator.div(12,2);
			
			assertEquals(3,result);
		}
	@AfterAll
	public static void m6()
	{
		System.out.println("All Testcases Failed");
	}
	/*
	@Test 
	public void testAddEmployee()
	{
		int result=Calculator.addEmployee(111,"sandeep");
		Calculator.addEmployee(112,"sangavi");
    
		assertEquals(2,result);
				
	}
	@Test
	public void testUpdateEmployee()
	{
		
	String oldName=Calculator.updateEmployee(111,"pratheek");
		assertEquals("sandeep",oldName);
		
	}
	@Test
	public void testDeleteEmployee()
	{
		
	String deleteName=Calculator.deleteEmployee(111);
		assertEquals("pratheek",deleteName);
		
	}
	@Test
	public void testFetchEmployee()
	{
		
	String fetched=Calculator.fetchEmp(112);
		assertEquals("sangavi",fetched);
		
	}
	/*@Test
	public void testFetchAllEmployee()
	{
		
	HashMap<Integer, String> fetched=Calculator.fetchAllEmployees();
		assertEquals("sangavi",fetched);
		
	}*/
	//@Test
	


}