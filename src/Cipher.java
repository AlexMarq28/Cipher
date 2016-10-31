public class Cipher{
private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%-_;?-=:"
			+ '\n' + '\r';
	private static final String SIMPLE_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	// Set this variable to the default alphabet you wish to use
	private static final String DEFAULT_ALPHABET = ALPHABET;
	
	private static Dictionary d = Dictionary.buildDictionary("words.txt");

	public static void main(String[]args){
		System.out.println(isEnglish("hello"));
	}

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param shiftAmount
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public int[] randomPermutation(int length) {
		int[] result = new int[length];
		for (int i = 0; i < length; i++) {
			result[i] = i;
		}
		for (int i = 0; i < length; i++) {
			int x = (int) (Math.random() * length);
			if (x != i) {
				int temp = result[i];
				result[i] = x;
				result[x] = temp;
			}
		}
		return result;

	}

	public String substitutionCipher(String plaintext, int[] permutation, String alphabet) {
		String result = "";
		for (int i = 0; i < plaintext.length(); i++) {
			int temp = permutation[alphabet.indexOf(plaintext.substring(i, i + 1))];
			result += alphabet.substring(temp, temp + 1);
		}
		return result;
	}

	/**
	 * Returns plaintext encrypted by the rotation cipher with a shift of
	 * movement.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param shiftAmount
	 *            the number of characters in ALPHABET to shift by.
	 * @return returns the encrypted plainText.
	 */
	public static String rotationCipherEncrypt(String plain, int shift, String alphabet) {
		String result = "";
		for (int i = 0; i < plain.length(); i++) {
			int index = alphabet.indexOf(plain.substring(i, i + 1));
			index += shift;
			while (i < 0) {
				index += alphabet.length();
			}
			index = index % alphabet.length();
			result += alphabet.substring(index, index + 1);

		}
		return result;
	}

	public static String rotationCipherEncrypt(String plainText, int shiftAmount) {
		return rotationCipherEncrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
	}

	public static String rotationCipherDecrypt(String plainText, int shiftAmount) {
		return rotationCipherDecrypt(plainText, shiftAmount, DEFAULT_ALPHABET);
	}

	/**
	 * Returns a the result of decrypting cipherText by shiftAmount using the
	 * rotation cipher.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param shiftAmount
	 *            the key to decrypt the cipher.
	 * @return returns the decrypted cipherText.
	 */
	public static String rotationCipherDecrypt(String cipher, int shift, String alphabet) {
		String result = "";
		for (int i = 0; i < cipher.length(); i++) {
			int index = alphabet.indexOf(cipher.substring(i, i + 1));
			index -= shift;
			while (index < 0) {
				index += alphabet.length();
			}

			index = index % alphabet.length();

			result += alphabet.substring(index, index + 1);
		}
		return result;
	}

	public static int shiftIndex(int index, int shift, int alphabetLength) {
		index += shift;
		while (index < 0) {
			index += alphabetLength;
		}
		index %= alphabetLength;
		return index;
	}

	/**
	 * Returns plaintext encrypted by the vigenere cipher encoded with the
	 * String code
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param plainText
	 *            the plain text to be encrypted.
	 * @param code
	 *            the code to use as the encryption key.
	 * @return returns the encrypted plainText.
	 */
	public static String vigenereCipherEncrypt(String plain, String password, String alphabet) {

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < plain.length(); i++) {
			int index = i % password.length();

			String temp = password.substring(index, index + 1);

			index = alphabet.indexOf(temp);

			index = shiftIndex(alphabet.indexOf(plain.substring(i, i + 1)), index, alphabet.length());
			result.append(alphabet.substring(index, index + 1));

		}
		return (result.toString());
	}

	public static String vigenereCipherEncrypt(String plainText, String code) {
		return vigenereCipherEncrypt(plainText, code, DEFAULT_ALPHABET);
	}

	/**
	 * Returns the result of decrypting cipherText with the key code.
	 * 
	 * @param alphabet
	 *            the alphabet to be used for the encryption
	 * @param cipherText
	 *            the encrypted text.
	 * @param code
	 *            the decryption key
	 * @return returns the decrypted cipherText.
	 */
	public static String vigenereCipherDecrypt(String cipher, String password, String alphabet) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < cipher.length(); i++) {
			int index = i % password.length();

			String temp = password.substring(index, index + 1);

			index = alphabet.indexOf(temp);

			index = shiftIndex(alphabet.indexOf(cipher.substring(i, i + 1)), index * (-1), alphabet.length());
			result.append(alphabet.substring(index, index + 1));

		}
		return (result.toString());
	}

	public static String vigenereCipherDecrypt(String cipherText, String code) {
		return vigenereCipherDecrypt(cipherText, code, DEFAULT_ALPHABET);
	}

	/**
	 * returns a copy of the input plaintext String with invalid characters
	 * stripped out.
	 * 
	 * @param plaintext
	 *            The plaintext string you wish to remove illegal characters
	 *            from
	 * @param alphabet
	 *            A string of all legal characters.
	 * @return String A copy of plain with all characters not in alphabet
	 *         removed.
	 */
	private static String stripInvalidChars(String plaintext, String alphabet) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < plaintext.length(); i++) { // loop through plaintext
			if (alphabet.indexOf(plaintext.charAt(i)) >= 0) // get index of char
				b.append(plaintext.charAt(i)); // if it exists, keep it
			else
				// otherwise skip it &
				System.out.println("Stripping letter: \"" + plaintext.charAt(i) // display
																				// a
																				// message
						+ "\"");
		}
		return b.toString();
	}

	/**
	 * returns true if plaintext is valid English.
	 * 
	 * @param plaintext
	 *            the text you wish to test for whether it's valid English
	 * @return boolean returns true if plaintext is valid English.
	 */
	private static boolean isEnglish(String plaintext) {
		
		d.buildDictionary("words.txt");
		return (d.isWord(plaintext));

	}

	public static int wordCount(String input) {
		int wordCount = 0;

		for (int i = 0; i < input.length(); i++) {
			int nextSpace = input.indexOf(" ", i + 1);
			if (nextSpace == -1 && i < input.length() - 1) {
				wordCount++;
				i = input.length();
			} else if (nextSpace != i + 1) {
				wordCount++;
				i = nextSpace;
			}
		}
		return wordCount;
	}

	public static String[] getWords(String input) {

		String[] arr = new String[wordCount(input)];
		int counter = 0;
		for (int i = 0; i < input.length(); i++) {
			int nextSpace = input.indexOf(" ", i + 1);
			if (nextSpace == -1 && i < input.length() - 1) {

				arr[counter] = input.substring(i, input.length());

				counter++;
				i = input.length();
			} else if (nextSpace != i + 1) {

				arr[counter] = input.substring(i, nextSpace);

				counter++;
				i = nextSpace;
			}
		}
		return arr;
	}

	public static String rotationCipherCrack(String cipher, String alphabet) {
		double counter = 0;
		for (int i = 0; i < alphabet.length(); i++) {
			String test = rotationCipherDecrypt(cipher, i, alphabet);
			String[] arr = getWords(test);
			counter = 0;
			for (int x = 0; x < arr.length; x++) {
				System.out.println(arr[x] + " " + isEnglish(arr[x]));

				if (isEnglish(arr[x])) {

					counter++;
				}
			}
			if (counter / arr.length >= 0.3) {
				return test;
			}

		}
		return cipher;

	}

	public static String getLettersOut(String encrypted, int index, int length) {
		String temp = "";
		for (int i = index; i < encrypted.length(); i = i + length) {
			temp = temp + encrypted.substring(i, i + 1);
		}
		return temp;
	}

	public static String findCodeLetter(String temp, String alphabet) {
		for (int i = 0; i < alphabet.length(); i++) {
			String decoded = rotationCipherDecrypt(temp, i, alphabet);
			LetterBag bag = new LetterBag();
			for (int x = 0; x < decoded.length(); x++) {
				
				bag.add(decoded.substring(x, x + 1));
			}
			
			if (bag.getMostFrequent().equals( " ")) {
				
				return alphabet.substring(i, i + 1);

			}
			bag.clear();
		}
		return null;
	}

	public static String vigenereCipherCrackThreeLetter(String cipher, String alphabet) {
		String temp1 = getLettersOut(cipher, 0, 3);
		String temp2 = getLettersOut(cipher, 1, 3);
		String temp3 = getLettersOut(cipher, 2, 3);
		String code = "";

		code = code + findCodeLetter(temp1, alphabet);
		code = code + findCodeLetter(temp2, alphabet);
		code = code + findCodeLetter(temp3, alphabet);
	

		return (code);
	}
	public static String vigenereCipherCrack(String cipher, int PasswordLength, String alphabet){
		String code="";
		for(int i=0;i<PasswordLength;i++){
			String temp=getLettersOut(cipher, i,PasswordLength);
			code+=findCodeLetter(temp,ALPHABET);
		}
		return( code);
	}
}

