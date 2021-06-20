package model.structural.base;

import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Profile</b>.</p>
 * <p>Class responsible for representing the <b>Profile</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-22
 * @see    model.structural.base.Stereotype
 * @see    model.structural.base.interfaces.Exportable
 */
public class Profile implements Exportable {
    private Stereotype mandatory;
    private Stereotype optional;
    private Stereotype variationPoint;
    private Stereotype inclusive;
    private Stereotype exclusive;
    private Stereotype requires;
    private Stereotype mutex;
    
    /**
     * Method responsible for returning the Mandatory Stereotype.
     * @return Mandatory Stereotype.
     */
    public Stereotype getMandatory() {
        return mandatory;
    }

    /**
     * Method responsible for setting the Mandatory Stereotype.
     * @param mandatory Mandatory Stereotype.
     */
    public void setMandatory(Stereotype mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Method responsible for returning the Optional Stereotype.
     * @return Optional Stereotype.
     */
    public Stereotype getOptional() {
        return optional;
    }

    /**
     * Method responsible for setting the Optional Stereotype.
     * @param optional Optional Stereotype.
     */
    public void setOptional(Stereotype optional) {
        this.optional = optional;
    }

    /**
     * Method responsible for returning the Variation Point Stereotype.
     * @return Variation Point Stereotype.
     */
    public Stereotype getVariationPoint() {
        return variationPoint;
    }
    
    /**
     * Method responsible for setting the Variation Point Stereotype.
     * @param variationPoint Variation Point Stereotype.
     */
    public void setVariationPoint(Stereotype variationPoint) {
        this.variationPoint = variationPoint;
    }

    /**
     * Method responsible for returning the Inclusive Stereotype.
     * @return Inclusive Stereotype.
     */
    public Stereotype getInclusive() {
        return inclusive;
    }
    
    /**
     * Method responsible for setting the Inclusive Stereotype.
     * @param inclusive Inclusive Stereotype.
     */
    public void setInclusive(Stereotype inclusive) {
        this.inclusive = inclusive;
    }

    /**
     * Method responsible for returning the Exclusive Stereotype.
     * @return Exclusive Stereotype.
     */
    public Stereotype getExclusive() {
        return exclusive;
    }
    
    /**
     * Method responsible for setting the Exclusive Stereotype.
     * @param exclusive Exclusive Stereotype.
     */
    public void setExclusive(Stereotype exclusive) {
        this.exclusive = exclusive;
    }

    /**
     * Method responsible for returning the Requires Stereotype.
     * @return Requires Stereotype.
     */
    public Stereotype getRequires() {
        return requires;
    }

    /**
     * Method responsible for setting the Requires Stereotype.
     * @param requires Requires Stereotype.
     */
    public void setRequires(Stereotype requires) {
        this.requires = requires;
    }

    /**
     * Method responsible for returning the Mutex Stereotype.
     * @return Mutex Stereotype.
     */
    public Stereotype getMutex() {
        return mutex;
    }

    /**
     * Method responsible for setting the Mutex Stereotype.
     * @param mutex Mutex Stereotype.
     */
    public void setMutex(Stereotype mutex) {
        this.mutex = mutex;
    }

    @Override
    public String export() {
        String export  = "  <profile";
               export += " mandatory=\""      + mandatory.getId()      + "\"";
               export += " optional=\""       + optional.getId()       + "\"";
               export += " variationPoint=\"" + variationPoint.getId() + "\"";
               export += " inclusive=\""      + inclusive.getId()      + "\"";
               export += " exclusive=\""      + exclusive.getId()      + "\"";
               export += " requires=\""       + requires.getId()       + "\"";
               export += " mutex=\""          + mutex.getId()          + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        String profile  = "Mandatory       = " + mandatory      + "\n";
               profile += "Optional        = " + optional       + "\n";
               profile += "Variation Point = " + variationPoint + "\n";
               profile += "Inclusive       = " + inclusive      + "\n";
               profile += "Exclusive       = " + exclusive      + "\n";
               profile += "Requires        = " + requires       + "\n";
               profile += "Mutex           = " + mutex          + "\n";
        return profile;
    }
}