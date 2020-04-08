package view.panel.edit.base.feature;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import view.edit.panel.base.PanelBaseElement;
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
    private PanelBaseElement panelBaseElement;
    
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
        this.panelBaseElement = new PanelBaseElement(this.viewMenu, this.diagram, this.feature);
        this.panelBaseElement.hideMandatory();
        this.createScrollPane("scrollPanelBaseElement",  this.panelBaseElement);
        this.getScrollPanelBaseElement().setViewportView(this.panelBaseElement);
        this.tabbedPane.add("Feature", this.getScrollPanelBaseElement());
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
     * Method responsible for returning the Scroll Panel Base Element.
     * @return Scroll Panel Base Element.
     */
    public JScrollPane getScrollPanelBaseElement() {
        return this.scrollPanes.get("scrollPanelBaseElement");
    }

    /**
     * Method responsible for returning the Panel Base Element.
     * @return Panel Base Element.
     */
    public PanelBaseElement getPanelBaseElement() {
        return this.panelBaseElement;
    }
}