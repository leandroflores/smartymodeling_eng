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
    private String  category;
    private String  name;
    private Integer sequence;
    
    /**
     * Default constructor method of Class.
     * @param source Lifeline UML.
     * @param target Lifeline UML.
     */
    public MessageUML(LifelineUML source, LifelineUML target) {
        this.source   = source;
        this.target   = target;
        this.type     = "message";
        this.sequence = 0;
    }
    
    /**
     * Default constructor method of Class.
     * @param source Lifeline UML.
     * @param target Lifeline UML.
     * @param category Category.
     */
    public MessageUML(LifelineUML source, LifelineUML target, String category) {
        this.source   = source;
        this.target   = target;
        this.category = category;
        this.sequence = 0;
        this.type     = "message";
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

    /**
     * Method responsible for returning the Name.
     * @return Name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method responsible for setting the Name.
     * @param name Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method responsible for returning the Sequence.
     * @return Sequence.
     */
    public Integer getSequence() {
        return this.sequence;
    }

    /**
     * Method responsible for setting the Sequence.
     * @param sequence Sequence.
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    
    @Override
    public String getTitle() {
        return this.sequence + ". operation()";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleMessageUML";
    }

    /**
     * Method responsible for returning the End Arrow.
     * @return End Arrow.
     */
    private String getEndArrow() {
        if (this.category.equals("asynchronous"))
            return mxConstants.ARROW_OPEN;
        return mxConstants.ARROW_BLOCK;
            
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#FFFFFF");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
               style.put(mxConstants.STYLE_ENDARROW,   this.getEndArrow());
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