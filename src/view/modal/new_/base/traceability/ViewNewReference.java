package view.modal.new_.base.traceability;

import controller.view.modal.new_.base.traceability.ControllerViewNewReference;
import java.awt.Dimension;
import model.structural.base.traceability.Reference;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseReference;
import view.modal.new_.ViewNew;
import view.main.structural.ViewMenu;
import view.panel.edit.base.traceability.PanelEditReference;

/**
 * <p>Class of View <b>ViewNewReference</b>.</p>
 * <p>Class responsible for defining the <b>New Reference View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.modal.new_.base.traceability.ControllerViewNewReference
 * @see    model.structural.base.traceability.Reference
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewReference extends ViewNew {
    private final Reference reference;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewReference(ViewMenu view) {
        super(view);
        reference  = new Reference();
        controller = new ControllerViewNewReference(this);
        title      = "New Reference";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditReference createPanelNew() {
        return new PanelEditReference(view, reference);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 325);
    }
    
    @Override
    protected PanelEditReference getPanelNew() {
        return (PanelEditReference) super.getPanelNew();
    }
    
    /**
     * Method responsible for returning the Panel Base Reference.
     * @return Panel Base Reference.
     */
    public PanelBaseReference getPanelBaseReference() {
        return getPanelNew().getPanelBaseReference();
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return getPanelNew().getPanelBaseElements();
    }
    
    /**
     * Method responsible for returning the Reference.
     * @return Reference.
     */
    public Reference getReference() {
        return reference;
    }
}