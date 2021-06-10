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
 * @since  2019-07-21
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.ComponentDiagram
 * @see    view.panel.diagram.types.PanelComponentDiagram
 */
public class ControllerEventAssociationComponent extends ControllerEventAssociation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Component Diagram.
     */
    public ControllerEventAssociationComponent(PanelComponentDiagram panel) {
        super(panel);
    }
    
    @Override
    public void addAssociation(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        if (check(source, target) && distinct(source, target))
            createAssociation(association);
    }
    
    @Override
    public void createAssociation(mxCell association) {
        switch (getPanel().getType()) {
            case 0:
            case 1:
                addComunicationUML(association);
                break;
            case 2:
                addDependency(association);
                break;
            case 3:
                addRequires(association);
                break;
            case 4:
                addMutex(association);
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
        ComunicationUML comunicationUML = createComunicationUML(association);
        if (comunicationUML != null)
            getDiagram().addComunication(comunicationUML);
    }
    
    /**
     * Method responsible for returning a new Comunication UML.
     * @param  association Association.
     * @return Comunication UML.
     */
    private ComunicationUML createComunicationUML(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        try {
            return new ComunicationUML((ComponentUML) source, (InterfaceUML) target, getCategory());
        }catch (ClassCastException exception) {
            return createComunicationUML(source, target);
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
            return new ComunicationUML((ComponentUML) target, (InterfaceUML) source, getCategory());
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for returning the Comunication Category.
     * @return Comunication Category.
     */
    private String getCategory() {
        if (getPanel().getPanelOperation().getAssociationComboBox().getSelectedIndex() == 0)
            return "provide";
        return "require";
    }
    
    @Override
    public ComponentDiagram getDiagram() {
        return (ComponentDiagram) diagram;
    }
    
    @Override
    public PanelComponentDiagram getPanel() {
        return (PanelComponentDiagram) panel;
    }
}