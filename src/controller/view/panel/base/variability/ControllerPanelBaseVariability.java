package controller.view.panel.base.variability;

import java.awt.event.ActionEvent;
import view.panel.base.variability.PanelBaseVariability;

/**
 * <p>Class of Controller <b>ControllerPanelBaseVariability</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseVariability</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.panel.base.variability.ControllerPanelBase
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
    public void actionPerformed(ActionEvent event) {
        if (this.ready) {
            if (this.getPanel().getVariationPointComboBox().equals(event.getSource()))
                this.getPanel().setVariationPoint();
            this.update();
        }
    }
    
    @Override
    protected void update() {
        this.getVariability().setName(this.getString(this.getPanel().getNameTextField()));
        this.getPanel().setVariationPoint();
        this.getVariability().setBindingTime(this.getPanel().getBindingTimeComboBox().getSelectedItem().toString());
        this.refresh();
    }
    
    @Override
    public PanelBaseVariability getPanel() {
        return (PanelBaseVariability) this.panel;
    }
}