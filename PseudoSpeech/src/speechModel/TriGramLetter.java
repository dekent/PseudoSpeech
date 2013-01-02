package speechModel;

import java.util.Random;

public class TriGramLetter {
	int[][][] freq;
	int[][] count;
	double[][][] prob;
	
	public TriGramLetter()
	{
		freq = new int[28][28][28];
		count = new int[28][28];
		prob = new double[28][28][28];
	}
	
	public void updateModel(String text)
	{
		for (int i = 0; i < text.length() - 2; i ++)
		{
			freq[getIndex(text.charAt(i))][getIndex(text.charAt(i+1))][getIndex(text.charAt(i+2))] ++;
			count[getIndex(text.charAt(i))][getIndex(text.charAt(i+1))] ++;
		}
		
		for (int i = 0; i < freq.length; i ++)
		{
			for (int j = 0; j < freq[0].length; j ++)
			{
				int c = count[i][j];
				for (int k = 0; k < freq[0][0].length; k ++)
				{
					prob[i][j][k] = ((double)freq[i][j][k]) / c;
				}
			}
		}
	}
	
	public char nextLetter(char c1, char c2)
	{
		Random rand = new Random();
		double target = rand.nextDouble();
		int index1 = getIndex(c1);
		int index2 = getIndex(c2);
		double count = 0;
		for (int i = 0; i < prob[index1][index2].length; i ++)
		{
			count += prob[index1][index2][i];
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
