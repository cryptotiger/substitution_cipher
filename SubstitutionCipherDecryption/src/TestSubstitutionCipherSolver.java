import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.Assert;

import org.junit.Test;


public class TestSubstitutionCipherSolver {

	private static final int NUMBER_OF_TRIALS = 10;
	private static final int NUMBER_OF_ACCEPTABLE_CHARACTERS = 22;
	private static final double ACCEPTABLE_PERCENTAGE = 0.9;
	
	@Test
	public void test_with_file_1() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file1"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 1 - Percentage: " + percentage);
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}
	
	@Test
	public void test_with_file_2() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file2"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 2 - Percentage: " + percentage);
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}
	
	@Test
	public void test_with_file_3() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file3"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 3 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}

	@Test
	public void test_with_file_4() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file4"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 4 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE);
	
	}
	
	@Test
	public void test_with_file_5() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file5"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 5 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}
	
	@Test
	public void test_with_file_6() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file6"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 6 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage > ACCEPTABLE_PERCENTAGE );
	
	}
	
	@Test
	public void test_with_file_7() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file7"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 7 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}
	
	@Test
	public void test_with_file_8() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file8"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 8 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}
	
	@Test
	public void test_with_file_9() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file9"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 9 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}
	
	@Test
	public void test_with_file_10() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file10"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 10 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}
	
	@Test
	public void test_with_file_11() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file11"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 11 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}
	
	@Test
	public void test_with_file_12() {
		
		int randomKey[];
		int keyGuess[] = new int[26];
		Scanner scan;
		String from_file = "";
		String ciphertext = "";
		String plain = "";
		String input_sanitized = "";
		int total_correct = 0;
		
		try {
			scan = new Scanner(new File("text_file12"));
			
			
			while(scan.hasNextLine())
			{
				from_file += scan.nextLine();
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int total_acceptable_solution = 0;
		
		for(int i = 0; i < NUMBER_OF_TRIALS; i++)
		{
			// generate a random key
			randomKey = SubstitutionCipher.generateRandomKeyForEncryption();
			// read from file
			
			// solve
			
			// check solve with decryption
			ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
			
			plain = SubstitutionCipherSolver.getInstance().solve(ciphertext, keyGuess);
		
			System.out.println(plain);
			input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
			System.out.println(input_sanitized);
			
			System.out.println(plain.equals(input_sanitized));
			System.out.println(input_sanitized.length());
			
			total_correct = 0;
			for(int j = 0; j < 26; j++ )
			{
				if( keyGuess[j] == randomKey[j])
				{
					total_correct++;
				}
			}
			System.out.println("Number of key guessed correctly: " + total_correct);
			
			if( total_correct >= NUMBER_OF_ACCEPTABLE_CHARACTERS)
			{
				total_acceptable_solution++;
			}
			
		}
		
		double percentage = (total_acceptable_solution / (double)NUMBER_OF_TRIALS);
		System.out.println("Test Case 12 - Percentage: " + percentage);
		
		// check if %90 solution rate can be reached using random keys
		Assert.assertTrue( percentage >= ACCEPTABLE_PERCENTAGE );
	
	}
}
