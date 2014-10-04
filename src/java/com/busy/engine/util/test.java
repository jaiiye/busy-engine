
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Sourena Nasiriamini
 */
public class test
{
    public static void outputSumOfKeyCounts(String fileName)
    {
        Map<String, Integer> keyCounts = new HashMap<>();

        //try with resources automatically closes the "reader" resource
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName)))
        {
            for (String s = reader.readLine(); s != null; s = reader.readLine())
            {
                String[] result = s.split(",");
                String key = result[0];
                int keyCount = Integer.parseInt(result[1]);

                int count = keyCounts.containsKey(key) ? keyCounts.get(key) : 0;
                keyCounts.put(key, count + keyCount);
            }
            for (Entry e : keyCounts.entrySet())
            {
                System.out.print("The total for " + e.getKey() + " is " + e.getValue() + ". ");
            }
        }
        catch (IOException | NumberFormatException e)
        {
            System.out.print("Error: " + e.getMessage() + ". ");
        }
    }

    public static boolean isPalindrome(String stringToTest)
    {
        StringBuilder result = new StringBuilder();

        //cleanup the string
        for (char c : stringToTest.toCharArray())
        {
            if (Character.isLetterOrDigit(c))
            {
                result.append(c);
            }
        }

        //test for palindrome
        String cleanString = result.toString().toLowerCase();
        int n = cleanString.length();

        for (int i = 0; i < (n / 2) + 1; ++i)
        {
            if (cleanString.charAt(i) != cleanString.charAt(n - i - 1))
            {
                System.out.println("String is NOT a palindrome.");
                return false;
            }
        }

        System.out.println("String is a palindrome.");
        return true;
    }

    public static void main(String[] args)
    {
        // Question 1 test
        //file contains:
        //John,2
        //Jane,3
        //John,4
        //Jane,5    
        outputSumOfKeyCounts("E:/input.txt");
        System.out.println("");

        //palindrome test
        isPalindrome("Madam, I'm Adam.");
        isPalindrome("A Toyota’s a Toyota.");
        isPalindrome("A Honda’s a Toyota.");
    }
}
