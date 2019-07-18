package model.structural.diagram.classes.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.association.Association;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;

/**
 * <p>Class of Model <b>RealizationUML</b>.</p>
 * <p>Class responsible for representing <b>Realization UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    model.structural.diagram.classes.base.InterfaceUML
 */
public class RealizationUML extends Association {
    
    /**
     * Default constructor method of Class.
     * @param class_ Classe UML.
     * @param interface_ Interface UML.
     */
    public RealizationUML(ClassUML class_, InterfaceUML interface_) {
        this.source = class_;
        this.target = interface_;
        this.type   = "realization";
    }

    @Override
    public ClassUML getSource() {
        return (ClassUML) this.source;
    }

    /**
     * Method responsible for setting the Source.
     * @param source Source.
     */
    public void setSource(ClassUML source) {
        this.source = source;
    }

    @Override
    public InterfaceUML getTarget() {
        return (InterfaceUML) this.target;
    }

    /**
     * Method responsible for setting the Target.
     * @param target Target.
     */
    public void setTarget(InterfaceUML target) {
        this.target = target;
    }
    
    @Override
    public String getTitle() {
        return "";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleRealizationUML";
    }
    
    @Override
    public String export() {
        String export  = "    <" + this.type;
               export += " class=\""     + this.source.getId() + "\"";
               export += " interface=\"" + this.target.getId() + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "1");
               style.put(mxConstants.STYLE_ENDSIZE,  "10");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_BLOCK);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
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
    public boolean equals(Object object) {
        if (object instanceof RealizationUML == false)
            return false;
        return this.source.equals(((RealizationUML) object).getSource())
            && this.target.equals(((RealizationUML) object).getTarget());
    }
}