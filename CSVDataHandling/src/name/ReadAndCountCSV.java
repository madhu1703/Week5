package name;

import java.io.*;

public class ReadAndCountCSV {
public static void main(String[] args)
{
	String filePath="C:\\Users\\madhumitha\\OneDrive\\Documents\\students.csv";
	String line;
	int recordCount=0;
	 try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
         br.readLine();
         while((line=br.readLine())!=null)
         {
        	 if(!line.trim().isEmpty())
        	 {
        		 recordCount++;
        	 }
         }
         System.out.println("Total number of records:"+ recordCount);
}
	 catch(IOException e)
	 {
		 System.out.println("Error in file");
		 e.printStackTrace();
	 }
}
}
