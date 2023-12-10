package com.bridgelabz.employeePayrollService;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class EmployeePayrollService 
{
	static String directoryPath = "EmployeeData";
    static String filePath = directoryPath+"/"+"EmployeeDetails.txt";
    static String fileExtension = "txt";
	// stores the list of employees
	static ArrayList<Employee> employeeList=new ArrayList<>(); 
//< ----------------------------------- UC-1 ------------------------------------>
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
	//< ----------------------------------- UC-2 ------------------------------------>	
	
	/*
	 * @desc:This method creates directory
	 * 
	 * @returns:void
	 * 
	 * @params:none
	 */
	public void createDirectory()
	{
		try 
		{
		    Path path = Paths.get(directoryPath);
		    if(Files.exists(path))
			    System.out.println("Directory already exists");
		    else
		    {			 
			    Path dirpath = Files.createDirectories(path);
				System.out.println("Directory created at "+dirpath.toString());
			} 
		}
		catch (Exception e) 
		{
				e.printStackTrace();
		}
			
	}
	/*
	 * @desc:This method creates empty-file
	 * 
	 * @returns:void
	 * 
	 * @params:none
	 */
	public void createFile()
	{
		try
		{
			Path path = Paths.get(filePath);
			if(checkFileExist(path))
			    System.out.println("File already exists");
		    else
		    {			 
			    Path filepath = Files.createFile(path);
				System.out.println("File created at "+filepath.toString());
			} 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	/*
	 * @desc:This method checks if file exists at given path or not
	 * 
	 * @returns:boolean
	 * 
	 * @params:filepath
	 */
	boolean checkFileExist(Path filePath)
	{
		return Files.exists(filePath);
	}
	/*
	 * @desc:This method deletes file at given path if it exists
	 * 
	 * @returns:void
	 * 
	 * @params:filepath
	 */	
	void deleteFile()
	{
	  try 
	  {
		    Path deleteFilePath = Paths.get(filePath);
			Files.delete(deleteFilePath);
            System.out.println("File deleted successfully.");
	  }
	  catch (IOException e) 
	  {
            System.err.println("Error deleting the file: " + e.getMessage());
      }
	}
	/*
	 * @desc:This method lists Files, Directories as well as Files with Extension
	 * 
	 * @returns:void
	 * 
	 * @params:none
	 */
	 void listFilesAndDirectories() 
	 {
	        Path path = Paths.get(directoryPath);
	        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
	            System.out.println("Files and directories:");

	            for (Path entry : stream) {
	                System.out.println(entry.getFileName());

	                // Check if the entry is a file and has the specified extension
	                if (Files.isRegularFile(entry) && entry.toString().endsWith("." + fileExtension)) {
	                    System.out.println("  (File with extension: " + entry.getFileName() + ")");
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error listing files and directories: " + e.getMessage());
	        }
      }
	 
	    
	    
}
