package cipher;

import java.util.Scanner;
import cipher.VigenereCipher;;

public class main {
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int function;
		String inputFile;
		String keyword;
		System.out.println("What is the input text file?");
		inputFile = scanner.next();
		System.out.println("What is the keyword?");
		keyword = scanner.next();
		System.out.println("Do you want to 1. Encrypt or 2. Decrypt?");
		function = scanner.nextInt();
		scanner.close();
		
		VigenereCipher vigenereCipher = new VigenereCipher(inputFile, keyword);
		
		vigenereCipher.doCipher(function);
	}
	

}
