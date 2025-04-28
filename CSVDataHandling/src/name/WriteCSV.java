package name;
import java.io.*;
public class WriteCSV {
public static void main(String[] args)
{
	String filePath="C:\\Users\\madhumitha\\OneDrive\\Documents\\students.csv";
	String[] employees= {"ID,Name,Department,Salary",
            "101,John Doe,Engineering,75000",
            "102,Jane Smith,Marketing,68000",
            "103,Robert Brown,Finance,72000",
            "104,Lisa White,Human Resources,65000",
            "105,Michael Green,Engineering,80000"};
try(FileWriter writer=new FileWriter(filePath))
{
	for(String emp:employees)
	{
		writer.write(emp+ "\n");
	}
	System.out.println("CSV file written successfully"+ filePath);
}
catch(IOException e)
{
	System.out.println("An error occured writing the file:");
	e.printStackTrace();
}
}
}
