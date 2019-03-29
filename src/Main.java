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

		// Displaying welcome message
		displayWelcomeMessage();

		// Prompt User for input filename, checks if it's valid
		String inputFilneName = promptForFileName();


		// == ENDING PROGRAM ==
		// Displaying exit message
		displayExitMessage(0);
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

