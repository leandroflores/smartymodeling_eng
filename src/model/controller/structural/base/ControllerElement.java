package model.controller.structural.base;

import model.structural.base.Element;
import model.structural.diagram.component.base.InterfaceUML;

/**
 * <p>Class of Controller <b>ControllerElement</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-31
 * @see    model.structural.base.Element
 */
public class ControllerElement {
    
    /**
     * Method responsible for returning the Diagram Type of Element.
     * @param  element Element.
     * @return Diagram Type of Element.
     */
    public String getType(Element element) {
        if (element instanceof InterfaceUML)
            return "Component";
        switch (element.getType().toLowerCase().trim()) {
            case "feature":
                return "Feature";
            case "actor":
            case "usecase":
                return "UseCase";
            case "class":
            case "interface":
                return "Class";
            case "component":
                return "Component";
            case "lifeline":
            case "instance":
                return "Sequence";
            case "activity":
            case "decision":
                return "Activity";
            default:
                return "";
        }
    }
}