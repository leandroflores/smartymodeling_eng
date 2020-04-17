package controller.view.panel.base.diagram;

import view.panel.base.diagram.PanelBaseAssociation;

/**
 * <p>Class of Controller <b>ControllerPanelBaseAssociation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseAssociation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-07
 * @see    controller.view.panel.base.ControllerPanelBaseAssociation
 * @see    view.panel.base.diagram.PanelBaseAssociation
 */
public class ControllerPanelBaseAssociation extends controller.view.panel.base.ControllerPanelBaseAssociation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Association.
     */
    public ControllerPanelBaseAssociation(PanelBaseAssociation panel) {
        super(panel);
    }

    @Override
    protected void update() {}
}