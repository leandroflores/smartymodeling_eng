package view.panel.edit.base.traceability;

import java.awt.Dimension;
import model.structural.base.traceability.Traceability;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseTraceability;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditTraceability</b>.</p> 
 * <p>Class responsible for defining the <b>Edit Traceability Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    model.structural.base.traceability.Traceability
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditTraceability extends PanelEdit {
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param traceability Traceability.
     */
    public PanelEditTraceability(ViewMenu view, Traceability traceability) {
        super(view);
        this.traceability = traceability;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseTraceability();
        this.addPanelBaseElements();
    }
    
    /**
     * Method responsible for adding the Panel Base Traceability.
     */
    private void addPanelBaseTraceability() {
        this.addPanel("panelBaseTraceability", new PanelBaseTraceability(this.getViewMenu(), this.traceability));
        this.createScrollPane("scrollPanelBaseTraceability",  this.getPanelBaseTraceability());
        this.getScrollPane("scrollPanelBaseTraceability").setViewportView(this.getPanelBaseTraceability());
        this.tabbedPane.add("Traceability", this.getScrollPane("scrollPanelBaseTraceability"));
    }
    
    /**
     * Method responsible for adding the Panel Base Elements.
     */
    private void addPanelBaseElements() {
        this.addPanel("panelBaseElements", new PanelBaseElements(this.getViewMenu(), this.traceability));
        this.createScrollPane("scrollPanelBaseElements",  this.getPanelBaseElements());
        this.getScrollPane("scrollPanelBaseElements").setViewportView(this.getPanelBaseElements());
        this.tabbedPane.add("Elements", this.getScrollPane("scrollPanelBaseElements"));
    }
    
    /**
     * Method responsible for returning the Panel Base Traceability.
     * @return Panel Base Traceability.
     */
    public PanelBaseTraceability getPanelBaseTraceability() {
        return (PanelBaseTraceability) this.getPanel("panelBaseTraceability");
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return (PanelBaseElements) this.getPanel("panelBaseElements");
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
}