package view.panel.edit.diagram.feature.base;

import javax.swing.JTabbedPane;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import view.panel.base.diagram.feature.base.PanelBaseFeature;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

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
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
            this.addPanelBaseFeature();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Feature.
     */
    protected void addPanelBaseFeature() {
        this.addPanel("panelBaseFeature", new PanelBaseFeature(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelBaseElement",  this.getPanelBaseFeature());
        this.getScrollPanelBaseElement().setViewportView(this.getPanelBaseFeature());
        this.tabbedPane.add("Feature", this.getScrollPanelBaseElement());
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) this.diagram;
    }
    
    @Override
    public Feature getElement() {
        return (Feature) this.element;
    }

    /**
     * Method responsible for returning the Panel Base Feature.
     * @return Panel Base Feature.
     */
    public PanelBaseFeature getPanelBaseFeature() {
        return (PanelBaseFeature) this.getPanel("panelBaseFeature");
    }
}