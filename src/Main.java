// -----------------------------------------------------
// Assignment 4
// Part: 1
// Written by: Laurent (40020483)
// This assignment is meant practice using ArrayLists
// It reads through a user-inputted file and creates a dictionary
// out of the input file with headers.
// -----------------------------------------------------

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #4 Part 1
 * Due Date: April 8 2019
 * Driver Class
 */
public class Main {

	/**
	 * Main method
	 *
	 * @param args is unused
	 */
	public static void main(String[] args) {

		Scanner      inputReader        = null;                                // Will Store inputReader
		PrintWriter  outputWriter       = null;                                // Will Store outputWriter
		final String outputFileName     = "Sub-Dictionary.txt";                // Name of the outputFile
		boolean      isDeleteOutputFile = false;                               // Flag to delete outputFile

		// Displaying welcome message
		displayWelcomeMessage();

		// Prompt User for input filename, checks if it's valid
		String inputFileName = promptForFileName();

		// Opening the input and output file
		try
		{
			System.out.println("Opening " + inputFileName + "...");
			inputReader = new Scanner(new FileInputStream(inputFileName));

			System.out.println("Creating " + outputFileName + "...");
			outputWriter = new PrintWriter(new FileOutputStream(outputFileName));
		} catch (FileNotFoundException e)
		{
			System.out.println("Error. " + e.getMessage());
			isDeleteOutputFile = true;
		}

		// Reading the input file
		ArrayList<String> subDictionary = new ArrayList<String>();
		while (inputReader.hasNext())
		{

			processNextWord(inputReader, subDictionary);
		}

		// Printing the output file
		printArrayListToOutputFile(outputWriter, subDictionary);

		// 									== ENDING PROGRAM ==
		// If need to delete output file
		//isDeleteOutputFile = true; // DEBUG
		if (isDeleteOutputFile)
		{
			deleteFile(outputFileName);
			displayExitMessage(1);
		}
		else
		{
			// Displaying regular exit message
			displayExitMessage(0);
		}

		// Closing Scanners and PrintWriter
		if (inputReader != null)
		{
			inputReader.close();
		}
		if (outputWriter != null)
		{
			outputWriter.close();
		}
	} // End of Main

	/**
	 * Processes the new word, either add it to the dictionary or do nothing
	 *
	 * @param inputReader   is a Scanner object representing the inputStreamFile
	 * @param subDictionary is a ArrayList of Strings that represents the subDictionary
	 */
	public static void processNextWord(Scanner inputReader, ArrayList<String> subDictionary) {
		String nextWord = inputReader.next();
		nextWord = Word.clean(nextWord);

		if (nextWord != null)
		{
			// searchResult[0]: flag: 0 - the word doesn't exist, 1 - the word exists
			// searchResult[1]: if searchResult[0], searchResult[1] contains the index for the word to be appended at
			int[] searchResult = Word.search(subDictionary, nextWord);

			// If the word doesn't exist yet, append the nextWord at the index returned by searchResult[1]
			if (searchResult[0] == 0)
			{
				subDictionary.add(searchResult[1], nextWord);
			}
		}
	}

	/**
	 * Prints the ArrayList with a header when the starting character is different
	 *
	 * @param outputWriter  is a PrintWriter object for the outputStreamFile
	 * @param subDictionary is an ArrayList of String representing the words to be printed
	 */
	public static void printArrayListToOutputFile(PrintWriter outputWriter, ArrayList<String> subDictionary) {
		char previousFirstChar = 0;

		// Print intro message to outputFile (size of entries and all)
		outputWriter.println("The document produced this sub-dictionary, which includes " + subDictionary.size() + " entries.");

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
	 * Prompts for the filename and checks if the file exists, reprompt until file exists
	 *
	 * @return a String containing the name of the input file
	 */
	public static String promptForFileName() {
		Scanner keyIn        = new Scanner(System.in);
		boolean fileExists   = false;
		String  filename     = null;
		int     triesCounter = 1;

		do
		{

			if (triesCounter > 3)
			{
				System.out.println("Exceeded number of tries.");
				displayExitMessage(1);
				System.exit(0);
			}
			else if (triesCounter != 1)
			{
				System.out.println("\nTry again. Try #" + triesCounter + " (3 Maximum)");
			}

			// Prompting user
			System.out.print("Please enter the full name (with extension) of the file for which\n you wish to create a dictionary: ");
			filename = keyIn.nextLine();

			// Check if the file exists
			File inputFile = new File(filename);
			if (!inputFile.exists())
			{
				System.out.println("\nThe file doesn't exist. Check your input and verify that the file is in the correct directory.");
				triesCounter++;
			}
			else
			{
				fileExists = true;
			}
		}
		while (!fileExists);

		return filename;
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
				System.out.println("File Sub-Dictionary.txt has been created.");
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

