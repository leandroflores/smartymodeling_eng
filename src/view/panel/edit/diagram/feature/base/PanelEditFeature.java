package view.panel.edit.diagram.feature.base;

import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import view.panel.base.diagram.feature.base.PanelBaseFeature;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditFeature</b>.</p> 
 * <p>Class responsible for defining a <b>Feature Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-08
 * @see    model.structural.diagram.feature.base.Feature
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditFeature extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Feature Diagram.
     * @param feature Feature.
     */
    public PanelEditFeature(ViewMenu viewMenu, FeatureDiagram diagram, Feature feature) {
        super(viewMenu, diagram, feature);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseFeature();
    }
    
    /**
     * Method responsible for adding the Panel Base Feature.
     */
    protected void addPanelBaseFeature() {
        addPanel("base_feature", new PanelBaseFeature(viewMenu, getDiagram(), getElement()));
        createScrollPane("base_feature", getPanelBaseFeature());
        getScrollPane("base_feature").setViewportView(getPanelBaseFeature());
        tabbedPane.add("Feature", getScrollPane("base_feature"));
    }
    
    /**
     * Method responsible for returning the Panel Base Feature.
     * @return Panel Base Feature.
     */
    public PanelBaseFeature getPanelBaseFeature() {
        return (PanelBaseFeature) getPanel("base_feature");
    }
    
    @Override
    public Feature getElement() {
        return (Feature) element;
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) diagram;
    }
}