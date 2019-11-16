package controller.view.edit.panel.base.classes.association;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.edit.panel.base.classes.association.PanelBaseTarget;

/**
 * <p>Class of Controller <b>ControllerPanelBaseTarget</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseTarget</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  16/11/2019
 * @see    controller.view.ControllerPanel
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.edit.panel.base.classes.association.PanelBaseTarget
 */
public class ControllerPanelBaseTarget extends ControllerPanel {
    private final PanelBaseTarget panelBaseTarget;
    private final AssociationUML  associationUML;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Target.
     */
    public ControllerPanelBaseTarget(PanelBaseTarget panel) {
        super(panel);
        this.panelBaseTarget = panel;
        this.associationUML  = panel.getAssociationUML();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }
    
    /**
     * Method responsible for updating the Target Cardinality.
     * @return Source Cardinality.
     */
    private void updateCardinality() {
        String value = this.panelBaseTarget.getCardinalityTextField().getText().trim();
        if (this.checkCardinality(value)) {
            this.associationUML.setTargetMin(this.getMin(value));
            this.associationUML.setTargetMax(this.getMax(value));
        }
    }
    
    /**
     * Method responsible for setting the Target Association Values.
     */
    private void update() {
        this.panelBaseTarget.getAssociationUML().setTargetName(this.panelBaseTarget.getNameTextField().getText().trim());
        this.updateCardinality();
        this.panelBaseTarget.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseTarget.getViewMenu().getPanelModeling().updateModelingPanel();
        this.panelBaseTarget.getViewMenu().setSave(false);
    }
}