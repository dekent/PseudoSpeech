package SpeechGenerator;

import java.util.ArrayList;

import Input.InputReader;
import SpeechModel.BiGramLetter;

public class BiGramGenerator {
	BiGramLetter model;
	InputReader reader;
	
	public BiGramGenerator(ArrayList<String> inputFiles)
	{
		model = new BiGramLetter();
		for (int i = 0; i < inputFiles.size(); i ++)
		{
			reader = new InputReader(inputFiles.get(i));
			reader.formatInput();
			reader.removeExcessSpace();
			model.updateModel(reader.getInput());
		}
	}
	
	public String generateSpeech(int minLength)
	{
		String str = "";
		char currentChar = ' ';
		str += model.nextLetter(currentChar);
		currentChar = str.charAt(str.length() - 1);
		
		while (str.length() < minLength || currentChar != ' ')
		{
			str += model.nextLetter(currentChar);
			currentChar = str.charAt(str.length() - 1);
		}
		
		return str;
	}
	
	public static void main(String[] args)
	{
		ArrayList<String> files = new ArrayList<String>();
		files.add("text/SkypeConv.txt");
		BiGramGenerator bg = new BiGramGenerator(files);
		
		System.out.println(bg.generateSpeech(100));
	}
}
