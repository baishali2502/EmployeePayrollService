package com.bridgelabz.employeePayrollService;

public class Employee 
{
	int id;
	String name;
	double salary;
	
	Employee(int id,String name,double salary)
	{
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	Employee()
	{
		
	}
	public String toString()
	{
		String ans="";
		ans = "id="+id+" ,name="+name+" ,salary="+salary;
		return ans;
	}

}
