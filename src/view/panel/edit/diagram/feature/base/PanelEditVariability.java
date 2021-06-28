package view.panel.edit.diagram.feature.base;

import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Variability;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;
import view.panel.base.diagram.feature.base.PanelBaseVariability;

/**
 * <p>Class of View <b>PanelEditVariability</b>.</p> 
 * <p>Class responsible for defining a <b>Variability Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-06-29
 * @see    model.structural.diagram.feature.base.Variability
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditVariability extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Feature Diagram.
     * @param variability Variability.
     */
    public PanelEditVariability(ViewMenu viewMenu, FeatureDiagram diagram, Variability variability) {
        super(viewMenu, diagram, variability);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseVariability();
    }
    
    /**
     * Method responsible for adding the Panel Base Variability.
     */
    protected void addPanelBaseVariability() {
        addPanel("base_variability", new PanelBaseVariability(viewMenu, getDiagram(), getElement()));
        createScrollPane("base_variability",  getPanelBaseVariability());
        getScrollPane("base_variability").setViewportView(getPanelBaseVariability());
        tabbedPane.add("Variability", getScrollPane("base_variability"));
    }
    
    /**
     * Method responsible for returning the Panel Base Variability.
     * @return Panel Base Variability.
     */
    public PanelBaseVariability getPanelBaseVariability() {
        return (PanelBaseVariability) getPanel("base_variability");
    }
    
    @Override
    public Variability getElement() {
        return (Variability) element;
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) diagram;
    }
}