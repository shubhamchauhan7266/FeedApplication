package com.feedapplication.utills;

/**
 * Class is used to provide method for String.
 *
 * @author Shubham Chauhan
 */
public class StringUtils {

    /**
     * Method is used to check the given string is null or empty.
     *
     * @param pStr pStr
     * @return true if string is null/empty otherwise false.
     */
    public static boolean isNullOrEmpty(String pStr) {
        return pStr == null || pStr.trim().length() == 0 || pStr.trim().equalsIgnoreCase("null");
    }
}
