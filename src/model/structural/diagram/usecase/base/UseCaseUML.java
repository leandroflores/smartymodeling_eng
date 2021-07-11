package model.structural.diagram.usecase.base;

import com.mxgraph.util.mxConstants;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Model <b>UseCaseUML</b>.</p>
 * <p>Class responsible for representing <b>Use Case UML</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-22
 * @see    model.structural.base.Element
 */
public class UseCaseUML extends Element {
    
    /**
     * Default constructor method of Class.
     * @param diagram Use Case Diagram.
     */
    public UseCaseUML(Diagram diagram) {
        super(diagram);
        name = "UseCase";
        size = new Point(200, 100);
        type = "useCase";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     * @param diagram Use Case Diagram.
     */
    public UseCaseUML(org.w3c.dom.Element element, Diagram diagram) {
        super(element, diagram, true);
        type = "useCase";
    }

    
    @Override
    public void setDefaultName() {
        setName("Use Case");
        if (id != null && id.contains("#"))
            setName("Use Case " + id.substring(id.indexOf("#") + 1));
    }
    
    @Override
    public String getIcon() {
        return getFolder() + "usecase/use-case.png";
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
               style.put(mxConstants.STYLE_FILLCOLOR,   mxConstants.NONE);
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_CENTER);
        return style;
    }
    
    @Override
    public String toString() {
        return "Use Case: " + name;
    }
}