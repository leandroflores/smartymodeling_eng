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
                     .replaceAll("$", "")
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
     * Method responsible for returning a String right aligned.
     * @param  string String initial.
     * @param  size String size.
     * @return String right aligned.
     */
    public String toRight(String string, int size) {
        if (string.length() > size) 
            return string;
        return getSpaces(size - string.length()) + string;
    }
    
    /**
     * Method responsible for returning a String left aligned.
     * @param  string String initial.
     * @param  size String size.
     * @return String left aligned.
     */
    public String toLeft(String string, int size) {
        if (string.length() > size) 
            return string;
        return string + getSpaces(size - string.length());
    }
    
    /**
     * Method responsible for returning a String center aligned.
     * @param  string String initial.
     * @param  size String size.
     * @return String center aligned.
     */
    public String toCenter(String string, int size) {
        String gap = getSpaces((size - string.length()) / 2);
        if (string.length() > size) 
            return string;
        return gap + string + gap;
    }
    
    /**
     * Method responsible for returning the Frequency of a Character in a String.
     * @param  string String.
     * @param  character Character.
     * @return Frequency of Character in a String.
     */
    public Integer countChar(String string, char character) {
        Integer count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == character)
                count += 1;
        }
        return count;
    }
    
    /**
     * Method responsible for returning a String in reverse order.
     * @param  string String.
     * @return String in reverse order.
     */
    public String reverse(String string) {
        String reverse  = "";
        for (int i = string.length() - 1; i >= 0; i--)
               reverse += string.charAt(i);
        return reverse;
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
     * Method responsible for returning the Numbers of a String.
     * @param  string String.
     * @return Numbers of a String.
     */
    public String getNumbers(String string) {
        String numbers    = "0123456789";
        String newString  = "";
        for (int i = 0; i < string.length(); i++)
               newString += numbers.contains(Character.toString(string.charAt(i))) ? string.charAt(i) : "";
        return newString;
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
            case '??':
            case '??':
            case '??':
            case '??':
            case '??':
                return 'a';
            case '??':
                return 'c';
            case '??':
            case '??':
            case '??':
            case '??':
                return 'e';
            case '??':
            case '??':
            case '??':
            case '??':
                return 'i';
            case '??':
            case '??':
            case '??':
            case '??':
            case '??':
                return 'o';
            case '??':
            case '??':
            case '??':
            case '??':
                return 'u';
            case '??':
            case '??':
            case '??':
            case '??':
            case '??':
                return 'A';
            case '??':
                return 'C';
            case '??':
            case '??':
            case '??':
            case '??':
                return 'E';
            case '??':
            case '??':
            case '??':
            case '??':
                return 'I';
            case '??':
            case '??':
            case '??':
            case '??':
            case '??':
                return 'O';
            case '??':
            case '??':
            case '??':
            case '??':
                return 'U';
            default:
                return character;
        }
    }
    
    /**
     * Method responsible for returning a List of Longs.
     * @param  values String with values separated by space.
     * @return List of Longs.
     */
    public List<Long> createList(String values) {
        List<Long> list  = new ArrayList<>();
        String[]   array = values.split("\\ ");
        for (String current : array)
                   list.add(Long.parseLong(current.trim()));
        return     list;
    }
    
    /**
     * Method responsible for returning a List of Strings.
     * @param  values String with values separated by space.
     * @return List of Strings.
     */
    public List<String> stringToList(String values) {
        List<String> list  = new ArrayList<>();
        String[]     array = values.split("\\ ");
        for (String current : array)
                     list.add(current.trim());
        return       list;
    }
    
    /**
     * Method responsible for returning a String with List values.
     * @param  list List.
     * @return String with List values.
     */
    public String listToString(List<String> list) {
        String values = "";
        for (String current : list)
               values += current + "\n";
        return values;
    }
    
    /**
     * 
     * Method responsible for checking if a mask is valid.
     * @param  string String.
     * @param  mask   Mask.
     * @return String matches Mask.
     */
    public boolean checkMask(String string, String mask) {
        for (int i = 0; i < string.length(); i++) {
            if (!mask.contains(Character.toString(string.charAt(i)))) 
                return false;
        }
        return true;
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