package controller.view.panel.base.variability;

import java.awt.event.ActionEvent;
import view.panel.base.variability.PanelBaseVariability;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVariability</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseVariability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.panel.base.variability.ControllerPanelBase
 * @see    model.structural.base.variability.Variability
 * @see    view.panel.base.variability.PanelBaseVariability
 */
public class ControllerPanelBaseVariability extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Variability.
     */
    public ControllerPanelBaseVariability(PanelBaseVariability panel) {
        super(panel);
    }
    
    @Override
    public void setReady() {
        ready = true;
        getPanel().setVariationPoint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (ready && getPanel().getVariationPointComboBox().equals(event.getSource())) {
            getPanel().setVariationPoint();
            update();
        }
    }
    
    @Override
    protected void update() {
        getVariability().setName(getString(getPanel().getNameTextField()));
        getPanel().setVariationPoint();
        getVariability().setBindingTime(getPanel().getBindingTimeComboBox().getSelectedItem().toString());
        refresh();
    }
    
    @Override
    public PanelBaseVariability getPanel() {
        return (PanelBaseVariability) panel;
    }
}