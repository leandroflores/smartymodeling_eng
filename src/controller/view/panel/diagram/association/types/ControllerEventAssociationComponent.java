package controller.view.panel.diagram.association.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.association.ControllerEventAssociation;
import model.structural.base.Element;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;
import model.structural.diagram.component.base.association.ComunicationUML;
import view.panel.diagram.types.PanelComponentDiagram;

/**
 * <p>Class of Controller <b>ControllerEventAssociationComponent</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Component Diagram Association</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/07/2019
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.ComponentDiagram
 * @see    view.panel.diagram.types.PanelComponentDiagram
 */
public class ControllerEventAssociationComponent extends ControllerEventAssociation {
    private final PanelComponentDiagram panelDiagram;
    private final ComponentDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Component Diagram.
     */
    public ControllerEventAssociationComponent(PanelComponentDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
        this.diagram      = this.panelDiagram.getDiagram();
    }
    
    @Override
    public void addAssociation(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        if (this.check(source, target) && this.distinct(source, target))
            this.createAssociation(association);
    }
    
    @Override
    public void createAssociation(mxCell association) {
        switch (this.panelDiagram.getType()) {
            case 0:
            case 1:
                this.addComunicationUML(association);
                break;
            case 2:
                this.addDependency(association);
                break;
            case 3:
                this.addRequires(association);
                break;
            case 4:
                this.addMutex(association);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for adding the Comunication UML.
     * @param association mxCell Association.
     */
    private void addComunicationUML(mxCell association) {
        ComunicationUML comunicationUML = this.createComunicationUML(association);
        if (comunicationUML != null)
            this.diagram.addComunication(comunicationUML);
    }
    
    /**
     * Method responsible for returning a new Comunication UML.
     * @param  association Association.
     * @return Comunication UML.
     */
    private ComunicationUML createComunicationUML(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        try {
            return new ComunicationUML((ComponentUML) source, (InterfaceUML) target, this.getCategory());
        }catch (ClassCastException exception) {
            return this.createComunicationUML(source, target);
        }
    }
    
    /**
     * Method responsible for returning a new Comunication UML.
     * @param  source Interface UML.
     * @param  target Component UML
     * @return Comunication UML.
     */
    private ComunicationUML createComunicationUML(Element source, Element target) {
        try {
            return new ComunicationUML((ComponentUML) target, (InterfaceUML) source, this.getCategory());
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for returning the Comunication Category.
     * @return Comunication Category.
     */
    private String getCategory() {
        System.out.println("Type: " + this.panelDiagram.getType());
        if (this.panelDiagram.getAssociationComboBox().getSelectedIndex() == 0)
            return "provide";
        return "require";
    }
}