package funct;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class of Functions <b>FunctString</b>.</p>
 * <p>Class responsible for operations involving <b>Strings</b>.</p>
 * @author Leandro
 * @since  2019-01-14
 */
public class FunctString {
    
    /**
     * Method responsible for returning the New String.
     * @param  string Input String.
     * @return New String.
     */
    public String getString(String string) {
        return string.replaceAll("<", "")
                     .replaceAll(">", "")
                     .replaceAll("#", "")
                     .replaceAll("\\+", "")
                     .replaceAll("-", "")
                     .replaceAll("\\*", "")
                     .replaceAll("/", "")
                     .replaceAll("\\.", "")
                     .replaceAll("%", "")
                     .replaceAll("\\$", "")
                     .replaceAll("!", "")
                     .replaceAll("\\?", "").trim();
    }
    
    /**
     * Method responsible for returning a String containing a specifical Character.
     * @param  character Character.
     * @param  size String size.
     * @return String.
     */
    public String getString(char character, int size) {
        String string  = "";
        for (int i = 0; i < size; ++i)
               string += character;
        return string;
    }
    
    /**
     * Method responsible for returning a String containing spaces.
     * @param  size String Size.
     * @return String containing spaces.
     */
    public String getSpaces(int size) {
        return getString(' ', size);
    }
    
    /**
     * Method responsible for returning a String with the Upper First Character.
     * @param  string String.
     * @return String with the Upper First Character.
     */
    public String initUpperCase(String string) {
        if (string.length() == 0) 
            return "";
        if (string.length() == 1) 
            return string.toUpperCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
    
    /**
     * Method responsible for returning a String with the Upper Firsts Characters.
     * @param  string String.
     * @return String with Upper Firsts Characters.
     */
    public String getInitUpperCase(String string) {
        String   initUpper = "";
        String[] wordsList = string.split(" ");
        for (int i = 0; i < wordsList.length; ++i)
                 initUpper += initUpperCase(wordsList[i]) + " ";
        return   initUpper.trim();
    }
    
    /**
     * Method responsible for returning a String without special characters.
     * @param  string String.
     * @return String without special characters.
     */
    public String removeSpecialChars(String string) {
        String newString  = "";
        for (int i = 0; i < string.length(); i++)
               newString += replaceChar(string.charAt(i));
        return newString;
    }
    
    /**
     * Method responsible for returning the character of a special character.
     * @param  character Special character.
     * @return Character.
     */
    public char replaceChar(char character) {
        switch(character) {
            case 'á':
            case 'à':
            case 'â':
            case 'ã':
            case 'ä':
                return 'a';
            case 'ç':
                return 'c';
            case 'é':
            case 'è':
            case 'ê':
            case 'ë':
                return 'e';
            case 'í':
            case 'ì':
            case 'î':
            case 'ï':
                return 'i';
            case 'ó':
            case 'ò':
            case 'ô':
            case 'õ':
            case 'ö':
                return 'o';
            case 'ú':
            case 'ù':
            case 'û':
            case 'ü':
                return 'u';
            case 'Á':
            case 'À':
            case 'Â':
            case 'Ã':
            case 'Ä':
                return 'A';
            case 'Ç':
                return 'C';
            case 'É':
            case 'È':
            case 'Ê':
            case 'Ë':
                return 'E';
            case 'Í':
            case 'Ì':
            case 'Î':
            case 'Ï':
                return 'I';
            case 'Ó':
            case 'Ò':
            case 'Ô':
            case 'Õ':
            case 'Ö':
                return 'O';
            case 'Ú':
            case 'Ù':
            case 'Û':
            case 'Ü':
                return 'U';
            default:
                return character;
        }
    }
    
    /**
     * Method responsible for returning a String encrypted by the MD5 algorithm.
     * @param  string String.
     * @return Encrypted String.
     */
    public String md5(String string) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                          messageDigest.update(string.getBytes(), 0, string.length());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        }catch (NoSuchAlgorithmException exception) {}
        return null;
    }
}