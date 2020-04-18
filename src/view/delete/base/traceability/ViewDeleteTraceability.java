package view.delete.base.traceability;

import controller.view.delete.base.traceability.ControllerViewDeleteTraceability;
import model.structural.base.traceability.Traceability;
import view.delete.base.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteTraceability</b>.</p>
 * <p>Class responsible for defining the <b>Traceability Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23-07-2019
 * @see    controller.view.delete.base.traceability.ControllerViewDeleteTraceability
 * @see    model.structural.base.traceability.Traceability
 * @see    view.delete.base.ViewDelete
 */
public final class ViewDeleteTraceability extends ViewDelete {
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param traceability Traceability.
     */
    public ViewDeleteTraceability(PanelModeling panel, Traceability traceability) {
        super(panel);
        this.traceability = traceability;
        this.controller   = new ControllerViewDeleteTraceability(this);
        this.title       = "Delete Traceability";
        this.initComponents();
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.traceability.getName());
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
}