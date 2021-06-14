package controller.view.panel.base.diagram.feature.base;

import controller.view.panel.base.ControllerPanelBaseElement;
import model.structural.diagram.feature.base.Feature;
import view.panel.base.diagram.feature.base.PanelBaseFeature;

/**
 * <p>Class of Controller <b>ControllerPanelBaseFeature</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseFeature</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-17
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    model.structural.diagram.feature.base.Feature
 * @see    view.panel.base.diagram.feature.base.PanelBaseFeature
 */
public class ControllerPanelBaseFeature extends ControllerPanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Feature.
     */
    public ControllerPanelBaseFeature(PanelBaseFeature panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        getFeature().setName(getString(getPanel().getNameTextField()));
        getFeature().setAbstract(getPanel().getAbstractCheckBox().isSelected());
        refresh();
    }
    
    /**
     * Method responsible for returning the Feature.
     * @return Feature.
     */
    private Feature getFeature() {
        return getPanel().getElement();
    }
    
    @Override
    public PanelBaseFeature getPanel() {
        return (PanelBaseFeature) panel;
    }
}