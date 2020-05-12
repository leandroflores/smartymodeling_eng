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
 * @since  04/06/2019
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.ClassDiagram
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventAssociationClass extends ControllerEventAssociation {
    private final PanelClassDiagram panelDiagram;
    private final ClassDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventAssociationClass(PanelClassDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
        this.diagram      = this.panelDiagram.getDiagram();
    }
    
    @Override
    public void addAssociation(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        if (this.check(source, target))
            this.createAssociation(association);
    }
    
    @Override
    public void createAssociation(mxCell association) {
        switch (this.panelDiagram.getType()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                this.addAssociationUML(association);
                break;
            case 6:
                this.addGeneralization(association);
                break;
            case 7:
                this.addRealizationUML(association);
                break;
            case 8:
                this.addAbstraction(association);
                break;
            case 9:
                this.addUsage(association);
                break;
            case 10:
                this.addDependency(association);
                break;
            case 11:
                this.addRequires(association);
                break;
            case 12:
                this.addMutex(association);
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
        if (this.checkAssociationUML(association)) {
            AssociationUML associationUML = this.createAssociationUML(association);
            if (associationUML != null)
                this.diagram.addAssociationUML(associationUML);
        }
    }
    
    /**
     * Method responsible for checking the Association Elements.
     * @param  association Association.
     * @return Elements checked.
     */
    private boolean checkAssociationUML(mxCell association) {
        return   ((this.getSource(association) instanceof Entity)
              &&  (this.getTarget(association) instanceof Entity));
    }
    
    /**
     * Method responsible for returning a new Association UML.
     * @param  association Association.
     * @return Association UML.
     */
    private AssociationUML createAssociationUML(mxCell association) {
        Entity  source    = (Entity) this.getSource(association);
        Entity  target    = (Entity) this.getTarget(association);
        String  category  = this.getCategory();
        boolean direction = this.getDirection();
        return  new AssociationUML(source, target, category, direction);
    }
    
    /**
     * Method responsible for returning the Association Direction Flag.
     * @return Association Direction Flag.
     */
    private boolean getDirection() {
        switch (this.panelDiagram.getType()) {
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
        switch (this.panelDiagram.getType()) {
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
        RealizationUML realizationUML = this.createRealizationUML(associacao);
        if (realizationUML != null)
            this.diagram.addRealizationUML(realizationUML);
    }
    
    /**
     * Method responsible for returning a new Realization UML.
     * @param  association Association Cell.
     * @return Realization UML.
     */
    private RealizationUML createRealizationUML(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
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
        Abstraction abstraction = this.createAbstraction(association);
        if (abstraction != null)
            this.diagram.addAbstraction(abstraction);
    }
    
    /**
     * Method responsible for returning a New Abstraction.
     * @param  association Association Cell.
     * @return New Abstraction.
     */
    private Abstraction createAbstraction(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
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
        Usage usage = this.createUsage(association);
        if (usage != null)
            this.diagram.addUsage(usage);
    }
    
    /**
     * Method responsible for returning a New Usage.
     * @param  association Association Cell.
     * @return New Usage.
     */
    private Usage createUsage(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        try {
            return new Usage(source, target);
        }catch (ClassCastException exception) {
            return null;
        }
    }
}