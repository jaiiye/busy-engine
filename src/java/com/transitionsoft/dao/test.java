package com.transitionsoft.dao;

/**
 *
 * @author Sourena
 */
public class test
{
    public static void main(String[] args)
    {
        System.out.println("Hello " + generateProperty("SiteResourceTypeId"));
        
    }
    
    public static String generateProperty(String columnName)
    {
        String propertyString = "";
        for(int i = 0; i < columnName.length(); i++)
        {
            String currentChar = columnName.charAt(i) + "";
            if(Character.isUpperCase(currentChar.charAt(0)) && i != 0)
            {
                propertyString += "_" + currentChar.toUpperCase();
            }
            else
            {                
                propertyString += currentChar.toUpperCase();
            }
        }
        return propertyString;
    }
}
