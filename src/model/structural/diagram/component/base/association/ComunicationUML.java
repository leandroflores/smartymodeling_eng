package model.structural.diagram.component.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
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
        this.category = "provided";
        this.type     = "comunication";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param componentUML Component UML.
     * @param interfaceUML Interface UML.
     * @param category Category.
     */
    public ComunicationUML(ComponentUML componentUML, InterfaceUML interfaceUML, String category) {
        this.source   = componentUML;
        this.target   = interfaceUML;
        this.category = category;
        this.type     = "comunication";
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
     * Method responsible for returning the End Arrow Style.
     * @return End Arrow Style.
     */
    private Object getEndArrowStyle() {
        if (this.category.toLowerCase().trim().equals("provided"))
            return mxConstants.ARROW_SPACING;
        return mxConstants.ARROW_OVAL;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_ENDARROW,   this.getEndArrowStyle());
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
        return style;
    }
    
    @Override
    protected String exportHeader() {
        String export  = "    <"         + this.type;
               export += " id=\""        + this.id.trim()       + "\"";
               export += " component=\"" + this.source.getId()  + "\"";
               export += " interface=\"" + this.target.getId()  + "\"";
               export += " category=\""  + this.category.trim() + "\"";
               export += ">\n";
        return export;
    }
}