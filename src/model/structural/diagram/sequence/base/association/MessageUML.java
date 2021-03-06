package model.structural.diagram.sequence.base.association;

import com.mxgraph.util.mxConstants;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.diagram.classes.base.MethodUML;

/**
 * <p>Class of Model <b>MessageUML</b>.</p>
 * <p>Class responsible for representing the <b>Message UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-25
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.sequence.base.LifelineUML
 */
public class MessageUML extends Association implements Comparator<MessageUML> {
    private String    category;
    private String    name;
    private Integer   sequence;
    private MethodUML method;
    
    /**
     * Default constructor method of Class.
     * @param source Source Element.
     * @param target Target Element.
     */
    public MessageUML(Element source, Element target) {
        super();
        this.source   = source;
        this.target   = target;
        this.sequence = 0;
        this.method   = null;
        this.type     = "message";
    }
    
    /**
     * Default constructor method of Class.
     * @param source Entity.
     * @param target Entity.
     * @param category Category.
     */
    public MessageUML(Element source, Element target, String category) {
        super();
        this.source   = source;
        this.target   = target;
        this.name     = ".operation()";
        this.category = category;
        this.sequence = 0;
        this.method   = null;
        this.type     = "message";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public MessageUML(org.w3c.dom.Element element) {
        super(element);
        category = element.getAttribute("category");
        name     = "";
        sequence = Integer.parseInt(element.getAttribute("sequence"));
        method   = null;
        type     = "message";
    }
    
    /**
     * Method responsible for returning if the Message is a Loop.
     * @return Message is a Loop.
     */
    public boolean isLoop() {
        return source.equals(target);
    }
    
    /**
     * Method responsible for returning the Category.
     * @return Category.
     */
    public String getCategory() {
        return category;
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
        return name;
    }

    /**
     * Method responsible for changing the Name by Method.  
    * @param method Method.
     */
    public void changeName(MethodUML method) {
        if (method != null && method.equals(method))
            name = "." + method.getShortSignature();
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
        return sequence;
    }
    
    /**
     * Method responsible for setting the Sequence.
     * @param sequence Sequence.
     */
    public void setSequence(Integer sequence) {
        this.sequence = this.sequence.equals(0) ? sequence : this.sequence;
    }
    
    /**
     * Method responsible for changing the Sequence.
     * @param sequence Sequence.
     */
    public void changeSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Override
    public int compare(MessageUML message1, MessageUML message2) {
        return message1.getSequence().compareTo(message2.getSequence());
    }
    
    /**
     * Method responsible for returning the Method UML.
     * @return Method UML.
     */
    public MethodUML getMethod() {
        return method;
    }

    /**
     * Method responsible for setting the Method UML.
     * @param method Method UML.
     */
    public void setMethod(MethodUML method) {
        this.method =  method;
        this.name   = (method == null) ? ".operation" : "." + method.getShortSignature();
    }
    
    /**
     * Method responsible for returning the Signature.
     * @return Signature.
     */
    private String getSignature() {
        return method == null ? ". operation()" : ". " + method.getShortSignature();
    }
    
    @Override
    public String getTitle() {
        return sequence + getSignature();
    }
    
    @Override
    public String getStyleLabel() {
        return "styleMessageUML" + getId();
    }

    /**
     * Method responsible for returning the End Arrow.
     * @return End Arrow.
     */
    private String getEndArrow() {
        return category.equals("asynchronous") ? mxConstants.ARROW_OPEN : mxConstants.ARROW_BLOCK;
            
    }
    
    /**
     * Method responsible for returning the Method Id.
     * @return Method Id.
     */
    private String getMethodId() {
        return method != null ? method.getId() : "";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_MOVABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_ENDSIZE,  "15");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE,      mxConstants.SHAPE_CONNECTOR);
               style.put(mxConstants.STYLE_ENDARROW,   getEndArrow());
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
        return style;
    }
    
    @Override
    protected String exportHeader() {
        String export  = "    <"        + type;
               export += " id=\""       + id.trim()       + "\"";
               export += " source=\""   + source.getId()  + "\"";
               export += " target=\""   + target.getId()  + "\"";
               export += " name=\""     + name.trim()     + "\"";
               export += " category=\"" + category.trim() + "\"";
               export += " sequence=\"" + sequence        + "\"";
               export += " method=\""   + getMethodId()   + "\"";
               export += ">\n";
        return export;
    }
    
    @Override
    public String toString() {
        return sequence + " - " + name + " (" + id + ")";
    }
}