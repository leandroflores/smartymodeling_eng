package model.controller.structural.base;

import java.util.Arrays;
import model.controller.Controller;
import model.structural.base.Diagram;
import model.structural.base.Element;

/**
 * <p>Class of Controller <b>ControllerDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-31
 * @see    model.controller.Controller
 * @see    model.structural.base.Diagram
 */
public class ControllerDiagram extends Controller {
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param diagram Diagram.
     */
    public ControllerDiagram(Diagram diagram) {
        super(diagram.getProject());
        this.diagram = diagram;
    }
    
    @Override
    public Element[] getDefaultElements() {
        Object[] array = diagram.getDefaultElements().toArray();
        return Arrays.copyOf(array, array.length, Element[].class);
    }
}