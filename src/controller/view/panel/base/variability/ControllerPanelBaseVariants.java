package controller.view.panel.base.variability;

import controller.view.panel.base.ControllerPanelBase;
import java.awt.event.ActionEvent;
import model.structural.base.variability.Variability;
import view.panel.base.variability.PanelBaseVariants;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVariants</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseVariants</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-05
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.base.variability.PanelBaseVariants
 */
public class ControllerPanelBaseVariants extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Variants.
     */
    public ControllerPanelBaseVariants(PanelBaseVariants panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.getPanel().getConstraintComboBox().equals(event.getSource()))
            this.getPanel().updateValues();
        else if (this.getPanel().getAddVariantButton().equals(event.getSource()))
            this.getPanel().addVariant();
        else if (this.getPanel().getDelVariantButton().equals(event.getSource()))
            this.getPanel().delVariant();
        if (this.ready)
            this.update();
    }
    
    @Override
    protected void refresh() {
        this.getPanelTree().updateNode(this.getVariability());
        this.getPanelModeling().updateDiagram(this.getPanel().getDiagram());
        super.refresh();
    }
    
    @Override
    protected void update() {
        this.getVariability().setConstraint(this.getValue(this.getPanel().getConstraintComboBox()));
        this.getVariability().setMinimum(this.getInteger(this.getPanel().getMinimumTextField()));
        this.getVariability().setMaximum(this.getInteger(this.getPanel().getMaximumTextField()));
        this.getPanel().getDiagram().updateElementsStereotype();
        this.refresh();
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    private Variability getVariability() {
        return this.getPanel().getVariability();
    }
    
    @Override
    public PanelBaseVariants getPanel() {
        return (PanelBaseVariants) this.panel;
    }
}