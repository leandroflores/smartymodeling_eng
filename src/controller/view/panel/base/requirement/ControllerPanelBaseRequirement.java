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
        this.getPanelTree().updateNode(this.getRequirement());
        super.refresh();
    }
    
    @Override
    protected void update() {
        this.getRequirement().setCode(this.getPanel().getCodeTextField().getText().trim());
        this.getRequirement().setType(this.getPanel().getTypeComboBox().getSelectedItem().toString());
        this.getRequirement().setName(this.getPanel().getNameTextField().getText().trim());
        this.getRequirement().setDescription(this.getPanel().getDescriptionTextArea().getText().trim());
        this.refresh();
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    private Requirement getRequirement() {
        return this.getPanel().getRequirement();
    }
    
    @Override
    public PanelBaseRequirement getPanel() {
        return (PanelBaseRequirement) this.panel;
    }
}