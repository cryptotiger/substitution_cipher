import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;


public class TestSubstitutionCipher {

	@SuppressWarnings("deprecation")
	@Test
	public void testCheckKeyValidity() {
		//fail("Not yet implemented");
		
		//SubstitutionCipher cipher = SubstitutionCipher.getinstance();
		
		int key_valid[] = new int[26];
		
		for(int i = 0; i < 26; i++)
		{
			key_valid[i] = i;
		}
		
		boolean valid_key = SubstitutionCipher.checkKeyValidity(key_valid);
		Assert.assertEquals(true, valid_key);
		
		for(int i = 0; i < 50000; i++)
		{
			SubstitutionCipher.swapTwoKeyElements(key_valid);
			valid_key = SubstitutionCipher.checkKeyValidity(key_valid);
				
			Assert.assertEquals(true, valid_key);
		}
		
		
		//boolean valid_key = SubstitutionCipher.checkKeyValidity(key_valid);
	
		//Assert.assertEquals(true, valid_key);
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSanitizeInput() {
		//fail("Not yet implemented");
		
		//SubstitutionCipher cipher = SubstitutionCipher.getinstance();
		
		String sample1 = "It DOESN'T work if you expect replacing a character with the null character would somehow remove that character from the string. ";
		
		String result1 = SubstitutionCipher.sanitizeInput(sample1);
		String expectedResult1 = "itdoesntworkifyouexpectreplacingacharacterwiththenullcharacterwouldsomehowremovethatcharacterfromthestring";
		//System.out.println(result1);
		
		Assert.assertEquals(true, result1.equals(expectedResult1));
		
		String sample2 = "Here's how Rowhammer gets its name: In the Dynamic Random Access Memory (DRAM) used in some laptops, a hacker can run a program designed to repeatedly access a certain row of transistors in the computer's memory, \"hammering\" it until the charge from that row leaks into the next row of memory. That electromagnetic leakage can cause what's known as \"bit flipping,\" in which transistors in the neighboring row of memory have their state reversed, turning ones into zeros or vice versa. ";
		
		String result2 = SubstitutionCipher.sanitizeInput(sample2);
		
		String expectedResult2 = "hereshowrowhammergetsitsnameinthedynamicrandomaccessmemorydramusedinsomelaptopsahackercanrunaprogramdesignedtorepeatedlyaccessacertainrowoftransistorsinthecomputersmemoryhammeringituntilthechargefromthatrowleaksintothenextrowofmemorythatelectromagneticleakagecancausewhatsknownasbitflippinginwhichtransistorsintheneighboringrowofmemoryhavetheirstatereversedturningonesintozerosorviceversa";
		
		Assert.assertEquals(true, result2.equals(expectedResult2));
		
		
		
	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGenerateRandomKey() {
		//fail("Not yet implemented");
		
		int currentKey[];
		
		for(int i = 0; i < 30000; i++)
		{
			currentKey = SubstitutionCipher.generateRandomKeyForEncryption();
			
			/*
			for(int j = 0; j < 26; j++ )
			{
				System.out.print(currentKey[j] + "\t");
			}
			System.out.println();
			*/
			
			Assert.assertTrue(currentKey != null);
		}

	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testEncrypt() {
		//fail("Not yet implemented");
		
		int currentKey[];

		currentKey = SubstitutionCipher.generateRandomKeyForEncryption();
		String sample1 = "It DOESN'T work if you expect replacing a character with the null character would somehow remove that character from the string. ";
		
		System.out.println("KEY");
		for(int j = 0; j < 26; j++ )
		{
			System.out.print(currentKey[j] + "\t");
		}
		
		System.out.println();
		System.out.println("---");
		
		String cipher1 = SubstitutionCipher.encrypt(sample1, currentKey);
		String plain1 = SubstitutionCipher.decrypt(cipher1, currentKey);
		
		
		Assert.assertEquals(true, plain1.equals(SubstitutionCipher.sanitizeInput(sample1)));
		
		System.out.println(cipher1);
		System.out.println(plain1);
		
		//String sample2 = "Here's how Rowhammer gets its name: In the Dynamic Random Access Memory (DRAM) used in some laptops, a hacker can run a program designed to repeatedly access a certain row of transistors in the computer's memory, \"hammering\" it until the charge from that row leaks into the next row of memory. That electromagnetic leakage can cause what's known as \"bit flipping,\" in which transistors in the neighboring row of memory have their state reversed, turning ones into zeros or vice versa. ";
		
		currentKey = SubstitutionCipher.generateRandomKeyForEncryption();
		String sample2 = "Here's how Rowhammer gets its name: In the Dynamic Random Access Memory (DRAM) used in some laptops, a hacker can run a program designed to repeatedly access a certain row of transistors in the computer's memory, \"hammering\" it until the charge from that row leaks into the next row of memory. That electromagnetic leakage can cause what's known as \"bit flipping,\" in which transistors in the neighboring row of memory have their state reversed, turning ones into zeros or vice versa. ";
		
		System.out.println("KEY");
		for(int j = 0; j < 26; j++ )
		{
			System.out.print(currentKey[j] + "\t");
		}
		
		System.out.println();
		System.out.println("---");
		
		String cipher2 = SubstitutionCipher.encrypt(sample2, currentKey);
		String plain2 = SubstitutionCipher.decrypt(cipher2, currentKey);
		
		
		Assert.assertEquals(true, plain2.equals(SubstitutionCipher.sanitizeInput(sample2)));
		
		System.out.println(cipher2);
		System.out.println(plain2);

	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testEncryptRandomStrings() {
		//fail("Not yet implemented");
		
		int currentKey[];
		String current_plaintext = "";
		String cipher = "";
		String recoveredPlain = "";
		
		for(int i = 0; i < 10; i++)
		{
			currentKey = SubstitutionCipher.generateRandomKeyForEncryption();
			current_plaintext = SubstitutionCipher.generateRandomString(1000);
			//System.out.println(current_plaintext);
			
			cipher = SubstitutionCipher.encrypt(current_plaintext, currentKey);
			//System.out.println(cipher);
			recoveredPlain = SubstitutionCipher.decrypt(cipher, currentKey);
			
			Assert.assertEquals(true, current_plaintext.equals(recoveredPlain));
			
		}
		
		

	}

}
