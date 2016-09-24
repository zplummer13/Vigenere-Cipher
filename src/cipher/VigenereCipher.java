package cipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class VigenereCipher {
	
	private String inputFile;
	private String keyword;
	private int length;
	public char[] alpha = {
		'a','b','c','d','e','f','g','h','i','j','k','l','m',
		'n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public VigenereCipher(String inputFile, String keyword)
	{
		this.inputFile = inputFile;
		this.keyword = keyword;
		length = keyword.length();
	}
	
	public void doCipher(int function)
	{
		if (function == 1)
		{
			encrypt();
		}
		else if (function == 2)
		{
			decrypt();
		}
		else
		{
			System.out.println("Error: That function is not defined.");
		}
	}
	
	private void encrypt()
	{
		System.out.println("Attempting to encrypt using keyword: " + keyword);
		int c = -1;
		char outputC;
		try
		{
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			FileWriter writer = new FileWriter((inputFile + "_encrypted"), false);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			
			int index = 0;
			char[] keyArr = keyword.toLowerCase().toCharArray();
			
			while ((c = bufferedReader.read()) != -1)
			{
				char character = (char)c;
				if (Character.isLetter(character))
				{
					//encrypt letters and write them to the output file
					char key = keyArr[index];
					if(Character.isLowerCase(character))
					{
						outputC = alpha[(alphaIndex(key) + alphaIndex(character)) % alpha.length];
					}
					else
					{
						character = Character.toLowerCase(character);					
						outputC = alpha[(alphaIndex(key) + alphaIndex(character)) % alpha.length];
						outputC = Character.toUpperCase(outputC);
					}
					bufferedWriter.write(outputC);
					index = (index + 1) % keyArr.length;
				}
				else
				{
					//write non letter characters to the output file
					bufferedWriter.write(character);
					System.out.println("....................................");
				}
			}
			bufferedWriter.close();
			bufferedReader.close();
			System.out.println();
			System.out.println("Done encrypting!");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: " + inputFile + " not found.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void decrypt()
	{
		System.out.println("Attempting to decrypt using keyword: " + keyword);
		int c = -1;
		char outputC;
		try
		{
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			FileWriter writer = new FileWriter((inputFile + "_decrypted"), false);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			
			int index = 0;
			char[] keyArr = keyword.toLowerCase().toCharArray();
			
			while ((c = bufferedReader.read()) != -1)
			{
				char character = (char)c;
				if (Character.isLetter(character))
				{
					//encrypt letters and write them to the output file
					char key = keyArr[index];
					if(Character.isLowerCase(character))
					{
						outputC = alpha[(alphaIndex(character) - alphaIndex(key) + alpha.length) % alpha.length];
					}
					else
					{
						character = Character.toLowerCase(character);					
						outputC = alpha[(alphaIndex(character) - alphaIndex(key) + alpha.length) % alpha.length];
						outputC = Character.toUpperCase(outputC);
					}
					bufferedWriter.write(outputC);
					index = (index + 1) % keyArr.length;
				}
				else
				{
					//write non letter characters to the output file
					bufferedWriter.write(character);
					System.out.println("....................................");
				}
			}
			bufferedWriter.close();
			bufferedReader.close();
			System.out.println();
			System.out.println("Done Decrypting!");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: " + inputFile + " not found.");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private int alphaIndex(char c)
	{
		for (int i = 0; i < alpha.length; i++)
		{
			if(c == alpha[i])
			{
				return i;
			}
		}
		return -1;
	}
}
