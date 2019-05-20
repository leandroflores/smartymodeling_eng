package model.structural;

/**
 * <p>Class of Model <b>Association</b>.</p>
 * <p>Class responsible for representing <b>Association</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.Element
 * @see    model.structural.Exportable
 * @see    model.structural.Modelable
 */
public abstract class Association implements Exportable, Modelable {
    protected Element source;
    protected Element target;
    protected String  type;
    
    /**
     * Method responsible for returning the Association Id.
     * @return Association Id.
     */
    public String getId() {
        return this.type.toUpperCase().trim() + "#" + this.source.getId() + "-" + this.target.getId();
    }
    
    /**
     * Method responsible for checking if a Element is Source.
     * @param  element Element.
     * @return Element is Source.
     */
    public boolean isSource(Element element) {
        return this.source.equals(element);
    }
    
    /**
     * Method responsible for checking if a Element is Target.
     * @param  element Element.
     * @return Element is Target.
     */
    public boolean isTarget(Element element) {
        return this.target.equals(element);
    }
    
    /**
     * Method responsible for checking if a Element is part of Association.
     * @param  element Element.
     * @return Element is part of Association.
     */
    public boolean contains(Element element) {
        return    this.isSource(element)
               || this.isTarget(element);
    }
            
    /**
     * Method responsible for returning Source Element.
     * @return Source Element.
     */
    public Element getSource() {
        return this.source;
    }
    
    /**
     * Method responsible for defining Source Element.
     * @param source Source Element.
     */
    public void setSource(Element source) {
        this.source = source;
    }
    
    /**
     * Method responsible for returning Target Element.
     * @return Target Element.
     */
    public Element getTarget() {
        return this.target;
    }
    
    /**
     * Method responsible for defining Target Element.
     * @param target Target Element.
     */
    public void setTarget(Element target) {
        this.target = target;
    }
    
    /**
     * Method responsible for returning Association Type.
     * @return Association Type.
     */
    public String getType() {
        return this.type;
    }
    
    @Override
    public String export() {
        String export  = "    <"      + this.type;
               export += " source=\"" + this.source.getId() + "\"";
               export += " target=\"" + this.target.getId() + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.source.getName() + " - " + this.target.getName();
    }
}