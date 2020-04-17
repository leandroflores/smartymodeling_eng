package controller.view.panel.base.product;

import controller.view.panel.base.ControllerPanelBase;
import view.panel.base.product.PanelBaseRelationship;

/**
 * <p>Class of Controller <b>ControllerPanelBaseRelationship</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseRelationship</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-14
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.product.Relationship
 * @see    view.panel.base.product.PanelBaseRelationship
 */
public class ControllerPanelBaseRelationship extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Relationship.
     */
    public ControllerPanelBaseRelationship(PanelBaseRelationship panel) {
        super(panel);
    }

    @Override
    protected void update() {}
}