package view.modal.edit.base.traceability;

import controller.view.modal.edit.base.traceability.ControllerViewEditTraceability;
import java.awt.Dimension;
import model.structural.base.traceability.Traceability;
import view.modal.edit.ViewEdit;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseTraceability;
import view.panel.edit.base.traceability.PanelEditTraceability;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditTraceability</b>.</p>
 * <p>Class responsible for defining the <b>Traceability Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.base.traceability.ControllerViewEditTraceability
 * @see    model.structural.base.traceability.Traceability
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditTraceability extends ViewEdit {
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param traceability Traceability.
     */
    public ViewEditTraceability(PanelModeling panel, Traceability traceability) {
        super(panel.getViewMenu());
        this.traceability = traceability;
        this.controller  = new ControllerViewEditTraceability(this);
        this.title       = "Edit Traceability Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(600, 350));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelEditTraceability();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Traceability.
     */
    private void addPanelEditTraceability() {
        this.addPanel("panelEditTraceability", new PanelEditTraceability(this.view, this.traceability));
        this.getPanel("panelEditTraceability").setPreferredSize(new Dimension(500, 300));
        this.add(this.getPanel("panelEditTraceability"));
    }
    
    /**
     * Method responsible for returning the Panel Edit Traceability.
     * @return Panel Edit Traceability.
     */
    private PanelEditTraceability getPanelEditTraceability() {
        return (PanelEditTraceability) this.getPanel("panelEditTraceability");
    }
    
    /**
     * Method responsible for returning the Panel Base Traceability.
     * @return Panel Base Traceability.
     */
    public PanelBaseTraceability getPanelBaseTraceability() {
        return this.getPanelEditTraceability().getPanelBaseTraceability();
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return this.getPanelEditTraceability().getPanelBaseElements();
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
}