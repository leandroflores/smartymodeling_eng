package view.modal.delete.base.variability;

import controller.view.modal.delete.base.variability.ControllerViewDeleteVariability;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.modal.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.modal.delete.base.variability.ControllerViewDeleteVariability
 * @see    model.structural.base.variability.Variability
 * @see    view.modal.delete.ViewDelete
 */
public final class ViewDeleteVariability extends ViewDelete {
    private final Diagram diagram;
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public ViewDeleteVariability(PanelModeling panel, Diagram diagram, Variability variability) {
        super(panel);
        this.diagram     = diagram;
        this.variability = variability;
        this.controller  = new ControllerViewDeleteVariability(this);
        this.title       = "Delete Variability";
        initComponents();
        addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(variability.getName());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    public Variability getVariability() {
        return variability;
    }
}