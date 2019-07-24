package view.delete.variability;

import controller.view.delete.variability.ControllerViewDeleteVariability;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.ViewStyle;
import view.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.delete.variability.ControllerViewDeleteVariability
 * @see    model.structural.base.variability.Variability
 * @see    view.delete.ViewDelete
 */
public final class ViewDeleteVariability extends ViewDelete {
    private final Diagram diagrama;
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public ViewDeleteVariability(PanelModeling panel, Diagram diagram, Variability variability) {
        super(panel);
        this.diagrama    = diagram;
        this.variability = variability;
        this.controller  = new ControllerViewDeleteVariability(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Delete Variability");
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.variability.getName());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagrama;
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    public Variability getVariability() {
        return this.variability;
    }
}