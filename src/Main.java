// -----------------------------------------------------
// Assignment 4
// Part: 1
// Written by: Laurent (40020483)
// This assignment is meant practice using ArrayLists
// It reads through a user-inputted file and creates a dictionary
// out of the input file with headers.
// -----------------------------------------------------

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #4 Part 1
 * Due Date: April 8 2019
 * Driver Class
 */
public class Main {

	public static class exitMessageVars {
		static int numberofEntries;
	}

	public static class instanceVars {
		static       Scanner     inputReader    = null;                              // Will Store inputReader
		static       PrintWriter outputWriter   = null;                              // Will Store outputWriter
		static final String      outputFileName = "Sub-Dictionary.txt";              // Name of the outputFile
	}

	/**
	 * Main method
	 *
	 * @param args is unused
	 */
	public static void main(String[] args) {

		boolean mustDeleteOutputFile = false;                             // Flag to delete outputFile

		// Displaying welcome message
		displayWelcomeMessage();

		// Prompt User for input filename, checks if it's valid
		// Initializes Scanner and PrintWriter
		FileManip fileManip = new FileManip(instanceVars.outputFileName);
		instanceVars.inputReader = fileManip.getInputReader();
		instanceVars.outputWriter = fileManip.getOutputWriter();

		// Reading the input file
		ArrayList<String> subDictionary = new ArrayList<String>();
		while (instanceVars.inputReader.hasNext())
		{

			// Process and append the word into subDictionary list
			// Binary searches for index to append at
			cleanAndAppendNextWordIfUnique_sorted(instanceVars.inputReader, subDictionary);
		}

		// Verify and print the ArrayList
		if (subDictionary.size() == 0)
		{
			System.out.println("SubDictionary array is empty.");
			// Toggle flag since empty
			mustDeleteOutputFile = true;
		}
		else
		{
			// Trim the
			subDictionary.trimToSize();

			// Printing the output file with sorted ArrayList
			printArrayListToOutputFile(instanceVars.outputWriter, subDictionary);

		}

		// 									== ENDING PROGRAM ==

		// Closing Scanners and PrintWriter
		fileManip.closeScannerAndWriter();

		// If need to delete output file
		//mustDeleteOutputFile = true; // DEBUG
		if (mustDeleteOutputFile)
		{
			fileManip.deleteOutputFile();
			displayExitMessage(1);
		}
		else
		{
			// Displaying regular exit message
			displayExitMessage(0);
		}
	} // End of Main

	/**
	 * Processes the new word, either add it to the dictionary or do nothing
	 *
	 * @param inputReader   is a Scanner object representing the inputStreamFile
	 * @param subDictionary is a ArrayList of Strings that represents the subDictionary
	 */
	public static void cleanAndAppendNextWordIfUnique_sorted(Scanner inputReader, ArrayList<String> subDictionary) {
		String nextWord      = inputReader.next();
		String wordCleanedUp = Word.clean(nextWord);

		if (wordCleanedUp != null)
		{
			// Subdictionary binary search for wordCleanedUp
			int[]   searchResult      = Word.search(subDictionary, wordCleanedUp);
			boolean wordAlreadyExists = searchResult[0] != 0;

			// If the word doesn't exist yet, append the wordCleanedUp at the index returned by searchResult[1]
			if (!wordAlreadyExists)
			{
				int indexForAppending = searchResult[1];
				subDictionary.add(indexForAppending, wordCleanedUp);
			}
		}
	}

	/**
	 * Prints the ArrayList with a header when the starting character is different
	 *
	 * @param outputWriter  is a PrintWriter object for the outputStreamFile
	 * @param subDictionary is an ArrayList of String representing the words to be printed
	 * @return whether the ArrayList is empty or not
	 */
	public static boolean printArrayListToOutputFile(PrintWriter outputWriter, ArrayList<String> subDictionary) {
		char previousFirstChar = 0;

		// Print intro message to outputFile (size of entries and all)
		outputWriter.println("The document produced this sub-dictionary, which includes "
				+ subDictionary.size() + " entries.");
		exitMessageVars.numberofEntries = subDictionary.size();

		if (subDictionary.size() != 0)
		{
			for (String wordToPrint : subDictionary)
			{
				// Check if it's a new first letter, and add heading if true
				char firstCharOfWordToPrint = wordToPrint.charAt(0);
				if (firstCharOfWordToPrint != previousFirstChar)
				{
					// Change the First Char for the next iteration
					previousFirstChar = firstCharOfWordToPrint;

					// Print the header
					outputWriter.println("\n" + firstCharOfWordToPrint + "\n==");
				}

				// Print the word into the file
				outputWriter.println(wordToPrint);
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Deletes specified file
	 *
	 * @param outputFileName represents the file to delete
	 */
	public static void deleteFile(String outputFileName) {
		File outputFile = new File(outputFileName);
		if (outputFile.exists())
		{
			boolean deleteIsSuccessful = outputFile.delete();
			//System.out.println("Debug: Delete has been successful? " + deleteIsSuccessful);
		}
	}

	/**
	 * Displays Welcome message
	 */
	public static void displayWelcomeMessage() {
		System.out.println("Welcome to Laurent's Sub-Dictionary Creator");
	}

	/**
	 * Displays Exit message
	 *
	 * @param messageCase an integer representing the exit message to be displayed
	 */
	public static void displayExitMessage(int messageCase) {

		// Custom Message
		switch (messageCase)
		{
			case 0:
				System.out.println("File Sub-Dictionary.txt has been created. There are "
						+ exitMessageVars.numberofEntries + " entries.");
				break;
			case 1:
				System.out.println("The program is ending, no file has been created.");
				break;
			default:
				System.out.println("Debug: No such case for displayWelcomeMessage");
				break;
		}

		// Display End of Program
		System.out.println("End of Program");
	}
}

