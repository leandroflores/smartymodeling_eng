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
        setPreferredSize(new Dimension(200, 100));
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseTraceability();
        addPanelBaseElements();
    }
    
    /**
     * Method responsible for adding the Panel Base Traceability.
     */
    private void addPanelBaseTraceability() {
        addPanel("base_traceability", new PanelBaseTraceability(getViewMenu(), traceability));
        createScrollPane("base_traceability", getPanelBaseTraceability());
        getScrollPane("base_traceability").setViewportView(getPanelBaseTraceability());
        tabbedPane.add("Traceability", getScrollPane("base_traceability"));
    }
    
    /**
     * Method responsible for adding the Panel Base Elements.
     */
    private void addPanelBaseElements() {
        addPanel("elements", new PanelBaseElements(getViewMenu(), traceability));
        createScrollPane("elements", getPanelBaseElements());
        getScrollPane("elements").setViewportView(getPanelBaseElements());
        tabbedPane.add("Elements", getScrollPane("elements"));
    }
    
    /**
     * Method responsible for returning the Panel Base Traceability.
     * @return Panel Base Traceability.
     */
    public PanelBaseTraceability getPanelBaseTraceability() {
        return (PanelBaseTraceability) getPanel("base_traceability");
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return (PanelBaseElements) getPanel("elements");
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return traceability;
    }
}