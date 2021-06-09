package controller.view.panel.base.variability;

import java.awt.event.ActionEvent;
import view.panel.base.variability.PanelBaseVariants;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVariants</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseVariants</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-05
 * @see    controller.view.panel.base.variability.ControllerPanelBase
 * @see    model.structural.base.variability.Variability
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
        if (ready) {
            if (getPanel().getConstraintComboBox().equals(event.getSource()))
                getPanel().updateValues();
            else if (getPanel().getAddVariantButton().equals(event.getSource()))
                getPanel().addVariant();
            else if (getPanel().getDelVariantButton().equals(event.getSource()))
                getPanel().delVariant();
            update();
        }
    }
    
    @Override
    protected void update() {
        getVariability().setConstraint(getValue(getPanel().getConstraintComboBox()));
        getVariability().setMinimum(getInteger(getPanel().getMinimumTextField()));
        getVariability().setMaximum(getInteger(getPanel().getMaximumTextField()));
        getPanel().getDiagram().updateElementsStereotype();
        refresh();
    }
    
    @Override
    public PanelBaseVariants getPanel() {
        return (PanelBaseVariants) panel;
    }
}