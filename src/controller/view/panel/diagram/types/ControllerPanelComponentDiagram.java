package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
    private final PanelComponentDiagram panelDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Component Diagram.
     */
    public ControllerPanelComponentDiagram(PanelComponentDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelDiagram.getComponentButton().equals(event.getSource()))
            this.setAddComponent();
        else if (this.panelDiagram.getInterfaceButton().equals(event.getSource()))
            this.setAddInterface();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        if (this.panelDiagram.getOperation().equals("Component"))
            this.addComponent(event);
        else if (this.panelDiagram.getOperation().equals("Interface"))
            this.addInterface(event);
    }
    
    /**
     * Method responsible for setting Add Component Operation.
     */
    public void setAddComponent() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getComponentButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Component");
    }
    
    /**
     * Method responsible for setting Add Interface Operation.
     */
    public void setAddInterface() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getInterfaceButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Interface");
    }

    /**
     * Method responsible for adding a new Component UML.
     * @param event Mouse Event.
     */
    public void addComponent(MouseEvent event) {
        ComponentUML componentUML = new ComponentUML();
                     componentUML.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addComponent(componentUML);
                     componentUML.setDefaultName();
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Interface UML.
     * @param event Mouse Event.
     */
    public void addInterface(MouseEvent event) {
        InterfaceUML interfaceUML = new InterfaceUML();
                     interfaceUML.setPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addInterface(interfaceUML);
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
}