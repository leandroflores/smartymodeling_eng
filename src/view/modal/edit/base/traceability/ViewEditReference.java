package view.modal.edit.base.traceability;

import controller.view.modal.edit.base.traceability.ControllerViewEditReference;
import java.awt.Dimension;
import model.structural.base.traceability.Reference;
import view.modal.edit.ViewEdit;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseReference;
import view.panel.edit.base.traceability.PanelEditReference;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditReference</b>.</p>
 * <p>Class responsible for defining the <b>Reference Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.base.traceability.ControllerViewEditReference
 * @see    model.structural.base.traceability.Reference
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditReference extends ViewEdit {
    private final Reference reference;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param reference Reference.
     */
    public ViewEditReference(PanelModeling panel, Reference reference) {
        super(panel.getViewMenu());
        this.reference  = reference;
        this.controller = new ControllerViewEditReference(this);
        this.title      = "Edit Reference Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 350);
    }
    
    @Override
    protected PanelEditReference createPanelEdit() {
        return new PanelEditReference(view, reference);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 300);
    }
    
    @Override
    public PanelEditReference getPanelEdit() {
        return (PanelEditReference) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Reference.
     * @return Panel Base Reference.
     */
    public PanelBaseReference getPanelBaseReference() {
        return getPanelEdit().getPanelBaseReference();
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return getPanelEdit().getPanelBaseElements();
    }
    
    /**
     * Method responsible for returning the Reference.
     * @return Reference.
     */
    public Reference getReference() {
        return reference;
    }
}