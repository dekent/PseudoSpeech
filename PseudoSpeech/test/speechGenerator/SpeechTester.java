package speechGenerator;

import java.util.ArrayList;

import speechGenerator.BiGramGenerator;


public class SpeechTester {
	public void bigramTest()
	{
		ArrayList<String> files = new ArrayList<String>();
		files.add("text/SkypeConv.txt");
		BiGramGenerator bg = new BiGramGenerator(files);
		
		System.out.println(bg.generateSpeech(150));
	}
	
	public void trigramTest()
	{
		ArrayList<String> files = new ArrayList<String>();
		files.add("text/SkypeConv.txt");
		TriGramGenerator tg = new TriGramGenerator(files);
		
		System.out.println(tg.generateSpeech(150));
	}
	
	public static void main(String[] args)
	{
		SpeechTester st = new SpeechTester();
		//st.bigramTest();
		st.trigramTest();
	}
}
