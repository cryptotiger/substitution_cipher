import java.util.ArrayList;
import java.util.Random;


public class SubstitutionCipher {

	// instance of substitution cipher
	private static SubstitutionCipher cipher;
	
	private SubstitutionCipher()
	{
		
	}
	
	// Singleton Class
	public static SubstitutionCipher getinstance()
	{
		if( cipher == null)
		{
			cipher = new SubstitutionCipher();
		}
		
		return cipher;
	}
	
	public static String encrypt( String plaintext, int key[])
	{
		if( !checkKeyValidity(key))
		{
			System.out.println("Error: Not a valid key specified");
			return "";
		}
		else
		{
			String ciphertextResult = "";
			String plaintext_input = sanitizeInput(plaintext);
			char current_char = 0;
			int current_index = 0;
			char replacement_char = 0;
			int replacement_index = 0;
			// for each character in the plaintext_input
			for(int i = 0; i < plaintext_input.length(); i++)
			{
				current_char = plaintext_input.charAt(i);
				
				// subtract 'a' 
				current_index = (int)current_char - 'a';
				
				replacement_index = key[current_index];
				
				replacement_char = (char) ('A' + replacement_index);
				
				ciphertextResult += replacement_char;
			}
			
			return ciphertextResult;
		}
		
	}
	
	public static String decrypt( String ciphertext, int key[])
	{
		if( !checkKeyValidity(key))
		{
			System.out.println("Error: Not a valid key specified");
			return "";
		}
		
		else
		{
			// calculate inverse of key
			int key_inv[] = generateKeyInverse(key);
			String plaintextResult = "";
			
			char current_char = 0;
			int current_index = 0;
			char replacement_char = 0;
			int replacement_index = 0;
			// for each character in the plaintext_input
			for(int i = 0; i < ciphertext.length(); i++)
			{
				current_char = ciphertext.charAt(i);
				
				// subtract 'a' 
				current_index = (int)current_char - 'A';
				
				replacement_index = key_inv[current_index];
				
				replacement_char = (char) ('a' + replacement_index);
				
				plaintextResult += replacement_char;
			}
			
			return plaintextResult;
		}
		
	}
	
	public static boolean checkKeyValidity(int key[])
	{
		if( key.length != 26)
			return false;
		else {
			// check if each value from 0 to 25 appears exactly once
			int appearances[] = new int[26];
			
			for(int i = 0; i < appearances.length; i++)
			{
				appearances[i] = 0;
			}
			
			for( int i = 0; i < key.length; i++)
			{
				appearances[key[i]]++;
			}
			
			// now all appearances should be 1
			boolean all_one = true;
			for(int i = 0; i < appearances.length && all_one; i++)
			{
				if( appearances[i] != 1)
				{
					all_one = false;
				}
			}
			
			return all_one;
			
		}
	}
	
	public static void swapTwoKeyElements(int key[])
	{
		Random generator = new Random();
		
		int index_1 = generator.nextInt(26);
		int index_2 = generator.nextInt(26);
		
		int temp = key[index_1];
		key[index_1] = key[index_2];
		key[index_2] = temp;
		
		
	}
	
	// remove all characters in input which are not alphabetic ( between a-z) and make all characters
	// lowercase before
	public static String sanitizeInput(String plaintext)
	{
		String result = "";
		
		String plaintext_with_lowercase = plaintext.toLowerCase();
		char current_char = 0;
		for( int i = 0; i < plaintext_with_lowercase.length(); i++)
		{
			current_char = plaintext_with_lowercase.charAt(i);
			
			if( current_char >= 'a' && current_char <= 'z')
			{
				result += current_char;
			}
			
		}
		
		return result;
		
	}
	
	// generate a random key for use for encrypting plaintext
	public static int[] generateRandomKeyForEncryption()
	{
		int generatedKey[] = new int[26];
		
		ArrayList<Integer> possibleKeyValues = new ArrayList<Integer>();
		Random generator = new Random();
		int randomIndex = 0;
		for( int i = 0; i < 26; i++)
		{
			possibleKeyValues.add(new Integer(i));
		}
		
		
		
		// now select a random value among possibleKeyValues, add into key and remove it from list 
		for( int i = 0; i < 26; i++)
		{
			randomIndex = generator.nextInt(possibleKeyValues.size());
			
			generatedKey[i] = possibleKeyValues.get(randomIndex);
			
			possibleKeyValues.remove(randomIndex);
			
		}
		
		if( checkKeyValidity(generatedKey))
		{
			return generatedKey;
		}
		else
		{
			System.out.println("Not a valid random key generated");
			return null;
		}
	
	
	}
	
	public static int[] generateKeyInverse(int key[])
	{
		int key_inv[] = new int[26];
	
		for(int i = 0; i < 26; i++)
		{
			key_inv[key[i]] = i;
		}
		
		return key_inv;
	
	}
	
	public static String generateRandomString(int length)
	{
		String result = "";
		Random generator = new Random();
		int randomIndex = 0;
		char current_char = 0;
		
		for(int i = 0; i < length; i++)
		{
			randomIndex = generator.nextInt(26);
			current_char = (char) ('a' + randomIndex);
			result += current_char;
			
		}
		
		return result;
		
	}

	
	
}
