package controller.view.panel.base.requirement;

import controller.view.panel.base.ControllerPanelBase;
import model.structural.base.requirement.Requirement;
import view.panel.base.requirement.PanelBaseRequirement;

/**
 * <p>Class of Controller <b>ControllerPanelBaseRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseRequirement</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.requirement.Requirement
 * @see    view.panel.base.requirement.PanelBaseRequirement
 */
public class ControllerPanelBaseRequirement extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Requirement.
     */
    public ControllerPanelBaseRequirement(PanelBaseRequirement panel) {
        super(panel);
    }
    
    @Override
    protected void refresh() {
        getPanelTree().updateNode(getRequirement());
        super.refresh();
    }
    
    @Override
    protected void update() {
        getRequirement().setCode(getPanel().getCodeTextField().getText().trim());
        getRequirement().setType(getPanel().getTypeComboBox().getSelectedItem().toString());
        getRequirement().setName(getPanel().getNameTextField().getText().trim());
        getRequirement().setDescription(getPanel().getDescriptionTextArea().getText().trim());
        refresh();
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    private Requirement getRequirement() {
        return getPanel().getRequirement();
    }
    
    @Override
    public PanelBaseRequirement getPanel() {
        return (PanelBaseRequirement) panel;
    }
}