package com.bridgelabz.employeePayrollService;
import java.util.*;

public class Main {
    
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		EmployeePayrollService employeePayrollServiceObj = new EmployeePayrollService();
		
		employeePayrollServiceObj.readEmployeeData(scanner);
		employeePayrollServiceObj.readEmployeeData(scanner);
		employeePayrollServiceObj.readEmployeeData(scanner);
		//employeePayrollServiceObj.writeEmployeeData();
		employeePayrollServiceObj.writeToFile();
		employeePayrollServiceObj.readFromFile();
		System.out.print(employeePayrollServiceObj.countEntries());
	}
	
	

}
