package com.example.mydiary.utils;

import java.util.List;

public class StringUtils {

    /**
     * Return true if the string is empty or null
     * @param aStr
     * @return boolean
     */
    public static boolean isEmpty(String aStr) {
        if ((aStr == null) ||
                (aStr.trim().length() == 0))
            return (true);
        return (false);
    }

    /**
     * Return string with first letter capitilized
     * @param aStr
     * @return String
     */
    public  static String capitalizeFirstLetter(String aStr){
        if (aStr == null || aStr.length() == 0) {
            return aStr;
        }
        return aStr.substring(0, 1).toUpperCase() + aStr.substring(1).toLowerCase();
    }

    /**
     * Convert a list of Strings into a single string object with each value separated
     * by a comma
     * @param stringArray
     * @return String
     */
    public static String toCommaDelimitedString(List<String> stringArray) {
        return stringArray.toString().trim().replace("[", "").replace("]", "");
    }
}
