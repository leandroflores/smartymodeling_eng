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
        super(panel.getViewMenu());
        this.diagram    = diagram;
        this.feature    = feature;
        this.controller = new ControllerViewEditFeature(this);
        this.title      = "Edit Feature Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditFeature createPanelEdit() {
        return new PanelEditFeature(view, diagram, feature);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 300);
    }
    
    @Override
    public PanelEditFeature getPanelEdit() {
        return (PanelEditFeature) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Feature.
     * @return Panel Base Feature.
     */
    public PanelBaseFeature getPanelBaseFeature() {
        return getPanelEdit().getPanelBaseFeature();
    }
    
    /**
     * Method responsible for returning the Feature.
     * @return Feature.
     */
    public Feature getFeature() {
        return feature;
    }
}