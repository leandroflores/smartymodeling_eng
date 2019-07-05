package controller.view.edit.panel.stereotype.variability;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.variability.PanelBaseVariability;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVariability</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseVariability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/07/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.variability.PanelBaseVariability
 */
public class ControllerPanelBaseVariability extends ControllerPanel {
    private final PanelBaseVariability panelVariability;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Variability.
     */
    public ControllerPanelBaseVariability(PanelBaseVariability panel) {
        super(panel);
        this.panelVariability = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelVariability.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelVariability.getNameTextField().equals(event.getSource()))
            this.update();
    }

    /**
     * Method responsible for checking the Variability.
     * @return Diagram checked.
     */
    private boolean check() {
        return this.check(this.panelVariability.getNameTextField().getText());
    }
    
    /**
     * Method responsible for setting the Project Values.
     */
    private void update() {
//        this.panelVariability.getVariability().setName(this.panelVariability.getNameTextField().getText().trim());
//        this.panelVariability.getVariability().setVariationPoint((Element) this.panelVariability.getVariationPointComboBox().getSelectedItem());
//        this.panelVariability.getVariability().setBindingTime((String) this.panelVariability.getBindingTimeComboBox().getSelectedItem());
//        this.panelVariability.getVariability().setAllowsBindingVar(this.panelVariability.getAllowsAddingVarCheckBox().isSelected());
        this.panelVariability.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelVariability.getViewMenu().getPanelModeling().updateDiagram(this.panelVariability.getDiagram());
        this.panelVariability.getViewMenu().setSave(false);
    }
}