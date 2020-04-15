package view.panel.edit.base.feature;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import view.edit.panel.base.feature.PanelBaseFeature;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditFeature</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Feature</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  08/04/2020
 * @see    model.structural.diagram.feature.base.Feature
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditFeature extends PanelEditElement {
    private final FeatureDiagram diagram;
    private final Feature feature;
    private PanelBaseFeature panelBaseFeature;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Feature Diagram.
     * @param feature Feature.
     */
    public PanelEditFeature(ViewMenu viewMenu, FeatureDiagram diagram, Feature feature) {
        super(viewMenu, feature);
        this.diagram    = diagram;
        this.feature = feature;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseElement();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Element.
     */
    private void addPanelBaseElement() {
        this.panelBaseFeature = new PanelBaseFeature(this.viewMenu, this.diagram, this.feature);
        this.createScrollPane("scrollPanelBaseFeature",  this.panelBaseFeature);
        this.getScrollPanelBaseFeature().setViewportView(this.panelBaseFeature);
        this.tabbedPane.add("Feature", this.getScrollPanelBaseFeature());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public FeatureDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Feature.
     * @return Feature.
     */
    public Feature getFeature() {
        return this.feature;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Feature.
     * @return Scroll Panel Base Feature.
     */
    public JScrollPane getScrollPanelBaseFeature() {
        return this.scrollPanes.get("scrollPanelBaseFeature");
    }

    /**
     * Method responsible for returning the Panel Base Feature.
     * @return Panel Base Feature.
     */
    public PanelBaseFeature getPanelBaseFeature() {
        return this.panelBaseFeature;
    }
}