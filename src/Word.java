public class Word {

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

	public static boolean isForbiddenChar(char charToCheck) {
		boolean isLowerAlpha = charToCheck >= 'a' && charToCheck <= 'z';
		boolean isUpperAlpha = charToCheck >= 'A' && charToCheck <= 'Z';

		return !(isLowerAlpha || isUpperAlpha);
	}
}
