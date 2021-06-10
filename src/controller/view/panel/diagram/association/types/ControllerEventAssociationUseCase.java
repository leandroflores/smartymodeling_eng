package controller.view.panel.diagram.association.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.association.ControllerEventAssociation;
import model.structural.base.Element;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.association.ExtendUML;
import model.structural.diagram.usecase.base.association.IncludeUML;
import model.structural.diagram.usecase.base.association.CommunicationUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.panel.diagram.types.PanelUseCaseDiagram;

/**
 * <p>Class of Controller <b>ControllerEventAssociationUseCase</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Use Case Diagram Association</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.UseCaseDiagram
 * @see    view.panel.diagram.types.PanelUseCaseDiagram
 */
public class ControllerEventAssociationUseCase extends ControllerEventAssociation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Use Case Diagram.
     */
    public ControllerEventAssociationUseCase(PanelUseCaseDiagram panel) {
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
                addRealizationUML(association);
                break;
            case 1:
                addExtendUML(association);
                break;
            case 2:
                addIncludeUML(association);
                break;
            case 3:
                addGeneralization(association);
                break;
            case 4:
                addRequires(association);
                break;
            case 5:
                addMutex(association);
                break;
            case 6:
                addDependency(association);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for adding a Realization UML.
     * @param association Association.
     */
    private void addRealizationUML(mxCell association) {
        CommunicationUML realizationUML = createRealizationUML(association);
        if (realizationUML != null)
            getDiagram().addCommunication(realizationUML);
    }
    
    /**
     * Method responsible for returning a new Realization UML.
     * @param  association Association.
     * @return Realization UML.
     */
    private CommunicationUML createRealizationUML(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        try {
            return new CommunicationUML((ActorUML) source, (UseCaseUML) target);
        }catch (ClassCastException exception) {
            return createRealizationUML(source, target);
        }
    }
    
    /**
     * Method responsible for returning a new Realization UML.
     * @param  source Use Case UML.
     * @param  target Actor UML
     * @return Realization UML.
     */
    private CommunicationUML createRealizationUML(Element source, Element target) {
        try {
            return new CommunicationUML((ActorUML) target, (UseCaseUML) source);
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for adding a Extend UML.
     * @param association mxCell Association.
     */
    private void addExtendUML(mxCell association) {
        ExtendUML extendUML = createExtendUML(association);
        if (extendUML != null)
            getDiagram().addExtend(extendUML);
    }
    
    /**
     * Method responsible for returning a new Extend UML.
     * @param  association Association.
     * @return Extend UML.
     */
    private ExtendUML createExtendUML(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        try {
            return new ExtendUML((UseCaseUML) source, (UseCaseUML) target);
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for adding a Include UML.
     * @param association Association.
     */
    private void addIncludeUML(mxCell association) {
        IncludeUML includeUML = createIncludeUML(association);
        if (includeUML != null)
            getDiagram().addInclude(includeUML);
    }
    
    /**
     * Method responsible for returning a new Include UML.
     * @param  association Association.
     * @return Include UML.
     */
    private IncludeUML createIncludeUML(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        try {
            return new IncludeUML((UseCaseUML) source, (UseCaseUML) target);
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    @Override
    public UseCaseDiagram getDiagram() {
        return (UseCaseDiagram) diagram;
    }
    
    @Override
    public PanelUseCaseDiagram getPanel() {
        return (PanelUseCaseDiagram) panel;
    }
}