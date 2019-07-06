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
    private final PanelBaseVariability panelBaseVariability;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Variability.
     */
    public ControllerPanelBaseVariability(PanelBaseVariability panel) {
        super(panel);
        this.panelBaseVariability = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseVariability.getVariationPointComboBox().equals(event.getSource()))
            this.panelBaseVariability.setVariationPoint();
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseVariability.getNameTextField().equals(event.getSource()))
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseVariability.getNameTextField().equals(event.getSource()))
            this.update();
    }

    /**
     * Method responsible for checking the Variability.
     * @return Diagram checked.
     */
    private boolean check() {
        return this.check(this.panelBaseVariability.getNameTextField().getText());
    }
    
    /**
     * Method responsible for setting the Project Values.
     */
    public void update() {
        this.panelBaseVariability.getVariability().setName(this.panelBaseVariability.getNameTextField().getText().trim());
        this.panelBaseVariability.setVariationPoint();
        this.panelBaseVariability.getVariability().setBindingTime((String) this.panelBaseVariability.getBindingTimeComboBox().getSelectedItem());
        this.panelBaseVariability.getVariability().setAllowsBindingVar(this.panelBaseVariability.getAllowsAddingVarCheckBox().isSelected());
        this.panelBaseVariability.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseVariability.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseVariability.getDiagram());
        this.panelBaseVariability.getViewMenu().setSave(false);
    }
}