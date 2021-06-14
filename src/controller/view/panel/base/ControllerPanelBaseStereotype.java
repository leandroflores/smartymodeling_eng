package controller.view.panel.base;

import view.panel.base.PanelBaseStereotype;

/**
 * <p>Class of Controller <b>ControllerPanelBaseStereotype</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseStereotype</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-17
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.Stereotype
 * @see    view.panel.base.PanelBaseStereotype
 */
public class ControllerPanelBaseStereotype extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Stereotype.
     */
    public ControllerPanelBaseStereotype(PanelBaseStereotype panel) {
        super(panel);
    }
    
    @Override
    protected void refresh() {
        getPanelModeling().updateModelingPanels();
        super.refresh();
    }
    
    @Override
    protected void update() {
        getPanel().getStereotype().setName(getPanel().getNameTextField().getText().trim());
        refresh();
    }
    
    @Override
    public PanelBaseStereotype getPanel() {
        return (PanelBaseStereotype) panel;
    }
}