import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class SubstitutionCipherSolver {

	
	/**
	 * Retrieved from: http://www.cryptograms.org/letter-frequencies.php
	 */
	final static double[] LETTER_FREQ2 = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702,
										  0.02228, 0.02015, 0.06094, 0.06966, 0.00153,
										  0.00772, 0.04025, 0.02406, 0.06749, 0.07507,
										  0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 
										  0.02758, 0.00978, 0.02360, 0.00150, 0.01974,
										  0.00074};
	
	/**
	 * Retrieved from http://keithbriggs.info/documents/english_latin.pdf
	 */
	final static double[][] BIGRAM_FREQ = { {0.005, 0.037, 0.048, 0.054, 0.013, 0.013, 0.024, 0.020, 0.075, 0.000, 0.028, 0.158, 0.056, 0.440, 0.002, 0.018, 0.000, 0.146, 0.128, 0.204, 0.019, 0.050, 0.014, 0.001, 0.056, 0.003},
		                                    {0.019, 0.002, 0.000, 0.000, 0.121, 0.000, 0.000, 0.000, 0.014, 0.001, 0.000, 0.026, 0.000, 0.001, 0.028, 0.000, 0.000, 0.028, 0.005, 0.002, 0.041, 0.000, 0.000, 0.000, 0.024, 0.000},
		                                    {0.060, 0.000, 0.010, 0.000, 0.068, 0.000, 0.000, 0.086, 0.025, 0.000, 0.019, 0.015, 0.000, 0.000, 0.080, 0.000, 0.001, 0.014, 0.000, 0.031, 0.014, 0.000, 0.000, 0.000, 0.003, 0.000}, 
		                                    {0.035, 0.000, 0.000, 0.004, 0.087, 0.001, 0.006, 0.000, 0.051, 0.000, 0.000, 0.005, 0.003, 0.006, 0.039, 0.000, 0.000, 0.019, 0.023, 0.000, 0.011, 0.002, 0.003, 0.000, 0.006, 0.000},
		                                    {0.121, 0.006, 0.048, 0.157, 0.074, 0.026, 0.014, 0.011, 0.043, 0.001, 0.004, 0.084, 0.062, 0.200, 0.021, 0.024, 0.003, 0.305, 0.166, 0.080, 0.002, 0.043, 0.015, 0.015, 0.049, 0.002},
		                                    {0.028, 0.000, 0.000, 0.000, 0.036, 0.022, 0.000, 0.000, 0.033, 0.000, 0.000, 0.011, 0.000, 0.000, 0.097, 0.000, 0.000, 0.031, 0.001, 0.016, 0.011, 0.000, 0.000, 0.000, 0.001, 0.000},
		                                    {0.030, 0.000, 0.000, 0.002, 0.043, 0.000, 0.003, 0.050, 0.020, 0.000, 0.000, 0.008, 0.002, 0.004, 0.048, 0.000, 0.000, 0.024, 0.014, 0.002, 0.006, 0.000, 0.000, 0.000, 0.003, 0.000},
		                                    {0.249, 0.001, 0.000, 0.000, 0.710, 0.001, 0.000, 0.000, 0.184, 0.000, 0.000, 0.001, 0.001, 0.001, 0.106, 0.000, 0.000, 0.017, 0.002, 0.036, 0.016, 0.000, 0.000, 0.000, 0.024, 0.000},
		                                    {0.022, 0.007, 0.061, 0.059, 0.055, 0.035, 0.038, 0.000, 0.000, 0.001, 0.008, 0.077, 0.064, 0.332, 0.052, 0.009, 0.001, 0.051, 0.164, 0.167, 0.001, 0.034, 0.000, 0.003, 0.000, 0.002},
		                                    {0.005, 0.000, 0.000, 0.000, 0.016, 0.000, 0.000, 0.000, 0.002, 0.000, 0.000, 0.000, 0.000, 0.000, 0.010, 0.000, 0.000, 0.000, 0.000, 0.000, 0.011, 0.000, 0.000, 0.000, 0.000, 0.000},
		                                    {0.001, 0.000, 0.000, 0.000, 0.050, 0.000, 0.000, 0.000, 0.027, 0.000, 0.000, 0.001, 0.000, 0.013, 0.002, 0.000, 0.000, 0.000, 0.006, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000},
		                                    {0.063, 0.000, 0.001, 0.056, 0.119, 0.009, 0.000, 0.000, 0.064, 0.000, 0.004, 0.141, 0.002, 0.000, 0.082, 0.002, 0.000, 0.001, 0.020, 0.019, 0.007, 0.007, 0.002, 0.000, 0.047, 0.000},
		                                    {0.080, 0.012, 0.001, 0.000, 0.127, 0.001, 0.000, 0.000, 0.037, 0.000, 0.000, 0.000, 0.011, 0.001, 0.051, 0.015, 0.000, 0.003, 0.011, 0.000, 0.013, 0.000, 0.000, 0.000, 0.025, 0.000},
		                                    {0.034, 0.001, 0.041, 0.354, 0.107, 0.004, 0.145, 0.004, 0.031, 0.001, 0.008, 0.006, 0.000, 0.009, 0.087, 0.000, 0.001, 0.000, 0.056, 0.139, 0.008, 0.002, 0.001, 0.000, 0.016, 0.000},
		                                    {0.010, 0.010, 0.011, 0.045, 0.006, 0.216, 0.008, 0.003, 0.013, 0.001, 0.015, 0.039, 0.081, 0.192, 0.037, 0.025, 0.000, 0.208, 0.039, 0.074, 0.194, 0.024, 0.060, 0.001, 0.004, 0.001},
		                                    {0.032, 0.000, 0.000, 0.000, 0.059, 0.000, 0.000, 0.015, 0.014, 0.000, 0.000, 0.033, 0.000, 0.000, 0.037, 0.013, 0.000, 0.045, 0.005, 0.013, 0.011, 0.000, 0.000, 0.000, 0.001, 0.000},
		                                    {0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.011, 0.000, 0.000, 0.000, 0.000, 0.000},
		                                    {0.073, 0.002, 0.009, 0.066, 0.259, 0.004, 0.009, 0.001, 0.092, 0.000, 0.011, 0.009, 0.015, 0.027, 0.089, 0.004, 0.000, 0.012, 0.049, 0.044, 0.021, 0.011, 0.001, 0.000, 0.028, 0.000},
		                                    {0.078, 0.001, 0.012, 0.001, 0.154, 0.001, 0.001, 0.098, 0.048, 0.000, 0.004, 0.009, 0.006, 0.003, 0.069, 0.030, 0.001, 0.010, 0.047, 0.134, 0.029, 0.000, 0.008, 0.000, 0.004, 0.000},
		                                    {0.052, 0.000, 0.004, 0.000, 0.119, 0.001, 0.000, 0.858, 0.092, 0.000, 0.000, 0.014, 0.001, 0.002, 0.178, 0.000, 0.000, 0.041, 0.037, 0.022, 0.022, 0.000, 0.013, 0.000, 0.023, 0.000},
		                                    {0.010, 0.010, 0.019, 0.014, 0.010, 0.002, 0.022, 0.000, 0.011, 0.000, 0.001, 0.044, 0.011, 0.086, 0.001, 0.033, 0.000, 0.072, 0.073, 0.075, 0.000, 0.000, 0.000, 0.000, 0.000, 0.001},
		                                    {0.018, 0.000, 0.000, 0.000, 0.142, 0.000, 0.000, 0.000, 0.029, 0.000, 0.000, 0.000, 0.000, 0.000, 0.007, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000},
		                                    {0.072, 0.000, 0.000, 0.000, 0.070, 0.000, 0.000, 0.084, 0.084, 0.000, 0.000, 0.003, 0.000, 0.016, 0.039, 0.000, 0.000, 0.005, 0.004, 0.000, 0.000, 0.000, 0.000, 0.000, 0.001, 0.000},
		                                    {0.002, 0.000, 0.002, 0.000, 0.002, 0.000, 0.000, 0.000, 0.002, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.003, 0.000, 0.000, 0.000, 0.005, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000},
		                                    {0.001, 0.002, 0.000, 0.000, 0.035, 0.000, 0.000, 0.000, 0.009, 0.000, 0.000, 0.002, 0.001, 0.001, 0.034, 0.004, 0.000, 0.002, 0.013, 0.001, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000},
		                                    {0.003, 0.000, 0.000, 0.000, 0.005, 0.000, 0.000, 0.000, 0.003, 0.000, 0.000, 0.000, 0.000, 0.000, 0.001, 0.000, 0.000, 0.001, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.001}
										 };
	
	
	
	private final static String TRIAGRAMS_FILE = "AnalysisFiles/english_trigrams.txt";
	private final static String QUADGRAMS_FILE = "AnalysisFiles/english_quadgrams.txt";
	private static long ENGLISH_BIGRAMS_TOTAL;
	private static long ENGLISH_TRIGRAMS_TOTAL;
	private static long ENGLISH_QUADGRAMS_TOTAL;
	private static int[][] ENGLISH_BIGRAMS;
	private static HashMap<String, Integer> ENGLISH_TRIAGRAMS;
	private static HashMap<String, Integer> ENGLISH_QUADGRAMS;
	
	public static SubstitutionCipherSolver instance;
	
	public static SubstitutionCipherSolver getInstance()
	{
		if( instance == null)
		{
			instance = new SubstitutionCipherSolver();
		}
		return instance;
	}
	
	
	private SubstitutionCipherSolver()
	{
		// calculate static values that will be used throughout the program
		// convert bigram frequencies to integer and put it into matrix
		// this value never changes
		ENGLISH_BIGRAMS = convertBigramsToInteger(BIGRAM_FREQ);
				
		// calculate total for integer_bigrams array since it is needed for calculations
		// this value never changes
		ENGLISH_BIGRAMS_TOTAL = total_of_int_array(ENGLISH_BIGRAMS);
				
		// calculate HashMap for trigrams
		ENGLISH_TRIAGRAMS = calculateTrigramsHashMapFromFile(TRIAGRAMS_FILE);
	
		// total number of bigrams in Analysis of English texts
		ENGLISH_TRIGRAMS_TOTAL = calculateTotalOfHashMap(ENGLISH_TRIAGRAMS);
				
		// calculate HashMap for quadgrams
		ENGLISH_QUADGRAMS = calculateQuadgramsHashMapFromFile(QUADGRAMS_FILE);
		
		// total number of quadgrams in Analysis of English texts
		ENGLISH_QUADGRAMS_TOTAL = calculateTotalOfHashMap(ENGLISH_QUADGRAMS);
		
	}
	
	// calculates total number of elements in a matrix, total of integer values in matrix
	public static int total_of_int_array(int array[][])
	{
		int total = 0;
		
		for(int i = 0; i < 26; i++)
		{
			for(int j = 0; j < 26; j++)
			{
				total += array[i][j];
			}
		}
		
		return total;
	}
	
	/**
	 * Converts bigrams to integer since it is easier to work with integers during calculations
	 * to get more accurate results
	 * @param bigrams
	 * @return
	 */
	public static int[][] convertBigramsToInteger( double bigrams[][])
	{
		double value = 0.0;
		int returnValue[][] = new int[26][26];
		
		
		for(int i = 0; i < 26; i++)
		{
			for( int j = 0; j < 26; j++)
			{
				value = bigrams[i][j] * 1000;
				returnValue[i][j] = (int)value;
			}
		}
		
		return returnValue;
	}
	
	public static HashMap<String, Integer> calculateTrigramsHashMapFromFile(String fileName)
	{
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		
		File fileToRead = new File(fileName);
		String current_line = "";
		String key = "";
		String data = "";
		
		// working with trigrams
		int space_index = 3;
		
		try {
			Scanner scan = new Scanner(fileToRead);
			
			while(scan.hasNextLine())
			{
				current_line = scan.nextLine();
				key = current_line.substring(0, space_index).toLowerCase();
				data = current_line.substring(space_index + 1, current_line.length() );
				result.put(key, Integer.parseInt(data));
				
				
			}
			
			scan.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
	
	public static HashMap<String, Integer> calculateTrigramsOfPlaintext(String plaintext)
	{
		char first_letter;
		char second_letter;
		char third_letter;
		String triagram;
		HashMap<String, Integer> cipher_trigrams = new HashMap<String, Integer>();
		for(int index = 0; index < plaintext.length() - 2; index++)
		{
			first_letter = plaintext.charAt(index);
			second_letter = plaintext.charAt(index + 1);
			third_letter = plaintext.charAt(index + 2);
			
			triagram = "" + first_letter + second_letter + third_letter;
			
			if(cipher_trigrams.get(triagram) == null)
			{
				cipher_trigrams.put(triagram, 1);
			}
			else
			{
				cipher_trigrams.put(triagram, cipher_trigrams.get(triagram) + 1);
			}
			
		}
		
		return cipher_trigrams;
	}
	
	public static HashMap<String, Integer> calculateQuadGramsOfPlaintext(String plaintext)
	{
		char first_letter;
		char second_letter;
		char third_letter;
		char fourth_letter;
		String quadgram;
		HashMap<String, Integer> cipher_quadgrams = new HashMap<String, Integer>();
		for(int index = 0; index < plaintext.length() - 3; index++)
		{
			first_letter = plaintext.charAt(index);
			second_letter = plaintext.charAt(index + 1);
			third_letter = plaintext.charAt(index + 2);
			fourth_letter = plaintext.charAt(index + 3);
			
			quadgram = "" + first_letter + second_letter + third_letter + fourth_letter;
			
			if(cipher_quadgrams.get(quadgram) == null)
			{
				cipher_quadgrams.put(quadgram, 1);
			}
			else
			{
				cipher_quadgrams.put(quadgram, cipher_quadgrams.get(quadgram) + 1);
			}
			
		}
		
		return cipher_quadgrams;
	}
	
	public static HashMap<String, Integer> calculateQuadgramsHashMapFromFile(String fileName)
	{
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		
		File fileToRead = new File(fileName);
		String current_line = "";
		String key = "";
		String data = "";
		
		// working with quadrams
		int space_index = 4;
		
		try {
			Scanner scan = new Scanner(fileToRead);
			
			while(scan.hasNextLine())
			{
				current_line = scan.nextLine();
				key = current_line.substring(0, space_index).toLowerCase();
				data = current_line.substring(space_index + 1, current_line.length() );
				result.put(key, Integer.parseInt(data));
				//System.out.println(key + " " + data);
				
			}
			
			
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//long total = calculateTotalOfHashMap(result);
		
		//System.out.println(total);
		
		return result;

	}
	
	public static long calculateTotalOfHashMap(HashMap<String,Integer> result)
	{
		ArrayList<Integer> values =new ArrayList<Integer>(result.values());
		long total = 0;
		
		for(int i = 0; i < values.size(); i++)
		{
			total += values.get(i).intValue();
		}
		
		//System.out.println(total);
		
		return total;
		
	}
			
	public static void printKey(int keyGuess[])
	{
		for(int i = 0; i < keyGuess.length; i++)
		{
			System.out.println("Character: " + (char)('A' + i) + " Replacement: " + (char)('A' + keyGuess[i]));
		}
	}
	
	/*
	 * Modified the key with guessed key and returns the resulting 
	 * plaintext from ciphertext
	 */
	public static String solve(String ciphertext, int guessed_key[])
	{
		SubstitutionCipherSolver.getInstance();
		
		
		// initial key guess based on English letter frequencies
		int initialGuess[] = createInitialKeyBasedOnFreq(ciphertext);
		
		// decrypt
		String initialplaintext = SubstitutionCipher.decrypt(ciphertext, initialGuess);
		// create initial bigram counts matrix based on ciphertext
		int bigrams_matrix[][] = calculateBigramOccurences(initialplaintext); 
		
		//double initial_score = calculateScore(initial_bigram_freq);
		
		int current_guess[] = new int[26];
		
		// now try to find a better key using swaps based on indexes
		for(int i = 0; i < 26; i++)
		{
			current_guess[i] = initialGuess[i];
		}
		
		int best_guess[] = new int[26];
		
		for(int i = 0; i < 26; i++)
		{
			best_guess[i] = current_guess[i];
		}
		
		while( best_guess != null)
		{
			// find a guess better than current key candidate
			best_guess = findBetterKey(current_guess, bigrams_matrix, ciphertext );
			// if there is no better guess than current key candidate, just set best_guess to null

			if( best_guess != null)
			{
				for(int i = 0; i < 26; i++)
				{
					current_guess[i] = best_guess[i];
				}		
			}
			
		}
		
		// now current_guess is the most likely key
		String solved_plaintext = SubstitutionCipher.decrypt(ciphertext, current_guess);				
		
		for(int i = 0 ; i < 26; i++)
		{
			guessed_key[i] = current_guess[i];  
		}
		
		if(!SubstitutionCipher.checkKeyValidity(guessed_key))
			System.out.println("Invalid Key");
		
		return solved_plaintext;

	}
	
	public static int[][] calculateBigramOccurences(String text)
	{
		int bigrams_count[][] = new int[26][26];
		char charAtIndex = 0;
		char charAtIndexPlus1 = 0;
		int char_indexForIndex = 0;
		int char_indexForIndexPlus1 = 0;
		
		for(int i = 0; i < 26; i++)
		{
			for( int j = 0; j < 26; j++)
			{
				bigrams_count[i][j] = 0;
			}
		}
		
		
		for(int index = 0; index < text.length() - 1; index++)
		{
			charAtIndex = text.charAt(index);
			charAtIndexPlus1 = text.charAt(index + 1);
			char_indexForIndex = (int)(charAtIndex - 'a');
			char_indexForIndexPlus1 = (int)(charAtIndexPlus1 - 'a');
			
		
			bigrams_count[char_indexForIndex][char_indexForIndexPlus1]++;
		}
		
		return bigrams_count;
		
	}
		
	public static void swapmatrixRowsAndColumns( double bigramFreq[][], int index_one, int index_two )
	{
		double temp = 0.0;
		
		// rows
		for(int i = 0; i <26; i++)
		{
			temp = bigramFreq[index_one][i];
			bigramFreq[index_one][i] = bigramFreq[index_two][i];
			bigramFreq[index_two][i] = temp;
		}
		
		// columns
		for(int i = 0; i <26; i++)
		{
			temp = bigramFreq[i][index_one];
			bigramFreq[i][index_one] = bigramFreq[i][index_two];
			bigramFreq[i][index_two] = temp;
		}
	}
	
	public static void swapmatrixRowsAndColumns( int bigramFreq[][], int index_one, int index_two )
	{
		int temp = 0;
		
		// rows
		for(int i = 0; i <26; i++)
		{
			temp = bigramFreq[index_one][i];
			bigramFreq[index_one][i] = bigramFreq[index_two][i];
			bigramFreq[index_two][i] = temp;
		}
		
		// columns
		for(int i = 0; i <26; i++)
		{
			temp = bigramFreq[i][index_one];
			bigramFreq[i][index_one] = bigramFreq[i][index_two];
			bigramFreq[i][index_two] = temp;
		}
	}
		
	public static BigDecimal calculateScoreWhenReplaced(int bigrams_matrix[][], String ciphertext, int key_given[], int swap1, int swap2)
	{
		int bigrams_temp[][] = new int[26][26];
		int current_key[] = new int[26];
		
		for(int k = 0; k < 26; k++)
		{
			for(int l = 0; l < 26; l++)
			{
				bigrams_temp[k][l] = bigrams_matrix[k][l];
			}
		}
		
		for(int i = 0; i < 26; i++)
		{
			current_key[i] = key_given[i];
		}
		
		
		swapmatrixRowsAndColumns(bigrams_temp, swap1, swap2);
		swapTwoKeyElements(current_key, swap1, swap2);
		
		// calculate score also needs decrypted plaintext
		String plaintextPrediction = SubstitutionCipher.decrypt(ciphertext, current_key);
		
		// calculate trigrams and quadgrams of plaintextPrediction
		HashMap<String, Integer> plaintext_triagrams = calculateTrigramsOfPlaintext(plaintextPrediction);
		HashMap<String, Integer> plaintext_quadgrams = calculateQuadGramsOfPlaintext(plaintextPrediction);
		
		return calculateScore(bigrams_temp, plaintext_triagrams, plaintext_quadgrams, plaintextPrediction.length());
	}
	
	
	// finds a better key score than current_key and assigns new key to current_key
	// also sets current_bigrams to to bigram frequencies based on new key
	// for bigrams, just changes matrix to find best possible score, ciphertext itself is
	// needed for triagrams and quadgrams
	// returns score associated with newly found key
	public static int[] findBetterKey(int current_key[], int bigrams_matrix[][], String ciphertext)
	{
		int first_index = 0;
		int second_index = 0;

		int current_key_temp[] = new int[26];
		
		// score for the current key
		String current_plaintext = SubstitutionCipher.decrypt(ciphertext, current_key);		
		HashMap<String,Integer> current_plaintext_tri = calculateTrigramsOfPlaintext(current_plaintext);
		HashMap<String,Integer> current_plaintext_quad = calculateQuadGramsOfPlaintext(current_plaintext);
		long current_length = current_plaintext.length();
		
		BigDecimal current_score = calculateScore(bigrams_matrix, current_plaintext_tri, current_plaintext_quad, current_length);
		
		
		BigDecimal min_score = current_score;
		int min_index1 = -1;
		int min_index2 = -1;
		BigDecimal score_calculation = new BigDecimal(0.0);
		for(int i = 0; i < 26; i++)
		{
			current_key_temp[i] = current_key[i];
		}
		
		boolean smaller_found = false;
		
		for( int round = 1; round <= 25 && !smaller_found; round++ )
		{
			
			first_index = 0;
			second_index = first_index + round;
			
			// swap will be done here and new key will be compared with current best
			//System.out.println("Round " + round + " First index: " + first_index + " Second Index: " + second_index );
			
			score_calculation = calculateScoreWhenReplaced(bigrams_matrix, ciphertext, current_key, first_index, second_index);
			if( (score_calculation.compareTo(min_score) == -1)  )
			{
				//System.out.println(score_calculation.doubleValue());
				
				min_score = score_calculation;
				min_index1 = first_index;
				min_index2 = second_index;
				smaller_found = true;
			}
			
			while( first_index < 26 && second_index < 26 && !smaller_found)
			{
				first_index++;
				second_index = first_index + round;
				
				if( first_index < 26 && second_index < 26 )
				{
					// swap will be done here and new key will be compared with current best
					//System.out.println("Round " + round + " First index: " + first_index + " Second Index: " + second_index );
					
					score_calculation = calculateScoreWhenReplaced(bigrams_matrix, ciphertext, current_key, first_index, second_index);
					if( (score_calculation.compareTo(min_score) == -1) )
					{
						//System.out.println(score_calculation.doubleValue());
						
						min_score = score_calculation;
						min_index1 = first_index;
						min_index2 = second_index;
						smaller_found = true;
					}
					
				}
				
			}
						
		}
		
		if( min_index1 == -1 && min_index2 == -1 )
			return null;
		else
		{
		
			// now replace key elements according to best score
			swapTwoKeyElements(current_key_temp, min_index1,min_index2 );
			swapmatrixRowsAndColumns(bigrams_matrix, min_index1, min_index2);
			// if no better key was found
			return current_key_temp;
		}
		
		
	}
	
	public static BigDecimal calculateScore(int bigramOccur[][], HashMap<String, Integer> plaintext_triagrams, HashMap<String, Integer> plaintext_quadgrams, long prediction_length)
	{
		double term1 = 0.0;
		double term2 = 0.0;
		
		final int scale = 16;
		
		long number_of_bigrams_in_text = prediction_length - 1;
		long number_of_trigrams_in_text = prediction_length - 2;
		long number_of_quadgrams_in_text = prediction_length - 3;
		
		BigDecimal scaled_term1;
		BigDecimal scaled_term2;
		BigDecimal scaled_diff;
		BigDecimal scaled_total_bigrams = new BigDecimal(0.0);
		scaled_total_bigrams.setScale(scale, BigDecimal.ROUND_HALF_UP);
		
		BigDecimal scaled_total_triagrams = new BigDecimal(0.0);
		scaled_total_triagrams.setScale(scale, BigDecimal.ROUND_HALF_UP);
		
		BigDecimal scaled_total_quadgrams = new BigDecimal(0.0);
		scaled_total_quadgrams.setScale(scale, BigDecimal.ROUND_HALF_UP);
		
		BigDecimal scaled_total_score = new BigDecimal(0.0);
		scaled_total_score.setScale(scale, BigDecimal.ROUND_HALF_UP);
		
		
		//= value.setScale(0, RoundingMode.HALF_UP);
		//System.out.println(value + " -> " + scaled);
		
		// calculate score for bigrams
		for(int i = 0; i < 26; i++)
		{
			for( int j = 0; j < 26; j++)
			{
				term1 = (double)bigramOccur[i][j] / number_of_bigrams_in_text;
				term2 = (double)ENGLISH_BIGRAMS[i][j] / ENGLISH_BIGRAMS_TOTAL;
				
				scaled_term1 = new BigDecimal(term1);
				scaled_term2 = new BigDecimal(term2);
				
				scaled_term1 = scaled_term1.setScale(scale, BigDecimal.ROUND_HALF_UP);
				scaled_term2 = scaled_term2.setScale(scale, BigDecimal.ROUND_HALF_UP);
				
				scaled_diff = scaled_term1.subtract(scaled_term2);
				
				scaled_total_bigrams = scaled_total_bigrams.add(scaled_diff.abs());
			}
		}
		
		// calculate score for tiagrams
		ArrayList<String> triagrams_in_plaintext = new ArrayList<String>(plaintext_triagrams.keySet());
		String current_triagram = "";
		String current_quadgram = "";
		long current_occurence_in_plaintext;
		long current_occurence_in_english;
		
		
		for(int i = 0; i < triagrams_in_plaintext.size(); i++)
		{
			current_triagram = triagrams_in_plaintext.get(i);
			
			// find occurence of current_triagram
			current_occurence_in_plaintext = plaintext_triagrams.get(current_triagram);
			
			if( ENGLISH_TRIAGRAMS.get(current_triagram) != null)
			{		
				// find current_triagram in English triagrams
				current_occurence_in_english = ENGLISH_TRIAGRAMS.get(current_triagram);
			
				// find first percentage as number of current_triagram / total_in_plaintext
				term1 =  current_occurence_in_plaintext / (double)number_of_trigrams_in_text;
				// find second percentage as number of current_triagram_in_English / total_in_English
				term2 = current_occurence_in_english / (double)ENGLISH_TRIGRAMS_TOTAL;
			
				scaled_term1 = new BigDecimal(term1);
				scaled_term2 = new BigDecimal(term2);
				
				scaled_term1 = scaled_term1.setScale(scale, BigDecimal.ROUND_HALF_UP);
				scaled_term2 = scaled_term2.setScale(scale, BigDecimal.ROUND_HALF_UP);
				
				scaled_diff = scaled_term1.subtract(scaled_term2);
				
				scaled_total_triagrams = scaled_total_triagrams.add(scaled_diff.abs());
	
			}
		}
		
		ArrayList<String> quadgrams_in_plaintext = new ArrayList<String>(plaintext_quadgrams.keySet());
		
		for(int i = 0; i < quadgrams_in_plaintext.size(); i++)
		{
			current_quadgram  = quadgrams_in_plaintext.get(i);
			
			// find occurence of current_triagram
			current_occurence_in_plaintext = plaintext_quadgrams.get(current_quadgram);
			
			// find current_triagram in English triagrams
			if( ENGLISH_QUADGRAMS.get(current_quadgram) != null)
			{
			
				current_occurence_in_english = ENGLISH_QUADGRAMS.get(current_quadgram);
			
				// find first percentage as number of current_triagram / total_in_plaintext
				term1 =  current_occurence_in_plaintext / (double)number_of_quadgrams_in_text;
			
				// find second percentage as number of current_triagram_in_English / total_in_English
				term2 = current_occurence_in_english / (double)ENGLISH_QUADGRAMS_TOTAL;
			
				scaled_term1 = new BigDecimal(term1);
				scaled_term2 = new BigDecimal(term2);
				
				scaled_term1 = scaled_term1.setScale(scale, BigDecimal.ROUND_HALF_UP);
				scaled_term2 = scaled_term2.setScale(scale, BigDecimal.ROUND_HALF_UP);
				
				scaled_diff = scaled_term1.subtract(scaled_term2);
				
				scaled_total_quadgrams = scaled_total_quadgrams.add(scaled_diff.abs());
			}
		
		}
		
		scaled_total_score = scaled_total_score.add(scaled_total_bigrams);
		scaled_total_score = scaled_total_score.add(scaled_total_triagrams);
		scaled_total_score = scaled_total_score.add(scaled_total_quadgrams);
		
		return scaled_total_score;
	}
	
	// sort frequencies array using selection sort and return characters matching each frequencies
	public static char[] sortFrequencies(double freqs[])
	{
		char letters[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
						   'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
						  
		
		
		int max_index = 0;
		double max = 0.0;
		double temp_freq;
		char temp_char;
		
		for( int index = 0; index < freqs.length - 1; index++)
		{
				
			max_index = index;
			max = freqs[index];
			for(int second_index = index + 1; second_index < freqs.length; second_index++ )
			{
				if( freqs[second_index] > max )
				{
					max = freqs[second_index];
					max_index = second_index;
				}
				
			}
			
			// make swap
			
			// for frequency
			temp_freq = freqs[index];
			freqs[index] = freqs[max_index];
			freqs[max_index] = temp_freq;
			
			// for character
			temp_char = letters[index];
			letters[index] = letters[max_index];
			letters[max_index] = temp_char;

		}

		return letters;
	}
	
	// creates a key based on two letter frequencies, one retrieved using ciphertext
	// another one retrieved based on expected language frequencies 
	public static int[] createInitialKeyBasedOnFreq(String ciphertext)
	{
		// now count occurence of each letter in ciphertext
		int letter_occurences[] = new int[26];
		
		for( int i = 0; i < letter_occurences.length; i++)
		{
			letter_occurences[i] = 0;
		}
		
		char current_char;
		int current_index;
		
		for(int i = 0; i < ciphertext.length(); i++)
		{
			current_char = ciphertext.charAt(i);
			current_index = current_char - 'A';
			
			letter_occurences[current_index]++;
			
		}
		
		// now calculate frequencies
		double ciphertext_frequencies[] = new double[26];
		
	    BigDecimal bd = null;
	    double current_val = 0.0;
	    double rounded_val = 0.0;

		
	    for( int i = 0; i < ciphertext_frequencies.length; i++)
		{
			current_val = ((double)letter_occurences[i] / ciphertext.length());
			bd = new BigDecimal(current_val);
			bd = bd.setScale(4,BigDecimal.ROUND_HALF_UP);
			rounded_val = bd.doubleValue();
			ciphertext_frequencies[i] = rounded_val;
		}
		
		char ciphertext_characters[] = sortFrequencies(ciphertext_frequencies);

		/*
		System.out.println("Cipher letters and frequencies");
		
		for(int i = 0; i < 26; i++)
		{
			System.out.println("Letter: " + ciphertext_characters[i] + " Freq: " + ciphertext_frequencies[i] );
		}
		*/
		
		
		
		double current_freq[] = new double[26];
		
		for(int i = 0; i < LETTER_FREQ2.length; i++)
		{
			current_freq[i] = LETTER_FREQ2[i];
		}
		
		char expected_letters[] = sortFrequencies(current_freq);
				
		//System.out.println("Expected letters and frequencies");
		
		/*
		for(int i = 0; i < 26; i++)
		{
			System.out.println("Letter: " + expected_letters[i] + " Freq: " + current_freq[i] );
		}
		*/

		// now generate key based on ciphertext_characters and expected_letters
		int ciphertext_index;
		int expected_index;
		char ciphertext_char;
		char expected_char;
		int key_guess[] = new int[26];
		for( int i = 0; i < ciphertext_characters.length; i++)
		{
			ciphertext_char = ciphertext_characters[i];
			ciphertext_index = ciphertext_char - 'A';
			expected_char = expected_letters[i];
			expected_index = expected_char - 'A';
			key_guess[expected_index] = ciphertext_index;
		}
		
		return key_guess;
	}
	
	/**
	 * 
	 * @param key
	 * @param index_1
	 * @param index_2
	 */
	public static void swapTwoKeyElements(int key[], int index_1, int index_2)
	{
		int temp = key[index_1];
		key[index_1] = key[index_2];
		key[index_2] = temp;		
	}
	

	public static void main(String args[]) throws FileNotFoundException
	{
		
		
		int randomKey[] = SubstitutionCipher.generateRandomKeyForEncryption();
		
		/*
		System.out.println("Random Generated Key");
		for(int i = 0; i < randomKey.length; i++)
		{
			System.out.println("Character: " + (char)('A' + i) + " Replacement: " + (char)('A' + randomKey[i]));
		}
		System.out.println("--Random Generated Key--");
		*/
		
		
		//String plaintext = "Even when a victim does manage to attribute a cyberattack, the process can take a long time. It took the US weeks to publicly blame North Korea for the Sony attacks. That was relatively fast; most of that time was probably spent trying to figure out how to respond. Attacks by China against US companies have taken much longer to attribute. This delay makes defense policy difficult. Microsoft's Scott Charney makes this point: When you're being physically attacked, you can call on a variety of organizations to defend you—the police, the military, whoever does antiterrorism security in your country, your lawyers. The legal structure justifying that defense depends on knowing two things: who's attacking you, and why. Unfortunately, when you're being attacked in cyberspace, the two things you often don't know are who's attacking you, and why.";
		
		//String plaintext2 = "Different types of attribution require different levels of evidence. In the Sony case, we saw the US government was able to generate enough evidence to convince itself. Perhaps it had the additional evidence required to convince North Korea it was sure, and provided that over diplomatic channels. But if the public is expected to support any government retaliatory action, they are going to need sufficient evidence made public to convince them. Today, trust in US intelligence agencies is low, especially after the 2003 Iraqi weapons-of-mass-destruction debacle.What all of this means is that we are in the middle of an arms race between attackers and those that want to identify them: deception and deception detection. It's an arms race in which the US—and, by extension, its allies—has a singular advantage. We spend more money on electronic eavesdropping than the rest of the world combined, we have more technology companies than any other country, and the architecture of the Internet ensures that most of the world's traffic passes through networks the NSA can eavesdrop on.";
		//String ciphertext = SubstitutionCipher.encrypt(plaintext, randomKey);
		
		//String t = "So began 10 minutes of a remarkably demanding concentration game. At Level 2, even adults find the task somewhat taxing. Almost no one gets past Level 3 without training. But most people who stick with the game do get better with practice. This isn’t surprising: practice improves performance on almost every task humans engage in, whether it’s learning to read or playing horseshoes.What is surprising is what else it improved. In a 2008 study, Susanne Jaeggi and Martin Buschkuehl, now of the University of Maryland, found that young adults who practiced a stripped-down, less cartoonish version of the game also showed improvement in a fundamental cognitive ability known as “fluid” intelligence: the capacity to solve novel problems, to learn, to reason, to see connections and to get to the bottom of things. The implication was that playing the game literally makes people smarter.Psychologists have long regarded intelligence as coming in two flavors: crystallized intelligence, the treasure trove of stored-up information and how-to knowledge (the sort of thing tested on “Jeopardy!” or put to use when you ride a bicycle); and fluid intelligence. Crystallized intelligence grows as you age; fluid intelligence has long been known to peak in early adulthood, around college age, and then to decline gradually. And unlike physical conditioning, which can transform 98-pound weaklings into hunks, fluid intelligence has always been considered impervious to training.That, after all, is the premise of I.Q. tests, or at least the portion that measures fluid intelligence: we can test you now and predict all sorts of things in the future, because fluid intelligence supposedly sets in early and is fairly immutable. While parents, teachers and others play an essential role in establishing an environment in which a child’s intellect can grow, even Tiger Mothers generally expect only higher grades will come from their children’s diligence — not better brains.";
		
		
		Scanner scan = new Scanner(new File("text_file9"));
		String from_file = "";
		while(scan.hasNextLine())
		{
			from_file += scan.nextLine();
		}
		scan.close();
		
		
		
		//System.out.println(SubstitutionCipher.sanitizeInput(from_file));
		int keyGuess[] = new int[26];
		
		String ciphertext = SubstitutionCipher.encrypt(from_file, randomKey);
		
		String plain = SubstitutionCipherSolver.solve(ciphertext, keyGuess);
	
		System.out.println(plain);
		String input_sanitized = SubstitutionCipher.sanitizeInput(from_file);
		System.out.println(input_sanitized);
		
		System.out.println(plain.equals(input_sanitized));
		System.out.println(input_sanitized.length());
		
		int total_correct = 0;
		for(int i = 0; i < 26; i++ )
		{
			if( keyGuess[i] == randomKey[i])
			{
				total_correct++;
			}
		}
		System.out.println("Number of key guessed correctly: " + total_correct);
		
		for(int i = 0; i < plain.length(); i++)
		{
			if( plain.charAt(i) != input_sanitized.charAt(i))
			{
				System.out.println("Characters at index " + i + " are different");
			}
		}
		

	}
	
}
