package model.structural.base.evaluation;

import funct.FunctDate;
import model.structural.base.interfaces.Exportable;

/**
 * <p>Class of Model <b>Measure</b>.</p>
 * <p>Class responsible for representing the <b>Measure</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  02/09/2019
 * @see    model.structural.base.interfaces.Exportable
 */
public class Measure implements Exportable {
    private String id;
    private String name;
    private String date;
    private Metric metric;
    private Double value;
    
     /**
     * Default constructor method of Class.
     */
    public Measure() {
        this.id     = "";
        this.name   = "NewMeasure";
        this.date   = new FunctDate().getCurrentFormattedDate();
        this.metric = null;
        this.value  = 0.0d;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public Measure(org.w3c.dom.Element element) {
        this.id     = element.getAttribute("id");
        this.name   = element.getAttribute("name");
        this.date   = new FunctDate().getCurrentFormattedDate();
        this.metric = null;
        this.value  = 0.0d;
    }
    
    /**
     * Method responsible for returning the Measure Id.
     * @return Measure Id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Method responsible for defining the Measure Id.
     * @param id Measure Id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method responsible for returning the Measure Name.
     * @return Measure Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for defining the Measure Name.
     * @param name Measure Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method responsible for returning the Measure Date.
     * @return Measure Date.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Method responsible for setting the Measure Date.
     * @param date Measure Date.
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * Method responsible for returning the Measure Metric.
     * @return Measure Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }

    /**
     * Method responsible for setting the Measure Metric.
     * @param metric Measure Metric.
     */
    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    /**
     * Method responsible for returning the Measure Value.
     * @return Measure Value.
     */
    public Double getValue() {
        return this.value;
    }

    /**
     * Method responsible for setting the Measure Value.
     * @param value Measure Value.
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * Method responsible for returning the Measure Icon.
     * @return Measure Icon.
     */
    public String getIcon() {
        return "src/images/icons/evaluation/metric.png";
    }
    
    @Override
    public String export() {
        String export  = "  <measure";
               export += " id=\""     + this.id             + "\"";
               export += " name=\""   + this.name           + "\"";
               export += " date=\""   + this.date           + "\"";
               export += " metric=\"" + this.metric.getId() + "\"";
               export += " value=\""  + this.value          + "\">\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }
}