package view.panel.edit.base.traceability;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.traceability.Traceability;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseTraceability;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

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
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
            this.addPanelBaseTraceability();
            this.addPanelBaseElements();
        this.add(this.tabbedPane);
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
     * Method responsible for adding the Panel Base Elements.
     */
    private void addPanelBaseElements() {
        this.addPanel("panelBaseElements", new PanelBaseElements(this.getViewMenu(), this.traceability));
        this.createScrollPane("scrollPanelBaseElements",  this.getPanelBaseElements());
        this.getScrollPanelBaseElements().setViewportView(this.getPanelBaseElements());
        this.tabbedPane.add("Elements", this.getScrollPanelBaseElements());
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
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return (PanelBaseElements) this.getPanel("panelBaseElements");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Elements.
     * @return Scroll Panel Base Elements.
     */
    public JScrollPane getScrollPanelBaseElements() {
        return this.getScrollPane("scrollPanelBaseElements");
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
}