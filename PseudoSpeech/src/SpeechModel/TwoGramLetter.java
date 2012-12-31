package SpeechModel;

public class TwoGramLetter {
	int[][] freq;
	int count;
	
	public TwoGramLetter()
	{
		freq = new int[28][28];
		count = 0;
	}
	
	public void updateModel(String text)
	{
		for (int i = 0; i < text.length() - 1; i ++)
		{
			freq[getIndex(text.charAt(i))][getIndex(text.charAt(i+1))] ++;
			count ++;
		}
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
	
	public static void main(String[] args)
	{
		TwoGramLetter m = new TwoGramLetter();
	}
}
