# Sub-Dictionary Creator (COMP 249 Assignment 4 - Part 1)

## Assignment Description

This program will accept any text file as input, and creates a sub-dictionary that includes all the words found in that input file based on some rules.

## Requirements

* The program should allow the user to enter the name of the input file at run-time
* The program must handle input files with zero or more words
* The program shall handle a specific limited set of other characters / punctuation that are used in a specific and predetermined manner, mainly: ? : ' , = ; ! . digits and single characters
* The program must record only once each words
* The words must be recorded only in UPPERCASE
* The words cannot be recorded with any of the punctuation
* No numbers or words with digits may be recorded
* No single characters except "a" and "i" can be recorded
* The dictionary must record words in sorted alphabetic order under a header that indicates the first letter of the word
* The dictionary must have an initial line indicating its entries size based on the given input file
* MC^2 must be kept in the text and the output to honor Albert Einstein
* Some restrictions:
    * Must use ArrayList class to implement what is needed
        * When reading the input file, all data must be stored in one, or more, Array List objects before finally being stored in the output file.
    * Only use these Java packages:
        * import java.util.ArrayList;
        * import java.util.Scanner;
        * import java.io.PrintWriter;
        * import java.io.FileOutputStream;
        * import java.io.FileInputStream;
        * import java.io.FileNotFoundException;
