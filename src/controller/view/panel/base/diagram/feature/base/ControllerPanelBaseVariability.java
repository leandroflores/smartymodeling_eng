package controller.view.panel.base.diagram.feature.base;

import controller.view.panel.base.ControllerPanelBaseElement;
import model.structural.diagram.feature.base.Variability;
import view.panel.base.diagram.feature.base.PanelBaseVariability;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVariability</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseVariability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-06-29
 * @see    controller.view.panel.base.ControllerPanelBaseElement
 * @see    model.structural.diagram.feature.base.Variability
 * @see    view.panel.base.diagram.feature.base.PanelBaseVariability
 */
public class ControllerPanelBaseVariability extends ControllerPanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Variability.
     */
    public ControllerPanelBaseVariability(PanelBaseVariability panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        refresh();
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    private Variability getVariability() {
        return getPanel().getElement();
    }
    
    @Override
    public PanelBaseVariability getPanel() {
        return (PanelBaseVariability) panel;
    }
}