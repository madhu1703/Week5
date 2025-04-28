package name;
import java.io.*;
public class ReadAndFilter {
	public static void main(String[] args)
	{
		String filePath="C:\\Users\\madhumitha\\OneDrive\\Documents\\studentMarks.csv";
		String line;
		try(BufferedReader br=new BufferedReader(new FileReader(filePath)))
		{
			br.readLine();
			while((line=br.readLine())!=null)
			{
				if(!line.trim().isEmpty())
				{
					String[] data=line.split(",");
					int marks=Integer.parseInt(data[2].trim());
					if(marks>80)
					{
						System.out.println(line);
					}
				}
			}
		}
		catch(IOException e)
		{
			System.out.println("Error reading the file");
			e.printStackTrace();
		}
	}
}
