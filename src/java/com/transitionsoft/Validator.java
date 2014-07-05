package com.transitionsoft;

import java.util.regex.*;        

public class Validator 
{
    // method validate compares user input with the pattern
    // if match return "success" else return false    
    public static boolean validate(String expression, String input)
    {
        if(input == null)
        {
            return false;
        }
        Pattern pat = Pattern.compile( expression );
        return pat.matcher(input).matches();
    }
}