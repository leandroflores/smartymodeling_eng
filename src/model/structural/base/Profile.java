package model.structural.base;

import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Profile</b>.</p>
 * <p>Class responsible for representing the <b>Profile</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/05/2019
 * @see    model.structural.base.Stereotype
 * @see    model.structural.base.interfaces.Exportable
 */
public class Profile implements Exportable {
    private Stereotype mandatory;
    private Stereotype optional;
    private Stereotype variationPoint;
    private Stereotype inclusive;
    private Stereotype exclusive;
    
    /**
     * Method responsible for returning Mandatory Stereotype.
     * @return Mandatory Stereotype.
     */
    public Stereotype getMandatory() {
        return this.mandatory;
    }

    /**
     * Method responsible for setting Mandatory Stereotype.
     * @param mandatory Mandatory Stereotype.
     */
    public void setMandatory(Stereotype mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Method responsible for returning Optional Stereotype.
     * @return Optional Stereotype.
     */
    public Stereotype getOptional() {
        return this.optional;
    }

    /**
     * Method responsible for setting Optional Stereotype.
     * @param optional Optional Stereotype.
     */
    public void setOptional(Stereotype optional) {
        this.optional = optional;
    }

    /**
     * Method responsible for returning Variation Point Stereotype.
     * @return Variation Point Stereotype.
     */
    public Stereotype getVariationPoint() {
        return this.variationPoint;
    }
    
    /**
     * Method responsible for setting Variation Point Stereotype.
     * @param variationPoint Variation Point Stereotype.
     */
    public void setVariationPoint(Stereotype variationPoint) {
        this.variationPoint = variationPoint;
    }

    /**
     * Method responsible for returning Inclusive Stereotype.
     * @return Inclusive Stereotype.
     */
    public Stereotype getInclusive() {
        return this.inclusive;
    }
    
    /**
     * Method responsible for setting Inclusive Stereotype.
     * @param inclusive Inclusive Stereotype.
     */
    public void setInclusive(Stereotype inclusive) {
        this.inclusive = inclusive;
    }

    /**
     * Method responsible for returning Exclusive Stereotype.
     * @return Exclusive Stereotype.
     */
    public Stereotype getExclusive() {
        return this.exclusive;
    }
    
    /**
     * Method responsible for setting Exclusive Stereotype.
     * @param exclusive Exclusive Stereotype.
     */
    public void setExclusive(Stereotype exclusive) {
        this.exclusive = exclusive;
    }

    @Override
    public String export() {
        String export  = "  <profile>\n";
               export += "    <mandatory>"      + this.mandatory      + "</mandatory>\n";
               export += "    <optional>"       + this.optional       + "</optional>\n";
               export += "    <variationPoint>" + this.variationPoint + "</variationPoint>\n";
               export += "    <inclusive>"      + this.inclusive      + "</inclusive>\n";
               export += "    <exclusive>"      + this.exclusive      + "</exclusive>\n";
               export += "  </profile>\n";
        return export;
    }
    
    @Override
    public String toString() {
        String perfil =  "Mandatory       = " + this.mandatory      + "\n";
               perfil += "Optional        = " + this.optional       + "\n";
               perfil += "Variation Point = " + this.variationPoint + "\n";
               perfil += "Inclusive       = " + this.inclusive      + "\n";
               perfil += "Exclusive       = " + this.exclusive      + "\n";
        return perfil;
    }
}