package model.structural.diagram.usecase.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>UseCaseUML</b>.</p>
 * <p>Class responsible for representing <b>Use Case UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/05/2019
 * @see    model.structural.base.Element
 */
public class UseCaseUML extends Element {
    
    /**
     * Default constructor method of Class.
     */
    public UseCaseUML() {
        this.name      = "UseCase";
        this.type      = "useCase";
        this.mandatory = true;
        this.size      = new Point(200, 100);
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public UseCaseUML(org.w3c.dom.Element element) {
        super(element, true);
        this.type = "useCase";
    }

    @Override
    public String getIcon() {
        return super.getFolder() + "usecase/use-case.png";
    }
    
    @Override
    public String getStyleLabel() {
        return "styleUseCaseUML";
    }
    
    @Override
    public Map getStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE, "1");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#B4D34C");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
    
    @Override
    public String toString() {
        return "Use Case: " + this.name;
    }
}