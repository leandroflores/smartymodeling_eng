package view.modal.edit.base.traceability;

import controller.view.modal.edit.base.traceability.ControllerViewEditTraceability;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.traceability.Traceability;
import view.modal.edit.ViewEdit;
import view.panel.base.traceability.PanelBaseTraceability;
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
        super(panel);
        this.traceability = traceability;
        this.controller  = new ControllerViewEditTraceability(this);
        this.title       = "Edit Traceability Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 350);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 225));
            this.addPanelBaseTraceability();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Traceability.
     */
    private void addPanelBaseTraceability() {
        this.addPanel("panelBaseTraceability", new PanelBaseTraceability(this.getViewMenu(), this.traceability));
        this.createScrollPane("scrollPanelBaseTraceability",  this.getPanelBaseTraceability());
        this.getScrollPanelBaseTraceability().setViewportView(this.getPanelBaseTraceability());
        this.tabbedPane.add("Traceability", this.getScrollPanelBaseTraceability());
    }
    
    /**
     * Method responsible for returning the Panel Base Traceability.
     * @return Panel Base Traceability.
     */
    public PanelBaseTraceability getPanelBaseTraceability() {
        return (PanelBaseTraceability) this.getPanel("panelBaseTraceability");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Traceability.
     * @return Scroll Panel Base Traceability.
     */
    public JScrollPane getScrollPanelBaseTraceability() {
        return this.getScrollPane("scrollPanelBaseTraceability");
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
}