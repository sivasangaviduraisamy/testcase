
import java.util.HashMap;

public class Calculator {
	static	HashMap<Integer,String> emps=new HashMap<Integer,String>();
	public static int add(int a,int b)
	{
		return a+b;
	}
	public static int div(int a,int b)
	{
		return a/b;
	}
	public static int addEmployee(int empId ,String empName)
	{
		emps.put(empId,empName);
	return	emps.size();//1
	}
	public static String  updateEmployee(int empId ,String empName)
	{
		return emps.put(empId,empName);
	//return	emps.size();//1
	}
	public static HashMap<Integer,String> fetchAllEmployees()
	{
		return emps;
	//return	emps.size();//1
	}
	public static String deleteEmployee(int empId)
	{
		return emps.remove(111);
		
	}
	public static String fetchEmp(int empId)
	{
		return emps.get(empId);
	}
	
}