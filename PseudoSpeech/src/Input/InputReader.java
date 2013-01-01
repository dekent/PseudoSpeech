package Input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputReader {
	String input;
	
	public InputReader(String filename)
	{
		StringBuilder text = new StringBuilder();
		text.append(" ");
		Scanner scanner;
		try {
			scanner = new Scanner(new FileInputStream(filename));
			try {
				while (scanner.hasNextLine()) {
					text.append(scanner.nextLine() + " ");
				}
			} finally {
				scanner.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found...");
			e.printStackTrace();
		}
		
		input = text.toString();
	}
	
	public void formatInput()
	{
		input = input.toLowerCase();
		char c;
		StringBuilder temp = new StringBuilder(input);
		for (int i = input.length() - 1; i >= 0; i --)
		{
			c = input.charAt(i);
			if (c < 97 || c > 122)
			{
				if (c != ' ' && c != '\'')
					temp.setCharAt(i, ' ');
			}
		}
		input = temp.toString();
	}
	
	public void removeExcessSpace()
	{
		input = input.replaceAll(" +", " ");
	}
	
	public String getInput()
	{
		return input;
	}
}
