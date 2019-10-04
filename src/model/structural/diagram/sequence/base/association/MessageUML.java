package model.structural.diagram.sequence.base.association;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.diagram.classes.base.MethodUML;

/**
 * <p>Class of Model <b>MessageUML</b>.</p>
 * <p>Class responsible for representing the <b>Message UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.sequence.base.LifelineUML
 */
public class MessageUML extends Association {
    private String    category;
    private String    name;
    private Integer   sequence;
    private MethodUML method;
    
    /**
     * Default constructor method of Class.
     * @param source Element.
     * @param target Element.
     */
    public MessageUML(Element source, Element target) {
        this.source   = source;
        this.target   = target;
        this.type     = "message";
        this.sequence = 0;
        this.method   = null;
    }
    
    /**
     * Default constructor method of Class.
     * @param source Element.
     * @param target Element.
     * @param category Category.
     */
    public MessageUML(Element source, Element target, String category) {
        this.source   = source;
        this.target   = target;
        this.type     = "message";
        this.name     = ".operation()";
        this.category = category;
        this.sequence = 0;
        this.method   = null;
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

    /**
     * Method responsible for returning the Method UML.
     * @return Method UML.
     */
    public MethodUML getMethod() {
        return this.method;
    }

    /**
     * Method responsible for setting the Method UML.
     * @param method Method UML.
     */
    public void setMethod(MethodUML method) {
        if (method != null) {
            this.method = method;
            this.name   = this.method.getShortSignature();
        }else {
            this.name   = ".operation()";
        }
    }
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    private String getSignature() {
        if (this.method == null)
            return ". operation()";
        return method.getSignature();
    }
    
    @Override
    public String getTitle() {
        return this.sequence + this.getSignature();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleMessageUML" + this.getId();
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
    
    /**
     * Method responsible for returning the Method Id.
     * @return Method Id.
     */
    private String getMethodId() {
        if (this.method != null) 
            return this.method.getId();
        return "";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
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
    
    @Override
    public String export() {
        String export  = "    <"        + this.type;
               export += " source=\""   + this.source.getId()  + "\"";
               export += " target=\""   + this.target.getId()  + "\"";
               export += " name=\""     + this.name.trim()     + "\"";
               export += " category=\"" + this.category.trim() + "\"";
               export += " sequence=\"" + this.sequence        + "\"";
               export += " method=\""   + this.getMethodId()   + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}