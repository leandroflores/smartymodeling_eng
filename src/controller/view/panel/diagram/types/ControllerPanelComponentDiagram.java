package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.MouseEvent;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;
import view.panel.diagram.types.PanelComponentDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelComponentDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Component Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
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
        if (this.getPanelDiagram().getOperation().equals("Component"))
            this.addComponent(event);
        else if (this.getPanelDiagram().getOperation().equals("Interface"))
            this.addInterface(event);
    }
    
    /**
     * Method responsible for adding a New Component UML.
     * @param event Mouse Event.
     */
    public void addComponent(MouseEvent event) {
        ComponentUML component = new ComponentUML();
                     component.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addComponent(component);
                     component.setDefaultName();
        this.getPanelDiagram().updateDiagram();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New Interface UML.
     * @param event Mouse Event.
     */
    public void addInterface(MouseEvent event) {
        InterfaceUML interface_ = new InterfaceUML();
                     interface_.setPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addInterface(interface_);
                     interface_.setDefaultName();
        this.getPanelDiagram().updateDiagram();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    @Override
    protected PanelComponentDiagram getPanelDiagram() {
        return (PanelComponentDiagram) this.panel;
    }
}