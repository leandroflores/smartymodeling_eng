package controller.view.panel.diagram.association.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.association.ControllerEventAssociation;
import model.structural.base.Element;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.association.Abstraction;
import model.structural.diagram.classes.base.association.AssociationUML;
import model.structural.diagram.classes.base.association.RealizationUML;
import model.structural.diagram.classes.base.association.Usage;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventAssociationClass</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Class Diagram Association</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-04
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.ClassDiagram
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventAssociationClass extends ControllerEventAssociation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventAssociationClass(PanelClassDiagram panel) {
        super(panel);
    }
    
    @Override
    public void addAssociation(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        if (check(source, target))
            createAssociation(association);
    }
    
    @Override
    public void createAssociation(mxCell association) {
        switch (getPanel().getType()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                addAssociationUML(association);
                break;
            case 6:
                addGeneralization(association);
                break;
            case 7:
                addRealizationUML(association);
                break;
            case 8:
                addAbstraction(association);
                break;
            case 9:
                addUsage(association);
                break;
            case 10:
                addDependency(association);
                break;
            case 11:
                addRequires(association);
                break;
            case 12:
                addMutex(association);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for adding the Association UML.
     * @param association Association mxCell.
     */
    private void addAssociationUML(mxCell association) {
        if (checkAssociationUML(association)) {
            AssociationUML associationUML = createAssociationUML(association);
            if (associationUML != null)
                getDiagram().addAssociationUML(associationUML);
        }
    }
    
    /**
     * Method responsible for checking the Association Elements.
     * @param  association Association.
     * @return Elements checked.
     */
    private boolean checkAssociationUML(mxCell association) {
        return getSource(association) instanceof Entity 
            && getTarget(association) instanceof Entity;
    }
    
    /**
     * Method responsible for returning a new Association UML.
     * @param  association Association.
     * @return Association UML.
     */
    private AssociationUML createAssociationUML(mxCell association) {
        Entity  source    = (Entity) getSource(association);
        Entity  target    = (Entity) getTarget(association);
        String  category  = getCategory();
        boolean direction = getDirection();
        return  new AssociationUML(source, target, category, direction);
    }
    
    /**
     * Method responsible for returning the Association Direction Flag.
     * @return Association Direction Flag.
     */
    private boolean getDirection() {
        switch (getPanel().getType()) {
            case 1:
            case 3:
            case 5:
                return true;
            default:
                return false;
        }
    }
    
    /**
     * Method responsible for returning the Association Category.
     * @return Association Category.
     */
    private String getCategory() {
        switch (getPanel().getType()) {
            case 2:
            case 3:
                return "aggregation";
            case 4:
            case 5:
                return "composition";
            default:
                return "normal";
        }
    }
    
    /**
     * Method responsible for adding the Realization UML.
     * @param association Association mxCell.
     */
    private void addRealizationUML(mxCell associacao) {
        RealizationUML realizationUML = createRealizationUML(associacao);
        if (realizationUML != null)
            getDiagram().addRealizationUML(realizationUML);
    }
    
    /**
     * Method responsible for returning a new Realization UML.
     * @param  association Association Cell.
     * @return Realization UML.
     */
    private RealizationUML createRealizationUML(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        try {
            return new RealizationUML((ClassUML) source, (InterfaceUML) target);
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for adding the Abstraction.
     * @param association Abstraction mxCell.
     */
    private void addAbstraction(mxCell association) {
        Abstraction abstraction = createAbstraction(association);
        if (abstraction != null)
            getDiagram().addAbstraction(abstraction);
    }
    
    /**
     * Method responsible for returning a New Abstraction.
     * @param  association Association Cell.
     * @return New Abstraction.
     */
    private Abstraction createAbstraction(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        try {
            return new Abstraction(source, target);
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    /**
     * Method responsible for adding the Usage.
     * @param association Usage mxCell.
     */
    private void addUsage(mxCell association) {
        Usage usage = createUsage(association);
        if (usage != null)
            getDiagram().addUsage(usage);
    }
    
    /**
     * Method responsible for returning a New Usage.
     * @param  association Association Cell.
     * @return New Usage.
     */
    private Usage createUsage(mxCell association) {
        Element source = getSource(association);
        Element target = getTarget(association);
        try {
            return new Usage(source, target);
        }catch (ClassCastException exception) {
            return null;
        }
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
    
    @Override
    public PanelClassDiagram getPanel() {
        return (PanelClassDiagram) panel;
    }
}