package view.delete.traceability;

import controller.view.delete.traceability.ControllerViewDeleteTraceability;
import model.structural.base.traceability.Traceability;
import view.ViewStyle;
import view.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteTraceability</b>.</p>
 * <p>Class responsible for defining the <b>Traceability Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    controller.view.delete.traceability.ControllerViewDeleteTraceability
 * @see    model.structural.base.traceability.Traceability
 * @see    view.delete.ViewDelete
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
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Delete Traceability");
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