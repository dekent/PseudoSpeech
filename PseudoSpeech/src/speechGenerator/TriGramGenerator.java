package speechGenerator;

import input.InputReader;

import java.util.ArrayList;

import speechModel.BiGramLetter;
import speechModel.TriGramLetter;

public class TriGramGenerator {
	BiGramLetter biModel;
	TriGramLetter triModel;
	InputReader reader;
	
	public TriGramGenerator(ArrayList<String> inputFiles)
	{
		biModel = new BiGramLetter();
		triModel = new TriGramLetter();
		for (int i = 0; i < inputFiles.size(); i ++)
		{
			reader = new InputReader(inputFiles.get(i));
			reader.formatInput();
			reader.removeExcessSpace();
			biModel.updateModel(reader.getInput());
			triModel.updateModel(reader.getInput());
		}
	}
	
	public String generateSpeech(int minLength)
	{
		String str = "";
		char currentChar1 = ' ';
		char currentChar2;
		str += biModel.nextLetter(currentChar1);
		currentChar2 = str.charAt(str.length() - 1);
		while (str.length() < minLength || (currentChar1 != ' ' && currentChar2 != ' '))
		{
			str += triModel.nextLetter(currentChar1, currentChar2);
			currentChar1 = str.charAt(str.length() - 2);
			currentChar2 = str.charAt(str.length() - 1);
		}
		
		if (currentChar1 == ' ')
			str = str.substring(0, str.length() - 1);
		
		return str;
	}
}
