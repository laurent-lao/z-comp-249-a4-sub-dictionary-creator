import java.io.*;
import java.util.Scanner;

/**
 * Laurent's fileManipulation Helper class
 * Handles Opening, Scanners and PrintWriter with filename
 * * fileManip() prompts user for both input and output filename
 * * fileManip(String) prompts user for input but allows to specify output file
 * *
 */
public class FileManip {

	boolean showDebug = false;            			// Manually toggle to true for debug messages
	private Scanner     inputReader    = null;
	private PrintWriter outputWriter   = null;
	private String      inputFileName  = null;
	private String      outputFileName = null;

	/**
	 * Default constructor
	 */
	public FileManip() {
		if (showDebug)
		{
			System.out.println("fileManip default constructor has been ran." +
					"\ninputReader and outputWriter must be initialized manually." +
					"\nUse: initializeReader(String filename) and initializeWriter(String filename)");
		}
	}

	public FileManip(String outputFileName) {
		inputFileName = promptForFileName(true);
		this.outputFileName = outputFileName;
		initializeScannerAndWriter(inputFileName, this.outputFileName);
	}

	/**
	 * Initializes the Scanner and Writer
	 */
	public void initializeScannerAndWriter(String inputFileName, String outputFileName) {
		// Initializes Scanner and Writer
		if (inputFileName != null && outputFileName != null)
		{
			// If unsuccessful, quit program
			if (!initializeReader(inputFileName))
			{
				System.out.println("End of program");
				System.exit(0);
			}

			// If unsuccessful, quit program
			if (!initializeWriter(outputFileName))
			{
				System.out.println("End of program");
				System.exit(0);
			}
		}
	}

	/**
	 * Closes the Scanner and Writers
	 *
	 * @return
	 */
	public boolean closeScannerAndWriter() {
		if (inputReader != null && outputFileName != null)
		{
			System.out.println("Closing Scanner and PrintWriter");
			inputReader.close();
			outputWriter.close();
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Prompts for the filename and checks if the file exists, reprompt until file exists
	 *
	 * @return a String containing the name of the input file
	 */
	public String promptForFileName(boolean checkIfExists) {

		Scanner keyIn        = new Scanner(System.in);
		boolean inputisOK    = false;
		String  filename     = null;
		int     triesCounter = 1;

		do
		{

			if (triesCounter > 3)
			{
				System.out.println("Exceeded number of tries.");
				System.out.println("Goodbye.");
				System.exit(0);
			}
			else if (triesCounter != 1)
			{
				System.out.println("\nTry again. Try #" + triesCounter + " (3 Maximum)");
			}

			// Prompting user
			System.out.print("Please enter the full name (with extension) of the file for which\n you wish to create a dictionary: ");
			filename = keyIn.nextLine();

			if (!filename.contains("."))
			{
				inputisOK = false;
				System.out.println("\nPlease enter a file extension. Try again.");
				triesCounter++;
			}
			else if (checkIfExists)
			{
				// Check if the file exists
				File inputFile = new File(filename);
				if (!inputFile.exists())
				{
					System.out.println("\nThe file doesn't exist. Check your input and verify that the file is in the correct directory.");
					triesCounter++;
				}
				else
				{
					inputisOK = true;
				}
			}

		}
		while (!inputisOK);

		return filename;
	}

	/**
	 * Init Reader
	 *
	 * @param filename String representing the file to be read
	 *
	 * @return boolean value if successfully ran
	 */
	public boolean initializeReader(String filename) {
		try
		{
			System.out.println("Opening Scanner...");
			this.inputReader = new Scanner(new FileInputStream(filename));
			System.out.println("Successfully opened Scanner.");
			return true;
		} catch (FileNotFoundException e)
		{
			System.out.println("Error opening Scanner using " + filename + ": \n" + e.getMessage());
			return false;
		}
	}

	/**
	 * Init Writer
	 *
	 * @param filename String representing the file to be written
	 *
	 * @return boolean value if successfully ran
	 */
	public boolean initializeWriter(String filename) {
		try
		{
			System.out.println("Opening PrintWriter...");
			this.outputWriter = new PrintWriter(new FileOutputStream(filename));
			System.out.println("Successfully opened PrintWriter.");
			return true;
		} catch (FileNotFoundException e)
		{
			System.out.println("Error opening PrintWriter using " + filename + ": \n" + e.getMessage());
			return false;
		}
	}

	/**
	 * Getter for Scanner
	 *
	 * @return gets InputReader Scanner
	 */
	public Scanner getInputReader() {
		return inputReader;
	}

	/**
	 * Getter for PrintWriter
	 *
	 * @return gets OutputWriter PrintWriter
	 */
	public PrintWriter getOutputWriter() {
		return outputWriter;
	}

	/**
	 * Getter for inputfileName
	 *
	 * @return String for inputFileName
	 */
	public String getInputFileName() {
		return inputFileName;
	}

	/**
	 * Getter for outputfileName
	 *
	 * @return String for outputFileName
	 */
	public String getOutputFileName() {
		return outputFileName;
	}

	/**
	 * Deletes the outputFile
	 *
	 * @return boolean if successfully deleted
	 */
	public boolean deleteOutputFile() {
		File outputFile = new File(outputFileName);
		if (outputFile.exists())
		{
			outputFile.delete();
			return true;
		}
		else
		{
			return false;
		}
	}
}