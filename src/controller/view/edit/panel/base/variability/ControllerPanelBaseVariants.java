package controller.view.edit.panel.base.variability;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.variability.PanelBaseVariants;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVariants</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseVariants</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/07/2019
 * @see    controller.view.panel.ControllerPanel
 * @see    view.edit.panel.base.variability.PanelBaseVariants
 */
public class ControllerPanelBaseVariants extends ControllerPanel {
    private final PanelBaseVariants panelBaseVariants;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Variants.
     */
    public ControllerPanelBaseVariants(PanelBaseVariants panel) {
        super(panel);
        this.panelBaseVariants = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelBaseVariants.getConstraintComboBox().equals(event.getSource()))
            this.panelBaseVariants.updateValues();
        else if (this.panelBaseVariants.getAddVariantButton().equals(event.getSource()))
            this.panelBaseVariants.addVariant();
        else if (this.panelBaseVariants.getDelVariantButton().equals(event.getSource()))
            this.panelBaseVariants.delVariant();
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseVariants.getConstraintComboBox().equals(event.getSource()))
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
        this.panelBaseVariants.getVariability().setConstraint(this.panelBaseVariants.getConstraintComboBox().getSelectedItem().toString());
        this.panelBaseVariants.getVariability().setMinimum(Integer.parseInt(this.panelBaseVariants.getMinimumTextField().getText().trim()));
        this.panelBaseVariants.getVariability().setMaximum(Integer.parseInt(this.panelBaseVariants.getMaximumTextField().getText().trim()));
        this.panelBaseVariants.getDiagram().updateElementsStereotype();
        this.panelBaseVariants.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseVariants.getDiagram());
//        this.panelBaseVariants.getViewMenu().getPanelProject().getPanelTree().getPanelTreeDiagram().updateNode(this.panelBaseVariants.getVariability());
        this.panelBaseVariants.getViewMenu().getPanelProject().getPanelTree().updateUI(); 
        this.panelBaseVariants.getViewMenu().setSave(false);
    }
}