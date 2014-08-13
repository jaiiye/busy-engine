package com.busy.engine.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Sourena
 * 
 * The purpose of this class is to make a 104 char hash from the input string and make 
 *     an 18 char key code from it using the index values specified by the indices array
 * 
 */
public class ActivationKey
{
    private static int[] indices = {12,74,102,30,2,51,43,62,88,42,63,18,85,27,53,102,15,99};
    
    public static String makeKey(String input)
    {
        String serialNumberEncoded = null;
        try
        {
            serialNumberEncoded = calculateHash(input, "MD2") + calculateHash(input, "MD5") + calculateHash(input, "SHA1");
        }
        catch (NoSuchAlgorithmException ex)
        {
            ex.printStackTrace();
        }

        String key = "";
        
        for(int index : indices)
        {
            key += serialNumberEncoded.charAt(index);
        }
        
        return key;
    }
    
    public static boolean validateKey(String input, String submittedKey)
    {       
        if (makeKey(input).equals(submittedKey))
        {
            return true;
        }
        else
        {
            return false; 
        }
    }
    
    private static String calculateHash(String stringInput, String algorithmName) throws NoSuchAlgorithmException
    {
        String hexMessageEncode = "";
        
        byte[] buffer = stringInput.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance(algorithmName);
        messageDigest.update(buffer);
        
        byte[] messageDigestBytes = messageDigest.digest();
        
        for (int index = 0; index < messageDigestBytes.length; index++)
        {
            int countEncode = messageDigestBytes[index] & 0xff;
            if (Integer.toHexString(countEncode).length() == 1)
            {
                hexMessageEncode = hexMessageEncode + "0";
            }
            hexMessageEncode = hexMessageEncode + Integer.toHexString(countEncode);
        }
        return hexMessageEncode;
    }
}
