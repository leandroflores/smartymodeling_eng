package view.panel.edit.base.traceability;

import java.awt.Dimension;
import model.structural.base.traceability.Reference;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseReference;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditReference</b>.</p> 
 * <p>Class responsible for defining the <b>Edit Reference Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    model.structural.base.traceability.Reference
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditReference extends PanelEdit {
    private final Reference reference;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param reference Reference.
     */
    public PanelEditReference(ViewMenu view, Reference reference) {
        super(view);
        this.reference = reference;
        setPreferredSize(new Dimension(200, 100));
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseReference();
        addPanelBaseElements();
    }
    
    /**
     * Method responsible for adding the Panel Base Reference.
     */
    private void addPanelBaseReference() {
        addPanel("base_reference", new PanelBaseReference(getViewMenu(), reference));
        createScrollPane("base_reference", getPanelBaseReference());
        getScrollPane("base_reference").setViewportView(getPanelBaseReference());
        tabbedPane.add("Reference", getScrollPane("base_reference"));
    }
    
    /**
     * Method responsible for adding the Panel Base Elements.
     */
    private void addPanelBaseElements() {
        addPanel("elements", new PanelBaseElements(getViewMenu(), reference));
        createScrollPane("elements", getPanelBaseElements());
        getScrollPane("elements").setViewportView(getPanelBaseElements());
        tabbedPane.add("Elements", getScrollPane("elements"));
    }
    
    /**
     * Method responsible for returning the Panel Base Reference.
     * @return Panel Base Reference.
     */
    public PanelBaseReference getPanelBaseReference() {
        return (PanelBaseReference) getPanel("base_reference");
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return (PanelBaseElements) getPanel("elements");
    }
    
    /**
     * Method responsible for returning the Reference.
     * @return Reference.
     */
    public Reference getReference() {
        return reference;
    }
}