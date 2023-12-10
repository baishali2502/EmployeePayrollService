package com.bridgelabz.employeePayrollService;

import java.util.ArrayList;
import java.nio.*;
import java.util.Scanner;

public class EmployeePayrollService 
{
	// stores the list of employees
	static ArrayList<Employee> employeeList=new ArrayList<>(); 
	
	public void writeEmployeeData() {
		System.out.println("\nAll Employee Details:-");
		System.out.println(employeeList);
	}

	public void readEmployeeData(Scanner scanner)
	{
		//creating an object of Employee class
		Employee employee = new Employee();
		
		System.out.print("Enter employee-id = ");
		employee.id = scanner.nextInt();
				
		System.out.print("Enter name = ");
		employee.name = scanner.next();
				
		System.out.print("Enter salary = ");
		employee.salary = scanner.nextDouble();
				
		employeeList.add(employee);// adding employee to the employee-list
		
	}

}
