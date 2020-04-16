package controller.view.edit.panel.base.requirement;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.requirement.PanelBaseRequirement;

/**
 * <p>Class of Controller <b>ControllerPanelBaseRequirement</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseRequirement</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    controller.view.panel.ControllerPanel
 * @see    model.structural.base.requirement.Requirement
 * @see    view.edit.panel.base.requirement.PanelBaseRequirement
 */
public class ControllerPanelBaseRequirement extends ControllerPanel {
    private final PanelBaseRequirement panelBaseRequirement;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Requirement.
     */
    public ControllerPanelBaseRequirement(PanelBaseRequirement panel) {
        super(panel);
        this.panelBaseRequirement = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }

    /**
     * Method responsible for checking the Requirement.
     * @return Requirement checked.
     */
    private boolean check() {
        return    this.check(this.panelBaseRequirement.getCodeTextField().getText())
               && this.check(this.panelBaseRequirement.getNameTextField().getText())
               && this.check(this.panelBaseRequirement.getDescriptionTextArea().getText());
    }
    
    /**
     * Method responsible for setting the Requirement Values.
     */
    private void update() {
        this.panelBaseRequirement.getRequirement().setCode(this.panelBaseRequirement.getCodeTextField().getText().trim());
        this.panelBaseRequirement.getRequirement().setType(this.panelBaseRequirement.getTypeComboBox().getSelectedItem().toString());
        this.panelBaseRequirement.getRequirement().setName(this.panelBaseRequirement.getNameTextField().getText().trim());
        this.panelBaseRequirement.getRequirement().setDescription(this.panelBaseRequirement.getDescriptionTextArea().getText());
        this.panelBaseRequirement.getViewMenu().getPanelProject().getPanelTree().updateNode(this.panelBaseRequirement.getRequirement());
//        this.panelBaseRequirement.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseRequirement.getViewMenu().setSave(false);
    }
}