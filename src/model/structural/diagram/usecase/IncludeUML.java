package model.structural.diagram.usecase;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.association.Association;

/**
 * <p>Class of Model <b>IncludeUML</b>.</p>
 * <p>Class responsible for representing <b>Include UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/05/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.usecase.UseCaseUML
 */
public class IncludeUML extends Association {
    
    /**
     * Default constructor method of Class.
     * @param source Use Case UML.
     * @param target Use Case UML.
     */
    public IncludeUML(UseCaseUML source, UseCaseUML target) {
        this.source = source;
        this.target = target;
        this.type   = "include";
    }

    @Override
    public UseCaseUML getSource() {
        return (UseCaseUML) this.source;
    }

    /**
     * Method responsible for setting Source.
     * @param source Use Case UML.
     */
    public void setSource(UseCaseUML source) {
        this.source = source;
    }

    @Override
    public UseCaseUML getTarget() {
        return (UseCaseUML) this.target;
    }

    /**
     * Method responsible for setting Target.
     * @param target Use Case UML.
     */
    public void setTarget(UseCaseUML target) {
        this.target = target;
    }
    
    @Override
    public String getTitle() {
        return "<<include>>";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleIncludeUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "1");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
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
        if (object instanceof IncludeUML == false)
            return false;
        return this.source.equals(((IncludeUML) object).getSource())
            && this.target.equals(((IncludeUML) object).getTarget());
    }
}