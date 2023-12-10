package com.bridgelabz.employeePayrollService;
import java.io.File;
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
		    if(Files.exists(path)) {
			    //System.out.println("Directory already exists");
		    }else
		    {			 
			    Path dirpath = Files.createDirectories(path);
				//System.out.println("Directory created at "+dirpath.toString());
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
			if(checkFileExist(path)) {
			    System.out.println("File already exists at : "+path.toAbsolutePath());
			}else
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
	 //< ----------------------------------- UC-3 ------------------------------------>
	 
		/*
		 * @desc: This method creates a Watch Service to watch particular directory
		 * along with all Files and Sub Directories
		 * 
		 * @params:Directory path,Filepath
		 * 
		 * @returns:void
		 */
	  void startWatchService(Path directoryToWatch, Path countFile) throws IOException {
	        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
	            // Register the directory and its subdirectories for ENTRY_MODIFY events
	            Files.walkFileTree(directoryToWatch, new SimpleFileVisitor<Path>() {
	                @Override
	                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
	                    dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
	                    return FileVisitResult.CONTINUE;
	                }
	            });

	            System.out.println("Watch service is running...");

	            // Infinite loop to wait for events
	            while (true) {
	                WatchKey key;
	                try {
	                    key = watchService.take();
	                } catch (InterruptedException e) {
	                    System.err.println("Error waiting for watch service events: " + e.getMessage());
	                    return;
	                }

	                for (WatchEvent<?> event : key.pollEvents()) {
	                    Path modifiedPath = (Path) event.context();
	                    System.out.println("File modified: " + modifiedPath);

	                    // Count the number of entries in the file
	                    int count = countEntries(countFile);
	                    System.out.println("Number of entries in the file: " + count);
	                }

	                key.reset();
	            }
	        }
	    }

	    int countEntries(Path countFile) throws IOException 
	    {
	        if (!Files.exists(countFile)) {
	            Files.createFile(countFile);
	        }

	        // Read the count from the file
	        String countString = Files.readString(countFile);

	        // Parse the count as an integer
	        int count = 0;
	        try {
	            count = Integer.parseInt(countString.trim());
	        } catch (NumberFormatException e) {
	            System.err.println("Error parsing count from the file: " + e.getMessage());
	        }

	        // Increment the count
	        count++;

	        // Write the updated count back to the file
	        Files.writeString(countFile, String.valueOf(count));

	        return count;
	    }
	  //< ----------------------------------- UC-4 ------------------------------------> 
	    
		/*
		 * @desc:This method adds the employee details to a file
		 * 
		 * @params:none
		 * 
		 * @returns:void
		 */
	  public void writeToFile()
	  {
		  createDirectory();
		  createFile();
		  Path path = Paths.get(filePath);
		  List<String> emplist = new ArrayList<>();
		  for(Employee emp:employeeList)
		  {
			  emplist.add(emp.toString());
		  }
		  
		  try {
		      Files.write(path,emplist);
		      System.out.println("Content added to the file successfully.");
		  }catch(IOException e)
		  {
			  System.err.println("Error writing to the file: " + e.getMessage());
		  }
	  }
	  //< ----------------------------------- UC-5 ------------------------------------> 
	  
	  /*
		 * @desc:This method reads the employee details from the file
		 * 
		 * @params:none
		 * 
		 * @returns:void
		 */
	  public void readFromFile()
	  {
		  Path path = Paths.get(filePath);
		  
		  try {
			  Files.lines(path).forEach(System.out::println);
		     
		  }catch(IOException e)
		  {
			  System.err.println("Error reading from the file: " + e.getMessage());
		  }
	  }
//< ----------------------------------- UC-6 ------------------------------------> 
	  
	  /*
		 * @desc:This method returns the no. of entries in a file
		 * 
		 * @params:none
		 * 
		 * @returns:int
		 */
	  public int countEntries() {
	        try {
	            // Read all lines from the file
	            List<String> lines = Files.readAllLines(Paths.get(filePath));

	            // Count the number of lines (entries)
	            return lines.size();
	        } catch (IOException e) {
	            System.err.println("Error reading the file: " + e.getMessage());
	            return -1; // Return -1 to indicate an error
	        }
	    }
	  
	    
}
