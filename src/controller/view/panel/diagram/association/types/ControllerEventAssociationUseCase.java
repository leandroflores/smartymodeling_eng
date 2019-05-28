package controller.view.panel.diagram.association.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.association.ControllerEventAssociation;
import model.structural.base.Element;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.ActorUML;
import model.structural.diagram.usecase.ExtendUML;
import model.structural.diagram.usecase.IncludeUML;
import model.structural.diagram.usecase.RealizationUML;
import model.structural.diagram.usecase.UseCaseUML;
import view.panel.diagram.types.PanelUseCaseDiagram;

/**
 * <p>Class of Controller <b>ControllerEventAssociationUseCase</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Use Case Diagram Association</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.UseCaseDiagram
 * @see    view.panel.diagram.types.PanelUseCaseDiagram
 */
public class ControllerEventAssociationUseCase extends ControllerEventAssociation {
    private final PanelUseCaseDiagram panelDiagram;
    private final UseCaseDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Use Case Diagram.
     */
    public ControllerEventAssociationUseCase(PanelUseCaseDiagram panel) {
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
                this.addRealizationUML(association);
                break;
            case 1:
                this.addExtendUML(association);
                break;
            case 2:
                this.addIncludeUML(association);
                break;
            case 3:
                this.addGeneralization(association);
                break;
            case 4:
                this.addRequires(association);
                break;
            case 5:
                this.addMutex(association);
                break;
            case 6:
                this.addDependency(association);
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
        RealizationUML realizationUML = this.createRealizationUML(association);
        if (realizationUML != null)
            this.diagram.addRealization(realizationUML);
    }
    
    /**
     * Method responsible for returning a new Realization UML.
     * @param  association Association.
     * @return Realization UML.
     */
    private RealizationUML createRealizationUML(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        try {
            return new RealizationUML((ActorUML) source, (UseCaseUML) target);
        }catch (ClassCastException exception) {
            return this.createRealizationUML(source, target);
        }
    }
    
    /**
     * Method responsible for returning a new Realization UML.
     * @param  source Use Case UML.
     * @param  target Actor UML
     * @return Realization UML.
     */
    private RealizationUML createRealizationUML(Element source, Element target) {
        try {
            return new RealizationUML((ActorUML) target, (UseCaseUML) source);
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for adding a Extend UML.
     * @param association mxCell Association.
     */
    private void addExtendUML(mxCell association) {
        ExtendUML extendUML = this.createExtendUML(association);
        if (extendUML != null)
            this.diagram.addExtend(extendUML);
    }
    
    /**
     * Method responsible for returning a new Extend UML.
     * @param  association Association.
     * @return Extend UML.
     */
    private ExtendUML createExtendUML(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
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
        IncludeUML includeUML = this.createIncludeUML(association);
        if (includeUML != null)
            this.diagram.addInclude(includeUML);
    }
    
    /**
     * Method responsible for returning a new Include UML.
     * @param  association Association.
     * @return Include UML.
     */
    private IncludeUML createIncludeUML(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        try {
            return new IncludeUML((UseCaseUML) source, (UseCaseUML) target);
        }catch (ClassCastException exception) {
            return null;
        }
    }
}