package view.modal.delete.base.traceability;

import controller.view.modal.delete.base.traceability.ControllerViewDeleteReference;
import model.structural.base.traceability.Reference;
import view.modal.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteReference</b>.</p>
 * <p>Class responsible for defining the <b>Reference Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-21
 * @see    controller.view.modal.delete.base.traceability.ControllerViewDeleteReference
 * @see    model.structural.base.traceability.Reference
 * @see    view.modal.delete.ViewDelete
 */
public final class ViewDeleteReference extends ViewDelete {
    private final Reference reference;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param reference Reference.
     */
    public ViewDeleteReference(PanelModeling panel, Reference reference) {
        super(panel);
        this.reference  = reference;
        this.controller = new ControllerViewDeleteReference(this);
        this.title      = "Delete Reference";
        initComponents();
        addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(reference.getName());
    }
    
    /**
     * Method responsible for returning the Reference.
     * @return Reference.
     */
    public Reference getReference() {
        return reference;
    }
}