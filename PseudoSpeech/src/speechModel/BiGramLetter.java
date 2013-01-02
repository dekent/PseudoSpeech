package speechModel;

import java.util.Random;

public class BiGramLetter {
	int[][] freq;
	int[] count;
	double[][] prob;
	
	public BiGramLetter()
	{
		freq = new int[28][28];
		count = new int[28];
		prob = new double[28][28];
	}
	
	public void updateModel(String text)
	{
		for (int i = 0; i < text.length() - 1; i ++)
		{
			freq[getIndex(text.charAt(i))][getIndex(text.charAt(i+1))] ++;
			count[getIndex(text.charAt(i))] ++;
		}
		
		for (int i = 0; i < freq.length; i ++)
		{
			int c = count[i];
			for (int j = 0; j < freq[0].length; j ++)
			{
				prob[i][j] = ((double)freq[i][j]) / c;
			}
		}
	}
	
	public char nextLetter(char c)
	{
		Random rand = new Random();
		double target = rand.nextDouble();
		int index = getIndex(c);
		double count = 0;
		for (int i = 0; i < prob[index].length; i ++)
		{
			count += prob[index][i];
			if (count > target)
				return getChar(i);
		}
		return getChar(prob.length - 1);
	}
	
	private int getIndex(char c)
	{
		switch (c)
		{
		case '\'':
			return 26;
		case ' ':
			return 27;
		default:
			return c - 97;
		}
	}
	
	private char getChar(int i)
	{
		switch (i)
		{
		case 26:
			return '\'';
		case 27:
			return ' ';
		default:
			return (char)(i + 97);
		}
	}
}
