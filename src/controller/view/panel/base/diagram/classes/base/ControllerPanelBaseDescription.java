package controller.view.panel.base.diagram.classes.base;

import controller.view.panel.base.ControllerPanelBase;
import model.structural.diagram.classes.Entity;
import view.panel.base.diagram.classes.base.PanelBaseDescription;

/**
 * <p>Class of Controller <b>ControllerPanelBaseDescription</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseDescription</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-05-11
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.diagram.classes.Entity
 * @see    view.panel.base.diagram.classes.base.PanelBaseDescription
 */
public class ControllerPanelBaseDescription extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Description.
     */
    public ControllerPanelBaseDescription(PanelBaseDescription panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        getEntity().setDescription(getPanel().getDescriptionTextArea().getText().trim());
        refresh();
    }
    
    /**
     * Method responsible for returning the Entity.
     * @return Entity.
     */
    private Entity getEntity() {
        return getPanel().getEntity();
    }
    
    @Override
    public PanelBaseDescription getPanel() {
        return (PanelBaseDescription) panel;
    }
}