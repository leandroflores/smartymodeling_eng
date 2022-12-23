package funct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Class of Functions <b>FunctDate</b>.</p>
 * <p>Class responsible for operations involving <b>Dates</b> and <b>Hours</b>.</p>
 * @author Leandro
 * @since  2019-01-14
 * @see    java.util.Date
 */
public class FunctDate {
    
    /**
     * Method responsible for create a new Date in "dd/MM/yyyy" format.
     * @param  string String in "dd/MM/yyyy" format.
     * @return New Date.
     */
    public Date createDate(String string) {
        try {
            return new Date(new SimpleDateFormat("dd/MM/yyyy").parse(string).getTime());
        } catch (ParseException exception) {}
        return null;
    }
    
    /**
     * Method responsible for returning a System Current Date.
     * @return System Current Date.
     */
    public Date getCurrentDate() {
        return new Date();
    }
    
    /**
     * Method responsible for returning the System Current US Formatted Date.
     * @return System Current US Formatted Date.
     */
    public String getCurrentUSFormattedDate() {
        return getFormattedUSDate(getCurrentDate());
    }
    
    /**
     * Method responsible for returning a String by Format and Date.
     * @param  format Format.
     * @param  date Date.
     * @return String with a Date in a Format.
     */
    public String getFormattedDate(String format, Date date) {
        if (date != null)
            return new SimpleDateFormat(format).format(date);
        return "";
    }
    
    /**
     * Method responsible for returning a String with a Date in "dd/MM/yyyy" format.
     * @param  date Date.
     * @return String with a Date formatted.
     */
    public String getFormattedDate(Date date) {
        return getFormattedDate("dd/MM/yyyy", date);
    }
    
    /**
     * Method responsible for returning a String with a Date in "yyyy-MM-dd" format.
     * @param  date Date.
     * @return String with a US Date formatted.
     */
    public String getFormattedUSDate(Date date) {
        return getFormattedDate("yyyy-MM-dd", date);
    }
}