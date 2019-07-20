package model.structural.diagram.component.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.association.Association;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;

/**
 * <p>Class of Model <b>ComunicationUML</b>.</p>
 * <p>Class responsible for representing the <b>Comunication UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/07/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.component.base.ComponentUML
 * @see    model.structural.diagram.component.base.InterfaceUML
 */
public class ComunicationUML extends Association {
    private String category;
    
    /**
     * Default constructor method of Class.
     * @param componentUML Component UML.
     * @param interfaceUML Interface UML.
     */
    public ComunicationUML(ComponentUML componentUML, InterfaceUML interfaceUML) {
        this.source   = componentUML;
        this.target   = interfaceUML;
        this.type     = "comunication";
        this.category = "required";
    }

    @Override
    public ComponentUML getSource() {
        return (ComponentUML) this.source;
    }

    /**
     * Method responsible for setting the Source Component.
     * @param component Source Component.
     */
    public void setSource(ComponentUML component) {
        this.source = component;
    }

    @Override
    public InterfaceUML getTarget() {
        return (InterfaceUML) this.target;
    }

    /**
     * Method responsible for setting the Target Interface.
     * @param target Target Interface.
     */
    public void setTarget(InterfaceUML target) {
        this.target = target;
    }
    
    /**
     * Method responsible for returning the Category.
     * @return Category.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Method responsible for setting the Category.
     * @param category Category.
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    @Override
    public String getTitle() {
        return "";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleComunicationUML(" + this.category + ")";
    }
    
    /**
     * Method responsible for returning the Dashed Style.
     * @return Dashed Style.
     */
    private String getDashedStyle() {
        if (this.category.toLowerCase().trim().equals("required"))
            return "1";
        return "0";
    }
    
    /**
     * Method responsible for returning the End Arrow Style.
     * @return End Arrow Style.
     */
    private Object getEndArrowStyle() {
        if (this.category.toLowerCase().trim().equals("provided"))
            return mxConstants.ARROW_SPACING;
        return mxConstants.ARROW_OPEN;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   this.getDashedStyle());
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_ENDARROW,   this.getEndArrowStyle());
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
        return style;
    }
    
    @Override
    public int hashCode() {
        int    hash = 3;
               hash = 19 * hash + Objects.hashCode(this.source);
               hash = 19 * hash + Objects.hashCode(this.target);
        return hash;
    }
    
    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof ComunicationUML == false)
            return false;
        return this.source.equals(((ComunicationUML) objeto).getSource())
            && this.target.equals(((ComunicationUML) objeto).getTarget());
    }
}