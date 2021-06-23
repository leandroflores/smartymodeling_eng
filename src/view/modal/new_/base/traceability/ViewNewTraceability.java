package view.modal.new_.base.traceability;

import controller.view.modal.new_.base.traceability.ControllerViewNewTraceability;
import java.awt.Dimension;
import model.structural.base.traceability.Traceability;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseTraceability;
import view.modal.new_.ViewNew;
import view.main.structural.ViewMenu;
import view.panel.edit.base.traceability.PanelEditTraceability;

/**
 * <p>Class of View <b>ViewNewTraceability</b>.</p>
 * <p>Class responsible for defining the <b>New Traceability View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.modal.new_.base.traceability.ControllerViewNewTraceability
 * @see    model.structural.base.traceability.Traceability
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewTraceability extends ViewNew {
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewTraceability(ViewMenu view) {
        super(view);
        traceability = new Traceability();
        controller   = new ControllerViewNewTraceability(this);
        title        = "New Traceability";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditTraceability createPanelNew() {
        return new PanelEditTraceability(view, traceability);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 325);
    }
    
    @Override
    protected PanelEditTraceability getPanelNew() {
        return (PanelEditTraceability) super.getPanelNew();
    }
    
    /**
     * Method responsible for returning the Panel Base Traceability.
     * @return Panel Base Traceability.
     */
    public PanelBaseTraceability getPanelBaseTraceability() {
        return getPanelNew().getPanelBaseTraceability();
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return getPanelNew().getPanelBaseElements();
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return traceability;
    }
}