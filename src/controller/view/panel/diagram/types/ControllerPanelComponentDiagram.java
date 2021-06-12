package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.MouseEvent;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;
import view.panel.diagram.types.PanelComponentDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelComponentDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>PanelComponentDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    model.structural.diagram.ComponentDiagram
 * @see    view.panel.diagram.types.PanelComponentDiagram
 */
public class ControllerPanelComponentDiagram extends ControllerPanelDiagram {

    /**
     * Default constructor method of Class.
     * @param panel Panel Component Diagram.
     */
    public ControllerPanelComponentDiagram(PanelComponentDiagram panel) {
        super(panel);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        if (getPanel().getOperation().equals("Component"))
            addComponent(event);
        else if (getPanel().getOperation().equals("Interface"))
            addInterface(event);
    }
    
    /**
     * Method responsible for adding a New UML Component.
     * @param event Mouse Event.
     */
    public void addComponent(MouseEvent event) {
        ComponentUML component = new ComponentUML(getPanel().getDiagram());
                     component.setPosition(event.getX(), event.getY());
                     getPanel().getDiagram().addComponent(component);
                     component.setDefaultName();
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New UML Interface.
     * @param event Mouse Event.
     */
    public void addInterface(MouseEvent event) {
        InterfaceUML interface_ = new InterfaceUML(getPanel().getDiagram());
                     interface_.setPosition(event.getX(), event.getY());
                     getPanel().getDiagram().addInterface(interface_);
                     interface_.setDefaultName();
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    @Override
    public PanelComponentDiagram getPanel() {
        return (PanelComponentDiagram) panel;
    }
}