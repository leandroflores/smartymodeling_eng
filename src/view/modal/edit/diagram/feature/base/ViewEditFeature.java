package view.modal.edit.diagram.feature.base;

import controller.view.modal.edit.diagram.feature.base.ControllerViewEditFeature;
import java.awt.Dimension;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import view.modal.edit.ViewEdit;
import view.panel.base.diagram.feature.base.PanelBaseFeature;
import view.panel.edit.diagram.feature.base.PanelEditFeature;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditFeature</b>.</p>
 * <p>Class responsible for defining the <b>Feature Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.modal.edit.diagram.feature.base.ControllerViewEditFeature
 * @see    model.structural.diagram.feature.base.Feature
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditFeature extends ViewEdit {
    private final FeatureDiagram diagram;
    private final Feature feature;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Feature Diagram.
     * @param feature Feature.
     */
    public ViewEditFeature(PanelModeling panel, FeatureDiagram diagram, Feature feature) {
        super(panel);
        this.diagram    = diagram;
        this.feature    = feature;
        this.controller = new ControllerViewEditFeature(this);
        this.title      = "Edit Feature Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(600, 425));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelBaseFeature();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Feature.
     */
    private void addPanelBaseFeature() {
        this.addPanel("panelEditFeature", new PanelEditFeature(this.view, this.diagram, this.feature));
        this.getPanel("panelEditFeature").setPreferredSize(new Dimension(500, 300));
        this.add(this.getPanel("panelEditFeature"));
    }
    
    /**
     * Method responsible for returning the Panel Base Feature.
     * @return Panel Base Feature.
     */
    public PanelBaseFeature getPanelBaseFeature() {
        return ((PanelEditFeature) this.getPanel("panelEditFeature")).getPanelBaseFeature();
    }
    
    /**
     * Method responsible for returning the Feature.
     * @return Feature.
     */
    public Feature getFeature() {
        return this.feature;
    }
}