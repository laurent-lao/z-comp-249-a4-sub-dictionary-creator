// -----------------------------------------------------
// Assignment 4
// Part: 1
// Written by: Laurent (40020483)
// This assignment is meant practice using ArrayLists
// It reads through a user-inputted file and creates a dictionary
// out of the input file with headers.
// -----------------------------------------------------

import java.util.ArrayList;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #4 Part 1
 * Due Date: April 8 2019
 * Word cleaner and searcher
 */
public class Word {

	/**
	 * Cleans the String from forbidden characters and capitalize it
	 * @param wordToClean String representing the word to be cleaned
	 * @return String capitalized and with alpha removed OR returns null if cleaned is of length 0 OR returns MCsquare
	 */
	public static String clean(String wordToClean) {
		String cleaned = "";
		for (int i = 0; i < wordToClean.length(); i++)
		{
			// If it's not a forbidden character, add to the string
			if (!isForbiddenChar(wordToClean.charAt(i)))
			{
				cleaned += wordToClean.charAt(i);
			}
			else
			{
				break;
			}
		}

		if (cleaned.length() == 0)
		{
			return null;
		}
		else if (cleaned.equalsIgnoreCase("MC"))
		{
			char square = 178;
			return "MC" + square;
		}
		else
		{
			// Return the upper case of cleaned
			return cleaned.toUpperCase();
		}
	}

	/**
	 * Checks whether the char is a forbidden character (not alpha)
	 * @param charToCheck represents the char to be checked
	 * @return a boolean value that is true if it is a forbidden character
	 */
	public static boolean isForbiddenChar(char charToCheck) {
		boolean isLowerAlpha = charToCheck >= 'a' && charToCheck <= 'z';
		boolean isUpperAlpha = charToCheck >= 'A' && charToCheck <= 'Z';

		return !(isLowerAlpha || isUpperAlpha);
	}

	/**
	 * Searches for a String in an ArrayList of String
	 * @param subDictionary represents the ArrayList of String
	 * @param wordToSearch represents the String to be searched for
	 * @return search[0]: int representing found (0 = true), search[1]: int representing the index to append at
	 */
	public static int [] search(ArrayList<String> subDictionary, String wordToSearch)
	{
		int startIndex = 0;
		int endIndex = subDictionary.size() - 1;
		return search(subDictionary, wordToSearch, startIndex, endIndex);
	}

	/**
	 * RECURSIVE METHOD: Searches for a String in an ArrayList of String
	 * @param subDictionary represents the ArrayList of String to be searched into
	 * @param wordToSearch represents the String to be searched for
	 * @param startIndex Int representing the starting Index
	 * @param endIndex Int representing the ending index
	 * @return search[0]: int representing found (0 = false), search[1]: int representing the index to append at
	 */
	public static int [] search(ArrayList<String> subDictionary, String wordToSearch, int startIndex, int endIndex){

		// If the startIndex is bigger than the endIndex, the search has ended; the word is not in the array
		if (startIndex > endIndex)
		{
			return new int[]{0, startIndex};
		}
		else
		{
			int midIndex = (startIndex + endIndex)/2;
			int newStartIndex = midIndex + 1;
			int newEndIndex = midIndex - 1;

			// Get the word at the midIndex
			String wordAtMidIndex = subDictionary.get(midIndex);

			// Compute whether the wordToSearch is equal, lower or smaller than the word at midIndex
			int comparingString = wordToSearch.compareTo(wordAtMidIndex);

			if (comparingString == 0) // then it the word has been found in the list
			{
				return new int[]{1, -1};
			}
			else if (comparingString < 0) // then the word is lexically before the midIndex, change the end bound of search
			{
				// RECURSION
				return search(subDictionary, wordToSearch, startIndex, newEndIndex);
			}
			else if (comparingString > 0) // then the word is lexically after the midIndex, change the start bound of the search
			{
				// RECURSION
				return search(subDictionary, wordToSearch, newStartIndex, endIndex);
			}
			else
			{
				return null;
			}
		}
	}

}
