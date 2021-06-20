package model.structural.base.evaluation;

import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Metric</b>.</p>
 * <p>Class responsible for representing the <b>Metric</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    model.structural.base.interfaces.Exportable
 */
public class Metric implements Exportable {
    private String id;
    private String name;
    private String label;
    private String description;
    private String target;
    private String operation;
    
     /**
     * Default constructor method of Class.
     */
    public Metric() {}
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Metric(org.w3c.dom.Element element) {
        id          = element.getAttribute("id");
        name        = element.getAttribute("name");
        label       = element.getAttribute("label");
        target      = element.getAttribute("target");
        description = "";
        operation   = "";
    }
    
    /**
     * Method responsible for returning the Metric Id.
     * @return Metric Id.
     */
    public String getId() {
        return id;
    }

    /**
     * Method responsible for defining the Metric Id.
     * @param id Metric Id.
     */
    public void setId(String id) {
        this.id = (this.id == null || this.id.trim().equals("")) ? id : this.id;
    }

    /**
     * Method responsible for returning the Metric Name.
     * @return Metric Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method responsible for defining the Metric Name.
     * @param name Metric Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method responsible for returning the Metric Label.
     * @return Metric Label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Method responsible for defining the Metric Label.
     * @param label Metric Label.
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * Method responsible for returning the Metric Description.
     * @return Metric Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method responsible for defining the Metric Description.
     * @param description Metric Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method responsible for returning the Metric Target.
     * @return Metric Target.
     */
    public String getTarget() {
        return target;
    }

    /**
     * Method responsible for defining the Metric Target.
     * @param target Metric Target.
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * Method responsible for returning the Metric Operation.
     * @return Metric Operation.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Method responsible for defining the Metric Operation.
     * @param operation Metric Operation.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    /**
     * Method responsible for returning the Metric Icon.
     * @return Metric Icon.
     */
    public String getIcon() {
        return "icons/menu/evaluation/metric.png";
    }
    
    @Override
    public String export() {
        String export  = "  <metric";
               export += " id=\""     + id     + "\"";
               export += " name=\""   + name   + "\"";
               export += " label=\""  + label  + "\"";
               export += " target=\"" + target + "\">\n";
               export += "    <description>" + description + "</description>\n";
               export += "    <operation>"   + operation   + "</operation>\n";
               export += "  </metric>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return name;
    }
}