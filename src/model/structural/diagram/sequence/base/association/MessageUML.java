package model.structural.diagram.sequence.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.association.Association;
import model.structural.diagram.sequence.base.LifelineUML;

/**
 * <p>Class of Model <b>MessageUML</b>.</p>
 * <p>Class responsible for representing the <b>Message UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.sequence.base.LifelineUML
 */
public class MessageUML extends Association {
    private String category;
    private String name;
    
    /**
     * Default constructor method of Class.
     * @param source Lifeline UML.
     * @param target Lifeline UML.
     */
    public MessageUML(LifelineUML source, LifelineUML target) {
        this.source = source;
        this.target = target;
        this.type   = "message";
    }

    @Override
    public LifelineUML getSource() {
        return (LifelineUML) this.source;
    }

    /**
     * Method responsible for setting the Source.
     * @param source Lifeline UML.
     */
    public void setSource(LifelineUML source) {
        this.source = source;
    }

    @Override
    public LifelineUML getTarget() {
        return (LifelineUML) this.target;
    }

    /**
     * Method responsible for setting Target.
     * @param target Lifeline UML.
     */
    public void setTarget(LifelineUML target) {
        this.target = target;
    }
    
    @Override
    public String getTitle() {
        return "<<>>";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleExtendUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "1");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_OPEN);
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
        if (object instanceof MessageUML == false)
            return false;
        return this.source.equals(((MessageUML) object).getSource())
            && this.target.equals(((MessageUML) object).getTarget());
    }
}