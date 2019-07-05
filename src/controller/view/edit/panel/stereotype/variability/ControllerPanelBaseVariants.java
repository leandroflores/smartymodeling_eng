package controller.view.edit.panel.stereotype.variability;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.variability.PanelBaseVariants;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVariants</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseVariants</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/07/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.variability.PanelBaseVariants
 */
public class ControllerPanelBaseVariants extends ControllerPanel {
    private final PanelBaseVariants panelVariants;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Variants.
     */
    public ControllerPanelBaseVariants(PanelBaseVariants panel) {
        super(panel);
        this.panelVariants = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelVariants.getConstraintComboBox().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
//        if (this.panelVariants.getConstraintComboBox().equals(event.getSource()))
    }
    
    /**
     * Method responsible for setting the Project Values.
     */
    private void update() {
//        this.panelVariability.getVariability().setName(this.panelVariability.getNameTextField().getText().trim());
//        this.panelVariability.getVariability().setVariationPoint((Element) this.panelVariability.getVariationPointComboBox().getSelectedItem());
//        this.panelVariability.getVariability().setBindingTime((String) this.panelVariability.getBindingTimeComboBox().getSelectedItem());
//        this.panelVariability.getVariability().setAllowsBindingVar(this.panelVariability.getAllowsAddingVarCheckBox().isSelected());
        this.panelVariants.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelVariants.getViewMenu().getPanelModeling().updateDiagram(this.panelVariants.getDiagram());
        this.panelVariants.getViewMenu().setSave(false);
    }
}