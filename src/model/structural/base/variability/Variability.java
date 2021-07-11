package model.structural.base.variability;

import java.util.ArrayList;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Variability</b>.</p>
 * <p>Class responsible for representing <b>Variability</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    model.structural.base.Element
 * @see    model.structural.base.interfaces.Exportable
 */
public class Variability implements Exportable {
    private String  id;
    private String  name;
    private Element variationPoint;
    private String  constraint;
    private String  bindingTime;
    private Integer minimum;
    private Integer maximum;
    private List<Element> variants;
    
    /**
     * Default constructor method of Class.
     */
    public Variability() {
        name           = "Variability Name";
        variationPoint = null;
        constraint     = "Exclusive";
        bindingTime    = "DESIGN_TIME";
        variants       = new ArrayList<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param variationPoint Variation Point.
     */
    public Variability(Element variationPoint) {
        this();
        this.variationPoint = variationPoint;
    }

    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Variability(org.w3c.dom.Element element) {
        id          = element.getAttribute("id");
        name        = element.getAttribute("name");
        constraint  = element.getAttribute("constraint");
        bindingTime = element.getAttribute("bindingTime");
        minimum     = Integer.parseInt(element.getAttribute("min"));
        maximum     = Integer.parseInt(element.getAttribute("max"));
        variants    = new ArrayList<>();
    }

    /**
     * Method responsible for returning the Variability Id.
     * @return Variability Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for defining the Variability Id.
     * @param id Variability Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning the Variability Name.
     * @return Variability Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method responsible for defining the Variability Name.
     * @param name Variability Name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method responsible for returning the Variability Variation Point.
     * @return Variability Variation Point.
     */
    public Element getVariationPoint() {
        return variationPoint;
    }

    /**
     * Method responsible for defining the Variability Variation Point.
     * @param variationPoint Variability Variation Point.
     */
    public void setVariationPoint(Element variationPoint) {
        this.variationPoint = variationPoint;
    }

    /**
     * Method responsible for returning the Variability Constraint.
     * @return Variability Constraint.
     */
    public String getConstraint() {
        return constraint;
    }

    /**
     * Method responsible for defining the Variability Constraint.
     * @param constraint Variability Constraint.
     */
    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    /**
     * Method responsible for returning the Variability Binding Time.
     * @return Variability Binding Time.
     */
    public String getBindingTime() {
        return bindingTime;
    }

    /**
     * Method responsible for defining the Variability Binding Time.
     * @param bindingTime Variability Binding Time.
     */
    public void setBindingTime(String bindingTime) {
        this.bindingTime = bindingTime;
    }

    /**
     * Method responsible for returning the Variability Minimum.
     * @return Variability Minimum.
     */
    public Integer getMinimum() {
        return minimum;
    }

    /**
     * Method responsible for defining the Variability Minimum.
     * @param minimum Variability Minimum.
     */
    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    /**
     * Method responsible for returning the Variability Maximum.
     * @return Variability Maximum.
     */
    public Integer getMaximum() {
        return maximum;
    }

    /**
     * Method responsible for defining the Variability Maximum.
     * @param maximum Variability Maximum.
     */
    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }
    
    /**
     * Method responsible for returning the Variants.
     * @return Variants.
     */
    public List<Element> getVariants() {
        return variants;
    }
    
    /**
     * Method responsible for returning the Variants List.
     * @return Variants List.
     */
    public String getVariantsList() {
        if (variants.size() == 1)
            return "{" + variants.get(0).getName() + "}";
        String toReturn  = "{" + variants.get(0) + ", \n";
        for (int i = 1; i < variants.size() - 1; i++)
            toReturn += variants.get(i).getName() + ", \n";
               toReturn += variants.get(variants.size() - 1).getName() + "}";
        return toReturn;
    }
    
    /**
     * Method responsible for adding a Variant.
     * @param variant Variant.
     */
    public void addVariant(Element variant) {
        if (!variants.contains(variant))
             variants.add(variant);
    }
    
    /**
     * Method responsible for checking if an Element is Variant.
     * @param  element Element.
     * @return Element is Variant.
     */
    public boolean isVariant(Element element) {
        return variants.contains(element);
    }
    
    /**
     * Method responsible for checking if an Element is Variant.
     * @param  element Element Name.
     * @return Element is Variant.
     */
    public boolean isVariant(String element) {
        for (Element variant : variants) {
            if (variant.getName().equalsIgnoreCase(element))
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for removing a Variant.
     * @param variant Variant.
     */
    public void removeVariant(Element variant) {
        if (variants.contains(variant))
            variants.remove(variant);
    }
    
    /**
     * Method responsible for checking if is no Variants.
     * @return No Variants.
     */
    public boolean emptyVariants() {
        return variants.isEmpty();
    }
    
    /**
     * Method responsible for defining Variability Variants.
     * @param variants Variability Variants.
     */
    public void setVariants(List<Element> variants) {
        this.variants = variants;
    }
    
    /**
     * Method responsible for returning the Variability Icon.
     * @return Variability Icon.
     */
    public String getIcon() {
        return "icons/variability/variability.png";
    }
    
    /**
     * Method responsible for returning the Element Icon in a Variability.
     * @param  element Element.
     * @return Element Icon in a Variability.
     */
    public String getIcon(Element element) {
        if (getVariants().contains(element)) {
            if (getConstraint().equalsIgnoreCase("inclusive"))
                return "icons/variability/inclusive.png";
            if (getConstraint().equalsIgnoreCase("exclusive"))
                return "icons/variability/exclusive.png";
        }
        return element.getIcon();
    }
    
    /**
     * Method responsible for returning the Variability Summary.
     * @return Variability Summary.
     */
    public String getSummary() {
        return "Variability: " + getName();
    }
    
    /**
     * Method responsible for exporting the Variation Point String.
     * @return Variation Point String.
     */
    private String exportVariationPoint() {
        return " variationPoint=\"" + variationPoint.getId() + "\"";
    }
    
    /**
     * Method responsible for exporting the Variants String.
     * @return Variants String.
     */
    private String exportVariants() {
        String export  = "";
        for (Element variant : variants)
               export += "      <variant id=\"" + variant.getId() + "\"/>\n";
        return export;
    }
    
    @Override
    public String export() {
        String export  = "    <variability";
               export += " id=\""          + id          + "\"";
               export += " name=\""        + name        + "\"";
               export += exportVariationPoint();
               export += " constraint=\""  + constraint  + "\"";
               export += " bindingTime=\"" + bindingTime + "\"";
               export += " min=\""         + minimum     + "\"";
               export += " max=\""         + maximum     + "\"";
               export += ">\n";
               export += exportVariants();
               export += "    </variability>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return name;
    }
}